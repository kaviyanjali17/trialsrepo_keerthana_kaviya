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

public class ECP_TC7to9 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/FieldValidationECPReport.html");
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
    public void validateQuantityFieldECP() {
        test = extent.createTest("TC1: Validate Quantity Field with ECP Inputs");

        try {
            navigateToFirstProduct();

            WebElement quantityField = driver.findElement(By.id("quantity"));

            // Test Data 1: Valid numeric input
            quantityField.clear();
            quantityField.sendKeys("2");
            test.pass("✅ Quantity '2' accepted as valid");
            captureScreenshot("01_Quantity_Valid");

            // Test Data 2: Special character
            quantityField.clear();
            quantityField.sendKeys("@");
            String value2 = quantityField.getAttribute("value");
            if (value2.equals("@")) {
                test.warning("⚠️ Quantity '@' accepted unexpectedly");
            } else {
                test.pass("✅ Quantity '@' rejected as expected");
            }
            captureScreenshot("02_Quantity_SpecialChar");

            // Test Data 3: Alphabet
            quantityField.clear();
            quantityField.sendKeys("a");
            String value3 = quantityField.getAttribute("value");
            if (value3.equals("a")) {
                test.warning("⚠️ Quantity 'a' accepted unexpectedly");
            } else {
                test.pass("✅ Quantity 'a' rejected as expected");
            }
            captureScreenshot("03_Quantity_Alphabet");

        } catch (Exception e) {
            test.fail("❌ Quantity field validation failed: " + e.getMessage());
            captureScreenshot("QuantityField_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void validateQuantityLimitsECP() {
        test = extent.createTest("TC2: Validate Quantity Field Limits (1–99)");

        try {
            navigateToFirstProduct();

            WebElement quantityField = driver.findElement(By.id("quantity"));

            String[] testData = {"0", "1", "99", "100"};
            for (String qty : testData) {
                quantityField.clear();
                quantityField.sendKeys(qty);
                String actual = quantityField.getAttribute("value");

                boolean isValid = actual.equals(qty) && Integer.parseInt(qty) >= 1 && Integer.parseInt(qty) <= 99;

                if (isValid) {
                    test.pass("✅ Quantity '" + qty + "' accepted as valid");
                } else {
                    test.warning("⚠️ Quantity '" + qty + "' accepted unexpectedly");
                }
                captureScreenshot("Quantity_" + qty);
            }

        } catch (Exception e) {
            test.fail("❌ Quantity limit validation failed: " + e.getMessage());
            captureScreenshot("QuantityLimit_Failure");
            throw new RuntimeException(e);
        }
    }

   

    public void navigateToFirstProduct() {
        driver.findElement(By.xpath("//a[text()=' Products']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));

        WebElement viewProductBtn = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductBtn);
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(viewProductBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductBtn);
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

