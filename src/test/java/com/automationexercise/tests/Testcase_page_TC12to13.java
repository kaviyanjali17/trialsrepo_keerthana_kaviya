package com.automationexercise.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Testcase_page_TC12to13 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ScrollBarValidation.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void validateScrollBarMovement() throws IOException, InterruptedException {
        test = extent.createTest("Validate Scroll Bar Movement on Test Cases Page");

        driver.get("https://automationexercise.com/test_cases");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for page to settle
        Thread.sleep(2000);

        // Get initial scroll position
        Long initialScroll = (Long) js.executeScript("return Math.floor(document.documentElement.scrollTop);");
        test.info("Initial scroll position: " + initialScroll);

        // Scroll to bottom
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);
        Long scrolledDown = (Long) js.executeScript("return Math.floor(document.documentElement.scrollTop);");
        test.info("Scrolled down to: " + scrolledDown);

        // Scroll back to top
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
        Long scrolledUp = (Long) js.executeScript("return Math.floor(document.documentElement.scrollTop);");
        test.info("Scrolled back up to: " + scrolledUp);

        // Validate scroll movement
        boolean downMoved = scrolledDown > initialScroll + 50;
        boolean upMoved = scrolledUp < scrolledDown - 50;

        if (downMoved && upMoved) {
            test.pass("Scroll bar moved down and back up successfully.");
        } else {
            test.fail("Scroll bar did not move as expected. Initial: " + initialScroll +
                      ", Down: " + scrolledDown + ", Up: " + scrolledUp);
        }

        captureScreenshot("scroll_bar_movement");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }

    private void captureScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "screenshots/" + name + ".png";
        FileUtils.copyFile(src, new File(path));
        test.addScreenCaptureFromPath(path);
    }
}




