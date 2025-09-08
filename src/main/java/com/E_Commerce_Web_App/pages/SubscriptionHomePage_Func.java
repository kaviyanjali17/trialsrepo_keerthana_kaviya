package com.E_Commerce_Web_App.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubscriptionHomePage_Func {

    WebDriver driver;

    private By subscriptionField = By.id("susbscribe_email");
    private By subscriptionButton = By.id("subscribe"); 
    private By successMessage = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    public SubscriptionHomePage_Func(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void enterSubscription(String email) {
        WebElement emailField = driver.findElement(subscriptionField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickSubscriptionButton() {
        driver.findElement(subscriptionButton).click();
    }

    public boolean isSubscriptionSuccessDisplayed() {
        try {
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
