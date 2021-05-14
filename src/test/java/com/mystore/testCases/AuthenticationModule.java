package com.mystore.testCases;

import com.mystore.pageObjects.AccountCreationPage;
import com.mystore.pageObjects.AuthPage;
import com.mystore.utilities.InputData;
import com.mystore.utilities.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthenticationModule extends BaseClass {
    public String email = randomestring() + "@gmail.com";
    AuthPage authPage;
    AccountCreationPage accountCreationPage;

    @DataProvider(name = "EmailCheckDataProvider")
    public static Object[][] getCheckEmailData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/AuthenticationModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "EmailCheckData");
        int colCount = XLUtils.getCellCount(path, "EmailCheckData", 1);
        Object[][] emailData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                emailData[i - 1][j] = XLUtils.getCellData(path, "EmailCheckData", i, j);
            }

        }
        return emailData;
    }

    @DataProvider(name = "CustomerInfoDataProvider")
    public static Object[][] getCustomerInfoData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/AuthenticationModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "CustomerInfoData");
        int colCount = XLUtils.getCellCount(path, "CustomerInfoData", 1);
        Object[][] customerData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                customerData[i - 1][j] = XLUtils.getCellData(path, "CustomerInfoData", i, j);
            }

        }
        return customerData;
    }

    @DataProvider(name = "CustomerSignInDataProvider")
    public static Object[][] getCustomerSignInData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/AuthenticationModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "SignInData");
        int colCount = XLUtils.getCellCount(path, "SignInData", 1);
        Object[][] customerSignInData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                customerSignInData[i - 1][j] = XLUtils.getCellData(path, "SignInData", i, j);
            }

        }
        return customerSignInData;
    }

    @DataProvider(name = "ResetPasswordDataProvider")
    public static Object[][] getResetPasswordData() throws IOException {
        String path = System.getProperty("user.dir")
                + "/src/test/java/com/mystore/testData/AuthenticationModuleData.xlsx";

        int rowNum = XLUtils.getRowCount(path, "ResetEmailData");
        int colCount = XLUtils.getCellCount(path, "ResetEmailData", 1);
        Object[][] resetEmailData = new Object[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                resetEmailData[i - 1][j] = XLUtils.getCellData(path, "ResetEmailData", i, j);
            }

        }
        return resetEmailData;
    }

    @Test(priority = 1, description = "Customer can view create an account page using valid email address")
    public void createAccountUsingValidEmail() throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitAuthenticationEmailForm(email);
        Thread.sleep(5000);
        if (driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "checkCreateAccountEmail");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 2, dataProvider = "EmailCheckDataProvider", description = "Customer can't view create an account page using Invalid email address")
    public void createAccountUsingInvalidEmail(String em) throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitAuthenticationEmailForm(em);
        Thread.sleep(5000);
        if (driver.getCurrentUrl().equals("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation")) {
            logger.warn("Test Failed");
            captureScreen(driver, "checkCreateAccountEmail");
            Assert.assertTrue(false);
        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 3, description = "customer can create an account using valid personal information")
    public void createAccountUsingValidInfo() throws IOException, InterruptedException {
        //Verify email is already registered or not
        authPage = new AuthPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        authPage.submitAuthenticationEmailForm(email);
        Thread.sleep(5000);

        accountCreationPage.submitAccountCreationForm(InputData.title, InputData.firstName, InputData.lastName, email, InputData.password, InputData.birthDay, InputData.birthMonth, InputData.birthYear, InputData.addressFirstName, InputData.addressLastName, InputData.company, InputData.address, InputData.address2, InputData.city, InputData.state, InputData.postalCode, InputData.country, InputData.additionalInfo, InputData.homePhone, InputData.mobilePhone, InputData.ref);
        Thread.sleep(5000);
        if (driver.getTitle().equals("My account - My Store")) {
            logger.info("Test Passed");
            config.setLoginEmailAndPass(email,InputData.password);
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "createAccountUsingValidInfo");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 4, dataProvider = "CustomerInfoDataProvider", description = "customer can't create an account using Invalid personal information")
    public void createAccountUsingInvalidInfo(String title, String fName, String lName, String em, String pass,
                                              String birthDay, String birthMonth, String birthYear, String addressFName, String addressLName,
                                              String company, String address, String address2, String city, String state, String postalCode, String country, String additionalInfo, String homePhoneNumber, String mobilePhoneNumber, String refferAddress) throws IOException, InterruptedException {
        //Verify email is already registered or not
        authPage = new AuthPage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        authPage.submitAuthenticationEmailForm(email);
        Thread.sleep(5000);


        accountCreationPage.submitAccountCreationForm(title, fName, lName, em, pass, birthDay, birthMonth, birthYear, addressFName, addressLName, company, address, address2, city, state, postalCode, country, additionalInfo, homePhoneNumber, mobilePhoneNumber, refferAddress);
        Thread.sleep(4000);
        if (driver.getTitle().equals("My account - My Store")) {
            logger.warn("Test Failed");
            captureScreen(driver, "createAccountUsingInvalidInfo");
            Assert.assertTrue(false);

        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 5, description = "Customer can signin using valid email and password")
    public void signInUsingValidData() throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitSignInForm(loginEmail, loginPassword);
        Thread.sleep(3000);

        if (driver.getTitle().equals("My account - My Store")) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "signInUsingValidData");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 6, dataProvider = "CustomerSignInDataProvider", description = "Customer can't signin using Invalid email and password")
    public void signInUsingInvalidData(String email, String password) throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitSignInForm(email, password);
        Thread.sleep(3000);
        if (driver.getTitle().equals("My account - My Store")) {
            logger.warn("Test Failed");
            captureScreen(driver, "signInUsingInvalidData");
            Assert.assertTrue(false);

        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 7, description = "Customer can reset password using valid email")
    public void resetPasswordUsingValidData() throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitForgetPassEmailForm(loginEmail);
        Thread.sleep(3000);

        if (driver.getPageSource().contains("A confirmation email has been sent to your address: " + loginEmail)) {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        } else {
            logger.warn("Test Failed");
            captureScreen(driver, "resetPasswordUsingValidData");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 8, dataProvider = "ResetPasswordDataProvider", description = "Customer can't reset password using Invalid email")
    public void resetPasswordUsingInvalidData(String email) throws IOException, InterruptedException {
        authPage = new AuthPage(driver);
        authPage.submitForgetPassEmailForm(email);
        Thread.sleep(3000);
        if (driver.getPageSource().contains("A confirmation email has been sent to your address: " + loginEmail)) {
            logger.warn("Test Failed");
            captureScreen(driver, "resetPasswordUsingInvalidData");
            Assert.assertTrue(false);

        } else {
            logger.info("Test Passed");
            Assert.assertTrue(true);
        }
    }

}
