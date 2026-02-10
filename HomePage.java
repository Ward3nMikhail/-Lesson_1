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

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы блока "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//section[contains(@class, 'online-payment')]")
    private WebElement onlinePaymentBlock;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//h2")
    private WebElement blockTitle;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement moreDetailsLink;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//img[contains(@alt, 'платеж') or contains(@alt, 'Visa') or contains(@alt, 'MasterCard')]")
    private List<WebElement> paymentSystemLogos;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//input[@type='tel']")
    private WebElement phoneInput;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//input[@name='connectionType'][@value='service']")
    private WebElement serviceTypeRadio;

    @FindBy(xpath = "//section[contains(@class, 'online-payment')]//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Методы для работы с блоком
    public boolean isOnlinePaymentBlockDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(onlinePaymentBlock));
        return onlinePaymentBlock.isDisplayed();
    }

    public String getBlockTitle() {
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        return blockTitle.getText();
    }

    public boolean isMoreDetailsLinkDisplayed() {
        return moreDetailsLink.isDisplayed();
    }

    public void clickMoreDetailsLink() {
        moreDetailsLink.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public int getPaymentSystemLogosCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemLogos));
        return paymentSystemLogos.size();
    }

    public boolean arePaymentSystemLogosDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(paymentSystemLogos));
        return paymentSystemLogos.stream().allMatch(WebElement::isDisplayed);
    }

    // Методы для заполнения формы
    public void selectServiceType() {
        serviceTypeRadio.click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    // Метод для проверки активности кнопки
    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    // Метод для проверки, что поле ввода номера активно
    public boolean isPhoneInputEnabled() {
        return phoneInput.isEnabled();
    }

    // Проверка всех элементов блока
    public void waitForBlockToLoad() {
        wait.until(ExpectedConditions.visibilityOf(onlinePaymentBlock));
        wait.until(ExpectedConditions.visibilityOf(blockTitle));
        wait.until(ExpectedConditions.visibilityOf(moreDetailsLink));
        wait.until(ExpectedConditions.visibilityOf(phoneInput));
    }
}