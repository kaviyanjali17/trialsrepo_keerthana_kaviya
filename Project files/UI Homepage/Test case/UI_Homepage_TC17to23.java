package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.testng.Assert;


public class UI_Homepage_TC17to23 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageNavigationChrome.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage Navigation Tests - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateApiTestingIcon() {
        try {
            WebElement apiIcon = driver.findElement(By.xpath("//a[contains(text(),'API Testing')]"));
            apiIcon.click();
            WebElement apiHeader = driver.findElement(By.xpath("//h2[contains(text(),'APIs list for practice')]"));
            Assert.assertTrue(apiHeader.isDisplayed(), "✅ API Testing page opened successfully");
            test.pass("✅ API Testing page opened successfully");

            captureScreenshot("ApiTesting_Chrome");
        } catch (Exception e) {
            test.fail("❌ API Testing icon failed: " + e.getMessage());
            captureScreenshot("ApiTesting_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateHomeIcon() {
        try {
            WebElement homeIcon = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
            homeIcon.click();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.equals("https://automationexercise.com/"), "✅ Home icon navigated correctly");
            captureScreenshot("HomeIcon_Chrome");
        } catch (Exception e) {
            test.fail("❌ Home icon failed: " + e.getMessage());
            captureScreenshot("HomeIcon_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateTestCasesIcon() {
        try {
            WebElement testCasesIcon = driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]"));
            testCasesIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Test Cases')]"));
            Assert.assertTrue(header.isDisplayed(), "✅ Test Cases page opened");
            captureScreenshot("TestCasesIcon_Chrome");
        } catch (Exception e) {
            test.fail("❌ Test Cases icon failed: " + e.getMessage());
            captureScreenshot("TestCasesIcon_Failure_Chrome");
        }
    }

    @Test(priority = 4)
    public void validateProductsIcon() {
        try {
            WebElement productsIcon = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
            productsIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[text()='All Products']"));
            Assert.assertTrue(header.isDisplayed(), "✅ Products page opened");
            captureScreenshot("ProductsIcon_Chrome");
        } catch (Exception e) {
            test.fail("❌ Products icon failed: " + e.getMessage());
            captureScreenshot("ProductsIcon_Failure_Chrome");
        }
    }

    @Test(priority = 5)
    public void validateCategorySelection() {
        try {
            WebElement womenCategory = driver.findElement(By.xpath("//a[@href='#Women']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);
            womenCategory.click();
            WebElement subCategory = driver.findElement(By.xpath("//a[contains(text(),'Dress')]"));
            subCategory.click();
            WebElement productGrid = driver.findElement(By.xpath("//h2[text()='Women - Dress Products']"));
            Assert.assertTrue(productGrid.isDisplayed(), "✅ Category page loaded");
            captureScreenshot("CategorySelection_Chrome");
        } catch (Exception e) {
            test.fail("❌ Category selection failed: " + e.getMessage());
            captureScreenshot("CategorySelection_Failure_Chrome");
        }
    }

    @Test(priority = 6)
    public void validateBrandSelection() {
        try {
            WebElement brandLink = driver.findElement(By.xpath("//a[contains(text(),'Polo')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", brandLink);
            brandLink.click();
            WebElement brandHeader = driver.findElement(By.xpath("//h2[contains(text(),'Brand - Polo Products')]"));
            Assert.assertTrue(brandHeader.isDisplayed(), "✅ Brand page loaded");
            captureScreenshot("BrandSelection_Chrome");
        } catch (Exception e) {
            test.fail("❌ Brand selection failed: " + e.getMessage());
            captureScreenshot("BrandSelection_Failure_Chrome");
        }
    }

    @Test(priority = 7)
    public void validateProductHoverFunctionality() {
        try {
            // Scroll to product section
            WebElement productContainer = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productContainer);
            Thread.sleep(1000);

            // Hover over product container
            Actions actions = new Actions(driver);
            actions.moveToElement(productContainer).perform();
            Thread.sleep(1000); // Let overlay render

            // Check if overlay is visible
            WebElement overlay = driver.findElement(By.xpath("(//div[@class='product-overlay'])[1]"));
            boolean isOverlayVisible = overlay.isDisplayed();

            // Check if Add to Cart button is visible inside overlay
            WebElement addToCartBtn = overlay.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));
            boolean isAddToCartVisible = addToCartBtn.isDisplayed();

            if (isOverlayVisible && isAddToCartVisible) {
                test.pass("✅ Hover revealed 'Add to cart' button");
            } else {
                test.fail("❌ 'Add to cart' button not visible on hover");
            }

            captureScreenshot("ProductHover_Chrome");

        } catch (Exception e) {
            test.fail("❌ Exception during hover validation: " + e.getMessage());
            captureScreenshot("ProductHover_Failure_Chrome");
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

