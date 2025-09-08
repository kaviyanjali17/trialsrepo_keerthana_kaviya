package com.E_Commerce_Web_App.tests;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.HomePage_Busi_Need;
import com.E_Commerce_Web_App.pages.ProductPage_Busi_Need;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class ProductPageTest_Busi_Need extends BaseTest {
    
    @DataProvider(name = "searchData")
    public Object[][] getSearchData() throws IOException {
        String projectPath = System.getProperty("user.dir");
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx","SearchProduct");
    }

    @DataProvider(name = "reviewData")
    public Object[][] getReviewData() throws IOException {
        String projectPath = System.getProperty("user.dir");
        return ExcelUtilities.getdata(projectPath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile1.xlsx","WriteReview");
    }

    @Test(dataProvider = "searchData")
    public void verifyProductSearch(String productName) throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Search Product: " + productName);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need productPage = new ProductPage_Busi_Need(driver);
        productPage.searchProduct(productName);

        test.pass("Searched for product: " + productName)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Search_" + productName));
    }

    @Test
    public void verifyAddToCart() throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Add First Product to Cart");

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need productPage = new ProductPage_Busi_Need(driver);
        productPage.addFirstProductToCart();

        test.pass("First product added to cart successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "AddToCart"));
    }

    @Test
    public void verifyViewFirstProduct() throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("View First Product");

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need productPage = new ProductPage_Busi_Need(driver);
        productPage.viewFirstProduct();

        test.pass("First product viewed successfully")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "ViewProduct"));
    }

    @Test
    public void verifyCategoryAndBrandNavigation() throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Category and Brand Navigation");

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need productPage = new ProductPage_Busi_Need(driver);

        productPage.selectCategoryWomen();
        test.pass("Navigated to Women Category")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "WomenCategory"));

        productPage.selectCategoryMen();
        test.pass("Navigated to Men Category")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "MenCategory"));

        productPage.selectCategoryKids();
        test.pass("Navigated to Kids Category")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "KidsCategory"));

        productPage.selectBrand();
        test.pass("Navigated to a Brand Page")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "BrandPage"));
    }

    @Test(dataProvider = "reviewData")
    public void verifyWriteYourReview(String name, String email, String review) throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Write Review Test for: " + name);

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need productPage = new ProductPage_Busi_Need(driver);
        productPage.submitReview(name, email, review);

        test.pass("Review submitted successfully for user: " + name)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Review"));
    }
}
