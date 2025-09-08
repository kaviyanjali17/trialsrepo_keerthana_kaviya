package com.automationexercise.tests;


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

public class End_end_Cartpage {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/CartCheckoutReport.html");
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
    public void verifyCheckoutButtonFunctionality() {
        test = extent.createTest("TC1: Verify Checkout Button on Cart Page");

        try {
            // Step 1: Add product to cart
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));

            WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
            captureScreenshot("01_AddToCart");

            // Step 2: Handle modal and click View Cart
            WebElement modal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
            WebElement viewCartBtn = modal.findElement(By.xpath(".//a[@href='/view_cart']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartBtn);
            captureScreenshot("02_ViewCart");

            // Step 3: Click Checkout
            WebElement checkoutBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Proceed To Checkout']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
            checkoutBtn.click();
            captureScreenshot("03_CheckoutClicked");

            // Step 4: Validate navigation to login/register
            WebElement loginPrompt = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[text()='Register / Login']")));
            assert loginPrompt.isDisplayed();
            test.pass("✅ Checkout button working, navigated to login/register");

        } catch (Exception e) {
            test.fail("❌ Checkout button test failed: " + e.getMessage());
            captureScreenshot("Checkout_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void verifyPlaceOrderButtonFunctionality() {
        test = extent.createTest("TC2: Verify Place Order Button on Cart Page");

        try {
            // Step 1: Login
            driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthanavs126@gmail.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi@123");
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
            captureScreenshot("01_LoggedIn");

            // Step 2: Add product to cart
            driver.findElement(By.xpath("//a[text()=' Products']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));

            WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
            captureScreenshot("02_AddToCart");

            // Step 3: Handle modal and click View Cart
            WebElement modal = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
            WebElement viewCartBtn = modal.findElement(By.xpath(".//a[@href='/view_cart']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartBtn);
            captureScreenshot("03_ViewCart");

            // Step 4: Click Checkout
            WebElement checkoutBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Proceed To Checkout']")));
            checkoutBtn.click();
            captureScreenshot("04_CheckoutClicked");

            // Step 5: Click Place Order
            WebElement placeOrderBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderBtn);
            placeOrderBtn.click();
            captureScreenshot("05_PlaceOrderClicked");

            // Step 6: Validate navigation to payment page
            WebElement paymentHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Payment']")));
            assert paymentHeader.isDisplayed();
            test.pass("✅ Place Order button working, navigated to payment page");

        } catch (Exception e) {
            test.fail("❌ Place Order button test failed: " + e.getMessage());
            captureScreenshot("PlaceOrder_Failure");
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

