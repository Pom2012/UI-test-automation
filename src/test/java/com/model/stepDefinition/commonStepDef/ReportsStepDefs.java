package com.model.stepDefinition.commonStepDef;

import com.model.pages.repCenter.ReportsCenterPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

public class ReportsStepDefs extends BasePage {
    ReportsCenterPages repCenMethods = new ReportsCenterPages();

    @When("^\"([^\"]*)\" clicks on and sees the below reports for an \"([^\"]*)\":$")
    public void clicks_on_and_sees_the_below_reports_for_an(String role, String application, DataTable reports) throws Throwable {
        repCenMethods.iterateThroughTheReports(role, application, reports);
    }
}
