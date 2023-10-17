package com.model.stepDefinition.utilStepDef;

import com.github.javafaker.Faker;
import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.locators.hd_ManageUsersLoc.HD_ManageUsers_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class UserUpdatePwdStepDefs extends BasePage {

    private static String tempPD = null;
    public static Faker faker = new Faker();
    public List<HashMap<String, String>> envData;
    HD_ManageUsers_Locators hd_manageUs_locs = PageFactory.initElements(driver, HD_ManageUsers_Locators.class);
    BasePage_Locators login;

    public UserUpdatePwdStepDefs() {
        envData = DataHelper.data("testData2.xlsx", env);
        login = PageFactory.initElements(driver, BasePage_Locators.class);
    }

    @And("^clicks on \"([^\"]*)\" and views the fields$")
    public void clicks_on_and_views_the_fields(String searchFeature1) throws Throwable {

        if (searchFeature1.contains("Application")) {
            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.applicationSearch));
            Model_CommonFunctions.highLightElement(hd_manageUs_locs.applicationSearch);
            click(hd_manageUs_locs.applicationSearch);
        }
        if (searchFeature1.contains("Enterprise")) {
            wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.enterpriseSearch));
            Model_CommonFunctions.highLightElement(hd_manageUs_locs.enterpriseSearch);
            click(hd_manageUs_locs.enterpriseSearch);
        }
    }

    @And("^the table fields with the given IC user are displayed$")
    public void the_table_fields_with_the_given_IC_user_are_displayed() {}

    @And("^clicks on \"([^\"]*)\" and selects \"([^\"]*)\"$")
    public void clicks_on_and_selects(String selectAction, String updateLOA) throws Throwable {
        wait(800);
        Model_CommonFunctions.highLightElement(locateElementByXPathText(" " + selectAction + " "));
        click(locateElementByXPathText(" " + selectAction + " "));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(updateLOA)));
        Model_CommonFunctions.highLightElement(locateElementByXPathText(updateLOA));
        click(locateElementByXPathText(updateLOA));
    }

    @Then("^clicks on \"([^\"]*)\", justifies and clicks on \"([^\"]*)\"$")
    public void clicks_on_justifies_and_clicks_on(String arg1, String arg2) throws Throwable {
        wait(500);
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(arg1)));
        Model_CommonFunctions.highLightElement(locateElementByXPathText(arg1));
        clickCheckbox_byText(arg1);
        highLightElement(hd_manageUs_locs.justifationField);
        locateElementByID("cms-generateTempPw").sendKeys("" + faker.shakespeare().hamletQuote() + "");
        wait(500);
        highLightElement(hd_manageUs_locs.generatePWBtn);
        click(hd_manageUs_locs.generatePWBtn);
    }

    @Then("^submit the \"([^\"]*)\" confirmation message$")
    public void submit_the_confirmation_message(String arg1) throws Throwable {
        highLightElement(locateElementByXPath("//*[@id='cms-confirmationModal-title']"));
        System.out.println(locateElementByXPath("//*[@id='cms-confirmationModal-title']").getText());
        System.out.println("The confirmation text contains = " + arg1 + " "
                + locateElementByXPath("//*[@id='cms-confirmationModal-title']").getText().contains(arg1));
        highLightElement(hd_manageUs_locs.submitBtn);
        click(hd_manageUs_locs.submitBtn);
    }

    @Then("^\"([^\"]*)\" text is generated$")
    public void text_is_generated(String arg1) throws Throwable {
        highLightElement(hd_manageUs_locs.tempPWText);
        System.out.println((hd_manageUs_locs.tempPWText).getText());
        hd_manageUs_locs.tempPWText.getSize();
        tempPD = hd_manageUs_locs.tempPWText.getText().substring(20, 28);
    }

    //------------------------------IC User can retrieve the temp password ------------------------------------------

    @When("^fills out (\\d+) with the \"([^\"]*)\"$")
    public void fills_out_with_the(String UserID, String arg2) throws Throwable {
        int index = Integer.parseInt(UserID) - 1;
        System.out.println("index = " + index);
        String value = envData.get(index).get("UserID");
        String value2 = getExpectedPwd(); //envData.get(index).get("Password");
        System.out.println("User ID = " + value);
        System.out.println("Temprary password = " + tempPD);
        System.out.println("New password = " + value2);
        isElementPresent(locateElementByID("loginAction"));
        System.out.println("... The login box is presenting.....");
        wait(3000);
        type(login.userID, value);
        wait(3000);
        type(login.password, tempPD);
    }

    @Then("^\"([^\"]*)\" box with fields displayed$")
    public void box_with_fields_displayed(String arg1) throws Throwable {
        wait(2000);
        isElementPresent(locateElementByID("expiredPasswordDiv"));
        highLightElement(locateElementByID("expiredPasswordDiv"));
    }

    @And("^fills out with (\\d+) the \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void fills_out_with_the_and(String UserID, String arg2, String arg3, String arg4) throws Throwable {
        int index = Integer.parseInt(UserID) - 1;
        System.out.println("index = " + index);
        String value = envData.get(index).get("UserID");
        String value2 = getExpectedPwd(); //envData.get(index).get("Password");
        System.out.println("User ID = " + value);
        System.out.println("Temprary password = " + tempPD);
        System.out.println("New password = " + value2);
        type(locateElementByID("cms-verify-oldPassword"), tempPD);
        type(locateElementByID("cms-verify-newPassword"), value2);
        type(locateElementByID("cms-verify-confirmNewPassword"), value2);
    }

    @Then("^clicks on \"([^\"]*)\"$")
    public void clicks_on(String arg1) throws Throwable {
        click(locateElementByID("cms-reset-password"));
        wait(7000);
    }

    @Then("^user logs in and sees \"([^\"]*)\"$")
    public void user_logs_in_and_sees(String arg1) throws Throwable {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cms-int")));
        isElementPresent(locateElementByID("cms-int"));
    }
}



