package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

import java.time.Duration;

public class LogoutTest extends BaseTest
{
    @Test
    public void validateTokenRemovedAfterLogout() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        HomePage homePage = new HomePage(driver);

        Thread.sleep(5000);
        homePage.clickLogout();

        Alert alert =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.alertIsPresent());

        System.out.println(alert.getText());

        alert.accept();

        String token = (String)
                ((JavascriptExecutor) driver)
                        .executeScript(
                                "return localStorage.getItem('token');"
                        );

        Assert.assertNull(
                token,
                "Token still exists after logout"
        );

        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void validateTokenRemovedAfterLogoutForAdmin() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "Admin@123"
        );

        HomePage homePage = new HomePage(driver);

        Thread.sleep(5000);
        homePage.clickLogout();

        Alert alert =
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.alertIsPresent());

        System.out.println(alert.getText());

        alert.accept();

        String token = (String)
                ((JavascriptExecutor) driver)
                        .executeScript(
                                "return localStorage.getItem('token');"
                        );

        Assert.assertNull(
                token,
                "Token still exists after logout"
        );
    }
}
