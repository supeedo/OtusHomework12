package ru.otus;

import config.UrlConfig;
import factory.BrowserFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

//@Listeners(ExecutionListener.class)
public class SimpleTest extends BrowserFactory {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SimpleTest.class);
    private UrlConfig cfg = ConfigFactory.create(UrlConfig.class);


    @BeforeTest
    public void setUp() {
        String s = System.getProperty("browser");
        driver = BrowserFactory.GetBrowser(s);
        logger.info("Двайвер поднят" + driver.getClass());
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());
        logger.info("Открыта страница Отус");
    }

    @AfterTest
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт");
        }
    }
}
