package com.E_Commerce_Web_App.tests;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.ContactUsPage_Busi_Need;
import com.E_Commerce_Web_App.pages.HomePage_Busi_Need;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class ContactUsPageTest_Busi_Need extends BaseTest {

    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "contactData")
    public void verifyContactUsForm(String name, String email, String subject, String message) throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Contact Us form submission for: " + name);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickContactUs();

        ContactUsPage_Busi_Need contactUs = new ContactUsPage_Busi_Need(driver);
        contactUs.enterContactDetails(name, email, subject, message);
        contactUs.clickSubmit();

        if (contactUs.isSubmissionSuccessful()) {
            test.pass("Contact form submitted successfully")
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactFormSuccess"));
        } else {
            test.fail("Contact form submission failed")
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ContactFormFail"));
        }
    }

    @DataProvider
    public Object[][] contactData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx","ContactUs");
    }
}
