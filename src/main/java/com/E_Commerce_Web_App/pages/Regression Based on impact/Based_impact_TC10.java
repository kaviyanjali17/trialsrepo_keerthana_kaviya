package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Based_impact_TC10 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC10(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToContactUs() {
        WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactLink.click();
    }

    public void enterName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterMessage(String message) {
        WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("message")));
        messageField.clear();
        messageField.sendKeys(message);
    }

    public void clickSubmit() {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
        submitBtn.click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='status alert alert-success']")));
            return successMsg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

