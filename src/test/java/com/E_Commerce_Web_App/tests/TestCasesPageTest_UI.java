package com.E_Commerce_Web_App.tests;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.TestCasesPage_UI;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class TestCasesPageTest_UI extends BaseTest {
    static String projectpath = System.getProperty("user.dir");
    TestCasesPage_UI testCasesPage;

    @Test(priority = 1)
    public void verifyLogoDisplayed() throws IOException {
        driver.get("https://automationexercise.com/");
        testCasesPage = new TestCasesPage_UI(driver);
        testCasesPage.clickTestCases();
        test = extent.createTest("Verify Logo 'Automation Engineer'");
        Assert.assertTrue(testCasesPage.isLogoDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LogoDisplayed"));
    }

    @Test(priority = 2)
    public void verifyProductsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Products Icon");
        Assert.assertTrue(testCasesPage.isProductsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ProductsIcon"));
    }

    @Test(priority = 3)
    public void verifyCartIconDisplayed() throws IOException {
        test = extent.createTest("Verify Cart Icon");
        Assert.assertTrue(testCasesPage.isCartIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "CartIcon"));
    }

    @Test(priority = 4)
    public void verifySignupLoginIconDisplayed() throws IOException {
        test = extent.createTest("Verify Signup/Login Icon");
        Assert.assertTrue(testCasesPage.isSignupLoginIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupLoginIcon"));
    }

    @Test(priority = 5)
    public void verifyVideoTutorialsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Video Tutorials Icon");
        Assert.assertTrue(testCasesPage.isVideoTutorialsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "VideoTutorialsIcon"));
    }

    @Test(priority = 6)
    public void verifyHomeIconDisplayed() throws IOException {
        test = extent.createTest("Verify Home Icon");
        Assert.assertTrue(testCasesPage.isHomeIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomeIcon"));
    }

    @Test(priority = 7)
    public void verifyContactUsIconDisplayed() throws IOException {
        test = extent.createTest("Verify Contact Us Icon");
        Assert.assertTrue(testCasesPage.isContactUsIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactUsIcon"));
    }

    @Test(priority = 8)
    public void verifyApiTestingIconDisplayed() throws IOException {
        test = extent.createTest("Verify API Testing Icon");
        Assert.assertTrue(testCasesPage.isApiTestingIconDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ApiTestingIcon"));
    }

    @Test(priority = 36)
    public void verifyFeedbackSectionDisplayed() throws IOException {
        test = extent.createTest("Verify Feedback Section");
        Assert.assertTrue(testCasesPage.isFeedbackSectionDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "FeedbackSection"));
    }

    @Test(priority = 37)
    public void verifyFeedbackEmailDisplayed() throws IOException {
        test = extent.createTest("Verify Feedback Email");
        Assert.assertTrue(testCasesPage.isFeedbackEmailDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "FeedbackEmail"));
    }

    @Test(priority = 9)
    public void verifyTestCasesSectionDisplayed() throws IOException {
        test = extent.createTest("Verify Test Cases Section");
        Assert.assertTrue(testCasesPage.isTestCasesSectionDisplayed());
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCasesSection"));
    }

    //Test Case Hyperlinks (1 → 26)
    @Test(priority = 10)
    public void verifyTestCase1LinkDisplayed() throws IOException {
    	testCasesPage = new TestCasesPage_UI(driver);
        test = extent.createTest("Verify Test Case 1 Link");
        Assert.assertTrue(testCasesPage.isTestCase1LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase1Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase1Link"));
    }

    @Test(priority = 11)
    public void verifyTestCase2LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 2 Link");
        Assert.assertTrue(testCasesPage.isTestCase2LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase2Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase2Link"));
    }

    @Test(priority = 12)
    public void verifyTestCase3LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 3 Link");
        Assert.assertTrue(testCasesPage.isTestCase3LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase3Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase3Link"));
    }
    
    @Test(priority = 13)
    public void verifyTestCase4LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 4 Link");
        Assert.assertTrue(testCasesPage.isTestCase4LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase4Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase4Link"));
    }
    
    @Test(priority = 14)
    public void verifyTestCase5LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 5 Link");
        Assert.assertTrue(testCasesPage.isTestCase5LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase5Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase5Link"));
    }
    
    @Test(priority = 15)
    public void verifyTestCase6LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 6 Link");
        Assert.assertTrue(testCasesPage.isTestCase6LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase6Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase6Link"));
    }
    
    @Test(priority = 16)
    public void verifyTestCase7LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 7 Link");
        Assert.assertTrue(testCasesPage.isTestCase7LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase7Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase7Link"));
    }
    
    @Test(priority = 17)
    public void verifyTestCase8LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 8 Link");
        Assert.assertTrue(testCasesPage.isTestCase8LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase8Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase8Link"));
    }
    
    @Test(priority = 18)
    public void verifyTestCase9LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 9 Link");
        Assert.assertTrue(testCasesPage.isTestCase9LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase9Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase9Link"));
    }
    
    @Test(priority = 19)
    public void verifyTestCase10LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 10 Link");
        Assert.assertTrue(testCasesPage.isTestCase10LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase10Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase10Link"));
    }
    
    @Test(priority = 20)
    public void verifyTestCase11LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 11 Link");
        Assert.assertTrue(testCasesPage.isTestCase11LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase11Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase11Link"));
    }
    
    @Test(priority = 21)
    public void verifyTestCase12LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 12 Link");
        Assert.assertTrue(testCasesPage.isTestCase12LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase12Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase12Link"));
    }
    
    @Test(priority = 22)
    public void verifyTestCase13LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 13 Link");
        Assert.assertTrue(testCasesPage.isTestCase13LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase13Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase13Link"));
    }
    
    @Test(priority = 23)
    public void verifyTestCase14LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 14 Link");
        Assert.assertTrue(testCasesPage.isTestCase14LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase14Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase14Link"));
    }
    
    @Test(priority = 24)
    public void verifyTestCase15LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 15 Link");
        Assert.assertTrue(testCasesPage.isTestCase15LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase15Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase15Link"));
    }
    
    @Test(priority = 25)
    public void verifyTestCase16LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 16 Link");
        Assert.assertTrue(testCasesPage.isTestCase16LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase16Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase16Link"));
    }
    
    @Test(priority = 26)
    public void verifyTestCase17LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 17 Link");
        Assert.assertTrue(testCasesPage.isTestCase17LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase17Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase17Link"));
    }
    
    @Test(priority = 27)
    public void verifyTestCase18LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 18 Link");
        Assert.assertTrue(testCasesPage.isTestCase18LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase18Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase18Link"));
    }
    
    @Test(priority = 28)
    public void verifyTestCase19LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 19 Link");
        Assert.assertTrue(testCasesPage.isTestCase19LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase19Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase19Link"));
    }
    
    @Test(priority = 29)
    public void verifyTestCase20LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 20 Link");
        Assert.assertTrue(testCasesPage.isTestCase20LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase20Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase20Link"));
    }
    
    @Test(priority = 30)
    public void verifyTestCase21LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 21 Link");
        Assert.assertTrue(testCasesPage.isTestCase21LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase21Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase21Link"));
    }
    
    @Test(priority = 31)
    public void verifyTestCase22LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 22 Link");
        Assert.assertTrue(testCasesPage.isTestCase22LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase22Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase22Link"));
    }
    
    @Test(priority = 32)
    public void verifyTestCase23LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 23 Link");
        Assert.assertTrue(testCasesPage.isTestCase23LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase23Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase23Link"));
    }
    
    @Test(priority = 33)
    public void verifyTestCase24LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 24 Link");
        Assert.assertTrue(testCasesPage.isTestCase24LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase24Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase24Link"));
    }
    
    @Test(priority = 34)
    public void verifyTestCase25LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 25 Link");
        Assert.assertTrue(testCasesPage.isTestCase25LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase25Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase25Link"));
    }

    @Test(priority = 35)
    public void verifyTestCase26LinkDisplayed() throws IOException {
        test = extent.createTest("Verify Test Case 26 Link");
        Assert.assertTrue(testCasesPage.isTestCase26LinkDisplayed());
        testCasesPage.scrollToElement(testCasesPage.testCase26Link);
        test.addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TestCase26Link"));
    }
    
    @Test(priority = 42)
    public void verifyScrollToTopButton() throws IOException {
        test = extent.createTest("Verify Scroll To Top Button");
    	testCasesPage = new TestCasesPage_UI(driver);
    	testCasesPage.scrollDown();
        testCasesPage.clickScrollToTop();
        test.pass("Scroll to top button working")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ScrollToTop"));
    }

    @Test(dataProvider = "subscriptionDataValid", priority = 41) 
    public void verifySubscriptionValid(String subscriptionEmail) throws IOException { 
    	test = extent.createTest("Verify Subscription with Valid Email"); 
        testCasesPage = new TestCasesPage_UI(driver);
        testCasesPage.scrollDown(); 
        testCasesPage.enterSubscription(subscriptionEmail); 
        testCasesPage.clickSubscriptionButton(); 
    	Assert.assertTrue(testCasesPage.isSubscriptionSuccessDisplayed(), "Subscription success message not displayed"); 
    	test.pass("Subscription working with valid email") 
    	    .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionValid"));
    }
    
    @Test(priority = 40, dataProvider = "subscriptionDataInvalid")
    public void verifySubscriptionInvalid(String invalidEmail) throws IOException,InterruptedException {
        test = extent.createTest("Verify Subscription with Invalid Email");
        testCasesPage = new TestCasesPage_UI(driver);
        testCasesPage.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        subscriptionField.sendKeys(invalidEmail);
        testCasesPage.clickSubscriptionButton();
        Thread.sleep(2000);

        String validationMessage = subscriptionField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.contains("Please include an '@'"),
                "Subscription accepted invalid email!");
        test.pass("Proper validation message displayed for invalid subscription email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionInvalid"));
    }
    
    @Test(priority = 39)
    public void verifySubscriptionEmpty() throws IOException {
        test = extent.createTest("Verify Subscription with Empty Email");
        testCasesPage = new TestCasesPage_UI(driver);
        testCasesPage.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        testCasesPage.clickSubscriptionButton();
        String validationMessage = subscriptionField.getAttribute("validationMessage");
        
        Assert.assertTrue(validationMessage.toLowerCase().contains("fill"),
                "Subscription accepted empty email! Validation: " + validationMessage);
        String screenshotPath = ScreenshotUtilities.capturescreen(driver, "SubscriptionEmpty");
        test.pass("Proper validation message displayed for empty subscription email: " + validationMessage)
            .addScreenCaptureFromPath(screenshotPath);
    }
    
    @Test(priority = 38)
    public void verifySubscriptionIconVisible() throws IOException {
        test = extent.createTest("Verify Subscription Icon is visible");
        testCasesPage = new TestCasesPage_UI(driver);
        testCasesPage.scrollDown();
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