package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    // add the code what you want to run before running
    // a test script like start a browser

    @BeforeMethod
    public void setUp() {
        // load configReader
        ConfigReader.loadConfig();

        // get browser from the config property file
        String browser = ConfigReader.getProperty("browser");

        // initialize the browser
        this.driver = DriverFactory.initDriver(browser);

        // launch the url
        String url = ConfigReader.getProperty("baseUrl");
        DriverFactory.getDriver().get(url);

    }

    // add the code what you want to run after running
    // all test scripts like closing a browser
    @AfterMethod
    public void tearDown()
    {
        if(driver!=null)
            DriverFactory.quitDriver();
    }
}
