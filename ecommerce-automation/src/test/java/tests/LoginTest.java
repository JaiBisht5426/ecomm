package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest
{
    @Test
    public void validLoginTest() throws InterruptedException
    {
        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovin@1234"
        );

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("viewproducts"),
                "User is not redirected to Products Page"
        );

//        Thread.sleep(10000);
    }

//    @Test
//    public void emptyFieldsValidationTest() throws InterruptedException {
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        loginPage.clickLogin();
//
//        WebElement email =
//                driver.findElement(By.name("email"));
//
//        String validationMessage =
//                email.getAttribute("validationMessage");
//
//        Assert.assertFalse(
//                validationMessage.isEmpty()
//        );
//    }
}
