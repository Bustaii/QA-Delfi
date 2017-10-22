package PageObject.Helper;


import PageObject.Pages.BaseFunctions;
import PageObject.Pages.HomePage;
import com.google.common.base.CharMatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentHelper {
    //BaseFunctions baseFunk;
    private static final Logger LOG = LogManager.getLogger(CommentHelper.class); //define loger


    public Integer stringToInt(String textInput) {
        LOG.info("Convert String to Integer");
        String digits = CharMatcher.digit().retainFrom(textInput);
        return Integer.valueOf(digits);
    }
}
