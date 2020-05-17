package test.ru.otus;

import org.testng.annotations.*;
import test.BaseTest;

//@Listeners(ExecutionListener.class)
public class SimpleOtusTest extends BaseTest {

    @Test(description = "Check the opening of the page Otus")
    public void openPage() {
        logger.info("Run test \"SimpleOtusTest\"");
        driver.navigate().to(cfg.URL());
        logger.info("Page is open = {}", cfg.URL());
    }

}
