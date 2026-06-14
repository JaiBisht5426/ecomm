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

    @Test(priority = 4)
    public void validateProductDetailsTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".card")
                )
        );

        Thread.sleep(3000);

        driver.findElements(By.cssSelector(".card"))
                .get(0)
                .click();

        Thread.sleep(3000);

        Assert.assertTrue(
                driver.findElement(By.tagName("h1"))
                        .isDisplayed(),
                "Product Name is not displayed"
        );

        Assert.assertTrue(
                driver.getPageSource().contains("₹"),
                "Product Price is missing"
        );
    }

    @Test(priority = 5)
    public void verifyProductNavigationTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".card")
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.tagName("img")
                )
        );

        List<WebElement> images =
                driver.findElements(By.tagName("img"));

        System.out.println("Images Found: " + images.size());

        for(WebElement img : images)
        {
            System.out.println(
                    img.getAttribute("src")
            );
        }

        Thread.sleep(3000);

        driver.findElements(By.cssSelector(".card"))
                .get(0)
                .click();

        Thread.sleep(3000);

        wait.until(
                ExpectedConditions.urlContains("/viewproducts/3")
        );

        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/viewproducts/3"),
                "User not redirected to Product Details Page"
        );
    }
}
