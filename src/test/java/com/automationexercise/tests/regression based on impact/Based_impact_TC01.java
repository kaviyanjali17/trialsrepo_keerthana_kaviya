package com.automationexercise.tests;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class Based_impact_TC01 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC01(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators for Products Page
    By productsLink = By.xpath("//a[@href='/products']");
    By allProductsHeading = By.xpath("//h2[text()='All Products']");
    By addToCartButton = By.xpath("(//a[contains(text(),'Add to cart')])[1]");

    // Locators for Contact Page
    By contactUsLink = By.xpath("//a[@href='/contact_us']");
    By nameField = By.name("name");
    By emailField = By.name("email");
    By messageField = By.name("message");
    By submitButton = By.name("submit");

    // Actions for Products Flow
    public void goToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public boolean isAllProductsVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeading)).isDisplayed();
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    // Actions for Contact Flow
    public void goToContactPage() {
        wait.until(ExpectedConditions.elementToBeClickable(contactUsLink)).click();
    }

    public void fillContactForm(String name, String email, String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }
}
