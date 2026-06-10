package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

import java.time.Duration;
import java.util.List;

public class ProductPageTest extends BaseTest
{
    @Test
    public void productListingPageTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("viewproducts"),
                "Products page not loaded"
        );

        Thread.sleep(4000);
    }

    @Test(priority = 2)
    public void productListingPageTestNegative() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushya@gmail.com",
                "RaghuGovind@1234"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("viewproducts"),
                "Products page not loaded"
        );

        Thread.sleep(4000);
    }

    @Test(priority = 3)
    public void validateProductVisibilityTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".card"),
                        0
                )
        );

        List<WebElement> products =
                driver.findElements(
                        By.cssSelector(".card")
                );

        System.out.println(
                "Total Products: " + products.size()
        );

        Assert.assertTrue(
                products.size() > 0
        );
    }
}
