package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

import java.time.Duration;
import java.util.List;

public class OrderSummaryTest extends BaseTest
{
    @Test
    public void validateOrderSummaryTest() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "monisharawat@gmail.com",
                "MonishaRawat@1234"
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

        driver.findElement(
                By.name("fullName")
        ).sendKeys("Jaiprakash Bisht");

        driver.findElement(
                By.name("phone")
        ).sendKeys("9876543210");

        driver.findElement(
                By.name("city")
        ).sendKeys("Lucknow");

        driver.findElement(
                By.name("state")
        ).sendKeys("UP");

        driver.findElement(
                By.name("pincode")
        ).sendKeys("226001");

        driver.findElement(
                By.name("addressLine")
        ).sendKeys("Hazratganj");

        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//button[contains(text(),'Place Order')]")
        ).click();

        Thread.sleep(3000);
        alert.accept();

        Thread.sleep(2000);

        driver.get("http://localhost:3000/viewproducts");

        Thread.sleep(2000);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(10));

        WebElement buttons = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[contains(text(),'My Orders')]")
                )
        );

        buttons.click();

        Thread.sleep(2000);

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "window.scrollBy(0,500)"
        );

        List<WebElement> orders =
                driver.findElements(
                        By.cssSelector(".order-card")
                );

        System.out.println(
                "Total Orders: " + orders.size()
        );

        Assert.assertTrue(
                orders.size() > 0,
                "No orders found"
        );
    }
}
