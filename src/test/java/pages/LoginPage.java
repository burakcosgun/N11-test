package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends  BasePage
{
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAttempt (String userName,String password){
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }

    public void openLoginPage(){
        driver.findElement(By.linkText("Giriş Yap")).click();
    }

    public void getPage(String pageURL) {
        WebDriverWait wait=  new WebDriverWait(driver,15);

        driver.get(pageURL);
        waitForPageLoad();
    }
}
