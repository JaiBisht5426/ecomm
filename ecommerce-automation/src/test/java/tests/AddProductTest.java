package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProductPage;
import pages.LoginPage;
import utils.BaseTest;

public class AddProductTest extends BaseTest
{
    @Test(priority = 1)
    public void addProductAlreadyExistTest() throws InterruptedException {

        LoginPage admin =
                new LoginPage(driver);

        AddProductPage product =
                new AddProductPage(driver);

        admin.login(
                "admin@gmail.com",
                "Admin@123"
        );

        Thread.sleep(3000);

        product.addProduct(

                "iPhone 16",

                "Apple Smartphone",

                "85000",

                "Electronics",

                "20",

                "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9"
        );

        Alert alert =
                driver.switchTo().alert();

        String alertText =
                alert.getText();

        Assert.assertEquals(

                alertText,

                "Product Added Successfully ✅"

        );

        Thread.sleep(3000);

        System.out.println(alert.getText());
        alert.accept();

        Thread.sleep(4000);

    }

    @Test(priority = 2)
    public void emptyProductNameTest() throws InterruptedException {

        LoginPage admin =
                new LoginPage(driver);

        AddProductPage product =
                new AddProductPage(driver);

        admin.login(
                "admin@gmail.com",
                "Admin@123"
        );

        product.addProduct(

                "",

                "Apple",

                "90000",

                "Electronics",

                "5",

                "image.jpg"
        );

        String validation =

                driver.findElement(
                                By.name("name")
                        )

                        .getAttribute("validationMessage");

        Assert.assertFalse(

                validation.isEmpty()

        );

        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void emptyNegativeQuantityTest() throws InterruptedException {

        LoginPage admin =
                new LoginPage(driver);

        AddProductPage product =
                new AddProductPage(driver);

        admin.login(
                "admin@gmail.com",
                "Admin@123"
        );

        product.addProduct(

                "Apple Phone",

                "Apple",

                "90000",

                "Electronics",

                "-5",

                "image.jpg"
        );


        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void updateProductTest() throws InterruptedException {
        LoginPage login =
                new LoginPage(driver);

        AddProductPage page =
                new AddProductPage(driver);

        login.login(
                "admin@gmail.com",
                "Admin@123"
        );

        page.clickEdit();

        Thread.sleep(2000);

        page.updatePrice("80000");

        Thread.sleep(2000);

        page.clickUpdate();

        Alert alert =
                driver.switchTo().alert();

        Assert.assertEquals(
                alert.getText(),
                "Product Updated Successfully ✅"
        );

        alert.accept();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void deleteProductTest() throws InterruptedException {
        LoginPage login =
                new LoginPage(driver);

        AddProductPage page =
                new AddProductPage(driver);

        login.login(
                "admin@gmail.com",
                "Admin@123"
        );

        page.clickDelete();

        Thread.sleep(2000);

        Alert alert =
                driver.switchTo().alert();

        Assert.assertEquals(
                alert.getText(),
                "Are you sure you want to delete?"
        );
        Thread.sleep(1500);
        alert.accept();

        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void addProductTest() throws InterruptedException {

        LoginPage admin =
                new LoginPage(driver);

        AddProductPage product =
                new AddProductPage(driver);

        admin.login(
                "admin@gmail.com",
                "Admin@123"
        );

        Thread.sleep(3000);

        product.addProduct(

                "iPhone 16",

                "Apple Smartphone",

                "85000",

                "Electronics",

                "20",

                "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9"
        );

        Alert alert =
                driver.switchTo().alert();

        String alertText =
                alert.getText();

        Assert.assertEquals(

                alertText,

                "Product Added Successfully ✅"

        );

//        Thread.sleep(3000);

        System.out.println(alert.getText());
        alert.accept();

        Thread.sleep(4000);

    }

}
