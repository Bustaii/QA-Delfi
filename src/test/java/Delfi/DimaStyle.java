package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.Assert;
import org.testng.annotations.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import java.util.*; //selected all - required for arrays

public class DimaStyle {
    private String WEB_URL = "http://www.delfi.lv/"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By ARTICLE_TITLE = By.xpath("//a[@class='top2012-title']");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//a[@class='comment-count']");

    private static final By ARTICLE_MOBILE = By.xpath("//div[@class='md-mosaic-title']");
    private static final By ARTICLE_TITLE_MOBILE = By.xpath("//a[@class='md-scrollpos']");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE = By.xpath("//a[@class='commentCount']");

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

        List<String> titlesArray = new ArrayList<String>();
        List<Integer> countArray = new ArrayList<Integer>();

        List<String> titlesArrayMobile = new ArrayList<String>();
        List<Integer> countArrayMobile = new ArrayList<Integer>();

        List<WebElement> articleList = driver.findElements(ARTICLE);
        for (int i = 0; i < 5; i++) {
            WebElement element = articleList.get(i);
            String title = element.findElements(ARTICLE_TITLE).get(i).getText();
            titlesArray.add(title);

            if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElements(ARTICLE_COMMENT_COUNT).get(i).getText(); //(106)
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')')); //106
                countArray.add(Integer.valueOf(countToParse));
            } else {
                countArray.add(0);
            }
        }

        driver.switchTo().window(browserTabs.get(1)); // switch to new tab [1] second tab

        List<WebElement> articleListMobile = driver.findElements(ARTICLE_MOBILE);
        for (int i = 0; i < 5; i++) {
            WebElement elementMobile = articleListMobile.get(i);
            String title = elementMobile.findElements(ARTICLE_TITLE_MOBILE).get(i).getText();
            titlesArrayMobile.add(title);

            if (elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).size() != 0) {
                String countToParse = elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).get(i).getText(); //(106)
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')')); //106
                countArrayMobile.add(Integer.valueOf(countToParse));
            } else {
                countArrayMobile.add(0);
            }
        }

        //WEB
        System.out.println(titlesArray); // print array
        System.out.println(countArray); // print array
        //MOBILE
        System.out.println(titlesArrayMobile); // print array
        System.out.println(countArrayMobile); // print array


        Assert.assertEquals(titlesArray, titlesArrayMobile); // compare two arrays of titles
        Assert.assertEquals(countArray, countArrayMobile); // compare two arrays of titles
         // compare two arrays of titles
//        if (Arrays.equals(countArray, countArrayMobile) ) {
//        } else {
//            System.out.println("GOOD"); // print array
//        }


//idsToCheck.add(Long.valueOf(element.findElement(ARTICLE_TITLE).getAttribute("href").substring()));

        //Kak perejti na tretju statju iz lista
//        driver.get("http://delfi.lv/blablabla?id=" + idsToCheck.get(2));
//
//        for (int i = 0; i < titles.size(); i++) {
//            Assert.assertEquals("Title is not equals for an article: " + titles.get(i), titles.get(i), mobileTitles.get(i));
//            Assert.assertEquals();
//        }
        driver.quit(); //close browser
    }
}

