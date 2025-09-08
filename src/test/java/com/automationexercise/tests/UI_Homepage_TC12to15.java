package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Homepage_TC12to15 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageIconsChrome.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage Icon Validations - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateContactUsIcon() {
        try {
            WebElement contactUs = driver.findElement(By.xpath("//a[contains(text(),'Contact us')]"));
            contactUs.click();
            WebElement contactForm = driver.findElement(By.xpath("//h2[contains(text(),'Get In Touch')]"));
            if (contactForm.isDisplayed()) {
                test.pass("✅ Contact Us page opened successfully");
            } else {
                test.fail("❌ Contact Us page did not load properly");
            }
            captureScreenshot("ContactUs_Chrome");
        } catch (Exception e) {
            test.fail("❌ Exception in Contact Us validation: " + e.getMessage());
            captureScreenshot("ContactUs_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateSubscriptionSection() {
        try {
            WebElement emailInput = driver.findElement(By.id("susbscribe_email"));
            WebElement subscribeBtn = driver.findElement(By.id("subscribe"));

            emailInput.sendKeys("keerthana.test@example.com");
            subscribeBtn.click();

            WebElement successMsg = driver.findElement(By.xpath("//*[contains(text(),'You have been successfully subscribed!')]"));
            if (successMsg.isDisplayed()) {
                test.pass("✅ Subscription successful with confirmation message");
            } else {
                test.fail("❌ Subscription confirmation not displayed");
            }
            captureScreenshot("Subscription_Chrome");
        } catch (Exception e) {
            test.fail("❌ Exception in subscription validation: " + e.getMessage());
            captureScreenshot("Subscription_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateCartIconFunctionality() {
        try {
            WebElement cartIcon = driver.findElement(By.xpath("//a[contains(text(),'Cart')]"));
            cartIcon.click();

            WebElement cartHeader = driver.findElement(By.xpath("//li[contains(text(),'Shopping Cart is empty!')]"));
            if (cartHeader.isDisplayed()) {
                test.pass("✅ Cart page opened and shows empty cart");
            } else {
                test.fail("❌ Cart page did not load or cart status not visible");
            }
            captureScreenshot("CartIcon_Chrome");
        } catch (Exception e) {
            test.fail("❌ Exception in cart icon validation: " + e.getMessage());
            captureScreenshot("CartIcon_Failure_Chrome");
        }
    }

    @Test(priority = 4)
    public void validateProductSuggestionPopup() {
        try {
            WebElement viewProductBtn = driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductBtn);
            viewProductBtn.click();

            WebElement recommendedSection = driver.findElement(By.xpath("//h2[contains(text(),'recommended items')]"));
            if (recommendedSection.isDisplayed()) {
                test.pass("✅ Product suggestion pop-up is visible and working");
            } else {
                test.fail("❌ Product suggestion pop-up not displayed");
            }
            captureScreenshot("SuggestionPopup_Chrome");
        } catch (Exception e) {
            test.fail("❌ Exception in suggestion pop-up validation: " + e.getMessage());
            captureScreenshot("SuggestionPopup_Failure_Chrome");
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

