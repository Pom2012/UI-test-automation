package com.model.stepDefinition.LMStepDef;

import com.model.pages.admConsole.AdmAppRoleCustAttr;
import com.model.pages.listMngmnt.ListManagementPages;
import com.model.base.BasePage;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.model.pages.admConsole.AdmAppRoleCustAttr.nestedListManipulation;
import static com.model.pages.admConsole.AdmAppRoleCustAttr.simpleListManipulation;
import static com.model.pages.admConsole.AdminCenterPages.updateCustomAttribute;

public class ListsManagementStepDes extends BasePage {
    Date date1 = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
    String time = dateFormat.format(date1);
    ListManagementPages listManegMeths = new ListManagementPages();
    ApplicationsManagement_Locators alocators = PageFactory.initElements(driver, ApplicationsManagement_Locators.class);
    AdmAppRoleCustAttr adminRoleCustAttr = new AdmAppRoleCustAttr();
//    private final String listSearchBox = "//input[@title='Search within List Management of Application Custom Attributes']";

    @Then("^scrolls into view \"([^\"]*)\" and selects \"([^\"]*)\" list and submits it$")
    public void scrolls_into_view_and_selects_list_and_submits_it(String expectedPortletName, String listSelectOption) throws Throwable {
        listManegMeths.verifyAndSelectListPage(expectedPortletName, listSelectOption);
    }

    @Then("see {string} for {string} page")
    public void see_for_page(String expectedListPageTitle, String listType) throws Exception {
        listManegMeths.addApplRoleCustAttrList(expectedListPageTitle, listType);
    }

    @Then("^performs some below manipulations and sees the errors$")
    public void performs_some_below_manipulations_and_sees_the_errors(DataTable opts) throws Throwable {
        listManegMeths.errorsInNestedListVerification(opts);
    }

    @When("^provides a \"([^\"]*)\" list name, \"([^\"]*)\" and adds \"([^\"]*)\" value for List entries text fields$")
    public void provides_a_list_name_and_adds_value_for_List_entries_text_fields(String simlist, String desc, int num) throws Throwable {
        listManegMeths.creatingSimpleList(simlist, desc, num, time);
    }

    @And("^clicks on Add Attribute list button$")
    public void clicks_on_Add_Attribute_list_button() throws Throwable {
        simpleClick(locateElementByXPath("//button[normalize-space()='Add Attribute List']"));
    }

    @Then("^users is on \"([^\"]*)\" pages$")
    public void users_is_on_pages(String arg1) throws Throwable {
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByXPath("//div[@class='cicdim-title']"));
    }

    @Then("^verifies the list is stored$")
    public void verifies_the_list_is_stored() throws Throwable {
        listManegMeths.verifyListIsStored();
    }

    @Then("^verifies the \"([^\"]*)\" list is stored$")
    public void verifies_the_list_is_stored(String list) throws Throwable {
        listManegMeths.listIsStoredInEnv(list);
    }

    @And("^scrolls into view \"([^\"]*)\", searches the \"([^\"]*)\", clicks on update icon$")
    public void scrolls_into_view_searches_the_clicks_on_update_icon(String arg1, String listType) throws Throwable {
        if(listType.equalsIgnoreCase("Simple")||listType.equalsIgnoreCase("Nested"))listManegMeths.updateAList(arg1,listType);
        else listManegMeths.updateUtilList(arg1,listType);
    }

    @Then("^\"([^\"]*)\" page, list name and entries are displayed$")
    public void page_list_name_and_entries_are_displayed(String arg1) {
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByID("updatePredefAttrList"));
        highLightElement(locateElementByCSS("#optionGroupName"));
    }

    @Then("^clicks on update icon and sequentially performs and submits the manipulation for \"([^\"]*)\" list:$")
    public void clicks_on_update_icon_and_sequentially_performs_and_submits_the_manipulation_for_list(String listType, DataTable localData) throws Throwable {
        if(listType.equalsIgnoreCase("simple"))simpleListManipulation(localData);
        if(listType.equalsIgnoreCase("nested"))nestedListManipulation(localData);
    }

    @Then("^provides \"([^\"]*)\" name, selects a list from \"([^\"]*)\" and adds \"([^\"]*)\" New Entries$")
    public void provides_name_selects_a_list_from_and_adds_New_Entries(String listType, String topLvl, int num) throws Throwable {
        listManegMeths.createListFromCsv(listType, topLvl, num, time);
    }

    @Then("^searches \"([^\"]*)\" application in search bar, clicks on roles icon and see \"([^\"]*)\" page$")
    public void searches_application_in_search_bar_clicks_on_roles_icon_and_see_page(String appName, String pageName) throws Throwable {
        adminRoleCustAttr.appsRolesPageselecting(alocators, appName, pageName);
    }

    @When("^clicks on \"([^\"]*)\" icon for \"([^\"]*)\" user$")
    public void clicks_on_icon_for_user(String caIcon, String modelRole) throws Throwable {
        waitForCommonPageLoadingElements();
        listManegMeths.selectCustAttr4Role(caIcon, modelRole);
    }

    @When("selects {string}, sees {string}, and creates default custom attributes of type:")
    public void selects_sees_and_selects_the_below_options(String btnOrLinkTxt, String title, DataTable dataValues) throws Throwable {
        adminRoleCustAttr.selectsAddNewAttribute(btnOrLinkTxt, title, dataValues);
    }

    @When("^clicks on view icon for \"([^\"]*)\" \"([^\"]*)\" from \"([^\"]*)\"$")
    public void clicks_on_view_icon_for_from(String attrType, String arg2, String arg3) throws Throwable {
        listManegMeths.viewIcon(attrType, arg2, arg3);
    }

    @Then("^a windows of an attribute with the list values is popped up$")
    public void a_windows_of_an_attribute_with_the_list_values_is_popped_up() throws Throwable {
        listManegMeths.viewIconWinDialoge();
    }

    @When("^clicks on the arrow \"([^\"]*)\" icon for \"([^\"]*)\" custom attribute of the table$")
    public void clicks_on_the_arrow_icon_for_custom_attribute_of_the_table(String arrow, String routingCustAttr) throws Throwable {
        adminRoleCustAttr.moveArrowUpDownInCusAttr(arrow, routingCustAttr);
    }

    @Then("^\"([^\"]*)\" custom attribute changes its position in the table and moves \"([^\"]*)\"$")
    public void custom_attribute_changes_its_position_in_the_table_and_moves(String routingCustAttr, String arrow) {
        adminRoleCustAttr.verifyTheMovedArrowUpDownInCusAttr(routingCustAttr, arrow);
    }

    @Then("^clicks on update icon from \"([^\"]*)\" column and update the custom attributes$")
    public void clicks_on_update_icon_from_column_and_update_the_custom_attributes(String updateCol) throws Throwable {
        updateCustomAttribute(updateCol);
    }

    @Then("^clicks on delete icon from \"([^\"]*)\" column and deletes the custom attributes$")
    public void clicks_on_delete_icon_from_column_and_deletes_the_custom_attributes(String colName) throws Throwable {
//        deleteCustomAttribute(colName);
    }

    @And("views {string} select dropdown and inactive {string} button in the {string}")
    public void views_select_dropdown_and_inactive_button_in_the(String string, String string2, String string3) throws Exception {
        listManegMeths.verifyListOptsFromSelectDropDown(string, string2, string3);
    }

    @Then("{string} page with the below features is displayed:")
    public void page_with_the_below_features_is_displayed(String string, DataTable dataTable) throws Exception {
        listManegMeths.verifyListManagementPage(string, dataTable);
    }

    @When("views the below options when clicks on {string}:")
    public void views_the_below_options_when_clicks_on(String string, DataTable dataTable) throws Exception {
        listManegMeths.verifyAddNewListDropDownOptions(string,dataTable);
      }

    @When("the {string} button activates when the user selects the below options:")
    public void the_button_activates_when_the_user_selects_the_below_options(String string, DataTable dataTable) throws Exception {
        listManegMeths.verifySubmitBtnActive(string, dataTable);
    }

    @When("selects {string} from dropdown and clicks on {string} button")
    public void selects_from_dropdown_and_clicks_on_button(String string, String string2) throws Exception {
        waitForCommonPageLoadingElements();
        wait(5000);
        selectFromDropDownMenu(locateElementByCSS("[id='optionId']"), string);
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//*[@id='selected-option']"))).click();
    }

    @Then("{string} page with the below elements is displayed:")
    public void page_with_the_below_elements_is_displayed(String string, DataTable dataTable) {
        listManegMeths.verifyAddAppRoleCustomAttributeListPage(string, dataTable);
    }

    @Then("{string} is displayed")
    public void is_displayed(String string) {
        isElementPresent(locateEleByXPathTextNormSpace(string));
    }

    @Then("the below text is displayed:")
    public void the_below_text_is_displayed(DataTable dataTable) {
        listManegMeths.errorsAreDisplayed(dataTable);
    }

    @Then("selects {string} and views unchecked {string}")
    public void selects_and_views_unchecked(String btnOrLinkTxt, String string2) throws Exception {
        adminRoleCustAttr.selectsAddNewAttributeAndSeeCheckBoxes(btnOrLinkTxt, string2);
    }

    @When("selects {string} checkbox")
    public void selects_checkbox(String string) throws Exception {
        adminRoleCustAttr.clickAndVerifDynCusAttr(string);
    }

    @Then("the below select dropdowns is appeared:")
    public void the_below_select_dropdowns_is_appeared(DataTable dataTable) {
        adminRoleCustAttr.verifyDynamicCustAttrFeatures(dataTable);
    }
    @When("iteratively searches and updates values for the below from {string}:")
    public void iteratively_searches_and_updates_values_for_the_below_from(String string, DataTable dataTable) throws Exception {
        listManegMeths.utilUpdatingOfList(string, dataTable);
    }

//    @When("selects {string} from {string} and clicks on the next arrow icon")
//    public void selects_from_and_clicks_on_the_next_arrow_icon(String str, String str2) throws Exception {
//        selectFromDropDownMenu(locateElementByID("entryMethod"), str2);
//        wait.until(ExpectedConditions.elementToBeClickable(locateElementByID("selectEntryMethod"))).click();
//        locateElementByID("csvText").isDisplayed();
//        wait(500);
//        try {
//            actions.doubleClick(locateElementByXPath("//tbody/tr[6]/td[1]")).perform();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            System.out.println("finnaly = " );
//        }
//        wait(100000);
//    }
}
