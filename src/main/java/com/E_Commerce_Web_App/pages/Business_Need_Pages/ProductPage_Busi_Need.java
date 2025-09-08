package com.E_Commerce_Web_App.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage_Busi_Need {
    WebDriver driver;

    public ProductPage_Busi_Need(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.xpath("//*[@id=\"search_product\"]");
    By searchBtn = By.xpath("//*[@id=\"submit_search\"]");
    By addToCartBtn = By.xpath("(//a[text()='Add to cart'])[1]");
    By viewProductLink = By.xpath("(//a[text()='View Product'])[1]");
    By categoryWomen = By.linkText("WOMEN");
    By categoryMen = By.linkText("MEN");
    By categoryKids = By.linkText("KIDS");
    By brandLink = By.xpath("(//div[@class='brands_products']//a)[1]");

    By reviewName = By.xpath("//*[@id=\"name\"]");
    By reviewEmail = By.id("email");
    By reviewText = By.xpath("//*[@id=\"review\"]");
    By reviewSubmitBtn = By.id("button-review");

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartBtn).click();
    }

    public void viewFirstProduct() {
        driver.findElement(viewProductLink).click();
    }

    public void selectCategoryWomen() { 
    	driver.findElement(categoryWomen).click(); 
    	}
    public void selectCategoryMen() {
    	driver.findElement(categoryMen).click(); 
    	}
    public void selectCategoryKids() { 
    	driver.findElement(categoryKids).click();
    	}
    public void selectBrand() { 
    	driver.findElement(brandLink).click(); 
    	}
    
    public void submitReview(String name, String email, String review) {
        // first click "View Product"
        driver.findElement(viewProductLink).click();
        driver.findElement(reviewName).sendKeys(name);
        driver.findElement(reviewEmail).sendKeys(email);
        driver.findElement(reviewText).sendKeys(review);
        driver.findElement(reviewSubmitBtn).click();
    }
}

