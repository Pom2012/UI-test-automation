package com.model.pages.supCntr;

import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.model.utility.Alerts_and_Requests.*;
import static com.model.utility.ExcelFile.writeReqWithStsToExc;
import static com.model.base.Constants.ModelApplications.*;

public class AssignRoleTab extends SupportCenterModule {
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    public static String assignRoleUserIDTexfield = "[id='cmmi-hd'] [placeholder='Enter User ID']";
    public static String assignRoleFNamefield = "//*[@id='cmmi-hd']//*[@placeholder='Users First Name']";
    public static String AppDropDownBoxAsRole = "[id='cmmi-hd'] [placeholder='please select an application']";
    public static String RoleDropDownBoxAsRole = "[id='cmmi-hd'] [placeholder='please select a role']";
    public static String assignRoleAttribute1 = "(//form[@name='assignRoleForm']//input)[5]";
    public static String assignRoleADDButton = "(//form[@name='assignRoleForm']//button)[1]";
    public static String assignRoleAssignBtn = "(//form[@name='assignRoleForm']//button)[3]";
    public static String assignRoleAssignSubmitBtn = "//div[@id='cmmi-hd']//button[@type='submit']";
    public static String assignRoleAssignClearBtn = "//form//button[@type='reset']";
    public static String currBatchNumber;
    SupportCenterModule suppCentTabMeth = new SupportCenterModule();

    public AssignRoleTab() {
        this.RequestAccess_Locators = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
    }

    public static void verifyAutoGeneratingValueInAssignRole(DataTable values) throws Exception {
        cucTabledata = values.asLists(String.class);
        for (List<String> cucTabledatum : cucTabledata) {
            String actualText;
            boolean isFieldPopulated;
            String failedFields = "";
            int retries = 5;
            while (retries > 0) {
//TODO: this seems minimalist and possibly flakey. Revisit - as well as using userData to check expectedValues for the fields rather than just length > 0
                actualText = locateElementByXPath("//form[@name='assignRoleForm']//input[@placeholder='" + cucTabledatum.get(0) + "']").getText();
                isFieldPopulated = (actualText.length() > 0);
                if (isFieldPopulated) {
                    log.info("INFO: " + cucTabledatum.get(0) + ": " + actualText);
                    retries = 0;
                    continue;
                } else if (retries == 0) {
                    log.warn("WARN: No value populated element: " + cucTabledatum.get(0) + ": " + actualText);
                    if (failedFields.isEmpty())
                        failedFields = cucTabledatum.get(0);
                    else failedFields = failedFields + ", " + cucTabledatum.get(0);
                    //TODO: Assert.fail is one is missing?  Would be a good place to SoftAssert if 1 field fails:
                    // Keep going through steps, but fail the TC at the end.
                }
                wait(1000);
                retries--;
            }
            if (!failedFields.isEmpty()) Assert.fail("No value populated for the following field(s): " + failedFields);
        }
    }

    public static void assignAModelRole(String attribute, String assignBtn, DataTable valTabledata) throws Throwable {
        cucTabledata = valTabledata.asLists(String.class);
        String appName = "";
        for (List<String> dataRow : cucTabledata) {
            if (dataRow.get(0).equalsIgnoreCase("User ID"))
                setFieldByLabelAndValue("User ID", getUserByUserNumberFromExcelFile(dataRow.get(1)).get("UserName"));
            else if (dataRow.get(0).equalsIgnoreCase("Application")
                    || dataRow.get(0).equalsIgnoreCase("Application Name"))
                appName = dataRow.get(1);
        }
        waitForCommonPageLoadingElements();
        //TODO: DATA: App and Custom Attribute hardcoded. CA also not in VAL (Cache issue?)  Try/Catch to not fail if missing.
        try {
            if (appName.equalsIgnoreCase(ARGO)) {
                for (int i = 1; i < cucTabledata.size(); i++) {
                    System.out.println("i = " + i);
                    if (isElementPresent("((//form[@role='form'])[1]//select)[" + i + "]", "xpath"))
                        selectFromDropDownMenu(locateElementByXPath("((//form[@role='form'])[1]//select)[" + i + "]"), cucTabledata.get(i).get(1));
                    System.out.println("i = " + cucTabledata.get(i).get(1));
                    wait(200);
                }
                if (attribute.equalsIgnoreCase("with")) {
                    if (isElementPresent("//button[@name='add']", "xpath"))
                        simpleClick(locateElementByXPath("//button[@name='add']"));
                }
            }
        } catch (Exception ignored) {
        }
        //TODO: DATA: Remove data-driven logic from Java
        if (appName.equalsIgnoreCase(ONYD) && cucTabledata.get(1).get(1).equalsIgnoreCase("Old New Year User 4")) {
            type(locateElementByXPath(assignRoleAttribute1), "Role with custom attribute " + Date);
            click(locateElementByXPath(assignRoleADDButton));
        }
        setFieldByLabelAndValue("Justification", cucTabledata.get(1).get(1));
        wait(1000);
        System.out.println(" Clicking on =" + assignBtn + " button");
        locateElementByCSS("button[type='submit'][value=Submit]").click();
        System.out.println(" Clicked on =" + assignBtn + " button");
        wait(3000);
    }

    public static void assignAModelRole(DataTable valTabledata) throws Throwable {
        //ENH: Method above this should hand off to this method; suggest consolidation
        cucTabledata = valTabledata.asLists(String.class);
        String appName = "";
        System.out.println("Before... ");
        for (int i = 2; i <= 4; i++) {
            String disabledAttribute = locateElementByXPath("(//div[@class='form-group ng-scope']//input)[" + i + "]").getAttribute("disabled");
            System.out.println("disabledAttribute = " + disabledAttribute);
            if (disabledAttribute.equalsIgnoreCase("true")) {
                highLightElement(locateElementByXPath("(//div[@class='form-group ng-scope']//input)[" + i + "]"));
            }
        }
        wait(5000);
        for (List<String> dataRow : cucTabledata) {
            if (dataRow.get(0).equalsIgnoreCase("User ID"))
                setFieldByLabelAndValue("User ID", getUserByUserNumberFromExcelFile("3").get("UserName"));
            else if (dataRow.get(0).equalsIgnoreCase("Application")
                    || dataRow.get(0).equalsIgnoreCase("Application Name"));
        }
        wait(5000);
        System.out.println("After... ");
        for (int i = 2; i <= 4; i++) {
            String disabledAttribute = locateElementByXPath("(//div[@class='form-group ng-scope']//input)[" + i + "]").getAttribute("disabled");
            System.out.println("disabledAttribute = " + disabledAttribute);
            highLightElement(locateElementByXPath("(//div[@class='form-group ng-scope']//input)[" + i + "]"));
            Assert.assertSame(null, disabledAttribute);
        }
    }

    public void handleAlertAndSaveReqID(String app, String userID1, String reqID, String dValue) throws IOException {
        if (app.equalsIgnoreCase(ARGO)) {
            reqID = handleAlertAndGetRequest();
            System.out.println("Request ID >>> " + reqID);
        }
        //TODO: DATA: Should it only write to Excel in this case?  Is that for manual review ~ cannot be for another TC to read as that is unreliable
        if (app.equalsIgnoreCase(AMRVT)) {
            reqID = handleAlertGetRequest();
            System.out.println("Request ID >>> " + reqID);
            writeReqWithStsToExc("reqUserVerif.xlsx",
                    "AMRVT",
                    "" + userID1 + "",
                    "" + reqID + "",
                    "APPROVED",
                    "" + AMRVT + "",
                    "" + dValue + "",
                    "" + env2 + "");
        }
        waitForCommonPageLoadingElements();
    }

    public void assignSingleRole(DataTable dataTable) throws Throwable {
        Map<String, String> keyValues = dataTable.asMap();
        String value;
        for (String key : keyValues.keySet()) {
            value = keyValues.get(key);
            //TODO: row#: needs to be changed when User is set by something other than Excel row
            if (key.equals("User ID"))
                value = getUserByUserNumberFromExcelFile(value).get("UserName");
            setFieldByLabelAndMultiValues(key, value, "Add", "");
        }
    }

    public void handleAlertInAssignRole(String app, String dValue) throws Exception {
        //ENH: Temporary patch. Should be rethought with other methods that do similar things
        simpleClick(locateElementByXPath(assignRoleAssignSubmitBtn));
        wait(1000);
        List<String> reqIDs = handleAlertAndReturnRequestIDsAndErrors();
        BasePage.requestID = reqIDs.get(0);
        System.out.println("Request ID: " + BasePage.requestID);
        if (app.equalsIgnoreCase(AMRVT)) {
            //TODO: DATA: Why only record this app? Trace how this is used - Request ID should not be passed by Excel write-read
            //TODO: Consolidate Excel Write methods
            writeReqWithStsToExc("reqUserVerif.xlsx", "AMRVT", "" + userID + "", "" + BasePage.requestID + "", "APPROVED", "" + AMRVT + "", "" + dValue + "");
        }
        wait(200);
    }

    public void verifyErrMessInAssignRoleTab(DataTable errorList) {
        cucTabledata = errorList.asLists(String.class);
        IntStream.range(0, 1).forEach(x -> IntStream.range(x, 1).forEach(y -> {
            try {
                highLightElement(locateElementByXPath("(//*[text() = ' " + cucTabledata.get(x).get(y) + " '])"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
