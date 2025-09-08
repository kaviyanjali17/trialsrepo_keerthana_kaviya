package project;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class Based_impact_TC001 {
    WebDriver driver;
    WebDriverWait wait;

    public Based_impact_TC001(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Locators
    By products = By.xpath("//a[@href='/products']");
    By signupLogin = By.xpath("//a[@href='/login']");
    By contactUs = By.xpath("//a[@href='/contact_us']");
    By cart = By.xpath("//a[@href='/view_cart']");
    By testCases = By.xpath("//a[@href='/test_cases']");
    By apiTesting = By.xpath("//a[@href='/api_list']");
    By videoTutorials = By.xpath("//a[@href='/video_tutorials']");

    public void clickLink(String linkName) {
        By locator;
        switch (linkName.toLowerCase()) {
            case "products": locator = products; break;
            case "signup/login": locator = signupLogin; break;
            case "contact us": locator = contactUs; break;
            case "cart": locator = cart; break;
            case "test cases": locator = testCases; break;
            case "api testing": locator = apiTesting; break;
            case "video tutorials": locator = videoTutorials; break;
            default: throw new IllegalArgumentException("Invalid link: " + linkName);
        }
        try {
            Thread.sleep(3000); // Let page settle
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            try {
                element.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }

        } catch (Exception e) {
            throw new RuntimeException("Element '" + linkName + "' not clickable within timeout. Reason: " + e.getMessage());
        }
    }

    public String getPageHeading() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2"))).getText();
        } catch (Exception e) {
            return driver.getTitle(); // fallback
        }
    }
}


