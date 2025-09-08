package com.E_Commerce_Web_App.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage_Freq {
	    WebDriver driver;

	    public SignupLoginPage_Freq(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Signup
	    By nameField = By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]");
	    By emailField = By.xpath("//input[@data-qa='signup-email']");
	    By signupButton = By.xpath("//button[@data-qa='signup-button']");

	    // Login
	    By loginEmail = By.xpath("//input[@data-qa='login-email']");
	    By loginPassword = By.xpath("//input[@data-qa='login-password']");
	    By loginButton = By.xpath("//button[@data-qa='login-button']");

	    By logoutBtn = By.linkText("Logout");
	    
	    public void signup(String name, String email) {
	        driver.findElement(nameField).sendKeys(name);
	        driver.findElement(emailField).sendKeys(email);
	        driver.findElement(signupButton).click();
	    }

	    public void login(String email, String password) {
	        driver.findElement(loginEmail).sendKeys(email);
	        driver.findElement(loginPassword).sendKeys(password);
	        driver.findElement(loginButton).click();
	    }
	    
	    public void logout() {
	        driver.findElement(logoutBtn).click();
	    }

	    public boolean isLogoutVisible() {
	        return driver.findElement(logoutBtn).isDisplayed();
	    }
	    
}
