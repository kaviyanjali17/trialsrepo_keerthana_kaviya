package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;

public class UI_Hompepage_TC24to27 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageScrollSectionsChrome.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage Scroll & Section Tests - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateVideoTutorialsIcon() {
        try {
            WebElement videoTutorials = driver.findElement(By.xpath("//a[contains(text(),'Video Tutorials')]"));
            videoTutorials.click();

            WebElement header = driver.findElement(By.xpath("//h2[contains(text(),'Video Tutorials')]"));
            Assert.assertTrue(header.isDisplayed(), "✅ Video Tutorials page opened");
            test.pass("✅ Video Tutorials icon navigated correctly");
            captureScreenshot("VideoTutorials_Chrome");
        } catch (Exception e) {
            test.fail("❌ Video Tutorials icon failed: " + e.getMessage());
            captureScreenshot("VideoTutorials_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateFeaturedItemsSection() {
        try {
            WebElement featuredHeader = driver.findElement(By.xpath("//h2[text()='Features Items']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", featuredHeader);
            Thread.sleep(1000);
            Assert.assertTrue(featuredHeader.isDisplayed(), "✅ Featured Items section is visible");
            test.pass("✅ Featured Items section displayed");
            captureScreenshot("FeaturedItems_Chrome");
        } catch (Exception e) {
            test.fail("❌ Featured Items section failed: " + e.getMessage());
            captureScreenshot("FeaturedItems_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateVerticalScrollingOnLoginPage() {
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            Thread.sleep(1000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500)");
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0, -500)");
            Thread.sleep(1000);

            test.pass("✅ Vertical scrolling on login page works");
            captureScreenshot("LoginScroll_Chrome");
        } catch (Exception e) {
            test.fail("❌ Vertical scrolling failed: " + e.getMessage());
            captureScreenshot("LoginScroll_Failure_Chrome");
        }
    }

    @Test(priority = 4)
    public void validateScrollToTopButton() {
        try {
            WebElement homeIcon = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
            homeIcon.click();
            Thread.sleep(1000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            WebElement scrollToTopBtn = driver.findElement(By.id("scrollUp"));
            js.executeScript("arguments[0].click();", scrollToTopBtn);
            Thread.sleep(1000);

            Long scrollY = (Long) js.executeScript("return window.pageYOffset;");
            Assert.assertTrue(scrollY == 0, "✅ Page scrolled to top");
            test.pass("✅ Scroll to Top button works");
            captureScreenshot("ScrollToTop_Chrome");
        } catch (Exception e) {
            test.fail("❌ Scroll to Top button failed: " + e.getMessage());
            captureScreenshot("ScrollToTop_Failure_Chrome");
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

