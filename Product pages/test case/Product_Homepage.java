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

public class Product_Homepage {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Reports/HomepageValidationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\vskee\\eclipse-workspace\\seleniumsetup\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://automationexercise.com");
    }

    @Test
    public void verifyHomepageElementsAndProductsButton() {
        test = extent.createTest("TC: Verify Homepage Elements and Products Button");

        try {
            // Step 1: Validate homepage logo
            WebElement logo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));
            assert logo.isDisplayed();
            captureScreenshot("01_LogoVisible");
            test.pass("✅ Website logo is visible");

            // Step 2: Validate navigation menu
            WebElement navMenu = driver.findElement(By.xpath("//div[@class='shop-menu pull-right']"));
            assert navMenu.isDisplayed();
            captureScreenshot("02_NavMenuVisible");
            test.pass("✅ Navigation menu is visible");

            // Step 3: Validate 'Products' button
            WebElement productsBtn = driver.findElement(By.xpath("//a[text()=' Products']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsBtn);
            assert productsBtn.isDisplayed();
            captureScreenshot("03_ProductsButtonVisible");
            test.pass("✅ 'Products' button is present in the navigation menu");

        } catch (Exception e) {
            test.fail("❌ Homepage validation failed: " + e.getMessage());
            captureScreenshot("HomepageValidation_Failure");
            throw new RuntimeException(e);
        }
    }

    public void captureScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("Screenshots/" + name + ".png");
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
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


