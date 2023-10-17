package com.model.stepDefinition.commonStepDef;

import com.model.base.BasePage;
import io.cucumber.java.en.Then;

public class CMSPortalLogin_StepDefinition extends BasePage {

    @Then("^clicks the \"([^\"]*)\" button$")
    public void click_the_button(String buttonName) throws Throwable {
        click_bytext(buttonName);
    }

    @Then("^enters \"([^\"]*)\" as \"([^\"]*)\"$")
    public void enter_of(String textfieldName, String value) {
        typeonForm(textfieldName, value);
    }

    @Then("^search \"([^\"]*)\" by \"([^\"]*)\" filter$")
    public void search_by_filter(String searchLocator, String value) {
        searchby(searchLocator, value);
    }

    @Then("^User navigates to \"([^\"]*)\" tab$")
    public void user_navigates_to_tab_in_the_BCPI_Module(String tabName) throws Throwable {
        gotoXtab(tabName);
    }

    @Then("clicks on the {string} header button, and in Section {string}, clicks {string}")
    public void clicks_header_button_and_then_select_link_from_popup(String headerLink, String section, String link) throws Throwable {
        selectByHeaderLink(headerLink, section, link);
    }

    @Then("^logout$")
    public void logout() throws Throwable {
        logoutCurrentUserWithRetry();
    }
}
