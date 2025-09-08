package project;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;
import com.aventstack.extentreports.*;


import java.lang.reflect.Method;

public class Based_impact_TC02 {
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
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
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

