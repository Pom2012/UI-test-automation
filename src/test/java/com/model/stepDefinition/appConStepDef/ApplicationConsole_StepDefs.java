package com.model.stepDefinition.appConStepDef;

import com.github.javafaker.Faker;
import com.model.pages.appConsole.ApplicationConsolePages;
import com.model.pages.appConsole.ConfirmAccess;
import com.model.pages.appConsole.EmailNotifications;
import com.model.pages.appConsole.RequestAccess;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.pages.appConsole.ApplicationConsolePages.appConsTabsVerification;
import static com.model.utility.Alerts_and_Requests.handleAlertAndReturnText;

public class ApplicationConsole_StepDefs extends BasePage {
    BasePage_Locators login;
    EmailNotifications emailNotifications = new EmailNotifications();
    public static Faker faker = new Faker();
    RequestAccess reqAccTab=new RequestAccess();
    ConfirmAccess confirmAccess = new ConfirmAccess();
    ApplicationConsolePages appConsMeths=new ApplicationConsolePages();


    public ApplicationConsole_StepDefs(){
        login = PageFactory.initElements(driver, BasePage_Locators.class);
    }

    @And("^views and clicks on envelope icon$")
    public void views_and_clicks_on_envelope_icon() throws Throwable {
        highLightElement(locateElementByID("cicdim-email-pref-icon"));
        click(locateElementByID("cicdim-email-pref-icon"));
    }

    @Then("^the below Email Notifications options are displayed:$")
    public void the_below_Email_Notifications_options_are_displayed(DataTable emNotOpts) throws Throwable {
        emailNotifications.verifyEmailNotificationTitleAndCheckBoxOptions(emNotOpts);
    }

    @Then("^the below the default de/selected Email Notifications options are displayed:$")
    public void the_below_the_default_deselected_Email_Notifications_options_are_displayed(DataTable emNotOpts) throws Throwable {
        emailNotifications.verifydefaultEmailNotificationCheckBoxOptions(emNotOpts);
    }

    @Then("^selects the below and verify the rest options are unselected$")
    public void selects_the_below_and_verify_the_rest_options_are_unselected(DataTable emNotOpts) throws Throwable {
        emailNotifications.verifySelectedReceiveNoEmailNotifOpt(emNotOpts);
    }

    @Then("^unselects the below and selects the rest options$")
    public void unselects_the_below_and_selects_the_rest_options(DataTable emNotOpts) throws Throwable {
        emailNotifications.verifyUnSelectedReceiveNoEmailNotifOpt(emNotOpts);
    }

    @And("alert message {string} is displayed")
    public void alert_message_is_displayed(String expectedAlertMessage) {
        String actualAlertMessage = handleAlertAndReturnText();
        verifyActualText(actualAlertMessage, expectedAlertMessage);
    }

    @And("^\"([^\"]*)\" options is pop-up$")
    public void options_is_pop_up(String arg1) throws Throwable {
        highLightElement(locateElementByXPathText(arg1 + " "));
    }

    @And("^user clicks on \"([^\"]*)\" button$")
    public void user_clicks_on_button(String arg1) throws Throwable {
        simpleClick(locateEleByXPathTextNormSpace(arg1));
    }

    @When("^user clicks on \"([^\"]*)\" as a app BO$")
    public void user_clicks_on_as_a_app_BO(String arg1) throws Throwable {
        highLightElement(locateElementByXPathText(arg1));
        locateElementByXPathText(arg1).click();
    }

    @Then("^selects the below options from select drop-down and views different page options:$")
    public void selects_the_below_options_from_select_drop_down_and_views(DataTable voptions) throws Throwable {
        confirmAccess.viewOptionsInConfirmAccess(voptions);
    }

    @When("get access to Angular update for {string}")
    public void get_access_to_angular_update_for(String string) throws Exception {
        waitForCommonPageLoadingElements();
        clickWithJSE(locateElementByCSS("li[heading='EmailNotifications'] a[class='ng-binding']"));
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByCSS("[ui-view='emailLog'] div p a"))).click();
    }

    @Then("{string} title is displayed")
    public void banner_is_displayed(String banner) {
        appConsMeths.ac_BannerIsDisplayed(banner);
    }

    @Then("the below tabs are displayed on {string} page:")
    public void page_and_the_below_tabs_are_displayed(String page, DataTable dataTable) {
        appConsMeths.appConsoleTabsAngUpt(page,dataTable);
    }

    @Then("^sees the below tabs as an IC application BO:$")
    public void sees_the_below_tabs_as_an_IC_application_BO(DataTable cucTabTable) {
        appConsTabsVerification(cucTabTable);
    }

    @Then("^clicks on \"([^\"]*)\" tab and see the numbers of requests in below subtabs:$")
    public void clicks_on_tab_and_see_the_numbers_of_requests_in_below_subtabs(String portlTab, DataTable sTabs) throws Throwable {
        reqAccTab.requestConfirmNumbers(portlTab, sTabs);
    }

}