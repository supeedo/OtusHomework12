package test.ru.market;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.market.MarketPage;
import pages.market.MobilPhonePage;
import test.BaseTest;

import java.util.List;

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
 * <p>
 * Критерии оценки: +1 балл - код компилируется и запускается
 * +1 балл - код запускается без дополнительных действий со стороны проверяющего (не нужно скачивать WebDriver, решать конфликты зависимостей и т.п.)
 * +1 балл - задействован WebDriverManager
 * +1 балл - логи пишутся в консоль и файл
 * +1 балл - тест проходит без падений (допускается падение теса только при некорректной работе SUT)
 * +2 балла - тест проходит при разной скорости интернет-соединения
 * +1 балл - используются явные и неявные ожидания
 * <p>
 * -1 балл - за каждый Thread.sleep()
 */
public class SimpleMarketTest {
    WebDriver driver;
    WebDriverWait wait;
    WebElement all;

    @BeforeTest
    public void upDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 5);
    }

    @Test(description = "Test YandexMarket page and filters on market")
    public void marketTest() {
        driver.navigate().to("http://market.yandex.ru");
        try {
            WebElement pp = driver.findElement(By.xpath("//div[@class=\"popup2__content\"]"));
            while (pp.isDisplayed() && pp.isEnabled()) {
                if (!(pp.isEnabled() && pp.isDisplayed())) {
                    break;
                }
            }
        } catch (NoSuchElementException e) {
        }

        wait.until(driver -> driver.findElement(By.xpath("//div[@class = \"_35SYuInI1T _1vnugfYUli\"]/a[@href = \"/catalog--elektronika/54440\"]/.."))).click();
        wait.until(driver -> driver.findElement(By.xpath("//a[@href=\"/catalog--mobilnye-telefony/54726/list?hid=91491\"]"))).click();
        driver.findElement(By.xpath("//input[@id = \"7893318_7701962\"]/..")).click(); //Сяоми
        driver.findElement(By.xpath("//input[@id=\"7893318_1007740\"]/..")).click(); //ZTE
        driver.findElement(By.xpath("(//a[@class=\"link link_theme_major n-filter-sorter__link i-bem link_js_inited\"])[2]")).click();

        while (true) {
            try {
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//a[contains(@class,'button button_size_m')]"))).click();
            } catch (TimeoutException e) {
                break;
            }
        }
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='n-snippet-cell2__brand-name']"));
        System.out.println(list.size());
        int countXiaomi = 0;
        int countZTE = 0;

        for (int i = 0; i < list.size() ; i++) {
            System.out.println(list.get(i).getText());
            if(list.get(i).getText().equals("XIAOMI")
            && countXiaomi == 0){
                System.out.println("добавили Сяоми");
                countXiaomi++;
            }else if(list.get(i).getText().equals("ZTE")
            && countZTE == 0){
                System.out.println("Добавили ЗТЕ");
                countZTE++;
            }
        }
    }
}