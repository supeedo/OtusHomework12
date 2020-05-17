package pages.market;

import helpers.WaitersHelpClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComparisonPage extends WaitersHelpClass {
    private WebDriver driver;
    private WebDriverWait wait;

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


    public ComparisonPage( WebDriver driver, WebDriverWait wait ) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public int countCompareElements() {
        return compareElements.size();
    }

    public ComparisonPage changeCharasterMenuInAll() {
        allChataster.click();
        return this;
    }

    public ComparisonPage changeCharasterMenuInVarious() {
        variousCharaster.click();
        return this;
    }

    public ComparisonPage checkElementNotVisible() {
        checkDispayedElementNotVisible(driver, operationSystem);
        return this;
    }
    public ComparisonPage checkElementVisible() {
        checkDispayedElement(driver, operationSystem);
        return this;
    }

}
