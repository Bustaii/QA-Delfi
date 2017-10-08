package PageObject.Pages;

import PageObject.PageObjectClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*; //selected all - Selenium
import org.testng.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import org.testng.annotations.*;
import java.util.*; //selected all - required for arrays
import static java.lang.Integer.valueOf;
import java.util.List;

public class BaseFunctions {
    WebDriver driver;
    private static final String FIREFOX_DRIVER_PATH = "G:\\_driver/geckodriver.exe"; //define driver path to
    private static final Logger LOG = LogManager.getLogger(BaseFunctions.class); //define loger

    //CONSTRUCTOR
    public BaseFunctions() {
        LOG.info("Setting system properties");
        System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH); //path to driver.exe

        LOG.info("Setting driver");
        this.driver = new FirefoxDriver();

        LOG.info("Open browser and maximize it");
        driver.manage().window().maximize(); //maximize browser window
    }

    public void goToURL(String url) {
        LOG.info("Open URL: " + url);
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        LOG.info("Getting element ");
        return driver.findElement(locator);
    }
}
