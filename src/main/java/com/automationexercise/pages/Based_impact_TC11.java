package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Based_impact_TC11 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC11(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void scrollToSubscriptionSection() {
        WebElement subscriptionHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[@class='single-widget']/h2[text()='Subscription']")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
    }

    public void enterEmail(String email) {
        scrollToSubscriptionSection();
        try {
            WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe_email")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", emailField);
            emailField.clear();
            emailField.sendKeys(email);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter email: " + e.getMessage());
        }
    }

    public void clickSubscribeButton() {
        WebElement subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));
        subscribeBtn.click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));
            return successMsg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

