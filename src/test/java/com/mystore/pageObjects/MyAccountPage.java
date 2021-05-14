package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    WebDriver ldriver;
    public MyAccountPage(WebDriver driver) {
        ldriver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[text()='My addresses']")
    WebElement myAddressesOptionElement;
    @FindBy(xpath = "//span[text()='My personal information']")
    WebElement myPersonalInfoOptionElement;



    public void goToMyAddress(){
        myAddressesOptionElement.click();
    }
    public void goToMyPersonalInfo(){
        myPersonalInfoOptionElement.click();
    }
}
