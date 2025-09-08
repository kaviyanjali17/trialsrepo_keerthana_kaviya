package com.E_Commerce_Web_App.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.E_Commerce_Web_App.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String browserUsed;

    @BeforeSuite
    @Parameters("reportName")  
    public void setupExtent(String reportName) {
        extent = ExtentManager.getInstance(reportName);
    }

    @BeforeClass
    @Parameters("browser")  
    public void setupDriver(String browser) {
        browserUsed = browser.toLowerCase(); 

        test = extent.createTest("Test Execution on Browser: " + browserUsed);
        test.assignCategory(browserUsed); 
        test.info("Initializing WebDriver for: " + browserUsed);

        switch (browserUsed) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        test.info("Browser launched successfully: " + browserUsed);
    }

    @AfterClass
    public void teardownDriver() {
        if (driver != null) {
        driver.quit();
         test.info("Browser closed: " + browserUsed);
 }
    }

    @AfterSuite
    public void flushExtent() {
        if (extent != null) {
            extent.flush();
        }
    }
}