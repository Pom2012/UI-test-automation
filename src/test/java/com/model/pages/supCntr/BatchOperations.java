package com.model.pages.supCntr;

import com.model.pages.repCenter.ReportsCenterPages;
import com.model.utility.FileActions;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.model.utility.Alerts_and_Requests.handleAlertBatchOperGetReq;
import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.utility.FileActions.detecteAndDeletedownloadedFile;
import static com.model.base.Constants.RequestStatus.appr;
import static com.model.base.Constants.RequestStatus.pend;
import static java.lang.Integer.parseInt;

public class BatchOperations extends SupportCenterModule {
    private final String boWebTblID = "#hdRequestTable", ttlReq = "#message-hd";
    private int totalExpNums = 0;
    private int totActNumbs = 0;
        public static final java.util.Date date1 = new Date();
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public static final String Date1 = dateFormat.format(date1);
        public static String approveDateAndTime = null;
    public static String rejectDateAndTime = null;
    public static String approveJust = "Test for approving " + Date1;
    public static String rejectJust = "Test for rejecting " + Date1;
    ReportsCenterPages repCentMethods = new ReportsCenterPages();
    public String getBoWebTblID() {
        return boWebTblID;
    }



    public void selectApp(String dropdow, String sBtn) throws Exception {
        System.out.println("App = " + readDataFromExcel(getEnvVal(), dropdow, "newApps", "SearchApp"));
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByCSS("#hdForm_ic-select_applicationId_6"), readDataFromExcel(getEnvVal(), dropdow, "newApps", "SearchApp"));
        click(locateElementByCSS("button[value='" + sBtn + "']"));
        wait.until(ExpectedConditions.visibilityOfAllElements(locateElementsByCss(boWebTblID + " tr")));
        highLightElement(locateElementByCSS(boWebTblID));
    }

    public void webTableAndColumnsVer(String string, DataTable dataTable) {
        List<String> list = dataTable.asList();
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByCSS(boWebTblID));
        locateElementsByCss(boWebTblID + " th").forEach(p -> {
            textVal = p.getText();
            list.forEach(w -> {
                if (textVal.contains(w)) {
                    highLightElement(p);
                    if (textVal.contains("Status")) {
                        textA = textVal.substring(0, 6);
                        verifyActualText(w, textA);
                    } else {
                        verifyActualText(w, p.getText());
                    }
                }
            });
        });
    }

    public void selectAppliction(String app) throws Exception {
        System.out.println("Searching by Application Name >> " + app);
        selectFromDropDownMenu(locateElementByID("hdForm_ic-select_applicationId_6"), app);
        wait(2000);
        click(locateElementByCSS("button[type='submit']"));
        waitForExpectedElement(locateElementByCSS(ttlReq));
        waitForCommonPageLoadingElements();
    }

    public void webTableIsDisplayed(String arg1) {
        isElementPresent(locateEleByXPathTextNormSpace(arg1));
        highLightElement(locateElementByID("message-hd"));
        highLightElement(locateElementByCSS(boWebTblID));
    }

    public void verifyReqStatusDropDown(DataTable reqStatOpt) throws Exception {
        cucTabledata = reqStatOpt.asLists(String.class);
        for (List<String> cucTabledatum : cucTabledata) {
            selectFromDropDownMenu(locateElementByXPath("//select[@ng-model='status']"), cucTabledatum.get(0));
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS(boWebTblID)));
            highLightElement(locateElementByCSS(ttlReq));
            System.out.println("\n\tSelecting " + cucTabledatum.get(0) + " results the below \n" + locateElementByCSS(ttlReq).getText() + "\n");
        }
    }

    public void requestHistoryAlert(String arg1) throws Exception {
        isElementPresent(locateElementByXPathContainsText(arg1));
        highLightElement(locateElementByXPathContainsText(arg1));
        String text = locateElementByXPathContainsText(arg1).getText();
        boolean expectedTextIsTrue = text.contains(arg1);
        log.info("expectedIsTrue = " + expectedTextIsTrue);
        click(locateElementByXPath("(//*[@class='btn btn-sm btn-blue'])[1]"));
    }

    public void processedRecordsDataDisplayed(String string) throws Exception {
        totalExpNums = totalEntriesNum(locateElementByCSS(ttlReq).getText());
        System.out.println("totNum = " + totActNumbs);
    }

    public void csvFileBOVerification(String string) throws Exception {
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("HelpDeskSearchResult.csv", driver));
        wait(2000);
        totActNumbs = FileActions.getRecordsCountInCSV("HelpDeskSearchResult.csv");
        Assert.assertEquals("Downloaded file does not match with actual", totActNumbs, totalExpNums);
        detecteAndDeletedownloadedFile("HelpDeskSearchResult.csv");
    }

    public void searchAUserInBatchOperation(String tab, String app, String role, String btn) throws Throwable {
        click(locateElementByXPathText(tab));
        selectByLabelAndOptionText("Application Name", app);
        selectByLabelAndOptionText("Role", role);
        //ENH: Works here but not universally
        if (tab.equalsIgnoreCase("Batch Operations") && btn.equalsIgnoreCase("Search"))
            click(locateElementByXPath("//label[contains(text(), 'Request ID')]/ancestor::form//button[@type='submit']"));
        else
            click(locateElementByXPathText(btn));

    }

    public void approveRejectedRrquest(String status, String btn) throws Exception {
        selectFromDropDownMenu(locateElementByXPath("//select[@ng-model='status']"), status);
        wait(5000);
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//table[@id='hdRequestTable']//tbody//tr[1]//a")));
        requestID = locateElementByXPath("//table[@id='hdRequestTable']//tbody//tr[1]//a").getText();
        System.out.println("requestID = " + requestID);
        click(locateElementByXPath("//table[@id='hdRequestTable']//tbody//tr[1]//input"));
        wait(2000);
        click(locateElementByXPathText(btn));
    }

    public void searchApplication(String appname, String tabName) throws Exception {
        selectFromDropDownMenu(locateElementByID("hdForm_ic-select_applicationId_6"), appname);
        click(locateElementByCSS("button[type='submit']"));
        waitForCommonPageLoadingElements();
    }

    public void rejectRequests() throws Exception {
//        TODO: this is temp code, update it;
        String[] requestsStatus = {appr, pend};
        int tot=0;
        boolean totalRequest;
        String rejJust = "Your request is rejected because ACO has been decommissioned from Innovation Center landing page as per SR-1468 approved by CMS.";
        for (int i = 0; i < 100; i++) {
            selectFromDropDownMenu(locateElementByCSS("select[ng-model='status']"), appr);
            waitForCommonPageLoadingElements();
            totalRequest = locateElementByCSS(ttlReq).getText().contains("There are 0 requests");
            if (!totalRequest) {
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByCSS("#select-all"))).click();
                click(locateElementByXPathText("Reject Request(s)"));
                wait(1000);
                inputTextByLabelAndElementAndText("Justification", "textarea", rejJust);
                wait.until(ExpectedConditions.elementToBeClickable(locateEleByXPathTextNormSpace("Submit"))).click();
                handleAlert();
                waitForCommonPageLoadingElements();
                wait(1000);
            }
            waitForCommonPageLoadingElements();
        }

    }

    public void submitApproveFromBO(String submit, String arg2) throws Exception {
        highLightElement(locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea"));
        locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea").sendKeys(approveJust);
        highLightElement(locateEleByXPathTextNormSpace(submit));
        wait.until(ExpectedConditions.elementToBeClickable(locateEleByXPathTextNormSpace(submit))).click();
        String requestIDFromAlert = handleAlertBatchOperGetReq();
        System.out.println("request ID from alert = " + requestIDFromAlert);
        verifyActualText(requestIDFromAlert, requestID);
        approveDateAndTime = Date1;
        System.out.println("Rejected >>> " + approveDateAndTime);
    }

    public void submitRejectFromBO(String btn) throws Exception {
        click(locateElementByXPath("//table[@id='hdRequestTable']//tbody//tr[contains(. ,'" + BasePage.requestID + "')]//input"));
        click(locateElementByXPathText(btn));
        inputTextByLabelAndElementAndText("Justification", "textarea", rejectJust);
        wait.until(ExpectedConditions.elementToBeClickable(locateEleByXPathTextNormSpace("Submit"))).click();
        handleAlert();
        rejectDateAndTime = Date1;
        System.out.println("Rejected >>> " + rejectDateAndTime);
    }

    public void operationGeneratedInReport() throws Exception {
        repCentMethods.batchOperChangesAndAppSummRepVer(rejectDateAndTime, rejectJust);
    }
}

