package com.model.stepDefinition.appConStepDef;

import com.github.javafaker.Faker;
import com.model.pages.appConsole.ApplicationConsolePages;
import com.model.base.BasePage;
import com.model.locators.WelcomePage_Locators;
import com.model.locators.BasePage_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_Home_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;


public class ApplicationLaunch_StepDefinition extends BasePage {
    private final boolean iframeExist = true;
    public ApplicationConsolePages appConMeths;
    public Portal_AppConsole_Home_Locators myAccess;
    public WelcomePage_Locators welcome;
    public Portal_MyPortal_Locators myPortal;
    BasePage_Locators login;
    public static Faker faker = new Faker();

    public ApplicationLaunch_StepDefinition() {
        appConMeths = new ApplicationConsolePages();
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        myAccess = PageFactory.initElements(driver, Portal_AppConsole_Home_Locators.class);
        welcome = PageFactory.initElements(driver, WelcomePage_Locators.class);
        myPortal = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    }

    @And("clicks on {string} link")
    public void clicks_on_link(String portlet) throws Throwable {
        switchToTheModule(portlet);
    }

    @Then("user is on {string} application page")
    public void user_is_on_application_page(String appTileName) throws Throwable {
        appConMeths.getAccessAndVerifyApp(appTileName);
    }

    @And("clicks {string} application link")
    public void clicks_application_link(String appName) throws Exception {
        appConMeths.selectAppTile(appName);
    }

    @Then("selects {string} tile and views {string} and {string}")
    public void user_views_application_and_page(String appName, String appDisplayName, String addHeading) throws Exception {
        appConMeths.downstreamAppPageLoaded(appName, appDisplayName,addHeading);
    }

    @And("^clicks AHC application link$")
    public void clicks_AHC_application_link() throws Throwable {
        accessApplication(myAccess.AHC_Application);
    }

    @Then("^AHC application page is open$")
    public void AHC_application_page_is_open() {
        verifyApplicationHomePages(myAccess.AHC_Application, welcome.AHCWelcomePage, "ahc");
    }

    @And("^user clicks on and views the apps \"([^\"]*)\" and \"([^\"]*)\" pages$")
    public void user_clicks_on_and_views_the_apps_and_pages(String homePage, String additionalTab) throws Throwable {
        waitForCommonPageLoadingElements();
        isElementPresent(locateEleByXPathTextNormSpace(homePage));
        isElementPresent(locateEleByXPathTextNormSpace(additionalTab));
        highLightElement(locateEleByXPathTextNormSpace(homePage));
        highLightElement(locateEleByXPathTextNormSpace(additionalTab));
        click(locateEleByXPathTextNormSpace(homePage));
        waitForCommonPageLoadingElements();
        click(locateEleByXPathTextNormSpace(additionalTab));
    }


    @And("^click BPCI_Application link$")
    public void click_BPCI_Application_link() throws Throwable {
        accessApplication(myAccess.BPCI_Application);
    }

    @Then("^BPCI application page is open$")
    public void BPCI_application_page_is_open() {
        verifyApplicationHomePages(myAccess.BPCI_Application, welcome.BPCIWelcomePage, "bpci");
    }

    @And("^click CDX_Application link$")
    public void click_CDX_Application_link() throws Throwable {
        accessApplication(myAccess.CDX_Application);
    }

    @Then("^CDX application page is open$")
    public void CDX_application_page_is_open() {
        verifyApplicationHomePages(myAccess.CDX_Application, welcome.CDXWelcomePage, "cdx", iframeExist);
    }

    @And("^click CJR_Application link$")
    public void click_CJR_Application_link() throws Throwable {
        accessApplication(myAccess.CJR_Application);
    }

    @Then("^CJR application page is open$")
    public void CJR_application_page_is_open() {
        verifyApplicationHomePages(myAccess.CJR_Application, welcome.CJRWelcomePage, "cjr");
    }

    @And("^click CPCplus_Application link$")
    public void click_CPCplus_Application_link() throws Throwable {
        accessApplication(myAccess.CPCplusApplication);
        accessApplication(myAccess.CPCplusLink);
    }

    @Then("^CPCplus application page is open$")
    public void CPCplus_application_page_is_open() {
        verifyApplicationHomePages(myAccess.CPCplusApplication, welcome.CPCplWelcomePage, "cpc");
    }

    @And("^navigates back$")
    public void navigates_back() {
        driver.navigate().back();
        System.out.println("Navigated back");
    }

    @And("^click EDFR_Application link$")
    public void click_EDFR_Application_link() throws Throwable {
        accessApplication(myAccess.EDFR_Application);
    }

    @Then("^EDFR application page is open$")
    public void EDFR_application_page_is_open() {
        verifyApplicationHomePages(myAccess.EDFR_Application, welcome.eDFRWelcomePage, "edfr", iframeExist);
    }


    @And("^click EnhMTM_Application link$")
    public void click_EnhMTM_Application_link() throws Throwable {
        accessApplication(myAccess.Enh_MTMApplication);
        accessApplication(myAccess.Enh_MTMlink);
    }

    @Then("^EnhMTM application page is open$")
    public void EnhMTM_application_page_is_open() {
        verifyApplicationHomePages(myAccess.Enh_MTMApplication, welcome.Enh_MTMRWelcomePage, "mtm");
    }
    @And("^click ET3_Application link$")
    public void click_ET3_Application_link() throws Throwable {
        accessApplication(myAccess.ET3_Application);
    }

    @Then("^ET3 application page is open$")
    public void ET3_application_page_is_open() {
        wait(2000);
        verifyApplicationHomePages(myAccess.ET3_Application, welcome.ET3WelcomePage, "et3");
    }

    @And("^click HDR_Application link$")
    public void click_HDR_Application_link() throws Throwable {
        wait(2000);
        accessApplication(myAccess.HDR_Application);
    }

    @Then("^HDR application page is open$")
    public void HDR_application_page_is_open() {
        wait(3000);
        verifyApplicationHomePages(myAccess.HDR_Application, welcome.HDRWelcomePage, "hdr", iframeExist);
    }

    @And("^click HHVBP_Application link$")
    public void click_HHVBP_Application_link() throws Throwable {
        accessApplication(myAccess.HHVB_Application);
    }

    @Then("^HHVBP application page is open$")
    public void HHVBP_application_page_is_open() {
        verifyApplicationHomePages(myAccess.HHVB_Application, welcome.HHVBPWelcomePage, "hhvbp");
    }

    @And("^click HPI_Application link$")
    public void click_HPI_Application_link() throws Throwable {
        accessApplication(myAccess.HPI_Application);
    }

    @Then("^HPI application page is open$")
    public void HPI_application_page_is_open() {
        verifyApplicationHomePages(myAccess.HPI_Application, welcome.HPIWelcomePage, "hpi");
    }

    @And("^click InCK_Application link$")
    public void click_InCK_Application_link() throws Throwable {
        accessApplication(myAccess.InCK_Application);
    }

    @Then("^InCK application page is open$")
    public void InCK_application_page_is_open() {
        verifyApplicationHomePages(myAccess.InCK_Application, welcome.InCKWelcomePage, "inck");
    }

    @And("^click AHC_Application link$")
    public void click_AHC_Application_link() throws Throwable {
        accessApplication(myAccess.AHC_Application);
    }

    @And("^click IPC_Application link$")
    public void click_IPC_Application_link() throws Throwable {
        accessApplication(myAccess.IPC_Application);
    }

    @And("^click MDPCP_Application link$")
    public void click_MDPCP_Application_link() throws Throwable {
        scroll_Into_View(myAccess.MDPCP_Application);
        accessApplication(myAccess.MDPCP_Application);
    }

    @Then("^MDPCP application page is open$")
    public void MDPCP_application_page_is_open() {
        verifyApplicationHomePages(myAccess.MDPCP_Application, welcome.MDPCPWelcomePage, "mdpcp", iframeExist);
    }

    @And("^click MOM_Application link$")
    public void click_MOM_Application_link() throws Throwable {
        accessApplication(myAccess.MOM_Application);
    }

    @And("^click OCM_Plus_Application link$")
    public void click_OCM_Plus_Application_link() throws Throwable {
        accessApplication(myAccess.OCM_Plus_Application);
    }

    @Then("^OCM_Plus application page is open$")
    public void OCM_Plus_application_page_is_open() {
        verifyApplicationHomePages(myAccess.OCM_Plus_Application, welcome.OCM_PlusWelcomePage, "omcp");
    }

    @And("^click PCFsip_Application link$")
    public void click_PCFsip_Application_link() throws Throwable {
        accessApplication(myAccess.PCF_SIPApplication);
    }

    @Then("^PCFsip application page is open$")
    public void PCFsip_application_page_is_open() {
        verifyApplicationHomePages(myAccess.PCF_SIPApplication, welcome.PCFWelcomePage, "pcf");
    }

    @And("^click RO_Application link$")
    public void click_RO_Application_link() throws Throwable {
        accessApplication(myAccess.ROM_Application);
    }

    @Then("^RO application page is open$")
    public void RO_application_page_is_open() {
        verifyApplicationHomePages(myAccess.ROM_Application, welcome.ROMWelcomePage, "ro");
    }

    @And("^click ICSampleApp_Application link$")
    public void click_ICSampleApp_Application_link() throws Throwable {
        accessApplication(myAccess.SAPT4_Application);
    }


    @Then("^ICSampleApp application page is open$")
    public void ICSampleApp_application_page_is_open() {
        verifyApplicationHomePages(myAccess.SAPT4_Application, welcome.SAPT4WelcomePage, "icapp4");
    }

    @And("^click CPCBIR_Application link$")
    public void click_CPCBIR_Application_link() throws Throwable {
        accessApplication(myAccess.CPCBIR_Application);
    }

    @Then("^CPCBIR application page is open$")
    public void CPCBIR_application_page_is_open() {
        verifyApplicationHomePages(myAccess.CPCBIR_Application, welcome.SharedReportsLink, "cpcreports", iframeExist);
    }

    @And("^click ICBIR_Application link$")
    public void click_ICCBIR_Application_link() throws Throwable {
        accessApplication(myAccess.ICBIR_Application);
    }

    @Then("^ICBIR application page is open$")
    public void ICBIR_application_page_is_open() {
        driver.switchTo().frame(0);
        verifyApplicationHomePages(myAccess.ICBIR_Application, welcome.SharedReportsLink, "microstrategyreports", iframeExist);
    }

    @And("^click MDPCPBIR_Application link$")
    public void click_MDPCPBIR_Application_link() throws Throwable {
        accessApplication(myAccess.MDPCPBIR_Application);
    }


    @Then("^MDPCPBIR application page is open$")
    public void mdpcpbir_application_page_is_open() {
        verifyApplicationHomePages(myAccess.MDPCPBIR_Application, welcome.SharedReportsLink, "mdpcpwbireports", iframeExist);
    }

    @And("^click QMATR_Application link$")
    public void click_QMATR_Application_link() throws Throwable {
        accessApplication(myAccess.QMATR_Application);
    }

    @Then("^QMATR application page is open$")
    public void qmatr_application_page_is_open() {
        verifyApplicationHomePages(myAccess.QMATR_Application, welcome.SharedReportsLink, "icqmatreports", iframeExist);
    }

    @And("^click QMAT_Application link$")
    public void click_QMAT_Application_link() throws Throwable {
        accessApplication(myAccess.QMAT_Application);
    }

    @Then("^QMAT application page is open$")
    public void qmat_application_page_is_open() {
        verifyApplicationHomePages(myAccess.QMAT_Application, welcome.QMATWelcomePage, "qmat");
    }

    @And("^click RFAA_Application link$")
    public void click_RFAA_Application_link() throws Throwable {
        accessApplication(myAccess.RFAA_Application);
    }

    @Then("^RFAA application page is open$")
    public void RFAA_application_page_is_open() {
        verifyApplicationHomePages(myAccess.RFAA_Application, welcome.RFAAWelcomePage, "rfadmin");
    }

    @And("^click Shared Reports link$")
    public void click_Shared_Reports_link() throws Throwable {
        verifyIfElementPartofIframe(welcome.SharedReportsLink);
    }

    @And("^click Practice Information Reports link$")
    public void click_Practice_Information_Reports_link() throws Throwable {
        verifyIfHoverOverElementPartofIframe(welcome.infoReportsHoverOver, welcome.infoReports);
    }

    @And("^click Practice Reporting Reports Count link$")
    public void click_Practice_Reporting_Reports_Count_link() throws Throwable {
        verifyIfHoverOverElementPartofIframe(welcome.PractRepReportsCountHoverOver, welcome.PractRepReportsCount);
    }

    @And("^select \"([^\"]*)\" value from table$")
    public void select_value_from_table(String value) {
        verifyIfTablePartofIframe(value, welcome.yearQuaterTable);
    }

    @And("^clicks the Run Report button$")
    public void clicks_the_Run_Report_button() throws Throwable {
        verifyIfElementPartofIframe(welcome.runReport);
    }

    @And("^clicks the Run Document button$")
    public void clicks_the_Run_Document_button() throws Throwable {
        verifyIfElementPartofIframe(welcome.runDocument);
    }

    @And("^clicks the CPCBIR Add button$")
    public void clicks_the_CPCBIR_Add_button() throws Throwable {
        verifyIfElementPartofIframe(welcome.addCPCBIR);
    }

    @And("^clicks the QMATR Add button$")
    public void clicks_the_QMATR_Add_button() throws Throwable {
        verifyIfElementPartofIframe(welcome.addQMATR);
    }

    @And("^click Application User Usage Statistics link$")
    public void click_Application_User_Usage_Statistics_link() throws Throwable {
        verifyIfHoverOverElementPartofIframe(welcome.userUsageStat, welcome.userUsageStatHoverOver);
    }

    @And("^click Practice Reporting Reports link$")
    public void click_Practice_Reporting_Reports_link() throws Throwable {
        verifyIfHoverOverElementPartofIframe(welcome.PractRepReportsHoverOver, welcome.PractRepReports);
    }

    @And("^click Staff Roster Details link$")
    public void click_Staff_Roster_Details_link() throws Throwable {
        verifyIfHoverOverElementPartofIframe(welcome.rosterDetailsHoverOver, welcome.rosterDetails);
    }

    @And("^click User Activity Report link$")
    public void click_User_Activity_Report_link() throws Throwable {
        verifyIfElementPartofIframeScroll(welcome.userActivity);
        verifyIfHoverOverElementPartofIframe(welcome.userActivityHoverOver, welcome.userActivity);
    }

    @And("^clicks on a button to access portal page$")
    public void clicks_on_a_button_to_access_portal_page() throws Throwable {
        if (isElementPresent(welcome.portalLink)) {
            click(welcome.portalLink);
        } else if (isElementPresent(welcome.portalLinkCMS)) {
            click(welcome.portalLinkCMS);
        }
    }

    @Then("^Logout$")
    public void logout() throws Throwable {
        appConMeths.logOut();
    }

}
