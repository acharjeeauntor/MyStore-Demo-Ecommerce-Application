package com.mystore.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchaseProductPage {
    WebDriver ldriver;
    @FindBy(id = "search_query_top")
    WebElement searchBoxElement;
    @FindBy(name = "submit_search")
    WebElement searchBtnIconElement;
    @FindBy(id = "quantity_wanted")
    WebElement productQuantityElement;
    @FindBy(className = "icon-plus")
    WebElement incrementQuantityBtnElement;
    @FindBy(xpath = "//span[text()='Add to cart']")
    WebElement addToCartBtnElement;
    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    WebElement proceedToCheckoutTitleBtnElement;
    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    WebElement proceedToCheckoutSpanBtnElement;
    @FindBy(name = "message")
    WebElement productCommentElement;
    @FindBy(id = "cgv")
    WebElement termsOfServiceCheckboxElement;
    @FindBy(xpath = "//span[text()='I confirm my order']")
    WebElement confirmOrderBtnElement;
    @FindBy(id = "email")
    WebElement signInEmailElement;
    @FindBy(id = "passwd")
    WebElement signInPasswordElement;
    @FindBy(id = "SubmitLogin")
    WebElement submitLoginBtnElement;




    public PurchaseProductPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void purchaseProduct(String productName, String quantity, String email, String password, String comment, String paymentType) throws InterruptedException {
        searchBoxElement.sendKeys(productName);
        searchBtnIconElement.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//h5/a[@title='" + productName + "']")).click();
        int qn = Integer.parseInt(productQuantityElement.getAttribute("value"));
        int ourQn = Integer.parseInt(quantity);
        if (ourQn > qn) {
            for (int i = qn; i < ourQn; i++) {
                incrementQuantityBtnElement.click();
            }
        }
        addToCartBtnElement.click();
        Thread.sleep(2000);
        proceedToCheckoutTitleBtnElement.click();
        Thread.sleep(2000);
        proceedToCheckoutSpanBtnElement.click();
        Thread.sleep(2000);
        signInEmailElement.sendKeys(email);
        signInPasswordElement.sendKeys(password);
        submitLoginBtnElement.click();
        Thread.sleep(2000);
        productCommentElement.sendKeys(comment);
        proceedToCheckoutSpanBtnElement.click();
        termsOfServiceCheckboxElement.click();
        ldriver.findElement(By.xpath("//button[@name='processCarrier']")).click();
        ldriver.findElement(By.xpath("//a[@title='" + paymentType + "']")).click();
        Thread.sleep(2000);
        confirmOrderBtnElement.click();
    }


}
