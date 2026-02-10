package listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import utils.ScreenShotUtil;
import java.lang.reflect.Field;
import java.util.Optional;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Тест " + context.getDisplayName() + " провален!");

        try {
            // Получаем экземпляр тестового класса
            Object testInstance = context.getRequiredTestInstance();

            // Пытаемся получить драйвер из тестового класса
            Field driverField = findDriverField(testInstance.getClass());
            if (driverField != null) {
                driverField.setAccessible(true);
                WebDriver driver = (WebDriver) driverField.get(testInstance);

                if (driver != null) {
                    // Делаем скриншот
                    ScreenShotUtil.takeScreenshotOnFailure(driver, context.getDisplayName(), cause);
                }
            }
        } catch (Exception e) {
            System.err.println("Не удалось сделать скриншот: " + e.getMessage());
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Тест " + context.getDisplayName() + " пройден успешно!");
    }

    private Field findDriverField(Class<?> clazz) {
        try {
            return clazz.getDeclaredField("driver");
        } catch (NoSuchFieldException e) {
            // Проверяем родительский класс
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null && !superClass.equals(Object.class)) {
                return findDriverField(superClass);
            }
            return null;
        }
    }
}