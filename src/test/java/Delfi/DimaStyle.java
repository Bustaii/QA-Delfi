package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.Assert;
import org.testng.annotations.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import java.util.*; //selected all - required for arrays

import static java.lang.Integer.valueOf;

public class DimaStyle {
    private String WEB_URL = "http://www.delfi.lv/"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

    //WEB
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By ARTICLE_TITLE = By.xpath("//a[@class='top2012-title']");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//a[@class='comment-count']");
    private static final By ARTICLE_TITLE_PAGE = By.xpath("//h1[@class='article-title']/span");
    private static final By ARTICLE_COMMENT_COUNT_PAGE = By.xpath("//a[@class='comment-count']");

    //MOBILE
    private static final By ARTICLE_MOBILE = By.xpath("//div[@class='md-mosaic-title']");
    private static final By ARTICLE_TITLE_MOBILE = By.xpath("//a[@class='md-scrollpos']");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE = By.xpath("//a[@class='commentCount']");
    private static final By ARTICLE_TITLE_MOBILE_PAGE = By.xpath("//div[@class='article-title']/h1");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE_PAGE = By.xpath("//a[@class='commentCount']");

    @Test
    public void testMethod() {
        System.setProperty("webdriver.gecko.driver", "G:\\_driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        driver.get(WEB_URL);
        String newTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        driver.findElement(By.xpath("//a[@class='headerLogo']")).sendKeys(newTab);

        //create array of tabs - defined as "browserTabs"
        List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
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

        //WEB
        System.out.println(titlesArray); // print array
        System.out.println(countArray); // print array
        System.out.println(urlArray); // print array
        //MOBILE
        System.out.println(titlesArrayMobile); // print array
        System.out.println(countArrayMobile); // print array
        System.out.println(urlArrayMobile); // print array

        //Assert.assertEquals(titlesArray, titlesArrayMobile); // compare two arrays of titles
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
            Assert.assertEquals(getArrayCount, findCountInt); // compare two arrays of comment count
            System.out.println(findCount); // print array
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
            Assert.assertEquals(getArrayCount, findCountInt); // compare two arrays of comment count
            System.out.println(findCount); // print array
        }
        driver.quit(); //close browser
    }
}

