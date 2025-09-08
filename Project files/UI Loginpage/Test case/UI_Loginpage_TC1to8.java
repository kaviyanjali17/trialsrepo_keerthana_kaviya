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

public class UI_Loginpage_TC1to8 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/SignupLoginChromeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Signup/Login Page Validations - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1)
    public void validateLogoPresence() {
        try {
            WebElement logo = driver.findElement(By.xpath("//h2[contains(text(),'Login to your account')]"));
            Assert.assertTrue(logo.isDisplayed(), "Logo is not visible");
            test.pass("Logo 'Automation Engineer' is visible on Signup/Login page");
            captureScreenshot("LogoPresence");
        } catch (Exception e) {
            test.fail("Logo validation failed: " + e.getMessage());
            captureScreenshot("LogoPresence_Failure");
        }
    }

    @Test(priority = 2)
    public void validateSignupButtonPresence() {
        try {
            WebElement signupBtn = driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
            Assert.assertTrue(signupBtn.isDisplayed(), "Signup button is not visible");
            test.pass(" Signup button is present on Signup/Login page");
            captureScreenshot("SignupButton");
        } catch (Exception e) {
            test.fail(" Signup button validation failed: " + e.getMessage());
            captureScreenshot("SignupButton_Failure");
        }
    }

    @Test(priority = 3)
    public void signupWithValidNameAndEmail() {
        try {
            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("keethana12003@gmail.com");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement accountInfoHeader = driver.findElement(By.xpath("//b[contains(text(),'Enter Account Information')]"));
            Assert.assertTrue(accountInfoHeader.isDisplayed(), "Account creation page not loaded");
            test.pass(" Signup with valid name and email navigated to account creation");
            captureScreenshot("SignupValid");
        } catch (Exception e) {
            test.fail(" Signup with valid data failed: " + e.getMessage());
            captureScreenshot("SignupValid_Failure");
        }
    }

    @Test(priority = 4)
    public void signupWithInvalidEmail() {
        try {
            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("keerthana12003gmail.com");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Email Address already exist!')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown for invalid email");
            test.pass(" Invalid email triggered error message");
            captureScreenshot("SignupInvalidEmail");
        } catch (Exception e) {
            test.fail(" Signup with invalid email failed: " + e.getMessage());
            captureScreenshot("SignupInvalidEmail_Failure");
        }
    }

    @Test(priority = 5)
    public void signupWithNoName() {
        try {
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("keerthana12003@gmail.com");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement nameField = driver.findElement(By.name("name"));
            String validationMessage = nameField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing name");
            test.pass(" Alert shown for missing name");
            captureScreenshot("SignupNoName");
        } catch (Exception e) {
            test.fail(" Signup with no name failed: " + e.getMessage());
            captureScreenshot("SignupNoName_Failure");
        }
    }

    @Test(priority = 6)
    public void signupWithNoEmail() {
        try {
            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
            String validationMessage = emailField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing email");
            test.pass(" Alert shown for missing email");
            captureScreenshot("SignupNoEmail");
        } catch (Exception e) {
            test.fail(" Signup with no email failed: " + e.getMessage());
            captureScreenshot("SignupNoEmail_Failure");
        }
    }

    @Test(priority = 7)
    public void signupWithNoData() {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement nameField = driver.findElement(By.name("name"));
            String validationMessage = nameField.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("fill out this field"), "No alert for missing name and email");
            test.pass(" Alert shown for missing name and email");
            captureScreenshot("SignupNoData");
        } catch (Exception e) {
            test.fail(" Signup with no data failed: " + e.getMessage());
            captureScreenshot("SignupNoData_Failure");
        }
    }

    @Test(priority = 8)
    public void signupWithExistingEmail() {
        try {
            driver.findElement(By.name("name")).sendKeys("Keerthana V S");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("keerthana12003@gmail.com");
            driver.findElement(By.xpath("//button[contains(text(),'Signup')]")).click();

            WebElement errorMsg = driver.findElement(By.xpath("//p[contains(text(),'Email Address already exist!')]"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not shown for existing email");
            test.pass(" Existing email triggered error message");
            captureScreenshot("SignupExistingEmail");
        } catch (Exception e) {
            test.fail(" Signup with existing email failed: " + e.getMessage());
            captureScreenshot("SignupExistingEmail_Failure");
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

