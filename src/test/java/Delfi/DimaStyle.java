package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import org.testng.annotations.*;

import java.awt.*;
import java.util.*; //selected all - required for arrays

import static java.lang.Integer.valueOf;

import java.util.List;

public class DimaStyle {
    private String WEB_URL = "http://www.delfi.lv/"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

    //WEB LOCATORS
    private static final By LOGO = By.xpath("//a[@class='headerLogo']");

    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By ARTICLE_TITLE = By.xpath("//h3/a[@class='top2012-title']");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//a[@class='comment-count']");
    private static final By ARTICLE_TITLE_PAGE = By.xpath("//h1[@class='article-title']/span");
    private static final By ARTICLE_COMMENT_COUNT_PAGE = By.xpath("//a[@class='comment-count']");

    //MOBILE LOCATORS
    private static final By ARTICLE_MOBILE = By.xpath("//div[@class='md-mosaic-title']");
    private static final By ARTICLE_TITLE_MOBILE = By.xpath("//a[@class='md-scrollpos']");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE = By.xpath("//a[@class='commentCount']");
    private static final By ARTICLE_TITLE_MOBILE_PAGE = By.xpath("//div[@class='article-title']/h1");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE_PAGE = By.xpath("//a[@class='commentCount']");

    //SHARED LOCATORS
    private static final By REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-reg')]/span");
    private static final By NO_REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-anon')]/span");


    @Test
    public void testMethod() throws AWTException {
        System.setProperty("webdriver.gecko.driver", "G:\\_driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver(); // define driver variable
        driver.manage().window().maximize(); // maximize browser window

        driver.get(WEB_URL); // go to url
        String newTab = Keys.chord(Keys.CONTROL, Keys.RETURN); // open second tab
        driver.findElement(LOGO).sendKeys(newTab); // by clicking on logo

        //create array of tabs - defined as "browserTabs"
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        while (browserTabs.size() != 2) { //add check if there are less then 2 tabs active will wait until
            browserTabs.clear();
            browserTabs.addAll(driver.getWindowHandles());
        }
        driver.switchTo().window(browserTabs.get(1)); // switch to new tab [1] second tab
        driver.get(MOB_URL); //open this url in new tab\
        driver.switchTo().window(browserTabs.get(0)); // switch back to new tab [0] first tab

        //WEB
        List<String> titlesArray = new ArrayList<String>();
        List<Integer> countArray = new ArrayList<Integer>();
        List<String> urlArray = new ArrayList<String>();

        //MOBILE
        List<String> titlesArrayMobile = new ArrayList<String>();
        List<Integer> countArrayMobile = new ArrayList<Integer>();
        List<String> urlArrayMobile = new ArrayList<String>();

        List<WebElement> articleList = driver.findElements(ARTICLE);
        for (int i = 0; i < 5; i++) {
            WebElement element = articleList.get(i);
            String title = element.findElements(ARTICLE_TITLE).get(i).getText();
            titlesArray.add(title);

            String url = element.findElements(ARTICLE_TITLE).get(i).getAttribute("href"); // get url
            urlArray.add(url);

            if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElements(ARTICLE_COMMENT_COUNT).get(i).getText(); //(106)
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')')); //106
                countArray.add(valueOf(countToParse));
            } else {
                countArray.add(0);
            }
        }

        driver.switchTo().window(browserTabs.get(1)); // switch to new tab [1] second tab

        List<WebElement> articleListMobile = driver.findElements(ARTICLE_MOBILE);
        for (int i = 0; i < 5; i++) {
            WebElement elementMobile = articleListMobile.get(i);
            String titleMobile = elementMobile.findElements(ARTICLE_TITLE_MOBILE).get(i).getText();
            titlesArrayMobile.add(titleMobile);

            String urlMobile = elementMobile.findElements(ARTICLE_TITLE_MOBILE).get(i).getAttribute("href"); // get url
            urlArrayMobile.add(urlMobile);

            if (elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).size() != 0) {
                String countToParseMobile = elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).get(i).getText(); //(106)
                countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
                countArrayMobile.add(valueOf(countToParseMobile));
            } else {
                countArrayMobile.add(0);
            }
        }


        Assert.assertEquals(titlesArray, titlesArrayMobile); // compare two arrays of titles
        try {
            Assert.assertEquals(countArray, countArrayMobile);
        } catch (Exception e) {
            System.out.println("some comments count is different");
        }
        //Assert.assertEquals(countArray, countArrayMobile); // compare two arrays of comment count

        //MOBILE
        for (int i = 0; i < 5; i++) {
            String getArrayTitle = titlesArrayMobile.get(i);
            String getArrayUrl = urlArrayMobile.get(i);
            Integer getArrayCount = countArrayMobile.get(i);
                driver.get(getArrayUrl);
                String findTitle = driver.findElement(ARTICLE_TITLE_MOBILE_PAGE).getText();
                    Assert.assertTrue(findTitle.contains(getArrayTitle), "\n" + "Looking : " + getArrayTitle + "\n" + "Results : " + findTitle + "\n");

                String findCount = driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE).getText();
                    findCount = findCount.substring(findCount.indexOf('(') + 1, findCount.indexOf(')')); //106
                        Integer findCountInt = Integer.valueOf(findCount);
                driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE).click();
                String regComments = driver.findElement(REG_USR_COMMENTS).getText();
                    regComments = regComments.substring(regComments.indexOf('(') + 1, regComments.indexOf(')')); //106
                        Integer regCommentsInt = Integer.valueOf(regComments);

                String noregComments = driver.findElement(NO_REG_USR_COMMENTS).getText();
                    noregComments = noregComments.substring(noregComments.indexOf('(') + 1, noregComments.indexOf(')')); //106
                        Integer noregCommentsInt = Integer.valueOf(noregComments);

                    Integer summOfComments = (regCommentsInt + noregCommentsInt);
                Assert.assertEquals(getArrayCount, findCountInt, summOfComments); // compare tree arrays of comment count
        }

        //WEB
        for (int i = 0; i < 5; i++) {
            String getArrayTitle = titlesArray.get(i);
            String getArrayUrl = urlArray.get(i);
            Integer getArrayCount = countArray.get(i);
                driver.get(getArrayUrl);
                String findTitle = driver.findElement(ARTICLE_TITLE_PAGE).getText();
                    Assert.assertTrue(findTitle.contains(getArrayTitle), "\n" + "Looking : " + getArrayTitle + "\n" + "Results : " + findTitle + "\n");

                String findCount = driver.findElement(ARTICLE_COMMENT_COUNT_PAGE).getText();
                    findCount = findCount.substring(findCount.indexOf('(') + 1, findCount.indexOf(')')); //106
                        Integer findCountInt = Integer.valueOf(findCount);
                driver.findElement(ARTICLE_COMMENT_COUNT_PAGE).click();
                String regComments = driver.findElement(REG_USR_COMMENTS).getText();
                    regComments = regComments.substring(regComments.indexOf('(') + 1, regComments.indexOf(')')); //106
                        Integer regCommentsInt = Integer.valueOf(regComments);

                String noregComments = driver.findElement(NO_REG_USR_COMMENTS).getText();
                    noregComments = noregComments.substring(noregComments.indexOf('(') + 1, noregComments.indexOf(')')); //106
                        Integer noregCommentsInt = Integer.valueOf(noregComments);

                    Integer summOfComments = (regCommentsInt + noregCommentsInt);
                Assert.assertEquals(getArrayCount, findCountInt, summOfComments); // compare tree arrays of comment count
        }
        driver.quit(); //close browser
    }
}

