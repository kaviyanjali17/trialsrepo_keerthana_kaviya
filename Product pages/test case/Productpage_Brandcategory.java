package com.automationexercise.tests;

import org.testng.annotations.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Productpage_Brandcategory {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/SearchAndBrandReport.html");
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
    public void validateBrandAndSearchResults() {
        test = extent.createTest("TC: Validate Brand on Product Detail Page and Search Results");

        try {
            // Step 1: Navigate to Products page
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
            test.pass("Navigated to All Products page");

            // Step 2: Click 'View Product' for first product
            WebElement viewProductBtn = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
            viewProductBtn.click();
            test.pass("Clicked 'View Product' for first product");

            // Step 3: Validate Brand on Product Detail Page
            WebElement brand = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']/p[4]")));
            String brandText = brand.getText().trim();
            Assert.assertTrue(brandText.contains("Brand"), "Brand text not found");
            test.pass("Brand displayed: " + brandText);
            captureScreenshot("01_BrandDisplayed");

            // Step 4: Navigate to Products page again for search
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));

            // Step 5: Wait for search input
            WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='search']")));
            searchField.clear();
            searchField.sendKeys("T-Shirt");

            // Step 6: Click Search
            WebElement searchBtn = driver.findElement(By.xpath("//button[@id='submit_search']"));
            searchBtn.click();
            test.pass("Search initiated for 'T-Shirt'");
            captureScreenshot("02_SearchClicked");

            // Step 7: Validate SEARCHED PRODUCTS heading
            WebElement searchedHeading = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Searched Products']")));
            Assert.assertTrue(searchedHeading.isDisplayed(), "'SEARCHED PRODUCTS' heading not visible");
            test.pass(" 'SEARCHED PRODUCTS' heading is visible");
            captureScreenshot("03_SearchedProductsHeading");

        } catch (Exception e) {
            test.fail("Test failed: " + e.getMessage());
            captureScreenshot("Failure_SearchAndBrand");
            throw new RuntimeException(e);
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
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


