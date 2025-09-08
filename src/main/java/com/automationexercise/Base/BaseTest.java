package com.automationexercise.Base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.createInstance("test-output/ExtentReport.html");
    }

    @BeforeMethod
    public void setup(Method method) {
        test = extent.createTest(method.getName());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        // ✅ Add timeout configurations
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // ✅ Optional retry logic for page load
        for (int i = 0; i < 2; i++) {
            try {
                driver.get("https://automationexercise.com/");
                break;
            } catch (TimeoutException e) {
                test.warning("Page load timed out, retrying...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }

        test.info("Opened homepage");
        test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "HomePage"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtilities.capture(driver, result.getName());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(path);
        }
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}




