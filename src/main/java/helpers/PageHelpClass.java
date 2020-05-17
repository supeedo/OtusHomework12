package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageHelpClass {
    public String getNameSmartphone( String json ) {
        String tempList[] = json.split(":");
        return getClearName(tempList[3]);
    }

    public String getClearName( String fullName ) {
        String tempList[] = fullName.split("\"");
        return tempList[1];
    }

    public static Boolean waitElementVisible( WebDriver driver, WebElement element ) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    public static Boolean waitElementNotVisible( WebDriver driver, WebElement element ) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.not(ExpectedConditions.invisibilityOf(element)));
    }

    public static Boolean checkDispayedElement(WebDriver driver, WebElement webElement ) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions
                    .visibilityOf(webElement)).isDisplayed();
    }

    public static void useElement(WebElement webElement, WebDriver driver){
        new WebDriverWait(driver, 10, 2000).until(ExpectedConditions.visibilityOf(webElement)).click();
    }
}
