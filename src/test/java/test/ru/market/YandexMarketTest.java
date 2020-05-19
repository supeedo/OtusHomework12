package test.ru.market;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.market.ComparisonPage;
import pages.market.MarketMainPage;
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
public class YandexMarketTest extends BaseTest {
    private MarketMainPage mainPage;
    private MobilPhonePage mobilPhonePage;
    private ComparisonPage comparePage;
    protected static Logger logger = LogManager.getLogger(YandexMarketTest.class);

    @Test(description = "Test YandexMarket page and filters on market")
    public void marketTest() {
        logger.info("Переходим на сайт: {}", cfg.URL_MARKET());
        driver.navigate().to(cfg.URL_MARKET());

        mainPage = new MarketMainPage(driver, wait);

        mobilPhonePage = mainPage
                .waitClosePopupWindow()
                .useMenu();
        comparePage = mobilPhonePage
                .selectMobileFilter1()
                .selectMobileFilter2()
                .usePriceFilter()
                .useShowAllButton()
                .additToComparation("ZTE")
                .checkComparringDisplay("ZTE")
                .additToComparation("Xiaomi")
                .checkComparringDisplay("Xiaomi")
                .useComparisonButton();
        comparePage
                .assertCountCompareElements(2)
                .changeCharasterMenuInAll()
                .checkElementVisible()
                .changeCharasterMenuInVarious()
                .checkElementNotVisible();
    }
}