//package PageObject.Wrappers;
//
//import PageObject.Pages.ArticlePage;
//import PageObject.Pages.BaseFunctions;
//import PageObject.Pages.HomePage;
//import org.testng.annotations.Test;
//
//public class WrapperClass {
//    BaseFunctions baseFunk = new BaseFunctions();
//    private static final String URL = "http://www.delfi.lv";
//    private static final String ARTICLE_TITLE = "RIGA GA GA";
//
//    @Test
//    void wrapperExample() {
//        baseFunk.goToURL(URL);
//        HomePage homePage = new HomePage(baseFunk);
//        ArticlePage articlePage = new homePage.openArticleByTitle(ARTICLE_TITLE);
//    }
//}
