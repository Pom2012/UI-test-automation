package com.model.stepDefinition.admConStepDef;

import com.model.pages.admConsole.AdminCenterPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ManageApplicationsStepDefs extends BasePage {
    AdminCenterPages admCenterMethods = new AdminCenterPages();

    @When("selects the below values from {string} and see the results in showing entries:")
    public void selects_the_below_values_from_and_see_the_results_in_showing_entries(String string, DataTable dataTable) {
        selectEntriesDropDownVerification(string, dataTable, admCenterMethods.xPathVal4SelectWE, admCenterMethods.showingEntriesWebEl);
    }

    @And("see the {string} & {string} are {string} when the {string} page is selected for {string} entries")
    public void see_the_are_when_the_page_is_selected_for_entries(String first, String previous, String disabled, String page, String selectedEntryValue) throws Exception {
        admCenterMethods.verifyFirstAndPreviousIsDisabled(first, previous, disabled, page, selectedEntryValue);
    }

    @And("selects {string} entries, clicks on the below buttons and see the showing entries value:")
    public void selects_entries_clicks_on_the_below_buttons_and_see_the_showing_entries_value(String selectedEntryValue, DataTable pagesTextValue) throws Exception {
        admCenterMethods.verifyNextPreviousFirstLastBts(selectedEntryValue, pagesTextValue);
    }

    @And("see the below columns name:")
    public void see_the_below_columns_name(DataTable dataTable) {
        admCenterMethods.appManagementColNamesVer(dataTable);
    }

    @Then("user scrolls down and see the {string} web table")
    public void user_scrolls_down_and_see_the_web_table(String string) throws Exception {
        admCenterMethods.appUserRoleHierarchyTable(string);
    }

    @When("types \"([^\"]*)\" in search text box, the system displays the \"([^\"]*)\"$")
    public void types_in_search_text_box_the_system_displays(String appId, String appName) throws Throwable {
        admCenterMethods.searchApp4UV(appId, appName);
    }

    @Then("clicks on \"([^\"]*)\" shield icon$")
    public void clicks_on_shield_icon_for_checking_user_verification(String appName) throws Throwable {
        admCenterMethods.clickOnShieldIcon(appName);
    }

    @Then("^\"([^\"]*)\" and the \"([^\"]*)\" are displayed$")
    public void and_the_are_displayed(String userVerificationPage, String appName) {
        admCenterMethods.uvScheduleTabsPages(userVerificationPage, appName);
    }

    @Then("{string} page for {string} tab with the below is displayed:")
    public void page_for_tab_with_the_below_is_displayed(String string, String string2, DataTable dataTable) throws Exception {
        admCenterMethods.prtcpntUsersTabPageVerif(string, string2, dataTable);
    }

}
