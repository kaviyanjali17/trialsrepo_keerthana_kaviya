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

public class UI_Loginpage_TC17to23 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ScrollSubscriptionReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Scroll & Subscription Validations - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1)
    public void validateScrollBarFunctionality() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)");
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0, -500)");
            test.pass(" Scroll bar is working properly");
            captureScreenshot("ScrollBar");
        } catch (Exception e) {
            test.fail("Scroll bar test failed: " + e.getMessage());
            captureScreenshot("ScrollBar_Failure");
        }
    }

    @Test(priority = 2)
    public void validateScrollToTopArrow() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement scrollUpBtn = driver.findElement(By.id("scrollUp"));
            js.executeScript("arguments[0].click();", scrollUpBtn);
            Thread.sleep(1000);

            Long scrollY = (Long) js.executeScript("return window.pageYOffset;");
            Assert.assertTrue(scrollY == 0, "Page did not scroll to top");
            test.pass(" Scroll to top arrow is working");
            captureScreenshot("ScrollToTop");
        } catch (Exception e) {
            test.fail(" Scroll to top arrow test failed: " + e.getMessage());
            captureScreenshot("ScrollToTop_Failure");
        }
    }

    @Test(priority = 3)
    public void validateSubscriptionIconPresence() {
        try {
            WebElement emailInput = driver.findElement(By.id("susbscribe_email"));
            WebElement subscribeBtn = driver.findElement(By.id("subscribe"));
            Assert.assertTrue(emailInput.isDisplayed() && subscribeBtn.isDisplayed(), "Subscription elements not visible");
            test.pass(" Subscription icon/button is available");
            captureScreenshot("SubscriptionIcon");
        } catch (Exception e) {
            test.fail(" Subscription icon presence test failed: " + e.getMessage());
            captureScreenshot("SubscriptionIcon_Failure");
        }
    }

    @Test(priority = 4)
    public void subscribeWithValidEmail() {
        try {
            driver.findElement(By.id("susbscribe_email")).sendKeys("keerthana12003@gmail.com");
            driver.findElement(By.id("subscribe")).click();
            WebElement successMsg = driver.findElement(By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
            Assert.assertTrue(successMsg.isDisplayed(), "Success message not shown");
            test.pass(" Subscription with valid email succeeded");
            captureScreenshot("SubscriptionValid");
        } catch (Exception e) {
            test.fail(" Subscription with valid email failed: " + e.getMessage());
            captureScreenshot("SubscriptionValid_Failure");
        }
    }

    @Test(priority = 5)
    public void subscribeWithInvalidEmail() {
        try {
            driver.findElement(By.id("susbscribe_email")).sendKeys("keerthana12003gmail.com");
            driver.findElement(By.id("subscribe")).click();
            Alert alert = driver.switchTo().alert();
            Assert.assertTrue(alert.getText().contains("@"), "No alert for invalid email");
            alert.accept();
            test.pass(" Invalid email triggered alert");
            captureScreenshot("SubscriptionInvalidEmail");
        } catch (Exception e) {
            test.fail(" Subscription with invalid email failed: " + e.getMessage());
            captureScreenshot("SubscriptionInvalidEmail_Failure");
        }
    }

    @Test(priority = 6)
    public void subscribeWithExistingEmail() {
        try {
            driver.findElement(By.id("susbscribe_email")).sendKeys("keerthana12003@gmail.com");
            driver.findElement(By.id("subscribe")).click();
            WebElement successMsg = driver.findElement(By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
            Assert.assertTrue(successMsg.isDisplayed(), "Success message not shown");
            test.pass(" Subscription with existing email succeeded (no duplicate check)");
            captureScreenshot("SubscriptionExistingEmail");
        } catch (Exception e) {
            test.fail(" Subscription with existing email failed: " + e.getMessage());
            captureScreenshot("SubscriptionExistingEmail_Failure");
        }
    }

    @Test(priority = 7)
    public void subscribeWithNoEmail() {
        try {
            driver.findElement(By.id("subscribe")).click();
            WebElement emailField = driver.findElement(By.id("susbscribe_email"));
            String validationMessage = emailField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing email");
            test.pass(" Alert shown for missing email");
            captureScreenshot("SubscriptionNoEmail");
        } catch (Exception e) {
            test.fail(" Subscription with no email failed: " + e.getMessage());
            captureScreenshot("SubscriptionNoEmail_Failure");
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

