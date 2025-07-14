package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    protected WebDriver driver;

    protected By mobileLink= By.xpath("//a[normalize-space()='Mobile']");
    /*
    @FindBy (xpath = "//a[normalize-space()='Mobile']")
    protected WebElement mobileLink;
*/
// used when using PageFactory @FindBy annotation
    /*
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, HomePage.class);
    }
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnMobileLink(){
        WebElement mobileElement = driver.findElement(mobileLink);
        mobileElement.click();
    }

    public String getHomePageTitle(){
        return driver.getTitle();
    }
}
