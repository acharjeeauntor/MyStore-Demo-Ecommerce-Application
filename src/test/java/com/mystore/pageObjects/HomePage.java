package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver ldriver;


    public HomePage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "login")
    WebElement signInElement;









}
