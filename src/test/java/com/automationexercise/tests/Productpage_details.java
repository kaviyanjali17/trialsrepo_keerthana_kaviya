package com.automationexercise.tests;

import org.testng.annotations.Test;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;


import org.apache.commons.io.FileUtils;

public class Productpage_details {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ProductDetailReport.html");
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
    public void validateProductDetailFields() {
        test = extent.createTest("TC: Validate Product Detail Page Fields");

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

            // Step 3: Wait for product detail page
            WebElement productInfo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));
            captureScreenshot("01_ProductDetailPage");

            // Step 4: Validate Category
            WebElement category = driver.findElement(By.xpath("//div[@class='product-information']/p[1]"));
            String categoryText = category.getText().trim();
            test.pass("Category: " + categoryText);
            captureScreenshot("02_Category");

            // Step 5: Validate Availability
            WebElement availability = driver.findElement(By.xpath("//div[@class='product-information']/p[2]"));
            String availabilityText = availability.getText().trim();
            Assert.assertTrue(availabilityText.contains("Availability"));
            test.pass("Availability: " + availabilityText);
            captureScreenshot("03_Availability");

            // Step 6: Validate Condition
            WebElement condition = driver.findElement(By.xpath("//div[@class='product-information']/p[3]"));
            String conditionText = condition.getText().trim();
            Assert.assertTrue(conditionText.contains("Condition"));
            test.pass("✅ Condition: " + conditionText);
            captureScreenshot("04_Condition");

            // Step 7: Validate Price
            WebElement price = driver.findElement(By.xpath("//div[@class='product-information']//span"));
            String priceText = price.getText().trim();
            Assert.assertTrue(priceText.startsWith("Rs."));
            test.pass("Price: " + priceText);
            captureScreenshot("05_Price");

        } catch (Exception e) {
            test.fail("❌ Product detail validation failed: " + e.getMessage());
            captureScreenshot("Failure_ProductDetail");
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


