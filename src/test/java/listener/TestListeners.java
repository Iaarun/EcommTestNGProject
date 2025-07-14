package listener;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.ScreenShotUtility;

import java.io.ByteArrayInputStream;


public class TestListeners implements ITestListener {
    private static final Logger logger= LogManager.getLogger(TestListeners.class);
  private WebDriver driver;
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}", result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName(), result.getThrowable());
        if(driver != null) {
            // Assuming you have a utility to capture screenshots
            Allure.addAttachment("Screenshot on Failure",
                    ScreenShotUtility.captureScreenShot(driver));
        }
        // You can add code here to take a screenshot or log additional information
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());

    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: {}", context.getName());

    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished: {}", context.getName());
    }
}
