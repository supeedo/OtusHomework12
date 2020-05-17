package test.ru.market;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.market.ComparisonPage;
import pages.market.MarketPage;
import pages.market.MobilPhonePage;
import test.BaseTest;

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
public class SimpleMarketTest extends BaseTest {
    MarketPage mainPage;
    MobilPhonePage mobilPhonePage;
    ComparisonPage cPage;

    @Test(description = "Test YandexMarket page and filters on market")
    public void marketTest() {
        logger.info("Переходим на сайт: {}", cfg.URL_MARKET());
        driver.navigate().to(cfg.URL_MARKET());
        mainPage = new MarketPage(driver, wait);
        logger.info("Ждем закрытия инфо-окна, переходим в раздел Мобильных телефонов");
        mobilPhonePage = mainPage
                .waitClosePopupWindow()
                .useMenu();
        logger.info("Фильтруем по производителю, по цене, добавляем телефоны к сравнению, переходим на страницу сравнения");
        cPage = mobilPhonePage
                .useMobileFilter()
                .usePriceFilter()
                .useShowAllButton()
                .takeAllMobile()
                .useComparisonButton();
        logger.info("Проверяем, что в сравнении 2 телефона");
        Assert.assertEquals(2, cPage.countCompareElements());
        logger.info("Отображаем все характеристики");
        cPage.changeCharasterMenuInAll();
        logger.info("Проверяем, что отображается строка \"Операционная система\"");
        Assert.assertTrue(cPage.checkElement());
        logger.info("Отображаем отличающиеся характеристики");
        cPage.changeCharasterMenuInVarious();
        logger.info("Проверяем что строка \"Операционная система\" не отображается");
        Assert.assertFalse(cPage.checkElement());
    }
}