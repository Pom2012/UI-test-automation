package com.model.stepDefinition.portalStepDef;

import com.model.pages.appConsole.Unauthportal;
import com.model.base.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

public class unAuthAccess_step_defs extends BasePage {
    Unauthportal unauthportal = new Unauthportal();

    @When("^a \"([^\"]*)\" clicks on CMS \"([^\"]*)\" and select \"([^\"]*)\" from \"([^\"]*)\"$")
    public void a_clicks_on_CMS_and_select_from(String user, String apps, String icOpts, String selUApps) throws Throwable {
        unauthportal.selectICFromCMSApps(user, apps, icOpts, selUApps);
    }

    @When("^IC user with app model role scroll down to \"([^\"]*)\" and select \"([^\"]*)\" application$")
    public void ic_user_with_app_model_role_scroll_down_to_and_select_application(String arg1, String applName) throws Throwable {
        scroll_Down(locateElementByCSS("#cms-fya-container"));
        click(locateElementByCSS("#ngSelectApp"));
        locateElementByCSS("#textSearch").sendKeys(applName + Keys.ENTER);
    }

    @Then("^\"([^\"]*)\" with texts pops up$")
    public void with_texts_pops_up(String popUpText) throws Throwable {
        unauthportal.openDialog(popUpText);
    }

    @Then("^user scroll down to \"([^\"]*)\" button and navigate to \"([^\"]*)\" page in another windows tab$")
    public void user_scroll_down_to_button_and_navigate_to_page_in_another_windows_tab(String linkText, String arg2) throws Throwable {
        unauthportal.clickGetStartedAndHandleWinTab(linkText);
    }

    @When("^enter \"([^\"]*)\" in \"([^\"]*)\" text field and click on it$")
    public void enter_in_text_field_and_click_on_it(String appName, String inputPlaceholder) throws Throwable {
        unauthportal.searchICModuleAppInUnauthPortalPage(appName, inputPlaceholder);
    }

//    @Then("^clicks on \"([^\"]*)\" tile$")
//    public void clicks_on_tile(String arg1) throws Throwable {
//        switchToEnvIframeByID("Unauth");
//        scroll_Down(locateElementByCSS("[data-appname='" + arg1 + "']"));
//        click(locateElementByCSS("[data-appname='" + arg1 + "']"));
//    }

    @Then("^user is redirected to login box$")
    public void user_is_redirected_to_login_box() {
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByCSS("#loginAction"));
    }

    @Then("^the application main page and \"([^\"]*)\" is displayed$")
    public void the_application_main_page_and_is_displayed(String actualAppName) throws Exception {
        unauthportal.getAccessToICModuleAppVerification(actualAppName);
    }
}
