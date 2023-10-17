package com.model.stepDefinition.admConStepDef;

import com.model.pages.admConsole.AdminCenterPages;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserVerManagement extends BasePage {
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators = null;
    AdminCenterPages adminCenterPages = new AdminCenterPages();

    @When("^Clicks on \"([^\"]*)\" button and see \"([^\"]*)\" page$")
    public void clicks_on_button_and_see_page(String arg1, String arg2) throws Throwable {
        boolean btn = locateElementByXPath("(//div[@id='skipToContnet']//button)[1]").getText().contains(arg1);
        System.out.println("btn name = " + btn);
        click(locateElementByXPath("(//div[@id='skipToContnet']//button)[1]"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='skipToContnet']//div[@class='info-section']")));

        boolean title = locateElementByXPath("//div[@id='skipToContnet']//div[@class='info-section']").getText().contains(arg1);
        System.out.println("title name = " + title);
        highLightElement(locateElementByXPath("//div[@id='skipToContnet']//div[@class='info-section']"));

    }

    @When("^selects given information for the below features and confirm it:$")
    public void selects_given_information_for_the_below_features_and_confirm_it(DataTable practice) throws Throwable {
        adminCenterPages.userVerificationManagement(practice);
    }

    @And("clicks on {string} button after searching for the below practice:")
    public void clicks_on_button_after_searching_for_the_below_practice(String string, DataTable dataTable) throws Exception {
    adminCenterPages.updateExistedPractice(string,dataTable);
    }

    @Then("{string}'s {string} page for {string} is displayed:")
    public void s_page_for_is_displayed(String appName, String addNewPer, String NonPract, DataTable dataTable) throws Exception {
        adminCenterPages.verifyAddNewQuarter4NonPrtUssPage(appName, addNewPer, NonPract,dataTable);
    }

}
