package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalInfoPage {
    WebDriver ldriver;

    public PersonalInfoPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "uniform-id_gender1")
    WebElement mrRadioBtnElement;
    @FindBy(id = "uniform-id_gender2")
    WebElement mrsRadioBtnElement;
    @FindBy(id = "firstname")
    WebElement addressFirstNameElement;
    @FindBy(id = "lastname")
    WebElement addressLastNameElement;
    @FindBy(id = "email")
    WebElement emailElement;
    @FindBy(id = "days")
    WebElement birthDayElement;
    @FindBy(id = "months")
    WebElement birthMonthElement;
    @FindBy(id = "years")
    WebElement birthYearElement;
    @FindBy(id = "old_passwd")
    WebElement currentPasswordElement;
    @FindBy(id = "passwd")
    WebElement newPasswordElement;
    @FindBy(id = "confirmation")
    WebElement confirmPasswordElement;
    @FindBy(name = "submitIdentity")
    WebElement saveBtnElement;










}
