package pages.market;

import helpers.WaitersHelpClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Stream;

public class MobilPhonePage extends WaitersHelpClass {
    private WebDriver driver;
    private WebDriverWait wait;
    protected static Logger logger = LogManager.getLogger(MobilPhonePage.class);

    private final String XIAOMI_XPATCH = "//input[@id = \"7893318_7701962\"]/..";
    private final String ZTE_XPATCH = "//input[@id=\"7893318_1007740\"]/..";
    private final String PRICE_FILTER_BUTTON_XPATCH = "//a[text()=\"по цене\"]";
    private final String BUTTON_SHOWALL_XPATCH = "//a[@role='button' and contains(@class,'button button_size_m')]";
    private final String ALL_MOBILE_ELEMENTS_XPATCH = "//div[contains(@data-bem,\"n-user-lists_type_compare\")]/."; // //div[@class='_1sHDZU491h _1rDffWmsUY cia-vs cia-cs']/.
    private final String COMPARISON_BUTTON_CSS = "a[class^=button][href$=rmmbr]";
    private final String BRAND_NAME_FILTERS_XPATCH = "//label[@class  = '_2IwbFpEZn7 _1e7iX1B2oW']/input//..";

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
    @FindAll(@FindBy(xpath = BRAND_NAME_FILTERS_XPATCH))
    private List<WebElement> allBrandFilters;

    public MobilPhonePage( WebDriver driver, WebDriverWait wait ) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public MobilPhonePage selectMobileFilter1() {
        logger.info("Фильтруем по производителю 1");
        useElement(xiaomi, driver);
        return this;
    }
    public MobilPhonePage selectMobileFilter2() {
        logger.info("Фильтруем по производителю 2");
        useElement(zte, driver);
        return this;
    }

    public MobilPhonePage usePriceFilter() {
        logger.info("Фильтруем по цене");
        useElement(priceFilterButton, driver);
        return this;
    }

    public MobilPhonePage useShowAllButton() {
        logger.info("Отображаем все отфильтрованные телефоны через кнопку \"Показать все\"");
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
        logger.info("Сравниваем отобразившиеся телефоны, добавляя первые в сравнение");
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

    public ComparisonPage useComparisonButton() {
        logger.info("Переходим на страницу сравнения");
        inComparisonButton.click();
        return new ComparisonPage(driver, wait);
    }
}
