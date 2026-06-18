package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

import java.util.List;

public class AddToCartTest extends BaseTest
{
    @Test
    public void addToCartTest()
            throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Thread.sleep(3000);

        productPage.clickAddToCart();

        Thread.sleep(2000);

        Alert alert =
                driver.switchTo().alert();

        String alertText =
                alert.getText();

        Assert.assertEquals(
                alertText,
                "Added to cart ✅"
        );

        alert.accept();

        Thread.sleep(3000);

        driver.findElement(
                By.xpath("//button[contains(text(),'Cart')]")
        ).click();

        List<WebElement> cartProducts =
                driver.findElements(
                        By.cssSelector(".cart-items")
                );


        Assert.assertTrue(
                cartProducts.size() > 0,
                "Product not added to cart"
        );

        Thread.sleep(3000);

        driver.findElement(
                By.xpath("//button[contains(text(),'➕')]")
        ).click();
    }

    @Test
    public void validateCartItemsTest() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Thread.sleep(3000);

        productPage.clickAddToCart();

        Thread.sleep(2000);

        Alert alert =
                driver.switchTo().alert();

        String alertText =
                alert.getText();

        Assert.assertEquals(
                alertText,
                "Added to cart ✅"
        );

        alert.accept();

        Thread.sleep(3000);

        driver.findElement(
                By.xpath("//button[contains(text(),'Cart')]")
        ).click();

        Thread.sleep(3000);

        List<WebElement> cartItems =
                driver.findElements(
                        By.cssSelector(".cart-card")
                );

        System.out.println(
                "Total Cart Items: "
                        + cartItems.size()
        );

        Assert.assertTrue(
                cartItems.size() > 0,
                "Cart is empty"
        );

        Thread.sleep(2000);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollBy(0,500)"
        );

        Thread.sleep(2000);

        driver.findElement(
                By.xpath("(//button[contains(text(),'➕')])[2]")
        ).click();
    }
}
