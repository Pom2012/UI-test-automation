package com.model.pages.login;

import com.model.stepDefinition.commonStepDef.Common_StepDefinition;
import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.model.utility.MFA.getSecCode;

public class LoginFunctions extends BasePage {
    static BasePage_Locators login;
    Portal_MyPortal_Locators login2;

    public LoginFunctions() {
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        login2 = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    }

    public static String handlePwdValue(HashMap<String, String> user) {
        return user.get("Password") == null || user.get("Password").isEmpty() ? getExpectedPwd() : user.get("Password");
    }

    public void handleTheMFA(String mfa) throws Exception {
        log.info("MFA: START: " + userID + " : Clicking Send MFA Code-----");
        click(login.sendMFAcode);
        type(login.mfaCodeFromEmail, getSecCode());
        wait(1000);
    }

    public static String handleMFAValue(HashMap<String, String> user, String inputMFA) {
        String useMFA = user.get("MFA") == null || user.get("MFA").isEmpty() ?
                "NON-MFA" : user.get("MFA").toUpperCase();
        inputMFA = inputMFA == null || inputMFA.isEmpty() ?
                "NON-MFA" : inputMFA.toUpperCase();
        if (!useMFA.equals(inputMFA))
            log.warn("WARN: EXPECTED and ACTUAL MFA: using Excel as Expected Value: " + useMFA + " : NOT using Test Suite: " + inputMFA);
        return useMFA;
    }

    public static String loginByUserIDAndPwd(String userID, String pwd, String mfa) throws Throwable {
//        new LoginFunctions();
        List<String> pwds = new ArrayList<>();
        BasePage.userID = userID;
        if (!pwd.isEmpty() && !pwd.equals(getExpectedPwd())) pwds.add(pwd);
        pwds.add(getExpectedPwd());
        pwds.add(BasePage.prop.getProperty("password.prefix.current") + previousPwdEnd);
        if (!BasePage.prop.getProperty("password.prefix.new").isEmpty()) {
            log.warn("WARN: PROPERTY: password.prefix.new is not intended to be used long-term, " +
                    "only for Password Change: " + BasePage.prop.getProperty("password.prefix.new"));
            pwds.add(BasePage.prop.getProperty("password.prefix.new") + expectedPwdEnd);
            pwds.add(BasePage.prop.getProperty("password.prefix.new") + previousPwdEnd);
        }
        int p = 0;
        String tryPwd = pwds.get(p);
        int retries = 6;
        while (retries > 0) {
            waitForCommonPageLoadingElements();
            if (login == null) new LoginFunctions();
            type(login.userID, userID);
            type(login.password, tryPwd);
            if (!login.checkbox.isSelected()) click(login.agreeCheckbox);
            click(login.loginButton);
            wait(2500);
            if (isUserSuccessfullyLoggedIn(userID)) return tryPwd;
            wait(2500);
            if (isUIHanging("LOGIN") && !isUserSuccessfullyLoggedIn(userID)) {
                retries--;
                continue;
            }
            if (isUserSuccessfullyLoggedIn(userID)) return tryPwd;
            if (isElementPresent("//form//*[contains(text(), 'Invalid combination of User ID and Password')]", "xpath")) {
                tryPwd = (p + 1) >= pwds.size() ? pwds.get(0) : pwds.get(++p);
                continue;
            } else if (isAccountLockedPage()) {
                log.error("ERROR: USER IS LOCKED: " + userID);
                return null;
            }
            if (isOnMfaPage() && !mfa.equals("MFA")) {
                log.warn("MFA: User is unexpectedly on MFA page. Trying MFA login.");
                mfa = "MFA";
            }
            if (mfa.equals("MFA")) {
                if (isUserSuccessfullyLoggedIn(userID)) {
                    log.warn("LOGIN: MFA: User did not use MFA when it was expected, but is logged in. Continuing with test.");
                    return tryPwd;
                }
                if (!isOnMfaPage() && isUIHanging("MFA")) {
                    if (!isOnMfaPage()) {
                        log.error("LOGIN: MFA: Browser did not arrive at MFA page as expected. Restarting at base URL.");
                        Common_StepDefinition.openPortal(testName);
                        retries--;
                        continue;
                    }
                }
                log.info("MFA: START: " + userID + " : Clicking Send MFA Code-----");
                click(login.sendMFAcode);
                type(login.mfaCodeFromEmail, getSecCode());
                wait(1000);
                click(login.verifyButton);
                log.info("MFA:  END: " + userID + " : Clicked Verify-----");
            }
            //ENH: WORKAROUND: Login hangs and in some cases the wait graphic returns twice.
            // Padding out the wait here to help differentiate this repeated vs a true hanging condition.
            for (int i = 0; i < 5; i++) {
                wait(2500);
                if (isUserSuccessfullyLoggedIn(userID)) return tryPwd;
            }
            retries--;
        }
        //ENH: One more check if user is in
        if (isUserSuccessfullyLoggedIn(userID)) return tryPwd;
        Assert.fail("FAIL: Test cannot log in: userID: " + userID + " :  MFA: " + mfa);
        return null;
    }

    public static boolean isUserSuccessfullyLoggedIn(String userID) {
        waitForCommonPageLoadingElements();
        if (isElementPresent("//a[@id='logoutlink']", "xpath")) {
            BasePage.userID = userID;
            log.info("--USER: " + userID + " : User is successfully logged in --------------------------- ");
            return true;
        }
        return false;
    }

    public static boolean isOnMfaPage() {
        waitForCommonPageLoadingElements();
        return isElementPresent("//*[@id='cms-mfa-heading']", "xpath");
    }

    public static boolean isAccountLockedPage() {
        return (isElementPresent("//*[text()='Unlock My Account']", "xpath") || isInPageSource("Unlock My Account"));
    }

    @When("a user {string} logs in with {string}")
    public static String a_user_logs_in_with(String userRow, String featureFileMFA) throws Throwable {
        HashMap<String, String> user = getUserByUserNumberFromExcelFile(userRow);
        return loginByUserIDAndPwd(user.get("UserName"), handlePwdValue(user), handleMFAValue(user, featureFileMFA));
    }

    @When("a user where Column {string} has the value {string} logs in")
    public static String loginByColHeaderValue(String colHeader, String value) throws Throwable {
        HashMap<String, String> user = getRowByDataSrcColHeaderAndValue(userData, colHeader, value);
        return loginByUserIDAndPwd(user.get("UserName"), handlePwdValue(user), "NON-MFA");
    }

    @When("a user where Column {string} has the value {string} logs in using {string}")
    public static String loginByColHeaderValue(String colHeader, String value, String featureFileMFA) throws Throwable {
        HashMap<String, String> user = getRowByDataSrcColHeaderAndValue(userData, colHeader, value);
        return loginByUserIDAndPwd(user.get("UserName"), handlePwdValue(user), handleMFAValue(user, featureFileMFA));
    }

    @Then("^user successfully logged in$")
    public void user_successfully_logged_in() {
        Assert.assertTrue("Logout link is not displayed", isUserSuccessfullyLoggedIn(BasePage.userID));
    }

    @When("^fillout data SetLogIn \"([^\"]*)\" and \"([^\"]*)\" from excel sheet$")
    public void fillout_data_SetLogIn_and_from_excel_sheet(String rowIndex, String colName) throws Throwable {
        if (userData == null) userData = DataHelper.data("testData2.xlsx", env);
        System.out.println(Date + "... >>>Login box is present ...>>>");
        int index = Integer.parseInt(rowIndex) - 1;
        String value = userData.get(index).get(colName);
        wait(2000);

        if (colName.equalsIgnoreCase("UserName")) {
            Model_CommonFunctions.type(login.userID, value);
        } else if (colName.equalsIgnoreCase("Password")) {
            Model_CommonFunctions.type(login.password, value);
        } else if (colName.equalsIgnoreCase("MFA")) {
            wait(3000);
            selectFromDropDownMenu(login.MFADropDown, value);
        }
    }

    @And("^clicks Select MFA device type dropdown menu$")
    public void clicks_dropdown_menu() throws Throwable {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='cms-mfa-selectbox2']")));
        click(login.MFADropDown);
    }

    @And("^verify email sent message$")
    public void verify_email_sent_message() {
        Model_CommonFunctions.getTextofWebElementSimple(login.emailSendingMess);
    }

    @And("^click I agree to terms and conditions textbox$")
    public void click_I_agree_to_terms_and_conditions_textbox() throws Throwable {
        if (driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected()) {
            System.out.println("\"I agree to terms and conditions\" textbox is selected");
        } else {
            click(login.agreeCheckbox);
        }
    }

    @And("^click login button$")
    public void click_login_button() throws Throwable {
        click(login.loginButton);
    }

    @Then("^user is successfully logged in$")
    public void user_is_successfully_logged_in() {
        Assert.assertTrue("Logout link is not displayed", Model_CommonFunctions.isElementPresent(login2.logOutlink));
        System.out.println(Date + "...>>>Verified...>>>" + login2.logOutlink.getText());
        System.out.println("User is successfully logged in");
    }

    @And("^user without MFA logs in$")
    public void user_without_mfa_logs_in() throws Throwable {
        if (driver.findElement(By.id("checkd")).isSelected()) {
            System.out.println("Selected");
        } else {
            click(login.agreeCheckbox);
        }
        click(login.loginButton);
        wait(5000);
    }

    @Then("^user is locked$")
    public void user_is_locked() {
        Assert.assertTrue("Account was not locked as expected", isAccountLockedPage());
    }

    @When("a user {string} logs into CMS Portal with {string}")
    public void a_user_logs_into_cms_portal_with(String rowNum, String mfa) throws Throwable {
        userID = DataHelper.readDataFromExcel(Integer.parseInt(rowNum), "UserName", "NewUserRegData", "Roles");
        password = DataHelper.readDataFromExcel(Integer.parseInt(rowNum), "Password", "NewUserRegData", "Roles");
        type(login.userID, env + userID);
        type(login.password, password);
        if (!login.checkbox.isSelected()) click(login.agreeCheckbox);
        click(login.loginButton);
        if (isOnMfaPage()) {
            handleTheMFA(mfa);
        }
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-verify-code-submit"))).click();
        Assert.assertTrue("Logout link is not displayed", isUserSuccessfullyLoggedIn(BasePage.userID));
    }
}
