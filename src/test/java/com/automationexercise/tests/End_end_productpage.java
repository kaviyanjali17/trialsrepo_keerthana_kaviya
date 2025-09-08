package com.automationexercise.tests;

import org.testng.annotations.Test;



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

public class End_end_productpage {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ProductPageReport.html");
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

    @Test(priority = 1)
    public void verifySearchBarOnProductsPage() {
        test = extent.createTest("TC1: Verify Search Bar on Products Page");

        try {
            // Step 1: Navigate to Products page
            driver.findElement(By.xpath("//a[text()=' Products']")).click();

            // Step 2: Wait for search bar to appear
            WebElement searchBar = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search_product']")));
            assert searchBar.isDisplayed();
            test.pass("✅ Search bar is visible on Products page");
            captureScreenshot("TC1_SearchBarVisible");

            // Step 3: Enter product name and click search
            searchBar.sendKeys("T-Shirt");
            driver.findElement(By.xpath("//button[@id='submit_search']")).click();

            // Step 4: Validate search results
            WebElement resultHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Searched Products')]")));
            assert resultHeader.isDisplayed();
            test.pass("✅ Search executed and results displayed");
            captureScreenshot("TC1_SearchResults");

        } catch (Exception e) {
            test.fail("❌ Search bar test failed: " + e.getMessage());
            captureScreenshot("TC1_SearchBarFail");
            throw new RuntimeException(e);
        }
    }


    @Test(priority = 2)
    public void verifyViewProductFunctionality() {
        test = extent.createTest("TC2: Verify View Product Button Functionality");
        try {
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            WebElement allProductsHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
            assert allProductsHeader.isDisplayed();
            captureScreenshot("TC2_AllProductsVisible");

            driver.findElement(By.xpath("(//a[text()='View Product'])[1]")).click();
            WebElement productDetail = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));
            assert productDetail.isDisplayed();
            test.pass("View Product opened successfully");
            captureScreenshot("TC2_ProductDetailVisible");
        } catch (Exception e) {
            test.fail("View Product failed: " + e.getMessage());
            captureScreenshot("TC2_ViewProductFail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void verifyAddToCartFunctionality() {
        test = extent.createTest("TC3: Verify Add to Cart Button Functionality");
        try {
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            WebElement allProductsHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
            assert allProductsHeader.isDisplayed();
            captureScreenshot("TC3_AllProductsVisible");

            driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
            WebElement modal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cartModal']")));
            assert modal.isDisplayed();
            test.pass("Product added to cart and modal displayed");
            captureScreenshot("TC3_AddToCartSuccess");

            driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
        } catch (Exception e) {
            test.fail("Add to Cart failed: " + e.getMessage());
            captureScreenshot("TC3_AddToCartFail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 4)
    public void verifyQuantityUpdateFunctionality() {
        test = extent.createTest("TC4: Verify Quantity Input Update");
        try {
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            driver.findElement(By.xpath("(//a[text()='View Product'])[1]")).click();

            WebElement quantityInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='quantity']")));
            quantityInput.clear();
            quantityInput.sendKeys("3");
            captureScreenshot("TC4_QuantityUpdated");

            test.pass("Quantity updated to 3 successfully");
        } catch (Exception e) {
            test.fail("Quantity update failed: " + e.getMessage());
            captureScreenshot("TC4_QuantityUpdateFail");
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

