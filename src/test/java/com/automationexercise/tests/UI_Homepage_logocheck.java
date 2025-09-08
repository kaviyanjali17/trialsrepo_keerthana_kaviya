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
import java.util.List;

public class UI_Homepage_logocheck {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageChromeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage UI Validation - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateLogoClickability() {
        try {
            WebElement logo = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
            if (logo.isDisplayed() && logo.isEnabled()) {
                test.pass(" Logo is visible and clickable");
                logo.click();
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.equals("https://automationexercise.com/")) {
                    test.pass(" Logo click redirected to homepage");
                } else {
                    test.fail(" Logo click did not redirect to homepage. URL: " + currentUrl);
                }
            } else {
                test.fail(" Logo is not visible or not clickable");
            }
            captureScreenshot("LogoClick_Chrome");
        } catch (Exception e) {
            test.fail(" Exception in logo validation: " + e.getMessage());
            captureScreenshot("LogoClick_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateSearchBarFunctionality() {
        try {
            WebElement searchInput = driver.findElement(By.id("search_product"));
            WebElement searchButton = driver.findElement(By.id("submit_search"));

            searchInput.sendKeys("Tshirt");
            searchButton.click();

            WebElement resultsHeader = driver.findElement(By.xpath("//h2[contains(text(),'Searched Products')]"));
            if (resultsHeader.isDisplayed()) {
                test.pass(" Search results displayed for 'Tshirt'");
            } else {
                test.fail(" Search results not displayed");
            }
            captureScreenshot("SearchBar_Chrome");
        } catch (Exception e) {
            test.fail("Exception in search bar validation: " + e.getMessage());
            captureScreenshot("SearchBar_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateTopNavigationLinks() {
        try {
            List<WebElement> navLinks = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']/li/a"));
            for (WebElement link : navLinks) {
                String linkText = link.getText();
                test.info("Clicking navigation link: " + linkText);
                link.click();
                Thread.sleep(2000); // Wait for page load
                String currentUrl = driver.getCurrentUrl();
                test.pass("✅ '" + linkText + "' redirected to: " + currentUrl);
                driver.navigate().back();
                Thread.sleep(1000);
            }
            captureScreenshot("TopNavLinks_Chrome");
        } catch (Exception e) {
            test.fail("Exception in top navigation validation: " + e.getMessage());
            captureScreenshot("TopNavLinks_Failure_Chrome");
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            test.info("Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            test.warning("Screenshot failed: " + e.getMessage());
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
