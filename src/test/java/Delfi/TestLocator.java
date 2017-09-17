package Delfi;

import com.sun.net.httpserver.Authenticator;
import org.openqa.selenium.*; //selected all - Selenium
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import java.util.*; //selected all - required for arrays
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.chrome.ChromeDriver; <--because we are using only firefox
//import org.openqa.selenium.ie.InternetExplorerDriver; <--because we are using only firefox
//import org.openqa.selenium.By; <--because we are using *
//import org.openqa.selenium.WebDriver; <--because we are using *
//import org.testng.annotations.Test; <--because we are using *
//import org.openqa.selenium.firefox.FirefoxDriver; <--because we are using *

public class TestLocator {
    private String URL = "http://m.delfi.lv/laika-zinas/article.php?id=49247479"; //define desktop website url

    private List<String> desktopTitle = new ArrayList<String>(); //define desktop array
    private List<String> mobileTitle = new ArrayList<String>(); //define mobile array

    private String titleDesktop;
    private String titleMobile;
    private String findTitle;




    @Test()
    public void DelfiPostMethod() {
        //path to firefox driver
        System.setProperty("webdriver.gecko.driver", "G:\\Project_QA\\_Driver\\geckodriver.exe");

        WebDriver fireFox = new FirefoxDriver(); //define driver as varieble "fireFox"
        fireFox.manage().window().maximize(); //open browser and maximize the window
        fireFox.get(URL); //go to url

        findTitle = fireFox.findElement(By.xpath("//h1")).getText();
        System.out.println(findTitle);
    }
}