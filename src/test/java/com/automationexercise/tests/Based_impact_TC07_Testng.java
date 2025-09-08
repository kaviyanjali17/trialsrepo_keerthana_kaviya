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
import java.util.List;

public class Based_impact_TC07_Testng {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Replace with EdgeDriver or FirefoxDriver if needed
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Setup Extent Report
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/DeleteCartReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("TC07 - Verify Delete Icon in Cart");
    }

    @Test
    public void verifyDeleteIconInCartWorks() {
        try {
            driver.get("https://automationexercise.com");
            test.info("Opened homepage");

            // Step 1: Wait for homepage to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));

            // Step 2: Navigate to Products
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Products']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
            productsLink.click();
            test.info("Navigated to Products page");

            // Step 3: Add first product to cart
            WebElement firstProduct = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='productinfo text-center'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            Thread.sleep(500);

            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class,'add-to-cart')])[1]")));
            addToCartBtn.click();
            test.info("Clicked 'Add to Cart'");

            // Step 4: Click 'View Cart' in modal
            WebElement viewCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']")));
            viewCartBtn.click();
            test.info("Opened Cart page");

            // Step 5: Scroll to cart table and capture screenshot
            WebElement cartTable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='cart_info_table']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartTable);
            Thread.sleep(500);
            String addShot = ScreenshotUtilities.capture(driver, "AfterAddToCart");
            test.info("Cart after adding item").addScreenCaptureFromPath(addShot);

            // Step 6: Validate item is in cart
            List<WebElement> cartItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tr[contains(@id,'product')]")));
            Assert.assertTrue(cartItems.size() > 0, "Cart is empty before deletion");
            test.pass("Item successfully added to cart");

            // Step 7: Click delete icon
            WebElement deleteIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart_quantity_delete']")));
            deleteIcon.click();
            test.info("Clicked delete icon");

            // Step 8: Wait for deletion to complete
            wait.until(ExpectedConditions.invisibilityOf(deleteIcon));
            Thread.sleep(500);

            // Step 9: Scroll and capture after deletion
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartTable);
            String deleteShot = ScreenshotUtilities.capture(driver, "AfterDeleteFromCart");
            test.info("Cart after deleting item").addScreenCaptureFromPath(deleteShot);

            // Step 10: Validate cart is empty
            List<WebElement> updatedCartItems = driver.findElements(By.xpath("//tr[contains(@id,'product')]"));
            Assert.assertTrue(updatedCartItems.size() == 0, "Cart still contains items after deletion");
            test.pass("Item successfully removed from cart");

        } catch (Exception e) {
            e.printStackTrace();
            String errorShot = ScreenshotUtilities.capture(driver, "Exception_DeleteCart");
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




