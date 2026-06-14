package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.BaseTest;

import java.util.List;

public class ProductPageTest2 extends BaseTest
{
    @Test
    public void searchProductTest() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Thread.sleep(4000);

        productPage.searchProduct("iphone");

        Assert.assertTrue(
                productPage.getProductCount() > 0,
                "No Products found"
        );
      Thread.sleep(4000);
    }

    @Test
    public void searchProductTestInvalidData() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );
        Thread.sleep(4000);
        productPage.searchProduct("Trouser");
        Thread.sleep(3000);
        Assert.assertTrue(
                productPage.getProductCount() > 0,
                "No Products found"
        );
          Thread.sleep(4000);
    }

    @Test
    public void filterByCategoryTest() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Thread.sleep(3000);

        Select category =
                new Select(
                        driver.findElement(By.tagName("select"))
                );

        category.selectByVisibleText("Electronics");

        Thread.sleep(3000);

        Select sort =
                new Select(
                        driver.findElement(By.xpath("(//select)[2]"))
                );

        for(WebElement option : sort.getOptions())
        {
            System.out.println(option.getText());
        }

        Thread.sleep(2000);

        sort.selectByVisibleText("Price High → Low");

        List<WebElement> products =
                driver.findElements(By.cssSelector(".card"));

        Assert.assertTrue(products.size() > 0);

        Thread.sleep(4000);
    }

    @Test
    public void filterByCategoryTestMinMaxPrice() throws InterruptedException {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductPage productPage =
                new ProductPage(driver);

        loginPage.login(
                "raghushyam@gmail.com",
                "RaghuGovind@1234"
        );

        Thread.sleep(3000);

        Select category =
                new Select(
                        driver.findElement(By.tagName("select"))
                );

        category.selectByVisibleText("Electronics");

        Thread.sleep(4000);

        productPage.minmaxPrice("2000", "10000");

        List<WebElement> products =
                driver.findElements(By.cssSelector(".card"));

        Assert.assertTrue(products.size() > 0);

        Thread.sleep(4000);
    }

}
