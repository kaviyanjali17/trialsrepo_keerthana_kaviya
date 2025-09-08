package com.automationexercise.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Based_impact_TC002 {
    WebDriver driver;

    // Constructor to initialize WebDriver
    public Based_impact_TC002(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to Signup/Login page
    public void clickSignupLogin() {
        WebElement signupLoginLink = driver.findElement(By.linkText("Signup / Login"));
        signupLoginLink.click();
    }

    // Enter name in Signup form
    public void enterSignupName(String name) {
        WebElement nameField = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));
        nameField.clear();
        nameField.sendKeys(name);
    }

    // Enter email in Signup form
    public void enterSignupEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        emailField.clear();
        emailField.sendKeys(email);
    }

    // Click Signup button
    public void clickSignupButton() {
        WebElement signupBtn = driver.findElement(By.xpath("//button[text()='Signup']"));
        signupBtn.click();
    }

    // Navigate to Products page
    public void goToProductsPage() {
        driver.findElement(By.linkText("Products")).click();
    }

    // Check if ALL PRODUCTS section is visible
    public boolean isAllProductsVisible() {
        return driver.findElement(By.xpath("//h2[text()='All Products']")).isDisplayed();
    }

    // Click Add to Cart button
    public void clickAddToCart() {
        driver.findElement(By.xpath("//a[text()='Add to cart']")).click();
    }

    // Navigate to Contact Us page
    public void goToContactPage() {
        driver.findElement(By.linkText("Contact us")).click();
    }

    // Fill Contact Us form
    public void fillContactForm(String name, String email, String message) {
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("subject")).sendKeys("Test Subject");
        driver.findElement(By.name("message")).sendKeys(message);
    }

    // Click Submit button on Contact Us form
    public void clickSubmit() {
        driver.findElement(By.name("submit")).click();
    }
}

