package com.model.stepDefinition.appConStepDef;

import com.model.pages.appConsole.ApplicationConsolePages;
import com.model.pages.appConsole.MyRequests;
import com.model.pages.appConsole.RequestAccess;
import com.model.utility.randomData;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RequestAccess_StepDefinition extends BasePage {

    public static List<List<String>> list = null;
    public RequestAccess requestAcc = new RequestAccess();
    MyRequests myRequests=new MyRequests();
    ApplicationConsolePages appConsMeths = new ApplicationConsolePages();
    public com.model.utility.randomData randomData = new randomData(null);
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    Portal_AppConsole_RequestAccess_Locators appConsoleRequest = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);

    public RequestAccess_StepDefinition() {
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
    }

    @And("^opens Request New Access page$")
    public void opens_something_page() throws Throwable {
        requestAcc.openRequestNewAccessPage();
    }

    @When("^user submits new request for \"([^\"]*)\" application, \"([^\"]*)\" role, \"([^\"]*)\" attribute$")
    public void user_submits_new_request_for_application_role_attribute(String appName, String role, String contrType) throws Throwable {
        requestAcc.submitANewRequest(appName, role, contrType, "B02", "B02");
    }

    @When("user {string} submits new access request for app {string}, role {string}")
    public void user_submits_new_access_request(String userRowStr, String app, String app_role) throws Throwable {
        requestAcc.submitAccessRequest(app, app_role, Integer.parseInt(userRowStr));
    }

    @Then("^confirmation message will appear and the request will show as pending$")
    public void confirmation_message_will_appear_and_the_request_will_show_as_pending() throws Throwable {
        wait(1000);
        verifyWithTrueAssertions(appConsoleRequest.confirmPopUp);
        //TODO: redirect to more consistent handling in Alert class
        BasePage.requestID = getRequestID(appConsoleRequest.confirmPopUp);
        click(appConsoleRequest.okBtn);
        status = "PENDING";
        if (requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        requestAcc.searchrequestById(status, BasePage.requestID);
    }

    @Then("request confirmation message with {string} requests for a role is appeared")
    public void request_confirmation_message_with_requests_for_a_role_is_appeared(String string) throws Exception {
        requestAcc.submitReqConfirm(string);
    }

    @Then("^confirmation message for \"([^\"]*)\" is appeared and the request will show as pending$")
    public void confirmation_message_for_is_appeared_and_the_request_will_show_as_pending(String appName) throws Throwable {
        verifyWithTrueAssertions(appConsoleRequest.confirmPopUp);
        requestID = getReqFromComformMsg(appConsoleRequest.confirmPopUp);
        System.out.println("request ID is " + requestID);
        click(appConsoleRequest.okBtn);
    }

    @When("^logged into personal email and looks for the request ID and the condition below$")
    public void logged_into_personal_email_and_looks_for_the_request_ID_and_the_condition_below(DataTable options) {
        requestAcc.retrieveRequestIDFromAnExternalEmail(options);
    }

    @When("^provides justification and clicks on \"([^\"]*)\" button$")
    public void provides_justification_and_clicks_on_button(String arg1) throws Throwable {
        justification = "IC automation testing";
        type(locateElementByID("newRequestForm_ic-comment_comment_3"), justification);
        click(RequestAccess_Locators.confirmButton);
    }

    @When("^types justification and clicks on \"([^\"]*)\" button$")
    public void types_justification_and_clicks_on_button(String arg1) {
        type(locateElementByID("newRequestForm_ic-comment_comment_3"), "IC automation testing");
        RequestAccess_Locators.confirmButton.click();
    }

    @When("^provides a justification and clicks on \"([^\"]*)\" button$")
    public void provides_a_justification_and_clicks_on_button(String confBtn) throws Throwable {
        justification = "Requesting an access to Dynamic Custom Attribute role";
        type(locateElementByID("newRequestForm_ic-comment_comment_3"), justification);
        click(RequestAccess_Locators.confirmButton);
    }

    @And("^clicks on \"([^\"]*)\" button without filling the fields$")
    public void clicks_on_button_without_filling_the_fields(String arg1) throws Throwable {
        click(locateElementByXPathText(arg1));
    }

    @Then("^views the below errors in request access module:$")
    public void views(DataTable errorList) throws Throwable {
        verifyErrorMessages(errorList);
    }

    @Then("points to feature text and sees the tooltip text below in {string}:")
    public void points_to_feature_text_and_sees_the_tooltip_text_below(String str, DataTable dataTable) {
        requestAcc.tooltipTextVerification(str, dataTable);
    }

    @When("selects options from the below features:")
    public void selects_options_from_the_below_features(DataTable dataTable) throws Exception {
        requestAcc.selectsOptionsFromFeaturecs(dataTable);
    }

    @Then("accepts the alert box with the {string} when it is displayed")
    public void alert_box_with_the_is_displayed(String string) {
        requestAcc.alertDisplayed(string);
    }

    @And("^opens \"([^\"]*)\" page$")
    public void opens_page(String arg1) throws Throwable {
        click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
    }

    @And("search and cancel one's own previous requests for {string} app")
    public void search_and_cancel_the_previous_approved_request_for_app(String app) throws Throwable {
        appConsMeths.rejectOwnPreviousRequestsForAccessInAppCon(app);
        click(RequestAccess_Locators.requestNewAccessLink);
    }

    @And("^selects the below features:$")
    public void selects_the_below_features(DataTable cucOptTable) throws Throwable {
        requestAcc.requestAccessToICApplication(cucOptTable);
    }

    @And("^Clicks on \"([^\"]*)\", provides justification and submit the request$")
    public void clicks_on_provides_justification_and_submit_the_request(String arg1) throws Throwable {
        click(RequestAccess_Locators.addButton);
        justification = randomData.createStr(5);
        typeonForm("This justification field is required", justification);
        System.out.println("Justification string length: " + justification.length());
        simpleClick(RequestAccess_Locators.confirmButton);
    }

    @Then("^searches for the request ID and cancel this request$")
    public void searches_for_the_request_ID_and_cancel_this_request() throws Throwable {
        type(RequestAccess_Locators.searchTextBox, requestID);
        click(RequestAccess_Locators.searchBtn);
        highLightElement(locateElementByXPath("//div[@id='reqtable-frame-ra']//td"));
        appConsMeths.simpleCancelRequest("Cancel the Request");
    }

    @Then("^\"([^\"]*)\" as a \"([^\"]*)\" received conformation message for \"([^\"]*)\"$")
    public void asAReceivedConformationMessageForWith(String usID, String userRole, String appAcro, String reqID) throws Throwable {
        requestAcc.getReqIDAndStoreIt(usID, userRole, appAcro, reqID);
    }

    @When("^verified the below does not exist with \"([^\"]*)\" request status:$")
    public void verified_the_below_does_not_exist_with_request_status(String reqStatus, DataTable value) throws Throwable {
        requestAcc.ifUserHasAccessToICApp(reqStatus, value);
    }

    @When("{string} get data from {string} and submit request to IC app")
    public void get_data_from_and_submit_requests_to_IC_application(String user, String fileName) throws Throwable {
        requestAcc.requestAccess4MultipleRolesACUpdate(fileName, env2);
    }

    @Then("gets data from {string} and makes sure the role does not exist")
    public void clicks_on_tab_and_on_the_below_subtabs_and_sees(String dataSource) throws Throwable {
        myRequests.searchInMyRequests(dataSource);
    }

    @Then("^clicks on \"([^\"]*)\" tab and on the below subtabs and sees:$")
    public void clicks_on_tab_and_on_the_below_subtabs_and_sees(String appConTab, DataTable cucTabTable) throws Throwable {
        appConsMeths.requestStatusTabs(appConTab, cucTabTable);
    }

    @Then("{string} text is displaying")
    public void text_is_displaying(String expectedText) throws Exception {
        requestAcc.requestStaticTextVer(expectedText);
    }

    @And("^also views the below features:$")
    public void also_views_the_below_features(DataTable cucTabTable) throws Throwable {
        requestAcc.requestAccessUIAttributesVer(cucTabTable);
    }

    @Then("the dynamic {string} text is changed")
    public void the_dynamic_text_is_changed(String string) throws Exception {
        requestAcc.verifyDynamicRemainCharsText(string);
    }

    @Then("see the below features:")
    public void see_the_below_features(DataTable dataTable) throws Exception {
        appConsMeths.requestNewAccessPageFeaturesVerification(dataTable);
    }

    @Then("The system displays the {string} page with the below text and fields:")
    public void the_system_displays_the_page_with_the_below_text_and_fields(String str, DataTable dataTable) {
        requestAcc.acRAAngUpt(str, dataTable);
    }

    @Then("the {string} changed after entering the bellow into Justification:")
    public void the_changed_after_entering_the_bellow_into_justification(String string, DataTable dataTable) {
        requestAcc.justificationTextBoxVerification(string, dataTable);
    }

    @Then("the system removes all selected and entered fields")
    public void the_system_removes_all_selected_and_entered_fields() throws Exception {
        requestAcc.cancelBtnVerification();
    }

    @When("selects the below features for and application:")
    public void selects_the_below_features_for_and_application(DataTable dataTable) {
        requestAcc.selectAnApplication(dataTable);
    }

    @When("searches the below and the system returns results for the requested data:")
    public void searches_the_below_and_the_system_returns_results_for_the_requested_data(DataTable dataTable) {
        requestAcc.searchBoxVerification(dataTable);
    }

    @And("searches for request ID with {string} status and {string} it")
    public void search_for_request_id_with_status_and_it(String currentStatus, String statusToBeModify) throws Exception {
        requestAcc.approveRejectReq(currentStatus, statusToBeModify);
    }

}
