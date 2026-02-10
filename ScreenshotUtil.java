package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "screenshots/" + testName + "_" + timestamp + ".png";

            File destination = new File(fileName);
            FileUtils.copyFile(source, destination);

            System.out.println("Скриншот сохранен: " + destination.getAbsolutePath());
            return destination.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Ошибка при создании скриншота: " + e.getMessage());
            return null;
        }
    }

    public static String takeScreenshotOnFailure(WebDriver driver, String testName, Throwable throwable) {
        String screenshotPath = takeScreenshot(driver, "FAILED_" + testName);

        System.err.println("Тест " + testName + " упал с ошибкой: " + throwable.getMessage());
        if (screenshotPath != null) {
            System.err.println("Скриншот ошибки: " + screenshotPath);
        }

        return screenshotPath;
    }
}