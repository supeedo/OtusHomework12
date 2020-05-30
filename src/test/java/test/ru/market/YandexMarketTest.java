package test.ru.market;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.market.ComparisonPage;
import pages.market.MarketMainPage;
import pages.market.MobilPhonePage;
import test.PrepareTest;

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

//@Listeners(ExecutionListener.class)
public class YandexMarketTest extends PrepareTest {
    private MarketMainPage mainPage;
    private MobilPhonePage mobilPhonePage;
    private ComparisonPage comparePage;

    @BeforeMethod
    public void setUpMethod() {
        mainPage = new MarketMainPage(driver);
        mobilPhonePage = new MobilPhonePage(driver);
        comparePage = new ComparisonPage(driver);
    }

    @Test(description = "Test YandexMarket page and filters on market")
    public void marketTest() {
        mainPage
                .waitClosePopupWindow()
                .clickByButtonCatalog()
                .clickByButtonTelephone();
        mobilPhonePage
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