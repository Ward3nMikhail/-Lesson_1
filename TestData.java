package base;

public class TestData {
    // Тестовые данные
    public static final String TEST_PHONE_NUMBER = "297777777";
    public static final String TEST_AMOUNT = "5"; // Сумма по умолчанию

    // Ожидаемые заголовки и тексты
    public static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение без комиссии";

    // Плейсхолдеры для разных типов услуг
    public static final String SERVICE_CONNECTION_PLACEHOLDER = "Номер телефона";
    public static final String HOME_INTERNET_PLACEHOLDER = "Номер лицевого счета";
    public static final String INSTALLMENT_PLACEHOLDER = "Номер договора";
    public static final String DEBT_PLACEHOLDER = "Номер телефона или лицевого счета";

    // Платежные системы в модальном окне
    public static final String[] PAYMENT_SYSTEMS_MODAL = {
            "visa", "mastercard", "belkart", "mir"
    };

    // Плейсхолдеры полей карты
    public static final String CARD_NUMBER_PLACEHOLDER = "Номер карты";
    public static final String CARD_EXPIRY_PLACEHOLDER = "Срок действия";
    public static final String CARD_CVC_PLACEHOLDER = "CVC";

    // Тексты кнопок
    public static final String CONTINUE_BUTTON_TEXT = "Продолжить";
    public static final String PAY_BUTTON_TEXT_PREFIX = "Оплатить";
}