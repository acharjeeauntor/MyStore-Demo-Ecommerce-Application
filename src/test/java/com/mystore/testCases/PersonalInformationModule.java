package com.mystore.testCases;


import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.PersonalInfoPage;
import com.mystore.utilities.InputData;
import com.mystore.utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class PersonalInformationModule extends BaseClass {
    PersonalInfoPage personalInfoPage;
    IndexPage indexPage;


    @Test(priority = 1, description = "Customer can update personal information using valid personal data")
    public void updatePersonalInfoUsingValidData() throws InterruptedException, IOException {
        personalInfoPage = new PersonalInfoPage(driver);
        indexPage = new IndexPage(driver);
        personalInfoPage.saveUpdatePersonalInfo(InputData.updateTitle, InputData.updateFirstName, InputData.updateLastName,loginEmail, InputData.updateBirthDay, InputData.updateBirthMonth, InputData.updateBirthYear, loginPassword,InputData.updatePassword,InputData.updatePassword);
        if (driver.getPageSource().contains("Your personal information has been successfully updated.")) {
            logger.info("Test Passed");
            config.setLoginEmailAndPass(loginEmail, InputData.updatePassword);
            Assert.assertTrue(true);
            indexPage.signOutFromAccount();
        } else {
            logger.info("Test Failed");
            captureScreen(driver, "updatePersonalInfoUsingValidData");
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2,dataProvider = "UpdatePersonalInfoDataProvider",description = "Customer can't update personal information using Invalid personal data")
    public void updatePersonalInfoUsingInvalidData(String updateTitle,String updateFirstName,String updateLastName,String updateEmail,String updateBirthDay, String updateBirthMonth, String updateBirthYear,String currentPassword ,String updatePassword,String confirmPassword) throws InterruptedException, IOException {
        personalInfoPage = new PersonalInfoPage(driver);
        indexPage = new IndexPage(driver);
        personalInfoPage.saveUpdatePersonalInfo(updateTitle,updateFirstName,updateLastName,updateEmail,updateBirthDay,updateBirthMonth,updateBirthYear,currentPassword,updatePassword,confirmPassword);
        if (driver.getPageSource().contains("Your personal information has been successfully updated.")) {
            logger.info("Test Failed");
            captureScreen(driver, "updatePersonalInfoUsingInvalidData");
            config.setLoginEmailAndPass(updateEmail,updatePassword);
            indexPage.signOutFromAccount();
            Assert.assertTrue(false);

        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
            indexPage.signOutFromAccount();
        }
    }



    @DataProvider(name = "UpdatePersonalInfoDataProvider")
    public static Object[][] getUpdatePersonalInfo() throws IOException {
        String path = System.getProperty("user.dir")+"/src/test/java/com/mystore/testData/PersonalInfoModuleData.xlsx";
        int rowNum = XLUtils.getRowCount(path,"UpdatePersonalInfoData");
        int colNum = XLUtils.getCellCount(path,"UpdatePersonalInfoData",1);
        Object[][] UpdatePersonalInfo = new Object[rowNum][colNum];

        for(int i =1;i<=rowNum;i++){
            for(int j=0;j<colNum;j++){
                UpdatePersonalInfo[i-1][j] = XLUtils.getCellData(path,"UpdatePersonalInfoData",i,j);
            }
        }
        return UpdatePersonalInfo;
    }

}
