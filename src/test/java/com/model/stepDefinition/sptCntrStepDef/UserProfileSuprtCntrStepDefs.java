package com.model.stepDefinition.sptCntrStepDef;

import com.model.pages.supCntr.SupportCenterModule;
import com.model.base.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.model.utility.DataHelper.readDataFromExcel;

public class UserProfileSuprtCntrStepDefs extends BasePage {
    SupportCenterModule sprtCntrMeth = new SupportCenterModule();

    @When("enters the username of row {string}, selects {string} and {string}")
    public void enters_selects_and(String search_user_row_id, String application, String version) throws Throwable {
        String userID = readDataFromExcel(Integer.parseInt(search_user_row_id), "UserName", "testData2", env);
        type(locateElementByID("userProfileForm_ic-input_eidmId_0"), userID);
        selectFromDropDownMenu(locateElementByID("userProfileForm_ic-select_appShortName_1"), getModelAppName(application));
        selectFromDropDownMenu(locateElementByID("userProfileForm_ic-select_version_2"), version);
    }

    @Then("^user see the user profile json body$")
    public void user_see_the_user_profile_json_body() throws Throwable {
        sprtCntrMeth.userProfileHandling();
    }
}
