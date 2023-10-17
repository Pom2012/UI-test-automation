package com.model.stepDefinition.utilStepDef;

import com.github.javafaker.Faker;
import com.model.utility.DataHelper;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.locators.hd_ManageUsersLoc.HD_ManageUsers_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

import static com.model.utility.ExcelFile.writePwdUIDataToExcel;
import static com.model.utility.ExcelFile.writeTempPwdUIDataToExcel;
import static com.model.pages.login.LoginFunctions.isUserSuccessfullyLoggedIn;

public class UpdatingPwdsUtilStepDefs extends BasePage {
    public static final String tempPD = "updatePasswordACO1.xlsx";
    HD_ManageUsers_Locators hd_manageUs_locs = PageFactory.initElements(driver, HD_ManageUsers_Locators.class);
    Portal_MyPortal_Locators locators = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    public static Faker faker = new Faker();
    public BasePage_Locators login;
    Portal_MyPortal_Locators login2;
    List<HashMap<String, String>> envData;
    public List<HashMap<String, String>> exPwDatamap;
    public List<HashMap<String, String>> roles;

    /**
     * THIS EXCEL FILE IS LOCATED IN -->> src/test/resources/externFiles/tempPW.xlsx
     * YOU ONLY NEED TO OPEN IT AND TO PASTE IN "OldValues" SHEET YOUR USERID AND NEW DESIRED PASSWORDS,
     * REMOVE AND CLEAN UP "NewValues" SHEET FROM OLD VALUES,
     * AFTER THE PROCESS IS FINISHED, YOUR NEW CREDENTIALS WILL BE READY IN "NewValues" SHEET.
     */
    public UpdatingPwdsUtilStepDefs() {
        exPwDatamap = DataHelper.data("updatePassword.xlsx", "expPwds");
        roles = DataHelper.data("updatePassword.xlsx", "roles");
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        envData = DataHelper.data("testData2.xlsx", env);
    }
//    And iterates the updating process for "<userNumbers>"

    ///* //REVIEW: If needed, should not use row#
    @And("^iterates the updating process for \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iterates_the_updating_process_for(int userNumber, String tab1, String tab2) throws Throwable {
        List<HashMap<String, String>> oldDatamap = DataHelper.data("updatePassword.xlsx", tab1);
        wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.enterpriseSearch));
        click(hd_manageUs_locs.enterpriseSearch);
        for (int index = 0; index < userNumber; index++) {
            String valueUserID = oldDatamap.get(index).get("UserID");
//            String valueFName = oldDatamap.get(index).get("FirstName");
            String valuePWD = oldDatamap.get(index).get("Password");
            System.out.println(index + " UserID = " + valueUserID);
//            System.out.println("FirstName = " + valueFName);
            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.userID_searchBox));
            locateElementByID("cms-enterprisesearch-uid").clear();
            locateElementByID("cms-enterprisesearch-uid").sendKeys(valueUserID);
//            locateElementByID("cms-enterprisesearch-fn").sendKeys(valueFName);
            click(hd_manageUs_locs.searchBtn);
            wait(800);
            if (locateElementByXPath("//tbody/tr/td[6]/div[1]").getText().equalsIgnoreCase("SUSPENDED")) {
                System.out.println(index + " UserID = " + valueUserID + " is SUSPENDED");
                wait(300);
                locateElementByID("cms-editEntSearch").click();
                continue;
            } else {
                click(locateElementByXPathText(" " + "Select Action" + " "));
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Reset Password")));
                click(locateElementByXPathText("Reset Password"));
                wait(500);
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Generate Temporary Password")));
                clickCheckbox_byText("Generate Temporary Password");
                locateElementByID("cms-generateTempPw").sendKeys("Updating the passwords. IC test team.");
                wait(500);
                click(hd_manageUs_locs.generatePWBtn);
                // temporary password generated
                highLightElement(locateElementByXPath("//*[@id='cms-confirmationModal-title']"));
                System.out.println(locateElementByXPath("//*[@id='cms-confirmationModal-title']").getText());
                System.out.println("The confirmation text contains = " + "Generate Temporary Password" + " "
                        + locateElementByXPath("//*[@id='cms-confirmationModal-title']")
                        .getText().contains("Generate Temporary Password"));
                click(hd_manageUs_locs.submitBtn);
                System.out.println((hd_manageUs_locs.tempPWText).getText());
                hd_manageUs_locs.tempPWText.getSize();
                String tempPDID = hd_manageUs_locs.tempPWText.getText().substring(20, 28);
                System.out.println("Temporary Password = " + tempPDID);
                ////////////////////////////////////////////////////////////
                writeTempPwdUIDataToExcel(tempPD, tab2, tempPDID, valueUserID, valuePWD);
                wait(300);
                locateElementByXPathText(" Perform New Search ").click();
            }
        }
    }

    @When("^IC user fills the login page input boxes for \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void ic_user_fills_the_login_page_input_boxes_for(int userNumber, String tab, String tab2) throws Throwable {
        List<HashMap<String, String>> newDatamap = DataHelper.data("updatePasswordACO1.xlsx", tab);
        for (int index = 0; index < userNumber; index++) {
            String userIDValue = newDatamap.get(index).get("UserID");
            String tempPasswordValue = newDatamap.get(index).get("TemporaryPassword");
            String newPasswordValue = newDatamap.get(index).get("Password");
            waitForCommonPageLoadingElements();
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("loginAction")));
            isElementPresent(locateElementByID("loginAction"));
            type(login.userID, userIDValue);
            type(login.password, tempPasswordValue);
            if (!login.checkbox.isSelected()) click(login.agreeCheckbox);
            click(login.loginButton);
            wait(500);
            if (isElementPresent("//form//*[contains(text(), 'Invalid combination of User ID and Password')]", "xpath")) {
                continue;
            }
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("cms-verify-oldPassword")));
            type(locateElementByID("cms-verify-oldPassword"), tempPasswordValue);
            type(locateElementByID("cms-verify-newPassword"), newPasswordValue);
            type(locateElementByID("cms-verify-confirmNewPassword"), newPasswordValue);
            click(locateElementByID("cms-reset-password"));
            waitForCommonPageLoadingElements();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cms-int")));
            highLightElement(locateElementByID("cms-int"));
            isUserSuccessfullyLoggedIn(userIDValue);
            waitForCommonPageLoadingElements();
            writePwdUIDataToExcel("updatePasswordACO1", tab2, userIDValue, newPasswordValue);
            waitForCommonPageLoadingElements();
            logoutCurrentUserWithRetry();
        }
        driver.navigate().refresh();
    }

    @When("^updating \"([^\"]*)\" users' password with \"([^\"]*)\" status$")
    public void updating_users_password_with_status(int userNumber, String arg2) throws Throwable {

        for (int index = 0; index < userNumber; index++) {
            waitForCommonPageLoadingElements();
            userID = exPwDatamap.get(index).get("UserName");
            System.out.println("UserID = " + userID);
            String pwd = exPwDatamap.get(index).get("Password");
            System.out.println("pwd = " + pwd);
            String newPwd = exPwDatamap.get(index).get("Password2");
            type(login.userID, userID);
            wait(100);
            type(login.password, pwd);
            if (!login.checkbox.isSelected()) {
                click(login.agreeCheckbox);
            }
            click(login.loginButton);
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("expiredPasswordDiv")));
            type(locateElementByID("cms-verify-oldPassword"), pwd);
            type(locateElementByID("cms-verify-newPassword"), newPwd);
            type(locateElementByID("cms-verify-confirmNewPassword"), newPwd);
            click(locateElementByID("cms-reset-password"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("cms-landingpages-mp")));
            Assert.assertTrue("  Logout link is not displayed", isElementPresent(locateElementByID("cms-landingpages-mp")));
            logoutCurrentUserWithRetry();
            driver.navigate().refresh();
        }
    }

//    @When("{string} users log into the app and request an IC role {string} MFA")
//    public void users_log_into_the_app_and_request_an_ic_role_mfa(String string, String mfa) throws Exception {
//        requestICRolelifecycle(string, mfa);
//    }

    @When("remove the IC {string} role")
    public void remove_the_ic_role(String string) throws Exception {
        for (int index = 10; index < 1000; index++) {
            userID = roles.get(index).get("UserName");
            System.out.println("UserID = " + userID);
            String pwd = roles.get(index).get("Password");
            System.out.println("pwd = " + pwd);
            icRole = roles.get(index).get("Role");
            System.out.println("Role = " + icRole);
            type(login.userID, userID);
            type(login.password, pwd);
            if (!login.checkbox.isSelected()) {
                click(login.agreeCheckbox);
            }
            click(login.loginButton);
            waitForCommonPageLoadingElements();
            wait(2000);
            click(locators.myProfileDropDownLink);
            wait(300);
            clickWithJSE(locateElementByXPathText("My Access"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("h1[role='heading']")));
            clickWithJSE(locateElementByXPath("//*[normalize-space(text())='" + icRole + "']/ancestor::tr[@class='ng-star-inserted']//button"));
            wait(500);
            clickWithJSE(locateElementByCSS("[id='cms-action-remove-IC_USER_LOA3']"));
            wait(500);
            clickWithJSE(locateElementByXPath("(//button[@class='btn cms-green-button-reg'])[1]"));
            waitForCommonPageLoadingElements();
            logoutCurrentUserWithRetry();
        }
    }


    @When("selects {string} from the user's welcome dropdown feature")
    public void selects_from_the_user_s_welcome_dropdown_feature(String myProf) throws Exception {
        click(locateElementByXPath("//*[@id='cms-header-links']//*[@id='cms-myprofile-menu']"));
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//*[@id='cms-header-links']//*[@title='" + myProf + "']"))).click();
    }

    @Then("views {string} page and clicks on {string}")
    public void views_page_and_clicks_on(String myProfile, String changeFeature) {
        waitForCommonPageLoadingElements();
        isElementPresent(locateElementByCSS("[class='cms-h1-interior']"));
        wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpaceAttr("button", changeFeature))).click();
    }

    @And("{string} enters data for the below text fields and submits the changes:")
    public void enters_data_for_the_below_text_fields_and_submits_the_changes(String userRow, DataTable dataTable) {
        int userName = Integer.parseInt(userRow) - 1;
        waitForCommonPageLoadingElements();
        userID = envData.get(userName).get("UserName");
        String pwd = getExpectedPwd(); //envData.get(userName).get("Password");
        String newPwd = envData.get(userName).get("Updated");
        log.info("userID: " + userID + " password: " + pwd + " new password: " + newPwd);
        type(locateElementByCSS("[id='cms-myprofile-oldPwd']"), pwd);
        type(locateElementByCSS("[name='cms-myprofile-newPwd']"), newPwd);
        type(locateElementByCSS("[name='cms-myprofile-confNewPwd']"), newPwd);
        wait(10000);
    }

    //ENH: icRole - outdated?
//    public void requestICRolelifecycle(String a, String b) throws Exception {
//        oldDatamap = DataHelper.data(tempPD, "OldValues");
//        newDatamap = DataHelper.data(tempPD, "NewValues");
//        exPwDatamap = DataHelper.data(tempPD, "expPwds");
//        roles = DataHelper.data(tempPD, "roles");
//        login = PageFactory.initElements(driver, CMSPortal_BasePage_Locators.class);
//        for (int index = 123; index < 128; index++) {
//            String secCode;
//            userID = roles.get(index).get("UserName");
//            System.out.println("UserID = " + userID);
//            String pwd = roles.get(index).get("Password");
//            System.out.println("pwd = " + pwd);
//            icRole = roles.get(index).get("Role");
//            System.out.println("Role = " + icRole);
//            type(login.userID, userID);
//            type(login.password, pwd);
//            if (!login.checkbox.isSelected()) {
//                click(login.agreeCheckbox);
//            }
//            click(login.loginButton);
//            if (b.equalsIgnoreCase("with")) {
//                click(login.sendMFAcode);
//                secCode = MFA.getEmail(
//                        prop.getProperty("dEmail"),
//                        prop.getProperty("dPassword"),
//                        prop.getProperty("CMSEmail" + env),
//                        prop.getProperty("emailSubject" + env));
//                wait(6000);
//                type(login.mfaCodeFromEmail, secCode);
//                click(login.verifyButton);
//            }
//            waitForCommonPageLoadingElements();
//            simpleClick(locateElementByID("cms-landingpages-mp-addapp"));
//            waitForCommonPageLoadingElements();
//            click(locateElementByID("ngSelectApp"));
//            locateElementByID("textSearch").sendKeys("IC-Innovation Center");
//            simpleClick(locateEleByXPathTextNormSpaceAttr("span", "IC-Innovation Center"));
//            wait(300);
//            simpleClick(locateElementByID("step1_proceed"));
//            wait(1000);
//            locateElementByID("selectRole").click();
//            wait(1000);
//            typeWithJSById("textSearchRole", icRole);
//            wait(1000);
//            simpleClick(locateEleByXPathTextNormSpaceAttr("span", icRole));
//            wait(1000);
//            simpleClick(locateElementByID("step2_proceed"));
//            wait(1000);
//            locateElementByXPath("//textarea[@id='reasonForRequest']").sendKeys("User accounts for pentest");
//            wait(1000);
//            simpleClick(locateElementByID("submit_role_req"));
//            wait(1000);
//            click(locateElementByID("roleRequestModal_ok"));
//            waitForCommonPageLoadingElements();
//            logoutCurrentUserWithRetry();
//        }
//    }
}
