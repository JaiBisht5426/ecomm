package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage
{
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver)

    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By registerLink = By.xpath("//span[contains(text(),'Register')]");
    private By email = By.name("email");
    private By password = By.name("password");
    private By loginButton = By.xpath("//button[contains(text(),'Login')]");

    public void enterEmail(String value)
    {
        driver.findElement(email).sendKeys(value);
    }

    public void enterPassword(String value)
    {
        driver.findElement(password).sendKeys(value);
    }

    public void clickLogin() throws InterruptedException {
        driver.findElement(loginButton).click();

//        Thread.sleep(3000);
//        wait.until(ExpectedConditions.alertIsPresent());
//
//        String alertText = driver.switchTo().alert().getText();
//
//        System.out.println(alertText);
//
//        driver.switchTo().alert().accept();
    }

    public void login(String e, String p) throws InterruptedException {
        enterEmail(e);
        enterPassword(p);
        clickLogin();

        wait.until(ExpectedConditions.alertIsPresent());

        String alertText = driver.switchTo().alert().getText();

        System.out.println(alertText);

        Thread.sleep(3000);

        driver.switchTo().alert().accept();
    }
    public void clickRegister()

    {
        driver.findElement(registerLink).click();
    }
}
