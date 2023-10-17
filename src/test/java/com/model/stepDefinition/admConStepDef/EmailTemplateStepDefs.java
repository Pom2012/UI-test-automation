package com.model.stepDefinition.admConStepDef;

import com.model.pages.admConsole.AdminCenterPages;
import com.model.pages.repCenter.ReportsCenterPages;
import com.model.pages.supCntr.SupportCenterPages;
import com.model.base.BasePage;
import com.model.locators.reportCenterLoc.Portal_ReportCenter_All_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.pages.appConsole.EmailNotifications.emailTemplateSetUp;
import static com.model.pages.repCenter.ReportsCenterPages.calendarFeatureInReports;
import static com.model.pages.repCenter.ReportsCenterPages.emailTemplateAppSummRepVerification;

public class EmailTemplateStepDefs extends BasePage {

    Portal_ReportCenter_All_Locators reportLocator = PageFactory.initElements(driver, Portal_ReportCenter_All_Locators.class);
    ReportsCenterPages repCenMethods = new ReportsCenterPages();
    SupportCenterPages supCenMeths = new SupportCenterPages();
    AdminCenterPages admCentMeths = new AdminCenterPages();

    @Then("clicks on envelop icon for entering \"([^\"]*)\" email template builder$")
    public void clicks_on_envelop_icon_for_entering_email_template_builder(String appName) throws Throwable {
        webElement = locateElementByCSS("tbody>tr>:nth-child(7),tbody>td>:nth-child(7)");
        highLightElement(webElement);
        click(webElement);
    }

    @Then("^\"([^\"]*)\" label, \"([^\"]*)\" name, \"([^\"]*)\" box, \"([^\"]*)\" and the table is displayed$")
    public void label_name_box_and_the_table_is_displayed(String label, String appName, String search, String addFeature) throws Throwable {
        admCentMeths.emailTempleteBuilderInAdminManage(label, appName, search, addFeature);
    }

    @Then("views {string} after clicking on {string}")
    public void views_after_clicking_on(String addFeature, String addFeature2) throws Throwable {
        admCentMeths.emailTempleteViews(addFeature, addFeature2);
    }

    @Then("^value fields, \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" buttons are displayed$")
    public void value_fields_and_buttons_are_displayed(String addFeatureBtn, String clearBtn, String cancelBtn) throws Throwable {
        highLightElement(locateElementByXPathText(" " + addFeatureBtn));
        highLightElement(locateElementByCSS("#mat-tab-content-0-1"));
        highLightElement(locateElementByXPathText(" " + clearBtn));
        highLightElement(locateElementByXPathText(" " + cancelBtn));
    }

    @When("^the user inputs \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_user_inputs(String template, String subjectLine, String from, String bodyText, String enableDisable, String html) throws Throwable {
        emailTemplateSetUp(template, subjectLine, from, bodyText, enableDisable, html);
    }

    @When("^clicks on \"([^\"]*)\" and views \"([^\"]*)\"$")
    public void clicks_on_and_views(String clickBtn, String arg2) throws Throwable {
        admCentMeths.clickAndSwichEmailTempleteViews(clickBtn, arg2);
    }

    @When("^user clicks on \"([^\"]*)\" tab and fills out the json body field and \"([^\"]*)\"$")
    public void user_clicks_on_tab_and_fills_out_the_json_body_field_and(String emailTab, String sendBtn) throws Throwable {
        supCenMeths.verifyEmailSenterEndpoint(emailTab, sendBtn);
    }

    @Then("^user views \"([^\"]*)\" status code and \"([^\"]*)\" message in the json body$")
    public void user_views_status_code_and_message_in_the_json_body(String statusCode, String message) throws Throwable {
        supCenMeths.successMessageText(statusCode,message);
     }

    @And("^select the email template for \"([^\"]*)\" and clicks on \"([^\"]*)\" button$")
    public void select_the_email_template_and_clicks_on_button(String app, String arg1) throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(app)));
        locateElementByCSS("#mat-input-0").sendKeys(templateName);
        locateElementByXPath("(//*[text() = ' " + arg1 + "'])[2]").click();
    }

    @Then("^views \"([^\"]*)\"$")
    public void views(String arg1) throws Throwable {
        locateElementByXPathText(" " + arg1 + " ");
    }

    @When("^selects \"([^\"]*)\" from \"([^\"]*)\" and clicks on \"([^\"]*)\" button$")
    public void selects_from_and_clicks_on_button(String arg1, String arg2, String arg3) throws Throwable {
        if (arg3.contains("Update Template")) {
            highLightElement(locateElementByXPathText(arg2));
            selectFromDropDownMenu(locateElementByCSS("#mat-input-5"), arg1);
            click(locateElementByXPathText(" " + arg3));
        }
        if (arg3.contains("Report")) {
            repCenMethods.selectingApplicationReport(arg1, arg2);
        }
    }

    @Then("^user navigates to \"([^\"]*)\" page$")
    public void user_navigates_to_page(String arg1) throws Throwable {
        highLightElement(locateElementByXPathText(arg1));
    }

    @When("^clicks on \"([^\"]*)\" report button$")
    public void clicks_on_report_button(String arg1) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("cicdim-form-wrapper")));
        click(reportLocator.adminActivityLink);
        wait.until(ExpectedConditions.visibilityOf(reportLocator.adminActivityReportTotal));
    }


    @When("^provides the below values and filter the results:$")
    public void provides_the_below_values_and_filter_the_results(DataTable reportFeature) throws Throwable {
        calendarFeatureInReports(reportFeature);
    }

    @Then("^sees the changes in below columns:$")
    public void sees_the_changes_in_below_columns(DataTable colsName) {
        emailTemplateAppSummRepVerification(colsName);
    }

}
