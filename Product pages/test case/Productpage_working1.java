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
import org.apache.commons.io.FileUtils;

public class Productpage_working1 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ProductsPageReport.html");
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
    public void validateProductsPageFlow() {
        test = extent.createTest("TC: Validate Products Page Navigation and Details");

        try {
            // Step 1: Click 'Products' button
            WebElement productsBtn = driver.findElement(By.xpath("//a[text()=' Products']"));
            productsBtn.click();
            test.pass("✅ Clicked 'Products' button");
            captureScreenshot("01_ProductsClicked");

            // Step 2: Validate ALL PRODUCTS page title
            WebElement allProductsHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
            assert allProductsHeader.isDisplayed();
            test.pass("✅ Navigated to ALL PRODUCTS page");
            captureScreenshot("02_AllProductsPage");

            // Step 3: Validate product list is visible
            WebElement productCard = driver.findElement(By.xpath("(//div[@class='productinfo text-center'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productCard);
            assert productCard.isDisplayed();
            test.pass("✅ Product list is visible");
            captureScreenshot("03_ProductListVisible");

            // Step 4: Validate 'View Product' button of first product
            WebElement viewProductBtn = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
            assert viewProductBtn.isDisplayed();
            test.pass("✅ 'View Product' button is visible for first product");
            captureScreenshot("04_ViewProductVisible");

            // Step 5: Click 'View Product' and validate product detail page
            viewProductBtn.click();
            WebElement productDetailHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-information']")));
            assert productDetailHeader.isDisplayed();
            test.pass("✅ Navigated to product detail page");
            captureScreenshot("05_ProductDetailPage");

        } catch (Exception e) {
            test.fail("❌ Test failed: " + e.getMessage());
            captureScreenshot("Failure_ProductsFlow");
            throw new RuntimeException(e);
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
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

