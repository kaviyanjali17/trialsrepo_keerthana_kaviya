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

public class UI_Loginpage_TC30to33 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/SignupTextValidationChromeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Signup Page Text & Validation Tests - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1)
    public void signupWithInvalidNameValidEmail() {
        try {
            driver.findElement(By.name("name")).sendKeys("12@");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("Keerthana12003@gmail.com");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            // Check if it incorrectly proceeds to account creation
            boolean accountPageLoaded = driver.findElements(By.xpath("//b[contains(text(),'Enter Account Information')]")).size() > 0;

            if (accountPageLoaded) {
                test.fail("Signup accepted invalid name. No validation triggered.");
            } else {
                test.pass(" Invalid name prevented signup as expected.");
            }

            captureScreenshot("SignupInvalidName");

        } catch (Exception e) {
            test.fail(" Exception during invalid name signup test: " + e.getMessage());
            captureScreenshot("SignupInvalidName_Failure");
        }
    }

    @Test(priority = 2)
    public void validateNewUserSignupText() {
        try {
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'New User Signup!')]"));
            Assert.assertTrue(header.isDisplayed(), "'New User Signup!' text not visible");
            test.pass(" 'New User Signup!' is visible on the page");
            captureScreenshot("NewUserSignupText");
        } catch (Exception e) {
            test.fail(" 'New User Signup!' text validation failed: " + e.getMessage());
            captureScreenshot("NewUserSignupText_Failure");
        }
    }

    @Test(priority = 3)
    public void validateLoginToYourAccountText() {
        try {
            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));
            Assert.assertTrue(header.isDisplayed(), "'Login to your account' text not visible");
            test.pass(" 'Login to your account' is visible on the page");
            captureScreenshot("LoginToYourAccountText");
        } catch (Exception e) {
            test.fail(" 'Login to your account' text validation failed: " + e.getMessage());
            captureScreenshot("LoginToYourAccountText_Failure");
        }
    }

    @Test(priority = 4)
    public void validateOrTextVisibility() {
        try {
            WebElement orText = driver.findElement(By.xpath("//div[@class='signup-form']//p[text()='OR']"));
            Assert.assertTrue(orText.isDisplayed(), "'OR' text not visible");
            test.pass(" 'OR' is visible between signup and login sections");
            captureScreenshot("OrText");
        } catch (Exception e) {
            test.fail(" 'OR' text validation failed: " + e.getMessage());
            captureScreenshot("OrText_Failure");
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

