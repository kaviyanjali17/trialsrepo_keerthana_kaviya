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

public class Based_impact_TC08 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestCaseDetailValidation.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void validateFirstTestCaseSteps() throws IOException {
        test = extent.createTest("Validate First Test Case Steps Display");

        driver.get("https://automationexercise.com/test_cases");
        scrollToBottom();

        try {
            WebElement firstPanel = driver.findElement(By.xpath("(//div[@class='panel'])[1]"));
            WebElement heading = firstPanel.findElement(By.className("panel-heading"));
            WebElement body = firstPanel.findElement(By.className("panel-body"));

            String headingText = heading.getText().trim();
            test.info("Checking Test Case: " + headingText);

            // If body is not visible, click to expand
            if (!body.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);
                heading.click();
                Thread.sleep(1000); // Allow animation
            }

            // Locate the steps inside the panel-body
            List<WebElement> steps = body.findElements(By.xpath(".//ul/li"));

            if (steps.size() > 0) {
                test.pass("Detailed steps found for: " + headingText);
                for (WebElement step : steps) {
                    test.info("Step: " + step.getText().trim());
                }
                captureScreenshot("first_test_case_steps");
            } else {
                test.fail("No steps found inside panel-body for: " + headingText);
                captureScreenshot("first_test_case_failed");
                Assert.fail("Expected steps not found in Test Case 1");
            }

        } catch (Exception e) {
            test.fail("Error while validating test case steps: " + e.getMessage());
            captureScreenshot("first_test_case_error");
            Assert.fail("Exception occurred during test case validation.");
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


