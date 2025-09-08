package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class Based_impact_TC007 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC007(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Signup / Login"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']"))).sendKeys(email);
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
    }

    public void goToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products"))).click();
    }

    public void addFirstProductToCart() {
        WebElement productCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-overlay'])[1]")));
        new Actions(driver).moveToElement(productCard).perform();
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]")));
        addToCartBtn.click();
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']")));
        continueBtn.click();
    }

    public void goToCartPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart"))).click();
    }

    public boolean isItemPresentInCart() {
        return driver.findElements(By.xpath("//tr[contains(@id,'product-')]")).size() > 0;
    }

    public void clickDeleteIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='cart_quantity_delete']"))).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.xpath("//tr[contains(@id,'product-')]")).size() == 0;
    }
}


