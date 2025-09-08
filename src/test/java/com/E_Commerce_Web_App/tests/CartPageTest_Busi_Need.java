package com.E_Commerce_Web_App.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.CartPage_Busi_Need;
import com.E_Commerce_Web_App.pages.HomePage_Busi_Need;
import com.E_Commerce_Web_App.pages.ProductPage_Busi_Need;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;

public class CartPageTest_Busi_Need extends BaseTest {

    @Test
    public void verifyAddDeleteCheckoutFlow() throws IOException {
        driver.get("https://automationexercise.com/");
        test = extent.createTest("Cart: Add, Delete and Checkout flow");

        HomePage_Busi_Need home = new HomePage_Busi_Need(driver);
        home.clickProducts();

        ProductPage_Busi_Need product = new ProductPage_Busi_Need(driver);
        CartPage_Busi_Need cart = new CartPage_Busi_Need(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Add first product and Wait and click View Cart popup
        product.addFirstProductToCart();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")));
        driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")).click();

        // Delete 
        By deleteIcon = By.xpath("//a[@class='cart_quantity_delete']");
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
        cart.deleteItem();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteIcon));
        test.pass("Deleted first product from cart")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "DeleteFirstProduct"));
        
        // Go back to products page add another product and Wait and click View Cart popup again
        home.clickProducts();
        product.addFirstProductToCart();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")));
        driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")).click();

        // Proceed to checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed To Checkout")));
        cart.clickCheckout();
        test.pass("Clicked Proceed To Checkout")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Checkout"));
    }
}
