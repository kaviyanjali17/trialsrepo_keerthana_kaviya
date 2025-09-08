package com.automationexercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Based_impact_TC013 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC013(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void goToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products"))).click();
    }

    public void clickFirstViewProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='View Product'])[1]"))).click();
    }

    public void scrollToReviewSection() {
        WebElement reviewHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Write Your Review']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", reviewHeader);
    }

    public boolean isSubmitButtonVisible() {
        try {
            WebElement submitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-review")));
            return submitBtn.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterReviewDetails(String name, String email, String review) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("review"))).sendKeys(review);
    }

    public void clickSubmitReview() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-review"))).click();
    }

    public boolean isReviewSuccessMessageVisible() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Thank you for your review.']")));
            return successMsg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
