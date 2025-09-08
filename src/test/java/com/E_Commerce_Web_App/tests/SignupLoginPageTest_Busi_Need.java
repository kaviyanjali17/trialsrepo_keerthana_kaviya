package com.E_Commerce_Web_App.tests;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.HomePage_Busi_Need;
import com.E_Commerce_Web_App.pages.SignupLoginPage_Busi_Need;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class SignupLoginPageTest_Busi_Need extends BaseTest {

    static String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "signupData")
    public void verifySignup(String name, String email) throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Signup with Name: " + name + " | Email: " + email);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickSignupLogin();

        SignupLoginPage_Busi_Need signupLogin = new SignupLoginPage_Busi_Need(driver);
        signupLogin.signup(name, email);

        if (driver.getCurrentUrl().contains("signup")) {
            test.pass("Signup navigation successful for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupSuccess"));
        } else {
            test.fail("Signup failed for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupFail"));
        }
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String email, String password) throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Login with Email: " + email);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickSignupLogin();

        SignupLoginPage_Busi_Need signupLogin = new SignupLoginPage_Busi_Need(driver);
        signupLogin.login(email, password);

        if (signupLogin.isLogoutVisible()) {
            test.pass("Login successful for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LoginSuccess"));
        } else {
            test.fail("Login failed for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LoginFail"));
        }
    }

    @Test(dataProvider = "loginData")
    public void verifyLogout(String email, String password) throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Logout flow validation for Email: " + email);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickSignupLogin();

        SignupLoginPage_Busi_Need signupLogin = new SignupLoginPage_Busi_Need(driver);
        signupLogin.login(email, password);

        if (signupLogin.isLogoutVisible()) {
            signupLogin.logout();

            // Check that user is redirected back to login/signup page
            if (driver.getCurrentUrl().contains("login")) {
                test.pass("Logout successful, redirected to login page for email: " + email)
                    .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LogoutSuccess"));
            } else {
                test.fail("Logout click happened but did not return to login page for email: " + email)
                    .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LogoutFail"));
            }
        } else {
            test.fail("Logout button not found after login for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LogoutFail_NoButton"));
        }
    }

    @DataProvider
    public Object[][] signupData() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx","Signup");
    }

    @DataProvider
    public Object[][] loginData() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx","Login");
    }
}
