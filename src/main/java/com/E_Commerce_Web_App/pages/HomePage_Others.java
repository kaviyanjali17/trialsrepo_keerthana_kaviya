package com.E_Commerce_Web_App.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage_Others {
    WebDriver driver;

    public HomePage_Others(WebDriver driver) {
        this.driver = driver;
    }

    By recommendedProductsSection = By.xpath("//h2[text()='recommended items']");

    public boolean isRecommendedProductsDisplayed() {
        return driver.findElement(recommendedProductsSection).isDisplayed();
    }
}
