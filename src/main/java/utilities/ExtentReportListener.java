package utilities;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class ExtentReportListener implements ITestListener {

    private ExtentReports extentReports;
    private ExtentTest test;
    private ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context)
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        extentTestThreadLocal.get().pass("Test Passed");

        // 2) Try to capture screenshot (file-based)
        try{

            // 1. Get the test instance (object of the test class that failed)
            Object testInstance = result.getInstance();

            // 2. Cast it to BaseTest (so we can call getDriver())
            WebDriver driver = ((DriverFactory) testInstance).getDriver();

            if(driver != null) {
                // 3. Take screenshot with driver
                String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    extentTestThreadLocal.get().pass("Screenshot on Passed",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
            }
            else{
                extentTestThreadLocal.get().warning("Webdriver was null, screenshot not captured");
            }
        } catch (Exception e) {
            // Don’t swallow the original failure — just log that screenshot attachment failed.
            extentTestThreadLocal.get().warning("Failed to capture/attach screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        // 1) Log the original throwable (so stacktrace is visible)
        extentTestThreadLocal.get().fail(result.getThrowable());

        // 2) Try to capture screenshot (file-based)
        try{

            // 1. Get the test instance (object of the test class that failed)
            Object testInstance = result.getInstance();

            // 2. Cast it to BaseTest (so we can call getDriver())
            WebDriver driver = ((BaseTest) testInstance).getDriver();

            if(driver != null) {
                // 3. Take screenshot with driver
                String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    extentTestThreadLocal.get().fail("Screenshot on Failure",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }
            }
            else{
                    extentTestThreadLocal.get().warning("Webdriver was null, screenshot not captured");
            }
        } catch (Exception e) {
            // Don’t swallow the original failure — just log that screenshot attachment failed.
            extentTestThreadLocal.get().warning("Failed to capture/attach screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        extentTestThreadLocal.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context)
    {
        extentReports.flush();
    }

    // Utility for screenshot
    public String captureScreenshot(WebDriver driver,  String screenshotName)
    {
        try{
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "test-output/screenshots/" + screenshotName + ".png";
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
