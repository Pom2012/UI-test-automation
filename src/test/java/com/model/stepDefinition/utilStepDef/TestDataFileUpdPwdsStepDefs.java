package com.model.stepDefinition.utilStepDef;

import com.model.utility.DataHelper;
import com.model.utility.Screenshot;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.pages.login.LoginFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

import static com.model.utility.ExcelFile.writeDataToExcel;

public class TestDataFileUpdPwdsStepDefs extends BasePage {
    @And("^\"([^\"]*)\" enters the current the password and the new password \"([^\"]*)\" and submit it$")
    public void enters_the_current_the_password_and_the_new_password_and_submit_it(String userRow, String counter) throws Throwable {
        BasePage_Locators login = PageFactory.initElements(driver, BasePage_Locators.class);
        List<HashMap<String, String>> userData = DataHelper.data("testData2.xlsx", env);
        int index = Integer.parseInt(userRow) - 1;
        String newPasswordID = "P!na!ples" + counter;
        String userID = userData.get(index).get("UserName");
        String password = userData.get(index).get("Password");
        type(login.oldPassword, password);
        type(login.newPassword, newPasswordID);
        type(login.confirmNewPassword, newPasswordID);
        writeDataToExcel("testData2.xlsx", env, userRow, userID, newPasswordID);
    }

    @And("^navigates to \"([^\"]*)\" page and clicks on \"([^\"]*)\" tab$")
    public void navigates_to_page_and_clicks_on_tab(String mainPage, String tab) throws Throwable {
        click(locateElementByID("cms-myprofile-menu"));
        click(locateElementByCSS("a[title='" + mainPage + "']"));
        highLightElement(locateElementByXPath("//div[@class='pull-left']"));
        click(locateElementByXPath("//button[normalize-space()='" + tab + "']"));
        highLightElement(locateElementByID("chg-pwd-heading"));
    }

    @Then("^the confirmation massage is displayed$")
    public void the_confirmation_massage_is_displayed() throws Throwable {
        click(locateElementByID("cms-myprofile-passwordChange-submit"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='happyDIV']")));
        highLightElement(locateElementByXPath("//div[@id='happyDIV']"));
    }

    @Then("from Tab {string} in {string}: for each row, Login in. If needed, change the password")
    public void iterate_Assign_Role_Excel_and_Submit_form(String tabName, String filename) throws Throwable {
        String sheetName = tabName.equalsIgnoreCase("env") ? env :  tabName;
        LoginFunctions loginSteps = new LoginFunctions();
        BasePage_Locators login;
        String expectedPwd = BasePage.prop.getProperty("password.prefix.new").isEmpty() ?
                BasePage.prop.getProperty("password.prefix.current") + expectedPwdEnd :
                BasePage.prop.getProperty("password.prefix.new") + expectedPwdEnd;
        String currentPwd;
        String excelPwd;
        List<HashMap<String, String>> userRows = DataHelper.data(filename, sheetName);
        for (HashMap<String, String> rowData : userRows) {
            excelPwd = rowData.get("Password") == null ? getExpectedPwd() : rowData.get("Password");
            currentPwd = LoginFunctions.loginByUserIDAndPwd(rowData.get("UserName"), excelPwd, rowData.get("MFA"));
            if (currentPwd == null) {
                log.warn("WARN: User Password not changed: " + rowData.get("UserName"));
                Screenshot.captureScreen(driver, "FAILED", "PWD_NOT_CHANGED_" + rowData.get("UserName"));
                driver.get(url);
                wait(2000);
                continue;
            }
            if (currentPwd.equals(expectedPwd)) {
                log.info("INFO: User pwd is up to date: " + rowData.get("UserName"));
                logoutCurrentUserWithRetry();
                continue;
            }
            navigates_to_page_and_clicks_on_tab("My Profile", "Change Password");
            waitForCommonPageLoadingElements();
            login = PageFactory.initElements(driver, BasePage_Locators.class);
            type(login.oldPassword, currentPwd);
            type(login.newPassword, expectedPwd);
            type(login.confirmNewPassword, expectedPwd);
            click(locateElementByID("cms-myprofile-passwordChange-submit"));
            int retries = 5;
            while (retries > 0) {
                wait(2000);
                if (isElementPresent("//div[@id='happyDIV']", "xpath")) break;
                retries--;
            }
            if (!isElementPresent("//div[@id='happyDIV']", "xpath")) {
                log.warn("WARN: User Password not changed: " + rowData.get("UserName"));
                Screenshot.captureScreen(driver, "FAILED", "PWD_NOT_CHANGED_" + rowData.get("UserName"));
            }
            logoutCurrentUserWithRetry();
        }
    }
}
