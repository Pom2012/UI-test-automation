package com.model.pages.prtlModule;

import com.model.utility.DataHelper;
import com.model.base.BasePage;
import com.model.locators.myAccess_myProfileLoc.Portal_MyAccess_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step3_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

import static com.model.base.Constants.IC_Roles.*;

public class PortalPages extends BasePage {
    String actualRole;
    WebElement webActRole1 = null;
    WebElement webActRole2 = null;
    String attribute;
    Portal_UserRegistration_Step2_Locators newUserReg2;
    Portal_UserRegistration_Step3_Locators newUserReg3;
    List<HashMap<String, String>> inputRows = DataHelper.data("NewUserRegData.xlsx", env);
    Portal_MyPortal_Locators locators = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    Portal_MyAccess_Locators accessLocators = PageFactory.initElements(driver, Portal_MyAccess_Locators.class);

    public PortalPages() {
        newUserReg2 = PageFactory.initElements(driver, Portal_UserRegistration_Step2_Locators.class);
        newUserReg3 = PageFactory.initElements(driver, Portal_UserRegistration_Step3_Locators.class);
    }

    public void newUserRegistration(String att, String row) throws Exception {
        int index = Integer.parseInt(row) - 1;
        String value = inputRows.get(index).get(att);
        switch (att) {
            case "FirstName":
                type(newUserReg2.fName, value);
                break;
            case "LastName":
                type(newUserReg2.lName, value);
                break;
            case "SSNumber":
                type(newUserReg2.socialSecNumber, value);
                break;
            case "BirthMonth":
                selectFromDropDownMenu(newUserReg2.birthMonthDropDown, value);
                break;
            case "BirthDate":
                selectFromDropDownMenu(newUserReg2.birthDateDropDown, value);
                break;
            case "BirthYear":
                selectFromDropDownMenu(newUserReg2.birthYearDropDown, value);
                break;
            case "Street":
                type(newUserReg2.homeAddress, value);
                break;
            case "City":
                type(newUserReg2.city, value);
                break;
            case "State":
                selectFromDropDownMenu(newUserReg2.state, value);
                break;
            case "ZipCode":
                type(newUserReg2.zipCode, value);
                break;
            case "Email":
                type(newUserReg2.eMail, value);
                break;
            case "ConfirmEmail":
                type(newUserReg2.eMailConfirm, value);
                break;
            case "PhoneNumber":
                type(newUserReg2.phoneNumber, value);
                break;
            case "UserName":
                type(newUserReg3.uName, value);
                break;
            case "Password":
                type(newUserReg3.passw, value);
                break;
            case "ConfirmPassword":
                type(newUserReg3.passwConf, value);
                break;
            case "Challenge_Question_1":
                selectFromDropDownMenu(newUserReg3.secQuestion, value);
                break;
            case "Challenge_Question_1_Answer":
                type(newUserReg3.secQuestionAnswer, value);
                break;
            default:
                new Throwable(attribute + " is not available");
        }
    }

    public void icUserRoleVerification(String expectedRole) throws Exception {
        click(locators.myProfileDropDownLink);
        wait(300);
        clickWithJSE(locateElementByXPathText("My Access"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("h1[role='heading']")));
        highLightElement(locateElementByCSS("h1[role='heading']"));
        switch (expectedRole) {
            case IC_USER:
                webActRole1 = accessLocators.icRole;
                actualRole = webActRole1.getText();
                break;
            case PV_USER:
                webActRole1 = accessLocators.icPVuser;
                actualRole = webActRole1.getText();
                break;
            case ADMIN:
                webActRole1 = accessLocators.icAdminRole;
                actualRole = webActRole1.getText();
                break;
            case APPROVER:
                webActRole1 = accessLocators.icAppApprover;
                actualRole = webActRole1.getText();
                break;
            case ADMIN_HD:
                webActRole1 = accessLocators.icAdminRole;
                webActRole2 = accessLocators.icHelpDesk;
                String actualAdminRole = webActRole1.getText();
                String actualHDRole = webActRole2.getText();
                actualRole = actualAdminRole + " + " + actualHDRole;
                highLightElement(webActRole1);
                highLightElement(webActRole2);
                break;
            case HELPDESK:
                webActRole1 = accessLocators.icHelpDesk;
                actualRole = webActRole1.getText();
                break;
            case HD_ADMIN:
                webActRole1 = accessLocators.icHelpDeskAdmin;
                actualRole = webActRole1.getText();
                break;
            case POI_USER:
                webActRole1 = accessLocators.icPOIUser;
                actualRole = webActRole1.getText();
                break;
            case PV_REPORT_USER:
                webActRole2 = accessLocators.icReportuser;
                webActRole1 = accessLocators.icPVuser;
                String actualReportRole = webActRole2.getText();
                String actualPVRole = webActRole1.getText();
                actualRole = actualReportRole + " + " + actualPVRole;
                highLightElement(webActRole1);
                highLightElement(webActRole2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + expectedRole);
        }
        wait(500);
        highLightElement(webActRole1);
        System.out.println("Expected Role is = " + expectedRole);
        System.out.println("Actual Role is = " + actualRole);
        Assert.assertEquals("User is not logged in as >>>>>", actualRole, expectedRole);
        click(locateElementByCSS("#cms-homepage-header-logo-unauth"));
    }

    public void verifyIcLinks(String expectedSystemMessage, DataTable dataTable) {
        waitForCommonPageLoadingElements();
        List<List<String>> list = dataTable.asLists();
        for (int i = 0; i < list.get(0).size(); i++) {
            if (list.get(0).get(i).contains("-")) {
                continue;
            } else {
                ((JavascriptExecutor) driver).executeScript("window.open()");
                List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
                driver.get("https://portaldev.cms.gov/cmmi-" + list.get(0).get(i) + "/servlet/home");
                waitForCommonPageLoadingElements();
                Assert.assertTrue(locateElementByCSS("body pre").getText().contains(expectedSystemMessage));
            }
        }
    }
}
