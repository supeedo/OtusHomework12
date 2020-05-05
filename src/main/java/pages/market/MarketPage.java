package pages.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MarketPage {
    WebDriver driver;
    WebDriverWait wait;
    String menuElectronic = "//div[@class = \"_35SYuInI1T _1vnugfYUli\"]/a[@href = \"/catalog--elektronika/54440\"]/..";

    public MarketPage( WebDriver driver, WebDriverWait wait ) {
        this.driver = driver;
        this.wait = wait;
    }
//    @FindBy(how = How.XPATH, using = "//div[@class='_35SYuInI1T _1vnugfYUli']/a[contains(span,'Электроника')]")
//    static WebElement leftMenu;

  //  @FindBy(how = How.XPATH, using = "")
  //  static WebElement menuTelephone;

    public void menuElectronic (){
    /*    try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       // WebElement element = driver.findElement(By.xpath(menuElectronic));
         wait.until(driver->driver.findElement(By.xpath(menuElectronic))).click();
     //   return element;
    }
}