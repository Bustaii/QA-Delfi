package PageObject.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    BaseFunctions baseFunk;
    private static final Logger LOG = LogManager.getLogger(HomePage.class); //define loger
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']"); // locator of article from webelement on homepage (Web Element)
    private static final By TITLE = By.xpath("//h3/a[@class='top2012-title']"); // locator of title from article on homepage (String)
    private static final By COMMENT = By.xpath("//a[@class='comment-count']"); // locator of comment from article on homepage (Int)


    public HomePage(BaseFunctions baseFunk) {
        this.baseFunk = baseFunk;
    }

    public WebElement getArticle() {
        LOG.info("Get first article with locator");
        return baseFunk.getElement(ARTICLE);
    }

    public String getTitle(WebElement article) {
        LOG.info("Get title from article with locator");
        return article.findElement(TITLE).getText();
    }

    public int getComment(WebElement article) {
        LOG.info("Get comment from article with locator");
        String stringValue =  article.findElement(COMMENT).getText();
        stringValue = stringValue.substring(stringValue.indexOf('(') + 1, stringValue.indexOf(')')); // remove brackets
        return Integer.valueOf(stringValue);
    }

    public ArticlePage openArticle() {
        LOG.info("Click on title");
        baseFunk.clickThis(TITLE);
        return new ArticlePage(baseFunk);
    }
}
