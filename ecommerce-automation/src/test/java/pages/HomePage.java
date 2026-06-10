package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage
{
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By logoutButton =
            By.xpath("//button[contains(text(),'Logout')]");

    public void clickLogout() throws InterruptedException {
        driver.findElement(logoutButton).click();

        Thread.sleep(3000);
    }

}
