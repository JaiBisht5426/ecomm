package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.BaseTest;

import java.time.Duration;

public class RegisterTest extends BaseTest
{
    @Test
    public void validRegisterTest() throws InterruptedException
    {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.registerUser("Nidhi Shikawat", "nidhi1234gmail.com","Nidhi@1234","9090909090");

    }
}
