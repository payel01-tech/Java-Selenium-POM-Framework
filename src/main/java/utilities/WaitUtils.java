package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeout)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForVisibility(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForText(WebElement element, String text){
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

}
