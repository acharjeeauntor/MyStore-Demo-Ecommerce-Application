package com.mystore.pageObjects;

import com.mystore.utilities.InputData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.mystore.testCases.BaseClass.loginEmail;
import static com.mystore.testCases.BaseClass.loginPassword;

public class MyAddressPage {
    WebDriver ldriver;
    MyAccountPage myAccountPage;
    AuthPage authPage;
    @FindBy(xpath = "//span[text()='Update']")
    WebElement updateBtnElement;
    @FindBy(xpath = "//span[text()='Delete']")
    WebElement deleteBtnElement;
    @FindBy(id = "firstname")
    WebElement firstNameElement;
    @FindBy(id = "lastname")
    WebElement lastNameElement;
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
    @FindBy(id = "phone")
    WebElement homePhoneElement;
    @FindBy(id = "phone_mobile")
    WebElement mobilePhoneElement;
    @FindBy(id = "other")
    WebElement additionalInfoElement;
    @FindBy(id = "alias")
    WebElement futureReferenceElement;
    @FindBy(id = "submitAddress")
    WebElement saveBtnElement;

    public MyAddressPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void saveUpdateAddressInfo(String firstName, String lastName,
                                      String company, String address, String address2, String city, String state, String postalCode, String country, String homePhoneNumber, String mobilePhoneNumber, String additionalInfo, String refferAddress) throws InterruptedException {
        myAccountPage = new MyAccountPage(ldriver);
        authPage = new AuthPage(ldriver);


        //authPage.submitSignInForm(email, password);
        Thread.sleep(2000);
        myAccountPage.goToMyAddress();
        Thread.sleep(2000);
        updateBtnElement.click();
        Thread.sleep(2000);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
        companyElement.clear();
        companyElement.sendKeys(company);
        address1Element.clear();
        address1Element.sendKeys(address);
        address2Element.clear();
        address2Element.sendKeys(address2);
        cityElement.clear();
        cityElement.sendKeys(city);
        Select selectState = new Select(stateElement);
        selectState.selectByVisibleText(state);
        postcodeElement.clear();
        postcodeElement.sendKeys(postalCode);
        Thread.sleep(2000);
        Select selectCountry = new Select(countryElement);
        selectCountry.selectByVisibleText(country);
        homePhoneElement.clear();
        homePhoneElement.sendKeys(homePhoneNumber);
        mobilePhoneElement.clear();
        mobilePhoneElement.sendKeys(mobilePhoneNumber);
        additionalInfoElement.clear();
        additionalInfoElement.sendKeys(additionalInfo);
        futureReferenceElement.clear();
        futureReferenceElement.sendKeys(refferAddress);
        Thread.sleep(2000);
        saveBtnElement.click();


    }

    public void deleteAddress() throws InterruptedException {
        authPage = new AuthPage(ldriver);
        myAccountPage = new MyAccountPage(ldriver);
        authPage.submitSignInForm(loginEmail,loginPassword);
        myAccountPage.goToMyAddress();
        Thread.sleep(2000);
        deleteBtnElement.click();
    }

}
