package com.model.stepDefinition.sptCntrStepDef;

import com.model.pages.supCntr.SearchUsersPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchUsersInSuptCenStepDefs extends BasePage {

    SearchUsersPages searchUsMeths = new SearchUsersPages();

    @Then("^searches the given information in below fields in \"([^\"]*)\":$")
    public void searches_the_given_information_in_below_fields_in(String tabName, DataTable value) throws Throwable {
        searchUsMeths.searchUsrs(tabName, value);
    }

    @Then("^searches a certain custom attribute in \"([^\"]*)\":$")
    public void searches_a_certain_custom_attribute_in(String tabName, DataTable value) throws Throwable {
        searchUsMeths.searchCAs(tabName, value);
    }

    @When("^typed a \"([^\"]*)\" ID in search text box")
    public void typed_a_value_in_user_ID_search_text_box(String user) throws Throwable {
        if (user.equalsIgnoreCase("user")) userID=searchUsMeths.searchUser(4);
        if (user.equalsIgnoreCase("approver")) userID=searchUsMeths.searchUser(2);
        searchUsMeths.searchAValueInSearchUserTab(userID);
    }

    @When("^clicks on user ID hyperlink after the search result displayed a web table")
    public void clicks_on_user_ID_hyperlink_after_the_search_result_displayed_a_web_table() throws Throwable {
        click(locateElementByXPathText(userID));
    }

    @When("^user details screen is displayed")
    public void user_details_screen_is_displayed() {
        searchUsMeths.userDetailsScreenDisplayed();
    }

    @And("^sorts the below columns and orders it in \"([^\"]*)\" sequence:$")
    public void sorts_the_below_columns_and_orders_it_in_and_sequence(String sequence, DataTable colsName) {
        searchUsMeths.verifySorting(sequence, colsName);
    }

    @Then("^see the below content:$")
    public void see_the_below_content(DataTable options) throws Throwable {
        searchUsMeths.userInfoVerificationInSearchUserScreen(options);
    }

    @And("^clicks on a request and see the \"([^\"]*)\" is popped up$")
    public void clicks_on_a_request_and_see_the_is_popped_up(String arg1) throws Throwable {
        searchUsMeths.reqIDPopUpInSearchTab();
    }

    //TODO: NEXT: Approve blind row?
    @And("^approves a rejected request selected from rejected requests$")
    public void approves_a_rejected_request_selected_from_rejected_requests() throws Throwable {
        searchUsMeths.apprReqIDinSearchTab();
    }

    @Then("^rejects the newly approved request from approved requests$")
    public void rejects_the_newly_approved_request_from_approved_requests() throws Throwable {
        searchUsMeths.rejReqIDinSearchTab();
    }

    @Then("views the below columns and values in {string}:")
    public void views_the_below_columns_and_values_in_tab_page(String table, DataTable dataTable) throws Exception {
        searchUsMeths.searchResultsAndTable(userID, table, dataTable);
    }

    @Then("selects the below options and the system displays requests' status accordingly:")
    public void selects_the_below_options_and_the_system_displays_requests_status_accordingly(DataTable dataTable) throws Exception {
        searchUsMeths.selectdropdownsVerification(dataTable);
    }

    @Then("selects the below radio buttons' options in user screen and see dynamic {string}:")
    public void selects_the_below_radio_buttons_options_in_user_screen_and_see_dynamic(String string, DataTable dataTable) throws Exception {
        searchUsMeths.selectRadioButtons(string, dataTable);
    }

    @Then("clicks on and see a text as expected for the below:")
    public void clicks_on_and_see_the_below_text(DataTable dataTable) throws Exception {
        searchUsMeths.verifyInfoIcon(dataTable);
    }

    @And("selects from dropdown the status and see the request ID as {string}")
    public void selects_from_dropdown_the_and_see_the_request_id_as(String status) {
        searchUsMeths.selectAndSeeReqIDAndStatus(status);
    }

    @Then("selects the below radio button options in user details screen and see the web table:")
    public void selects_the_below_radio_button_options_in_user_details_screen_and_see_web_table(DataTable dataTable) throws Exception {
        searchUsMeths.selectOptsAndSeeWTab(dataTable);
    }

    @When("clicks on {string} button and see the below alert message:")
    public void clicks_on_button_and_see_the_below_alert_message(String string, DataTable dataTable) {
        searchUsMeths.verifyAlertText4EmptySearch(string, dataTable);
    }

    @When("typed a invalid value in {string} search text field")
    public void typed_a_invalid_value_in_user_id_search_text_field(String s) {
        searchUsMeths.typedInvalidUserIDValue(s);
    }

    @Then("{string} banner, {string} tab page and the below tabs are displayed:")
    public void banner_tab_page_and_the_below_tabs_are_displayed(String banner, String string2, DataTable dataTable) {
        searchUsMeths.hdPageAndTabsVerification(banner, string2, dataTable);
    }

    @And("the below texts and fields are displayed:")
    public void the_below_texts_and_fields_are_displayed(DataTable dataTable) {
        searchUsMeths.searchUsersTabPageVer(dataTable);
    }

}
