package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class Testcase_page_TC09to11 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestCasesValidation.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void validateFeedbackSectionPresence() throws IOException {
        test = extent.createTest("Validate 'Feedback for Us' Section Presence");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        try {
            // Case-insensitive match using translate()
            List<WebElement> feedbackSections = driver.findElements(By.xpath(
                "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'feedback for us')]"));

            if (feedbackSections.size() > 0) {
                WebElement section = feedbackSections.get(0);
                if (section.isDisplayed()) {
                    test.pass("'Feedback for Us' section is present and visible.");
                    captureScreenshot("feedback_section_present");
                } else {
                    test.warning("'Feedback for Us' section is present but not visible.");
                    captureScreenshot("feedback_section_hidden");
                }
            } else {
                test.info("'Feedback for Us' section not found on the page.");
                captureScreenshot("feedback_section_missing");
            }
        } catch (Exception e) {
            test.fail("Error while validating feedback section: " + e.getMessage());
            captureScreenshot("feedback_section_error");
            Assert.fail("Exception during feedback section validation.");
        }
    }


    @Test(priority = 2)
    public void validateHyperlinksInTestCasesSection() throws IOException {
        test = extent.createTest("Validate Hyperlinks in Test Cases Section");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        try {
            List<WebElement> links = driver.findElements(By.xpath("//div[@class='panel-heading']//a"));

            if (links.size() > 0) {
                test.pass("Hyperlinks found in test cases section: " + links.size());
                for (WebElement link : links) {
                    String href = link.getAttribute("href");
                    if (href != null && !href.trim().isEmpty()) {
                        test.info("Link: " + href);
                    } else {
                        test.warning("Hyperlink missing href attribute.");
                    }
                }
                captureScreenshot("hyperlinks_present");
            } else {
                test.fail("No hyperlinks found in test cases section.");
                captureScreenshot("hyperlinks_missing");
                Assert.fail("Hyperlinks not found.");
            }
        } catch (Exception e) {
            test.fail("Error while validating hyperlinks: " + e.getMessage());
            captureScreenshot("hyperlinks_error");
            Assert.fail("Exception during hyperlink validation.");
        }
    }

    @Test(priority = 3)
    public void validateFeedbackMailLinkPresence() throws IOException {
        test = extent.createTest("Validate Feedback Mail Link Presence");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        try {
            WebElement mailLink = driver.findElement(By.xpath("//a[starts-with(@href,'mailto:')]"));

            if (mailLink.isDisplayed()) {
                String mailHref = mailLink.getAttribute("href");
                test.pass("Feedback mail link is visible: " + mailHref);
                Assert.assertTrue(mailHref.contains("@"), "Mail link does not contain '@'.");
                captureScreenshot("feedback_mail_present");
            } else {
                test.fail("Feedback mail link is not visible.");
                captureScreenshot("feedback_mail_hidden");
                Assert.fail("Feedback mail link not displayed.");
            }
        } catch (NoSuchElementException e) {
            test.fail("Feedback mail link not found.");
            captureScreenshot("feedback_mail_missing");
            Assert.fail("Feedback mail link not found.");
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


