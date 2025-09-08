package com.automationexercise.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Based_impact_TC008 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC008(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToProductsPage() {
    	WebElement productsLink = driver.findElement(By.cssSelector("a[href='/products']"));
    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", productsLink);

    }

    public boolean isAllProductsVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='All Products']")));
            return header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void clickAddToCart() {
        WebElement productCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='product-overlay'])[1]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(productCard).perform();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]")));
        addToCartBtn.click();
    }

    public boolean isCartModalVisible() {
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}

