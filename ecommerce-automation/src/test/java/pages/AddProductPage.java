package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddProductPage {

    WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By productName =
            By.name("name");

    private By description =
            By.xpath("//textarea[@name='description']");

    private By price =
            By.name("price");

    private By category =
            By.name("category");

    private By quantity =
            By.name("quantity");

    private By imageUrl =
            By.name("imageUrl");

    private By addButton =
            By.xpath("//button[contains(text(),'Add Product')]");

    private By editButton =
            By.xpath("(//button[contains(text(),'Edit ✏️')])[1]");

    private By updateButton =
            By.xpath("//button[contains(text(),'Update Product')]");

    private By deleteButton =
            By.xpath("(//button[contains(text(),'Delete ❌')])[4]");

    public void enterProductName(String value) {
        driver.findElement(productName).sendKeys(value);
    }

    public void enterDescription(String value) {
        driver.findElement(description).sendKeys(value);
    }

    public void enterPrice(String value) {
        driver.findElement(price).sendKeys(value);
    }

    public void selectCategory(String value) {
        driver.findElement(category).sendKeys(value);
    }

    public void enterQuantity(String value) {
        driver.findElement(quantity).sendKeys(value);
    }

    public void enterImage(String value) {
        driver.findElement(imageUrl).sendKeys(value);
    }

    public void clickAddProduct() {
        driver.findElement(addButton).click();
    }

    public void clickDelete()
    {
//        driver.findElement(deleteButton).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> buttons =
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".delete-btn"), 0
                ));

        buttons.get(4).click();
    }
    public void addProduct(
            String name,
            String desc,
            String pr,
            String cat,
            String qty,
            String img
    ) throws InterruptedException {

        enterProductName(name);
        enterDescription(desc);
        enterPrice(pr);
        selectCategory(cat);
        enterQuantity(qty);
        enterImage(img);

        Thread.sleep(1500);
        clickAddProduct();
    }

    public void clickEdit()
    {
//        driver.findElement(editButton).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> buttons =
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".edit-btn"), 0
                ));

        buttons.get(0).click();

//        WebElement editButton =
//                driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
//
//        JavascriptExecutor js =
//                (JavascriptExecutor) driver;
//
//        js.executeScript("arguments[0].click();", editButton);
    }

    public void updatePrice(String value)
    {
        WebElement p = driver.findElement(price);

        p.clear();

        p.sendKeys(value);
    }

    public void clickUpdate()
    {
        driver.findElement(updateButton).click();
    }

}
