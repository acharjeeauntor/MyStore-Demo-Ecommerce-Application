package com.mystore.testCases;

import com.mystore.pageObjects.ContactUsPage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.utilities.InputData;
import com.mystore.utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ContactUsModule extends BaseClass {
    ContactUsPage contactUSPage;

    @DataProvider(name = "ContactUsDataProvider")
    public static Object[][] getContactUsData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/ContactUsModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "ContactUsInfoData");
        int colCount = XLUtils.getCellCount(path, "ContactUsInfoData", 1);
        Object[][] ContactUsData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                ContactUsData[i - 1][j] = XLUtils.getCellData(path, "ContactUsInfoData", i, j);
            }

        }
        return ContactUsData;
    }

    @Test(priority = 1, description = "Customer can submit contact us form using valid data")
    public void sendContactUsFormUsingValidData() throws InterruptedException, IOException {
        contactUSPage = new ContactUsPage(driver);
        contactUSPage.sendContactUsForm(InputData.subject, InputData.contactEmail, InputData.orderReference, InputData.fileName, InputData.message);
        if (driver.getPageSource().contains("Your message has been successfully sent to our team.")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.info("Test Failed");
            captureScreen(driver, "sendContactUsFormUsingValidData");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2, dataProvider = "ContactUsDataProvider", description = "Customer can't submit contact us form using Invalid data")
    public void sendContactUsFormUsingInvalidData(String subject, String contactEmail, String orderReference, String fileName, String message) throws InterruptedException, IOException {
        contactUSPage = new ContactUsPage(driver);
        contactUSPage.sendContactUsForm(subject, contactEmail, orderReference, fileName, message);
        if (driver.getPageSource().contains("Your message has been successfully sent to our team.")) {
            logger.info("Test Failed");
            captureScreen(driver, "sendContactUsFormUsingInvalidData");
            Assert.assertTrue(false);
        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }
    }

}
