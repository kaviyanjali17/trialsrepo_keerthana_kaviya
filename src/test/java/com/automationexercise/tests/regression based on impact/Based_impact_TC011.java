package com.automationexercise.tests;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.automationexercise.utilities.ScreenshotUtilities;

import java.io.File;
import java.time.Duration;

public class Based_impact_TC011 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Or EdgeDriver / FirefoxDriver
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
    }

    @Test
    public void verifySubscriptionIconOnHomePage() {
        try {
            driver.get("https://automationexercise.com");

            //  Wait for homepage to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));
         // Scroll to subscription section
            WebElement subscriptionHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='single-widget']/h2[text()='Subscription']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
            Thread.sleep(500);

            // Enter email
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Your email address']")
            ));
            emailField.clear();
            emailField.sendKeys("keerthivs126@gmail.com");

            // Scroll to email field before screenshot
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailField);
            Thread.sleep(500);
            ScreenshotUtilities.capture(driver, "AfterEmailEntered");

            // Click Subscribe
            WebElement subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='subscribe']")));
            subscribeBtn.click();

            // Wait for success message
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));

            // Scroll to success message before screenshot
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", successMsg);
            Thread.sleep(500);
            ScreenshotUtilities.capture(driver, "AfterSubscriptionSuccess");


            
            Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message not displayed");

            ScreenshotUtilities.capture(driver, "AfterSubscription");

            System.out.println(" Subscription icon is working on home page.");

        } catch (Exception e) {
            e.printStackTrace();
            ScreenshotUtilities.capture(driver, "Exception_Subscription");
            Assert.fail(" Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}




