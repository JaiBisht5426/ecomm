package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By registerLink = By.xpath("//span[contains(text(),'Register')]");

    public void clickRegister()
    {
        driver.findElement(registerLink).click();
    }
}
