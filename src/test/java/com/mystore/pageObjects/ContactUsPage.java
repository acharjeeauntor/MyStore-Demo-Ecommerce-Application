package com.mystore.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {
    WebDriver ldriver;
    IndexPage indexPage;

    @FindBy(id = "id_contact")
    WebElement subjectHeadingElement;
    @FindBy(id = "email")
    WebElement emailElement;
    @FindBy(id = "id_order")
    WebElement orderRefElement;
    @FindBy(id = "fileUpload")
    WebElement fileElement;
    @FindBy(id = "message")
    WebElement messageElement;
    @FindBy(id = "submitMessage")
    WebElement sendBtnElement;

    public ContactUsPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendContactUsForm(String sub, String email, String orderRef, String fileLocation, String msg) throws InterruptedException {
        indexPage = new IndexPage(ldriver);
        indexPage.clickOnContactUs();
        Thread.sleep(2000);
        Select subjectSelect = new Select(subjectHeadingElement);
        subjectSelect.selectByVisibleText(sub);
        emailElement.sendKeys(email);
        orderRefElement.sendKeys(orderRef);
        fileElement.sendKeys(fileLocation);
        messageElement.sendKeys(msg);
        Thread.sleep(2000);
        sendBtnElement.click();
    }


}
