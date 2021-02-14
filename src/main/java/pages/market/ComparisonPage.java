package pages.market;

import utils.WaitersHelpClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ComparisonPage extends WaitersHelpClass {
    private WebDriver driver;
    private WebDriverWait wait;
    protected static Logger logger = LogManager.getLogger(ComparisonPage.class);

    private final String COMPARE_ELEMENT_CSS = "div[class^=n-compare-cell][data-bem]";
    private final String ALL_CHARACTER_CSS = "span[class = \"link n-compare-show-controls__all\"][role=button] .link__inner";
    private final String VARIOUS_CHARACTER_CSS = "span[class = \"link n-compare-show-controls__diff\"][role=button] .link__inner";
    private final String OPERATION_SYSTEM_XPATCH = "//div[text()=\"Операционная система\"]";

    @FindAll(@FindBy(css = COMPARE_ELEMENT_CSS))
    private List<WebElement> compareElements;
    @FindBy(css = ALL_CHARACTER_CSS)
    private WebElement allChataster;
    @FindBy(css = VARIOUS_CHARACTER_CSS)
    private WebElement variousCharaster;
    @FindBy(xpath = OPERATION_SYSTEM_XPATCH)
    private WebElement operationSystem;


    public ComparisonPage( WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ComparisonPage assertCountCompareElements( int count ) {
        logger.info("Проверяем, что в сравнении 2 телефона");
        Assert.assertEquals(count, compareElements.size());
        return this;
    }

    public ComparisonPage changeCharasterMenuInAll() {
        logger.info("Отображаем все характеристики");
        allChataster.click();
        return this;
    }

    public ComparisonPage changeCharasterMenuInVarious() {
        logger.info("Отображаем отличающиеся характеристики");
        variousCharaster.click();
        return this;
    }

    public ComparisonPage checkElementNotVisible() {
        logger.info("Проверяем что строка \"Операционная система\" не отображается");
        checkDispayedElementNotVisible(driver, operationSystem);
        return this;
    }

    public ComparisonPage checkElementVisible() {

        logger.info("Проверяем, что отображается строка \"Операционная система\"");
        checkDispayedElement(driver, operationSystem);
        return this;
    }

}
