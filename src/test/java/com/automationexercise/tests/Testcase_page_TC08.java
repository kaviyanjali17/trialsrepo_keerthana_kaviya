package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Testcase_page_TC08 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/TestCaseStepsReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test
    public void verifyDetailedStepsDisplayed() {
        test = extent.createTest("TC08: Verify Detailed Steps Displayed for Test Case 1");

        try {
            // Step 1: Navigate to Test Cases page
            driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();

            // Step 2: Locate the first test case block directly
            WebElement firstTestCaseBlock = driver.findElement(By.xpath("//div[@class='panel-group']/div[1]"));
            scrollTo(firstTestCaseBlock);

            // Step 3: Validate that expected steps are present
            String fullText = firstTestCaseBlock.getText().toLowerCase();

            if (!fullText.contains("launch browser") || !fullText.contains("navigate to url")) {
                throw new AssertionError("Expected steps not found in Test Case 1");
            }

            test.pass("Detailed steps are displayed correctly for Test Case 1");
            captureScreenshot("TC08_DetailedStepsVisible");

        } catch (Exception e) {
            test.fail("Failed to validate detailed steps: " + e.getMessage());
            captureScreenshot("TC08_DetailedStepsFail");
            throw new RuntimeException(e);
        }
    }


    public void scrollTo(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot saved: " + dest.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}

