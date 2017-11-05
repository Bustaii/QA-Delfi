package PageObject.Wrappers;

import PageObject.Pages.BaseFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticleWrapper {
    private final BaseFunctions baseFunk;
    private final WebElement element;
    private static final By TITLE = By.xpath("locator here");

    public ArticleWrapper(BaseFunctions baseFunk, WebElement element) {
        this.baseFunk = baseFunk;
        this.element = element;

    }

    public String getArticleTitle() {
        return element.findElements(TITLE).isEmpty() ? element.findElement(TITLE).getText() : null;
    }

    public void clickOnTitle() {
        element.click();
    }
}
