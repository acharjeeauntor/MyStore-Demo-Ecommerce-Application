package com.mystore.pageObjects;

import com.mystore.testCases.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.mystore.testCases.BaseClass.loginEmail;
import static com.mystore.testCases.BaseClass.loginPassword;


public class PersonalInfoPage  {
    WebDriver ldriver;
    AuthPage authPage;
    MyAccountPage myAccountPage;

    public PersonalInfoPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "uniform-id_gender1")
    WebElement mrRadioBtnElement;
    @FindBy(id = "uniform-id_gender2")
    WebElement mrsRadioBtnElement;
    @FindBy(id = "firstname")
    WebElement firstNameElement;
    @FindBy(id = "lastname")
    WebElement lastNameElement;
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

public void saveUpdatePersonalInfo(String title, String fName, String lName, String email,
                                   String birthDay, String birthMonth, String birthYear,String currentPass,String newPass,String confirmPass) throws InterruptedException {
    myAccountPage = new MyAccountPage(ldriver);
    authPage = new AuthPage(ldriver);
    Thread.sleep(1000);
    authPage.submitSignInForm(loginEmail, loginPassword);
    Thread.sleep(2000);
    myAccountPage.goToMyPersonalInfo();
    Thread.sleep(2000);
    if (title.equals("Mr")) {
        mrRadioBtnElement.click();
    } else {
        mrsRadioBtnElement.click();
    }
    firstNameElement.clear();
    firstNameElement.sendKeys(fName);
    lastNameElement.clear();
    lastNameElement.sendKeys(lName);
    emailElement.clear();
    emailElement.sendKeys(email);
    Thread.sleep(1000);
    Select selectDay = new Select(birthDayElement);
    selectDay.selectByValue(birthDay);
    Thread.sleep(1000);
    Select selectMonth = new Select(birthMonthElement);
    selectMonth.selectByVisibleText(birthMonth);
    Thread.sleep(1000);
    Select selectYear = new Select(birthYearElement);
    selectYear.selectByValue(birthYear);
    currentPasswordElement.sendKeys(currentPass);
    newPasswordElement.sendKeys(newPass);
    confirmPasswordElement.sendKeys(confirmPass);
    Thread.sleep(2000);
    saveBtnElement.click();
}


}
