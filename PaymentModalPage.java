package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class PaymentModalPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Модальное окно оплаты
    @FindBy(xpath = "//div[contains(@class, 'payment-modal') or contains(@class, 'modal-content')]")
    private WebElement paymentModal;

    // Заголовок модального окна
    @FindBy(xpath = "//div[contains(@class, 'modal-header')]//h3")
    private WebElement modalTitle;

    // Отображаемый номер телефона
    @FindBy(xpath = "//div[contains(@class, 'phone-info')]//span[contains(@class, 'phone-number')]")
    private WebElement displayedPhoneNumber;

    // Отображаемая сумма
    @FindBy(xpath = "//div[contains(@class, 'amount-info')]//span[contains(@class, 'amount')]")
    private WebElement displayedAmount;

    // Сумма на кнопке оплаты
    @FindBy(xpath = "//button[contains(@class, 'pay-button')]")
    private WebElement payButton;

    // Поля для ввода реквизитов карты
    @FindBy(xpath = "//input[@placeholder='Номер карты']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@placeholder='Срок действия']")
    private WebElement cardExpiryInput;

    @FindBy(xpath = "//input[@placeholder='CVC']")
    private WebElement cardCvcInput;

    // Иконки платежных систем в модальном окне
    @FindBy(xpath = "//div[contains(@class, 'card-payment-systems')]//img")
    private List<WebElement> paymentSystemIcons;

    // Кнопка закрытия модального окна
    @FindBy(xpath = "//button[contains(@class, 'close-modal')]")
    private WebElement closeModalButton;

    public PaymentModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Проверка отображения модального окна
    public boolean isPaymentModalDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentModal));
            return paymentModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Получение номера телефона из модального окна
    public String getDisplayedPhoneNumber() {
        wait.until(ExpectedConditions.visibilityOf(displayedPhoneNumber));
        return displayedPhoneNumber.getText().replaceAll("[^0-9+]", "");
    }

    // Получение суммы из модального окна
    public String getDisplayedAmount() {
        wait.until(ExpectedConditions.visibilityOf(displayedAmount));
        return displayedAmount.getText().trim();
    }

    // Получение текста на кнопке оплаты
    public String getPayButtonText() {
        wait.until(ExpectedConditions.visibilityOf(payButton));
        return payButton.getText().trim();
    }

    // Проверка, что сумма на кнопке соответствует отображаемой сумме
    public boolean isAmountOnButtonCorrect() {
        String amount = getDisplayedAmount();
        String buttonText = getPayButtonText();
        return buttonText.contains(amount);
    }

    // Получение плейсхолдеров полей карты
    public String getCardNumberPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(cardNumberInput));
        return cardNumberInput.getAttribute("placeholder");
    }

    public String getCardExpiryPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(cardExpiryInput));
        return cardExpiryInput.getAttribute("placeholder");
    }

    public String getCardCvcPlaceholder() {
        wait.until(ExpectedConditions.visibilityOf(cardCvcInput));
        return cardCvcInput.getAttribute("placeholder");
    }

    // Проверка наличия иконок платежных систем
    public int getPaymentSystemIconsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemIcons));
        return paymentSystemIcons.size();
    }

    public boolean arePaymentSystemIconsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemIcons));
        return paymentSystemIcons.stream().allMatch(WebElement::isDisplayed);
    }

    // Закрытие модального окна
    public void closeModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeModalButton));
            closeModalButton.click();
            wait.until(ExpectedConditions.invisibilityOf(paymentModal));
        } catch (Exception e) {
            // Если нет кнопки закрытия, можно нажать ESC или кликнуть вне модального окна
            paymentModal.sendKeys(org.openqa.selenium.Keys.ESCAPE);
        }
    }

    // Заполнение полей карты (если нужно)
    public void enterCardDetails(String cardNumber, String expiryDate, String cvc) {
        cardNumberInput.sendKeys(cardNumber);
        cardExpiryInput.sendKeys(expiryDate);
        cardCvcInput.sendKeys(cvc);
    }

    // Проверка, что все поля карты пустые
    public boolean areCardFieldsEmpty() {
        return cardNumberInput.getAttribute("value").isEmpty() &&
                cardExpiryInput.getAttribute("value").isEmpty() &&
                cardCvcInput.getAttribute("value").isEmpty();
    }

    // Ожидание загрузки модального окна
    public void waitForModalToLoad() {
        wait.until(ExpectedConditions.visibilityOf(paymentModal));
        wait.until(ExpectedConditions.visibilityOf(displayedPhoneNumber));
        wait.until(ExpectedConditions.visibilityOf(displayedAmount));
        wait.until(ExpectedConditions.visibilityOf(cardNumberInput));
    }
}