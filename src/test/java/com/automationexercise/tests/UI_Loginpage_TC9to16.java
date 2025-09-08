package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Loginpage_TC9to16 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/LoginReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Login Page Validations - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1)
    public void validateLoginButtonPresence() {
        try {
            WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
            Assert.assertTrue(loginBtn.isDisplayed(), "Login button is not visible");
            test.pass(" Login button is present on Signup/Login page");
            captureScreenshot("LoginButton");
        } catch (Exception e) {
            test.fail(" Login button validation failed: " + e.getMessage());
            captureScreenshot("LoginButton_Failure");
        }
    }

    @Test(priority = 2)
    public void loginWithValidCredentials() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthana12003@gmail.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi@123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement logoutLink = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
            Assert.assertTrue(logoutLink.isDisplayed(), "Login failed");
            test.pass(" Login with valid credentials succeeded");
            captureScreenshot("LoginValid");
        } catch (Exception e) {
            test.fail(" Login with valid credentials failed: " + e.getMessage());
            captureScreenshot("LoginValid_Failure");
        }
    }

    @Test(priority = 3)
    public void loginWithInvalidCredentials() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthana@gmil.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown");
            test.pass(" Invalid credentials triggered error message");
            captureScreenshot("LoginInvalid");
        } catch (Exception e) {
            test.fail(" Login with invalid credentials failed: " + e.getMessage());
            captureScreenshot("LoginInvalid_Failure");
        }
    }

    @Test(priority = 4)
    public void loginWithValidEmailInvalidPassword() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("Keerthana12003@gmail.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown");
            test.pass(" Valid email with invalid password triggered error");
            captureScreenshot("LoginValidEmailInvalidPassword");
        } catch (Exception e) {
            test.fail(" Login with valid email and invalid password failed: " + e.getMessage());
            captureScreenshot("LoginValidEmailInvalidPassword_Failure");
        }
    }

    @Test(priority = 5)
    public void loginWithInvalidEmailValidPassword() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthana@gmil.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi@123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown");
            test.pass(" Invalid email with valid password triggered error");
            captureScreenshot("LoginInvalidEmailValidPassword");
        } catch (Exception e) {
            test.fail(" Login with invalid email and valid password failed: " + e.getMessage());
            captureScreenshot("LoginInvalidEmailValidPassword_Failure");
        }
    }

    @Test(priority = 6)
    public void loginWithNoEmailNoPassword() {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
            String validationMessage = emailField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing email");
            test.pass(" Alert shown for missing email and password");
            captureScreenshot("LoginNoEmailNoPassword");
        } catch (Exception e) {
            test.fail(" Login with no email and password failed: " + e.getMessage());
            captureScreenshot("LoginNoEmailNoPassword_Failure");
        }
    }

    @Test(priority = 7)
    public void loginWithNoEmailValidPassword() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi@123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
            String validationMessage = emailField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing email");
            test.pass(" Alert shown for missing email");
            captureScreenshot("LoginNoEmailValidPassword");
        } catch (Exception e) {
            test.fail(" Login with no email failed: " + e.getMessage());
            captureScreenshot("LoginNoEmailValidPassword_Failure");
        }
    }

    @Test(priority = 8)
    public void loginWithValidEmailNoPassword() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthana12003@gmil.com");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
            String validationMessage = passwordField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing password");
            test.pass(" Alert shown for missing password");
            captureScreenshot("LoginValidEmailNoPassword");
        } catch (Exception e) {
            test.fail(" Login with valid email and no password failed: " + e.getMessage());
            captureScreenshot("LoginValidEmailNoPassword_Failure");
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            test.info("📸 Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            test.warning("⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

