package com.model.locators.reportCenterLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_ReportCenter_All_Locators {


    @FindBy(how = How.XPATH, using = "cicdim-form-wrapper")
    public WebElement reportsTablePage;

    @FindBy(how = How.XPATH, using = "//a[@title='The Last Accessed report shows the most recent visit information of Innovation Center users.']")
    public WebElement lastAccessReportLink;

    @FindBy(how = How.XPATH, using = "//a[@title='The User Access report provides information of all users that have accessed the Innovation Center.']")
    public WebElement userAccessReportLink;

    @FindBy(how = How.XPATH, using = "//label[text()='Application Summary']")
    public WebElement appSummaryText;

    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Usage')]")
    public WebElement appUsageText;

    @FindBy(how = How.XPATH, using = "//label[text()='Custom Attribute']")
    public WebElement customAttributeText;

    @FindBy(how = How.XPATH, using = "//a[@title='The Administrator Activity report provides information of all the administrator activities performed in the past.']")
    public WebElement adminActivityLink;

    @FindBy(how = How.XPATH, using = "//a[@title='The List Activity report provides information of all the list activities performed in the past.']")
    public WebElement listActivityLink;

    @FindBy(how = How.XPATH, using = "//a[@title='The Application Monitoring report shows the number of approved users for Innovation Center users.']")
    public WebElement appMonitoringLink;

    @FindBy(how = How.XPATH, using = "//a[@title='The Privileged User Report displays information about Privileged Users']")
    public WebElement privUserLink;


    @FindBy(how = How.XPATH, using = "//a[@title='Back to Report Center main page']")
    public WebElement backLink;

    //Last Accessed Report
    @FindBy(how = How.XPATH, using = "//table[@id='last-access-report']")
    public WebElement lastAccessReportTable;
    @FindBy(how = How.XPATH, using = "//div[@id='last-access-report_info']")
    public WebElement lastAccessReportTotal;
    @FindBy(how = How.XPATH, using = "(//div[@class='row container-fluid cicdim-report-outline']//a)[6]")
    public WebElement downloadLastAccessReport;
    @FindBy(xpath = "//span[contains(text(), 'Processed records:')]")
    public WebElement lastAccessReportDownlTotal;


    //Admin Activity Report
    @FindBy(how = How.XPATH, using = "//table[@id='admin-actvty-report']//tbody")
    public WebElement adminActivityReportTableContent;

    @FindBy(how = How.XPATH, using = "//div[@id='admin-actvty-report_info']")
    public WebElement adminActivityReportTotal;
    //List Activity Report
    @FindBy(how = How.XPATH, using = "//div[@id='list-actvty-report_info']")
    public WebElement listActivityReportTotal;

    @FindBy(how = How.XPATH, using = "//div[@id='icUserCountDetails_info']")
    public WebElement appMonitICuserCountReportTotal;

    @FindBy(how = How.XPATH, using = "//div[@id='eidm-report_info']")
    public WebElement privUserReportTotal;

    //User Access Report
    @FindBy(how = How.XPATH, using = "//div[@id='user-access-report_info']")
    public WebElement userAccessReportTotal;

    @FindBy(how = How.XPATH, using = "(//div[@class='stControlBody stOverflowAuto wpthemeControlBody']//a)[8]")
    public WebElement downloadUserAccessReport;

    @FindBy(xpath = "//span[contains(text(), 'Processed records:')]")
    public WebElement userAccessReportDownlTotal;


    ////////////////////////////APP SUMMARY///////////////////////////////////

    @FindBy(xpath = "//select[@id='id']")
    public WebElement appSummaryDropDown;

    @FindBy(xpath = "//button[@title='The Application Summary Report provides a detailed report of all applications that are available on the Innovation Center. Select an Application in order to view.']")
    public WebElement appSummaryViewBtn;

    @FindBy(xpath = "//div[@id='applicationUserDetailReport_info']")
    public WebElement appSummUserRoleReportTotal;

    @FindBy(id = "cmmi-downloadReportLink")
    public WebElement detailDownLdRprtBnt;

    @FindBy(xpath = "(//*[@class='row container-fluid cicdim-report-outline']//a)[7]")
    public WebElement downloadappSummLink;

    @FindBy(xpath = "//span[contains(text(), 'Processed records:')]")
    public WebElement appSummaryDownlTotal;

    ////////////////////////////  APP USAGE ///////////////////////////////////

    @FindBy(xpath = "//select[@id='appid']")
    public WebElement appUsageDropDown;

    @FindBy(xpath = "//button[@title='The Application Usage Report provides a detailed user visit of all applications that are available on the Innovation Center. Select an Application in order to view.']")
    public WebElement appUsageViewBtn;

    @FindBy(xpath = "//div[@id='app-usage-report_info']")
    public WebElement appUsageReportTotal;

    @FindBy(xpath = "//a[@title='Export Application Usage Report in csv format']")
    public WebElement downloadappUsageLink;


    @FindBy(xpath = "(//div[@class='row container-fluid cicdim-report-outline']//a)[6]")
    public WebElement downloadappUsage;

    @FindBy(xpath = "//span[contains(text(), 'Processed records:')]")
    public WebElement appUsageDownloadTotal;

    ////////////////////////////  CUSTOM ATTR ///////////////////////////////////

    @FindBy(xpath = "(//select)[3]")
    public WebElement custAttrDropDown;

    @FindBy(xpath = "//button[@id='custIdSubmit']")
    public WebElement custAttrViewBtn;

    @FindBy(xpath = "//div[@id='customAttributeReport_info']")
    public WebElement custAttrReportTotal;

    @FindBy(xpath = "//a[@title='Export Custom Attribute Report in csv format']")
    public WebElement downloadcustAttrLink;

    @FindBy(xpath = "ns_0x002fcmmi_rp0x002fcmmi_report0x002f7_9G4004G0P8J170QQKOAANJ20J2_-downloadReportLinkTextMessage")
    public WebElement custAttrDownlTotal;

    ////////////////////////////USER INACTIVITY ///////////////////////////////////

    @FindBy(how = How.XPATH, using = "//label[text()='User Inactivity']")
    public WebElement userInactLabel;

    @FindBy(how = How.ID, using = "inactid")
    public WebElement userInactDropDown;

    @FindBy(how = How.ID, using = "inactIdSubmit")
    public WebElement userInactViewBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='email-sent-report_info']")
    public WebElement userInactReportTotal;

    @FindBy(how = How.XPATH, using = "//a[@title='Export User Inactivity Report in csv format']")
    public WebElement userInactAttrLink;

    @FindBy(how = How.ID, using = "ns_0x002fcmmi_rp0x002fcmmi_report0x002f7_5A9IH1G0POCPF0QEONBPL820S7_-downloadReportLinkTextMessage")
    public WebElement userInactDownlTotal;

    @FindBy(how = How.XPATH, using = "//a[text()='Users in the Warning Phase']")
    public WebElement userInactWarnTab;

    @FindBy(how = How.XPATH, using = "//a[text()='User Inactivity History']")
    public WebElement userInactHistTab;


    //      User Verification

    @FindBy(how = How.XPATH, using = "//label[text()='User Verification']")
    public WebElement userVerifLabel;

    @FindBy(how = How.ID, using = "userVerificationAppId")
    public WebElement userVerifDropDown;


    @FindBy(how = How.ID, using = "userVerificationSubmit")
    public WebElement userVerifViewBtn;



    @FindBy(how = How.XPATH, using = "//a[text()='User Inactivity History']")
    public WebElement manageAppTitle;


    @FindBy(xpath = "//a[@title='Export Administrator Activity Report in csv format']")
    public WebElement downloadAdminActRep;

    @FindBy(xpath = "//a[@title='Export User Access Report in csv format']")
    public WebElement downloadUserAccess;

    @FindBy(id = "cmmi-downloadReportLink")
    public WebElement downloadReport4UVBtn;

}
