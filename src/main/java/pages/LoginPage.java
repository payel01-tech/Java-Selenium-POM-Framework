package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;
import utilities.LocatorReader;
import utilities.WaitUtils;

public class LoginPage {

    LocatorReader locators = new LocatorReader();
    private WebDriver driver;
    private WaitUtils waitUtils;
    private By usernameTextBox = locators.getProperties("login.userNameTextBox");
    private By passwordTextBox = locators.getProperties("login.passwordTextBox");
    private By checkBoxRememberMe =  locators.getProperties("login.rememberMeCheckBox");
    private By loginButton = locators.getProperties("login.loginButton");

    public LoginPage()
    {
        this.driver= DriverFactory.getDriver();
        waitUtils = new WaitUtils(driver,10);
    }

    public void enterUserName(String userName)
    {
        waitUtils.waitForVisibility(driver.findElement(usernameTextBox));
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(userName);
    }

    public void enterPassword(String passWord)
    {
        waitUtils.waitForVisibility(driver.findElement(passwordTextBox));
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(passWord);
    }

    public void setCheckBoxRememberMe()
    {
        waitUtils.waitForClickable(driver.findElement(checkBoxRememberMe));
        driver.findElement(checkBoxRememberMe).click();
    }

    public void clickLogin()
    {
        waitUtils.waitForClickable(driver.findElement(loginButton));
        driver.findElement(loginButton).click();
    }
}


