package com.automationexercise.tests;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.automationexercise.utilities.ScreenshotUtilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.Duration;

public class Based_impact_TC04_Testng {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Setup Extent Report
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/LogoutButtonReport_TC04.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("TC04 - Logout Button Functionality");
    }

    @Test
    public void verifyLogoutButtonWorks() {
        try {
            driver.get("https://automationexercise.com");

            // Wait for full page load
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
            );

            // Confirm homepage loaded
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));
            test.info("Opened homepage");

            // Step 1: Click on Signup/Login
            WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Signup / Login']")));
            loginLink.click();
            test.info("Navigated to Login page");
         
            // Step 2: Enter login credentials
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
            WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
            WebElement loginBtn = driver.findElement(By.xpath("//button[@data-qa='login-button']"));

            emailField.sendKeys("keerthivs126@gmail.com");
            passwordField.sendKeys("Keerthi@123"); // Replace with your valid password
            loginBtn.click();
            test.info("Submitted login credentials");

            // Step 3: Wait for login confirmation
            WebElement loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as')]")
            ));
            Assert.assertTrue(loggedInText.isDisplayed(), "Login confirmation not displayed");

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loggedInText);
            Thread.sleep(1000);
            String loginShot = ScreenshotUtilities.capture(driver, "AfterLogin_TC04");
            test.info("After successful login").addScreenCaptureFromPath(loginShot);

            // Step 4: Click Logout
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Logout']")));
            logoutBtn.click();
            test.info("Clicked logout button");

            // Step 5: Validate redirection to login page
            WebElement loginHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Login to your account']")
            ));
            Assert.assertTrue(loginHeader.isDisplayed(), "User not redirected to login page after logout");

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginHeader);
            Thread.sleep(1000);
            String logoutShot = ScreenshotUtilities.capture(driver, "AfterLogout_TC04");
            test.info("After successful logout").addScreenCaptureFromPath(logoutShot);

            test.pass("User successfully logged out and redirected to login page");

        } catch (Exception e) {
            e.printStackTrace();
            String errorShot = ScreenshotUtilities.capture(driver, "Exception_Logout_TC04");
            test.fail("Test failed due to exception").addScreenCaptureFromPath(errorShot);
            Assert.fail("❌ Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // Generate the report
    }
}

