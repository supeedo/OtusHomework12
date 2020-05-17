package pages.market;

import helpers.PageHelpClass;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComparisonPage extends PageHelpClass {
    WebDriver driver;
    WebDriverWait wait;

    private final String compareElementsCSS = "div[class^=n-compare-cell][data-bem]";
    private final String allCharasterCSS = "span[class = \"link n-compare-show-controls__all\"][role=button] .link__inner";
    private final String variousCharasterCSS = "span[class = \"link n-compare-show-controls__diff\"][role=button] .link__inner";
    private final String operationSystemXpatch = "//div[text()=\"Операционная система\"]";

    @FindAll(@FindBy(css = compareElementsCSS))
    private List<WebElement> compareElements;
    @FindBy(css = allCharasterCSS)
    private WebElement allChataster;
    @FindBy(css = variousCharasterCSS)
    private WebElement variousCharaster;
    @FindBy(xpath = operationSystemXpatch)
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

    public boolean checkElement () {
        boolean flag = false;
        try {
             flag = checkDispayedElement(driver, operationSystem);
        }catch (TimeoutException e){}
        return  flag;
    }

}
