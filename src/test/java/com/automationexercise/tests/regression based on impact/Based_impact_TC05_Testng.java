package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.automationexercise.utilities.ScreenshotUtilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.Duration;

public class Based_impact_TC05_Testng {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Or EdgeDriver / FirefoxDriver
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // Prevent TimeoutException
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Setup Extent Report
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/SearchButtonReport_TC005.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("TC008 - Search Button Functionality on Product Page");
    }

    @Test
    public void verifySearchButtonWorks() {
        try {
            driver.get("https://automationexercise.com");
            test.info("Opened homepage");

            // Step 1: Navigate to Products page
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Products']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
            productsLink.click();
            test.info("Navigated to Products page");

            // Step 2: Locate search input field
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_product")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchInput);
            Thread.sleep(500);
            searchInput.sendKeys("Dress");
            test.info("Entered product name: Dress");

            String beforeSearchShot = ScreenshotUtilities.capture(driver, "BeforeSearch_TC005");
            test.info("Before clicking search").addScreenCaptureFromPath(beforeSearchShot);

            // Step 3: Click search button
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_search")));
            searchBtn.click();
            test.info("Clicked search button");

            // Step 4: Validate search results
            WebElement searchResultsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Searched Products']")
            ));
            Assert.assertTrue(searchResultsHeader.isDisplayed(), "Search results header not displayed");

         

            // Scroll to search results
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchResultsHeader);
            Thread.sleep(500); // Let scroll settle

            String afterSearchShot = ScreenshotUtilities.capture(driver, "AfterSearch_TC005");
            test.info("After search results loaded").addScreenCaptureFromPath(afterSearchShot);

        } catch (Exception e) {
            e.printStackTrace();
            String errorShot = ScreenshotUtilities.capture(driver, "Exception_Search_TC005");
            test.fail("Test failed due to exception").addScreenCaptureFromPath(errorShot);
            Assert.fail("❌ Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Generate the report
    }
}


