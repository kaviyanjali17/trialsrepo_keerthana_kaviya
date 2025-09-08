package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Based_impact_TC10;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;

public class Based_impact_TC010 extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "contactUsData")
    public void verifySubmitButton(String label, String name, String email, String message) {
        ExtentReports extent = ExtentManager.createInstance("ContactUsSubmitReport.html");
        ExtentTest test = extent.createTest("Contact Us Submit Test: " + label);

        try {
            driver.get("https://automationexercise.com/");
            Based_impact_TC10 page = new Based_impact_TC10(driver);

            page.navigateToContactUs();
            test.info("Navigated to Contact Us page");

            page.enterName(name);
            page.enterEmail(email);
            page.enterMessage(message);
            test.info("Entered contact form details");

            //  Screenshot before submission
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_BeforeSubmit"));

            page.clickSubmit();
            test.info("Clicked Submit button");

            //  Handle alert
            try {
                Alert alert = driver.switchTo().alert();
                test.info("Alert appeared with message: " + alert.getText());
                alert.accept();
                test.info("Alert accepted");
            } catch (NoAlertPresentException e) {
                test.warning("No alert appeared after submission");
            }

            //  Screenshot after submission
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_AfterSubmit"));

            //  Validate success message
            if (page.isSuccessMessageVisible()) {
                test.pass("Contact Us form submitted successfully");
            } else {
                test.fail("Success message not displayed after submission");
                test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_SubmitFailed"));
                Assert.fail("Form submission failed");
            }

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label + "_Exception");
            test.fail("Exception: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            throw new RuntimeException("Test failed due to exception", e);
        }

        extent.flush();
    }

    @DataProvider(name = "contactUsData")
    public Object[][] contactUsData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\automationexercise_Testdata\\Based_impact_data.xlsx", "Sheet9");
    }
}

