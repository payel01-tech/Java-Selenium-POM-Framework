package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

public class LoginPage {

    private WebDriver driver;

    private By usernameTextBox = By.id("Email");
    private By passwordTextBox = By.id("Password");
    private By checkBoxRememberMe =  By.xpath("//input[@type=\"checkbox\" and @id=\"RememberMe\"]");
    private By loginButton = By.xpath("//button[@type=\"submit\" and contains(text(), \"Log in\")]");

    public LoginPage()
    {
        this.driver= DriverFactory.getDriver();
    }

    public void enterUserName(String userName)
    {
        driver.findElement(usernameTextBox).clear();
        driver.findElement(usernameTextBox).sendKeys(userName);
    }

    public void enterPassword(String passWord)
    {
        driver.findElement(passwordTextBox).clear();
        driver.findElement(passwordTextBox).sendKeys(passWord);
    }

    public void setCheckBoxRememberMe()
    {
        driver.findElement(checkBoxRememberMe).click();
    }

    public void clickLogin()
    {
        driver.findElement(loginButton).click();
    }
}
