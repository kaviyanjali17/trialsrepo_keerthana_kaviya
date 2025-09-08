package com.automationexercise.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Based_impact_TC009 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC009(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickContactUsLink() {
        WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/contact_us']")));
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
        } catch (Exception e) {
            return false;
        }
    }

}


