package com.model.stepDefinition.sptCntrStepDef;

import com.github.javafaker.Faker;
import com.model.pages.supCntr.AssignRoleTab;
import com.model.pages.supCntr.SupportCenterModule;
import com.model.utility.DataHelper;
import com.model.utility.ExcelFile;
import com.model.utility.Screenshot;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import com.model.locators.supportCenterLoc.HelpDesk_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.model.pages.supCntr.AssignRoleTab.*;
import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.utility.ExcelFile.writeReqWithStsToExc;
import static com.model.base.Constants.ModelApplications.AMRVT;

public class AssignRoleTabStepDefs extends BasePage {
    public static Faker faker = new Faker();
    public static String userID = null;
    public static String value = null;
    public List<HashMap<String, String>> assignRoleDatamap;
    SupportCenterModule suppCentTabMeth = new SupportCenterModule();
    public String utilRequestsFile = "assignsRoles_MultipleAssignments.xlsx";
    public static String currBatchNumber;
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    public static java.util.Date date = new Date();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static String dValue = dateFormat.format(date);
    AssignRoleTab assignRoleTab = new AssignRoleTab();
    HelpDesk_Locators helpDesk_locators;

    public AssignRoleTabStepDefs() {
        assignRoleDatamap = DataHelper.data("assignsRoles.xlsx", env);
        this.helpDesk_locators = PageFactory.initElements(driver, HelpDesk_Locators.class);
        this.RequestAccess_Locators = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
    }

    @When("^\"([^\"]*)\" clicks on \"([^\"]*)\" and see the page$")
    public void clicks_on_and_see_the_page(String role, String tabName) throws Throwable {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#searchusers")));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(tabName)));
    }

    @When("^clicks on \"([^\"]*)\" and see the page$")
    public void clicks_on_and_sees_the_page(String sptCenTab) throws Throwable {
        suppCentTabMeth.clickATabSuppCent(sptCenTab);
    }

    @Then("^enters and selects values for the below fields for a role \"([^\"]*)\" an attribute and clicks on \"([^\"]*)\":$")
    public void enters_and_selects_values_for_the_below_fields_for_a_role_an_attribute_and_clicks_on(String attribute, String assignBtn, DataTable valuesData) throws Throwable {
        assignAModelRole(attribute, assignBtn, valuesData);
    }

    @Then("^enters and selects values for the below fields for a role:$")
    public void enters_and_selects_values_for_the_below_fields_for_a_role(DataTable valuesData) throws Throwable {
        assignAModelRole(valuesData);
    }

    @And("clicks Assign, then receives a message for {string} with the Request ID")
    public void alert_message_for_with_is_displayed(String app) throws Throwable {
        assignRoleTab.handleAlertInAssignRole(app, dValue);
    }

    @Then("clicks {string} with {string} {string}")
    public void clickByTypeAttrAndValue(String eleType, String eleAttr, String value) throws Exception {
        clickElementByTypeAttrAndValue(eleType, eleAttr, value);
    }

    @When("^enter the \"([^\"]*)\" and \"([^\"]*)\" it after clicking on \"([^\"]*)\"$")
    public void click_a_Tab_and_Search_by_Request_ID(String requestID, String searchBtn, String tab) throws Throwable {
        click(locateElementByXPathText(tab));
        type(locateElementByID("hdForm_hdSearch-input_requestId_1"), BasePage.requestID);
        click(locateElementByXPath("//div[@ui-view='batch']//button[@type='submit']"));
    }

    @Then("^a table with the Request ID, \"([^\"]*)\", \"([^\"]*)\" is displayed$")
    public void a_table_with_the_is_displayed(String app, String modelRole) throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText("Application Role Request Information")));
        String[] tableContent = {app, modelRole};
        highLightElement(locateElementByXPathText(BasePage.requestID));
        for (String each : tableContent) {
            highLightElement(locateElementByXPath("//table[@id='hdRequestTable']/tbody/tr[contains(., '" + BasePage.requestID + "')]//td[contains(text(), '" + each + "')]"));
        }
        click(locateElementByXPath("(//table[@id='hdRequestTable']/tbody/tr[contains(., '" + BasePage.requestID + "')]//td//following::input)[1]"));
    }

    @When("^selects check in checkbox and clicks on \"([^\"]*)\" button$")
    public void selects_check_in_checkbox_and_clicks_on_button(String rejectBtn) throws Throwable {
        click(locateElementByXPathText(rejectBtn));
    }

    @Then("^provide justification and \"([^\"]*)\" the alert with the \"([^\"]*)\"$")
    public void provide_justification_and_the_alert_with_the(String submitBtn, String requestID) throws Throwable {
        type(locateElementByID("formly_4_ic-comment_approvalComment_0"), "Rejecting the " + BasePage.requestID);
        simpleClick(locateElementByXPathText(submitBtn));
        handleAlert();
    }

    @Then("^enters the user ID, selects the below data, clicks on \"([^\"]*)\" button and submit the operation$")
    public void enters_the_user_ID_selects_the_below_data_clicks_on_button_and_submit_the_operation(String appBtn, DataTable data) throws Throwable {
        List<List<String>> assignRoleTabData = data.asLists(String.class);
        String appName = assignRoleTabData.get(1).get(0);
        String modelRoleName = assignRoleTabData.get(1).get(1);
        String entryIdentify = assignRoleTabData.get(1).get(2); //
        String entryList = assignRoleTabData.get(1).get(3); //Custom Attribute
        userID = assignRoleTabData.get(1).get(4); //user
        //TODO: DATA: Users should not be hard-coded or by row#
        String[] users ={
                readDataFromExcel(11, "UserName", "testData2", "" + env + ""),//IC_VAL_USER2,IC_DEV_USER6
                readDataFromExcel(10, "UserName", "testData2", "" + env + "")};//IDM_VAL_HD2, XXX_DEV_MP3
        for (int i = 0; i <= 1; i++) {
            waitForCommonPageLoadingElements();
            isElementPresent(getElementByLocatorAndSearchType(assignRoleUserIDTexfield, "css"));
            type(locateElementByCSS(assignRoleUserIDTexfield), users[i]);
            int retries = 20;
            while (retries > 0) {
                wait(1000);
                WebElement ele = waitForElementAllowNull("assignRoleForm_ic-eidm-readonly-input_firstName_2", "id");
                if (ele != null && ele.isEnabled()) break;
                retries--;
            }
            selectFromDropDownMenu(locateElementByCSS(AppDropDownBoxAsRole), appName.trim());
            selectFromDropDownMenu(locateElementByCSS(RoleDropDownBoxAsRole), modelRoleName);
            wait(200);
            if (appName.equalsIgnoreCase(AMRVT)) {
                isElementPresent(getElementByLocatorAndSearchType("input[placeholder='Please enter your selection']", "css"));
                type(locateElementByCSS("input[placeholder='Please enter your selection']"), entryIdentify);
                routingElemHandlingHelper(RequestAccess_Locators.attrList, entryList);
                simpleClick(locateElementByXPath(assignRoleADDButton));
            }
            setFieldByLabelAndValue("Justification", "Justification for assigning a practice role");
            try {
                click(locateElementByXPath(assignRoleAssignBtn));
            } catch (Exception e) {
                System.out.println("DEPRECATED: XPath: assignRoleAssignBtn: " + assignRoleAssignBtn);
                click(locateElementByXPath(assignRoleAssignSubmitBtn));
            }
            wait(200);
            assignRoleTab.handleAlertAndSaveReqID(appName, users[i], BasePage.requestID, dValue);
        }
    }

    //ENH: CODE: This is not set up for anything other than:
    // input as found by Label ; -- and a 1-to-1 on an attribute being present and having a .getText() value
    @Then("see the below {string} fields are {string}:")
    public void see_the_below_fields_are(String eleType, String expectedVals, DataTable items) {
        List<List<String>> itemList = items.asLists(String.class);
        List<String> expectedConditions = Arrays.stream(expectedVals.split(" and ")).collect(Collectors.toList());
        List<String> unmetConditions;
        List<String> errors = new ArrayList<>();
        WebElement ele;
        String labelXPath, eleXPath, actualEleText;
//      String actualdAttrVal;
        //ENH: Set for 1 Cucumber column, but could be List<String, String> for 2, ex. expectedVal = item.get(1)
        for ( List<String> item : itemList ) {
            labelXPath = getXPathfor1andOnly1Element("label", item.get(0), "//form[@name='assignRoleForm']");
//ENH: label = labelItem.get(0);   value = labelItem.get(1);  and/or condition on  condition = labelItem.get(2) etc
// However, the unmetConditions would need to be 1-to-1 (or a .get(0) unique key) to know to .remove because it is met
            unmetConditions = expectedConditions;
            eleXPath = labelXPath + "/ancestor::div[contains(@class, 'form-group')]//" + eleType;
            ele = null;
//          actualdAttrVal = null;
            actualEleText  = null;
            int retries = 5;
            while (retries > 0) {
                try {
                    ele = locateElementByXPath(eleXPath);
                } catch (Exception ignored) {}
                if (ele != null) break;
                wait(1000);
                retries--;
            }
            retries = 3;
            while (retries > 0) {
                if ( expectedConditions.contains("disabled") && !ele.isEnabled() )
                    unmetConditions.remove("disabled");

                if ( expectedConditions.contains("enabled") && ele.isEnabled() )
                    unmetConditions.remove("enabled");

                //ENH: "selected" in this case would be like an option, not "has focus"
                if ( ( expectedConditions.contains("checked") || expectedConditions.contains("selected") )
                        && ele.isSelected() ) {
                    unmetConditions.remove("checked");
                    unmetConditions.remove("selected");
                }

                if ( ( expectedConditions.contains("unchecked")
                    || expectedConditions.contains("unselected")
                    || expectedConditions.contains("deselected")
                    || expectedConditions.contains("not selected") )
                   && !ele.isSelected() ) {
                    unmetConditions.remove("unchecked");
                    unmetConditions.remove("unselected");
                    unmetConditions.remove("deselected");
                    unmetConditions.remove("not selected");
                }
                if (unmetConditions.size() == 0) break;
                actualEleText = null;
                try {
//ENH: May need to parse, like @class with several aspects..ex. negatives like "disabled" mught be the abscence or blank value...
//                actualdAttrVal = ele.getAttribute(expectedAttrVal);
                    //TODO: This probably needs review per Element Type
                    if (eleType.equalsIgnoreCase("input"))
                        actualEleText = getValueUsingJSByXPath(eleXPath);
                    else
                        actualEleText = ele.getText();
                    log.info("ELEMENT: " + ele + " : TEXT: " + actualEleText);
                } catch (Exception ignored) {}
                if (actualEleText == null) {
                    wait(1000);
                    retries--;
                    continue;
                }
                //ENH: Probably needs thought on if null/"" is acceptable...
                if (!actualEleText.equalsIgnoreCase(actualEleText.trim())) {
                    log.warn("ACTUAL TEXT included stray space. using trimmed value instead of:" + actualEleText + ": . Please review the spaces");
                    actualEleText = actualEleText.trim();
                }

                if ( expectedConditions.contains("populated") && !actualEleText.isEmpty() && actualEleText.length() > 0 )
                    unmetConditions.remove("populated");
                else if ( (expectedConditions.contains("unpopulated") || expectedConditions.contains("not populated"))
                        && actualEleText.isEmpty()) {
                    unmetConditions.remove("unpopulated");
                    unmetConditions.remove("not populated");
                } else if (expectedConditions.contains(actualEleText))
                    unmetConditions.remove(actualEleText);
                if (unmetConditions.size() == 0) break;
                wait(1000);
                retries--;
            }
            if (unmetConditions.size() > 0) errors.add("LABEL: " + item.get(0) + " : Unmet conditions: " + unmetConditions);
        }
        if (!errors.isEmpty()) Assert.fail("FAIL: " + errors.size() + " unexpected conditions:\n" + errors);
    }

    @Then("^a data values for the user is automatically displayed for the below text fields:$")
    public void a_data_value_for_the_user_is_automatically_displayed_for_the_below_text_fields(DataTable values) throws Throwable {
        verifyAutoGeneratingValueInAssignRole(values);
    }

    //--------------------------------- Utility for Multiple Assign Role ----------------------------------------------------

    @Then("^switch to Angular1$")
    public void switch_to_Angular1() throws Throwable {
        waitForCommonPageLoadingElements();
        suppCentTabMeth.clickATabSuppCent("Email Sender");
        click(locateEleByXPathTextNormSpace("angularjs code"));
        waitForCommonPageLoadingElements();
    }
    //When switch to Angular1

    @And("^from Tab \"([^\"]*)\" in \"([^\"]*)\": for each row, Assign the Role, and then if \"([^\"]*)\" is Y: reject it by Request ID$")
    public void withFilenameAndTab_iterate_Assign_Role_Excel_and_Submit_form(String tabName, String fileName, String isTestData) throws Throwable {
        boolean isScenarioPASS = iterateExcelForAssignRole(fileName, env + "_" + tabName, isTestData);
        if (!isScenarioPASS) Assert.fail("Test FAILED: " + fileName + " " + env + "_" + tabName);
    }

    @And("^from Tab \"([^\"]*)\": for each row, Assign the Role, and then if \"([^\"]*)\" is Y: reject it by Request ID$")
    public void withParallelTabsEnumerated_iterate_Assign_Role_Excel_and_Submit_form(String tabName, String isTestData) throws Throwable {
        String utilParallelRequestsFile = BasePage.prop.getProperty("utilParallelRequestsFile");
        boolean isScenarioPASS = iterateExcelForAssignRole(utilParallelRequestsFile, env + "_" + tabName, isTestData);
        if (!isScenarioPASS) Assert.fail("Test FAILED: " + utilParallelRequestsFile + " " + env + "_" + tabName);
    }

    @Then("^for each row, Assign the Role, and then if \"([^\"]*)\" is Y: reject it by Request ID$")
    public void iterate_Assign_Role_Excel_and_Submit_form(String isTestData) throws Throwable {
        boolean isScenarioPASS = iterateExcelForAssignRole(utilRequestsFile, env2, isTestData);
        if (!isScenarioPASS) Assert.fail("Test FAILED: " + utilRequestsFile + " " + env2);
    }

    private boolean iterateExcelForAssignRole(String requestFile, String sheetName, String isTestData) throws Throwable {
        if (currBatchNumber == null) currBatchNumber = "" + System.currentTimeMillis();
        List<HashMap<String, String>> inputRows = DataHelper.data(requestFile, sheetName);
        String utilRequestsResultsFile = "Requests/assignsRoles_MultipleAssignments.xlsx";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        boolean isScenarioPASS = true;
        for (HashMap<String, String> rowData : inputRows) {
            BasePage.log.info("START: Assign Role: " + rowData);
            boolean isUIUpdated = false;
            try {
                suppCentTabMeth.clickATabSuppCent("Assign Role");
                isUIUpdated = true;
            } catch (Exception ignored) {
            }
            if (!isUIUpdated) {
                recoverBrowserAfterError();
                suppCentTabMeth.clickATabSuppCent("Assign Role");
                try {
                    click(locateElementByXPath(assignRoleAssignClearBtn));
                } catch (Exception ignored) {
                }
            }
            String userID = rowData.get("UserID").trim();
            String appValue = rowData.get("ApplicationName").trim();
            String role = rowData.get("Role").trim();
            //ENH: WORKAROUND: AWS SBTEST: This much waiting should not be necessary
            type(locateElementByCSS(assignRoleUserIDTexfield), userID);
            //ENH: could test an expected value; for now, just wait until the get user details has finished and populated this field.
            //  Otherwise, the "Assign" submit of form can fail to register properly.
            wait(2000);
            //ENH: Consolidate the error-to-Excel into a method
            //formly_20_readonly_firstName_1
            if (getValueUsingJSByXPath(assignRoleFNamefield).equals("")) {
//                String[] valuesToWrite = {currBatchNumber, userID, "", "ERROR", appValue, role, dateFormat.format(new Date()), "User ID Never populated fields: " + rowData};
//                ExcelFile.writeToExcelByFileNameSheetAndHashMap(utilRequestsResultsFile, sheetName, rowData, valuesToWrite);
//                Screenshot.captureScreen(driver, "FAILED", testName);
                try {
                    click(locateElementByXPath(assignRoleAssignClearBtn));
                } catch (Exception ignored) {
                }
                isScenarioPASS = false;
                continue;
            }
            wait(200);
            selectFromDropDownMenu(locateElementByCSS(AppDropDownBoxAsRole), appValue);
            try {
                selectFromDropDownMenu(locateElementByCSS(RoleDropDownBoxAsRole), role);
                log.info("DONE: ROLE: " + role);
            } catch (Exception e) {
                log.error("\nERROR: " + e.getMessage() + "\nROW DATA: " + rowData);
                e.printStackTrace();
                log.error("Test FAILED: " + currBatchNumber + ": Setting ROLE did not complete successfully.\nROW DATA: " + rowData);
//                String[] valuesToWrite = {currBatchNumber, userID, "", "ERROR", appValue, role, dateFormat.format(new Date()), "ROLE FAILED: " + rowData};
//                ExcelFile.writeToExcelByFileNameSheetAndHashMap(utilRequestsResultsFile, sheetName, rowData, valuesToWrite);
//                Screenshot.captureScreen(driver, "FAILED", testName);
                try {
                    click(locateElementByXPath(assignRoleAssignClearBtn));
                } catch (Exception ignored) {
                }
                isScenarioPASS = false;
                continue;
            }
            boolean isCustomAttributeSettingSuccessfullyFinished = false;
            for (int j = 1; j < 6; j++) {
                String label = "";
                try {
                    label = rowData.get("Custom Attr Label " + j);
                } catch (Exception ignored) {
                }
                if (label == null || label.equals("") || label.equals("null")) {
                    isCustomAttributeSettingSuccessfullyFinished = true;
                    if (j == 1) {
                        log.info("INFO: Custom Attribute: NONE for this Row.");
                    } else {
                        log.info("INFO: Custom Attribute: Row's Col Value is empty as of: Custom Attr Label " + j);
                    }
                    break;
                }
                String value = rowData.get("Custom Attr Value " + j).trim();
                try {
//                    setCustomAttributeByLabelAndValue(label, value);
                    setCustomAttributeByLabelAndValueAn2(label, value);
                    log.info("DONE: Custom Attribute " + j);
                } catch (Exception e) {
                    log.error("\nERROR: " + e.getMessage() + "\nROW DATA: " + rowData);
                    e.printStackTrace();
                    log.error("Test FAILED: " + currBatchNumber + ": Setting Custom Attributes did not complete successfully.\nROW DATA: " + rowData);
                    String[] valuesToWrite = {currBatchNumber, userID, "", "ERROR", appValue, role, dateFormat.format(new Date()), "CUSTOM ATTRIBUTE FAILED: " + label + " : " + value};
                    ExcelFile.writeToExcelByFileNameSheetAndHashMap(utilRequestsResultsFile, sheetName, rowData, valuesToWrite);
                    Screenshot.captureScreen(driver, "FAILED", testName);
                    try {
                        click(locateElementByXPath(assignRoleAssignClearBtn));
                    } catch (Exception ignored) {
                    }
                    isScenarioPASS = false;
                    break;
                }
            }
            if (!isCustomAttributeSettingSuccessfullyFinished) continue;
            //ENH: UI Test Mode - rows that get this far are recorded to Excel as expected to succeed
            if (isTestData.equalsIgnoreCase("UI")) {
                String[] valuesToWrite = {currBatchNumber, userID, "", "OK", appValue, role, dateFormat.format(new Date()), "UI Test Only: " + rowData};
                ExcelFile.writeToExcelByFileNameSheetAndHashMap(utilRequestsResultsFile, sheetName, rowData, valuesToWrite);
                try {
                    click(locateElementByXPath(assignRoleAssignClearBtn));
                } catch (Exception ignored) {
                }
                continue;
            }
            setFieldByLabelAndValue("Justification" , rowData.get("Justification").trim());
            click(locateElementByXPath(assignRoleAssignSubmitBtn));
            handleAlert();
            waitForCommonPageLoadingElements();
            click(locateElementByXPath(assignRoleAssignClearBtn));
        }
        //DOC: Do not fail the batch as a Test if it is not being run as such
        return (isTestData.equalsIgnoreCase("N") || isScenarioPASS);
    }

    @Then("^logout current user$")
    public void logoutCurrentUser(){
        logoutCurrentUserWithRetry();
    }

    @Then("views the below errors:")
    public void views_the_below_errors(DataTable errorList) throws Throwable {
        verifyErrorMessages(errorList);
    }
}
