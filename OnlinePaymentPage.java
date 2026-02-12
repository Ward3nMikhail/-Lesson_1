package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OnlinePaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//h1[contains(text(), 'Пополнение счета')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public boolean isPhoneInputDisplayed() {
        return phoneInput.isDisplayed();
    }
}