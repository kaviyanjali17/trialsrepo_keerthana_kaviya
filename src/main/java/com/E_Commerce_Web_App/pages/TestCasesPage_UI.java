package com.E_Commerce_Web_App.pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCasesPage_UI {
    WebDriver driver;
    WebDriverWait wait;

    public TestCasesPage_UI(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    By TestCasesLink = By.linkText("Test Cases");
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
    By testCasesSection = By.xpath("//*[@id=\"form\"]/div/div[1]/div/h2/b");

    By scrollToTopBtn = By.id("scrollUp");
    By subscriptionInput = By.id("susbscribe_email");
    By subscriptionButton = By.id("subscribe");
    By subscriptionSuccess = By.xpath("//div[@class='alert-success alert']");

    // Hyperlinks 1 → 26
    public By testCase1Link = By.xpath("//*[@id=\"form\"]/div/div[2]/div/div[1]/h4/a/u");
    public By testCase2Link = By.xpath("//*[@id=\"form\"]/div/div[3]/div/div[1]/h4/a/u");
    public By testCase3Link = By.xpath("//*[@id=\"form\"]/div/div[4]/div/div[1]/h4/a/u");
    public By testCase4Link = By.xpath("//*[@id=\"form\"]/div/div[5]/div/div[1]/h4/a/u");
    public By testCase5Link = By.xpath("//*[@id=\"form\"]/div/div[6]/div/div[1]/h4/a/u");
    public By testCase6Link = By.xpath("//*[@id=\"form\"]/div/div[7]/div/div[1]/h4/a/u");
    public By testCase7Link = By.xpath("//*[@id=\"form\"]/div/div[8]/div/div[1]/h4/a/u");
    public By testCase8Link = By.xpath("//*[@id=\"form\"]/div/div[9]/div/div[1]/h4/a/u");
    public By testCase9Link = By.xpath("//*[@id=\"form\"]/div/div[10]/div/div[1]/h4/a/u");
    public By testCase10Link = By.xpath("//*[@id=\"form\"]/div/div[11]/div/div[1]/h4/a/u");
    public By testCase11Link = By.xpath("//*[@id=\"form\"]/div/div[12]/div/div[1]/h4/a/u");
    public By testCase12Link = By.xpath("//*[@id=\"form\"]/div/div[13]/div/div[1]/h4/a/u");
    public By testCase13Link = By.xpath("//*[@id=\"form\"]/div/div[14]/div/div[1]/h4/a/u");
    public By testCase14Link = By.xpath("//*[@id=\"form\"]/div/div[15]/div/div[1]/h4/a/u");
    public By testCase15Link = By.xpath("//*[@id=\"form\"]/div/div[16]/div/div[1]/h4/a/u");
    public By testCase16Link = By.xpath("//*[@id=\"form\"]/div/div[17]/div/div[1]/h4/a/u");
    public By testCase17Link = By.xpath("//*[@id=\"form\"]/div/div[18]/div/div[1]/h4/a/u");
    public By testCase18Link = By.xpath("//*[@id=\"form\"]/div/div[19]/div/div[1]/h4/a/u");
    public By testCase19Link = By.xpath("//*[@id=\"form\"]/div/div[20]/div/div[1]/h4/a/u");
    public By testCase20Link = By.xpath("//*[@id=\"form\"]/div/div[21]/div/div[1]/h4/a/u");
    public By testCase21Link = By.xpath("//*[@id=\"form\"]/div/div[22]/div/div[1]/h4/a/u");
    public By testCase22Link = By.xpath("//*[@id=\"form\"]/div/div[23]/div/div[1]/h4/a/u");
    public By testCase23Link = By.xpath("//*[@id=\"form\"]/div/div[24]/div/div[1]/h4/a/u");
    public By testCase24Link = By.xpath("//*[@id=\"form\"]/div/div[25]/div/div[1]/h4/a/u");
    public By testCase25Link = By.xpath("//*[@id=\"form\"]/div/div[26]/div/div[1]/h4/a/u");
    public By testCase26Link = By.xpath("//*[@id=\"form\"]/div/div[27]/div/div[1]/h4/a/u");
    

    public void clickTestCases() {
        driver.findElement(TestCasesLink).click();
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
        return driver.findElement(testCasesSection).isDisplayed();
    }

    public boolean isTestCase1LinkDisplayed() {
    	return driver.findElement(testCase1Link).isDisplayed(); 
    }
    
    public boolean isTestCase2LinkDisplayed() { 
    	return driver.findElement(testCase2Link).isDisplayed(); 
    }
    
    public boolean isTestCase3LinkDisplayed() {
    	return driver.findElement(testCase3Link).isDisplayed(); 
    }
    
    public boolean isTestCase4LinkDisplayed() {
    	return driver.findElement(testCase4Link).isDisplayed(); 
    }
    
    public boolean isTestCase5LinkDisplayed() {
    	return driver.findElement(testCase5Link).isDisplayed(); 
    }
    
    public boolean isTestCase6LinkDisplayed() { 
    	return driver.findElement(testCase6Link).isDisplayed();
    }
    
    public boolean isTestCase7LinkDisplayed() {
    	return driver.findElement(testCase7Link).isDisplayed(); 
    }
    
    public boolean isTestCase8LinkDisplayed() { 
    	return driver.findElement(testCase8Link).isDisplayed(); 
    }
    
    public boolean isTestCase9LinkDisplayed() { 
    	return driver.findElement(testCase9Link).isDisplayed(); 
    }
    
    public boolean isTestCase10LinkDisplayed() { 
    	return driver.findElement(testCase10Link).isDisplayed(); 
    }
    
    public boolean isTestCase11LinkDisplayed() { 
    	return driver.findElement(testCase11Link).isDisplayed();
    }
    
    public boolean isTestCase12LinkDisplayed() { 
    	return driver.findElement(testCase12Link).isDisplayed(); 
    }
    
    public boolean isTestCase13LinkDisplayed() { 
    	return driver.findElement(testCase13Link).isDisplayed(); 
    }
    
    public boolean isTestCase14LinkDisplayed() { 
    	return driver.findElement(testCase14Link).isDisplayed(); 
    }
    
    public boolean isTestCase15LinkDisplayed() { 
    	return driver.findElement(testCase15Link).isDisplayed(); 
    }
    
    public boolean isTestCase16LinkDisplayed() { 
    	return driver.findElement(testCase16Link).isDisplayed(); 
    }
    
    public boolean isTestCase17LinkDisplayed() { 
    	return driver.findElement(testCase17Link).isDisplayed(); 
    }
    
    public boolean isTestCase18LinkDisplayed() { 
    	return driver.findElement(testCase18Link).isDisplayed(); 
    }
    
    public boolean isTestCase19LinkDisplayed() { 
    	return driver.findElement(testCase19Link).isDisplayed(); 
    }
    
    public boolean isTestCase20LinkDisplayed() { 
    	return driver.findElement(testCase20Link).isDisplayed();
    }
    
    public boolean isTestCase21LinkDisplayed() { 
    	return driver.findElement(testCase21Link).isDisplayed(); 
    }
    
    public boolean isTestCase22LinkDisplayed() { 
    	return driver.findElement(testCase22Link).isDisplayed(); 
    }
    
    public boolean isTestCase23LinkDisplayed() {
    	return driver.findElement(testCase23Link).isDisplayed();
    }
    
    public boolean isTestCase24LinkDisplayed() { 
    	return driver.findElement(testCase24Link).isDisplayed(); 
    }
    
    public boolean isTestCase25LinkDisplayed() { 
    	return driver.findElement(testCase25Link).isDisplayed(); 
    }
    
    public boolean isTestCase26LinkDisplayed() { 
    	return driver.findElement(testCase26Link).isDisplayed(); 
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