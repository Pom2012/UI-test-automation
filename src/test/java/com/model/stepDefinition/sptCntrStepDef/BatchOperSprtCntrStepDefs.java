package com.model.stepDefinition.sptCntrStepDef;

import com.model.pages.supCntr.BatchOperations;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BatchOperSprtCntrStepDefs extends BasePage {
    BatchOperations batchOper = new BatchOperations();


    @And("^selects \"([^\"]*)\" and clicks search button$")
    public void selects_and_clicks_search_button(String app) throws Throwable {
        batchOper.selectAppliction(app);
    }

    @Then("^see the \"([^\"]*)\" table$")
    public void see_the_table(String arg1) {
        batchOper.webTableIsDisplayed(arg1);
    }

    @Then("^see the Request Status dropdown with the below filters:$")
    public void see_the_Request_Status_dropdown_with_the_below_filters(DataTable reqStatOpt) throws Throwable {
        batchOper.verifyReqStatusDropDown(reqStatOpt);
    }

    @When("^clicks on the first request ID of the table$")
    public void clicks_on_the_first_request_ID_of_the_table() throws Throwable {
        click(locateElementByXPath("(//table[@id='hdRequestTable']//td[1])[1]//a"));
    }

    @Then("^the \"([^\"]*)\" is popped up$")
    public void the_is_popped_up(String arg1) throws Throwable {
        batchOper.requestHistoryAlert(arg1);
    }

    @When("selects an application {string} dropdown and clicks on {string} button in {string}")
    public void selects_and_clicks_on_button_in(String dropdown, String sBtn, String tab) throws Exception {
        batchOper.selectApp(dropdown, sBtn);
    }

    @Then("{string} web table with the below columns is displayed:")
    public void web_table_with_the_below_columns_is_displayed(String string, DataTable dataTable) {
        batchOper.webTableAndColumnsVer(string, dataTable);
    }

    @Then("The total requests in the ALL status is displayed in {string}")
    public void the_total_requests_in_the_all_status_is_displayed_in(String string) throws Exception {
        batchOper.processedRecordsDataDisplayed(string);
    }

    @Then("verified the downloaded {string} file is matched with web table data")
    public void verified_the_downloaded_is_matched_with_web_table_data(String string) throws Exception {
        batchOper.csvFileBOVerification(string);
    }

    @And("from {string} tab, selects {string} app {string} role, and clicks {string}")
    public void selects_from_tab_and_clicks(String tab, String app, String role, String btn) throws Throwable {
        batchOper.searchAUserInBatchOperation(tab,app,role,btn);
    }

    @When("^selects \"([^\"]*)\" status, clicks on first row checkbox and clicks on \"([^\"]*)\" button$")
    public void selects_status_clicks_on_first_row_checkbox_and_clicks_on_button(String status, String btn) throws Throwable {
        batchOper.approveRejectedRrquest(status,btn);
    }

    @Then("searches {string} in Application Name field of {string}")
    public void searches_in_Application_Name_field_of(String tabName, String str) throws Throwable {
        batchOper.searchApplication(tabName, str);
    }

    @Then("selects requests with Pending and Approved status and rejects them")
    public void selects_requests_with_Pending_and_Approved_status_and_rejects_them() throws Throwable {
        batchOper.rejectRequests();
    }
    @Then("^\"([^\"]*)\" table is displayed$")
    public void table_is_displayed(String tableLabel) throws Throwable {
        highLightElement(locateElementByXPathText(tableLabel));
    }
    @Then("selects {string} from Status dropdown and see the approved role request")
    public void selects_from_Status_dropdown_and_see_the_requests(String status) throws Throwable {
        selectFromDropDownMenu(locateElementByXPath("//select[@ng-model='status']"), status);
        waitForCommonPageLoadingElements();
    }

    @Then("^sees the rejected request$")
    public void sees_the_rejected_request() throws Throwable {
        selects_from_Status_dropdown_and_see_the_requests("REJECTED");
        locateElementByXPath("//table[@id='hdRequestTable']//tbody//tr[contains(. ,'" + BasePage.requestID + "')]");
    }

    @Then("^clicks on \"([^\"]*)\" and handle the alert after \"([^\"]*)\" message is popped up$")
    public void clicks_on_and_handle_the_alert_after_message_is_popped_up(String submit, String arg2) throws Throwable {
        batchOper.submitApproveFromBO(submit,arg2);
    }

    @When("clicks to select the same request and clicks on {string} button")
    public void clicks_on_same_request_and_clicks_on_button(String btn) throws Throwable {
        batchOper.submitRejectFromBO(btn);
    }
    //the changes reflecting in the Report
    @Then("^see the changes made in Batch Operations$")
    public void see_the_changes_made_in_Batch_Operations() throws Throwable {
        batchOper.operationGeneratedInReport();

    }

}
