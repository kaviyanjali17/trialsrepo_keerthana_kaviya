package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Testcase_page_TC1to3 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/TestReport_TC1to3.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("✅ Browser launched successfully");
    }

    @Test(priority = 1)
    public void testURLLoadsSuccessfully() {
        test = extent.createTest("Test Case 2: URL Loads Successfully");
        try {
            driver.get("http://automationexercise.com");
            String currentURL = driver.getCurrentUrl();
            assert currentURL.contains("automationexercise");
            test.pass("URL loaded successfully: " + currentURL);
            captureScreenshot("URL_Loaded");
        } catch (Exception e) {
            test.fail("URL failed to load: " + e.getMessage());
            captureScreenshot("URL_Fail");
        }
    }

    @Test(priority = 2)
    public void testHomePageElementsVisible() {
        test = extent.createTest("Test Case 3: Home Page Elements Visible");
        try {
            driver.get("http://automationexercise.com");

            WebElement logo = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
            WebElement navMenu = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"));

            assert logo.isDisplayed();
            assert navMenu.isDisplayed();

            test.pass("Homepage elements are visible: Logo and Navigation Menu");
            captureScreenshot("HomePage_Visible");
        } catch (Exception e) {
            test.fail("Homepage elements not visible: " + e.getMessage());
            captureScreenshot("HomePage_Fail");
            throw new RuntimeException(e);
        }
    }

    public void captureScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("Screenshots/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
        System.out.println("🔒 Browser closed");
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}

