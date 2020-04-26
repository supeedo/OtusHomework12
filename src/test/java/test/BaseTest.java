package test;

import configuration.Config;
import factory.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import test.ru.otus.SimpleTest;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger logger = LogManager.getLogger(SimpleTest.class);
    protected static Config cfg = ConfigFactory.create(Config.class);


    @BeforeTest
    protected void setUp() {
        String browserType = System.getProperty("browser");
        logger.info("Got a browser name = {}", browserType);
        driver = WebDriverFactory.createNewDriver(browserType);
        logger.info("Driver set'up = {}", driver.getClass());
    }


    @AfterTest
    protected void setDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver close");
        }
    }
}