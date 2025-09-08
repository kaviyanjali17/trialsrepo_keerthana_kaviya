package project;



import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class TestCase7_9 {
    WebDriver driver;
    WebDriverWait wait;

    public TestCase7_9(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    By productsLink = By.xpath("//a[@href='/products']");
    By allProductsHeading = By.xpath("//h2[text()='All Products']");
    By viewProductButton = By.xpath("(//a[text()='View Product'])[1]");
    By quantityInput = By.id("quantity");
    By addToCartButton = By.xpath("//button[@type='submit' and contains(@class,'cart')]");
    By cartIcon = By.xpath("//a[@href='/view_cart']");
    By deleteIcon = By.xpath("//a[@class='cart_quantity_delete']");
    By checkoutButton = By.xpath("//a[text()='Proceed To Checkout']");

    // Actions
    public void goToProductsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
    }

    public boolean isAllProductsHeadingVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeading)).isDisplayed();
    }

    public void clickViewProduct() {
        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(viewProductButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewBtn);
    }

    public void updateQuantity(String qty) {
        WebElement qtyBox = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qtyBox.clear();
        qtyBox.sendKeys(qty);
    }

    public void clickAddToCart() {
        try {
            WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
            wait.until(ExpectedConditions.elementToBeClickable(addBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        } catch (Exception e) {
            throw new RuntimeException("Add to cart button not clickable or not found");
        }
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public boolean isDeleteIconVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(deleteIcon)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickDeleteIcon() {
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
    }

    public boolean isCheckoutButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickCheckoutButton() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutBtn.click();
    }
}

