package com.automationexercise.tests;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Pageobject;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;

public class Corebusiness_testcase_7to9 extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "testdata")
    public void runTest(String label, String name, String email, String message) {
        ExtentReports extent = ExtentManager.createInstance("CombinedReport.html");
        ExtentTest test = extent.createTest("Test Case: " + label);

        try {
            driver.get("https://automationexercise.com/");
            Pageobject page = new Pageobject(driver);

            if (label.contains("Add to Cart")) {
                page.goToProductsPage();
                Assert.assertTrue(page.isAllProductsVisible(), "ALL PRODUCTS not visible");
                page.clickAddToCart();
                test.pass("Add to cart button clicked successfully");
            }

            if (label.contains("Contact Form")) {
                page.goToContactPage();
                page.fillContactForm(name, email, message);
                test.pass("Contact form filled successfully");
            }

            if (label.contains("Submit")) {
                page.goToContactPage();
                page.fillContactForm(name, email, message);
                page.clickSubmit();
                test.pass("Submit button clicked successfully");
            }

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label);
            test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();
    }

    @DataProvider(name = "testdata")
    public Object[][] testdata() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\automationexercise_Testdata\\data.xlsx", "Sheet1");
    }
}




