package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.Assert;
import org.testng.annotations.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import java.util.*; //selected all - required for arrays

//import org.openqa.selenium.chrome.ChromeDriver; <--because we are using only firefox
//import org.openqa.selenium.ie.InternetExplorerDriver; <--because we are using only firefox
//import org.openqa.selenium.By; <--because we are using *
//import org.openqa.selenium.WebDriver; <--because we are using *
//import org.testng.annotations.Test; <--because we are using *
//import org.openqa.selenium.firefox.FirefoxDriver; <--because we are using *

public class TestDelfiPosts {
    private String WEB_URL = "http://delfi.lv"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

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
        fireFox.get(WEB_URL); //go to url

        //open new tab by clicking on logo image
        String newTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        fireFox.findElement(By.xpath("//a[@class='headerLogo']")).sendKeys(newTab);

        //create list of elements "postTitleList" DESKTOP version
        List<WebElement> postTitleList = fireFox.findElements(By.xpath("//h3/a[@class='top2012-title']"));
        List<WebElement> postCommentsList = fireFox.findElements(By.xpath("//h3/a[@class='comment-count']"));
        for (int i = 0; i < 5; i++) {
            desktopTitle.add (postTitleList.get(i).getText() + postCommentsList.get(i).getText()); //get titles and comment from first five elements from the list and store it into array
            //System.out.println(postTitleList.get(i).getText() + postCommentsList.get(i).getText());
            //System.out.println(desktopTitle); //show array content
        }


        //create array of tabs - defined as "browserTabs"
        ArrayList<String> browserTabs = new ArrayList<String> (fireFox.getWindowHandles());
        while (browserTabs.size() != 2) { //add check if there are less then 2 tabs active will wait until
            browserTabs.clear();
            browserTabs.addAll(fireFox.getWindowHandles());
        }
        fireFox.switchTo().window(browserTabs.get(1)); // switch to new tab 0=first tab, 1=second tab
        fireFox.get(MOB_URL); //open this url in new tab


        //create list of elements "postTitleList" MOBILE version
        List<WebElement> postTitleListMobile = fireFox.findElements(By.xpath("//a[@class='md-scrollpos']"));
        List<WebElement> postCommentsListMobile = fireFox.findElements(By.xpath("//a[@class='commentCount']"));
        for (int i = 0; i < 5; i++) {
            mobileTitle.add (postTitleListMobile.get(i).getText() + postCommentsListMobile.get(i).getText()); //get titles and comment from first five elements from the list and store it into array
            //System.out.println(postTitleListMobile.get(i).getText() + postCommentsListMobile.get(i).getText());
            //System.out.println(mobileTitle); //show array content
        }


        Assert.assertEquals(desktopTitle, mobileTitle);
            System.out.println("\n"); // add linebreak
            System.out.println("DESKTOP ARRAY - " + desktopTitle); //show array content desktop
            System.out.println("is equal to"); // add linebreak with copy is equal to
            System.out.println("MOBILE ARRAY - " + mobileTitle); //show array content mobile
            System.out.println("\n"); // add linebreak


        for (int i = 0; i < 5; i++) {
            titleMobile = postTitleListMobile.get(i).getText(); //select first title and store into "titleMobile" var
            postTitleListMobile.get(i).sendKeys(newTab); //open trird tab with post page open
                while (browserTabs.size() != 3) { //add check if there are less then 3 tabs active will wait until
                browserTabs.clear();
                browserTabs.addAll(fireFox.getWindowHandles());
            }
            fireFox.switchTo().window(browserTabs.get(2)); //switch to third tab
            System.out.println(titleMobile); //check if var still contains correct value
            findTitle= fireFox.findElement(By.className("article-title")).getText();
            //Assert.assertEquals(titleMobile, findTitle);
        }

        //browserTabs.clear(); //clear tabs array
        //fireFox.quit(); //close browser
    }
}

//postTitleList.get(0).sendKeys(newTab); //open first post in new tab
//selectedElement.sendKeys(newTab); //open each element with click event in new tab
//List<WebElement> postTitleList = fireFox.findElements(By.xpath("(//*[contains(@class, 'top2012')])[1]//h3/a[@class='top2012-title']"));

//WebElement selectedElement = postTitleList.get(i);
//System.out.println(selectedElement.getText()); //print result in console

//    WebDriverWait wait = new WebDriverWait(fireFox, 15);
//    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findTitle));
//                element.clear();
//                        element.sendKeys("findTitle");
//                        System.out.println("waited 10 sec");


//public class retryTest implements IRetryAnalyzer {
//    int minretryCount=0; //set counter to 0
//    int maxretryCount=2; //set maxcounter value this will execute our test 3 times
//
//    //retry Method
//    public boolean retry(ITestResult result) {
//        if(minretryCount<=maxretryCount) { // this will run until max count completes if test pass within this frame it will come out of for loop
//            System.out.println("Following test is failing===="+result.getName()); // print the test name for log purpose
//            System.out.println("Retrying the test Count is=== "+ (minretryCount+1));  // print the counter value
//            minretryCount++; // increment counter each time by 1
//            return true;
//        }
//        return false;
//    }
//}
//@Test(retryAnalyzer=retryTest.class)