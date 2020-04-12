package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver GetBrowser( String browserName ) {
        browserName = browserName.toLowerCase();
        if (browserName.equals("firefox"))
            return getFFInstance();
        else
            return getChromeInstance();
    }

    private static ChromeDriver getChromeInstance() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static FirefoxDriver getFFInstance() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
