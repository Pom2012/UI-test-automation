package com.model.stepDefinition.appConStepDef;

import com.model.pages.appConsole.ConfirmAccess;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_ConfirmAccess_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;

public class ConfirmAccess_StepDefinition extends BasePage {
    public ConfirmAccess confirmAcc = new ConfirmAccess();
    Portal_AppConsole_ConfirmAccess_Locators appConsoleConf = PageFactory.initElements(driver, Portal_AppConsole_ConfirmAccess_Locators.class);

    @And("^opens Confirm Access page$")
    public void opens_confirm_access_page() throws Throwable {
        highLightElement(appConsoleConf.confirmAccessTab);
        click(appConsoleConf.confirmAccessTab);
        wait(2000);
    }

    @And("clicks on the {string} tab")
    public void clicks_on_the_tab(String tab) throws Exception {
        click(getElementByLocatorAndSearchType("//a[contains(*,'" + tab + "')]", "xpath"));
    }

    @Then("^approves the request$")
    public void approves_the_request_() throws Throwable {
        wait(2000);
        confirmAcc.approveRequest();
        wait(2000);
    }

    @And("^request has been approved$")
    public void request_has_been_approved() throws Throwable {

    }

    @Then("^rejects the request$")
    public void rejects_the_request_() throws Throwable {
        confirmAcc.rejectRequest();
    }

    @Then("IC {string} approves the {string} request for the model {string} for user {string}")
    public void with_IC_approves_the_request_for_the_model(String approverICrole, String reqStatus, String app, String assigneeRowID) throws Throwable {
        confirmAcc.confirmPendRequest(assigneeRowID, approverICrole, reqStatus, app);
    }

    @Then("^selects and sees the below view options from select dropdown:$")
    public void selects_and_sees_the_below_view_options_from_select_dropdown(DataTable opts) throws Throwable {
        confirmAcc.viewOptions(opts);
    }
}
