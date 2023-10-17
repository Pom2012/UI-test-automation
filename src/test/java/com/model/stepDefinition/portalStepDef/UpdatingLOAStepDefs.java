package com.model.stepDefinition.portalStepDef;

import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.locators.hd_ManageUsersLoc.HD_ManageUsers_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;

public class UpdatingLOAStepDefs extends BasePage {
    public List<HashMap<String, String>> datamap;
    HD_ManageUsers_Locators hd_manageUs_locs;
    BasePage_Locators login;

    public UpdatingLOAStepDefs() {
        datamap = DataHelper.data("newUserRegData.xlsx", env);
        hd_manageUs_locs = PageFactory.initElements(driver, HD_ManageUsers_Locators.class);
        login = PageFactory.initElements(driver, BasePage_Locators.class);
    }

    @And("^fillout out the \"([^\"]*)\" field for searching the \"([^\"]*)\" and clicks on \"([^\"]*)\" button$")
    public void fillout_out_the_field_for_searching_the_and_clicks_on_button(String UserID, String row_index, String Search) throws Throwable {
        int index = Integer.parseInt(row_index) - 1;
        System.out.println("index = " + index);
        String value = datamap.get(index).get(UserID);
        System.out.println("value = " + value);
        wait.until(ExpectedConditions.visibilityOf(hd_manageUs_locs.userID_searchBox));
        Model_CommonFunctions.highLightElement(hd_manageUs_locs.userID_searchBox);
        locateElementByID("cms-enterprisesearch-uid").sendKeys(value);
        Model_CommonFunctions.highLightElement(hd_manageUs_locs.searchBtn);
        click(hd_manageUs_locs.searchBtn);
    }

    @And("^selects: \"([^\"]*)\" from \"([^\"]*)\", \"([^\"]*)\" and justified the change$")
    public void selects_from_and_justified_the_change(String loaValue, String loa, String reason) throws Throwable {
        selectFromDropDownMenu(locateElementByID("cms-loa"), loaValue);
        selectFromDropDownMenu(locateElementByID("cms-loa_reason"), reason);
        locateElementByID("loa_just").sendKeys(faker.lorem().paragraph());
    }

    @And("^enters the (\\d+) \"([^\"]*)\"$")
    public void enters_the(String row_index, String ssnValue) throws Throwable {
        int index = Integer.parseInt(row_index) - 1;
        System.out.println("index = " + index);
        String value = datamap.get(index).get(ssnValue);
        System.out.println("value = " + value);
        locateElementByID("cms-userSSN").sendKeys(value);
    }

    @Then("^the submit the LOA changes$")
    public void the_submit_the_LOA_changes() throws Throwable {
        click(locateElementByID("cms-updateLOA"));
        wait(200);
        click(locateElementByID("cms-submit-req"));
    }
}
