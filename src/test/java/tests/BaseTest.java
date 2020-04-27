package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected ChromeOptions chromeOptions;

    final static String MAAIN_PAGE_URL="https://www.n11.com/";
    final static String VALID_EMAIL_DUMMY= "dummynamejanedoe@gmail.com";
    final static String VALID_USERNAME_DUMMY= "Jane Doe";
    final static String VALID_PASSWORD_DUMMY= "dummypassword1";

    @Before
    public void setUpBeforeTestCase() throws InterruptedException {

        //Settings for Chrome as browser option
        /*System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver.exe");

        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-automatic-password-saving");
        chromeOptions.addArguments("allow-silent-push");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking"); */

        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(MAAIN_PAGE_URL);


        // close KVKK alert if exist
        List<WebElement> element = driver.findElements(By.cssSelector("#userKvkkModal > .content > .closeBtn"));
        boolean elementExist = element.size() != 0;

        while (elementExist) {
            element.get(0).click();
            Thread.sleep(3000);
            element = driver.findElements(By.cssSelector("#userKvkkModal > .content > .closeBtn"));
            elementExist = element.size() != 0;
        }
    }

    @After
    public void tearDownAfterTestCase() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
