package PageObject.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePage {
    BaseFunctions baseFunk;
    private static final Logger LOG = LogManager.getLogger(ArticlePage.class); //define loger
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']"); // locator of article from webelement on homepage (Web Element)
    private static final By TITLE = By.xpath("//h3/a[@class='top2012-title']"); // locator of title from article on homepage (String)
    private static final By COMMENT = By.xpath("//a[@class='comment-count']"); // locator of comment from article on homepage (Int)


    public ArticlePage(BaseFunctions baseFunk) {
        this.baseFunk = baseFunk;
    }
}
