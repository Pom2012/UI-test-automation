package com.model.stepDefinition.sptCntrStepDef;

import com.model.pages.supCntr.SupportCenterModule;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SupportCenterStepDefs extends BasePage {
    SupportCenterModule supCentTabs = new SupportCenterModule();

    @Then("^\"([^\"]*)\" sees the below tabs in \"([^\"]*)\" module:$")
    public void sees_the_below_tabs_in_module(String role, String module, DataTable data) throws Throwable {
        supCentTabs.supCenTabsVer(data);
    }

    @When("^clicks on \"([^\"]*)\" and selects the below:$")
    public void clicks_on_and_selects_the_below(String batchTab, DataTable data) throws Throwable {
        supCentTabs.searchUsersInBatchOperTab(batchTab, data);
    }

    @When("selects {string} options from {string} dropdown, clicks on the first row checkbox and rejects the request")
    public void selects_options_from_clicks_on_checkbox_and_reject_the_request(String opt, String dropdown) throws Throwable {
        supCentTabs.rejOrApprAccsInBatchOpTab(opt, dropdown.toLowerCase());
    }
}
