package PageObject;

import PageObject.Pages.ArticlePage;
import PageObject.Pages.BaseFunctions;
import PageObject.Pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*; //selected all - Selenium
import org.testng.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import org.testng.annotations.*;
import java.util.*; //selected all - required for arrays
import static java.lang.Integer.valueOf;
import java.util.List;

public class PageObjectClass {
    BaseFunctions baseFunc = new BaseFunctions(); // getting all base functions from BaseFunctions class
    private static final Logger LOG = LogManager.getLogger(PageObjectClass.class); // logger
    private static final String HOME_PAGE_URL = "http://delfy.lv";

    @Test
    public void delfiObjectMethod() {
        LOG.info("Open Delfi homepage");
        baseFunc.goToURL(HOME_PAGE_URL); //looking into baseFunc to

        LOG.info("Getting first title");
        HomePage homePage = new HomePage(baseFunc);
        WebElement article = homePage.getArticle();
        String title = homePage.getTitle(article);

        LOG.info("Getting comment count");
        int comment = homePage.getComment(article);

        LOG.info("Open first article");
        homePage.openArticle();

        LOG.info("Getting article title");
        ArticlePage articlePage = homePage.openArticle();
        LOG.info("Getting article comment count");
        LOG.info("Compare title");
        LOG.info("Compare comment count");

        LOG.info("Open comment page");
        LOG.info("Getting title comment count on comment page");
        LOG.info("Getting registered comment count");
        LOG.info("Getting not-registered comment count");
        LOG.info("Get sum of registered and not-registered comments");
        LOG.info("Get sum of registered and not-registered comments");
        LOG.info("Compare title with comment page");
        LOG.info("Compare comment count with comment page");

        LOG.info("Test is successful");

    }
}
