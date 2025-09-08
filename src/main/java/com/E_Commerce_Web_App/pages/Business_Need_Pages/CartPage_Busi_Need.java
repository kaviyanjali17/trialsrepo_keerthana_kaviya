package com.E_Commerce_Web_App.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage_Busi_Need {
    WebDriver driver;

    public CartPage_Busi_Need(WebDriver driver) {
        this.driver = driver;
    }

    By deleteIcon = By.xpath("//a[@class='cart_quantity_delete']");
    By checkoutBtn = By.linkText("Proceed To Checkout");

    public void deleteItem() {
        driver.findElement(deleteIcon).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutBtn).click();
    }
}
