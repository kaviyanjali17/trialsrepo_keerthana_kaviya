package com.automationexercise.tests;



import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Based_impact_TC009;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;
import java.time.Duration;

public class Based_impact_TC09 extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "contactUsData")
    public void verifyContactUsForm(String label, String name, String email, String message) {
        ExtentReports extent = ExtentManager.createInstance("ContactUsReport.html");
        ExtentTest test = extent.createTest("Contact Us Test: " + label);

        try {
        	Based_impact_TC009 page = new Based_impact_TC009(driver);
        	


            page.clickContactUsLink();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));


            test.info("Clicked on Contact Us");

            page.enterName(name);
            test.info("Entered name: " + name);

            page.enterEmail(email);
            test.info("Entered email: " + email);

            page.enterMessage(message);
            test.info("Entered message");

            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_ContactFormFilled"));


            page.clickSubmit();
            test.info("Clicked Submit");

            if (page.isSuccessMessageVisible()) {
                test.pass("Contact Us form submitted successfully");
                test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_ContactFormSuccess"));
            } else {
                test.fail("Success message not visible after submission");
                test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_NoSuccessMessage"));
            }


        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label + "_Error");
            test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();
    }

    @DataProvider(name = "contactUsData")
    public Object[][] contactUsData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\automationexercise_Testdata\\Based_impact_data.xlsx", "Sheet8");
    }
}

