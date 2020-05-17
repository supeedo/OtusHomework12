package test;

import configuration.Config;
import configuration.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Logger logger = LogManager.getLogger(BaseTest.class);
    protected static Config cfg = ConfigFactory.create(Config.class);


    @BeforeClass
    protected void setUp() {
        String browserType = System.getProperty("browser");
        logger.info("Got a browser name = {}", browserType);
        if(browserType == null) driver = WebDriverFactory.createNewDriver("chrome");
        else driver = WebDriverFactory.createNewDriver(browserType);
        logger.info("Driver set'up = {}", driver.getClass());
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10, 5000);
    }


   // @AfterClass
    protected void setDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver close");
        }
    }
}
