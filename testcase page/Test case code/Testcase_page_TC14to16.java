package com.automationexercise.tests;

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
import java.util.List;
import org.apache.commons.io.FileUtils;

public class Testcase_page_TC14to16 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/SubscriptionValidation.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void validateSubscriptionWithValidEmail() throws IOException {
        test = extent.createTest("Valid Email Subscription");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        WebElement subscribeButton = driver.findElement(By.id("subscribe"));

        emailField.clear();
        emailField.sendKeys("abc@gmail.com");
        subscribeButton.click();

        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='success-subscribe']/div[contains(text(),'You have been successfully subscribed!')]")));

            if (successMsg.isDisplayed()) {
                test.pass("Valid email subscription succeeded.");
                captureScreenshot("valid_email_subscription");
                return;
            }
        } catch (TimeoutException e) {
            test.fail("Success message not displayed for valid email.");
            captureScreenshot("valid_email_subscription_failed");
            Assert.fail("Valid email subscription failed.");
        }
    }

    @Test(priority = 2)
    public void validateSubscriptionWithInvalidEmail() throws IOException, InterruptedException {
        test = extent.createTest("Invalid Email Subscription");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        WebElement subscribeButton = driver.findElement(By.id("subscribe"));

        emailField.clear();
        emailField.sendKeys("kaviyamail.com");

        // Check if browser blocks submission using JS validity
        Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return arguments[0].checkValidity();", emailField);

        if (!isValid) {
            test.pass("Browser blocked invalid email submission.");
            captureScreenshot("invalid_email_blocked");
            return;
        }

        subscribeButton.click();
        Thread.sleep(1000);

        List<WebElement> successMsgs = driver.findElements(
            By.xpath("//div[@id='success-subscribe']/div[contains(text(),'You have been successfully subscribed!')]"));

        if (successMsgs.size() == 0) {
            test.pass("Invalid email did not trigger success message.");
            captureScreenshot("invalid_email_subscription");
        } else {
            test.fail("Unexpected success message for invalid email.");
            captureScreenshot("invalid_email_subscription_failed");
            Assert.fail("Invalid email subscription failed.");
        }
    }

    @Test(priority = 3)
    public void validateSubscriptionWithBlankEmail() throws IOException, InterruptedException {
        test = extent.createTest("Blank Email Subscription");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        WebElement subscribeButton = driver.findElement(By.id("subscribe"));

        emailField.clear();

        // Check if browser blocks blank input using JS validity
        Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return arguments[0].checkValidity();", emailField);

        if (!isValid) {
            test.pass("Browser blocked blank email submission.");
            captureScreenshot("blank_email_blocked");
            return;
        }

        subscribeButton.click();
        Thread.sleep(1000);

        List<WebElement> successMsgs = driver.findElements(
            By.xpath("//div[@id='success-subscribe']/div[contains(text(),'You have been successfully subscribed!')]"));

        if (successMsgs.size() == 0) {
            test.pass("Blank email did not trigger success message.");
            captureScreenshot("blank_email_subscription");
        } else {
            test.fail("Unexpected success message for blank email.");
            captureScreenshot("blank_email_subscription_failed");
            Assert.fail("Blank email subscription failed.");
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }

    private void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    private void captureScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + name + ".png";
        FileUtils.copyFile(src, new File(path));
        test.addScreenCaptureFromPath(path);
    }
}


