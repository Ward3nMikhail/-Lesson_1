package tests;

import base.BaseTest;
import base.TestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.HomePage;
import pages.PaymentModalPage;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Расширенные тесты блока 'Онлайн пополнение без комиссии'")
public class OnlinePaymentTest extends BaseTest {

    private HomePage homePage;
    private PaymentModalPage paymentModalPage;

    @BeforeEach
    public void setUpTest() {
        homePage = new HomePage(driver);
        homePage.waitForBlockToLoad();
    }

    @Test
    @Order(1)
    @DisplayName("1. Проверка названия блока")
    public void testBlockTitle() {
        String actualTitle = homePage.getBlockTitle();
        assertEquals(TestData.EXPECTED_BLOCK_TITLE, actualTitle,
                "Название блока должно быть: " + TestData.EXPECTED_BLOCK_TITLE);
        System.out.println("✓ Проверено название блока: " + actualTitle);
    }

    @Test
    @Order(2)
    @DisplayName("2. Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        int logosCount = homePage.getPaymentSystemLogosCount();
        assertTrue(logosCount >= 3,
                "Должно быть отображено минимум 3 логотипа платежных систем. Найдено: " + logosCount);
        System.out.println("✓ Найдено логотипов платежных систем: " + logosCount);
    }

    @Test
    @Order(3)
    @DisplayName("3. Проверка работы ссылки 'Подробнее о сервисе'")
    public void testMoreDetailsLink() {
        assertTrue(homePage.isMoreDetailsLinkDisplayed(),
                "Ссылка 'Подробнее о сервисе' должна отображаться");

        String originalWindow = driver.getWindowHandle();
        homePage.clickMoreDetailsLink();

        try {
            // Ожидание открытия новой вкладки
            Thread.sleep(3000);

            // Переключение на новую вкладку
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalWindow.contentEquals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }

            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("mts.by"),
                    "Открыта страница сайта MTS: " + currentUrl);

            System.out.println("✓ Ссылка 'Подробнее о сервисе' работает. Открыта страница: " + currentUrl);

            // Возврат на исходную вкладку
            driver.close();
            driver.switchTo().window(originalWindow);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    @DisplayName("4. Проверка плейсхолдеров для всех вариантов оплаты")
    public void testPlaceholdersForAllPaymentOptions() {
        // Проверка для "Услуги связи"
        homePage.selectServiceConnectionTab();
        String connectionPlaceholder = homePage.getActiveInputPlaceholder();
        assertEquals(TestData.SERVICE_CONNECTION_PLACEHOLDER, connectionPlaceholder,
                "Неверный плейсхолдер для 'Услуги связи'");
        System.out.println("✓ Плейсхолдер 'Услуги связи': " + connectionPlaceholder);

        // Проверка для "Домашний интернет"
        homePage.selectHomeInternetTab();
        String internetPlaceholder = homePage.getActiveInputPlaceholder();
        assertEquals(TestData.HOME_INTERNET_PLACEHOLDER, internetPlaceholder,
                "Неверный плейсхолдер для 'Домашний интернет'");
        System.out.println("✓ Плейсхолдер 'Домашний интернет': " + internetPlaceholder);

        // Проверка для "Рассрочка"
        homePage.selectInstallmentTab();
        String installmentPlaceholder = homePage.getActiveInputPlaceholder();
        assertEquals(TestData.INSTALLMENT_PLACEHOLDER, installmentPlaceholder,
                "Неверный плейсхолдер для 'Рассрочка'");
        System.out.println("✓ Плейсхолдер 'Рассрочка': " + installmentPlaceholder);

        // Проверка для "Задолженность"
        homePage.selectDebtTab();
        String debtPlaceholder = homePage.getActiveInputPlaceholder();
        assertEquals(TestData.DEBT_PLACEHOLDER, debtPlaceholder,
                "Неверный плейсхолдер для 'Задолженность'");
        System.out.println("✓ Плейсхолдер 'Задолженность': " + debtPlaceholder);
    }

    @Test
    @Order(5)
    @DisplayName("5. Заполнение формы 'Услуги связи' и проверка модального окна")
    public void testServiceConnectionPaymentFlow() {
        // 1. Выбираем "Услуги связи"
        homePage.selectServiceConnectionTab();

        // 2. Вводим номер телефона
        homePage.enterPhoneNumber(TestData.TEST_PHONE_NUMBER);

        // 3. Проверяем, что кнопка активна
        assertTrue(homePage.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна после ввода номера");

        // 4. Нажимаем кнопку "Продолжить"
        homePage.clickContinueButton();

        // 5. Инициализируем Page Object для модального окна
        paymentModalPage = new PaymentModalPage(driver);

        // 6. Проверяем, что модальное окно открылось
        assertTrue(paymentModalPage.isPaymentModalDisplayed(),
                "Модальное окно оплаты должно отображаться");
        System.out.println("✓ Модальное окно оплаты открылось");

        // 7. Проверяем отображение номера телефона
        String displayedPhone = paymentModalPage.getDisplayedPhoneNumber();
        assertTrue(displayedPhone.contains(TestData.TEST_PHONE_NUMBER),
                "В модальном окне должен отображаться введенный номер телефона. Ожидалось: " +
                        TestData.TEST_PHONE_NUMBER + ", получено: " + displayedPhone);
        System.out.println("✓ Номер телефона отображается корректно: " + displayedPhone);

        // 8. Проверяем отображение суммы
        String displayedAmount = paymentModalPage.getDisplayedAmount();
        assertNotNull(displayedAmount, "Сумма должна отображаться в модальном окне");
        assertFalse(displayedAmount.isEmpty(), "Сумма не должна быть пустой");
        System.out.println("✓ Сумма отображается: " + displayedAmount);

        // 9. Проверяем сумму на кнопке оплаты
        String payButtonText = paymentModalPage.getPayButtonText();
        assertTrue(payButtonText.contains(TestData.PAY_BUTTON_TEXT_PREFIX),
                "Текст кнопки должен содержать: " + TestData.PAY_BUTTON_TEXT_PREFIX);
        assertTrue(payButtonText.contains(displayedAmount),
                "На кнопке должна отображаться сумма: " + displayedAmount);
        System.out.println("✓ Текст кнопки оплаты: " + payButtonText);

        // 10. Проверяем плейсхолдеры полей карты
        assertEquals(TestData.CARD_NUMBER_PLACEHOLDER, paymentModalPage.getCardNumberPlaceholder(),
                "Неверный плейсхолдер для номера карты");
        assertEquals(TestData.CARD_EXPIRY_PLACEHOLDER, paymentModalPage.getCardExpiryPlaceholder(),
                "Неверный плейсхолдер для срока действия карты");
        assertEquals(TestData.CARD_CVC_PLACEHOLDER, paymentModalPage.getCardCvcPlaceholder(),
                "Неверный плейсхолдер для CVC");
        System.out.println("✓ Плейсхолдеры полей карты корректны");

        // 11. Проверяем, что поля карты пустые
        assertTrue(paymentModalPage.areCardFieldsEmpty(),
                "Поля карты должны быть пустыми при открытии модального окна");
        System.out.println("✓ Поля карты пустые (ожидаемое состояние)");

        // 12. Проверяем наличие иконок платежных систем в модальном окне
        int iconsCount = paymentModalPage.getPaymentSystemIconsCount();
        assertTrue(iconsCount >= 2,
                "В модальном окне должно быть минимум 2 иконки платежных систем. Найдено: " + iconsCount);
        assertTrue(paymentModalPage.arePaymentSystemIconsDisplayed(),
                "Иконки платежных систем должны отображаться");
        System.out.println("✓ Найдено иконок платежных систем в модальном окне: " + iconsCount);

        // 13. Закрываем модальное окно
        paymentModalPage.closeModal();
        System.out.println("✓ Модальное окно закрыто");
    }

    @ParameterizedTest
    @Order(6)
    @CsvSource({
            "297777777, 5",
            "291234567, 10",
            "292345678, 20"
    })
    @DisplayName("6. Параметризованный тест разных номеров и сумм")
    public void testDifferentPhoneNumbersAndAmounts(String phoneNumber, String amount) {
        homePage.selectServiceConnectionTab();
        homePage.enterPhoneNumber(phoneNumber);
        homePage.enterAmount(amount);

        assertTrue(homePage.isContinueButtonEnabled(),
                "Кнопка 'Продолжить' должна быть активна для номера: " + phoneNumber);

        homePage.clickContinueButton();

        paymentModalPage = new PaymentModalPage(driver);
        assertTrue(paymentModalPage.isPaymentModalDisplayed());

        String displayedPhone = paymentModalPage.getDisplayedPhoneNumber();
        assertTrue(displayedPhone.contains(phoneNumber),
                "Номер телефона должен отображаться корректно");

        System.out.println(String.format("✓ Тест для номера %s и суммы %s руб. пройден",
                phoneNumber, amount));

        paymentModalPage.closeModal();
    }

    @Test
    @Order(7)
    @DisplayName("7. Комплексная проверка всего функционала")
    public void testCompleteFunctionality() {
        System.out.println("=== Начало комплексной проверки ===");

        // Проверка базовых элементов
        testBlockTitle();
        testPaymentSystemLogos();
        testMoreDetailsLink();

        // Проверка всех табов
        testPlaceholdersForAllPaymentOptions();

        // Проверка основного сценария оплаты
        testServiceConnectionPaymentFlow();

        System.out.println("=== Комплексная проверка завершена успешно ===");
    }

    @AfterEach
    public void tearDownTest() {
        // Дополнительная очистка после каждого теста
        try {
            // Если модальное окно открыто - закрываем его
            if (paymentModalPage != null && paymentModalPage.isPaymentModalDisplayed()) {
                paymentModalPage.closeModal();
            }
        } catch (Exception e) {
            // Игнорируем исключения при закрытии
        }
    }
}