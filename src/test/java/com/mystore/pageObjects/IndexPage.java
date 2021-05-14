package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

    WebDriver ldriver;
    @FindBy(className = "login")
    WebElement signInElement;
    @FindBy(id = "contact-link")
    WebElement contactLinkElement;
    @FindBy(className = "logout")
    WebElement signOutElement;


    public IndexPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnSignIn() {
        signInElement.click();
    }

    public void clickOnContactUs() {
        contactLinkElement.click();
    }

    public void signOutFromAccount() {
        signOutElement.click();
    }
}
