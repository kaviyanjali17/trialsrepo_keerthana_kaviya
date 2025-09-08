package com.automationexercise.tests;

import org.testng.annotations.Test;


import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Based_impact_TC003;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;
import java.time.Duration;

public class Based_impact_TC03_Testng extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "loginData")
    public void verifyLoginButton(String label, String username, String password) {
        ExtentReports extent = ExtentManager.createInstance("LoginReport.html");
        ExtentTest test = extent.createTest("Login Test: " + label);

        try {
            driver.get("https://automationexercise.com/");
            Based_impact_TC003 page = new Based_impact_TC003(driver);

            test.info("Navigated to homepage");

            page.clickSignupLogin();
            test.info("Clicked on Signup/Login");

            page.enterLoginEmail(username);
            test.info("Entered email: " + username);

            page.enterLoginPassword(password);
            test.info("Entered password");

            page.clickLoginButton();
            test.info("Clicked Login");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            Assert.assertTrue(logoutBtn.isDisplayed(), "Login failed — Logout button not visible");

            test.pass("Login button is working and user navigated to account page");


            
        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label);
            test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\automationexercise_Testdata\\Based_impact_data.xlsx", "Sheet2");
    }
}

