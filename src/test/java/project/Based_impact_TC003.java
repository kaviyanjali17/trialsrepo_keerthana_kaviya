package project;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Based_impact_TC003 {
    WebDriver driver;

    public Based_impact_TC003(WebDriver driver) {
        this.driver = driver;
    }

    // Existing methods (e.g., for signup, contact, etc.)

    // ✅ Add these login methods below
    public void enterLoginEmail(String email) {
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }
}
