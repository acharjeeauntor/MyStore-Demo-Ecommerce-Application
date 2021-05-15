package com.mystore.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    WebDriver ldriver;
    @FindBy(xpath = "//ul/li[3]/a[text()='T-shirts']")
    WebElement productCategoryElement;
    @FindBy(id = "quantity_wanted")
    WebElement productQuantityElement;
    @FindBy(className = "icon-plus")
    WebElement incrementQuantityBtnElement;
    @FindBy(className = "icon-minus")
    WebElement decrementQuantityBtnElement;
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
    @FindBy(xpath = "//ul/li/a[@class='open-comment-form']")
    WebElement writeReviewLinkElement;

    @FindBy(id = "comment_title")
    WebElement titleElement;
    @FindBy(id = "content")
    WebElement commentElement;
    @FindBy(id = "submitNewMessage")
    WebElement sendBtnElement;
    @FindBy(id = "search_query_top")
    WebElement searchBoxElement;
    @FindBy(name = "submit_search")
    WebElement searchBtnIconElement;







    public ProductPage(WebDriver driver) {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void purchaseAProduct(String productName, String quantity, String comment, String paymentType) throws InterruptedException {
        productCategoryElement.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//h5/a[@title='" + productName + "']")).click();
//        productQuantityElement.clear();
//        productQuantityElement.sendKeys(quantity);
        int qn = Integer.parseInt(productQuantityElement.getAttribute("value"));
        int ourQn = Integer.parseInt(quantity);
        if (ourQn > qn) {
            for (int i = qn; i < ourQn; i++) {
                incrementQuantityBtnElement.click();
            }
        } else if (ourQn < qn) {
            for (int i = qn; i > ourQn; i--) {
                decrementQuantityBtnElement.click();
            }
        }
        addToCartBtnElement.click();
        Thread.sleep(2000);
        proceedToCheckoutTitleBtnElement.click();
        Thread.sleep(2000);
        proceedToCheckoutSpanBtnElement.click();
        Thread.sleep(2000);
        productCommentElement.sendKeys(comment);
        proceedToCheckoutSpanBtnElement.click();
        termsOfServiceCheckboxElement.click();
        ldriver.findElement(By.xpath("//button[@name='processCarrier']")).click();
        ldriver.findElement(By.xpath("//a[@title='" + paymentType + "']")).click();
        Thread.sleep(2000);
        confirmOrderBtnElement.click();
        Thread.sleep(2000);

    }

    public void sendReviewWithValidData(String productName,String star,String title,String comment) throws InterruptedException {
        productCategoryElement.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//h5/a[@title='" + productName + "']")).click();
        writeReviewLinkElement.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//div/a[@title='"+star+"']")).click();
        titleElement.sendKeys(title);
        commentElement.sendKeys(comment);
        sendBtnElement.click();
    }

    public void sendReviewWithEmptyData(String productName) throws InterruptedException {
        productCategoryElement.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//h5/a[@title='" + productName + "']")).click();
        Thread.sleep(2000);
        writeReviewLinkElement.click();
        sendBtnElement.click();
    }

    public void searchProduct(String productName){
        searchBoxElement.clear();
        searchBoxElement.sendKeys(productName);
        searchBtnIconElement.click();
    }




}
