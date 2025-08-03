package listener;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.ScreenShotUtility;

public class TestListeners implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListeners.class);

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
        try {
            // Get the test class instance and its driver field
            Object testInstance = result.getInstance();
            WebDriver driver = null;
            try {
                java.lang.reflect.Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
                driverField.setAccessible(true);
                driver = (WebDriver) driverField.get(testInstance);
            } catch (Exception e) {
                logger.error("Could not access WebDriver from test instance", e);
            }
            if (driver != null) {
                ScreenShotUtility screenShotUtility = new ScreenShotUtility(driver);
                String screenshotPath = "src/test/resources/screenshots/" + result.getName() + ".png";
                // Save screenshot to file
                screenShotUtility.saveScreenshotToFile(screenshotPath);
                // Attach screenshot to Allure
                Allure.addAttachment("Screenshot on Failure", screenShotUtility.captureScreenShot());
            } else {
                logger.error("WebDriver instance is null. Screenshot not taken.");
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for failed test: {}", result.getName(), e);
        }
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
