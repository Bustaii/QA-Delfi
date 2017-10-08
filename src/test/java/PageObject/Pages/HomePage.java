package PageObject.Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    BaseFunctions baseFunk;
    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']"); // locator of article from webelement on homepage
    private static final By TITLE = By.xpath("//h3/a[@class='top2012-title']"); // locator of title from article on homepahe
    private static final Logger LOG = LogManager.getLogger(BaseFunctions.class); //define loger

    public HomePage(BaseFunctions baseFunk) {
        this.baseFunk = baseFunk;
    }

    public WebElement getArticle() {
        LOG.info("Get first article with locator");
        return baseFunk.getElement(ARTICLE);
    }

    public WebElement getTitle(WebElement article) {
        LOG.info("Get title from article with locator");
        return article.findElement(TITLE);
    }
}
