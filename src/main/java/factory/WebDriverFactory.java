package factory;

import enums.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {

    public static WebDriver createNewDriver( String browserName ) {
        return createNewDriver(browserName, new MutableCapabilities());
    }

    public static WebDriver createNewDriver( String browserName, MutableCapabilities options ) {
        WebDriver wb = null;
        browserName = browserName.toUpperCase();

        if (browserName.equals(BrowserName.FIREFOX.name()))
            wb = getFFInstance();
        else if (browserName.equals(BrowserName.OPERA.name()))
            wb = getOperaInstance();
        else if (browserName.equals(BrowserName.CHROME.name()))
            wb = getChromeInstance();
        else
            throw new IllegalArgumentException("This browser is not supported!");

        return wb;
    }

    private static ChromeDriver getChromeInstance() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static FirefoxDriver getFFInstance() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static OperaDriver getOperaInstance() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }
}
