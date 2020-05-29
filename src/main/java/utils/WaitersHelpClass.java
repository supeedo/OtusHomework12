package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WaitersHelpClass {

    public String getNameSmartphone( String json ) {
        String tempList[] = json.split(":");
        return getClearName(tempList[3]);
    }

    public String getClearName( String fullName ) {
        String tempList[] = fullName.split("\"");
        return tempList[1];
    }

    public static Boolean waitElementVisible( WebDriver driver, WebElement element ) {
        return new WebDriverWait(driver, 15, 2000).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
    }

    public static Boolean waitElementNotVisible( WebDriver driver, WebElement element ) {
        return new WebDriverWait(driver, 15, 2000).until(ExpectedConditions.not(ExpectedConditions.invisibilityOf(element)));
    }

    public static Boolean checkDispayedElement( WebDriver driver, WebElement webElement ) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions
                .visibilityOf(webElement)).isDisplayed();
    }

    public static void checkDispayedElementNotVisible( WebDriver driver, WebElement webElement ) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.not(ExpectedConditions.
                visibilityOf(webElement)));
    }

    public static void useElement( WebElement webElement, WebDriver driver ) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(webElement)).click();
    }
}
