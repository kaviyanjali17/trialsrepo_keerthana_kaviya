package com.automationexercise.tests;

import org.testng.annotations.Test;


import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Homepage_TC16to19 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageIconNavigationChrome.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage Icon Navigation Tests - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateRecommendedItemClick() {
        try {
            // Scroll to recommended section header
            WebElement recommendedHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='recommended items']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedHeader);
            Thread.sleep(1500); // Let animations/rendering settle

            test.info("✅ Recommended section is visible");

            // Wait for the first 'View Product' button in recommended section
            WebElement viewProductBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='recommended items']/following::a[text()='View Product'][1]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductBtn);
            test.info("🖱️ Clicked on 'View Product' in recommended items");

            // Validate product detail page
            WebElement productDetail = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));

            if (productDetail.isDisplayed()) {
                test.pass("✅ Product detail page displayed after clicking recommended item");
            } else {
                test.fail("❌ Product detail page not displayed");
            }

            captureScreenshot("RecommendedItem_Chrome_1");

        } catch (Exception e) {
            test.fail("❌ Exception in recommended item validation: " + e.getMessage());
            captureScreenshot("RecommendedItem_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateApiTestingIcon() {
        try {
            WebElement apiIcon = driver.findElement(By.xpath("//a[contains(text(),'API Testing')]"));
            apiIcon.click();

            WebElement apiHeader = driver.findElement(By.xpath("//h2[contains(text(),'APIs list for practice')]"));
            if (apiHeader.isDisplayed()) {
                test.pass("✅ API Testing page opened successfully");
            } else {
                test.fail("❌ API Testing page did not load properly");
            }
            captureScreenshot("ApiTesting_Chrome_1");
        } catch (Exception e) {
            test.fail("❌ Exception in API Testing icon validation: " + e.getMessage());
            captureScreenshot("ApiTesting_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateHomeIconFunctionality() {
        try {
            WebElement homeIcon = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
            homeIcon.click();

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://automationexercise.com/")) {
                test.pass("✅ Home icon navigated to homepage successfully");
            } else {
                test.fail("❌ Home icon did not navigate to homepage. URL: " + currentUrl);
            }
            captureScreenshot("HomeIcon_Chrome_1");
        } catch (Exception e) {
            test.fail("❌ Exception in Home icon validation: " + e.getMessage());
            captureScreenshot("HomeIcon_Failure_Chrome");
        }
    }

    @Test(priority = 4)
    public void validateTestCasesIconFunctionality() {
        try {
            WebElement testCasesIcon = driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]"));
            testCasesIcon.click();

            WebElement testCasesHeader = driver.findElement(By.xpath("//h2[contains(text(),'Test Cases')]"));
            if (testCasesHeader.isDisplayed()) {
                test.pass("✅ Test Cases page opened successfully");
            } else {
                test.fail("❌ Test Cases page did not load properly");
            }
            captureScreenshot("TestCasesIcon_Chrome_1");
        } catch (Exception e) {
            test.fail("❌ Exception in Test Cases icon validation: " + e.getMessage());
            captureScreenshot("TestCasesIcon_Failure_Chrome");
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

