package com.E_Commerce_Web_App.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.ContactUsPage_Func;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class ContactUsPageTest_Func extends BaseTest {
    static String projectpath = System.getProperty("user.dir");
    ContactUsPage_Func contact;

    @Test(priority = 1)
    public void verifyBrowserLaunch() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Verify Browser Launch");
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        test.pass("Browser launched successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "BrowserLaunch"));
    }

    @Test(priority = 2)
    public void verifyContactUsIcon() throws IOException {
        test = extent.createTest("Verify Contact Us Icon");
        contact = new ContactUsPage_Func(driver);
        contact.clickContactUs();
        Assert.assertTrue(contact.isNameFieldDisplayed(), "Contact Us form not visible");
        test.pass("Contact Us icon working and form displayed")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactUsPage"));
    }

    @Test(dataProvider = "contactData1", priority = 3)
    public void enterName(String name) throws IOException {
        test = extent.createTest("Enter Name in Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.enterName(name);
        test.pass("Name entered successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "EnterName"));
    }

    @Test(dataProvider = "contactData2", priority = 9)
    public void enterEmail(String email) throws IOException {
        test = extent.createTest("Enter Email in Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.enterEmail(email);
        test.pass("Email entered successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "EnterEmail"));
    }

    @Test(dataProvider = "contactData3", priority = 4)
    public void enterSubject(String subject) throws IOException {
        test = extent.createTest("Enter Subject in Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.enterSubject(subject);
        test.pass("Subject entered successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "EnterSubject"));
    }

    @Test(dataProvider = "contactData4", priority = 5)
    public void enterMessage(String message) throws IOException {
        test = extent.createTest("Enter Message in Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.enterMessage(message);
        test.pass("Message entered successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "EnterMessage"));
    }

    @Test(priority = 6)
    public void uploadFile() throws IOException {
        test = extent.createTest("Upload File in Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.uploadFile(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\sample.txt");
        test.pass("File uploaded successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "UploadFile"));
    }

    @Test(priority = 10)
    public void submitForm() throws IOException {
        test = extent.createTest("Submit Contact Form");
        contact = new ContactUsPage_Func(driver);
        contact.clickSubmit();

        driver.switchTo().alert().accept(); 

        Assert.assertTrue(contact.isSuccessMessageDisplayed(), "Success message not displayed");
        test.pass("Form submitted successfully and success message displayed")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubmitForm"));
    }

    @Test(dataProvider = "emptyEmailData", priority = 8)
    public void verifyEmailEmpty(String name, String subject, String message) throws IOException {
        test = extent.createTest("Verify Contact Form with Empty Email");
        contact = new ContactUsPage_Func(driver);
        contact.enterName(name);
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear(); 
        contact.enterSubject(subject);
        contact.enterMessage(message);
        contact.uploadFile(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\sample.txt");
        contact.clickSubmit();

        String validationMessage = emailField.getAttribute("validationMessage");

        Assert.assertTrue(validationMessage.toLowerCase().contains("fill"),
                "Form submitted without email! Validation: " + validationMessage);
        String screenshotPath = ScreenshotUtilities.capturescreen(driver, "EmailEmpty");
        test.pass("Proper validation message displayed for empty email: " + validationMessage)
            .addScreenCaptureFromPath(screenshotPath);
    }

    
    @Test(dataProvider = "invalidEmailContactData", priority = 7)
    public void verifyEmailInvalid(String name, String invalidEmail, String subject, String message) throws IOException {
        test = extent.createTest("Verify Contact Form with Invalid Email");
        contact = new ContactUsPage_Func(driver);
        contact.enterName(name);
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys(invalidEmail);
        contact.enterSubject(subject);
        contact.enterMessage(message);
        contact.uploadFile(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\sample.txt");
        contact.clickSubmit();

        String validationMessage = emailField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.contains("Please include an '@'"),
                "Form submitted with invalid email!");
        test.pass("Proper validation message displayed for invalid email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "EmailInvalid"));
    }
    
    @Test(dataProvider = "subscriptionDataValid", priority = 11) 
    public void verifySubscriptionValid(String subscriptionEmail) throws IOException { 
    	test = extent.createTest("Verify Subscription with Valid Email"); 
    	contact = new ContactUsPage_Func(driver); 
    	contact.scrollDown(); 
    	contact.enterSubscription(subscriptionEmail); 
    	contact.clickSubscriptionButton(); 
    	Assert.assertTrue(contact.isSubscriptionSuccessDisplayed(), "Subscription success message not displayed"); 
    	test.pass("Subscription working with valid email") 
    	    .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionValid"));
    }
    
    @Test(priority = 12, dataProvider = "subscriptionDataInvalid")
    public void verifySubscriptionInvalid(String invalidEmail) throws IOException {
        test = extent.createTest("Verify Subscription with Invalid Email");
        contact = new ContactUsPage_Func(driver);
        contact.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        subscriptionField.sendKeys(invalidEmail);
        contact.clickSubscriptionButton();

        String validationMessage = subscriptionField.getAttribute("validationMessage");
        Assert.assertTrue(validationMessage.contains("Please include an '@'"),
                "Subscription accepted invalid email!");
        test.pass("Proper validation message displayed for invalid subscription email: " + validationMessage)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SubscriptionInvalid"));
    }
    
    @Test(priority = 13)
    public void verifySubscriptionEmpty() throws IOException {
        test = extent.createTest("Verify Subscription with Empty Email");
        contact = new ContactUsPage_Func(driver);
        contact.scrollDown();
        WebElement subscriptionField = driver.findElement(By.id("susbscribe_email"));
        subscriptionField.clear();
        contact.clickSubscriptionButton();
        String validationMessage = subscriptionField.getAttribute("validationMessage");
        
        Assert.assertTrue(validationMessage.toLowerCase().contains("fill"),
                "Subscription accepted empty email! Validation: " + validationMessage);
        String screenshotPath = ScreenshotUtilities.capturescreen(driver, "SubscriptionEmpty");
        test.pass("Proper validation message displayed for empty subscription email: " + validationMessage)
            .addScreenCaptureFromPath(screenshotPath);
    }

    @Test(priority = 14)
    public void verifyNavigationBackToHome() throws IOException {
        test = extent.createTest("Verify Navigation to Home");
        contact = new ContactUsPage_Func(driver);
        contact.clickHomeButton();
        Assert.assertTrue(driver.findElement(By.linkText("Signup / Login")).isDisplayed(), "Home page not displayed");
        test.pass("Navigated back to Home Page")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomePage"));
    }

    @Test(priority = 15)
    public void verifyScrollToTopButton() throws IOException {
        test = extent.createTest("Verify Scroll To Top Button");
        contact = new ContactUsPage_Func(driver);
        contact.scrollDown();
        contact.clickScrollToTop();
        test.pass("Scroll to top button working")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ScrollToTop"));
    }

    @DataProvider
    public Object[][] contactData1() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "Name");
    }

    @DataProvider
    public Object[][] contactData2() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "Email");
    }

    @DataProvider
    public Object[][] contactData3() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "Subject");
    }

    @DataProvider
    public Object[][] contactData4() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "Message");
    }

    @DataProvider
    public Object[][] invalidEmailContactData() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "InvalidEmailContact");
    }

    @DataProvider
    public Object[][] subscriptionDataValid() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "SubscriptionValid");
    }

    @DataProvider
    public Object[][] subscriptionDataInvalid() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "SubscriptionInvalid");
    }
    
    @DataProvider
    public Object[][] emptyEmailData() throws IOException {
        return ExcelUtilities.getdata(
            projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile3.xlsx", "EmptyEmailData");
    }
}
