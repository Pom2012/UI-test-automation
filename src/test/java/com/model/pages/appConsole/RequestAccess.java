package com.model.pages.appConsole;

import com.model.utility.DataHelper;
import com.model.utility.ExcelFile;
import com.model.utility.randomData;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.utility.ExcelFile.writeReqWithStsToExc;
import static com.model.utility.MFA.getRequestIDFromEmail;
import static com.model.base.Constants.ApplicationConsoleTabs.*;
import static com.model.base.Constants.ModelApplications.*;
import static com.model.base.Constants.RequestStatus.appr;
import static com.model.base.Constants.RequestStatus.pend;
import static com.model.base.Constants.RequestStatusTabs.apprTab;
import static com.model.base.Constants.RequestStatusTabs.pendTab;
import static com.model.base.Constants.fields.AN;
import static java.lang.Integer.parseInt;

public class RequestAccess extends ApplicationConsolePages {

    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    Portal_AppConsole_RequestAccess_Locators appConsoleRequest = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public randomData randomData = new randomData(null);
    String randomValue = null;
    private List<List<String>> dataInfo = null;
    EmailNotifications emailNotifications = new EmailNotifications();
    private static final String dValue = dateFormat.format(date);
    private final String justTextField = "//textarea[@placeholder='Enter Justification']";
    private List<List<String>> list = null;
    String outPutFile;

    public RequestAccess() {
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
        String winDialBox = this.winDialBox;
    }

    public static String getReqType(String str) {
        return str.contains("You have successfully submitted") ? "You have successfully submitted" : "Unsuccessful Request(s):";
    }

    public void openRequestNewAccessPage() throws Exception {
        click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        click(RequestAccess_Locators.requestNewAccessLink);
    }

    public void submitANewRequest(String appName, String role, String contrType, String attr, String attrToSel) throws Exception {
        click(RequestAccess_Locators.selectAppDropDown);
        selectDropDownOption_ByVisibleText(appName, RequestAccess_Locators.selectAppDropDown);
        selectDropDownOption_ByVisibleText(role, RequestAccess_Locators.selectRoleDropDown);
        if (contrType.equalsIgnoreCase("text") && attr.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                attrValue = faker.name().firstName();
                typeonForm(RequestAccess_Locators.attrAMRtextBox, attrValue);
                click(RequestAccess_Locators.addButton);
            }
        }
        if (contrType.equalsIgnoreCase("restText")) {
            typeonForm(RequestAccess_Locators.resTextAttr, attr);
            String innerHTML = RequestAccess_Locators.attrList.getAttribute("innerHTML");
            List<WebElement> liList = RequestAccess_Locators.attrList.findElements(By.tagName("li"));
            wait(2000);
            for (WebElement el : liList) {
                if (el.getText().contains(attrToSel)) {
                    System.out.println("Selected:" + el.getText());
                    click(el);
                    break;
                }
            }
        }
        if (contrType.equalsIgnoreCase("text") && !attr.isEmpty()) {
            attrValue = attr;
            typeonForm(RequestAccess_Locators.attrAMRtextBox, attrValue);
            String innerHTML = RequestAccess_Locators.attrList.getAttribute("innerHTML");
            List<WebElement> liList = RequestAccess_Locators.attrList.findElements(By.tagName("li"));
            wait(2000);
            for (WebElement el : liList) {
                if (el.getText().contains(attrToSel)) {
                    System.out.println("Selected:" + el.getText());
                    click(el);
                    break;
                }
            }
            click(RequestAccess_Locators.addButton);
        }
        justification = randomData.createStr(50);
        typeonForm("This justification field is required", justification);
        System.out.println("Justification string length: " + justification.length());
        click(RequestAccess_Locators.confirmButton);
    }

    public void submitAccessRequest(String appName, String role, int justifLength) throws Exception {
        click(RequestAccess_Locators.selectAppDropDown);
        selectDropDownOption_ByVisibleText(appName, RequestAccess_Locators.selectAppDropDown);
        selectDropDownOption_ByVisibleText(role, RequestAccess_Locators.selectRoleDropDown);
        attrValue = faker.name().firstName() + env + " " + Date;
        typeonForm(locateElementByXPath("(//input[contains(@id, 'newRequestForm')])[1]"), attrValue);
        if (isElementPresent("(//input[contains(@id, 'newRequestForm')])[2]", "xpath")) {
            typeonForm(locateElementByXPath("(//input[contains(@id, 'newRequestForm')])[2]"), "attrValue");
        }
        click(RequestAccess_Locators.addButton);
        justification = randomData.createStr(justifLength);
        typeonForm("This justification field is required", justification);
        System.out.println("Justification string length: " + justification.length());
        click(RequestAccess_Locators.confirmButton);
    }

    public void searchrequestById(String expStatus, String requestID) throws Exception {
        //TODO: EXCEL: Reading last row from Excel is currently unreliable as not linked to current TC
        if (BasePage.requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        //ENH: The tab text has spaces?
        if (expStatus.equalsIgnoreCase(pend)) {
            click(locateElementByXPath("//*[text()=' " + pendTab + " ']"));
        } else if (expStatus.equalsIgnoreCase(appr)) {
            click(locateElementByXPath("//*[text()=' " + apprTab + " ']"));
        } else {
            click(locateElementByXPath("//*[text()='" + expStatus + "']"));
        }
        waitForCommonPageLoadingElements();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, -2000);");
        type(locateElementByID(raSrchBxElID), requestID);
        click(locateElementByID(raSrchBtnElID));
        waitForCommonPageLoadingElements();
        isElementPresent(locateElementByXPathContainsText(BasePage.requestID));
        String actualStatus = driver.findElement(By.xpath("//span[contains(text(),'" + expStatus + "')]")).getText();
        highLightElement(locateElementByXPathContainsText(expStatus));
        System.out.println("The request " + BasePage.requestID + " is " + actualStatus);
    }

    public void requestAccessToICApplication(DataTable cucOptTable) throws Throwable {
        cucTabledata = cucOptTable.asLists(String.class);
        String value1 = null, value2 = null, modelApp, model1,
                application = cucTabledata.get(0).get(1),
                appRole = cucTabledata.get(1).get(1),
                customReference = cucTabledata.get(2).get(1);
        modelApp = getModelAppName(application);
        selectDropDownOption_ByVisibleText(modelApp, RequestAccess_Locators.selectAppDropDown);
        selectDropDownOption_ByVisibleText(appRole, RequestAccess_Locators.selectRoleDropDown);
        wait(500);
        switch (application) {
            case AHC:
                typeonForm(RequestAccess_Locators.resTextAttr, customReference);
                routingElemHandlingHelper(RequestAccess_Locators.attrList, envCustAttr("S012", "S001", "S001"));
                break;
            case OCM:
            case OCMPlus:
            case AMRVT:
            case PCF_Dummy:
                typeonForm(RequestAccess_Locators.resTextAttr, cucTabledata.get(2).get(1));
                routingElemHandlingHelper(RequestAccess_Locators.attrList, cucTabledata.get(3).get(1));
                break;
            case CDX:
                /* selecting Comprehensive Primary Care Plus Model ID */
                selectDropDownOption_ByVisibleText(cucTabledata.get(2).get(1), RequestAccess_Locators.selectModelIDDropDown);
                typeonForm(RequestAccess_Locators.resTextAttr, cucTabledata.get(3).get(1));
                routingElemHandlingHelper(RequestAccess_Locators.attrList, envCustAttr("1234567890 - Testing Name 1", "1234567890 - Testing Name 1", "1234567893 - NPI Participant 4"));
                click(RequestAccess_Locators.addButton);
                /* selecting Emergency Triage, Treat and Transport Model ID */
                selectDropDownOption_ByVisibleText(cucTabledata.get(5).get(1), RequestAccess_Locators.selectModelIDDropDown);
                typeonForm(RequestAccess_Locators.resTextAttr, cucTabledata.get(6).get(1));
                routingElemHandlingHelper(RequestAccess_Locators.attrList, cucTabledata.get(7).get(1));
                click(RequestAccess_Locators.addButton);
                break;
            case IPC:
                model1 = cucTabledata.get(2).get(1);
                selectDropDownOption_ByVisibleText(model1, locateElementByXPath("(//form[@name='newRequestForm']//select)[3]"));
                //TODO: DATA: should remove hard-coded data by env below, as well as fixed positions in the datatable from TC
                if (env.equalsIgnoreCase("DEV")) {
                    value1 = cucTabledata.get(3).get(1);
                    value2 = cucTabledata.get(4).get(1);
                }
                if (env.equalsIgnoreCase("VAL")) {
                    if (model1.equalsIgnoreCase("CPC+")) {
                        value1 = "CPC";
                        value2 = "T1AR0058";
                    }
                    if (model1.equalsIgnoreCase("CJR")) {
                        value1 = "CJR";
                        value2 = "010055";
                    }
                }
                typeonForm(RequestAccess_Locators.resTextAttr, value1);
                routingElemHandlingHelper(RequestAccess_Locators.attrList, value2);
                typeonForm(locateElementByXPath("(//form[@name='newRequestForm']//input)[2]"), cucTabledata.get(5).get(1));
                break;
            case HDR:
                break;
            case ARGO:
                selectDropDownOption_ByVisibleText(cucTabledata.get(2).get(1), locateElementByXPath("(//*[@name='newRequestForm']//select)[3]"));
                break;
            case ONYD:
                if (appRole.equalsIgnoreCase("Old New Year User 4")) {
                    locateElementByXPath("//input[@placeholder='Please enter a value']").sendKeys(cucTabledata.get(2).get(1));
                } else {
                    selectDropDownOption_ByVisibleText(cucTabledata.get(2).get(1), RequestAccess_Locators.selectModelIDDropDown);
                    typeonForm(RequestAccess_Locators.resTextAttr, cucTabledata.get(3).get(1));
                    routingElemHandlingHelper(RequestAccess_Locators.attrList, envCustAttr("1234567890 - Testing Name 1", "ET3-0012 - Testing Org Legal Name12", "1234567893 - NPI Participant 4"));
                    click(RequestAccess_Locators.addButton);
                }
                break;
            default:
                System.out.println("No such value....");

        }
    }

    public void ifUserHasAccessToICApp(String reqStatus, DataTable value) throws Exception {
        cucTabledata = value.asLists(String.class);
        for (int i = 0; i < cucTabledata.size(); i++) {
            waitForCommonPageLoadingElements();
            wait.until(ExpectedConditions.elementToBeClickable(Portal_AppConsole_RequestAccess_Locators.requestAccessTab)).click();
            waitForCommonPageLoadingElements();
            click(RequestAccess_Locators.pendingTab);
            waitForCommonPageLoadingElements();
            if (isElementPresentJSEByXPath("//input[@id='caSearchField']")) {
                type(locateElementByID(caSrchBxElID), cucTabledata.get(i).get(1));
                click(locateElementByID(caSrchBtnElID));
            } else {
                type(locateElementByID(raSrchBxElID), cucTabledata.get(i).get(1));
                click(locateElementByID(raSrchBtnElID));
            }
            click(RequestAccess_Locators.searchBtn);
            if (getRequestsNumberFromResult(reqAccTab, pendTab) != 0) {
                highLightElement(locateElementByXPath("//*[contains(text(),'" + cucTabledata.get(i).get(1) + "')]/ancestor::div[@class='container-fluid table-div-padding even']//*[contains(text(),'" + reqStatus + "')]"));
                click(locateElementByXPath("//*[contains(text(),'" + reqStatus + "')]/ancestor::div[@class='container-fluid table-div-padding even']//*[contains(text(),'Cancel Request')]"));
                typeonForm(RequestAccess_Locators.cancelReqJustification, "Rejected by IC test team");
                click(RequestAccess_Locators.okBtn);
                handleAlert();
            } else {
                System.out.println("Such role does not exist...");
            }
        }
    }

    public void requestConfirmNumbers(String portlTab, DataTable sTabs) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("skipToContent")));
        cucTabledata = sTabs.asLists(String.class);
        String acReqStTab;
        List<Integer> list = new ArrayList<>();
        clickWithJSE(locateElementByCSS("li[alt='" + portlTab + "'] a[class='ng-binding']"));
        try {
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//h1[@class='acHeader']/b")));
        } catch (StaleElementReferenceException e) {
            e.getStackTrace();
        }
        for (int i = 0; i < cucTabledata.size(); i++) {
            acReqStTab = cucTabledata.get(i).get(0);
            System.out.println("Actual Req Status Tab name = " + acReqStTab);
            click(locateElementByXPath("//uib-tab-heading[@class='ng-scope'][contains(text(),'" + acReqStTab + "')]"));
            if (portlTab.equalsIgnoreCase(reqAccTab))
                wait.until(ExpectedConditions.visibilityOf(locateElementByID("reqtable-frame-ra")));
            if (portlTab.equalsIgnoreCase(confAccTab))
                wait.until(ExpectedConditions.visibilityOf(locateElementByID("reqtable-frame-ca")));
            highLightElement(locateElementByXPath("//uib-tab-heading[@class='ng-scope'][contains(text(),'" + acReqStTab + "')]/span[1]"));
            list.add(getRequestsNumberFromResult(portlTab, acReqStTab));
        }
        int totNums = list.get(1) + list.get(2) + list.get(3);
        System.out.println("Total number of Rejected, Approved and Pending requests is..." + totNums);
        int actNum = list.get(0);
        System.out.println("The total number of requests, reflected in All tab is......" + actNum);
        Assert.assertEquals(totNums, actNum);
    }

    public void requestAccessUIAttributesVer(DataTable cucTabTable) throws Exception {
        cucTabledata = cucTabTable.asLists(String.class);
        String featureValue;
        String actualValue;
        for (int i = 0; i < cucTabledata.size(); i++) {
            waitForCommonPageLoadingElements();
            System.out.println("Size " + cucTabledata.size());
            featureValue = cucTabledata.get(i).get(1);
            System.out.println("Expected value: " + featureValue);
            switch (featureValue) {
                case "Request New Access":
                    actualValue = locateElementByXPath("//a[@id='request-link']").getText();
                    System.out.println("actual value = " + actualValue);
                    verifyActualText(actualValue, featureValue);
                    highLightElement(locateElementByID("request-link"));
                    Assert.assertTrue(locateElementByID("request-link").isDisplayed());
                    break;
                case "Email Notification Settings":
                    emailNotifications.emailSettingsVerification(featureValue);
                    break;
                case "Search":
                    locateElementByID(raSrchBxElID).isDisplayed();
                    highLightElement(locateElementByID(raSrchBxElID));
                    Assert.assertTrue(locateElementByID(raSrchBxElID).isDisplayed());
                    break;
            }
        }
    }

    public void requestStaticTextVer(String expectedText) throws Exception {
        isElementPresent(locateEleByXPathTextNormSpace(expectedText));
        highLightElement(locateEleByXPathTextNormSpace(expectedText));
        actualText = locateElementByXPath("//*[@id='addReqAccess']//div[@class='req-header']").getText();
        System.out.println("expected text = " + expectedText);
        System.out.println("actual text = " + actualText);
        verifyActualText(actualText, expectedText);
    }

    public void getReqIDAndStoreIt(String usID, String userRole, String appAcro, String reqID) throws Exception {
        userID = readDataFromExcel(Integer.parseInt(usID), "UserName", "testData2", "" + env + "");
        String reqConfMess = getRequestID(locateElementByXPath("//div[@id='reqConfirmMsg']//div[2]"));
        wait(500);
        click(RequestAccess_Locators.okBtn);
        searchValue(RequestAccess_Locators.searchTextBox, reqConfMess);
        clickWithJSE(RequestAccess_Locators.searchBtn);
        highLightElement(locateElementByXPath("//div[@id='reqtable-frame-ra']//span[contains(text(),'" + reqConfMess + "')]//following-sibling::span[4]"));
        String reqStatus = locateElementByXPath("//div[@id='reqtable-frame-ra']//span[contains(text(),'" + reqConfMess + "')]//following-sibling::span[4]").getText();
        writeReqWithStsToExc("reqUserVerif.xlsx", "" + appAcro + "", "" + userID + "", "" +
                reqConfMess + "", "" + reqStatus + "", "" + AMRVT + "", "" + dValue + "", "" + env2 + "");
    }

    public void submitReqConfirm(String str) throws Exception {
        System.out.println("Request numbers string = " + str);
        System.out.println("Request numbers int = " + Integer.parseInt(str));
        waitForCommonPageLoadingElements();
        verifyWithTrueAssertions(appConsoleRequest.confirmPopUp);
        String requestsConfTest = locateElementByXPath("//p[@id='confirm-message']/parent::div//div").getText();
        System.out.println("Numbers of Request ID = " + StringUtils.countMatches("Request ID", requestsConfTest));
        requests = requestsList(requestsConfTest);
        click(locateElementByXPath("//div[@id='reqConfirmMsg']//button[@ng-click='ok()']"));
    }

    public static List<String> requestsList(String alertText) {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("Request ID: (\\d+),")
                .matcher(alertText);
        while (m.find()) {
            allMatches.add(m.group().replaceAll("Request ID: ", "").replaceAll(",", ""));
        }
        m = Pattern.compile("request is (\\d+)")
                .matcher(alertText);
        while (m.find()) {
            allMatches.add(m.group().replaceAll("request is ", ""));
        }
        return allMatches;
    }

    public void retrieveRequestIDFromAnExternalEmail(DataTable options) {
        list = options.asLists(String.class);
        String emailID = list.get(0).get(1);
        String emailFolder = list.get(1).get(1);
        String emailSubject = list.get(2).get(1);

        String reguestIDFromEmail = getRequestIDFromEmail(
                dEmail = prop.getProperty("dEmail"), dPassword = prop.getProperty("dPassword"),
                emailID, emailFolder, emailSubject);
        wait(7000);
        System.out.println("Expected " + list.get(1).get(1) + " request ID is " + requestID);
        if ((list.get(1).get(1)).equals("Pending")) {
            String actualRequestID = reguestIDFromEmail.substring(0, 6).trim();
            System.out.println("Actual " + list.get(1).get(1) + " request ID from email is " + actualRequestID);
        } else {
            System.out.println("Actual " + list.get(1).get(1) + " request ID from email " + reguestIDFromEmail);
        }
    }

    public void enterTextToJustificationField() throws Exception {
        waitForCommonPageLoadingElements();
        String remainingNumberBefore = locateElementByXPath(justTextField + "//parent::div//span").getText().replaceAll("[^0-9]", "");
        int amountOfJustText = parseInt(remainingNumberBefore);
        System.out.println("Character(s) remaining = " + amountOfJustText);
        Assert.assertEquals(MaxJastif, amountOfJustText);
        justification = randomData.createStr(50);
        type(locateElementByXPath(justTextField), justification);
    }

    public void verifyDynamicRemainCharsText(String string) throws Exception {
        waitForCommonPageLoadingElements();
        String textAfter = locateElementByXPath(justTextField + "//parent::div//span").getText().replaceAll("[^0-9]", "");
        int amountOfJustText = parseInt(textAfter);
        System.out.println("Character(s) remaining = " + amountOfJustText);
        Assert.assertEquals(0, amountOfJustText);
    }

    public void tooltipTextVerification(String tab, DataTable dataTable) {
        waitForCommonPageLoadingElements();
        List<List<String>> lists = dataTable.asLists(String.class);
        String[] arr = null;
        if (tab.equalsIgnoreCase(reqAccTab))
            arr = new String[]{"request-link", "req-access-email-pref-icon", raSrchBxElID, raSrchBtnElID};
        if (tab.equalsIgnoreCase(confAccTab))
            arr = new String[]{caSrchBxElID, caSrchBtnElID};
        for (int i = 0; i < lists.size(); i++) {
            verifyActualText(lists.get(i).get(1), getTooltipText(arr[i], "id"));
        }
    }

    public void selectsOptionsFromFeaturecs(DataTable dataTable) {
        List<String> list = dataTable.asList();
        waitForCommonPageLoadingElements();
        list.forEach(w -> {
            System.out.println("value = " + w);
            if (w.equalsIgnoreCase("Participant ID")) {
                try {
                    type(locateElementByXPath("//*[normalize-space(text())='" + w + "']/parent::div//input"), readDataFromExcel(getEnvVal(), w, "newApps", "Batch"));
                    routingElemHandlingHelper(locateElementByXPath("//ul[@class='dropdown-menu ng-isolate-scope']"), readDataFromExcel(getEnvVal(), "RoutingCA", "newApps", "Batch"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[normalize-space(text())='" + w + "']")));
                    selectFromDropDownMenu(locateElementByXPath("//*[normalize-space(text())='" + w + "']/parent::div//select"), readDataFromExcel(getEnvVal(), w, "newApps", "Batch"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                wait(2000);
            }
        });
    }

    public void alertDisplayed(String string) {
        String alertText = handleAlert();
        System.out.println("alertText = " + alertText);
        verifyActualText(string, alertText);
    }

    public void requestAccess4MultipleRoles(String fileName, String sheetName) throws Throwable {
        List<HashMap<String, String>> inputRows = DataHelper.data(fileName + ".xlsx", sheetName);
        for (HashMap<String, String> rowData : inputRows) {
            BasePage.log.info("START: Request Role: " + rowData);
            try {
                waitForCommonPageLoadingElements();
                click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                click(RequestAccess_Locators.requestNewAccessLink);
            } catch (Exception ignored) {
            }
            String appValue = rowData.get("ApplicationName").trim();
            String role = rowData.get("Role").trim();
            textA = appValue;
            textB = role;
            System.out.println("app = " + appValue);
            System.out.println("role = " + role);
            selectDropDownOption_ByVisibleText(appValue, RequestAccess_Locators.selectAppDropDown);
            selectDropDownOption_ByVisibleText(role, RequestAccess_Locators.selectRoleDropDown);
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
                    setFieldByLabelAndAddMultiVal(label, value, "Add", "");
                    log.info("DONE: Custom Attribute " + j);
                } catch (Exception e) {
                    log.error("\nERROR: " + e.getMessage() + "\nROW DATA: " + rowData);
                    e.printStackTrace();
                }
            }
            if (!isCustomAttributeSettingSuccessfullyFinished) continue;
            justification = randomData.createStr(50);
            typeonForm("This justification field is required", justification);
            click(RequestAccess_Locators.confirmButton);
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#reqConfirmMsg")));
            String requestFromConfirmationWind = getRequestFromConfirmationWind(locateElementByXPath("//p[@id='confirm-message']/parent::div//div"));
            click(locateElementByCSS("#reqConfirmMsg button b"));
            System.out.println("App =" + textA + "Role = " + textB + " Request = " + requestFromConfirmationWind);
            waitForCommonPageLoadingElements();
        }
    }

    public void requestAccess4MultipleRolesACUpdate(String fileName, String sheetName) throws Throwable {
        List<HashMap<String, String>> inputRows = DataHelper.data(fileName, sheetName);
        outPutFile = "requests.xlsx";
        for (HashMap<String, String> rowData : inputRows) {
            BasePage.log.info("START: Request Role: " + rowData);
            try {
                waitForCommonPageLoadingElements();
                click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
            } catch (Exception ignored) {
            }
            textA = rowData.get("ApplicationName").trim();
            textB = rowData.get("Role").trim();
            selectDropDownOption_ByVisibleText(textA, locateElementByCSS("select[placeholder='Select Application']"));
            selectDropDownOption_ByVisibleText(textB, locateElementByCSS("select[placeholder='Select User Role']"));
            boolean isCustomAttributeSettingSuccessfullyFinished = false;
            for (int j = 1; j < 6; j++) {
                String label = "";
                try {
                    label = rowData.get("Custom Attr span " + j);
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
                    setFieldByLabelAndAddMultiValUp(textB, label, value, "");
                    log.info("DONE: Custom Attribute " + j);
                } catch (Exception e) {
                    log.error("\nERROR: " + e.getMessage() + "\nROW DATA: " + rowData);
                    e.printStackTrace();
                }
            }
            wait(500);
            if (!isCustomAttributeSettingSuccessfullyFinished) continue;
            typeonForm("This justification field is required", "IC test automation justification");
            click(RequestAccess_Locators.submitButton);
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#reqConfirmMsg")));
            System.out.println("getText() = " + locateElementByXPath("//div[@id='reqConfirmMsg']//div[2]//div").getText());
            String reqType = getReqType(locateElementByXPath("//div[@id='reqConfirmMsg']//div[2]//div").getText());
            System.out.println("reqType = " + reqType);
            if (!reqType.contains("Unsuccessful Request(s)")) {
                String requFromConfMess = getReqID(appConsoleRequest.confirmPopUp);
                ExcelFile.writeRequestDataToExc(outPutFile, env2, userID, textA, textB, requFromConfMess, dateFormat.format(new Date()), pend);
            }
            click(locateElementByCSS("#reqConfirmMsg button b"));
            waitForCommonPageLoadingElements();
        }
    }

    public String getRequestFromConfirmationWind(WebElement confWindMess) {
        String reqVal = null;
        Matcher m = Pattern.compile("request is (\\d+)")
                .matcher(confWindMess.getText());
        while (m.find()) {
            reqVal = m.group().replaceAll("request is ", "");
        }
        return reqVal;
    }

    public void acRAAngUpt(String str, DataTable dataTable) {
        String text = locateElementByCSS("h1[class='acHeader']").getText();
        System.out.println("text = " + text);
        dataInfo = dataTable.asLists(String.class);
        for (cols = 0; cols < dataInfo.size(); cols++) {
            System.out.println("Table Cols size= " + (dataInfo.size()) + "\nTable Rows size " + (dataInfo.get(0).size() - 1));
            System.out.println("cols = " + cols);
            for (rows = 1; rows <= dataInfo.get(0).size() - 1; rows++) {
                if (dataInfo.get(cols).get(rows).contains("-")) {
                    continue;
                } else {
                    System.out.println("rows = " + rows);
                    highLightElement(locateEleByXPathTextNormSpace(dataInfo.get(cols).get(rows)));
                    isElementPresent(locateEleByXPathTextNormSpace(dataInfo.get(cols).get(rows)));
                    if (dataInfo.get(cols).get(rows).contains("Justification")) {
                        isElementPresent(locateElementByCSS("textarea"));
                    }
                }
            }
        }
    }

    public void justificationTextBoxVerification(String string, DataTable dataTable) {
        waitForCommonPageLoadingElements();
        ArrayList<Integer> lists = new ArrayList<>(), remaining = new ArrayList<>();
        isElementPresent(locateElementByCSS("form textarea"));
        textVal = locateElementByCSS("form span[aria-live='polite']").getText().replaceAll("[^0-9]", "");
        dataTable.asLists().get(0).forEach(p -> {
            randomValue = faker.random().hex(parseInt(p));
            type(locateElementByCSS("form textarea"), randomValue);
            remaining.add(parseInt(locateElementByCSS("form span[aria-live='polite']").getText().replaceAll("[^0-9]", "")));
            lists.add(parseInt(p));
            wait(200);
        });
        for (int i = 0, j = 0; i < lists.size() && j < remaining.size(); i++, j++) {
            if (lists.get(i) >= 500) {
                Assert.assertEquals(0, (int) remaining.get(j));
                continue;
            }
            if (lists.get(i) < 500) {
                Assert.assertEquals(500 - lists.get(i), (int) remaining.get(j));
            }
        }
    }

    public void cancelBtnVerification() throws Exception {
        waitForCommonPageLoadingElements();
        Assert.assertFalse(locateElementByCSS("select[placeholder='Select Application']").isSelected());
        Assert.assertFalse(locateElementByCSS("select[placeholder='Select User Role']").isSelected());
    }

    public void selectAnApplication(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap();
        waitForCommonPageLoadingElements();
        map.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
            if (k.equalsIgnoreCase(AN)) {
                try {
                    selectDropDownOption_ByVisibleText(v, locateElementByCSS("select[placeholder='Select Application']"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (k.equalsIgnoreCase("Role")) {
                try {
                    selectDropDownOption_ByVisibleText(v, locateElementByCSS("select[placeholder='Select User Role']"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        type(locateElementByCSS("form textarea"), "Cancel button verification");
    }

    public void searchBoxVerification(DataTable dataTable) {
        dataTable.asLists().get(0).forEach(p -> {
            type(locateElementByCSS(acModuleRootWE + " input"), p);
            try {
                click(locateElementByCSS("#cmmi-ac mat-icon"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            waitForCommonPageLoadingElements();
            wait(10000);
            locateElementsByCss(acModuleRootWE + " tbody div div").forEach(a -> {
                System.out.println("a = " + a.getText());
//                Assert.assertTrue(a.getText().contains(p));
            });
//            locateElementByCSS("#cmmi-ac input").clear();
//            try {
//                click(locateElementByCSS("#cmmi-ac mat-icon"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            waitForCommonPageLoadingElements();
        });
    }

    public void approveRejectReq(String currentStatus, String statusToBeModify) throws Exception {
        status = currentStatus;
        outPutFile = "requests.xlsx";
        List<HashMap<String, String>> inputRows = DataHelper.getDataFromTargetFolder(outPutFile, env2);
        for (HashMap<String, String> rowData : inputRows) {
            BasePage.log.info("START: Request Role: " + rowData);
            try {
                waitForCommonPageLoadingElements();
                locateElementsByCss(acModuleRootWE + " span button").forEach(p -> {
                    if (p.getText().contains(status)) {
                        if (p.getText().contains("Approved")) {
                            try {
                                click(p);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        wait.until(ExpectedConditions.visibilityOf(p));
                        isElementPresent(p);
                        isElementPresent(locateElementByID("message-ra-view"));
                        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("table[aria-label='Confirm Access Table']")));
                        System.out.println("idDisplayed in the dynamic text = " + locateElementByID("message-ra-view").getText().contains(status));
                    }
                });
            } catch (Exception ignored) {
            }
            String[] values = {"UserID", "ApplicationName", "Role", "requestID"};
            requestID = rowData.get("requestID").trim();
            type(locateElementByID("raSearchField"), requestID);
            click(locateElementByCSS("mat-icon[title='Search']"));
            waitForCommonPageLoadingElements();
            Arrays.stream(values).forEach(row -> {
                        isElementPresent(locateEleByXPathContainsNormSpaceAttr("div/span", rowData.get(row)));
                        isElementPresent(locateEleByXPathContainsNormSpaceAttr("div/span", currentStatus.toUpperCase()));
                    }
            );
            approveRequest(statusToBeModify);
        }
    }

    private void approveRequest(String approverAction) {
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("td button"))).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(locateElementsByCss("[class='cicdim-inline ng-star-inserted'] button")));
        locateElementsByCss("[class='cicdim-inline ng-star-inserted'] button").forEach(p -> {
            if (p.getText().contains(approverAction)) p.click();
        });
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS(winDialBox)));
        type(locateElementByCSS(winDialBox + " textarea"), "IC automation testing for testing " + approverAction+" function");
        locateElementsByCss(winDialBox + " button").forEach(p -> {
                    if (p.getText().contains("Yes")) p.click();
                }
        );
        waitForCommonPageLoadingElements();
    }
}



