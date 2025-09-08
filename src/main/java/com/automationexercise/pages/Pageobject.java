package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Pageobject {
    WebDriver driver;
    WebDriverWait wait;

    public Pageobject(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Product page
    By productsLink = By.xpath("//a[@href='/products']");
    By allProductsHeading = By.xpath("//h2[text()='All Products']");
    By addToCartButton = By.xpath("(//a[text()='Add to cart'])[1]");

    // Contact Us page
    By contactLink = By.xpath("//a[text()='Contact us']");
    By nameField = By.name("name");
    By emailField = By.name("email");
    By messageField = By.name("message");
    By submitButton = By.name("submit");

    public void goToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public boolean isAllProductsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeading)).isDisplayed();
    }

    public void clickAddToCart() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
    }

    public void goToContactPage() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink)).click();
    }

    public void fillContactForm(String name, String email, String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}

