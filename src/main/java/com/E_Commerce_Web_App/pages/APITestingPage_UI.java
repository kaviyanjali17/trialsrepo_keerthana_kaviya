package com.E_Commerce_Web_App.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class APITestingPage_UI {
	WebDriver driver;
    WebDriverWait wait;

    public APITestingPage_UI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    By APITestingLink = By.linkText("API Testing");
    By logo = By.xpath("//*[@id=\"header\"]/div/div/div/div[1]/div/a/img");
    By productsIcon = By.xpath("//a[@href='/products']");
    By cartIcon = By.xpath("//a[@href='/view_cart']");
    By signupLoginIcon = By.xpath("//a[@href='/login']");
    By videoTutorialsIcon = By.xpath("//a[contains(text(),'Video Tutorials')]");
    By homeIcon = By.xpath("//a[@href='/']");
    By contactUsIcon = By.xpath("//a[@href='/contact_us']");
    By apiTestingIcon = By.xpath("//a[contains(text(),'API Testing')]");
    By feedbackSection = By.xpath("//div[@id='feedback']");
    By feedbackEmail = By.xpath("//div[@id=\"feedback\"]//a[contains(@href,'mailto:')]");
    By ApilistSection = By.xpath("//*[@id=\"form\"]/div/div[1]/div/h2/b");
    
    By scrollToTopBtn = By.id("scrollUp");
    By subscriptionInput = By.id("susbscribe_email");
    By subscriptionButton = By.id("subscribe");
    By subscriptionSuccess = By.xpath("//div[@class='alert-success alert']");

    // Hyperlinks 1 → 14
    public By ApiList1Link = By.xpath("//*[@id=\"form\"]/div/div[2]/div/div[1]/h4/a/u");
    public By ApiList2Link = By.xpath("//*[@id=\"form\"]/div/div[3]/div/div[1]/h4/a/u");
    public By ApiList3Link = By.xpath("//*[@id=\"form\"]/div/div[4]/div/div[1]/h4/a/u");
    public By ApiList4Link = By.xpath("//*[@id=\"form\"]/div/div[5]/div/div[1]/h4/a/u");
    public By ApiList5Link = By.xpath("//*[@id=\"form\"]/div/div[6]/div/div[1]/h4/a/u");
    public By ApiList6Link = By.xpath("//*[@id=\"form\"]/div/div[7]/div/div[1]/h4/a/u");
    public By ApiList7Link = By.xpath("//*[@id=\"form\"]/div/div[8]/div/div[1]/h4/a/u");
    public By ApiList8Link = By.xpath("//*[@id=\"form\"]/div/div[9]/div/div[1]/h4/a/u");
    public By ApiList9Link = By.xpath("//*[@id=\"form\"]/div/div[10]/div/div[1]/h4/a/u");
    public By ApiList10Link = By.xpath("//*[@id=\"form\"]/div/div[11]/div/div[1]/h4/a/u");
    public By ApiList11Link = By.xpath("//*[@id=\"form\"]/div/div[12]/div/div[1]/h4/a/u");
    public By ApiList12Link = By.xpath("//*[@id=\"form\"]/div/div[13]/div/div[1]/h4/a/u");
    public By ApiList13Link = By.xpath("//*[@id=\"form\"]/div/div[14]/div/div[1]/h4/a/u");
    public By ApiList14Link = By.xpath("//*[@id=\"form\"]/div/div[15]/div/div[1]/h4/a/u");
    
    public void clickApiTesting() {
        driver.findElement(APITestingLink).click();
    }
    
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public boolean isProductsIconDisplayed() {
        return driver.findElement(productsIcon).isDisplayed();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public boolean isSignupLoginIconDisplayed() {
        return driver.findElement(signupLoginIcon).isDisplayed();
    }

    public boolean isVideoTutorialsIconDisplayed() {
        return driver.findElement(videoTutorialsIcon).isDisplayed();
    }

    public boolean isHomeIconDisplayed() {
        return driver.findElement(homeIcon).isDisplayed();
    }

    public boolean isContactUsIconDisplayed() {
        return driver.findElement(contactUsIcon).isDisplayed();
    }

    public boolean isApiTestingIconDisplayed() {
        return driver.findElement(apiTestingIcon).isDisplayed();
    }

    public boolean isFeedbackSectionDisplayed() {
        return driver.findElement(feedbackSection).isDisplayed();
    }

    public boolean isFeedbackEmailDisplayed() {
        return driver.findElement(feedbackEmail).isDisplayed();
    }

    public boolean isTestCasesSectionDisplayed() {
        return driver.findElement(ApilistSection).isDisplayed();
    }

    public boolean isApiList1LinkDisplayed() {
    	return driver.findElement(ApiList1Link).isDisplayed(); 
    }
    
    public boolean isApiList2LinkDisplayed() { 
    	return driver.findElement(ApiList2Link).isDisplayed(); 
    }
    
    public boolean isApiList3LinkDisplayed() {
    	return driver.findElement(ApiList3Link).isDisplayed(); 
    }
    
    public boolean isApiList4LinkDisplayed() {
    	return driver.findElement(ApiList4Link).isDisplayed(); 
    }
    
    public boolean isApiList5LinkDisplayed() {
    	return driver.findElement(ApiList5Link).isDisplayed(); 
    }
    
    public boolean isApiList6LinkDisplayed() { 
    	return driver.findElement(ApiList6Link).isDisplayed();
    }
    
    public boolean isApiList7LinkDisplayed() {
    	return driver.findElement(ApiList7Link).isDisplayed(); 
    }
    
    public boolean isApiList8LinkDisplayed() { 
    	return driver.findElement(ApiList8Link).isDisplayed(); 
    }
    
    public boolean isApiList9LinkDisplayed() { 
    	return driver.findElement(ApiList9Link).isDisplayed(); 
    }
    
    public boolean isApiList10LinkDisplayed() { 
    	return driver.findElement(ApiList10Link).isDisplayed(); 
    }
    
    public boolean isApiList11LinkDisplayed() { 
    	return driver.findElement(ApiList11Link).isDisplayed();
    }
    
    public boolean isApiList12LinkDisplayed() { 
    	return driver.findElement(ApiList12Link).isDisplayed(); 
    }
    
    public boolean isApiList13LinkDisplayed() { 
    	return driver.findElement(ApiList13Link).isDisplayed(); 
    }
    
    public boolean isApiList14LinkDisplayed() { 
    	return driver.findElement(ApiList14Link).isDisplayed(); 
    }
    
    public void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickScrollToTop() {
        driver.findElement(scrollToTopBtn).click();
    }

    public void enterSubscription(String email) {
        WebElement element = driver.findElement(subscriptionInput);
        element.clear();
        element.sendKeys(email);
    }

    public void clickSubscriptionButton() {
        driver.findElement(subscriptionButton).click();
    }

    public boolean isSubscriptionSuccessDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccess)).isDisplayed();
    }
}
