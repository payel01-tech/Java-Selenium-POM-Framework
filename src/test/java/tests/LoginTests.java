package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void testValidLogin()
    {
        LoginPage obj = new LoginPage(driver);
        obj.enterPassword("admin@yourstore.com");
        obj.enterPassword("admin");
        obj.setCheckBoxRememberMe();
        obj.clickLogin();
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Dashboard / nopCommerce administration");
        System.out.println("Test Run Successfully");
    }
}
