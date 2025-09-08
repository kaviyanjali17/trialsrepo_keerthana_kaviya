package com.automationexercise.tests;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class End_end_Testcase {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/SignupLoginLogoutReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }


    @Test
    public void completeSignupFlow() {
        test = extent.createTest("TC: Complete Signup Flow with Account Creation");

        try {
            // Step 1: Click Signup/Login
            driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

            // Step 2: Enter name and email
            driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys("Keerthana V S");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("keerthana12@gmail.com");

            captureScreenshot("01_EnteredNameEmail");

            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

            // Step 3: Fill account info
            driver.findElement(By.id("id_gender2")).click(); // Mrs
            driver.findElement(By.id("password")).sendKeys("Keerthi@123");
            new Select(driver.findElement(By.id("days"))).selectByValue("10");
            new Select(driver.findElement(By.id("months"))).selectByValue("5");
            new Select(driver.findElement(By.id("years"))).selectByValue("1995");
            driver.findElement(By.id("newsletter")).click();
            driver.findElement(By.id("optin")).click();
            captureScreenshot("02_FilledBasicInfo");

            // Step 4: Fill address info
            driver.findElement(By.id("first_name")).sendKeys("Keerthana");
            driver.findElement(By.id("last_name")).sendKeys("V S");
            driver.findElement(By.id("company")).sendKeys("TestCorp");
            driver.findElement(By.id("address1")).sendKeys("123 Test Street");
            driver.findElement(By.id("address2")).sendKeys("Suite 456");
            new Select(driver.findElement(By.id("country"))).selectByVisibleText("India");
            driver.findElement(By.id("state")).sendKeys("Karnataka");
            driver.findElement(By.id("city")).sendKeys("Chitradurga");
            driver.findElement(By.id("zipcode")).sendKeys("577501");
            driver.findElement(By.id("mobile_number")).sendKeys("9876543210");
            captureScreenshot("03_FilledAddressInfo");

            // Step 5: Create account
            driver.findElement(By.xpath("//button[text()='Create Account']")).click();

            WebElement successMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Account Created!']")));
            assert successMsg.isDisplayed();
            test.pass("✅ Account created successfully");
            captureScreenshot("04_AccountCreated");

            // Step 6: Continue to logged-in state
            driver.findElement(By.xpath("//a[text()='Continue']")).click();
            WebElement loggedInMsg = driver.findElement(By.xpath("//a[text()=' Logged in as ']"));
            assert loggedInMsg.isDisplayed();
            test.pass("✅ Logged in after account creation");
            captureScreenshot("05_LoggedIn");

        } catch (Exception e) {
            test.fail("❌ Signup flow failed: " + e.getMessage());
            captureScreenshot("SignupFlow_Failure");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void verifyLoginAndLogoutFunctionality() {
        test = extent.createTest("TC2: Verify Login and Logout Functionality");
        try {
            // Step 1: Click Signup/Login
            driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

            // Step 2: Enter credentials
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("keerthana12@gmail.com");
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Keerthi@123");
            captureScreenshot("TC2_EnteredLoginDetails");

            // Step 3: Click Login
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

            // Step 4: Validate login success
            WebElement loggedInAs = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
            assert loggedInAs.isDisplayed();
            test.pass("✅ Login successful: " + loggedInAs.getText());
            captureScreenshot("TC2_LoginSuccess");

            // Step 5: Click Logout
            WebElement logoutBtn = driver.findElement(By.xpath("//a[text()=' Logout']"));
            scrollTo(logoutBtn);
            logoutBtn.click();
            captureScreenshot("TC2_ClickedLogout");

            // Step 6: Validate logout success
            WebElement signupLoginLink = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Signup / Login']")));
            assert signupLoginLink.isDisplayed();
            test.pass("✅ Logout successful and navigated to Signup/Login page");
            captureScreenshot("TC2_LogoutSuccess");

        } catch (Exception e) {
            test.fail("❌ Login or Logout failed: " + e.getMessage());
            captureScreenshot("TC2_LoginLogoutFail");
            throw new RuntimeException(e);
        }
    }

    public void scrollTo(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("📸 Screenshot saved: " + dest.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}



