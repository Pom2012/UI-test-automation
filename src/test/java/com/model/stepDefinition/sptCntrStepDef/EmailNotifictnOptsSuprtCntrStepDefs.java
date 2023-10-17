package com.model.stepDefinition.sptCntrStepDef;


import com.model.pages.supCntr.SearchUsersPages;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailNotifictnOptsSuprtCntrStepDefs extends BasePage {
    SearchUsersPages searchUsersPages =new SearchUsersPages();

    @And("^clicks on Email Preference settings icon$")
    public void clicks_on_Email_Preference_settings_icon() throws Throwable {
        searchUsersPages.clicksOnEmailNotificationIcon();
      }

    @And("^sees \"([^\"]*)\" the below \"([^\"]*)\" options:$")
    public void sees_the_below_options(String s,String s1, DataTable dataTable) throws Throwable {
        searchUsersPages.verifyDefaulSelection(s,s1, dataTable);
    }

    @Then("^\"([^\"]*)\" options is popped-up with below select options:$")
    public void options_is_popped_up_with_below_select_options(String arg1, DataTable arg2) throws Exception {
        searchUsersPages.verifyEmailNotCheckBox(arg1,arg2);
    }

    @When("^clicks on \"([^\"]*)\" checkbox$")
    public void clicks_on_checkbox(String arg1) throws Throwable {
        searchUsersPages.selectACheckBoc(arg1);
    }

    @When("^deselect \"([^\"]*)\" and select the other options$")
    public void deselect_and_select_the_other_options(String arg1) throws Throwable {
        searchUsersPages.deselectedEmNotCheckBoxOpts(arg1);

    }

    @Then("^updates the Email Notification pops-up$")
    public void updates_the_Email_Notification_pops_up() throws Throwable {
        searchUsersPages.clicksOnEmNotUpdate();

    }
}


