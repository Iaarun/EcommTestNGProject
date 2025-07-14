package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected WebDriver driver;
    // Locators for the login page elements
    protected By emailId= By.id("email");
    protected By passwordId = By.id("pass");
    protected By loginButtonId = By.id("send2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailId).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordId).sendKeys(password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginButtonId).click();
    }

    public HomePage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginBtn();
        return new HomePage(driver);

    }

}
