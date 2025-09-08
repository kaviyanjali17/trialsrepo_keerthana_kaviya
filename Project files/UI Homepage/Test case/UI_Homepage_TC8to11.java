package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class UI_Homepage_TC8to11 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setup() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageFunctionalChrome.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Tester", "Keerthana");

        test = extent.createTest("Homepage Functional Tests - Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 1)
    public void validateLoginLogoutFlow() {
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("email")).sendKeys("keerthivs12226@gmail.com");
            driver.findElement(By.name("password")).sendKeys("Keerthi@123");
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement logoutLink = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
            if (logoutLink.isDisplayed()) {
                test.pass("Login successful and Logout link is visible");
                logoutLink.click();
                WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
                if (loginLink.isDisplayed()) {
                    test.pass(" Logout successful and Login link is visible again");
                } else {
                    test.fail(" Logout did not redirect properly");
                }
            } else {
                test.fail(" Logout link not found after login");
            }
            captureScreenshot("LoginLogout_Chrome");
        } catch (Exception e) {
            test.fail(" Exception in login/logout flow: " + e.getMessage());
            captureScreenshot("LoginLogout_Failure_Chrome");
        }
    }

    @Test(priority = 2)
    public void validateCarouselFunctionality() {
        try {
            WebElement slider = driver.findElement(By.id("slider-carousel"));
            if (slider.isDisplayed()) {
                test.pass(" Carousel is visible on homepage");

                WebElement rightArrow = driver.findElement(By.className("right"));
                WebElement leftArrow = driver.findElement(By.className("left"));

                rightArrow.click();
                Thread.sleep(1000);
                leftArrow.click();
                Thread.sleep(1000);

                test.pass(" Carousel navigation arrows are clickable");
            } else {
                test.fail(" Carousel not visible");
            }
            captureScreenshot("Carousel_Chrome");
        } catch (Exception e) {
            test.fail(" Exception in carousel validation: " + e.getMessage());
            captureScreenshot("Carousel_Failure_Chrome");
        }
    }

    @Test(priority = 3)
    public void validateFooterContent() {
        try {
            WebElement footer = driver.findElement(By.xpath("//footer"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
            Thread.sleep(1000);

            WebElement copyright =
                driver.findElement(By.xpath("//*[contains(text(),'Copyright © 2021')]"));

            if (footer.isDisplayed() && copyright.isDisplayed()) {
                test.pass(" Footer and copyright info are visible");
            } else {
                test.fail(" Footer content not displayed properly");
            }
            captureScreenshot("Footer_Chrome");
        } catch (Exception e) {
            test.fail(" Exception in footer validation: " + e.getMessage());
            captureScreenshot("Footer_Failure_Chrome");
        }
    }

    @Test(priority = 4)
    public void validateResponsiveLayout() {
        try {
            driver.manage().window().setSize(new Dimension(375, 667)); // iPhone 8 size
            Thread.sleep(2000);

            WebElement menuToggle = driver.findElement(By.className("navbar-toggle"));
            if (menuToggle.isDisplayed()) {
                test.pass(" Responsive layout detected on smaller screen");
            } else {
                test.fail(" Responsive layout not adapting properly");
            }
            captureScreenshot("ResponsiveLayout_Chrome");
        } catch (Exception e) {
            test.fail(" Exception in responsive layout validation: " + e.getMessage());
            captureScreenshot("ResponsiveLayout_Failure_Chrome");
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
            test.info(" Screenshot saved: " + dest.getAbsolutePath());
        } catch (IOException e) {
            test.warning(" Screenshot failed: " + e.getMessage());
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
