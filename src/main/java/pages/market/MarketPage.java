package pages.market;

import helpers.PageHelpClass;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MarketPage extends PageHelpClass {
    private static WebDriver driver;
    private static WebDriverWait wait;


    private final String popupCSS = "div.popup2__content"; // информационное popup окно.
    private final String catalogElectronicXpatch = "//div[@class = \"_35SYuInI1T _1vnugfYUli\"]/a[@href = \"/catalog--elektronika/54440\"]/..";
    private final String mobilPhoneXpatch = "//a[@href=\"/catalog--mobilnye-telefony/54726/list?hid=91491\"]";

    @FindBy(css = popupCSS)
    private WebElement popup;
    @FindBy(xpath = catalogElectronicXpatch)
    private WebElement catalogElectronic;
    @FindBy(xpath = mobilPhoneXpatch)
    private WebElement mobilPhone;

    public MarketPage( WebDriver driver, WebDriverWait wait ) {

        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    public MarketPage waitClosePopupWindow() {
        try {
            waitElementVisible(driver, popup);
        }catch(TimeoutException e){
            throw new IllegalArgumentException("Too long!");
        }
        return this;
    }

    public MobilPhonePage useMenu() {
        //catalogElectronic.click();
       // mobilPhone.click();
        useElement(catalogElectronic, driver);
        useElement(mobilPhone, driver);
        return new MobilPhonePage(driver, wait);
    }
}