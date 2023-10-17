package com.model.stepDefinition.uVStepDef;

import com.model.pages.userVerif.UserVerificationPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UVModuleStepDefs extends BasePage {
    UserVerificationPages userVerifMeths;

    public UVModuleStepDefs() throws Exception {
        userVerifMeths = new UserVerificationPages();
    }

    @Then("{string} web table with the below columns:")
    public void web_table_with_the_below_columns(String assosUsrs, DataTable dataTable) {
        userVerifMeths.userVerifAssocUsersWTable(assosUsrs, dataTable);
    }

    @Then("^sees \"([^\"]*)\" page$")
    public void sees_page(String pageHeader) throws Throwable {
        if (pageHeader.contains("Schedule")) {
            userVerifMeths.userVerificationSchedulePage(pageHeader);
        } else {
            userVerifMeths.userVerAndAssosUsPage(pageHeader);
        }
    }

    @And("clicks on {string} file from {string} page and views the desktop version of the file")
    public void clicks_on_file_and_views_the_desktop_version_of_the_file(String fileFormat, String pageName) throws Exception {
        userVerifMeths.downloadUVSFileVerification(fileFormat, pageName);
    }

    @Then("views a blank page with the below text:")
    public void views_a_blank_page_with_the_below_text(DataTable dataTable) throws Exception {
        userVerifMeths.accessVerificationReportingPage(dataTable);
    }

    @Then("selects the below option from {string} select dropdown and see the table changes:")
    public void selects_the_below_option_from_select_dropdown_and_see_the_table_changes(String string, DataTable dataTable) throws Exception {
        userVerifMeths.reviewStatusSelectOptsVer(string, dataTable);
    }

    @When("click on User Verification Schedule info icon")
    public void click_on_user_verification_schedule_info_icon() throws Exception {
        userVerifMeths.clickOnUserVerSchInfoIcon();
    }

    @Then("the below text is displaying:")
    public void the_below_text_is_displaying(DataTable dataTable) throws Exception {
        userVerifMeths.verifyUserVerSchInfoIconText(dataTable);
    }

    @When("clicks {string} from {string} and sees the icon content")
    public void clicks_from_and_sees_the_icon_content(String infoicon, String page) throws Exception {
        userVerifMeths.uvInfoIconVerification(page, infoicon);
    }

    @When("clicks on {string} button for {string}, {string}, {string} and {string} columns")
    public void clicks_on_button_for_and_columns(String revBtn, String app, String org, String qtr, String stDate) throws Exception {
        userVerifMeths.clickOnUVSelectedReviewBtn(revBtn, app, org, qtr, stDate);
    }

    @Then("selects the below option from {string} select dropdown and see the changes in {string} col:")
    public void selects_the_below_option_from_select_dropdown_and_see_the_changes_in_col(String string, String string2, DataTable dataTable) throws Exception {
        userVerifMeths.userStatusSelectOptsVer(string, string2, dataTable);
    }

    @And("the {string} checkbox remains unselected and {string} button disabled")
    public void the_checkbox_remains_unselected_and_button_disabled(String string, String string2) throws Exception {
        userVerifMeths.attstnChkboxUnselctdSubmitBtnDisabled(string, string2);
    }

    @When("selects {string} option from {string} select dropdown")
    public void selects_option_from_select_dropdown(String activeOptd, String string2) throws Exception {
        userVerifMeths.selectAnOptionFromReviewStatus(activeOptd, string2);
    }

    @Then("clicks on selectable {string} checkbox")
    public void clicks_on_selectable_checkbox(String string) {
        userVerifMeths.clickOnAttestCheckbox(string);
    }

    @Then("{string} button becomes enabled")
    public void button_becomes_enabled(String string) throws Exception {
        userVerifMeths.submitBtnEnabled(string);
    }

    @Then("see the page {string} and below data info:")
    public void see_the_page_and_below_data_info(String string, DataTable dataTable) throws Exception {
        userVerifMeths.verifyNPUtitle(string);
        userVerifMeths.nonPrtcptUssReveiwPage(string, dataTable);
    }

    @Then("{string} web table with the below {string} features:")
    public void web_table_with_the_below_features(String table, String feature, DataTable dataTable) throws Exception {
        if (feature.contains("Select")) {
            userVerifMeths.selDropDwnVer(dataTable);
        }
    }
    @Then("a windows dialog box with {string} and {string} options and below text for {string} are displayed:")
    public void a_windows_dialog_box_with_and_options_and_below_text_are_displayed(String string, String string2, String string3, DataTable dataTable) throws Exception {
        userVerifMeths.cancelAndBackBtnsVer(string,string2,string3, dataTable);
    }

    @Then("the system navigates the user to {string} page")
    public void the_system_navigates_the_user_to_page(String string) throws Exception {
        userVerifMeths.nagigatesToUVSchedule(string);
    }
}
