package com.mystore.testCases;

import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.PurchaseProductPage;
import com.mystore.utilities.InputData;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseProductModule extends BaseClass {
    PurchaseProductPage purchaseProductPage;
    IndexPage indexPage;

    @Test(description = "Customer can Purchase a Product using valid account")
    public void purchaseProductUsingValidCustomerAccount() throws InterruptedException, IOException {
        purchaseProductPage = new PurchaseProductPage(driver);
        indexPage = new IndexPage(driver);

        purchaseProductPage.purchaseProduct(InputData.productNameForEndToEnd, InputData.quantityForEndToEnd, loginEmail, loginPassword, InputData.commentForEndToEnd, InputData.paymentTypeForEndToEnd);
        if (driver.getPageSource().contains("Your order on My Store is complete.")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
            indexPage.signOutFromAccount();
            Thread.sleep(2000);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "purchaseProductUsingValidCustomerAccount");
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);
        }


    }


}
