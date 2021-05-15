package com.mystore.testCases;

import com.mystore.pageObjects.AuthPage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.ProductPage;
import com.mystore.utilities.InputData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductModule extends BaseClass {
    AuthPage authPage;
    IndexPage indexPage;
    ProductPage productPage;

    @Test(priority = 1, description = "Customer can purchase Product using valid data")
    public void purchaseProductUsingValidData() throws InterruptedException, IOException {
        authPage = new AuthPage(driver);
        indexPage = new IndexPage(driver);
        productPage = new ProductPage(driver);

        authPage.submitSignInForm(loginEmail, loginPassword);
        Thread.sleep(2000);
        productPage.purchaseAProduct(InputData.productName, InputData.quantity, InputData.comment, InputData.paymentType);
        if (driver.getPageSource().contains("Your order on My Store is complete.")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
            indexPage.signOutFromAccount();
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "purchaseProductUsingValidData");
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);
        }

    }

    @Test(priority = 2, description = "Customer can give a review using valid data")
    public void reviewProductUsingValidData() throws InterruptedException, IOException {
        authPage = new AuthPage(driver);
        indexPage = new IndexPage(driver);
        productPage = new ProductPage(driver);

        authPage.submitSignInForm(loginEmail, loginPassword);
        Thread.sleep(3000);
        productPage.sendReviewWithValidData(InputData.productName, InputData.star, InputData.reviewTitle, InputData.reviewComment);
        Thread.sleep(2000);
        if (driver.findElement(By.xpath("//h2[text()='New comment']")).isDisplayed()) {
            logger.info("Test Passed");
            driver.findElement(By.xpath("//span[text()='OK']")).click();
            Thread.sleep(2000);
            Assert.assertTrue(true);
            indexPage.signOutFromAccount();
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "reviewProductUsingValidData");
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);
        }

    }

    @Test(priority = 3, description = "Customer can't give a review using empty field")
    public void reviewProductUsingEmptyField() throws InterruptedException, IOException {
        authPage = new AuthPage(driver);
        indexPage = new IndexPage(driver);
        productPage = new ProductPage(driver);

        authPage.submitSignInForm(loginEmail, loginPassword);
        Thread.sleep(3000);
        productPage.sendReviewWithEmptyData(InputData.productName);
        Thread.sleep(2000);
        if (driver.findElement(By.xpath("//h2[text()='Write a review']")).isDisplayed()) {
            logger.info("Test Passed");
            driver.findElement(By.xpath("//a[@title='Close']")).click();
            Assert.assertTrue(true);
            Thread.sleep(2000);
            indexPage.signOutFromAccount();
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "reviewProductUsingEmptyField");
            driver.findElement(By.xpath("//span[text()='OK']")).click();
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);
        }

    }

    @Test(priority = 4, description = "Customer can search a product using valid data")
    public void searchProductUsingValidData() throws InterruptedException, IOException {
        productPage = new ProductPage(driver);
        productPage.searchProduct(InputData.productName);
        Thread.sleep(2000);

        if (driver.getPageSource().contains(InputData.productName) || driver.getPageSource().contains("No results were found for your search \"" + InputData.productName + "\" ")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else if (driver.getPageSource().contains("Please enter a search keyword ")) {
            logger.warn("Test Failed");
            captureScreen(driver, "searchProductUsingValidData");
            Assert.assertTrue(false);
        }

    }

    @Test(priority = 5, description = "Customer can't search a product using Empty field")
    public void searchProductUsingEmptyField() throws InterruptedException, IOException {
        productPage = new ProductPage(driver);
        productPage.searchProduct("");
        Thread.sleep(2000);
        if (driver.getPageSource().contains("Please enter a search keyword")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "searchProductUsingEmptyField");
            Assert.assertTrue(false);
        }

    }

}
