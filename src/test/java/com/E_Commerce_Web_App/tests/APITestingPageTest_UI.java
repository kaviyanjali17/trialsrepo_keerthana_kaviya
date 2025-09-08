package com.E_Commerce_Web_App.tests;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.APITestingPage_UI;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class APITestingPageTest_UI extends BaseTest {
    static String projectpath = System.getProperty("user.dir");
    APITestingPage_UI apiTestingPage;

    @Test(priority = 1)
    public void verifyLogoDisplayed() throws IOException {
        driver.get("https://automationexercise.com/");
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.clickApiTesting();
        test = extent.createTest("Verify Logo 'Automation Engineer'");
        Assert.assertTrue(apiTestingPage.isLogoDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LogoDisplayed"));
    }

    @Test(priority = 2)
    public void verifyProductsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Products Icon");
        Assert.assertTrue(apiTestingPage.isProductsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ProductsIcon"));
    }

    @Test(priority = 3)
    public void verifyCartIconDisplayed() throws IOException {
        test = extent.createTest("Verify Cart Icon");
        Assert.assertTrue(apiTestingPage.isCartIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "CartIcon"));
    }

    @Test(priority = 4)
    public void verifySignupLoginIconDisplayed() throws IOException {
        test = extent.createTest("Verify Signup/Login Icon");
        Assert.assertTrue(apiTestingPage.isSignupLoginIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupLoginIcon"));
    }

    @Test(priority = 5)
    public void verifyVideoTutorialsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Video Tutorials Icon");
        Assert.assertTrue(apiTestingPage.isVideoTutorialsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "VideoTutorialsIcon"));
    }

    @Test(priority = 6)
    public void verifyHomeIconDisplayed() throws IOException {
        test = extent.createTest("Verify Home Icon");
        Assert.assertTrue(apiTestingPage.isHomeIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomeIcon"));
    }

    @Test(priority = 7)
    public void verifyContactUsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Contact Us Icon");
        Assert.assertTrue(apiTestingPage.isContactUsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactUsIcon"));
    }

    @Test(priority = 8)
    public void verifyApiTestingIconDisplayed() throws IOException {
        test = extent.createTest("Verify API Testing Icon");
        Assert.assertTrue(apiTestingPage.isApiTestingIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiTestingIcon"));
    }

    @Test(priority = 24)
    public void verifyFeedbackSectionDisplayed() throws IOException {
        test = extent.createTest("Verify Feedback Section");
        Assert.assertTrue(apiTestingPage.isFeedbackSectionDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "FeedbackSection"));
    }

    @Test(priority = 25)
    public void verifyFeedbackEmailDisplayed() throws IOException {
        test = extent.createTest("Verify Feedback Email");
        Assert.assertTrue(apiTestingPage.isFeedbackEmailDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "FeedbackEmail"));
    }
    
    @Test(priority = 9)
    public void verifyApilistSectionDisplayed() throws IOException {
        test = extent.createTest("Verify API List Section");
        Assert.assertTrue(apiTestingPage.isTestCasesSectionDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiListSection"));
    }
    
    //API List Hyperlinks (1 → 14)
    @Test(priority = 10)
    public void verifyApiList1LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 1 Link");
        apiTestingPage = new APITestingPage_UI(driver);
        Assert.assertTrue(apiTestingPage.isApiList1LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList1Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList1Link"));
    }

    @Test(priority = 11)
    public void verifyApiList2LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 2 Link");
        Assert.assertTrue(apiTestingPage.isApiList2LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList2Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList2Link"));
    }

    @Test(priority = 12)
    public void verifyApiList3LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 3 Link");
        Assert.assertTrue(apiTestingPage.isApiList3LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList3Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList3Link"));
    }
    
    @Test(priority = 13)
    public void verifyApiList4LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 4 Link");
        Assert.assertTrue(apiTestingPage.isApiList4LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList4Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList4Link"));
    }
    
    @Test(priority = 14)
    public void verifyApiList5LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 5 Link");
        Assert.assertTrue(apiTestingPage.isApiList5LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList5Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList5Link"));
    }
    
    @Test(priority = 15)
    public void verifyApiList6LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 6 Link");
        Assert.assertTrue(apiTestingPage.isApiList6LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList6Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList6Link"));
    }
    
    @Test(priority = 16)
    public void verifyApiList7LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 7 Link");
        Assert.assertTrue(apiTestingPage.isApiList7LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList7Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList7Link"));
    }
    
    @Test(priority = 17)
    public void verifyApiList8LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 8 Link");
        Assert.assertTrue(apiTestingPage.isApiList8LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList8Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList8Link"));
    }
    
    @Test(priority = 18)
    public void verifyApiList9LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 9 Link");
        Assert.assertTrue(apiTestingPage.isApiList9LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList9Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList9Link"));
    }
    
    @Test(priority = 19)
    public void verifyApiList10LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 10 Link");
        Assert.assertTrue(apiTestingPage.isApiList10LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList10Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList10Link"));
    }
    
    @Test(priority = 20)
    public void verifyApiList11LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 11 Link");
        Assert.assertTrue(apiTestingPage.isApiList11LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList11Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList11Link"));
    }
    
    @Test(priority = 21)
    public void verifyApiList12LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 12 Link");
        Assert.assertTrue(apiTestingPage.isApiList12LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList12Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList12Link"));
    }
    
    @Test(priority = 22)
    public void verifyApiList13LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 13 Link");
        Assert.assertTrue(apiTestingPage.isApiList13LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList13Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList13Link"));
    }
    
    @Test(priority = 23)
    public void verifyApiList14LinkDisplayed() throws IOException {
        test = extent.createTest("Verify API List 14 Link");
        Assert.assertTrue(apiTestingPage.isApiList14LinkDisplayed());
        apiTestingPage.scrollToElement(apiTestingPage.ApiList14Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiList14Link"));
    }
    
    @Test(priority = 30)
    public void verifyScrollToTopButton() throws IOException {
        test = extent.createTest("Verify Scroll To Top Button");
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.scrollDown();
        apiTestingPage.clickScrollToTop();
        test.pass("Scroll to top button working")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ScrollToTop"));
    }

    @Test(dataProvider = "subscriptionDataValid", priority = 29) 
    public void verifySubscriptionValid(String subscriptionEmail) throws IOException { 
        test = extent.createTest("Verify Subscription with Valid Email"); 
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.scrollDown(); 
        apiTestingPage.enterSubscription(subscriptionEmail); 
        apiTestingPage.clickSubscriptionButton(); 
        Assert.assertTrue(apiTestingPage.isSubscriptionSuccessDisplayed(), "Subscription success message not displayed"); 
        test.pass("Subscription working with valid email") 
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionValid"));
    }
    
    @Test(priority = 28, dataProvider = "subscriptionDataInvalid")
    public void verifySubscriptionInvalid(String invalidEmail) throws IOException, InterruptedException {
        test = extent.createTest("Verify Subscription with Invalid Email");
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        subscriptionField.sendKeys(invalidEmail);
        apiTestingPage.clickSubscriptionButton();
        Thread.sleep(2000);

        String validationMessage = subscriptionField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.contains("Please include an '@'"),
                "Subscription accepted invalid email!");
        test.pass("Proper validation message displayed for invalid subscription email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionInvalid"));
    }
    
    @Test(priority = 27)
    public void verifySubscriptionEmpty() throws IOException {
        test = extent.createTest("Verify Subscription with Empty Email");
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        apiTestingPage.clickSubscriptionButton();
        String validationMessage = subscriptionField.getAttribute("validationMessage");
        
        Assert.assertTrue(validationMessage.toLowerCase().contains("fill"),
                "Subscription accepted empty email! Validation: " + validationMessage);
        String screenshotPath = ScreenshotUtilities.capturescreen(driver, "SubscriptionEmpty");
        test.pass("Proper validation message displayed for empty subscription email: " + validationMessage)
            .addScreenCaptureFromPath(screenshotPath);
    }
    
    @Test(priority = 26)
    public void verifySubscriptionIconVisible() throws IOException {
        test = extent.createTest("Verify Subscription Icon is visible");
        apiTestingPage = new APITestingPage_UI(driver);
        apiTestingPage.scrollDown();
        WebElement subscriptionInput = driver.findElement(By.id("susbscribe_email"));
        
        Assert.assertTrue(subscriptionInput.isDisplayed(), "Subscription field not visible!");
        test.pass("Subscription icon is visible")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionIcon"));
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