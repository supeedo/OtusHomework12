package test.ru.market;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Домашнее задание
 * Написать автотест для каталога Яндекс.Маркет
 * Реализуйте автоматический тест, используя Java + Selenium
 * <p>
 * Шаги теста:
 * - Открыть в Chrome сайт Яндекс.Маркет - раздел "Мобильные телефоны"
 * - Отфильтровать список товаров: RedMi и Xiaomi
 * - Отсортировать список товаров по цене (от меньшей к большей)
 * - Добавить первый в списке RedMi
 * -- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
 * - Добавить первый в списке Xiaomi
 * -- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
 * - Перейти в раздел Сравнение
 * -- Проверить, что в списке товаров 2 позиции
 * - Нажать на опцию "все характеристики"
 * -- Проверить, что в списке характеристик появилась позиция "Операционная система"
 * - Нажать на опцию "различающиеся характеристики"
 * -- Проверить, что позиция "Операционная система" не отображается в списке характеристик
 */
public class SimpleMarketTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void upDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @Test(description = "Test YandexMarket page and filters on market")
    public void marketTest() {
        driver.navigate().to("http://market.yandex.ru");
        try {
            WebElement pp = wait.until(driver -> driver.findElement(By.cssSelector("div.popup2__content")));
            while (pp.isDisplayed() && pp.isEnabled()) {
                if (!(pp.isEnabled() && pp.isDisplayed())) {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
        }
        wait.until(driver -> driver.findElement(By
                .xpath("//div[@class = \"_35SYuInI1T _1vnugfYUli\"]/a[@href = \"/catalog--elektronika/54440\"]/.."))).click();
        wait.until(driver -> driver.findElement(By
                .xpath("//a[@href=\"/catalog--mobilnye-telefony/54726/list?hid=91491\"]"))).click();
        wait.until(driver -> driver.findElement(By
                .xpath("//input[@id = \"7893318_7701962\"]/.."))).click();  //  Сяоми
        wait.until(driver -> driver.findElement(By
                .xpath("//input[@id=\"7893318_1007740\"]/.."))).click();  // ЗТЕ
        wait.until(driver -> driver.findElement(By
                .xpath("//a[text()=\"по цене\"]"))).click();
        while (true) {
            try {
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath("//a[@role='button' and contains(@class,'button button_size_m')]"))).click();
            } catch (TimeoutException e) {
                break;
            }catch (StaleElementReferenceException e){}
        }

        List<WebElement> list2 = driver.findElements(By.xpath("//div[contains(@data-bem,\"n-user-lists_type_compare\")]/."));
        System.out.println(list2.size());

        int countXiaomi = 0;
        int countZTE = 0;

        for (int i = 0; i < list2.size(); i++) {
            System.out.println(getNameSmartphone(list2.get(i).getAttribute("data-bem")));
            if (countXiaomi == 1 && countZTE == 1) {
                break;
            } else if (getNameSmartphone(list2.get(i).getAttribute("data-bem")).contains("Xiaomi")
                    && countXiaomi == 0) {
                list2.get(i).click();
                Assert.assertTrue(driver.findElement(
                        By.xpath("//div[contains(text(), \"добавлен к сравнению\") and contains(text(), \"Xiaomi\")]")).isDisplayed());
                System.out.println("добавили Сяоми");
                countXiaomi++;
            } else if (getNameSmartphone(list2.get(i).getAttribute("data-bem")).contains("ZTE")
                    && countZTE == 0) {
                list2.get(i).click();
                Assert.assertTrue(driver.findElement(
                        By.xpath("//div[contains(text(), \"добавлен к сравнению\") and contains(text(), \"ZTE\")]")).isDisplayed());
                System.out.println("Добавили ЗТЕ");
                countZTE++;
            }
        }
        wait.until(driver -> driver.findElement(
                By.cssSelector("a[class^=button][href$=rmmbr]"))).click();
        int countInComparisson = driver.findElements(By.cssSelector("div[class^=n-compare-cell][data-bem]")).size();
        Assert.assertEquals(2, countInComparisson);  //  Ассерт количества проверяемых на странице
        wait.until(driver -> driver.findElement(
                By.cssSelector("span[class = \"link n-compare-show-controls__all\"][role=button] .link__inner"))).click();
         try {
             Assert.assertTrue(wait.until(ExpectedConditions
                     .visibilityOf(driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")))).isDisplayed());
         }catch (TimeoutException e){}
        wait.until(driver -> driver.findElement(
                By.cssSelector("span[class = \"link n-compare-show-controls__diff\"][role=button] .link__inner"))).click();
        try {
            Assert.assertFalse(wait.until(ExpectedConditions
                    .visibilityOf(driver.findElement(By.xpath("//div[text()=\"Операционная система\"]")))).isDisplayed());
        }catch (TimeoutException e){}
    }

    public String getNameSmartphone( String json ) {
        String tempList[] = json.split(":");
        return getClearName(tempList[3]);
    }

    public String getClearName( String fullName ) {
        String tempList[] = fullName.split("\"");
        return tempList[1];
    }
}