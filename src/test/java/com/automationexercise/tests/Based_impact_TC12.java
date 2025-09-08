package com.automationexercise.tests;




import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.automationexercise.utilities.ScreenshotUtilities;

import java.io.File;
import java.time.Duration;

public class Based_impact_TC12 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Or EdgeDriver / FirefoxDriver
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Ensure screenshot folder exists
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
    }

    @Test
    public void verifyReviewSubmitButtonWorks() {
        try {
            driver.get("https://automationexercise.com");

            //  Step 1: Wait for homepage to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));

            // Step 2: Click on Products
            WebElement productsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=' Products']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
            wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();

            // Step 3: Click on first View Product
            WebElement viewProductBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='View Product'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductBtn);

            // Step 4: Scroll to review section
            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nameField);
            Thread.sleep(500);

            // Step 5: Fill review form
            nameField.sendKeys("keerthana");
            driver.findElement(By.id("email")).sendKeys("keerthivs126@gmail.com");
            driver.findElement(By.id("review")).sendKeys("good product");

            ScreenshotUtilities.capture(driver, "FilledForm_ReviewSection");

            // Step 6: Validate and click Submit button
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-review")));
            Assert.assertTrue(submitBtn.isDisplayed(), "Submit button is not visible");
            submitBtn.click();

            // Step 7: Wait for success message
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Thank you for your review.']")
            ));
            Assert.assertTrue(successMsg.isDisplayed(), "Review success message not displayed");

            ScreenshotUtilities.capture(driver, "AfterSubmit_ReviewSuccess");

            System.out.println("Review submitted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            ScreenshotUtilities.capture(driver, "Exception_ReviewSubmit");
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







