package com.model.stepDefinition.appConStepDef;

import com.model.pages.appConsole.ApplicationConsolePages;
import com.model.pages.appConsole.RequestAccess;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.model.pages.appConsole.ApplicationConsolePages.appConsTabsPagesVer;

public class AppConsoleRequestAccessStepDefs extends BasePage {

    RequestAccess reqAccess = new RequestAccess();
    ApplicationConsolePages appConsMeths = new ApplicationConsolePages();

    @Then("The below {string} is displayed:")
    public void the_below_is_displayed(String winDial, DataTable dataTable) {
        appConsMeths.unsuccessfulReqsMesRorReqWithPendStatus(winDial, dataTable);
    }

    @Then("clicks on {string} and close the window dialog")
    public void clicks_on_and_close_the_window_dialog(String okBtn) throws Exception {
        simpleClick(locateEleByXPathTextNormSpace(okBtn));
    }

    @Then("clicks on and views {string} page")
    public void clicks_on_and_views_page(String string) throws Exception {
        waitForCommonPageLoadingElements();
        click(locateEleByXPathTextNormSpace(string));
    }

    @When("enter a proper amount text value to justification text field")
    public void enter_a_proper_amount_text_value_to_justification_text_field() throws Exception {
        reqAccess.enterTextToJustificationField();
    }

    @Then("the below request status tabs in {string} page is displayed:")
    public void the_below_request_status_tabs_in_page_is_displayed(String appConTab, DataTable dataTable) {
        waitForCommonPageLoadingElements();
//        appConsoleTabsNameVerification(appConTab);
        appConsMeths.appConsTabsTitleNameVerification(appConTab);

    }

    @When("^clicks on \"([^\"]*)\" button from pagination feature and see the \"([^\"]*)\" tab page$")
    public void clicks_on_button_from_pagination_feature_and_see_the_tab_page(String page, String tab) throws Throwable {
        appConsMeths.paginationInRequestAndConfirmAccesstab(page, tab);
    }

    @Then("{string} sees the below tabs as an IC application BO:")
    public void sees_the_below_tabs_as_an_ic_application_bo(String string, DataTable dataTable) throws Exception {
        appConsTabsPagesVer(string, dataTable);
    }
}
