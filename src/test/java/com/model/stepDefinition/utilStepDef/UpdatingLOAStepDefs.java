package com.model.stepDefinition.utilStepDef;

import com.github.javafaker.Faker;
import com.model.utility.DataHelper;
import com.model.base.BasePage;
import com.model.locators.hd_ManageUsersLoc.HD_ManageUsers_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;


public class UpdatingLOAStepDefs extends BasePage {

    // CMSPortal_BasePage_Locators login;
    public static Faker faker = new Faker();
    String emailValue = null;
    public List<HashMap<String, String>> dataMap;
    public List<HashMap<String, String>> dataMapEmail;
    HD_ManageUsers_Locators hd_manageUs_locs = PageFactory.initElements(driver, HD_ManageUsers_Locators.class);

    public UpdatingLOAStepDefs() {
        dataMap = DataHelper.data("NewUserRegData.xlsx", env);
        dataMapEmail = DataHelper.data("UpdatedEmails.xlsx", env);
    }

    @And("^clicks on \"([^\"]*)\" tile and views the page$")
    public void clicks_on_tile_and_views_the_page(String tileName) throws Throwable {
        waitForCommonPageLoadingElements();
        simpleClick(hd_manageUs_locs.hdManageUsers);
    }

    @Then("^performs updating LOA for \"([^\"]*)\" users$")
    public void performs_updating_LOA_for_users(int rowNumber) throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.enterpriseSearch));
        click(hd_manageUs_locs.enterpriseSearch);
        for (int index = 0; index <= rowNumber; index++) {
            System.out.println("Row  # = " + index);
            userID = dataMap.get(index).get("UserName");
            fName = dataMap.get(index).get("FirstName");
            lName = dataMap.get(index).get("LastName");
            System.out.println("User ID = " + userID);
            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.userID_searchBox));
            type(locateElementByID("cms-enterprisesearch-uid"), env + userID);
            type(locateElementByID("cms-enterprisesearch-fn"), fName);
            type(locateElementByID("cms-enterprisesearch-ln"), lName);
            click(hd_manageUs_locs.searchBtn);
            wait(800);
            highLightElement(locateElementByXPathText(" " + "Select Action" + " "));
            click(locateElementByXPathText(" " + "Select Action" + " "));
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Update LOA")));
            highLightElement(locateElementByXPathText("Update LOA"));
            click(locateElementByXPathText("Update LOA"));
            boolean isLOAUpgraded = locateElementByXPath("//select[@id='cms-loa']").isEnabled();
            if (isLOAUpgraded) {
                selectFromDropDownMenu(locateElementByID("cms-loa"), "3");
                selectFromDropDownMenu(locateElementByID("cms-loa_reason"), "Manually vetted by Application Help Desk");
                locateElementByID("loa_just").sendKeys("IC automation testing");
                userSSN = dataMap.get(index).get("SSNumber");
                System.out.println("SSN = " + userSSN);
                locateElementByID("cms-userSSN").sendKeys(userSSN);
                click(locateElementByID("cms-updateLOA"));
                wait(200);
                click(locateElementByID("cms-submit-req"));
                wait.until(ExpectedConditions.visibilityOf(locateElementByID("cms-alert-div")));
                highLightElement(locateElementByID("cms-alert-div"));
                if (!(locateElementByID("cms-alert-div")).isDisplayed()) {
                    highLightElement(locateElementByID("cms-alert"));
                }
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPathText(" Perform New Search ")));
            }
            click(locateElementByXPathText(" Perform New Search "));
        }
    }

//    @Then("^performs updating {string} for \"([^\"]*)\" users$")
//    public void performs_updating_for_users(String feature, int rowNumber) throws Throwable {
//        wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.enterpriseSearch));
//        click(hd_manageUs_locs.enterpriseSearch);
//        for (int index = 0; index <= rowNumber; index++) {
//            System.out.println("Row  # = " + index);
//            userID = dataMapEmail.get(index).get("UserName");
//            fName = dataMapEmail.get(index).get("FirstName");
//            lName = dataMapEmail.get(index).get("LastName");
//            System.out.println("User ID = " + userID);
//            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.userID_searchBox));
//            type(locateElementByID("cms-enterprisesearch-uid"), env + userID);
//            type(locateElementByID("cms-enterprisesearch-fn"), fName);
//            type(locateElementByID("cms-enterprisesearch-ln"), lName);
//            click(hd_manageUs_locs.searchBtn);
//            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-tabContent")));
//            highLightElement(locateElementByXPathText(" " + "Select Action" + " "));
//            click(locateElementByXPathText(" " + "Select Action" + " "));
//            System.out.println("feature = " + feature);
//            wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Update " + feature)));
//            highLightElement(locateElementByXPathText("Update " + feature));
//            click(locateElementByXPathText("Update " + feature));
//            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-details-container-el")));
//            boolean isEmailBoxDisplayed = locateElementByCSS("#cms-update-email-form").isDisplayed();
//            if (isEmailBoxDisplayed) {
//                emailValue = dataMap.get(index).get("NewEmailAddress");
//                typeWithJSById("cms-new_email", emailValue);
//                typeWithJSById("cms-confirm_new_email", emailValue);
//                locateElementByID("loa_just").sendKeys("IC automation testing");
//
////                click(locateElementByID("cms-updateEmail"));
////
////                wait.until(ExpectedConditions.visibilityOf(locateElementByID("cms-alert-div")));
////                highLightElement(locateElementByID("cms-alert-div"));
////                if (!(locateElementByID("cms-alert-div")).isDisplayed()) {
////                    highLightElement(locateElementByID("cms-alert"));
////                }
////                wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPathText(" Perform New Search ")));
//            }
//            click(locateElementByXPathText(" Perform New Search "));
//        }
//    }

    @Then("performs updating {string} for {string} users")
    public void performs_updating_for_users(String feature, String rowNumber) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.enterpriseSearch));
        click(hd_manageUs_locs.enterpriseSearch);
        for (int index = 0; index <= Integer.parseInt(rowNumber); index++) {
            System.out.println("Row  # = " + index);
            userID = dataMapEmail.get(index).get("UserName");
//            fName = dataMapEmail.get(index).get("FirstName");
//            lName = dataMapEmail.get(index).get("LastName");
//            System.out.println("User ID = " + userID);
            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.userID_searchBox));
            type(locateElementByID("cms-enterprisesearch-uid"), userID);
//            type(locateElementByID("cms-enterprisesearch-fn"), fName);
//            type(locateElementByID("cms-enterprisesearch-ln"), lName);
            click(hd_manageUs_locs.searchBtn);
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-tabContent")));
            highLightElement(locateElementByXPathText(" " + "Select Action" + " "));
            click(locateElementByXPathText(" " + "Select Action" + " "));
            System.out.println("feature = " + feature);
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Update " + feature)));
            highLightElement(locateElementByXPathText("Update " + feature));
            click(locateElementByXPathText("Update " + feature));
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-details-container-el")));
            boolean isEmailBoxDisplayed = locateElementByCSS("#cms-update-email-form").isDisplayed();
            if (isEmailBoxDisplayed) {
                emailValue = dataMapEmail.get(index).get("NewEmailAddress");
                typeWithJSById("cms-new_email", emailValue);
                typeWithJSById("cms-confirm_new_email", emailValue);
                locateElementByID("cms-update_email_just").sendKeys("IC automation testing");
                click(locateElementByID("cms-updateEmail"));
                wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-confirmationModal")));
                wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-submit-req"))).click();
                wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#cms-confirmation-msg")));
//                if (!(locateElementByID("cms-alert-div")).isDisplayed()) {
//                    highLightElement(locateElementByID("cms-alert"));
//                    driver.navigate().refresh();
//                    continue;
//                }
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPathText(" Perform New Search ")));
            }
            click(locateElementByXPathText(" Perform New Search "));
        }
    }
}
