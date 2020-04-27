package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FavouritePage extends BasePage {

    Actions builder;

    public FavouritePage(WebDriver driver) {

        this.driver = driver;

        builder= new Actions(driver);
    }

    public void removeFavouritedItem(String productId)
    {
        WebDriverWait wait=  new WebDriverWait(driver,15);

        System.out.println("productId: " + productId);
        driver.findElement(By.cssSelector("#"+productId+" .deleteProFromFavorites")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".confirm")));
        driver.findElement(By.cssSelector(".confirm")).click();
    }

    public void logout(){

        WebDriverWait wait=  new WebDriverWait(driver,10);

        WebElement menu = driver.findElement(By.cssSelector(".myAccount"));

        wait.until(ExpectedConditions.visibilityOf(menu));
        builder.moveToElement(menu).perform();

        //click logout on dropdown menu
        WebElement subelement= driver.findElement(By.linkText("Çıkış Yap"));
        wait.until(ExpectedConditions.elementToBeClickable(subelement));
        subelement.click();
    }

    public boolean isExistProductInFavList(String productId){

        driver.navigate().refresh();
        List<WebElement> favProductList = driver.findElements(By.xpath("//*[@id=\"view\"]/ul/li"));

        for (WebElement favProd:favProductList){
            if(productId.equals(favProd.findElement(By.xpath("div")).getAttribute("id"))){
                return  true;
            }
        }
        return false;
    }
}
