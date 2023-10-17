package com.model.stepDefinition.admConStepDef;

import com.model.pages.admConsole.AdminCenterPages;
import com.model.base.BasePage;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.pages.admConsole.AdminCenterPages.cucumberDataTableReadingMeths;

public class RoleMappingStepsDef extends BasePage {
    AdminCenterPages admCentMeths = new AdminCenterPages();
    ApplicationsManagement_Locators alocators = PageFactory.initElements(getDriver(), ApplicationsManagement_Locators.class);

    @Then("^clicks on \"([^\"]*)\" button and see the below features:$")
    public void clicks_on_button_and_see_the_below_features(String btn, DataTable tableValue) throws Throwable {
        admCentMeths.roleMappingVerification(btn, tableValue);
    }

    @When("types in searchbox \"([^\"]*)\", the system displays \"([^\"]*)\"$")
    public void types_in_searchbox_the_system_displays(String appId, String appName) throws Throwable {
        highLightElement(alocators.applSearchbox);
        alocators.applSearchbox.sendKeys(appId);
        highLightElement(locateElementByXPathText(appName));
    }

    @When("types in search box, sees \"([^\"]*)\" and clicks on envelop icon$")
    public void types_in_search_box_sees_and_clicks_on_envelop_icon(String appId) throws Throwable {
        admCentMeths.navigateToUsInactPage(appId);
    }

    @Then("^\"([^\"]*)\" is on \"([^\"]*)\"'s \"([^\"]*)\" tab page$")
    public void is_on_s_tab_page(String userRole, String appName, String tabName) {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathContainsText(tabName)));
        isElementPresent(locateElementByXPathContainsText(tabName));
        isElementPresent(locateEleByXPathTextNormSpace(appName));
    }

    @Then("^\"([^\"]*)\" clicks on envelop icon for checking \"([^\"]*)\" user inactivity$")
    public void clicks_on_envelop_icon_for_checking_user_inactivity(String ICAdmin, String appName) throws Throwable {
        webElement = locateElementByCSS("tbody>tr>:nth-child(7),tbody>td>:nth-child(7)");
        highLightElement(webElement);
        click(webElement);
    }

    @And("^user see the below features:$")
    public void user_see_the_below_features(DataTable cucData) {
        admCentMeths.userInactivityAmdManagement(cucData);
    }

    @Then("^User sees \"([^\"]*)\" search box, \"([^\"]*)\" button$")
    public void user_sees_search_box_button(String searchScheduleText, String addNewQuarter) throws Throwable {
        isElementPresent(locateElementByXPath("//*[text() = '" + searchScheduleText + " :']"));
        isElementPresent(locateElementByXPath("//*[text() = 'Search']"));
        isElementPresent(locateElementByXPath("//*[text() = ' " + addNewQuarter + "']"));
    }

    @When("^user see the below features in \"([^\"]*)\" page:$")
    public void user_see_the_below_features_in_page(String title, DataTable cucData) {
        cucumberDataTableReadingMeths(title, cucData);
    }

    @When("^clicks on \"([^\"]*)\" button and sees \"([^\"]*)\" page$")
    public void clicks_on_button_and_sees_page(String btn, String page) throws Throwable {
        admCentMeths.clickAndSeePageRoleMap(btn, page);
    }

    @When("^clicks on \"([^\"]*)\" button and see \"([^\"]*)\" page$")
    public void clicks_on_button_and_see_page(String btn, String arg2) throws Throwable {
        click(locateElementByXPathContainsText(btn));
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("div.header-label")));
    }

    @When("^provides the below data for each fields \"([^\"]*)\" page:$")
    public void provides_the_below_data_for_each_fields_page(String page, DataTable cucOpts) throws Throwable {
        cucTabledata = cucOpts.asLists(String.class);
        switch (page) {
            case "Add New Applications Role Mapping":
                for (int i = 1; i <= cucTabledata.size(); i++) {
                    type(locateElementByXPath("(//mat-form-field[@appearance='fill']//input)[" + i + "]"), cucTabledata.get(i - 1).get(1));
                    wait(200);
                    wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//span[@class='mat-option-text']"))).click();
                }
                break;
            case "Add New Custom Attributes Mapping":
                break;
        }
    }

    @Then("^user searches and sees the mapped \"([^\"]*)\" on \"([^\"]*)\" page after clicking on \"([^\"]*)\"$")
    public void user_searches_and_sees_the_mapped_on_page_after_clicking_on(String app, String page, String sbmtBtn) throws Throwable {
        click(locateElementByXPathContainsText(sbmtBtn));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='skipToContnet']//table")));
        highLightElement(locateElementByXPathContainsText(page));
        type(locateElementByXPath("//*[@id='skipToContnet']//input"), app);
        highLightElement(locateElementByXPathContainsText(app));
    }

    @When("searching by Target {string} and Source {string}, click {string}")
    public void clicks_on_after_searching_the_application_by_source_app_name(String tarApp, String srcApp, String btn) throws Throwable {
        admCentMeths.editingRoleMapp(tarApp, srcApp, btn);
    }

    @And("^selects \"([^\"]*)\" and submit the change on \"([^\"]*)\" page$")
    public void selects_and_submit_the_change_on_page(String checkBox, String page) throws Throwable {
        admCentMeths.disableAppsRoleMapp(checkBox, page);
    }

    @And("^deselects \"([^\"]*)\" and submit the change on \"([^\"]*)\" page$")
    public void deselects_and_submit_the_change_on_page(String checkBox, String page) throws Throwable {
        admCentMeths.deselectDisableStatusFromAppsRoleMap(checkBox, page);
    }

    @When("^provides the below data to each field below and clicks on \"([^\"]*)\" button:$")
    public void provides_the_below_data_to_each_field_below_and_clicks_on_button(String sentBtn, DataTable dataValues) throws Throwable {
        admCentMeths.roleMappVerifInUserProfile(sentBtn, dataValues);
    }

    @Then("^the json body of the user is displayed$")
    public void the_json_body_of_the_user_is_displayed(DataTable dataValues) {
        admCentMeths.jsonTextVerificationInUserProfile(dataValues);
    }
}
