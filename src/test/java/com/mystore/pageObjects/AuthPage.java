package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    WebDriver ldriver;
    IndexPage indexPage;

    public AuthPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    WebElement createAccountEmailElement;

    @FindBy(id = "SubmitCreate")
    WebElement createAccountBtnElement;

    @FindBy(id = "email")
    WebElement signInAndForgotPassEmailElement;

    @FindBy(id = "passwd")
    WebElement signInPasswordElement;

    @FindBy(id = "SubmitLogin")
    WebElement submitLoginBtnElement;

    @FindBy(linkText = "Forgot your password?")
    WebElement forgotPassLinkElement;

    @FindBy(xpath = " //button[@class='btn btn-default button button-medium']")
    WebElement retrievePassBtnElement;


    public void submitAuthenticationEmailForm(String email) {
        indexPage = new IndexPage(ldriver);
        indexPage.clickOnSignIn();
        createAccountEmailElement.sendKeys(email);
        createAccountBtnElement.click();
    }

    public void submitSignInForm(String email,String password) {
        indexPage = new IndexPage(ldriver);
        indexPage.clickOnSignIn();
        signInAndForgotPassEmailElement.sendKeys(email);
        signInPasswordElement.sendKeys(password);
        submitLoginBtnElement.click();
    }

    public void submitForgetPassEmailForm(String email){
        indexPage = new IndexPage(ldriver);
        indexPage.clickOnSignIn();
        forgotPassLinkElement.click();
        signInAndForgotPassEmailElement.sendKeys(email);
        retrievePassBtnElement.click();
    }


}
