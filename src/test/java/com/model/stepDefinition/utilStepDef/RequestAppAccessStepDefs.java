package com.model.stepDefinition.utilStepDef;

import com.github.javafaker.Faker;
import com.model.utility.DataHelper;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class RequestAppAccessStepDefs extends BasePage {

    public static Faker faker = new Faker();
    BasePage_Locators login;
    public List<HashMap<String, String>> envData;

    public RequestAppAccessStepDefs() {
        envData = DataHelper.data("NewUserRegData.xlsx", env);
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        if (data4 == null) data4 = DataHelper.data("NewUserRegData.xlsx", "Roles");
    }

    @When("^selects \"([^\"]*)\" after clicking on \"([^\"]*)\"$")
    public void selects_after_clicking_on(String icApp, String addApp) {
        waitForCommonPageLoadingElements();
//        if (!locateElementByCSS("#cms-ic-tile").isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("cms-landingpages-mp-addapp")));
            locateElementByID("cms-landingpages-mp-addapp").click();
            wait(100);
            locateElementByID("ngSelectApp").click();
            locateElementByID("textSearch").sendKeys(icApp + Keys.ENTER);
            wait(100);
            locateElementByID("step1_proceed").click();
            wait(100);
//        }else {
//            wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("logoutlink"))).click();
//            driver.close();
//        }
    }

    @And("^selects a Role for \"([^\"]*)\" and clicks on \"([^\"]*)\"$")
    public void selects_a_Role_for_and_clicks_on(int userRow, String nextBtn) {
        icRole = DataHelper.readDataFromExcel(userRow, "Role", "NewUserRegData", "Roles");
        locateElementByID("selectRole").click();
        locateElementByID("textSearchRole").sendKeys(icRole + Keys.ENTER);
        locateElementByID("step2_proceed").click();
        waitForCommonPageLoadingElements();
    }


    @And("^justify it and clicks \"([^\"]*)\"$")
    public void justify_it_and_clicks(String arg1) {
        locateElementByID("reasonForRequest").sendKeys("New Role");
        locateElementByID("submit_role_req").click();
    }

    @Then("^confirmation message will appear$")
    public void confirmation_message_will_appear() {
        locateElementByID("roleRequestModal_ok").click();
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("happyDivMsg")));
        locateElementByID("mySubmitBtns").click();
    }
}
