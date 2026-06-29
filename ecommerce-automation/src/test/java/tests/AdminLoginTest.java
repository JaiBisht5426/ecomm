package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class AdminLoginTest extends BaseTest
{
    @Test(priority = 1)
    public void validLoginTest() throws InterruptedException
    {
        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "Admin@123"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("products"),
                "Admin is not redirected to Products Page"
        );

        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void emptyFieldsValidationTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickLogin();

        WebElement email =
                driver.findElement(By.name("email"));

        String validationMessage =
                email.getAttribute("validationMessage");

        Assert.assertFalse(
                validationMessage.isEmpty()
        );

        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void enterInvalidCredentialTest() throws InterruptedException
    {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "Admin@1234"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("products"),
                "Admin is not redirected to Products Page"
        );

        Alert alert =
                driver.switchTo().alert();

        String alertText =
                alert.getText();

        Assert.assertEquals(
                alertText,
                "Invalid Credentials ❌"
        );

        alert.accept();
    }
}
