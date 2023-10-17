package com.model.stepDefinition.rprtsStepDef;

import com.model.pages.repCenter.ReportsCenterPages;
import com.model.pages.userVerif.UserVerificationPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserVerReportStepDefs extends BasePage {

    UserVerificationPages userVerifMeths;
    ReportsCenterPages repsCntrMeths = new ReportsCenterPages();

    public UserVerReportStepDefs() throws Exception {
        userVerifMeths = new UserVerificationPages();
    }

    @Then("^views \"([^\"]*)\" page with the below data:$")
    public void views_subpages_with_the_below_data(String arg1, DataTable dt) throws Exception {
        userVerifMeths.userVerifAssocUsersPage(arg1, dt);
    }

    @When("^searches the user, provides the below action to review, selects the checkbox, justify and confirm it:$")
    public void searches_the_user_provides_the_below_action_to_review_selects_the_checkbox_justify_and_confirm_it(DataTable arg1) throws Throwable {
        userVerifMeths.reviewUserVerifPractice(arg1);
    }

    @Then("views {string} page title and the below {string}:")
    public void views_page_title_and_the_below(String usVerSch, String string2, DataTable dataTable) throws Exception {
        if (string2.contains("web")) {
            userVerifMeths.userVerifScheduleWebTable(usVerSch, dataTable);
        }
        if (string2.contains("features")) {
            userVerifMeths.userVerifScheduleFeatures(usVerSch, dataTable);
        }
        if (string2.contains("buttons")) {
            userVerifMeths.verifyBtns(dataTable);
        }
    }

    @Then("{string} and {string} are displaying")
    public void and_are_displaying(String string, String string2) throws Exception {
        waitForCommonPageLoadingElements();
        highLightElement(locateEleByXPathTextNormSpace(string));
        highLightElement(locateEleByXPathTextNormSpace(string2));
    }

    @When("clicks on {string} and sees the table of the following columns:")
    public void clicks_on_and_sees_the_table_of_the_following_columns(String string, DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        userVerifMeths.clickOnNPrtcptUssAndSee(string, dataTable);
        userVerifMeths.userVerifScheduleWebTable("User Verification Schedule", dataTable);
    }

    @When("counts the numbers of the Report entries")
    public void counts_the_numbers_of_the_report_entries() throws Exception {
        repsCntrMeths.countWebBasedUVRepEntries();
    }

    @Then("see the same amounts of entries in {string} file as it is in the report web table")
    public void see_the_amounts_of_rows_are_identical_to_the_report_web_table(String filename) throws Exception {
        repsCntrMeths.countCSVDownloadedRepsEntries(filename);
    }

    @And("selects an IC application from {string} dropdown and clicks on {string}")
    public void selects_an_ic_application_from_dropdown_and_clicks_on(String appType, String viewBtn) throws Exception {
        repsCntrMeths.selectAppReport(appType, viewBtn);
    }

}
