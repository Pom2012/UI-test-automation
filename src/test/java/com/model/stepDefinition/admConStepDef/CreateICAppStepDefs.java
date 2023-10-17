package com.model.stepDefinition.admConStepDef;

import com.model.pages.admConsole.AdminCenterPages;
import com.model.base.BasePage;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.utility.DataHelper.getAppNameFromExcFile;

public class CreateICAppStepDefs extends BasePage {
    AdminCenterPages adCenMeths = new AdminCenterPages();
    ApplicationsManagement_Locators alocators = PageFactory.initElements(driver, ApplicationsManagement_Locators.class);


    @When("^clicks on \"([^\"]*)\" button, then see \"([^\"]*)\" page$")
    public void clicks_on_button_then_see_page(String arg1, String arg2) throws Throwable {
        waitForCommonPageLoadingElements();
        click(locateEleByXPathTextNormSpace(arg1));
        isElementPresent(locateEleByXPathTextNormSpace(arg2));
    }

    @When("^provide the information for text fields and select dropdowns:$")
    public void provide_the_information_for_text_fields_and_select_dropdowns(DataTable appfields) throws Throwable {
        adCenMeths.creatingApps(appfields);
    }

    @And("selects from the below select dropdowns:")
    public void selects_from_the_below_select_dropdowns(DataTable dataTable) {
        adCenMeths.selectAppProperties(dataTable);
    }

    @When("^clicks on \"([^\"]*)\", scrolling down and clicks on \"([^\"]*)\"$")
    public void clicks_on_scrolling_down_and_clicks_on(String verifyBtn, String addAppBtn) throws Throwable {
        simpleClick(locateElementByXPath("//button[normalize-space()='" + verifyBtn + "']"));
    }

    @Then("^user is on \"([^\"]*)\" page and see data table$")
    public void user_is_on_page_and_see_data_table(String arg1) {
        if (arg1.equalsIgnoreCase("User roles")) {
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("user-role-view")));
            isElementPresent(locateElementByID("user-role-view"));
        }
    }

    @Then("^clicks on roles icon after searching the application in search text field$")
    public void clicks_on_roles_icon_after_searching_the_application_in_search_text_field() throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(alocators.applSearchbox));
        String application = getAppNameFromExcFile("newApps", "Apps", env2);
        type(alocators.applSearchbox, application);
        click(locateElementByXPath("(//div[@id='cicdim-app-table_wrapper']//i)[3]"));
    }

    @When("^the user clicks on \"([^\"]*)\" button$")
    public void the_user_clicks_on_button(String arg1) throws Throwable {
        if (arg1.equalsIgnoreCase("Add New Role")) {
            clickWithJSE(locateElementByXPath("//button[normalize-space()='Add New Role']"));
        }
    }

    @Then("^views \"([^\"]*)\" page$")
    public void views_page(String arg1) throws Exception {
        if (arg1.equalsIgnoreCase("Add Application Role Information")) {
            highLightElement(locateElementByID("AddApplicationRoleForm"));
        }
        if (arg1.contains("Request Access")) {
            locateElementByXPath("//b[normalize-space(text())='Request Access']");
            isElementPresent(locateElementByXPath("//b[normalize-space(text())='Request Access']"));
        }
    }

    @Then("^provide the role information for text fields and select from dropdowns:$")
    public void provide_the_role_information_for_text_fields_and_select_from_dropdowns(DataTable rolefields) throws Throwable {
        adCenMeths.creatingRoles(rolefields);
    }

    @Then("^clicks on update \"([^\"]*)\" icon after searching the application in search text field$")
    public void clicks_on_update_icon_after_searching_the_application_in_search_text_field(String statusColor) throws Throwable {

        adCenMeths.searchAppAndVerifyStatus(statusColor);
    }

    @And("searches for the test application, sees it is {string}, and clicks {string}")
    public void search_for_app_and_update(String currentStatus, String actionText) throws Throwable {
        //ENH: Replace above color-based with the text of button/link
        String application = getAppNameFromExcFile("newApps", "Apps", env2);
        adCenMeths.searchAppCheckStatusAndClick(application, currentStatus, actionText);
    }

    @When("^user scroll down, select \"([^\"]*)\" from Application Status and clicks on \"([^\"]*)\"$")
    public void user_scroll_down_select_from_Application_Status_and_clicks_on(String status, String arg2) throws Throwable {
        scroll_Down(locateElementByID("recordActivationIndicator"));
        selectFromDropDownMenu(locateElementByID("recordActivationIndicator"), status);
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("edit-app-btn"))).click();
        handleAlert();
    }

    @Then("user navigates back to data table, search for the newly {string} application and see {string} icon")
    public void user_navigates_back_to_data_table_search_for_the_newly_application_and_see_icon(String expectedStatus, String expectedColor) throws Throwable {
        //TODO: App is currently from Excel, while Cucumber indicates it should be from the Feature file
        String application = getAppNameFromExcFile("newApps", "Apps", env2);
        adCenMeths.verifyAppStatusAfterUpdatingIt(application, expectedStatus, expectedColor);
    }

    @Then("^sees \"([^\"]*)\" page after clicking on \"([^\"]*)\" icon from a \"([^\"]*)\" table row$")
    public void sees_page_after_clicking_on_icon_from_a_table_row(String arg1, String arg2, String arg3) throws Throwable {
        click(locateElementByXPath("//table[@id='user-role-view']/tbody//td[contains(text(), '" + arg3 + "')][1]//following-sibling::td[6]/a"));
        isElementPresent(locateElementByXPath("//div[@class='cicdim-title'][1]"));
    }

    @When("^scrolling down and select \"([^\"]*)\" from \"([^\"]*)\" select dropdown$")
    public void scrolling_down_and_select_from_select_dropdown(String arg1, String arg2) throws Throwable {
        scroll_Down(locateElementByID("recordActivationIndicator"));
        selectFromDropDownMenu(locateElementByID("recordActivationIndicator"), arg1);
    }

    @Then("^see \"([^\"]*)\" after clicking on \"([^\"]*)\" button$")
    public void see_after_clicking_on_button(String arg1, String arg2) throws Throwable {
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//button[normalize-space()='" + arg2 + "']"))).click();
        handleAlert();
        isElementPresent(locateElementByXPath("  //table[@id='user-role-view']/tbody//td[contains(text(), '" + arg1 + "')][1]"));
    }
}
