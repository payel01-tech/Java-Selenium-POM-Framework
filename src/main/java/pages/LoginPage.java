package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;
import utilities.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private By usernameTextBox = By.id("Email");
    private By passwordTextBox = By.id("Password");
    private By checkBoxRememberMe =  By.xpath("//input[@type=\"checkbox\" and @id=\"RememberMe\"]");
    private By loginButton = By.xpath("//button[@type=\"submit\" and contains(text(), \"Log in\")]");

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


