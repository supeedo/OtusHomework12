package ru.otus;

import configuration.Config;
import factory.WebDriverFactory;
import listeners.ExecutionListener;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

//@Listeners(ExecutionListener.class)
public class SimpleTest {

    private static WebDriver driver;
    private static Logger logger = LogManager.getLogger(SimpleTest.class);
    private static Config cfg = ConfigFactory.create(Config.class);


    @BeforeTest
    public void setUp() {
        String browserType = System.getProperty("browser");
        logger.info("Got a browser name = " + browserType);
        driver = WebDriverFactory.createNewDriver(browserType);
        logger.info("Driver set'up = " + driver.getClass());
    }

    @Test(description = "Check the opening of the page")
    public void openPage() {
        logger.info("Run test \"openPage\"");
        driver.navigate().to(cfg.URL());
        logger.info("Page is open = " + cfg.URL());
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver close");
        }
    }
}
