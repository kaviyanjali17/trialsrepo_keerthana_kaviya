package project;

import org.testng.annotations.*;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;

public class Based_impact_TC1 {
    WebDriver driver;
    Based_impact_TC001 nav;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/NavigationReport.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        nav = new Based_impact_TC001(driver);
    }

    @Test
    public void verifyNavigationLinks() {
        String[][] links = {
            {"Products", "CATEGORY"},
            {"Signup/Login", "LOGIN TO YOUR ACCOUNT"},
            {"Contact Us", "CONTACT US"},
            {"Cart", "SUBSCRIPTION"},
            {"Test Cases", "TEST CASES"},
            {"API Testing", "APIS LIST FOR PRACTICE"},
            {"Video Tutorials", "VIDEO TUTORIALS"}
        };

        for (String[] pair : links) {
            test = extent.createTest("Navigation Test - " + pair[0]);
            try {
                nav.clickLink(pair[0]);
                Thread.sleep(3000); // brief wait
                String heading = nav.getPageHeading();
                test.info("Page heading: " + heading);

                Assert.assertTrue(heading.toLowerCase().contains(pair[1].toLowerCase()),
                    "Expected heading: " + pair[1] + ", but got: " + heading);
                test.pass("Navigation to " + pair[0] + " verified");
                driver.navigate().back();
            } catch (Exception e) {
                test.fail("Navigation failed for " + pair[0] + ": " + e.getMessage());
            }
        }
    }

    @AfterMethod
    public void teardownBrowser() {
        driver.quit();
    }

    @AfterClass
    public void flushReport() {
        extent.flush();
    }
}
