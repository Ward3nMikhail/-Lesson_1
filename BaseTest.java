package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

@ExtendWith(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Создаем папку для скриншотов
        new File("screenshots").mkdirs();

        // Автоматическая загрузка драйвера
        WebDriverManager.chromedriver().setup();

        // Настройка опций Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        // Для headless режима (если нужно)
        // options.addArguments("--headless");

        // Инициализация драйвера
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Открытие сайта mts.by
        driver.get("https://www.mts.by");

        // Ожидание загрузки страницы
        try {
            Thread.sleep(3000); // Даем время для загрузки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Принятие cookies, если есть
        acceptCookiesIfPresent();
    }

    private void acceptCookiesIfPresent() {
        try {
            // Попытка найти и закрыть cookies окно
            driver.findElement(org.openqa.selenium.By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]")).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            // Если cookies окна нет - продолжаем
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}