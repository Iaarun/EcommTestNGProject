package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MobilePage {

    protected WebDriver driver;

    protected By sortByDropdown = By.xpath("(//select[@title='Sort By'])");
    private By productNames = By.cssSelector(".product-name a");

    public MobilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMobilePageTitle() {
        return driver.getTitle();
    }

    public void selectSortBy(String option){
      WebElement dropDown= driver.findElement(sortByDropdown);
        Select select = new Select(dropDown);
        select.selectByVisibleText(option);
    }

    public List<String> getProductNames(){
    List<WebElement> product= driver.findElements(productNames);
   List<String> productNamesList = new ArrayList<>();

     for(WebElement ele: product){
         productNamesList.add(ele.getText());
     }

     return productNamesList;
    }
}
