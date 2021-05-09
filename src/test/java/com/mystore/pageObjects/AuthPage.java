package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage {
    WebDriver ldriver;

    public AuthPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "login")
    WebElement signInElement;

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
        signInElement.click();
        createAccountEmailElement.sendKeys(email);
        createAccountBtnElement.click();
    }
}
