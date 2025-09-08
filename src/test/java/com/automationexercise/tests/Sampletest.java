package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Sampletest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/TestCaseDetailChromeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Verify Test Case Detail Display - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/test_cases");
    }

  
    @Test
    public void validateTestCaseDetailsAreVisible() {
        try {
            driver.get("https://automationexercise.com/test_cases");

            // Scroll to ensure all content loads
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            // Locate heading and description
            WebElement heading = driver.findElement(By.xpath("//strong[contains(text(),'Test Case 1: Register User')]"));
            WebElement description = driver.findElement(By.xpath("//li[contains(text(),\"Verify that 'ENTER ACCOUNT INFORMATION' is visible\")]"));

            Assert.assertTrue(heading.isDisplayed(), "❌ Test case heading not visible");
            Assert.assertTrue(description.isDisplayed(), "❌ Detailed steps not visible");

            test.pass("✅ Test case heading and detailed steps are visible without clicking");
            captureScreenshot("TestCaseDetailsVisible");

        } catch (Exception e) {
            test.fail("❌ Failed to validate test case details: " + e.getMessage());
            captureScreenshot("TestCaseDetailsVisible_Failure");
        }
    }



    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            test.info("📸 Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            test.warning("⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

