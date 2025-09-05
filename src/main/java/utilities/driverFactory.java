package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

// DriverFactory → start/quit browsers

public class driverFactory {

    // ThreadLocal<WebDriver> is used to enable safe parallel test execution
    // by ensuring each test thread gets its own WebDriver instance.

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver(String browser)
    {
        if(browser.equalsIgnoreCase("Chrome"))
            driver.set(new ChromeDriver());
        else if (browser.equalsIgnoreCase("Edge"))
            driver.set(new EdgeDriver());
        else if (browser.equalsIgnoreCase("Firefox"))
            driver.set(new FirefoxDriver());
        else
            throw new IllegalArgumentException("Browser not supported: "+ browser);

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return getDriver();

    }
    public static WebDriver getDriver()
    {
        return driver.get();
    }

    public static void quitDriver()
    {
        if(driver.get() != null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}
