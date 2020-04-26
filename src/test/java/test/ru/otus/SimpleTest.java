package test.ru.otus;

import org.testng.annotations.*;
import test.BaseTest;

//@Listeners(ExecutionListener.class)
public class SimpleTest extends BaseTest {

    @Test(description = "Check the opening of the page")
    public void openPage() {
        logger.info("Run test \"openPage\"");
        driver.navigate().to(cfg.URL());
        logger.info("Page is open = {}", cfg.URL());
    }

}
