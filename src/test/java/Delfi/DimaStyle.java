package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.Assert;
import org.testng.annotations.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import java.util.*; //selected all - required for arrays

public class DimaStyle {
    private String WEB_URL = "http://delfi.lv"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By ARTICLE_TITLE = By.xpath("//a[@class='top2012-title']");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//a[@class='comment-count']");


    @Test
    public void testMethod() {
        System.setProperty("webdriver.gecko.driver", "C:\\_driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();


        driver.get("http://www.delfi.lv/");



        List<String> titlesArray = new ArrayList<String>();
        List<Integer> countArray = new ArrayList<Integer>();
//
//        List<String> mobileTitles = new ArrayList<String>();
//        List<Integer> mobileCounts = new ArrayList<Integer>();
//        List<Long> idsToCheck = new ArrayList<Long>();

        List<WebElement> articleList = driver.findElements(ARTICLE);
        for (int i = 0; i < 5; i++) {
            WebElement element = articleList.get(i);
            String title = element.findElements(ARTICLE_TITLE).get(i).getText();
            titlesArray.add(title);

            if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElement(ARTICLE_COMMENT_COUNT).getText(); //(106)
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')')); //106
                countArray.add(Integer.valueOf(countToParse));
            } else {
                countArray.add(0);
            }
        }
//        System.out.println(articleList); // add linebreak
        System.out.println(titlesArray); // add linebreak
        System.out.println("\n"); // add linebreak
        System.out.println(countArray); // add linebreak
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

