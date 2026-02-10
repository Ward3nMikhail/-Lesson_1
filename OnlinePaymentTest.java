package tests;

import base.BaseTest;
import base.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.HomePage;
import static org.junit.jupiter.api.Assertions.*;

public class OnlinePaymentTest extends BaseTest {

    @Test
    @DisplayName("Проверка отображения блока 'Онлайн пополнение без комиссии'")
    public void testOnlinePaymentBlockIsDisplayed() {
        HomePage homePage = new HomePage(driver);

        assertTrue(homePage.isOnlinePaymentBlockDisplayed(),
                "Блок 'Онлайн пополнение без комиссии' должен отображаться на странице");
    }

    @Test
    @DisplayName("Проверка названия блока")
    public void testBlockTitle() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();

        String actualTitle = homePage.getBlockTitle();
        assertEquals(TestData.EXPECTED_BLOCK_TITLE, actualTitle,
                "Название блока должно быть: " + TestData.EXPECTED_BLOCK_TITLE);
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();

        // Проверяем, что логотипы отображаются
        assertTrue(homePage.arePaymentSystemLogosDisplayed(),
                "Логотипы платежных систем должны отображаться");

        // Проверяем количество логотипов (минимум 3)
        int logosCount = homePage.getPaymentSystemLogosCount();
        assertTrue(logosCount >= 3,
                "Должно быть отображено минимум 3 логотипа платежных систем. Найдено: " + logosCount);

        System.out.println("Найдено логотипов платежных систем: " + logosCount);
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreDetailsLink() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();

        // Проверяем, что ссылка отображается
        assertTrue(homePage.isMoreDetailsLinkDisplayed(),
                "Ссылка 'Подробнее о сервисе' должна отображаться");

        // Сохраняем текущую вкладку
        String originalWindow = driver.getWindowHandle();

        // Кликаем по ссылке
        homePage.clickMoreDetailsLink();

        try {
            // Ждем открытия новой вкладки
            Thread.sleep(3000);

            // Переключаемся на новую вкладку
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            // Проверяем URL новой страницы
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("help") || currentUrl.contains("platezh"),
                    "URL должен содержать информацию о платежах. Текущий URL: " + currentUrl);

            System.out.println("Открыта страница: " + currentUrl);

            // Закрываем новую вкладку и возвращаемся
            driver.close();
            driver.switchTo().window(originalWindow);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Заполнение формы и проверка кнопки 'Продолжить' для услуг связи")
    public void testFillFormAndContinueButton() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();

        // Проверяем, что поле ввода доступно
        assertTrue(homePage.isPhoneInputEnabled(),
                "Поле ввода номера телефона должно быть доступно");

        // Выбираем "Услуги связи"
        homePage.selectServiceType();

        // Вводим тестовый номер
        homePage.enterPhoneNumber(TestData.TEST_PHONE_NUMBER);

        // Проверяем, что кнопка активна
        assertTrue(homePage.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна после заполнения формы");

        // Нажимаем кнопку (можно проверить переход на следующую страницу)
        homePage.clickContinueButton();

        // Проверяем, что произошел переход или появилась следующая форма
        try {
            Thread.sleep(3000); // Даем время для загрузки
            String currentUrl = driver.getCurrentUrl();

            // Проверяем, что мы остались на сайте mts.by
            assertTrue(currentUrl.contains("mts.by"),
                    "После нажатия кнопки 'Продолжить' должны остаться на сайте mts.by");

            System.out.println("Успешно перешли по URL: " + currentUrl);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"297777777", "291234567", "292345678"})
    @DisplayName("Проверка различных номеров телефонов")
    public void testDifferentPhoneNumbers(String phoneNumber) {
        HomePage homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();

        // Выбираем "Услуги связи"
        homePage.selectServiceType();

        // Вводим номер
        homePage.enterPhoneNumber(phoneNumber);

        // Проверяем, что кнопка активна
        assertTrue(homePage.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна для номера: " + phoneNumber);

        System.out.println("Телефон " + phoneNumber + " - кнопка активна");
    }

    @Test
    @DisplayName("Комплексная проверка всего блока")
    public void testCompleteBlockValidation() {
        HomePage homePage = new HomePage(driver);

        // 1. Проверка отображения блока
        assertTrue(homePage.isOnlinePaymentBlockDisplayed());

        // 2. Проверка заголовка
        String title = homePage.getBlockTitle();
        assertEquals(TestData.EXPECTED_BLOCK_TITLE, title);

        // 3. Проверка логотипов
        assertTrue(homePage.arePaymentSystemLogosDisplayed());
        int logosCount = homePage.getPaymentSystemLogosCount();
        assertTrue(logosCount >= 3);

        // 4. Проверка ссылки
        assertTrue(homePage.isMoreDetailsLinkDisplayed());

        // 5. Проверка формы
        assertTrue(homePage.isPhoneInputEnabled());

        // 6. Заполнение формы
        homePage.selectServiceType();
        homePage.enterPhoneNumber(TestData.TEST_PHONE_NUMBER);

        // 7. Проверка кнопки
        assertTrue(homePage.isContinueButtonEnabled());

        System.out.println("Все проверки блока 'Онлайн пополнение без комиссии' прошли успешно!");
    }
}
