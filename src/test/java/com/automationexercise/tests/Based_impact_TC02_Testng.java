package com.automationexercise.tests;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Based_impact_TC002;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;

public class Based_impact_TC02_Testng extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "signupData")
    public void verifySignupButton(String label, String name, String email) {
        ExtentReports extent = ExtentManager.createInstance("SignupReport.html");
        ExtentTest test = extent.createTest("Signup Test: " + label);

        try {
            driver.get("https://automationexercise.com/");
            Based_impact_TC002 page = new Based_impact_TC002(driver);

            test.info("Navigated to homepage");

            page.clickSignupLogin();
            test.info("Clicked on Signup/Login");

            page.enterSignupName(name);
            test.info("Entered name: " + name);

            page.enterSignupEmail(email);
            test.info("Entered email: " + email);

            page.clickSignupButton();
            test.info("Clicked Signup");

            Assert.assertTrue(driver.getCurrentUrl().contains("signup"), "Did not navigate to account creation");
            test.pass("User successfully navigated to account creation");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label);
            test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();
    }

    @DataProvider(name = "signupData")
    public Object[][] signupData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\automationexercise_Testdata\\Based_impact_data.xlsx", "Sheet1");
    }
}


