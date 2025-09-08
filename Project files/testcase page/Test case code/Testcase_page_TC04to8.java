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

public class Testcase_page_TC04to8 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/TestCasesReport.html");
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

    @Test(priority = 1)
    public void verifyTestCasesButtonVisible() {
        test = extent.createTest("TC1: Verify 'Test Cases' Button Visibility");
        try {
            WebElement testCasesBtn = driver.findElement(By.xpath("//a[text()=' Test Cases']"));
            assert testCasesBtn.isDisplayed();
            test.pass("'Test Cases' button is visible");
            captureScreenshot("TC1_ButtonVisible");
        } catch (Exception e) {
            test.fail("Button not visible: " + e.getMessage());
            captureScreenshot("TC1_ButtonVisible_Fail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 2)
    public void verifyNavigationToTestCasesPage() {
        test = extent.createTest("TC2: Verify Navigation to Test Cases Page");
        try {
            driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/test_cases"));
            String currentURL = driver.getCurrentUrl();
            assert currentURL.contains("test_cases");
            test.pass("Navigated to Test Cases page: " + currentURL);
            captureScreenshot("TC2_NavigationSuccess");
        } catch (Exception e) {
            test.fail("Navigation failed: " + e.getMessage());
            captureScreenshot("TC2_NavigationFail");
            throw new RuntimeException(e);
        }
    }
    @Test(priority = 3)
    public void verifyTestCasesPageTitle() {
        test = extent.createTest("TC3: Verify Test Cases Page Title");
        try {
            driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();
            String actualTitle = driver.getTitle().trim();
            String expectedTitle = "Automation Practice Website for UI Testing - Test Cases";
            assert actualTitle.equals(expectedTitle);
            test.pass("Page title matches: " + actualTitle);
            captureScreenshot("TC3_TitleMatch");
        } catch (Exception e) {
            test.fail("Title mismatch or error: " + e.getMessage());
            captureScreenshot("TC3_TitleFail");
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 4)
    public void verifyTestCasesPageContent() {
        test = extent.createTest("TC4: Verify Test Cases Page Content");
        try {
            driver.findElement(By.xpath("//a[text()=' Test Cases']")).click();
            WebElement contentSection = driver.findElement(By.xpath("//div[@class='panel-group']"));
            scrollTo(contentSection);
            String contentText = contentSection.getText().toLowerCase();
            if (!contentText.contains("test case 1") || !contentText.contains("register user")) {
                throw new AssertionError("Expected test case content not found");
            }
            test.pass("Test Cases content section is visible and contains expected text");
            captureScreenshot("TC4_ContentVisible");
        } catch (Exception e) {
            test.fail("Content section not visible or incorrect: " + e.getMessage());
            captureScreenshot("TC4_ContentFail");
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

