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

public class UserInacManagStepDef extends BasePage {

    AdminCenterPages admCentMeths = new AdminCenterPages();
    ApplicationsManagement_Locators alocators = PageFactory.initElements(getDriver(), ApplicationsManagement_Locators.class);

    @Then("^\"([^\"]*)\" page and the \"([^\"]*)\" are displayed$")
    public void page_and_the_are_displayed(String userInactivePage, String appName) throws Throwable {
        click(locateElementByXPathText("User Inactivity"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(userInactivePage)));
        highLightElement(locateElementByXPathText(userInactivePage));
        highLightElement(locateElementByXPathText(appName + " "));
        wait(2000);
    }

    @When("^selects \"([^\"]*)\" check box$")
    public void selects_check_box(String arg1) throws Throwable {
        if (!locateElementByID("mat-checkbox-1-input").isSelected()) {
            clickWithJSE(locateElementByID("mat-checkbox-1-input"));
        }
    }

    @When("^enters text values for the below text fields and clicks on \"([^\"]*)\" button:$")
    public void enters_text_values_for_the_below_text_fields_and_clicks_on_button(String conBtn, DataTable arg1) throws Throwable {
        admCentMeths.inputDateForUserInactivity(conBtn, arg1);
    }

    @And("^verified the page does not content any warning email templates$")
    public void verified_the_page_does_not_content_any_warning_email_templates() throws Throwable {
        admCentMeths.userInactivityTemplateSetupVerification();
    }

    @When("^clicks on \"([^\"]*)\" button and \"([^\"]*)\" dialog is popped up$")
    public void clicks_on_button_and_dialog_is_popped_up(String addNewTempBnt, String emailTemplTitle) {
// TODO: TC: Step does not do anything like the description
        textA = addNewTempBnt;
        textB = emailTemplTitle;
    }

    @Then("^enters values for the below fields, selects \"([^\"]*)\" and \"([^\"]*)\" it$")
    public void enters_values_for_the_below_fields_selects_and_it(String wrnSt, String saveBtn, DataTable cucTab) throws Throwable {
        admCentMeths.creatingUsInacWarning(wrnSt, saveBtn, cucTab);
    }

    @Then("^\"([^\"]*)\" is displayed and user clicks on \"([^\"]*)\"$")
    public void is_displayed_and_user_clicks_on(String arg1, String arg2) throws Throwable {
        highLightElement(locateEleByXPathTextNormSpace(arg1));
        clickOnTextWithJSEAndNormSpaceAttr("span", arg2);
    }

}
