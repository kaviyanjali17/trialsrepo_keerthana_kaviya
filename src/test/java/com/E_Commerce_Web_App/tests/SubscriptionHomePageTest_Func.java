package com.E_Commerce_Web_App.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.SubscriptionHomePage_Func;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class SubscriptionHomePageTest_Func extends BaseTest {

    static String projectpath = System.getProperty("user.dir");
    SubscriptionHomePage_Func subscriptionPage;

    @Test(priority = 1)
    public void verifySubscriptionIconVisible() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Subscription Icon on Home Page");

        subscriptionPage = new SubscriptionHomePage_Func(driver);
        subscriptionPage.scrollDown();

        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        Assert.assertTrue(subscriptionField.isDisplayed(), "Subscription field not visible!");

        test.pass("Subscription icon is visible on Home Page")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionIcon"));
    }

    @Test(priority = 2)
    public void verifySubscriptionEmpty() throws IOException {
        test = extent.createTest("Verify Subscription with Empty Email");

        subscriptionPage = new SubscriptionHomePage_Func(driver);
        subscriptionPage.scrollDown();

        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        subscriptionPage.clickSubscriptionButton();

        String validationMessage = subscriptionField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.toLowerCase().contains("fill"),
                "Subscription accepted empty email! Validation: " + validationMessage);

        test.pass("Proper validation shown for empty subscription email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionEmpty"));
    }

    @Test(priority = 4, dataProvider = "subscriptionDataValid")
    public void verifySubscriptionValid(String validEmail) throws IOException {
        test = extent.createTest("Verify Subscription with Valid Email");

        subscriptionPage = new SubscriptionHomePage_Func(driver);
        subscriptionPage.scrollDown();
        subscriptionPage.enterSubscription(validEmail);
        subscriptionPage.clickSubscriptionButton();

        Assert.assertTrue(subscriptionPage.isSubscriptionSuccessDisplayed(),
                "Subscription success message not displayed for valid email!");

        test.pass("Subscription worked successfully with valid email: " + validEmail)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionValid"));
    }

    @Test(priority = 3, dataProvider = "subscriptionDataInvalid")
    public void verifySubscriptionInvalid(String invalidEmail) throws IOException, InterruptedException {
        test = extent.createTest("Verify Subscription with Invalid Email");

        subscriptionPage = new SubscriptionHomePage_Func(driver);
        subscriptionPage.scrollDown();

        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        subscriptionField.sendKeys(invalidEmail);
        subscriptionPage.clickSubscriptionButton();
        Thread.sleep(2000);

        String validationMessage = subscriptionField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.contains("@"),
                "Subscription accepted invalid email! Validation: " + validationMessage);

        test.pass("Proper validation shown for invalid email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionInvalid"));
    }

    @DataProvider
    public Object[][] subscriptionDataValid() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile4.xlsx", "ValidEmail");
    }

    @DataProvider
    public Object[][] subscriptionDataInvalid() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile4.xlsx", "InvalidEmail");
    }
}
