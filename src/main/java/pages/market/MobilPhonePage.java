package pages.market;

import helpers.WaitersHelpClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MobilPhonePage extends WaitersHelpClass {
    private WebDriver driver;
    private WebDriverWait wait;

    private final String XIAOMI_XPATCH = "//input[@id = \"7893318_7701962\"]/..";
    private final String ZTE_XPATCH = "//input[@id=\"7893318_1007740\"]/..";
    private final String PRICE_FILTER_BUTTON_XPATCH = "//a[text()=\"по цене\"]";
    private final String BUTTON_SHOWALL_XPATCH = "//a[@role='button' and contains(@class,'button button_size_m')]";
    private final String ALL_MOBILE_ELEMENTS_XPATCH = "//div[contains(@data-bem,\"n-user-lists_type_compare\")]/.";
    private final String COMPARISON_BUTTON_CSS = "a[class^=button][href$=rmmbr]";

    @FindBy(xpath = XIAOMI_XPATCH)
    private WebElement xiaomi;
    @FindBy(xpath = ZTE_XPATCH)
    private WebElement zte;
    @FindBy(xpath = PRICE_FILTER_BUTTON_XPATCH)
    private WebElement priceFilterButton;
    @FindBy(xpath = BUTTON_SHOWALL_XPATCH)
    private WebElement showAllButton;
    @FindBy(css = COMPARISON_BUTTON_CSS)
    private WebElement inComparisonButton;
    @FindAll(@FindBy(xpath = ALL_MOBILE_ELEMENTS_XPATCH))
    private List<WebElement> allMobilePhone;

    public MobilPhonePage( WebDriver driver, WebDriverWait wait ) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public MobilPhonePage useMobileFilter() {
        useElement(xiaomi, driver);
        useElement(zte, driver);
        return this;
    }

    public MobilPhonePage someFilters(String brandName) {
        useElement("input[name$=\""+brandName+"\"]", driver); //  ??
        return this;
    }

    public MobilPhonePage usePriceFilter() {
        useElement(priceFilterButton, driver);
        return this;
    }

    public MobilPhonePage useShowAllButton() {
        while (true) {
            try {
                wait.until(ExpectedConditions
                        .visibilityOf(showAllButton)).click();
            } catch (TimeoutException e) {
                break;
            } catch (StaleElementReferenceException e) {
            }
        }
        return this;
    }

    public MobilPhonePage takeAllMobile() {
        int countXiaomi = 0;
        int countZTE = 0;
        for (int i = 0; i < allMobilePhone.size(); i++) {
            if (countXiaomi == 1 && countZTE == 1) {
                break;
            } else if (getNameSmartphone(allMobilePhone.get(i).getAttribute("data-bem")).contains("Xiaomi")
                    && countXiaomi == 0) {
                allMobilePhone.get(i).click();
                Assert.assertTrue(driver.findElement(
                        By.xpath("//div[contains(text(), \"добавлен к сравнению\") and contains(text(), \"Xiaomi\")]")).isDisplayed());
                countXiaomi++;
            } else if (getNameSmartphone(allMobilePhone.get(i).getAttribute("data-bem")).contains("ZTE")
                    && countZTE == 0) {
                allMobilePhone.get(i).click();
                Assert.assertTrue(driver.findElement(
                        By.xpath("//div[contains(text(), \"добавлен к сравнению\") and contains(text(), \"ZTE\")]")).isDisplayed());
                countZTE++;
            }
        }
        return this;
    }

    public ComparisonPage useComparisonButton (){
        inComparisonButton.click();
        return new ComparisonPage(driver, wait);
    }
}
