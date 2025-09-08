package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Loginpage_TC24to29 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/NavigationIconsChromeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Navigation Icon Validations - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1)
    public void validateProductsIcon() {
        try {
            WebElement productsIcon = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
            Assert.assertTrue(productsIcon.isDisplayed(), "Products icon not visible");
            productsIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[text()='All Products']"));
            Assert.assertTrue(header.isDisplayed(), "Products page not loaded");
            test.pass(" Products icon is available and navigates correctly");
            captureScreenshot("ProductsIcon");
        } catch (Exception e) {
            test.fail(" Products icon test failed: " + e.getMessage());
            captureScreenshot("ProductsIcon_Failure");
        }
    }

    @Test(priority = 2)
    public void validateCartIcon() {
        try {
            WebElement cartIcon = driver.findElement(By.xpath("//a[contains(text(),'Cart')]"));
            Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon not visible");
            cartIcon.click();
            WebElement cartHeader = driver.findElement(By.xpath("//li[contains(text(),'Shopping Cart is empty!')]"));
            Assert.assertTrue(cartHeader.isDisplayed(), "Cart page not loaded");
            test.pass(" Cart icon is available and navigates correctly");
            captureScreenshot("CartIcon");
        } catch (Exception e) {
            test.fail(" Cart icon test failed: " + e.getMessage());
            captureScreenshot("CartIcon_Failure");
        }
    }

    @Test(priority = 3)
    public void validateVideoTutorialsIcon() {
        try {
            WebElement videoIcon = driver.findElement(By.xpath("//a[contains(text(),'Video Tutorials')]"));
            Assert.assertTrue(videoIcon.isDisplayed(), "Video Tutorials icon not visible");
            videoIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Video Tutorials')]"));
            Assert.assertTrue(header.isDisplayed(), "Video Tutorials page not loaded");
            test.pass(" Video Tutorials icon is available and navigates correctly");
            captureScreenshot("VideoTutorialsIcon");
        } catch (Exception e) {
            test.fail(" Video Tutorials icon test failed: " + e.getMessage());
            captureScreenshot("VideoTutorialsIcon_Failure");
        }
    }

    @Test(priority = 4)
    public void validateContactUsIcon() {
        try {
            WebElement contactIcon = driver.findElement(By.xpath("//a[contains(text(),'Contact us')]"));
            Assert.assertTrue(contactIcon.isDisplayed(), "Contact Us icon not visible");
            contactIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Get In Touch')]"));
            Assert.assertTrue(header.isDisplayed(), "Contact Us page not loaded");
            test.pass(" Contact Us icon is available and navigates correctly");
            captureScreenshot("ContactUsIcon");
        } catch (Exception e) {
            test.fail(" Contact Us icon test failed: " + e.getMessage());
            captureScreenshot("ContactUsIcon_Failure");
        }
    }

    @Test(priority = 5)
    public void validateTestCasesIcon() {
        try {
            WebElement testCasesIcon = driver.findElement(By.xpath("//a[contains(text(),'Test Cases')]"));
            Assert.assertTrue(testCasesIcon.isDisplayed(), "Test Cases icon not visible");
            testCasesIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Test Cases')]"));
            Assert.assertTrue(header.isDisplayed(), "Test Cases page not loaded");
            test.pass(" Test Cases icon is available and navigates correctly");
            captureScreenshot("TestCasesIcon");
        } catch (Exception e) {
            test.fail(" Test Cases icon test failed: " + e.getMessage());
            captureScreenshot("TestCasesIcon_Failure");
        }
    }

    @Test(priority = 6)
    public void validateApiTestingIcon() {
        try {
            WebElement apiIcon = driver.findElement(By.xpath("//a[contains(text(),'API Testing')]"));
            Assert.assertTrue(apiIcon.isDisplayed(), "API Testing icon not visible");
            apiIcon.click();
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'APIs list for practice')]"));
            Assert.assertTrue(header.isDisplayed(), "API Testing page not loaded");
            test.pass(" API Testing icon is available and navigates correctly");
            captureScreenshot("ApiTestingIcon");
        } catch (Exception e) {
            test.fail(" API Testing icon test failed: " + e.getMessage());
            captureScreenshot("ApiTestingIcon_Failure");
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

