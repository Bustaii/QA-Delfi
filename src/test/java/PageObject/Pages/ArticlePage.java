package PageObject.Pages;

import PageObject.Helper.CommentHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePage {
    BaseFunctions baseFunk;
    CommentHelper comHelp = new CommentHelper(); // conenction with CommentHelper.java
    private static final Logger LOG = LogManager.getLogger(ArticlePage.class); //define loger
    private static final By TITLE = By.xpath("//h1[@class='article-title']/span"); // locator of title from article on homepage (String)
    private static final By COMMENT = By.xpath("//a[@class='comment-count']"); // locator of comment from article on homepage (Int)


    public ArticlePage(BaseFunctions baseFunk) {
        this.baseFunk = baseFunk;
    }

    public String getTitle() {
        LOG.info("Get Article Page Title");
        WebElement element = baseFunk.getElement(TITLE);
        String title = element.getText();
        return title;
    }

    public Integer getComment() {
        LOG.info("Get Article Page Comment Count");
        WebElement element = baseFunk.getElement(COMMENT);
        return comHelp.stringToInt(element.getText());
    }

    public CommentPage openComment() {
        LOG.info("Click on Comment count");
        baseFunk.clickThis(COMMENT);
        return new CommentPage(baseFunk);
    }
}
