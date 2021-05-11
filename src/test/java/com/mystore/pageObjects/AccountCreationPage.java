package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage {
    WebDriver ldriver;

    public AccountCreationPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "uniform-id_gender1")
    WebElement mrRadioBtnElement;

    @FindBy(id = "uniform-id_gender2")
    WebElement mrsRadioBtnElement;

    @FindBy(id = "customer_firstname")
    WebElement firstNameElement;

    @FindBy(id = "customer_lastname")
    WebElement lastNameElement;

    @FindBy(id = "email")
    WebElement emailElement;

    @FindBy(id = "passwd")
    WebElement passwordElement;

    @FindBy(id = "days")
    WebElement birthDayElement;

    @FindBy(id = "months")
    WebElement birthMonthElement;

    @FindBy(id = "years")
    WebElement birthYearElement;

    @FindBy(id = "firstname")
    WebElement addressFirstNameElement;

    @FindBy(id = "lastname")
    WebElement addressLastNameElement;

    @FindBy(id = "company")
    WebElement companyElement;

    @FindBy(id = "address1")
    WebElement address1Element;

    @FindBy(id = "address2")
    WebElement address2Element;

    @FindBy(id = "city")
    WebElement cityElement;

    @FindBy(id = "id_state")
    WebElement stateElement;

    @FindBy(id = "postcode")
    WebElement postcodeElement;

    @FindBy(id = "id_country")
    WebElement countryElement;

    @FindBy(id = "other")
    WebElement additionalInfoElement;

    @FindBy(id = "phone")
    WebElement homePhoneElement;

    @FindBy(id = "phone_mobile")
    WebElement mobilePhoneElement;

    @FindBy(id = "alias")
    WebElement futureReferenceElement;

    @FindBy(id = "submitAccount")
    WebElement registerBtnElement;

    public void submitAccountCreationForm(String title, String fName, String lName, String email, String pass,
                                          String birthDay, String birthMonth, String birthYear, String addressFName, String addressLName,
                                          String company, String address, String address2, String city, String state, String postalCode, String country, String additionalInfo, String homePhoneNumber, String mobilePhoneNumber, String refferAddress) throws InterruptedException {

        // Fill up all personal information and address information
        if (title.equals("Mr")) {
            mrRadioBtnElement.click();
        } else {
            mrsRadioBtnElement.click();
        }
        firstNameElement.sendKeys(fName);
        lastNameElement.sendKeys(lName);
        emailElement.clear();
        emailElement.sendKeys(email);
        passwordElement.sendKeys(pass);
        Thread.sleep(2000);
        Select selectDay = new Select(birthDayElement);
        selectDay.selectByValue(birthDay);
        Thread.sleep(2000);
        Select selectMonth = new Select(birthMonthElement);
        selectMonth.selectByVisibleText(birthMonth);
        Thread.sleep(2000);
        Select selectYear = new Select(birthYearElement);
        selectYear.selectByValue(birthYear);
        addressFirstNameElement.clear();
        addressFirstNameElement.sendKeys(addressFName);
        addressLastNameElement.clear();
        addressLastNameElement.sendKeys(addressLName);
        companyElement.sendKeys(company);
        address1Element.sendKeys(address);
        address2Element.sendKeys(address2);
        Thread.sleep(2000);
        cityElement.sendKeys(city);
        Select selectState = new Select(stateElement);
        Thread.sleep(2000);
        selectState.selectByVisibleText(state);
        postcodeElement.sendKeys(postalCode);
        Thread.sleep(2000);
        Select selectCountry = new Select(countryElement);
        selectCountry.selectByVisibleText(country);
        additionalInfoElement.sendKeys(additionalInfo);
        homePhoneElement.sendKeys(homePhoneNumber);
        mobilePhoneElement.sendKeys(mobilePhoneNumber);
        futureReferenceElement.clear();
        futureReferenceElement.sendKeys(refferAddress);
        Thread.sleep(2000);
        registerBtnElement.click();
    }

}




