package com.automationexercise.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Based_impact_TC003 {
    WebDriver driver;

    public Based_impact_TC003(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignupLogin() {
        driver.findElement(By.linkText("Signup / Login")).click();
    }

    public void enterLoginEmail(String email) {
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }
    public void clickLogoutButton() {
        driver.findElement(By.linkText("Logout")).click();
    }
    

    public void enterSearchTerm(String productName) {
        driver.findElement(By.id("search_product")).sendKeys(productName);
    }

    public void clickSearchButton() {
        driver.findElement(By.id("submit_search")).click();
    }
    public void goToCartPage() {
        driver.findElement(By.linkText("Cart")).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(By.linkText("Proceed To Checkout")).click();
    }
    
    public void clickDeleteIcon() {
        driver.findElement(By.xpath("//a[@class='cart_quantity_delete']")).click();
    }
    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]")));
        addToCartBtn.click();
    }
    public void goToProductsPage() {
        driver.findElement(By.linkText("Products")).click();
    }

    public boolean isAllProductsVisible() {
        return driver.findElement(By.xpath("//h2[text()='All Products']")).isDisplayed();
    }
    public void clickContactUs() {
        driver.findElement(By.xpath("//a[text()='Contact us']")).click();
    }

    public void enterContactName(String name) {
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
    }

    public void enterContactEmail(String email) {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    }

    public void enterContactMessage(String message) {
        driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys(message);
    }

    public void clickSubmitButton() {
        driver.findElement(By.xpath("//input[@name='submit']")).click();
    }




}
