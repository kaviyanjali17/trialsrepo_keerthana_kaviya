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

public class End_end_contact {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/ContactSubscriptionReport.html");
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
    public void verifyContactUsTextFields() {
        test = extent.createTest("TC1: Verify Contact Us Text Fields");

        try {
            driver.findElement(By.xpath("//a[text()=' Contact us']")).click();

            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.name("email")).sendKeys("keerthivs126@gmail.com");
            driver.findElement(By.name("subject")).sendKeys("Product Review Issue");
            driver.findElement(By.id("message")).sendKeys("Cant able to view product review");
            captureScreenshot("TC1_ContactUsFieldsFilled");

            test.pass("✅ All text fields on Contact Us page are working");
        } catch (Exception e) {
            test.fail("❌ Contact Us text field test failed: " + e.getMessage());
            captureScreenshot("TC1_ContactUsFieldsFail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void verifyContactUsFormSubmission() {
        test = extent.createTest("TC2: Verify Contact Us Form Submission");

        try {
            driver.findElement(By.xpath("//a[text()=' Contact us']")).click();

            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.name("email")).sendKeys("keerthivs126@gmail.com");
            driver.findElement(By.name("subject")).sendKeys("Product Review Issue");
            driver.findElement(By.id("message")).sendKeys("Cant able to view product review");

            WebElement uploadInput = driver.findElement(By.name("upload_file"));
            uploadInput.sendKeys(System.getProperty("user.dir") + "/Screenshots/TC1_ContactUsFieldsFilled.png"); // optional file upload

            driver.findElement(By.name("submit")).click();
            driver.switchTo().alert().accept(); // handle alert
            captureScreenshot("TC2_ContactUsSubmitted");

            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='status alert alert-success']")));
            assert successMsg.isDisplayed();
            test.pass("✅ Contact Us form submitted successfully");
        } catch (Exception e) {
            test.fail("❌ Contact Us form submission failed: " + e.getMessage());
            captureScreenshot("TC2_ContactUsSubmitFail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 3)
    public void verifySubscriptionIconOnHomePage() {
        test = extent.createTest("TC3: Verify Subscription Icon on Home Page");

        try {
            WebElement subscriptionEmail = driver.findElement(By.id("susbscribe_email"));
            subscriptionEmail.sendKeys("keerthivs126@gmail.com");

            WebElement subscribeBtn = driver.findElement(By.id("subscribe"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subscribeBtn);
            captureScreenshot("TC3_SubscriptionClicked");

            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert-success alert']")));
            assert successMsg.isDisplayed();
            test.pass("✅ Subscription icon is working and confirmation message displayed");
        } catch (Exception e) {
            test.fail("❌ Subscription icon test failed: " + e.getMessage());
            captureScreenshot("TC3_SubscriptionFail");
            throw new RuntimeException(e);
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot saved: " + dest.getAbsolutePath());
        } catch (Exception e) {
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

