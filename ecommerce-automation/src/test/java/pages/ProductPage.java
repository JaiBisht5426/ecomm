package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage
{

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBox =
            By.cssSelector(
                    "input[placeholder='Search products...']"
            );

    private By minPrice = By.xpath("(//input[@type='number'])[1]");

    private By maxPrice = By.xpath("(//input[@type='number'])[2]");
    private By productCards =
            By.cssSelector(".card");

    private By addToCartButton =
            By.xpath("(//button[contains(text(),'Add to Cart')])[1]");

    public void searchProduct(String product) {

        driver.findElement(searchBox)
                .clear();

        driver.findElement(searchBox)
                .sendKeys(product);
    }

    public void minmaxPrice(String min, String max)
    {
        driver.findElement(minPrice).sendKeys(min);
        driver.findElement(maxPrice).sendKeys(max);
    }
    public int getProductCount() {

        return driver.findElements(productCards)
                .size();
    }

    public void clickAddToCart()
    {
        driver.findElement(addToCartButton)
                .click();
    }
}
