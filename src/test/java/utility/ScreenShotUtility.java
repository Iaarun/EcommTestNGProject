package utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenShotUtility {

    public static ByteArrayInputStream captureScreenShot(WebDriver driver){
      return    new ByteArrayInputStream( ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
    }
}
