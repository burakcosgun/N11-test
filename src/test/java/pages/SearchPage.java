package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SearchPage extends BasePage{
    private Actions builder;
    String productId = null;

    public SearchPage(WebDriver driver) {

        this.driver = driver;
        builder=new Actions(driver);
    }

    public void search(String searchText) {

        driver.findElement(By.id("searchData")).clear();
        driver.findElement(By.id("searchData")).sendKeys(searchText);
        driver.findElement(By.cssSelector(".iconSearch")).click();

        waitForPageLoad();
    }

    public void goSearchPage(int pageIndex) throws InterruptedException {

        String pageXPath = " //*[@class='pagination']/a[" + Integer.toString(pageIndex) + "]";

        driver.findElement(By.xpath(pageXPath)).click();
        waitForPageLoad();
    }

    public String addFavouriteSearchedProduct(int productIndex){

        WebDriverWait wait=  new WebDriverWait(driver,10);

        WebElement toBeAddedElement;
        String productXPath;

        productXPath="//*[@id=\"view\"]/ul/li["+Integer.toString(productIndex)+"]";
        toBeAddedElement= driver.findElement(By.xpath(productXPath));
        wait.until(ExpectedConditions.visibilityOf(toBeAddedElement));

        productId = toBeAddedElement.findElement((By.xpath("div"))).getAttribute("id");

        driver.findElement(By.cssSelector("#" + productId + " .followBtn")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return productId;
    }

    public void goToFavouritePage(){

        WebDriverWait wait=  new WebDriverWait(driver,10);

        WebElement menu = driver.findElement(By.cssSelector(".myAccount"));

        wait.until(ExpectedConditions.visibilityOf(menu));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", menu);
        builder.moveToElement(menu).perform();

        //click favorite on dropdown menu
        WebElement subelement= driver.findElement(By.linkText("Favorilerim / Listelerim"));
        wait.until(ExpectedConditions.elementToBeClickable(subelement));
        subelement.click();

        //click favorite on listelerim
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myAccount")));
        WebElement favelement=driver.findElement(By.cssSelector(".wishGroupListItem.favorites .listItemWrap > a"));
        wait.until(ExpectedConditions.elementToBeClickable(favelement));
        favelement.click();
    }
}
