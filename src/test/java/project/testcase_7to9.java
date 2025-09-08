package project;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.*;
import org.testng.Assert;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.*;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testcase_7to9 {
    WebDriver driver;
    String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "cartflowdata")
    public void f(String testLabel) {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\CartFlowReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Cart Flow Test - " + testLabel);

        driver.get("https://automationexercise.com/");
        TestCase7_9 objCart = new TestCase7_9(driver);
        objCart.goToProductsPage();
        test.info("Navigated to Products page");

        Assert.assertTrue(objCart.isAllProductsHeadingVisible(), "'ALL PRODUCTS' heading not visible");
        test.pass("'ALL PRODUCTS' heading is visible");

        objCart.clickViewProduct();
        test.pass("Clicked 'View Product'");

        objCart.updateQuantity("3");
        test.pass("Updated quantity to 3");

        objCart.clickAddToCart();
        test.pass("Clicked 'Add to cart'");

        objCart.openCart();
        test.pass("Opened cart page");

        if (testLabel.contains("Delete")) {
            if (objCart.isDeleteIconVisible()) {
                objCart.clickDeleteIcon();
                test.pass("Clicked delete icon and removed item from cart");
            } else {
                test.fail("Delete icon not visible");
            }
        }

        if (testLabel.contains("Checkout")) {
            if (objCart.isCheckoutButtonVisible()) {
                objCart.clickCheckoutButton();
                test.pass("Clicked checkout button");
            } else {
                test.fail("Checkout button not visible");
            }
        }

        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public String[][] cartflowdata() throws IOException {
        File file = new File(projectpath + "\\testcase_7to9.xlsx");
        FileInputStream fs = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        String[][] data = new String[rowCount][1];

        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null) {
                data[i][0] = "";
            } else {
                Cell cell = row.getCell(0);
                switch (cell.getCellType()) {
                    case STRING:
                        data[i][0] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][0] = String.valueOf(cell.getNumericCellValue());
                        break;
                    default:
                        data[i][0] = "";
                }
            }
        }

        workbook.close();
        fs.close();
        return data;
    }

    @BeforeClass 
    public void beforeClass() 
    { 
    	System.out.println("This is Before Class"); 
    	}
    @AfterClass 
    public void afterClass() 
    { 
    	System.out.println("This is After Class"); 
    	}
    @BeforeTest 
    public void beforeTest() 
    { 
    	System.out.println("This is Before Test"); 
    	}
    @AfterTest 
    public void afterTest() 
    { 
    	System.out.println("This is After Test"); 
    	}
    @BeforeSuite 
    public void beforeSuite() 
    { 
    	System.out.println("This is Before Suite"); 
    	}
    @AfterSuite 
    public void afterSuite() 
    { 
    	System.out.println("This is After Suite"); 
    	}
}
