package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage
{
    WebDriver driver;
    WebDriverWait wait;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }



    private By name = By.name("name");
    private By email = By.name("email");
    private By password = By.name("password");
    private By phone = By.name("phone");

//    private By registerButton = By.xpath("//button[contains(text(),'Register')]");

    @FindBy(xpath = "//button[contains(text(),'Register')]")
    WebElement registerButton;

    public void enterName(String value)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(name))
                .sendKeys(value);
    }

    public void enterEmail(String value) throws InterruptedException {
        Thread.sleep(10000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(value);
    }

    public void enterPassword(String value)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password))
                .sendKeys(value);
    }

    public void enterPhone(String value)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phone))
                .sendKeys(value);
    }

    public void clickRegisterbutton() throws InterruptedException {
//        Thread.sleep(10000);
//        driver.findElement(registerButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public void registerUser(String n, String e, String p, String ph) throws InterruptedException {
        enterName(n);
        enterEmail(e);
        enterPassword(p);
        enterPhone(ph);
        clickRegisterbutton();

        Thread.sleep(3000);
        wait.until(ExpectedConditions.alertIsPresent());

        String alertText = driver.switchTo().alert().getText();

        System.out.println(alertText);

        driver.switchTo().alert().accept();
    }
}
