package PageObject;

import PageObject.Pages.ArticlePage;
import PageObject.Pages.BaseFunctions;
import PageObject.Pages.CommentPage;
import PageObject.Pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*; //selected all - Selenium
import org.testng.*; //selected all - TestNG
import org.testng.annotations.*;

public class PageObjectClass {
    BaseFunctions baseFunc = new BaseFunctions(); // getting all base functions from BaseFunctions class
    private static final Logger LOG = LogManager.getLogger(PageObjectClass.class); // logger
    private static final String HOME_PAGE_URL = "http://delfi.lv";

    @Test
    public void delfiObjectMethod() {
        LOG.info("Open Delfi homepage");
        baseFunc.goToURL(HOME_PAGE_URL); //looking into baseFunc to

        LOG.info("Getting first title");
        HomePage homePage = new HomePage(baseFunc);
        WebElement article = homePage.getArticle();
        String title = homePage.getTitle(article);

        LOG.info("Getting comment count");
        Integer comment = homePage.getComment(article);
        LOG.info("Got comment count" + comment);



        LOG.info("Open first article page");
        //homePage.openArticle(); //click on article
        ArticlePage articlePage = homePage.openArticle();

        LOG.info("Getting article title");
        String articleTitle = articlePage.getTitle();
        LOG.info("Got article title" + articleTitle);

        LOG.info("Getting article comment count");
        Integer articleComment = articlePage.getComment();
        LOG.info("Got article title" + articleComment);

        LOG.info("Compare title");
        Assert.assertEquals(title, articleTitle);

        LOG.info("Compare comment count");
        Assert.assertTrue(comment == articleComment, "Comment count no equels to Article Comment count"); //???



        LOG.info("Open comment page");
        //articlePage.openComment();
        CommentPage commentPage = articlePage.openComment();

        LOG.info("Getting registered comment count");
        Integer regComment = commentPage.getRegComment();
        LOG.info("Got registered comment count" + regComment);

        LOG.info("Getting not-registered comment count");
        Integer notRegComment = commentPage.getNonComment();
        LOG.info("Got not registered comment count" + notRegComment);

        LOG.info("Compare comments count");
        Assert.assertEquals(comment, articleComment,regComment + notRegComment); //???

        LOG.info("Compare title with comment page");
        LOG.info("Compare comment count with comment page");

        LOG.info("Test is successful");

    }
}
