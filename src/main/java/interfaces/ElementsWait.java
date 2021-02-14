package interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface ElementsWait {
    default WebElement waitElementByXpatch( WebDriver driver, String xPatch ){
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPatch)));
    }

    default WebElement waitElementByCSS( WebDriver driver, String css ){
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css)));
    }
}
