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

public class CheckoutTest extends BaseTest
{
//    @Test(priority = 0)
//    public void checkoutInitiationTest()
//            throws InterruptedException
//    {
//
//        LoginPage loginPage =
//                new LoginPage(driver);
//
//        ProductPage productPage =
//                new ProductPage(driver);
//
//        loginPage.login(
//                "raghushyam@gmail.com",
//                "RaghuGovind@1234"
//        );
//
//        Thread.sleep(3000);
//
//        productPage.clickAddToCart();
//
//        Thread.sleep(2000);
//
//        Alert alert =
//                driver.switchTo().alert();
//
//        String alertText =
//                alert.getText();
//
//        Assert.assertEquals(
//                alertText,
//                "Added to cart ✅"
//        );
//
//        alert.accept();
//
//        Thread.sleep(3000);
//
//        driver.findElement(
//                By.xpath("//button[contains(text(),'Cart')]")
//        ).click();
//
//        Thread.sleep(3000);
//
//        List<WebElement> cartItems =
//                driver.findElements(
//                        By.cssSelector(".cart-card")
//                );
//
//        System.out.println(
//                "Total Cart Items: "
//                        + cartItems.size()
//        );
//
//        Assert.assertTrue(
//                cartItems.size() > 0,
//                "Cart is empty"
//        );
//
//        Thread.sleep(2000);
//
//        JavascriptExecutor js =
//                (JavascriptExecutor) driver;
//
//        js.executeScript(
//                "window.scrollBy(0,500)"
//        );
//
//        Thread.sleep(2000);
//
//        driver.findElement(
//                By.xpath("//button[contains(text(),'Place Order')]")
//        ).click();
//
//        Thread.sleep(2000);
//
//        alert.accept();
//    }
//
//    @Test(priority = 1)
//    public void validCheckoutTest() throws InterruptedException {
//        LoginPage loginPage =
//                new LoginPage(driver);
//
//        ProductPage productPage =
//                new ProductPage(driver);
//
//        loginPage.login(
//                "raghushyam@gmail.com",
//                "RaghuGovind@1234"
//        );
//
//        Thread.sleep(3000);
//
//        productPage.clickAddToCart();
//
//        Thread.sleep(2000);
//
//        Alert alert =
//                driver.switchTo().alert();
//
//        String alertText =
//                alert.getText();
//
//        Assert.assertEquals(
//                alertText,
//                "Added to cart ✅"
//        );
//
//        alert.accept();
//
//        Thread.sleep(3000);
//
//        driver.findElement(
//                By.xpath("//button[contains(text(),'Cart')]")
//        ).click();
//
//        Thread.sleep(3000);
//
//        List<WebElement> cartItems =
//                driver.findElements(
//                        By.cssSelector(".cart-card")
//                );
//
//        System.out.println(
//                "Total Cart Items: "
//                        + cartItems.size()
//        );
//
//        Assert.assertTrue(
//                cartItems.size() > 0,
//                "Cart is empty"
//        );
//
//        Thread.sleep(2000);
//
//        driver.findElement(
//                By.name("fullName")
//        ).sendKeys("Jaiprakash Bisht");
//
//        driver.findElement(
//                By.name("phone")
//        ).sendKeys("9876543210");
//
//        driver.findElement(
//                By.name("city")
//        ).sendKeys("Lucknow");
//
//        driver.findElement(
//                By.name("state")
//        ).sendKeys("UP");
//
//        driver.findElement(
//                By.name("pincode")
//        ).sendKeys("226001");
//
//        driver.findElement(
//                By.name("addressLine")
//        ).sendKeys("Hazratganj");
//
//        Thread.sleep(2000);
//        driver.findElement(
//                By.xpath("//button[contains(text(),'Place Order')]")
//        ).click();
//
//        Thread.sleep(3000);
//        alert.accept();
//    }

    @Test(priority = 2)
    public void validCheckoutTestInvalidPincode() throws InterruptedException {
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
        ).sendKeys("123");

        driver.findElement(
                By.name("addressLine")
        ).sendKeys("Hazratganj");

        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//button[contains(text(),'Place Order')]")
        ).click();

        Thread.sleep(3000);

        Assert.assertEquals(
                alert.getText(),
                "Pincode must be exactly 6 digits ❌"
        );

        alert.accept();
    }

    @Test(priority = 3)
    public void validCheckoutTestInvalidPhoneNumber() throws InterruptedException {
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

        driver.findElement(
                By.name("fullName")
        ).sendKeys("Jaiprakash Bisht");

        driver.findElement(
                By.name("phone")
        ).sendKeys("98765432");

        driver.findElement(
                By.name("city")
        ).sendKeys("Lucknow");

        driver.findElement(
                By.name("state")
        ).sendKeys("UP");

        driver.findElement(
                By.name("pincode")
        ).sendKeys("110092");

        driver.findElement(
                By.name("addressLine")
        ).sendKeys("Hazratganj");

        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//button[contains(text(),'Place Order')]")
        ).click();

        Thread.sleep(3000);

        Assert.assertEquals(
                alert.getText(),
                "Phone Number must be exactly 10 digits ❌"
        );

        alert.accept();
    }

    @Test(priority = 4)
    public void validCheckoutTestInvalidPincodeAndPassword() throws InterruptedException {
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

        driver.findElement(
                By.name("fullName")
        ).sendKeys("Jaiprakash Bisht");

        driver.findElement(
                By.name("phone")
        ).sendKeys("98765432");

        driver.findElement(
                By.name("city")
        ).sendKeys("Lucknow");

        driver.findElement(
                By.name("state")
        ).sendKeys("UP");

        driver.findElement(
                By.name("pincode")
        ).sendKeys("123");

        driver.findElement(
                By.name("addressLine")
        ).sendKeys("Hazratganj");

        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//button[contains(text(),'Place Order')]")
        ).click();

        Thread.sleep(3000);

        Assert.assertEquals(
                alert.getText(),
                "Pincode must be exactly 6 digits and Phone Number must be exactly 10 digits ❌"
        );

        alert.accept();
    }

}
