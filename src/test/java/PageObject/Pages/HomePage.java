package PageObject.Pages;

import PageObject.Wrappers.ArticleWrapper;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomePage {
    BaseFunctions baseFunk;
    private static final Logger LOG = LogManager.getLogger(HomePage.class); //define loger
    private static final By ARTICLE_ITEM = By.xpath("//h3[@class='top2012-title']"); // locator of article wrapper (Web Element)
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

    //WRAPPER START
    private List<ArticleWrapper> getAllArticles() {
        List<WebElement> articles = baseFunk.getElements(ARTICLE_ITEM);
        List<ArticleWrapper> articleWrappersList = new ArrayList<>();
        Iterables.addAll(articleWrappersList, articles.stream()
                .map(webElem -> new ArticleWrapper(baseFunk, webElem))
                .collect(Collectors.toList()));
        return articleWrappersList;
    }

    private ArticleWrapper getArticleName(String name) {
        //Optional - java / Iterebles - google.common.collect
        com.google.common.base.Optional<ArticleWrapper> wrapper = Iterables.tryFind(getAllArticles(), articleWrapper -> name.contains(articleWrapper.getArticleTitle()));
        return wrapper.isPresent() ? wrapper.get() : null;
    }

    public ArticlePage openArticleByTitle(String articleName) {
        LOG.info("Get article title for our Wrapper");
        getArticleName(articleName).clickOnTitle();
        return new ArticlePage(baseFunk);
    }
}
