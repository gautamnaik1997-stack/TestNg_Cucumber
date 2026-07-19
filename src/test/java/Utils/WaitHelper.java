package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {
    public static WebDriverWait wait;
    public static WebElement explicitWait(WebDriver driver, By element, long seconds) {
        wait =new WebDriverWait(driver, Duration.ofSeconds(seconds));
        WebElement mywait = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return mywait;
    }
}
