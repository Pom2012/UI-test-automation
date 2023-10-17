package com.model.pages.repCenter;

import com.model.utility.DataHelper;
import com.model.utility.FileActions;
import com.model.base.BasePage;
import com.model.locators.reportCenterLoc.Portal_ReportCenter_All_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.model.utility.DataHelper.*;
import static com.model.utility.FileActions.detecteAndDeletedownloadedFile;
import static com.model.base.Constants.IC_Reports.*;
import static com.model.base.Constants.ModelApplications.AMRVT;
import static com.model.base.Constants.RequestStatus.appr;
import static com.model.base.Constants.RequestStatus.rej;
import static com.model.base.Constants.fields.*;

public class ReportsCenterPages extends BasePage {
    List<String> we4Reps;
    String subRep = null, selctDropWE = null, subRepTable = null, showEntr = null;
    public List<HashMap<String, String>> reportName;
    private List<List<String>> dtInf = null;
    AppSumReportPage appSumReptMeth = new AppSumReportPage();
    public static List<String> templates = new ArrayList<>();
    private static List<WebElement> elementList = new ArrayList<>();
    private static Portal_ReportCenter_All_Locators reportLocator;
    public static Date date = new Date();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMddyyyy");
    public static String dValue = dateFormat.format(date);
    private String dValue1 = dateFormat1.format(date);
    public static String app = null;
    public static String showEntriesText = "//div[@id='applicationUserDetailReport_info']";
    private String ASRUsRolDets4App = "//div[@id='applicationUserDetailReport_wrapper']";
    private String uvNPURep = "//div[@id='user-verification-history-report_wrapper']";
    String featureWE = "//div[@id='applicationUserDetailReport_paginate']";
    int totalReportValue = 0, downReportValue = 0, numbsOfUVRepTotalEntries = 0, acualNumbersOfEntries = 0;

    public void verifyCsvRepIsFullyDownloaded() throws InterruptedException {
        int x = 0;
        String value = null;
        while (value == null) {
            value = locateElementByID("cmmi-downloadReportLinkTextMessage").getText();
            totalReportValue = Integer.parseInt(value.replaceAll("^[^/]+[^0-9 ]", "").trim());
            downReportValue = Integer.parseInt(value.replaceAll("^[^:]*:", "").replaceAll("\\/.*$", "").trim());
            System.out.println(x + " round downloading. Total report value is " + totalReportValue + "\n. Downloaded Report Value " + downReportValue);
            Thread.sleep(300);
            System.out.println("check the down Report Value again= " + downReportValue);
            if (totalReportValue == downReportValue) break;
            x++;
        }
    }

    public void selectingApplicationReport(String appReportLbl, String appName, String viewBtn, DataTable dataTable) throws Exception {
        Map<String, String> mapData = dataTable.asMap(String.class, String.class);
        waitForCommonPageLoadingElements();
        mapData.forEach((key, value) -> {
            System.out.println("key = " + key + " value = " + value);
            try {
                highLightElement(locateElementByXPathText(key));
                selectFromDropDownMenu(locateElementByXPath("//*[text() = '" + key + "']//parent::div/following-sibling::div/select"), value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        click(locateElementByCSS("#idSubmit"));
        waitForCommonPageLoadingElements();
    }

    public ReportsCenterPages() {
        reportName = DataHelper.data("newApps.xlsx", "Reports");
        reportLocator = PageFactory.initElements(driver, Portal_ReportCenter_All_Locators.class);
    }

    public void verifyReport(String role, String reportName, String appName) throws Exception {
        System.out.println("Report = " + reportName);
        switch (reportName) {
            case ADM_ACT:       // Administrator Activity
                verifyAdministratorActivityReport();
                break;
            case LIST_ACT:      // List Activity
                verifyListActivityReport();
                break;
            case APP_MONTR:     // Application Monitoring
                verifyApplicationMonitoringReport();
                break;
            case LAST_ACCESS:   // Last Accessed
                verifyLastAccessedReport();
                break;
            case PV_USR:        // Privileged User
                verifyPrivilegedVUserReport();
                break;
            case USER_ACCS:     // User Access
                verifyUserAccessReport();
                break;
            case APP_SUM:       // Application Summary
                verifyApplicationSummaryReport(appName);
                break;
            case APP_USG:       // Application Usage
                verifyApplicationUsageReport(appName);
                break;
            case CUS_ATTR:      // Custom Attribute
                verifyCustomAttributeReport(appName);
                break;
            case US_INACT:      // User Inactivity
                verifyUserInactivityReport(appName);
                break;
            case US_VERIF:      // User Verification
                verifyUserVerificationReport(appName);
                break;
            default:
                System.out.println("ERROR: No such report: " + reportName);
        }
        clickWithJSE(locateElementByXPathText("Back"));
        log.info("User clicks on 'Back' button after Report: " + reportName);
    }

    public static void reports4UserVerification(String rep, String app, String userRoleDet4App, String reqStatus1, DataTable data) throws Throwable {
        List<List<String>> reportData = data.asLists(String.class);
        String id, webAttr = "applicationUserDetailReport_wrapper", reqStatus, userID, practiceRole, customAttr, actReqStatus, actLastActType, actUserID;
        System.out.println("\nReport: " + rep + "\nApplication: " + app);
        //TODO: DATA: Users should not be hard-coded or by row#
        String[] users =
                {readDataFromExcel(11, "UserName", "testData2", "" + env + ""),
                        readDataFromExcel(14, "UserName", "testData2", "" + env + ""),
                        readDataFromExcel(10, "UserName", "testData2", "" + env + "")
                };
        if (rep.equalsIgnoreCase(APP_SUM) && app.equalsIgnoreCase(AMRVT)) {
            searchBar("(//div[@class='row cicdim-report-table-padding'][2]//input)[1]", reportData.get(1).get(3));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("applicationUserDetailReport_wrapper")));
            for (int i = 1, k = 0; i < 4 || k < 3; i++, k++) {
                reqStatus = reportData.get(i).get(4);
                userID = users[k];
                practiceRole = reportData.get(i).get(2);
                customAttr = reportData.get(i).get(3);
                actLastActType = reportData.get(i).get(5);
                switch (reqStatus) {
                    case appr:
                        selectFromDropDownMenu(locateElementByID("roleStatus"), appr);
                        break;
                    case rej:
                        selectFromDropDownMenu(locateElementByID("roleStatus"), rej);
                        break;
                }

                waitForCommonPageLoadingElements();
                wait.until(ExpectedConditions.visibilityOf(locateElementByID(webAttr)));
                scroll_Down(locateElementByXPath("//strong[contains(text(),'User Role Details for')]"));
                actUserID = locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]").getText();
                System.out.println("\nExpected user: " + userID + " Actual user: " + actUserID + "\n");
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]"));

                System.out.println("\nActual request ID: " + locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]/ancestor::div[@id='applicationUserDetailReport_wrapper']//td[1]") + "\n");
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]/ancestor::div[@id='applicationUserDetailReport_wrapper']//td[1]"));

                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'"
                        + userID + "')]/following-sibling::td[contains(text(),'" + practiceRole + "')]"));
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'"
                        + userID + "')]/following-sibling::td[contains(text(),'" + customAttr + "')]"));
                actReqStatus = locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]/following-sibling::td[contains(text(),'" + reqStatus + "')]").getText();
                System.out.println("\nExpected request status: " + reqStatus + " Actual request status: " + actReqStatus.trim() + "\n");
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]/following-sibling::td[contains(text(),'" + reqStatus + "')]"));
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'"
                        + userID + "')]/following-sibling::td[contains(text(),'" + actLastActType + "')]"));
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'"
                        + userID + "')]/following-sibling::td[7]"));
                highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'"
                        + userID + "')]/following-sibling::td[8]"));

            }
        }
        if (rep.equalsIgnoreCase(US_VERIF) && app.equalsIgnoreCase(AMRVT)) {
            clickWithJSE(locateElementByID("ic-verification-history-open-dialog"));
            highLightElement(locateElementByID("ic-verification-history-info-dialog"));
            for (int i = 1; i <= 3; i++) {
                System.out.println("User: " + reportData.get(i).get(1));
                locateElementByXPath("(//div[@id='user-verification-history-report_wrapper']//input)[1]").clear();
                id = readUVRepData("reqUserVerif", "AMRVT", "" + reportData.get(i).get(1) + "");
                System.out.println("Request id = " + id);
                searchValue(locateElementByXPath("(//div[@id='user-verification-history-report_wrapper']//input)[1]"), id);
                click(locateElementByID("user_verification_information_btn"));
                wait(500);
                scroll_Down(locateElementByID("user-verification-history-report_wrapper"));
                //table content
                for (int j = 1; j <= 12; j++) {
                    highLightElement(locateElementByXPath("//table[@id='user-verification-history-report']//td[" + j + "]"));
                    if (j == 1) {
                        System.out.println("\nActual Request ID:   " + locateElementByXPath("//table[@id='user-verification-history-report']//td[" + j + "]").getText());
                        System.out.println("Expected Request ID: " + readUVRepData("reqUserVerif", "AMRVT", "" + reportData.get(1).get(i) + ""));
                    }
                }
            }
        }
    }

    void verifyAdministratorActivityReport() throws Exception {
        click(reportLocator.adminActivityLink);
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.adminActivityReportTableContent));
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#admin-actvty-report tbody").isDisplayed());
        String typeValue = locateElementByXPath("//div[@id='admin-actvty-report_wrapper']//tbody//td").getAttribute("class");
        System.out.println("Administrator Activity Report table  = " + typeValue);
        if (!typeValue.equalsIgnoreCase("dataTables_empty")) {
            highLightElement(reportLocator.adminActivityReportTableContent);
            scroll_Into_View(reportLocator.adminActivityReportTotal);
            searchValue(locateElementByCSS("#appName"), "XXX");
            click(locateElementByCSS("[id='filterFormBtn']"));
            waitForCommonPageLoadingElements();
            System.out.println("Admin Activity Report Table content is loaded...");
            String adminActivityNumOfRecords = getTextofWebElementSimple(reportLocator.adminActivityReportTotal);
            System.out.println("Total Entries = " + totalEntriesNum(adminActivityNumOfRecords));
            if (totalEntriesNum(adminActivityNumOfRecords) == 0)
                wait(5000);
            System.out.println("Total Entries = " + totalEntriesNum(adminActivityNumOfRecords));
            System.out.println("Admin Activity Report: " + adminActivityNumOfRecords);
            clickWithJSE(locateElementByID("cmmi-downloadReportLink"));
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDownlTotal));
            highLightElement(reportLocator.appSummaryDownlTotal);
            verifyCsvRepIsFullyDownloaded();
            String DownloadedRec1 = getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
            System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec1);
            System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("AdminActivityReport.csv", driver));
        } else {
            System.out.println("Administrator Activity Report table is not loaded");
        }
    }

    void verifyListActivityReport() throws Exception {
        reportLocator.listActivityLink.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.listActivityReportTotal));
        scroll_Into_View(reportLocator.listActivityReportTotal);
        highLightElement(reportLocator.listActivityReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#list-actvty-report_wrapper tbody").isDisplayed());
        String listActivityNumOfRecords = getTextofWebElementSimple(reportLocator.listActivityReportTotal);
        System.out.println("List Activity Report: " + listActivityNumOfRecords);
    }

    void verifyApplicationMonitoringReport() throws Exception {
        reportLocator.appMonitoringLink.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appMonitICuserCountReportTotal));
        scroll_Into_View(reportLocator.appMonitICuserCountReportTotal);
        highLightElement(reportLocator.appMonitICuserCountReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#appUserCountDetails_wrapper tbody").isDisplayed());
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#applicationMonitoringUserInformationReport_wrapper tbody").isDisplayed());
        String appMonitICUserNumOfRecords = getTextofWebElementSimple(reportLocator.appMonitICuserCountReportTotal);
        System.out.println("App Monitoring (IC User) Report: " + appMonitICUserNumOfRecords);
    }

    void verifyLastAccessedReport() throws Exception {
        //TODO: DATA: Users should not be hard-coded or by row#
        userID = readDataFromExcel(5, "UserName", "testData2", "" + env + "");
        wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportLink));
        reportLocator.lastAccessReportLink.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportTotal));
        scroll_Into_View(reportLocator.lastAccessReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#last-access-report_wrapper tbody").isDisplayed());
        searchValue(locateElementByCSS("#userId"), userID);
        wait(3000);
        do {
            try {
                clickWithJSE(locateElementByID("cmmi-downloadReportLink"));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!reportLocator.lastAccessReportDownlTotal.isDisplayed());
        highLightElement(reportLocator.lastAccessReportDownlTotal);
        verifyCsvRepIsFullyDownloaded();
        System.out.println("The Last Access Report file downloaded >>> " + FileActions.getDownloadedFileNameByDefaultFileName("LastAccessedReport.csv", driver));
    }

    void verifyPrivilegedVUserReport() throws Exception {
        reportLocator.privUserLink.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.privUserReportTotal));
        scroll_Into_View(reportLocator.privUserReportTotal);
        highLightElement(reportLocator.privUserReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#eidm-report_wrapper tbody").isDisplayed());
        String lastReportDate = locateElementByXPath("//div[@class='cicdim-report-inline']/p").getText();
        System.out.println("Last report date: " + lastReportDate);
        click(locateElementByXPathText("Run Report"));
        wait(6000);
        String newlyUpdatedReportDate = locateElementByXPath("//div[@class='cicdim-report-inline']/p").getText();
        System.out.println("Newly updated report date: " + newlyUpdatedReportDate);
        String privUserNumOfRecords = getTextofWebElementSimple(reportLocator.privUserReportTotal);
        System.out.println("PV user Report: " + privUserNumOfRecords);
    }

    void verifyUserAccessReport() throws Exception {
        reportLocator.userAccessReportLink.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.userAccessReportTotal));
        scroll_Into_View(reportLocator.userAccessReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#user-access-report_wrapper tbody").isDisplayed());
        //  filtering
        typeWithJSById("startDate", dValue);
        typeWithJSById("endDate", dValue);
        clickWithJSE(locateElementByID("filterFormBtn"));
        waitForCommonPageLoadingElements();
        do {
            try {
                clickWithJSE(locateElementByID("cmmi-downloadReportLink"));
                wait(10000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!(reportLocator.userAccessReportDownlTotal).isDisplayed());
        String userAccessNumOfRecords = getTextofWebElementSimple(reportLocator.userAccessReportTotal);
        System.out.println("User Access Report: " + userAccessNumOfRecords);
        verifyCsvRepIsFullyDownloaded();
        highLightElement(reportLocator.userAccessReportDownlTotal);
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("UserAccessReport.csv", driver));
    }

    void verifyApplicationSummaryReport(String appName) throws Exception {
        selectDropDownOption_ByVisibleText(appName, reportLocator.appSummaryDropDown);
        reportLocator.appSummaryViewBtn.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
        scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
        highLightElement(reportLocator.appSummUserRoleReportTotal);
        selectFromDropDownMenu(locateElementByCSS("#application-user-detail-report-form select"), appr);
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
        scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
        String appSummNumOfRecords = getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
        System.out.println("App Summary(User Role Details) Report: " + appSummNumOfRecords);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#applicationUserDetailReport tbody").isDisplayed());
        try {
            clickWithJSE(locateElementByID("cmmi-downloadReportLink"));
            System.out.println("Download report button is clicked... ");
            wait(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        reportLocator.appSummaryDownlTotal.isDisplayed();
        verifyCsvRepIsFullyDownloaded();
        highLightElement(reportLocator.appSummaryDownlTotal);
        String DownloadedRec = getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
        System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec);
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("ApplicationUserDetail.csv", driver));
    }

    void verifyApplicationUsageReport(String appName) throws Exception {
        reportLocator.appUsageText.click();
        selectDropDownOption_ByVisibleText(appName, reportLocator.appUsageDropDown);
        reportLocator.appUsageViewBtn.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageReportTotal));
        scroll_Into_View(reportLocator.appUsageReportTotal);
        highLightElement(reportLocator.appUsageReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#app-usage-report_wrapper tbody").isDisplayed());
        String appUsageNumOfRecords = getTextofWebElementSimple(reportLocator.appUsageReportTotal);
        System.out.println("App Usage Report: " + appUsageNumOfRecords);
        do {
            try {
                clickWithJSE(locateElementByID("cmmi-downloadReportLink"));
                wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageDownloadTotal));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!reportLocator.appUsageDownloadTotal.isDisplayed());
        verifyCsvRepIsFullyDownloaded();
        highLightElement(reportLocator.appUsageDownloadTotal);
        wait(200);
        System.out.println("The Application Usage Report file downloaded >>> " + FileActions.getDownloadedFileNameByDefaultFileName("ApplicationUsageReport.csv", driver));
    }

    void verifyCustomAttributeReport(String appName) throws Exception {
        highLightElement(reportLocator.customAttributeText);
        selectDropDownOption_ByVisibleText(appName, reportLocator.custAttrDropDown);
        reportLocator.custAttrViewBtn.click();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.custAttrReportTotal));
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#customAttributeReport_wrapper tbody").isDisplayed());
        scroll_Into_View(reportLocator.custAttrReportTotal);
        highLightElement(reportLocator.custAttrReportTotal);
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#customAttributeValuesReport_wrapper tbody").isDisplayed());
        String custAttrNumOfRecords = getTextofWebElementSimple(reportLocator.custAttrReportTotal);
        System.out.println("Custom Attribute Report: " + custAttrNumOfRecords);
    }

    void verifyUserInactivityReport(String appName) throws Exception {
        highLightElement(reportLocator.userInactLabel);
        System.out.println("uiReport: " + appName);
        selectDropDownOption_ByVisibleText(appName, reportLocator.userInactDropDown);
        click(reportLocator.userInactViewBtn);
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='MainContent']")));
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#user-role-view tbody").isDisplayed());
        /**    Tab 1     */
        highLightElement(locateElementByXPath("//div[@id='MainContent']"));
        String userTab1 = locateElementByID("userTab1").getText();
        //User Inactivity Status Summary
        highLightElement(locateElementByID("tabs-1"));
        /**    Tab 2     */
        clickWithJSE(locateElementByID("userTab2"));
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("tabs-2")));
        String userTab2 = locateElementByID("userTab2").getText();
        highLightElement(locateElementByID("tabs-2"));
        //Users in the Warning Phase
        /*    Tab 3     */
        clickWithJSE(locateElementByID("userTab3"));
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("tabs-3")));
        String userTab3 = locateElementByID("userTab3").getText();
        highLightElement(locateElementByID("tabs-3"));
        //User Inactivity History
    }

    void verifyUserVerificationReport(String appName) throws Exception {
        highLightElement(reportLocator.userVerifLabel);
        System.out.println("uvReport: " + appName);
        selectDropDownOption_ByVisibleText(appName, reportLocator.userVerifDropDown);
        click(reportLocator.userVerifViewBtn);
        waitForCommonPageLoadingElements();
        longWaitForElementAllowNull("//strong[text()='User Verification Report']", "xpath");
        highLightElement(locateElementByXPath("//strong[text()='User Verification Report']"));
        highLightElement(locateElementByXPath("//strong[text()='User Verification Summary Report']"));
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#user-verification-summary-report_wrapper tbody").isDisplayed());
        scroll_Down(locateElementByID("user-verification-history-report_info"));
        highLightElement(locateElementByXPath("//strong[text()='User Verification History Report']"));
        highLightElement(locateElementByID("user-verification-history-report_wrapper"));
        Assert.assertTrue("The report table did not displayed", locateElementByCSS("#user-verification-history-report_wrapper tbody").isDisplayed());
    }

    public static void verifyTheUserInactRepGenerated(String a, String b) throws Exception {
        for (int i = 0; i < 4; i++) {
            System.out.println("Template " + templates.get(i));
            String text = locateEleByXPathTextNormSpace(templates.get(i)).getText();
            verifyActualText(text, templates.get(i));
            clickOnTextWithJSEAndNormSpaceAttr("td", templates.get(i));
        }
    }

    public static void calendarFeatureInReports(DataTable reportFeature) throws Exception {
        cucTabledata = reportFeature.asLists(String.class);
        if (cucTabledata.get(0).get(0).equalsIgnoreCase("Start Date") && cucTabledata.get(0).get(1).equalsIgnoreCase("Today")) {
            click(locateElementByXPath("(//*[@class='glyphicon glyphicon-calendar'])[1]"));
            click(locateElementByXPath(("(//*[@class='day selectable curDay focus'])[1]")));
            wait(2000);
        }
        if (cucTabledata.get(1).get(0).equalsIgnoreCase("End Date") && cucTabledata.get(1).get(1).equalsIgnoreCase("Today")) {
            click(locateElementByXPath("(//*[@class='glyphicon glyphicon-calendar'])[2]"));
            click(locateElementByXPath(("(//*[@class='day selectable curDay focus'])[2]")));
        }
        System.out.println("Application name " + cucTabledata.get(2).get(1));
        searchValue(locateElementByID("appName"), cucTabledata.get(2).get(1));
        wait(500);
    }

    public static void emailTemplateAppSummRepVerification(DataTable colsName) {
        List<List<String>> data = colsName.asLists(String.class);
        List<WebElement> oldValue;
        for (int i = 3; i <= 1; i--) {
            System.out.println("Iterate # " + i);
            oldValue = driver.findElements(By.xpath("(//table[@id='admin-actvty-report']/tbody/tr/td[4])[" + i + "]"));
            for (WebElement each : oldValue) {
                if (each.getText().contains("title") && each.getText().contains(body4Creating)) {
                    createdEmailTemplateOV += each.getText();
                    System.out.println("Created template new value >>> " + each.getText());
                    break;
                }
                if (each.getText().contains(body4Updating)) {
                    updatedEmailTemplateOV += each.getText();
                    System.out.println("Updated template new value >>> " + each.getText());
                    break;
                }
                if (each.getText().contains(body4Deleting)) {
                    deletedEmailTemplateOV += each.getText();
                    System.out.println("Deleted template new value >>> " + each.getText());
                    break;
                }
            }
        }

        /** New Value */
        for (int i = 3; i <= 1; i--) {
            System.out.println("Iterate # " + i);
            oldValue = driver.findElements(By.xpath("(//table[@id='admin-actvty-report']/tbody/tr/td[5])[" + i + "]"));
            for (WebElement each : oldValue) {
                if (each.getText().contains("title") && each.getText().contains(body4Creating)) {
                    createdEmailTemplateNV += each.getText().contains(body4Creating);
                    System.out.println("Actual created template >>> " + each.getText());
                    break;
                }
                if (each.getText().contains(body4Updating)) {
                    updatedEmailTemplateNV += each.getText().contains(body4Updating);
                    System.out.println("Actual updated template >>> " + each.getText());
                    break;
                }
                if (each.getText().contains(body4Deleting)) {
                    deletedEmailTemplateOV += each.getText().contains(body4Deleting);
                    System.out.println("Actual deleted template >>> " + each.getText());
                    break;
                }
            }
        }
    }

    public void selectingApplicationReport(String arg1, String arg2) throws Exception {
        selectFromDropDownMenu(locateElementByXPath("//*[normalize-space(text())='" + arg2 + "']/ancestor::form//select"), arg1);
        click(locateElementByXPath("//*[normalize-space(text())='" + arg2 + "']/ancestor::form//button"));
    }

    public void selectEntriesInReports(String reprtName, DataTable entriesValue) throws Exception {
        cucTabledata = entriesValue.asLists(String.class);
        selctDropWE = weLocsGenerator(reprtName).get(0);
        subRepTable = weLocsGenerator(reprtName).get(1);
        showEntr = weLocsGenerator(reprtName).get(2);
        String parseNum;
        for (int i = 0; i < cucTabledata.get(0).size(); i++) {
            selectFromDropDownMenu(locateElementByXPath(selctDropWE), cucTabledata.get(0).get(i));
            waitForCommonPageLoadingElements();
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(subRepTable)));
            String text = locateElementByXPath(showEntr).getText();
            parseNum = text.replaceAll(".*(?= to)", "").replaceAll("[^of]*$", "").replaceAll("[^\\d]", "").trim();
            waitForCommonPageLoadingElements();
            if (Integer.parseInt(parseNum) == Integer.parseInt(cucTabledata.get(0).get(i))) {
                Assert.assertEquals(cucTabledata.get(0).get(i), parseNum);
            }
        }
    }

    private List<String> weLocsGenerator(String str) {
        we4Reps = new ArrayList<>();
        if (str.equalsIgnoreCase(UsDet4App)) {
            String[] locText = {"(//*[normalize-space(text())='", "']/parent::h2/parent::div/following-sibling::div"};
            we4Reps.add(locText[0] + str + locText[1] + "//select)[1]");
            we4Reps.add(locText[0] + str + locText[1] + "//table)[1]");
            we4Reps.add(locText[0] + str + locText[1] + ")[1]//div[@id='applicationUserDetailReport_info']");
        }
        if (str.equalsIgnoreCase("Detailed History")) {
            we4Reps.add("(//*[normalize-space(text())='" + str + "']/ancestor::div//select)[4]");
            we4Reps.add("//table[@id='user-verification-history-report']");
            we4Reps.add("//div[@id='user-verification-history-report_info']");
        }
        if (str.equalsIgnoreCase("Custom Attribute Values for Application")) {
            we4Reps.add("(//*[normalize-space(text())='" + str + "']/ancestor::div//select)[2]");
            we4Reps.add("//table[@id='customAttributeValuesReport']");
            we4Reps.add("//div[@id='customAttributeValuesReport_info']");
        }
        return we4Reps;
    }

    public void batchOperChangesAndAppSummRepVer(String rejectDateAndTime, String rejectJust) throws Exception {
        locateElementByXPath("(//div[@id='applicationUserDetailReport_wrapper']//input)[1]").sendKeys(requestID);
        click(locateElementByXPath("(//div[@id='applicationUserDetailReport_wrapper']//button)[1]"));
        wait(500);
        String actualRequestID = locateElementByXPath("//table[@id='applicationUserDetailReport']//td[1]").getText();
        String actualStatus = locateElementByXPath("//table[@id='applicationUserDetailReport']//td[7]").getText();
        String actualLastUpdatedDate = locateElementByXPath("//table[@id='applicationUserDetailReport']//td[9]").getText();
        String actualJustif = locateElementByXPath("//table[@id='applicationUserDetailReport']//td[10]").getText();
        System.out.println(
                actualRequestID + " " + requestID + "\n" +
                        actualStatus + " REJECTED \n" +
                        actualLastUpdatedDate.substring(0, 18) + " " + rejectDateAndTime + "\n" +
                        actualJustif + " " + rejectJust
        );
        Assert.assertEquals(actualRequestID, requestID);
        Assert.assertEquals(actualStatus, "REJECTED");
    }

    public void downloadAndVerifyAppSumRepCsvFile() throws Exception {
        System.out.println("\n....downloading the Application User Detail Report in CSV format............\n");
        try {
            clickWithJSE(reportLocator.downloadappSummLink);
            System.out.println("Download report button is clicked... ");
            wait(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String downloadedRecCSV = getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
        System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + downloadedRecCSV);
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("ApplicationUserDetail.csv", driver));
    }

    public void downloadAndVerifyAppSumRepCsvFile(String btnName, String downlFileName, DataTable data) throws Exception {
        waitForCommonPageLoadingElements();
        Map<String, String> keyVal = data.asMap();
        try {
            clickWithJSE(reportLocator.detailDownLdRprtBnt);
            wait(10000);
            longWait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDownlTotal));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Total downloaded : " + getTextofWebElementSimple(reportLocator.appSummaryDownlTotal));
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName(keyVal.get("downloaded_file_name") + ".csv", driver));
    }

    public void downloadAndVerifyAppSumRepExcelFile(String btnName, String downlFileName, DataTable data) throws Exception {
        Map<String, String> keyVal = data.asMap();
        if (keyVal.get("downloaded_file_name").contains("Application User Details Report")) textA = ASRUsRolDets4App;
        if (keyVal.get("downloaded_file_name").contains("User Verification History Report")) textA = uvNPURep;
        clickWithJSE(locateElementByXPath(textA + "//a[@title='Export Report in " + keyVal.get("buttonName") + " Format']"));
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName(keyVal.get("downloaded_file_name") + " " + dValue1 + ".xlsx", driver));
    }

    public static int currentEntries(String str) throws Exception {
        int currentEntries = 0;
        currentEntries = Integer.parseInt(locateElementByXPath(str).getText().
                replaceAll(".*(?= to )", "").replaceAll("(?<= of ).*", "").replaceAll("[^\\d]", "").trim());
        return currentEntries;
    }

    public static int totalEntriesByXPath(String str) throws Exception {
        int totalEntries = 0;
        totalEntries = Integer.parseInt(locateElementByXPath(str).getText().
                replaceAll(".*(?= of )", "").replaceAll("(?<= entries ).*", "").replaceAll("[^\\d]", "").trim());
        return totalEntries;
    }

    public void reptsPagination(DataTable data) throws Exception {

        cucTabledata = data.asLists(String.class);
        highLightElement(locateElementByXPath(featureWE));
        int totalPageNumb = Integer.parseInt(locateElementByXPath("" + featureWE + "//span[2]").getText().
                replaceAll("[^\\d]", "").trim());
        System.out.println("total number of the page = " + totalPageNumb);
        System.out.println("current entry number = " + currentEntries(showEntriesText));
        System.out.println("total number of entries = " + totalEntriesByXPath(showEntriesText));
        for (int i = 0; i <= 10; i++) {
            System.out.println("\nClicking " + i + " page\n");
            click(locateElementByXPath("" + featureWE + "//button[3]"));
            wait(50);
        }
        for (int i = 1; i <= 3; i++) {
            click(locateElementByXPath("//button[@id='applicationUserDetailReport_" + cucTabledata.get(0).get(i).toLowerCase() + "']"));
            System.out.println("\nClicking " + cucTabledata.get(0).get(i) + " button\n");
            wait(50);
        }
    }

    public void repTableColSorting(String report, String desc, String asc, DataTable cols) throws Exception {
        if (report.equalsIgnoreCase(USRDETS)) {
            appSumReptMeth.sortTabCols(desc, asc, cols);
        }
    }

    public static String colHdrNme(DataTable col) {
        String coln = null;
        for (List<String> list : col.asLists(String.class)) {
            if (list.contains(RID)) coln = "1";
            if (list.contains(AN)) coln = "2";
            if (list.contains(ARN)) coln = "3";
            if (list.contains(CA)) coln = "4";
            if (list.contains(RD)) coln = "5";
            if (list.contains(AAD)) coln = "6";
        }
        return coln;
    }


    public void navigateToUVReportTab(String title, String searchValue) throws Exception {
        scroll_Down(locateElementByXPath("//*[@class='row cicdim-report-table-padding'][2]"));
        String actSubtitle = locateElementByXPath("(//div[@class='cicdim-report-title report-heading'])[3]").getText();
        Assert.assertTrue(title.equalsIgnoreCase(actSubtitle));
        if (title.equalsIgnoreCase("Application Summary Report")) {
            searchBar("(//div[@class='row cicdim-report-table-padding'][2]//input)[1]", searchValue);
            clickWithJSE(locateElementByXPath("(//div[@class='row cicdim-report-table-padding'][2]//button)[1]"));
            scroll_Down(locateElementByXPath("//*[@class='row cicdim-report-table-padding'][2]"));
        } else {
            System.out.println();
        }
    }

    public void featuresDisplayingInNonPrtcntUsers(String string, String string2, DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        log.info(string + " page is loading");
        if (!string2.contains("2")) {
            actualText = locateEleByXPathContainsNormSpaceAttr("span", string).getText();
            Assert.assertTrue(actualText.contains(string));
        }
        highLightElement(locateElementByXPath(" //strong[contains(text(), '" + string2 + "')]"));
        dtInf = dataTable.asLists(String.class);
        for (cols = 0; cols < dtInf.size(); cols++) {
            System.out.println("Table Cols size= " + (dtInf.size()) + "\nTable Rows size " + (dtInf.get(0).size() - 1));
            for (rows = 1; rows <= dtInf.get(0).size() - 1; rows++) {
                if (dtInf.get(cols).get(rows).contains("-")) {
                    continue;
                } else {
                    if (dtInf.get(cols).get(rows).contains("Download") || dtInf.get(cols).get(rows).contains("Excel")) {
                        if (string2.contains("2")) {
                            highLightElement(locateElementByXPath("(//*[contains(text(), '" + dtInf.get(cols).get(rows) + "')])[2]"));
                        } else {
                            highLightElement(locateElementByXPath("(//*[contains(text(), '" + dtInf.get(cols).get(rows) + "')])[1]"));
                        }
                    }
                    if (dtInf.get(cols).get(rows).contains("Status")) {
                        highLightElement(locateElementByXPath("(//*[contains(text(), 'Status')])[2]"));
                    } else
                        highLightElement(locateElementByXPathContainsText(dtInf.get(cols).get(rows)));
                }
                wait(500);
            }
            wait(1000);
        }
    }

    public void scrollDownToReport(String string) {
        waitForCommonPageLoadingElements();
        switch (string) {
            case "Detailed History":
                subRep = "user-verification-history-report_wrapper";
                break;
            case "Values for Application":
                subRep = "customAttributeValuesReport_wrapper";
                break;
        }
        scroll_Into_View(locateElementByID(subRep));
    }

//    public void reportPageDisplayed(String repName, String repType) {
//        waitForCommonPageLoadingElements();
//        String we1 = null, we2 = null, we3 = null, we4 = null;
//        if (repType.equalsIgnoreCase(UVNPU)) {
//            we1 = "skipToICContent";
//            we2 = "strong";
//            we3 = "applicationHeader2";
//            we4 = "span";
//        }
//        locateElementsByTagName("//*[@id='" + we1 + "']//descendant::'" + we2 + "'").forEach(element -> {
//                    if (element.getText().contains(repType)) log.info("Report name is displayed in the page");
//                }
//        );
//        locateElementsByTagName("//*[@id='" + we3 + "']//descendant::'" + we4 + "'").forEach(element -> {
//                    if (element.getText().contains(repName)) log.info("Report name is displayed in the page");
//                }
//        );
//    }

    public void iterateThroughTheReports(String role, String application, DataTable reports) throws Exception {
        cucTabledata = reports.asLists(String.class);
        List<HashMap<String, String>> appsToUse = DataHelper.data("newApps.xlsx", "Reports");
        String envCol = env2;
        for (List<String> reportType : cucTabledata) {
            String REPORTNAME = reportType.get(0);
            String appToUse = application;
            for (HashMap<String, String> reportApps : appsToUse) {
                if (reportApps.get("REPORT_NAME").equals(REPORTNAME)) {
                    appToUse = reportApps.get(envCol);
                    break;
                }
            }
            waitForCommonPageLoadingElements();
            WebElement ele = wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(RepCenBanner)));
            if (ele == null) {
                log.warn("WARN: WORKAROUND: Report Center does not always load in a timely fashion");
                ele = longWait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(RepCenBanner)));
            }
            if (ele == null) {
                recoverBrowserAfterError();
                log.warn("WARN: WORKAROUND: Report Center does not always load the first time");
                ele = longWait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(RepCenBanner)));
            }
            if (ele == null)
                Assert.fail("ERROR: Report Center did not load correctly for Report: " + reportType.get(0));
            highLightElement(locateEleByXPathTextNormSpace(RepCenBanner));
            log.info("IC user role: " + role);
            log.info("REPORTNAME: " + REPORTNAME);
            log.info("App: " + appToUse);
            verifyReport(role, REPORTNAME, appToUse);
        }
    }

    public void countWebBasedUVRepEntries() throws Exception {
        numbsOfUVRepTotalEntries = totalEntriesByXPath("//div[@id='user-verification-history-report_info']");
        System.out.println("Expected total numbers of entries in the Report = " + numbsOfUVRepTotalEntries);
    }

    public void countCSVDownloadedRepsEntries(String csvFile) throws Exception {
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName(csvFile, driver));
        wait(2000);
        acualNumbersOfEntries = FileActions.getRecordsCountInCSV(csvFile);
        System.out.println("acualNumbersOfEntries = " + acualNumbersOfEntries);
        Assert.assertEquals("Downloaded file does not match with actual", numbsOfUVRepTotalEntries, acualNumbersOfEntries);
        detecteAndDeletedownloadedFile(csvFile);
    }

    public void selectAppReport(String appType, String viewBtn) throws Exception {
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@class='container']")));
        List<HashMap<String, String>> appsToUse = DataHelper.data("newApps.xlsx", "Reports");
        String envCol = environment;
        if (envCol.equalsIgnoreCase("VALAWS") || envCol.contains("SB")) {
            envCol = env2;
        }
        String appToUse = application;
        for (HashMap<String, String> reportApps : appsToUse) {
            if (reportApps.get("REPORT_NAME").equals(appType)) {
                appToUse = reportApps.get(envCol);
                break;
            }
        }
        System.out.println("appToUse = " + appToUse);
        highLightElement(locateElementByXPathText(appType));
        selectFromDropDownMenu(locateElementByXPath("//*[text() = '" + appType + "']//parent::div/following-sibling::div/select"), appToUse);
        click(locateElementByXPath("//*[text() = '" + appType + "']//parent::div/following-sibling::div/button"));
        waitForCommonPageLoadingElements();
    }

    public void repTableColumnsNameIsDisplayed(DataTable colDeadings) {
        cucTabledata = colDeadings.asLists(String.class);
        cucTabledata.get(0).forEach(name -> {
            wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpaceAttr("th", name)));
            isElementPresent(locateEleByXPathTextNormSpaceAttr("th", name));
        });
    }
}

