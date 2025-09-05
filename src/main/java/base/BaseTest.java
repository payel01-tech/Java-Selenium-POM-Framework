package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.configReader;
import utilities.driverFactory;

public class BaseTest {

    protected WebDriver driver;

    // add the code what you want to run before running
    // a test script like start a browser

    @BeforeMethod
    public void setUp() {
        // load configReader
        configReader.loadConfig();

        // get browser from the config property file
        String browser = configReader.getProperty("browser");

        // initialize the browser
        driverFactory.initDriver(browser);

        // launch the url
        String url = configReader.getProperty("baseUrl");
        driverFactory.getDriver().get(url);

    }

    // add the code what you want to run after running
    // all test scripts like closing a browser
    @AfterMethod
    public void tearDown()
    {
        if(driver!=null)
            driverFactory.quitDriver();
    }
}
