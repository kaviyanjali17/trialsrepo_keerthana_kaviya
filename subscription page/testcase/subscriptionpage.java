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

public class subscriptionpage {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/SubscriptionValidationReport.html");
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
    public void verifySubscriptionIconVisibleAndClickable() {
        test = extent.createTest("TC1: Verify Subscription Icon Visibility and Clickability");

        try {
            WebElement subscriptionHeader = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='single-widget']/h2[text()='Subscription']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
            Assert.assertTrue(subscriptionHeader.isDisplayed(), "Subscription header not visible");
            test.pass("Subscription icon is visible and clickable");
            captureScreenshot("SubscriptionIconVisible");

        } catch (Exception e) {
            test.fail("Subscription icon test failed: " + e.getMessage());
            captureScreenshot("SubscriptionIcon_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void subscribeWithValidEmail() {
        test = extent.createTest("TC2: Subscribe with Valid Email");

        try {
            scrollToSubscription();
            enterEmailAndSubscribe("keerthivs126@gmail.com");

            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));
            Assert.assertTrue(successMsg.isDisplayed(), "Success message not displayed");
            test.pass("Successfully subscribed with valid email");
            captureScreenshot("ValidEmailSubscribed");

        } catch (Exception e) {
            test.fail("❌ Valid email subscription failed: " + e.getMessage());
            captureScreenshot("ValidEmail_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void subscribeWithInvalidEmail() {
        test = extent.createTest("TC3: Subscribe with Invalid Email");

        try {
            scrollToSubscription();
            enterEmailAndSubscribe("keerthigmail.com");

            boolean successVisible = !driver.findElements(By.id("success-subscribe")).isEmpty();
            if (successVisible) {
                test.warning("Unexpected success message for invalid email");
            } else {
                test.pass("Invalid email prevented from subscribing");
            }
            captureScreenshot("InvalidEmailAttempt");

        } catch (Exception e) {
            test.fail("Invalid email test failed: " + e.getMessage());
            captureScreenshot("InvalidEmail_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 4)
    public void subscribeWithEmptyEmail() {
        test = extent.createTest("TC4: Subscribe with Empty Email");

        try {
            scrollToSubscription();
            enterEmailAndSubscribe("");

            boolean successVisible = !driver.findElements(By.id("success-subscribe")).isEmpty();
            if (successVisible) {
                test.warning(" Unexpected success message for empty email");
            } else {
                test.pass("Empty email prevented from subscribing");
            }
            captureScreenshot("EmptyEmailAttempt");

        } catch (Exception e) {
            test.fail("❌ Empty email test failed: " + e.getMessage());
            captureScreenshot("EmptyEmail_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 5)
    public void subscribeWithExistingEmail() {
        test = extent.createTest("TC5: Subscribe with Already Subscribed Email");

        try {
            scrollToSubscription();
            enterEmailAndSubscribe("keerthivs126@gmail.com");

            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));
            String message = successMsg.getText().trim();

            test.warning(" Subscription accepted for already subscribed email: " + message);
            captureScreenshot("ExistingEmailAttempt");

        } catch (Exception e) {
            test.fail("❌ Existing email test failed: " + e.getMessage());
            captureScreenshot("ExistingEmail_Failure");
            throw new RuntimeException(e);
        }
    }

    public void scrollToSubscription() {
        WebElement subscriptionHeader = driver.findElement(By.xpath("//div[@class='single-widget']/h2[text()='Subscription']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
    }

    public void enterEmailAndSubscribe(String email) {
        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        emailField.clear();
        emailField.sendKeys(email);
        driver.findElement(By.id("subscribe")).click();
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

