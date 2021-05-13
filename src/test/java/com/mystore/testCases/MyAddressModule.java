package com.mystore.testCases;

import com.mystore.pageObjects.AuthPage;
import com.mystore.pageObjects.MyAddressPage;
import com.mystore.utilities.InputData;
import com.mystore.utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyAddressModule extends BaseClass {
    MyAddressPage myAddressPage;
    AuthPage authPage;



    @Test(priority = 1, description = "Customer can update address using valid address information")
    public void updateAddressInfoUsingValidData() throws InterruptedException, IOException {
        myAddressPage = new MyAddressPage(driver);
        myAddressPage.saveUpdateAddressInfo("ontu", InputData.lastName, InputData.company, InputData.address,
                InputData.address2, "Dhaka", "Iowa", InputData.postalCode, InputData.country, InputData.homePhone, InputData.mobilePhone, "xyz", "adabor");
        if (driver.getPageSource().contains("Your addresses are listed below.")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "updateAddressInfoUsingValidData");
            Assert.assertTrue(false);
        }


    }

    @Test(priority = 2, dataProvider = "UpdateCustomerDataProvider", description = "Customer can't update address using Invalid address information")
    public void updateAddressInfoUsingInvalidData(String email,String pass,String firstName, String lastName, String company, String address, String address2, String city, String state, String code, String country, String homePhone, String mobilePhone, String additionalInfo, String reference) throws InterruptedException, IOException {
        myAddressPage = new MyAddressPage(driver);
        authPage= new AuthPage(driver);
        authPage.submitSignInForm(email,pass);
        myAddressPage.saveUpdateAddressInfo(firstName, lastName, company, address, address2, city, state, code, country, homePhone, mobilePhone, additionalInfo, reference);
        Thread.sleep(2000);
        if (driver.getPageSource().contains("Your addresses are listed below.")) {
            logger.warn("Test Failed");
            captureScreen(driver, "updateAddressInfoUsingInvalidData");
            Assert.assertTrue(false);
        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }


    }

    @Test(priority = 3,description ="Customer can delete address information" )
    public void deleteAddressInfo() throws IOException, InterruptedException {
        myAddressPage = new MyAddressPage(driver);
        myAddressPage.deleteAddress();
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }else{
            logger.warn("Test Failed");
            captureScreen(driver,"deleteAddressInfo");
            Assert.assertTrue(false);
        }
    }


    @DataProvider(name = "UpdateCustomerDataProvider")
    public static Object[][] getUpdateCustomerData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/MyAddressModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "UpdateCustomerData");
        int colCount = XLUtils.getCellCount(path, "UpdateCustomerData", 1);
        Object[][] updateCustomerData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                updateCustomerData[i - 1][j] = XLUtils.getCellData(path, "UpdateCustomerData", i, j);
            }

        }
        return updateCustomerData;
    }

}
