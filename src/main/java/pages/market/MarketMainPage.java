package pages.market;

import utils.WaitersHelpClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketMainPage extends WaitersHelpClass {
    private WebDriver driver;
    protected static Logger logger = LogManager.getLogger(MarketMainPage.class);


    private final String POPUP_CSS = "div.popup2__content"; // информационное popup окно.
    private final String CATALOG_ELECTRONIC_XPATCH = "//div[@class = \"_35SYuInI1T _1vnugfYUli\"]/a[@href = \"/catalog--elektronika/54440\"]/..";
    private final String MOBILE_PHONE_XPATCH = "//a[@href=\"/catalog--mobilnye-telefony/54726/list?hid=91491\"]";

    @FindBy(css = POPUP_CSS)
    private WebElement popup;
    @FindBy(xpath = CATALOG_ELECTRONIC_XPATCH)
    private WebElement catalogElectronic;
    @FindBy(xpath = MOBILE_PHONE_XPATCH)
    private WebElement mobilPhone;

    public MarketMainPage( WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MarketMainPage waitClosePopupWindow() {
        logger.info("Ждем закрытия инфо-окна");
        try {
            waitElementVisible(driver, popup);
        } catch (TimeoutException e) {
            throw new IllegalArgumentException("Too long!");
        }
        return this;
    }

    public MarketMainPage clickByButtonCatalog() {
        logger.info("Переходим в раздел \"Каталог электроники\"");
        useElement(catalogElectronic, driver);
        return this;
    }
    public MobilPhonePage clickByButtonTelephone() {
        logger.info("Переходим в раздел \"Мобильные телефоны\"");
        useElement(mobilPhone, driver);
        return new MobilPhonePage(driver);
    }
}