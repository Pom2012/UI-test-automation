package com.model.stepDefinition.rprtsStepDef;

import com.model.pages.repCenter.ReportsCenterPages;
import com.model.base.BasePage;
import com.model.locators.reportCenterLoc.Portal_ReportCenter_All_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static com.model.pages.repCenter.ReportsCenterPages.verifyTheUserInactRepGenerated;
import static com.model.base.Constants.IC_Reports.US_VERIF;

public class ReportsStepDefs extends BasePage {

    private String argument = null;
    ReportsCenterPages repCenMethods = new ReportsCenterPages();
    Portal_ReportCenter_All_Locators reportLocator = PageFactory.initElements(driver, Portal_ReportCenter_All_Locators.class);

    @Then("^verifies the user inactivity template is displayed in \"([^\"]*)\" tab$")
    public void verifies_the_user_inactivity_template_is_displayed_in_tab(String arg1) throws Throwable {
        verifyTheUserInactRepGenerated(arg1, argument);
    }

    @Then("^selects the below numbers entries from \"([^\"]*)\" and sees the dynamic showing entries:$")
    public void selects_the_below_numbers_entries_from_and_sees_the_dynamic_showing_entries(String reportName, DataTable entriesValue) throws Throwable {
        repCenMethods.selectEntriesInReports(reportName, entriesValue);
    }

    @And("clicks on the below sortable {string} columns and order it in {string} and {string} sequence:")
    public void clicks_on_the_below_sortable_columns_and_order_it_in_and_sequence(String repName, String desc, String asc, DataTable dataTable) throws Exception {
        repCenMethods.repTableColSorting(repName, desc, asc, dataTable);
    }

    @Then("sees {string} and the below features of the {string}:")
    public void sees_and_the_below_features_of_the(String string, String string2, DataTable dataTable) throws Exception {
        repCenMethods.featuresDisplayingInNonPrtcntUsers(string, string2, dataTable);
    }

    @When("scrolls down to {string}")
    public void scrolls_down_to(String string) {
        repCenMethods.scrollDownToReport(string);
    }

    @When("scrolls into view the {string}")
    public void scrolls_into_view(String val) {
        scroll_Into_View(locateElementByID("customAttributeValuesReport_wrapper"));
    }

    @And("clicks on the below {string} and see the desktop {string}:")
    public void clicks_on_the_below_and_see_the_desktop(String btn, String downlFile, DataTable dataTable) throws Exception {
        Map<String, String> repType = dataTable.asMap();
        if (repType.get("buttonName").contains("Excel")) {
            repCenMethods.downloadAndVerifyAppSumRepExcelFile(btn, downlFile, dataTable);
        }else {
            repCenMethods.downloadAndVerifyAppSumRepCsvFile(btn, downlFile, dataTable);
        }
    }

    @And("clicks on {string} button for {string}")
    public void clicks_on_button_for(String bnt, String repName) throws Exception {
        if (repName.equalsIgnoreCase(US_VERIF)) click(reportLocator.downloadReport4UVBtn);
        waitForCommonPageLoadingElements();
    }

}
