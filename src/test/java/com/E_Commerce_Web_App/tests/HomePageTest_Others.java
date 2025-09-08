package com.E_Commerce_Web_App.tests;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.HomePage_Others;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class HomePageTest_Others extends BaseTest {

    @Test
    public void verifyApplicationURL() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Application URL opens in Browser");

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://automationexercise.com/", "URL did not match");

        test.pass("Application URL opened successfully: " + currentURL)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomePageURL"));
    }

    @Test
    public void verifyRecommendedProductsSection() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Recommended Products Section");

        HomePage_Others homePage = new HomePage_Others(driver);

        if (homePage.isRecommendedProductsDisplayed()) {
            // Scroll to the recommended section before screenshot
            WebElement recommendedSection = driver.findElement(By.xpath("//h2[text()='recommended items']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", recommendedSection);

            test.pass("Recommended Products section is displayed on Home Page")
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "RecommendedProducts"));
        } else {
            test.fail("Recommended Products section is NOT displayed on Home Page")
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "RecommendedProductsFail"));
        }
    }
}
