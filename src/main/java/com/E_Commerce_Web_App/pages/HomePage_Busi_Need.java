package com.E_Commerce_Web_App.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage_Busi_Need {
    WebDriver driver;

    public HomePage_Busi_Need(WebDriver driver) {
        this.driver = driver;
    }

    By products = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a");
    By signupLogin = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a");
    By contactUs = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[8]/a");
    By cart = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a");
    By videoTutorials = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[7]/a");
    By testCases = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a");
    By apiTesting = By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[6]/a");
    
    By subscriptionInput = By.id("susbscribe_email");
    By subscriptionBtn = By.xpath("//*[@id=\"subscribe\"]");
    By successMsg = By.id("success-subscribe"); 
    
    public void clickProducts() {
    	driver.findElement(products).click(); 
        }
    public void clickSignupLogin() {
    	driver.findElement(signupLogin).click(); 
    	}
    public void clickContactUs() { 
    	driver.findElement(contactUs).click();
    	}
    public void clickCart() {
    	driver.findElement(cart).click();
    	}
    public void clickVideoTutorials() { 
    	driver.findElement(videoTutorials).click(); 
    	}
    public void clickTestCases() { 
    	driver.findElement(testCases).click(); 
    	}
    public void clickApiTesting() {
    	driver.findElement(apiTesting).click();
    	}
    
    public void subscribe(String email) {
        driver.findElement(subscriptionInput).sendKeys(email);
        driver.findElement(subscriptionBtn).click();
    }

    public boolean isSubscriptionSuccessful() {
        return driver.findElement(successMsg).isDisplayed();
    }

    public String getSubscriptionMessage() {
        return driver.findElement(successMsg).getText();
    }
    
}

