package utility;

import base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
//updating  code to captyre failed screenshot
public class ScreenShotUtility  {
   private final WebDriver driver;
    public ScreenShotUtility(WebDriver driver) {
        this.driver = driver;
    }

    public ByteArrayInputStream captureScreenShot() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        if (!(driver instanceof TakesScreenshot)) {
            throw new UnsupportedOperationException("Driver does not support screenshots.");
        }
        return new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }

    public void saveScreenshotToFile(String filePath) {
        File destFile = new File(filePath);
        File parentDir = destFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs(); // Create directories if they don't exist
        }
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + e.getMessage(), e);
        }
    }
}
