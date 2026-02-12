package base;

public class TestData {
    // Тестовый номер телефона
    public static final String TEST_PHONE_NUMBER = "297777777";

    // Ожидаемые данные
    public static final String EXPECTED_BLOCK_TITLE = "Онлайн пополнение без комиссии";
    public static final String EXPECTED_MORE_DETAILS_URL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    // Платежные системы (можно добавить больше)
    public static final String[] PAYMENT_SYSTEMS = {
            "visa", "mastercard", "belkart", "mir"
    };
}