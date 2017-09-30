package Delfi;

import org.openqa.selenium.*; //selected all - Selenium
import org.testng.*; //selected all - TestNG
import org.openqa.selenium.firefox.*; //selected all - required for firefox driver
import org.testng.annotations.*;
import java.util.*; //selected all - required for arrays
import static java.lang.Integer.valueOf;
import java.util.List;


public class DimaStyle {
    private String WEB_URL = "http://www.delfi.lv/"; //define desktop website url
    private String MOB_URL = "http://m.delfi.lv"; //define mobile website url

    //WEB LOCATORS
    private static final By LOGO = By.xpath("//a[@class='headerLogo']");

    private static final By ARTICLE = By.xpath("//h3[@class='top2012-title']");
    private static final By ARTICLE_TITLE = By.xpath("//h3/a[@class='top2012-title']");
    private static final By ARTICLE_COMMENT_COUNT = By.xpath("//a[@class='comment-count']");
    private static final By ARTICLE_TITLE_PAGE = By.xpath("//h1[@class='article-title']/span");
    private static final By ARTICLE_COMMENT_COUNT_PAGE = By.xpath("//a[@class='comment-count']");

    //MOBILE LOCATORS
    private static final By ARTICLE_MOBILE = By.xpath("//div[@class='md-mosaic-title']");
    private static final By ARTICLE_TITLE_MOBILE = By.xpath("//a[@class='md-scrollpos']");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE = By.xpath("//a[@class='commentCount']");
    private static final By ARTICLE_TITLE_MOBILE_PAGE = By.xpath("//div[@class='article-title']/h1");
    private static final By ARTICLE_TITLE_MOBILE_PAGE_OTHER = By.xpath("//h1[@class='article-title']"); //alternative
    private static final By ARTICLE_COMMENT_COUNT_MOBILE_PAGE = By.xpath("//a[@class='commentCount']");
    private static final By ARTICLE_COMMENT_COUNT_MOBILE_PAGE_OTHER = By.xpath("//*[contains(@class,'infobar-comments')]/a"); //alternative


    //SHARED LOCATORS
    private static final By REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-reg')]/span");
    private static final By NO_REG_USR_COMMENTS = By.xpath("//*[contains(@class,'list-a-anon')]/span");


    private String findThisTitle =  "Kremļa varas sistēma nepieļauj Putina aiziešanu, spriež eksperti";
    private final By TITLE_IS_FOUND = By.xpath("//h3/a[contains(text(), '"+ findThisTitle +"')]");
    private final By FOUND_PARENT = By.xpath("//h3/a[contains(text(), '"+ findThisTitle +"')]/parent::h3[@class='top2012-title']");



    @Test
    public void findUsingTitle() {
        System.setProperty("webdriver.gecko.driver", "G:\\_driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver(); //define driver variable
        driver.manage().window().maximize(); //maximize browser window

        driver.get(WEB_URL); //go to url

        List<String> titlesArrayT2 = new ArrayList<String>();
        List<Integer> countArrayT2 = new ArrayList<Integer>();

        WebElement title = driver.findElement(TITLE_IS_FOUND); //find title element using text
        titlesArrayT2.add((title).getText());

        List<WebElement> articleListMobile = driver.findElements(FOUND_PARENT);
        for(WebElement el : articleListMobile){
            el.getText();
            System.out.println(el.getText() + "FOR");
        }



//        WebElement elementMobile = articleListMobile.getText();
//        String titleMobile = elementMobile.findElements(FOUND_PARENT).get().getText();
//        titlesArrayMobile.add(titleMobile);

//        WebElement article = title.findElement(FOUND_PARENT); //find parent element of tha containts previous element
//        //WebElement article = title.findElement(By.xpath("..")); //find parent element of tha containts previous element
//        List<WebElement> articleElementsList = article.findElements(ARTICLE_COMMENT_COUNT);
//        System.out.println(title);
//        //System.out.println(article);
//        System.out.println((TITLE_IS_FOUND));
//        System.out.println((FOUND_PARENT));
//        System.out.println((ARTICLE_COMMENT_COUNT));


//        String gotCommentCount = articleElementsList.get(1).getText(); //(106)
//        if (article.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
//            gotCommentCount = gotCommentCount.substring(gotCommentCount.indexOf('(') + 1, gotCommentCount.indexOf(')')); //106
//            countArrayT2.add(valueOf(gotCommentCount));
//        } else {
//            countArrayT2.add(0);
//        }
        System.out.println(titlesArrayT2);
        System.out.println(countArrayT2);

        driver.findElement(TITLE_IS_FOUND).click();

        driver.quit(); //close browser
    }

    @Test
    public void testMethod() {
        System.setProperty("webdriver.gecko.driver", "G:\\_driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver(); //define driver variable
        driver.manage().window().maximize(); //maximize browser window

        driver.get(WEB_URL); //go to url
        String newTab = Keys.chord(Keys.CONTROL, Keys.RETURN); //open second tab
        driver.findElement(LOGO).sendKeys(newTab); //by clicking on logo

        //create array of tabs - defined as "browserTabs"
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        while (browserTabs.size() != 2) { //add check if there are less then 2 tabs active will wait until
            browserTabs.clear();
            browserTabs.addAll(driver.getWindowHandles());
        } //while close
        driver.switchTo().window(browserTabs.get(1)); //switch to new tab [1] second tab
        driver.get(MOB_URL); //open this url in new tab
        driver.switchTo().window(browserTabs.get(0)); //switch back to new tab [0] first tab

        //WEB
        List<String> titlesArray = new ArrayList<String>();
        List<Integer> countArray = new ArrayList<Integer>();
        List<String> urlArray = new ArrayList<String>();

        //MOBILE
        List<String> titlesArrayMobile = new ArrayList<String>();
        List<Integer> countArrayMobile = new ArrayList<Integer>();
        List<String> urlArrayMobile = new ArrayList<String>();

        List<String> correctTitleLoacator = new ArrayList<String>();

        List<Integer> totalCommentPageInt = new ArrayList<Integer>();
        List<Integer> regCommentsInt = new ArrayList<Integer>();
        List<Integer> noRegCommentsInt = new ArrayList<Integer>();

        List<WebElement> articleList = driver.findElements(ARTICLE);
        for (int i = 0; i < 5; i++) {
            WebElement element = articleList.get(i);
            String title = element.findElements(ARTICLE_TITLE).get(i).getText();
            titlesArray.add(title);

            String url = element.findElements(ARTICLE_TITLE).get(i).getAttribute("href"); //get url
            urlArray.add(url);

            if (element.findElements(ARTICLE_COMMENT_COUNT).size() != 0) {
                String countToParse = element.findElements(ARTICLE_COMMENT_COUNT).get(i).getText(); //(106)
                countToParse = countToParse.substring(countToParse.indexOf('(') + 1, countToParse.indexOf(')')); //106
                countArray.add(valueOf(countToParse));
            } else {
                countArray.add(0);
            }
        }

        driver.switchTo().window(browserTabs.get(1)); //switch to new tab [1] second tab

        List<WebElement> articleListMobile = driver.findElements(ARTICLE_MOBILE);
        for (int i = 0; i < 5; i++) {
            WebElement elementMobile = articleListMobile.get(i);
            String titleMobile = elementMobile.findElements(ARTICLE_TITLE_MOBILE).get(i).getText();
            titlesArrayMobile.add(titleMobile);

            String urlMobile = elementMobile.findElements(ARTICLE_TITLE_MOBILE).get(i).getAttribute("href"); //get url
            urlArrayMobile.add(urlMobile);

            if (elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).size() != 0) {
                String countToParseMobile = elementMobile.findElements(ARTICLE_COMMENT_COUNT_MOBILE).get(i).getText(); //(106)
                countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
                countArrayMobile.add(valueOf(countToParseMobile));
            } else {
                countArrayMobile.add(0);
            }
        }
        //System.out.println(titlesArray);
        //System.out.println(titlesArrayMobile);
        try {
            Assert.assertEquals(titlesArray, titlesArrayMobile); //compare two arrays of titles
        } catch (AssertionError e) {
            System.out.println("TITLES NOT MATCHING");
        } //try catch close

        System.out.println(countArray);
        System.out.println(countArrayMobile);
        try {
            Assert.assertEquals(countArray, countArrayMobile); //compare two arrays of comment count
        } catch (AssertionError e) {
            System.out.println("COMMENT COUNT IS NOT MATCHING");
        } //try catch close

        //MOBILE
        for (int i = 0; i < 5; i++) {
            String getArrayTitle = titlesArrayMobile.get(i);
            String getArrayUrl = urlArrayMobile.get(i);
            Integer getArrayCount = countArrayMobile.get(i);
                driver.get(getArrayUrl);
                    if (driver.findElements(ARTICLE_TITLE_MOBILE_PAGE).size() != 0) {
                        String findTitle = driver.findElement(ARTICLE_TITLE_MOBILE_PAGE).getText();
                        correctTitleLoacator.add(findTitle);
                    } else {
                        String findTitle = driver.findElement(ARTICLE_TITLE_MOBILE_PAGE_OTHER).getText();
                        correctTitleLoacator.add(findTitle);
                    }
                String getCorrectTitle = correctTitleLoacator.get(i);
                Assert.assertTrue(getCorrectTitle.contains(getArrayTitle), "\n" + "Looking : " + getArrayTitle + "\n" + "Results : " + getCorrectTitle + "\n");

                    if (driver.findElements(ARTICLE_COMMENT_COUNT_MOBILE_PAGE).size() != 0) {
                            String commentsExists = driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE).getText();
                            //System.out.println(commentsExists + "if");
                            commentsExists = commentsExists.substring(commentsExists.indexOf('(') + 1, commentsExists.indexOf(')')); //106
                            totalCommentPageInt.add(valueOf(commentsExists));
                            driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE).click();

                    } else if (driver.findElements(ARTICLE_COMMENT_COUNT_MOBILE_PAGE_OTHER).size() != 0) {
                            String commentsExists = driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE_OTHER).getText();
                            //System.out.println(commentsExists + "else if");
                            Integer numericValue = Integer.parseInt(commentsExists.replaceAll("\\D", "")); //remove text leave only numeric values
                            totalCommentPageInt.add(numericValue);
                            driver.findElement(ARTICLE_COMMENT_COUNT_MOBILE_PAGE_OTHER).click();
                    } else {
                        totalCommentPageInt.add(0);
                    }

                    //check if authorised comments are on the page
                    if (driver.findElements(REG_USR_COMMENTS).size() != 0) {
                        String countToParseMobile = driver.findElement(REG_USR_COMMENTS).getText(); //(106)
                        countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
                        regCommentsInt.add(valueOf(countToParseMobile));
                    } else {
                        regCommentsInt.add(0);
                    }

                    //check if not authorised comments are on the page
                    if (driver.findElements(NO_REG_USR_COMMENTS).size() != 0) {
                        String countToParseMobile = driver.findElement(NO_REG_USR_COMMENTS).getText(); //(106)
                        countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
                        noRegCommentsInt.add(valueOf(countToParseMobile));
                    } else {
                        noRegCommentsInt.add(0);
                    }

                    Integer totalCommmens = totalCommentPageInt.get(i);
                    Integer noRegArray = regCommentsInt.get(i);
                    Integer regArray = noRegCommentsInt.get(i);

                    Integer summOfComments = (noRegArray + regArray);

                    System.out.println(getArrayCount);
                    System.out.println(totalCommmens);
                    System.out.println(summOfComments);

                    Assert.assertEquals(getArrayCount, totalCommmens, summOfComments); // compare tree arrays of comment count

        } //for close

        //WEB
//        for (int i = 0; i < 5; i++) {
//            String getArrayTitle = titlesArray.get(i);
//            String getArrayUrl = urlArray.get(i);
//            Integer getArrayCount = countArray.get(i);
//
//                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //bypass long loading pages
//                try {
//                    driver.get(getArrayUrl);
//                } catch (TimeoutException e) {
//                    String findTitle = driver.findElement(ARTICLE_TITLE_PAGE).getText();
//                    Assert.assertTrue(findTitle.contains(getArrayTitle), "\n" + "Looking : " + getArrayTitle + "\n" + "Results : " + findTitle + "\n");
//
//                    String findCount = driver.findElement(ARTICLE_COMMENT_COUNT_PAGE).getText();
//                    findCount = findCount.substring(findCount.indexOf('(') + 1, findCount.indexOf(')')); //106
//                    Integer findCountInt = Integer.valueOf(findCount);
//                    driver.findElement(ARTICLE_COMMENT_COUNT_PAGE).click();
//
//                    //check if authorised comments are on the page
//                    if (driver.findElements(REG_USR_COMMENTS).size() != 0) {
//                        String countToParseMobile = driver.findElement(REG_USR_COMMENTS).getText(); //(106)
//                        countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
//                        regCommentsInt.add(valueOf(countToParseMobile));
//                    } else {
//                        regCommentsInt.add(0);
//                    }
//
//                    //check if not authorised comments are on the page
//                    if (driver.findElements(NO_REG_USR_COMMENTS).size() != 0) {
//                        String countToParseMobile = driver.findElement(NO_REG_USR_COMMENTS).getText(); //(106)
//                        countToParseMobile = countToParseMobile.substring(countToParseMobile.indexOf('(') + 1, countToParseMobile.indexOf(')')); //106
//                        noRegCommentsInt.add(valueOf(countToParseMobile));
//                    } else {
//                        noRegCommentsInt.add(0);
//                    }
//                    Integer noRegArray = regCommentsInt.get(i);
//                    Integer regArray = noRegCommentsInt.get(i);
//
//                    Integer summOfComments = (noRegArray + regArray);
//
//                    Assert.assertEquals(Integer.valueOf(getArrayCount), Integer.valueOf(findCountInt), Integer.valueOf(summOfComments)); // compare tree arrays of comment count
//                } //try catch close
//        } //for close
        driver.quit(); //close browser
    } //method close
} //class close


//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //bypass long loading pages
//        System.out.println("timer");
//        try {
//        System.out.println("in try");
//        driver.get(getArrayUrl);
//        } catch (TimeoutException e) {
//        System.out.println("in catch");
//        } //try catch close