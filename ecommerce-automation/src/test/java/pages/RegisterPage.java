package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage
{
    WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
    }

//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    private By name = By.name("name");
    private By email = By.name("email");
    private By password = By.name("password");
    private By phone = By.name("phone");

    private By registerButton = By.xpath("//button[contains(text(),'Register')]");


    public void enterName(String value)
    {
        driver.findElement(name).sendKeys(value);
    }

    public void enterEmail(String value)
    {
        driver.findElement(email).sendKeys(value);
    }

    public void enterPassword(String value)
    {
        driver.findElement(password).sendKeys(value);
    }

    public void enterPhone(String value)
    {
        driver.findElement(phone).sendKeys(value);
    }

    public void clickRegisterbutton() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(registerButton).click();
    }

    public void registerUser(String n, String e, String p, String ph) throws InterruptedException {
        enterName(n);
        enterEmail(e);
        enterPassword(p);
        enterPhone(ph);
        clickRegisterbutton();
    }
}
