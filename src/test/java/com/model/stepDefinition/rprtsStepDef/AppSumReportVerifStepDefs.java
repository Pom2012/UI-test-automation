package com.model.stepDefinition.rprtsStepDef;

import com.model.pages.repCenter.AppSumReportPage;
import com.model.pages.repCenter.ReportsCenterPages;
import com.model.pages.repCenter.UserVerificationReportPage;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.pages.repCenter.ReportsCenterPages.reports4UserVerification;
import static com.model.base.Constants.IC_Reports.APP_SUM;
import static com.model.base.Constants.IC_Reports.US_VERIF;

public class AppSumReportVerifStepDefs extends BasePage {

    ReportsCenterPages repCenMeth = new ReportsCenterPages();
    AppSumReportPage appSumReps = new AppSumReportPage();
    UserVerificationReportPage UVerifReport = new UserVerificationReportPage();

    @And("^selects the below values from \"([^\"]*)\" dropdown of an \"([^\"]*)\" and clicks on \"([^\"]*)\":$")
    public void selects_the_below_values_from_dropdown_of_an_and_clicks_on(String appReportLbl, String appName, String viewBtn, DataTable opts) throws Throwable {
        repCenMeth.selectingApplicationReport(appReportLbl, appName, viewBtn, opts);
    }

    @Then("^scrolls down to \"([^\"]*)\" after \"([^\"]*)\" table is displayed$")
    public void scrolls_down_to_after_table_is_displayed(String subRepName, String repName) throws Throwable {
        appSumReps.navigateToTheReport(subRepName,repName);
    }

//    @When("^selects the below options from \"([^\"]*)\" and see the table's \"([^\"]*)\":$")
//    public void selects_the_below_options_from_and_see_the_table_s(String arg1, String arg2, DataTable arg3) throws Throwable {
//        appSumReps.selectEntriesAndShowingEntriesDynText(arg1,arg2,arg3);
//       }

    @When("^the below report table columns name are displayed:$")
    public void the_below_report_table_columns_name_are_displayed(DataTable colDeadings) {
        repCenMeth.repTableColumnsNameIsDisplayed(colDeadings);
    }

    @And("searches the below values in the search bar of the {string} Report:")
    public void searches_the_below_values_in_the_search_bar(String report, DataTable colsValues) throws Throwable {
        appSumReps.searchValueInUserRoleDetailsReport(report,colsValues);
      }

    @When("^selects the below option from Request Status and the option is displayed:$")
    public void selects_the_below_option_from_Request_Status_and_the_option_is_displayed(DataTable arg1) throws Throwable {
        appSumReps.requestStatusSelectOptioninASR(arg1);
       }

    @When("^clicks on the below buttons and see the Report table changes:$")
    public void clicks_on_the_below_buttons_and_see_the_Report_table_changes(DataTable arg1) throws Throwable {
        repCenMeth.reptsPagination(arg1);
    }

    @When("^clicks on \"([^\"]*)\", \"([^\"]*)\", Download Report \"([^\"]*)\" and the files downloaded$")
    public void clicks_on_and_the_files_downloaded(String excel, String pdf, String csv) throws Throwable {
        repCenMeth.downloadAndVerifyAppSumRepCsvFile();
    }

    @And("^searches the \"([^\"]*)\" and/or request ID in the \"([^\"]*)\" search bar$")
    public void searchesTheAndOrRequestIDInTheSearchBar(String practice, String subReportName) throws Throwable {
        searchBar("(//div[@id='applicationUserDetailReport_wrapper']//input)[1]", practice);
        clickWithJSE(locateElementByXPath("(//div[@id='applicationUserDetailReport_wrapper']//button)[1]"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("applicationUserDetailReport_wrapper")));
    }

    @And("views the user ID, below data when searches the request ID in {string}:")
    public void views_the_user_id_below_data_when_searches_the_request_id_in(String app, DataTable dataTable) throws Exception {
        if (app.equalsIgnoreCase(APP_SUM)) appSumReps.userVerificationInAppSumReport(dataTable);
        if (app.equalsIgnoreCase(US_VERIF)) UVerifReport.userVerificInUVReport(dataTable);
    }

    @Then("^views the below for \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" report data for \"([^\"]*)\" request:$")
    public void views_the_below_for_report_data_for_request(String rep, String app, String userRoleDetails4App, String reqStatus, DataTable tableData) throws Throwable {
        reports4UserVerification(rep, app, userRoleDetails4App, reqStatus, tableData);
        locateElementByXPathText("Back").click();
    }

    @Then("^scrolls down to \"([^\"]*)\" and search for \"([^\"]*)\"$")
    public void scrolls_down_to_and_search_for(String title, String searchValue) throws Throwable {
        repCenMeth.navigateToUVReportTab(title,searchValue);
    }
}


