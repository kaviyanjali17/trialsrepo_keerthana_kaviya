package com.E_Commerce_Web_App.tests;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.HomePage_Busi_Need;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class HomePageTest_Busi_Need extends BaseTest {

    static String projectpath = System.getProperty("user.dir");

    @Test
    public void verifyNavigationLinks() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Home Page Navigation Links");

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);

        home.clickProducts();
        test.pass("Products link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ProductsLink"));
        driver.navigate().back();

        home.clickSignupLogin();
        test.pass("Signup/Login link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupLoginLink"));
        driver.navigate().back();

        home.clickContactUs();
        test.pass("Contact Us link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactUsLink"));
        driver.navigate().back();

        home.clickCart();
        test.pass("Cart link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "CartLink"));
        driver.navigate().back();

        home.clickVideoTutorials();
        test.pass("Video Tutorials link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "VideoTutorialsLink"));
        driver.navigate().back();

        home.clickTestCases();
        test.pass("Test Cases link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCasesLink"));
        driver.navigate().back();

        home.clickApiTesting();
        test.pass("API Testing link clicked successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiTestingLink"));
    }

    @Test(dataProvider = "subscriptionData")
    public void verifySubscription(String email) throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Subscription with email: " + email);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);

        home.subscribe(email);

        if (home.isSubscriptionSuccessful()) {
            test.pass("Subscription successful with email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionPass"));
        } else {
            test.fail("Subscription failed with email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionFail"));
        }
    }

    @DataProvider
    public Object[][] subscriptionData() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx", "Subscription");
    }
}
