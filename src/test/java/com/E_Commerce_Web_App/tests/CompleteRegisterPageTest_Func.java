package com.E_Commerce_Web_App.tests;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.E_Commerce_Web_App.Base.BaseTest;
import com.E_Commerce_Web_App.pages.CompleteRegisterPage_Func;
import com.E_Commerce_Web_App.utilities.ExcelUtilities;
import com.E_Commerce_Web_App.utilities.ScreenshotUtilities;


public class CompleteRegisterPageTest_Func extends BaseTest {

    static String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "accountData1", priority = 1)
    public void verifySignup( String name, String email) throws IOException {
        driver.get("https://automationexercise.com/");

        test = extent.createTest("Register with: " + name + " | Email: " + email);

        driver.findElement(By.linkText("Signup / Login")).click(); 

        CompleteRegisterPage_Func signupLogin = new CompleteRegisterPage_Func(driver);
        signupLogin.signup(name, email);

        if (driver.getCurrentUrl().contains("signup")) {
            test.pass("Signup navigation successful for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupSuccess"));
        } else {
            test.fail("Signup failed for email: " + email)
                .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SignupFail"));
        }
    }
    
    @Test(dataProvider = "accountData2", priority = 2, dependsOnMethods = {"verifySignup"})
    public void selectTitleTest(String title) throws IOException {

        test = extent.createTest("Select Title : " + title);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.selectTitle(title);

        test.pass("Title selected: " + title)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "TitleSelected"));
    }

    @Test(dataProvider = "accountData3", priority = 3, dependsOnMethods = {"selectTitleTest"})
    public void enterPasswordTest(String password) throws IOException {

        test = extent.createTest("Enter Password");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterPassword(password);

        test.pass("Password entered")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "PasswordEntered"));
    }

    @Test(dataProvider = "accountData4", priority = 4)
    public void selectDateOfBirthTest(String day, String month, String year) throws IOException {

        test = extent.createTest("Select Date of Birth : " + day + "/" + month + "/" + year);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.selectDateOfBirth(day, month, year);

        test.pass("Date of Birth selected: " + day + "/" + month + "/" + year)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "DOBSelected"));
    }

    @Test(priority = 5)
    public void selectNewsletterTest() throws IOException {

        test = extent.createTest("Select Newsletter");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.selectNewsletter();

        test.pass("Newsletter checkbox selected")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "NewsletterSelected"));
    }

    @Test(priority = 6)
    public void verifySpecialOffersCheckbox() throws IOException {

        test = extent.createTest("Verify Special Offers Checkbox");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.selectSpecialOffers();

        test.pass("Special offers checkbox selected")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "SpecialOffers"));
    }
    
    @Test(priority = 7)
    public void verifyAddressHeading() throws IOException {

        test = extent.createTest("Verify Address Information Heading");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);

        Assert.assertTrue(signupPage.isAddressHeadingDisplayed(), "Address Information heading not displayed");

        test.pass("Address Information heading displayed")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "AddressHeading"));
    }
    
    @Test(dataProvider = "accountData5", priority = 8)
    public void enterFirstName(String fname) throws IOException {

        test = extent.createTest("Enter First Name : " + fname);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterFirstName(fname);

        test.pass("First name entered: " + fname)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "FirstName"));
    }
    
    @Test(dataProvider = "accountData6", priority = 9)
    public void enterLastName(String lname) throws IOException {

        test = extent.createTest("Enter Last Name : " + lname);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterLastName(lname);

        test.pass("Last name entered: " + lname)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LastName"));
    }
    
    @Test(dataProvider = "accountData7", priority = 10)
    public void enterCompany(String comp) throws IOException {

        test = extent.createTest("Enter Company : " + comp);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterCompany(comp);

        test.pass("Company entered: " + comp)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Company"));
    }

    @Test(dataProvider = "accountData8", priority = 11)
    public void enterAddress1(String addr) throws IOException {

        test = extent.createTest("Enter Address1 : " + addr);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterAddress1(addr);

        test.pass("Address1 entered: " + addr)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Address1"));
    }

    @Test(dataProvider = "accountData9", priority = 12)
    public void enterAddress2(String addr) throws IOException {

        test = extent.createTest("Enter Address2 : " + addr);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterAddress2(addr);

        test.pass("Address2 entered: " + addr)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Address2"));
    }

    @Test(dataProvider = "accountData10", priority = 13)
    public void selectCountry(String country) throws IOException {

        test = extent.createTest("Select Country : " + country);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.selectCountry(country);

        test.pass("Country selected: " + country)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Country"));
    }

    @Test(dataProvider = "accountData11", priority = 14)
    public void enterState(String st) throws IOException {

        test = extent.createTest("Enter State : " + st);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterState(st);

        test.pass("State entered: " + st)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "State"));
    }

    @Test(dataProvider = "accountData12", priority = 15)
    public void enterCity(String ct) throws IOException {

        test = extent.createTest("Enter City : " + ct);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterCity(ct);

        test.pass("City entered: " + ct)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "City"));
    }

    @Test(dataProvider = "accountData13", priority = 16)
    public void enterZipcode(String zip) throws IOException {

        test = extent.createTest("Enter Zipcode : " + zip);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterZipcode(zip);

        test.pass("Zipcode entered: " + zip)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Zipcode"));
    }

    @Test(dataProvider = "accountData14", priority = 17)
    public void enterMobile(String mob) throws IOException {

        test = extent.createTest("Enter Mobile : " + mob);

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.enterMobile(mob);

        test.pass("Mobile entered: " + mob)
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "Mobile"));
    }
    
    @Test(priority = 18)
    public void clickCreateAccountButton() throws IOException {

        test = extent.createTest("Click 'Create Account' Button");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.clickCreateAccountButton();

        test.pass("'Create Account' button clicked")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "CreateAccountClicked"));
    }
    
    @Test(priority = 19)
    public void verifyAccountCreatedMessage() throws IOException {

        test = extent.createTest("Verify 'ACCOUNT CREATED!' Message");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);

        Assert.assertTrue(signupPage.isAccountCreatedMessageDisplayed(), "'ACCOUNT CREATED!' message not displayed");

        test.pass("'ACCOUNT CREATED!' message is visible")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "AccountCreatedMessage"));
    }
    
    @Test(priority = 20)
    public void clickContinueAfterAccountCreated() throws IOException {

        test = extent.createTest("Click 'Continue' After Account Creation");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.clickContinueAfterAccountCreated();

        test.pass("Redirected to logged-in home page")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomePageAfterSignup"));
    }
    
    @Test(priority = 21)
    public void verifyLoggedInAsUsername() throws IOException {

        test = extent.createTest("Verify 'Logged in as username' Displayed");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);

        Assert.assertTrue(signupPage.isLoggedInAsUsernameDisplayed(), "'Logged in as username' not displayed");

        test.pass("'Logged in as username' is displayed")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "LoggedInUsername"));
    }

    @Test(priority = 22)
    public void clickDeleteAccount() throws IOException {

        test = extent.createTest("Click 'Delete Account' Button");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.clickDeleteAccount();

        test.pass("'Delete Account' button clicked")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "DeleteAccountClicked"));
    }

    @Test(priority = 23)
    public void verifyAccountDeletedMessage() throws IOException {

        test = extent.createTest("Verify 'ACCOUNT DELETED!' Message");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);

        Assert.assertTrue(signupPage.isAccountDeletedMessageDisplayed(), "'ACCOUNT DELETED!' message not displayed");

        test.pass("'ACCOUNT DELETED!' message is visible")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "AccountDeletedMessage"));
    }

    @Test(priority = 24)
    public void clickContinueAfterAccountDeleted() throws IOException {

        test = extent.createTest("Click 'Continue' After Account Deletion");

        CompleteRegisterPage_Func signupPage = new CompleteRegisterPage_Func(driver);
        signupPage.clickContinueAfterAccountDeleted();

        Assert.assertTrue(signupPage.isHomePageDisplayed(), "Home page not displayed after clicking Continue post-deletion");

        test.pass("Redirected to home page after account deletion")
            .addScreenCaptureFromPath(ScreenshotUtilities.capturescreen(driver, "HomePageAfterDeletion"));
    }

    @DataProvider
    public Object[][] accountData1() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Signup");
    }
    
    @DataProvider
    public Object[][] accountData2() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Title");
    }
    
    @DataProvider
    public Object[][] accountData3() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Password");
    }
    
    @DataProvider
    public Object[][] accountData4() throws IOException {
        Object[][] rawData = ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","DOB");
        
        // Always return only the first row
        return new Object[][]{rawData[0]};
    }
    
    @DataProvider
    public Object[][] accountData5() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","FirstName");
    }
    
    @DataProvider
    public Object[][] accountData6() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","LastName");
    }
    
    @DataProvider
    public Object[][] accountData7() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Company");
    }
    
    @DataProvider
    public Object[][] accountData8() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Address1");
    }
    
    @DataProvider
    public Object[][] accountData9() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Address2");
    }
    
    @DataProvider
    public Object[][] accountData10() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Country");
    }
    
    @DataProvider
    public Object[][] accountData11() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","State");
    }
    
    @DataProvider
    public Object[][] accountData12() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","City");
    }
    
    @DataProvider
    public Object[][] accountData13() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Zipcode");
    }
    
    @DataProvider
    public Object[][] accountData14() throws IOException {
        return ExcelUtilities.getdata(projectpath + "\\src\\test\\resources\\E_Commerce_Web_App_Testdata\\datafile2.xlsx","Mobile");
    }
}


