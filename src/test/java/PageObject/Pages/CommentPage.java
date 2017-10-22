package PageObject.Pages;

import PageObject.Helper.CommentHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentPage {
    BaseFunctions baseFunk;
    CommentHelper comHelp = new CommentHelper(); // conenction with CommentHelper.java
    private static final Logger LOG = LogManager.getLogger(CommentPage.class); //define loger
    private static final By TITLE = By.xpath("//h1[@class='article-title']/span"); // locator of title from article on homepage (String)

    private static final By REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-reg')]/span");
    private static final By NO_REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-anon')]/span");


    public CommentPage(BaseFunctions baseFunk) {
        this.baseFunk = baseFunk;
    }

    public String getTitle() {
        LOG.info("Get Article Page Title");
        WebElement element = baseFunk.getElement(TITLE);
        String title = element.getText();
        return title;
    }

    public Integer getRegComment() {
        LOG.info("Get Article Page Comment Count");
        WebElement element = baseFunk.getElement(REG_USR_COMMENTS);
        return comHelp.stringToInt(element.getText());
    }

    public Integer getNonComment() {
        LOG.info("Get Article Page Comment Count");
        WebElement element = baseFunk.getElement(NO_REG_USR_COMMENTS);
        return comHelp.stringToInt(element.getText());
    }
}
