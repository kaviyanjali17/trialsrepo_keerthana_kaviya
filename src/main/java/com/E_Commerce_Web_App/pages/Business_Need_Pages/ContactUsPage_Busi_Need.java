package com.E_Commerce_Web_App.pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage_Busi_Need {
    WebDriver driver;

    public ContactUsPage_Busi_Need(WebDriver driver) {
        this.driver = driver;
    }

    By nameField = By.name("name");
    By emailField = By.name("email");
    By subjectField = By.name("subject");
    By messageField = By.id("message");
    By submitBtn = By.name("submit");
    By successMsg = By.xpath("//div[@class='status alert alert-success']");

    public void enterContactDetails(String name, String email, String subject, String message) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
        
        Alert alert = driver.switchTo().alert();
        alert.accept();  
    }

    public boolean isSubmissionSuccessful() {
        return driver.findElement(successMsg).isDisplayed();
    }
    
    public String getSuccessMessageText() {
        return driver.findElement(successMsg).getText();
    }
}
