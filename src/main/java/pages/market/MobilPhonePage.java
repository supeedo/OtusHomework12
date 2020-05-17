package pages.market;

import helpers.PageHelpClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MobilPhonePage extends PageHelpClass {
    WebDriver driver;
    WebDriverWait wait;

    private final String xiaomiXpatch = "//input[@id = \"7893318_7701962\"]/..";
    private final String zteXpatch = "//input[@id=\"7893318_1007740\"]/..";
    private final String priceFilterButtonXpatch = "//a[text()=\"по цене\"]";
    private final String showAllButtonXpatch = "//a[@role='button' and contains(@class,'button button_size_m')]";
    private final String allMobilePhoneXpatch = "//div[contains(@data-bem,\"n-user-lists_type_compare\")]/.";
    private final String inComparisonButtonCSS = "a[class^=button][href$=rmmbr]";

    @FindBy(xpath = xiaomiXpatch)
    private WebElement xiaomi;
    @FindBy(xpath = zteXpatch)
    private WebElement zte;
    @FindBy(xpath = priceFilterButtonXpatch)
    private WebElement priceFilterButton;
    @FindBy(xpath = showAllButtonXpatch)
    private WebElement showAllButton;
    @FindBy(css = inComparisonButtonCSS)
    private WebElement inComparisonButton;
    @FindAll(@FindBy(xpath = allMobilePhoneXpatch))
    private List<WebElement> allMobilePhone;

    public MobilPhonePage( WebDriver driver, WebDriverWait wait ) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public MobilPhonePage useMobileFilter() {
        xiaomi.click();
        zte.click();
        return this;
    }

    public MobilPhonePage usePriceFilter() {
        priceFilterButton.click();
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
