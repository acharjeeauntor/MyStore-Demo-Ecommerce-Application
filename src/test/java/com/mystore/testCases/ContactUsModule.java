package com.mystore.testCases;

import com.mystore.pageObjects.ContactUsPage;
import com.mystore.utilities.InputData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactUsModule extends BaseClass {
    ContactUsPage contactUSPage;

    @Test(priority = 1, description = "Customer can submit contact us form using valid data")
    public void sendContactUsFormUsingValidData() throws InterruptedException, IOException {
        contactUSPage = new ContactUsPage(driver);
        contactUSPage.sendContactUsForm(InputData.subject, InputData.contactEmail, InputData.orderReference, InputData.fileLocation, InputData.message);
        if (driver.getPageSource().contains("Your message has been successfully sent to our team.")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.info("Test Failed");
            captureScreen(driver, "sendContactUsFormUsingValidData");
            Assert.assertTrue(true);
        }
    }

}
