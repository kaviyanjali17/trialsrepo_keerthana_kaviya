package com.automationexercise.tests;

import org.testng.annotations.Test;


import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Homepage_chrome {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    String browserName;

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        browserName = browser.toLowerCase();

        // ✅ Initialize ExtentReports here to avoid null
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/Report_" + browserName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", browserName);
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("TC: Validate Home Page Load on " + browserName.toUpperCase());

        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("❌ Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test
    public void validateHomePageLoad() {
        try {
            String actualTitle = driver.getTitle();
            test.info("🔍 Actual page title: " + actualTitle);

            if (actualTitle.trim().contains("Automation Exercise")) {
                test.pass("✅ Title matched: " + actualTitle);
            } else {
                test.fail("❌ Title mismatch. Found: " + actualTitle);
            }

            captureScreenshot("HomePage_" + browserName);

        } catch (Exception e) {
            test.fail("❌ Exception: " + e.getMessage());
            captureScreenshot("Failure_" + browserName);
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
        extent.flush(); // ✅ Flush after each test
    }
}

