package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {

    public WebDriver createNewDriver( String browserName ) {
        return createNewDriver(browserName, new MutableCapabilities());
    }

    public WebDriver createNewDriver( String browserName, MutableCapabilities options ) {
        WebDriver wb = null;
        browserName = browserName.toLowerCase();

        if ("firefox".equals(browserName))
            wb = getFFInstance(new FirefoxOptions().merge(options));
        else if ("opera".equals(browserName))
            wb = getOperaInstance(new OperaOptions().merge(options));
        else if ("chrome".equals(browserName))
            wb = getChromeInstance(new ChromeOptions().merge(options));
        else
            throw new IllegalArgumentException("This browser is not supported!");
        return wb;
    }

    private ChromeDriver getChromeInstance( ChromeOptions options ) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    private FirefoxDriver getFFInstance( FirefoxOptions options ) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }

    private OperaDriver getOperaInstance( OperaOptions options ) {
        WebDriverManager.operadriver().setup();
        return new OperaDriver(options);
    }
}
