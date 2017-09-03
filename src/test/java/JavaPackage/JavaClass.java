/**if error use Alt + Enter pokazhet varianti reshenija problemi**/
package JavaPackage;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaClass {
    /**Anatacija posle dobavlenija dobavitj import org.junit.Test vishe!**/

    @Test

    /**METHOD - uznatj 4to takoe void!**/
    public void javaMethod() {

        /**podklju4aem GECKODRIVER - nuzen dlja raboti sileniuma s browserom, mozhno vinesti v globalnuju peremennuju**/
        /**dlja kazhodo browsera ispolzuetsa svoj driver, ne gecko**/
        System.setProperty("webdriver.gecko.driver", "c:/geckodriver.exe");

        /**BROWSER DRIVER **/
        WebDriver firefoxBrowser = new FirefoxDriver();

        /**perevodim broser v fullscreen mode**/
        firefoxBrowser.manage().window().maximize();

        /**otkrivaet okno browsera vpisivaet v adresssnutuju stroku url na kotorij idet perehod**/
        firefoxBrowser.get("http://javaguru.lv");

        /**zakrivaem Browser**/
        firefoxBrowser.quit();

        /**posle vsego zalivaem na GitHub**/
        /** VCS > Import into version control > Share project on GitHub**/

    }
}
