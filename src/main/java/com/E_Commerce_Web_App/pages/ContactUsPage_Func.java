package com.E_Commerce_Web_App.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage_Func {
    WebDriver driver;
    WebDriverWait wait;

    public ContactUsPage_Func(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By contactUsLink = By.linkText("Contact us");
    By nameField = By.name("name");
    By emailField = By.name("email");
    By subjectField = By.name("subject");
    By messageField = By.id("message");
    By uploadFileButton = By.name("upload_file");
    By submitButton = By.name("submit");
    By successMessage = By.xpath("//div[@class='status alert alert-success']");
    By homeButton = By.xpath("//*[@id=\"form-section\"]/a/span");

    By subscriptionInput = By.id("susbscribe_email");
    By subscriptionButton = By.id("subscribe");
    By subscriptionSuccess = By.xpath("//div[@class='alert-success alert']");
    By scrollToTopBtn = By.id("scrollUp");

    public void clickContactUs() {
        driver.findElement(contactUsLink).click();
    }

    public boolean isNameFieldDisplayed() {
        return driver.findElement(nameField).isDisplayed();
    }

    public void enterName(String name) {
        WebElement element = driver.findElement(nameField);
        element.clear();
        element.sendKeys(name);
    }

    public void enterEmail(String email) {
        WebElement element = driver.findElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    public void enterSubject(String subject) {
        WebElement element = driver.findElement(subjectField);
        element.clear();
        element.sendKeys(subject);
    }

    public void enterMessage(String message) {
        WebElement element = driver.findElement(messageField);
        element.clear();
        element.sendKeys(message);
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadFileButton).sendKeys(filePath);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }

    public void clickHomeButton() {
        driver.findElement(homeButton).click();
    }

    public void enterSubscription(String email) {
        WebElement element = driver.findElement(subscriptionInput);
        element.clear();
        element.sendKeys(email);
    }

    public void clickSubscriptionButton() {
        driver.findElement(subscriptionButton).click();
    }

    public boolean isSubscriptionSuccessDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccess)).isDisplayed();
    }

    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
    }

    public void clickScrollToTop() {
        driver.findElement(scrollToTopBtn).click();
    }
}
