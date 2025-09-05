package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin()
    {
        LoginPage obj = new LoginPage();
        String userName = ConfigReader.getProperty("userName");
        String passWord = ConfigReader.getProperty("passWord");
        obj.enterUserName(userName);
        obj.enterPassword(passWord);
        obj.setCheckBoxRememberMe();
        obj.clickLogin();
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Dashboard / nopCommerce administration");
        System.out.println("Test Run Successfully");
    }
}
