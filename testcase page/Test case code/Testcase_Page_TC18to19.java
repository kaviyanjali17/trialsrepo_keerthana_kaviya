package com.automationexercise.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class Testcase_Page_TC18to19 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/PopupPresenceValidation.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void validateAdvertisementPopupPresence() throws IOException {
        test = extent.createTest("Advertisement Popup Presence Validation");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        try {
            List<WebElement> adFrames = driver.findElements(By.xpath("//iframe[contains(@src,'ads')]"));

            if (adFrames.size() > 0) {
                WebElement adFrame = adFrames.get(0);
                if (adFrame.isDisplayed()) {
                    test.pass("Advertisement popup iframe is present and visible.");
                    captureScreenshot("ad_popup_present");
                } else {
                    test.warning("Advertisement iframe is present but not visible.");
                    captureScreenshot("ad_popup_hidden");
                }
            } else {
                test.info("No advertisement popup iframe found on the page.");
                captureScreenshot("ad_popup_absent");
            }
        } catch (Exception e) {
            test.warning("Error while checking advertisement popup: " + e.getMessage());
            captureScreenshot("ad_popup_error");
        }
    }


    @Test(priority = 2)
    public void validateSuggestionPopupPresence() throws IOException {
        test = extent.createTest("Suggestion Popup Presence Validation");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        List<WebElement> suggestionPopups = driver.findElements(By.xpath("//div[contains(@class,'suggestion')]"));

        if (suggestionPopups.size() == 0) {
            test.pass("No suggestion popup present on the Test Cases page.");
            captureScreenshot("suggestion_popup_absent");
        } else {
            test.fail("Unexpected suggestion popup found.");
            captureScreenshot("suggestion_popup_present");
            Assert.fail("Suggestion popup should not be present.");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }

    private void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    private void captureScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + name + ".png";
        FileUtils.copyFile(src, new File(path));
        test.addScreenCaptureFromPath(path);
    }
}

