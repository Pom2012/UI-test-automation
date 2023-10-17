package com.model.stepDefinition.portalStepDef;

import com.model.pages.admConsole.CustAttrListLinkage;
import com.model.pages.prtlModule.PortalPages;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.base.Constants;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import com.model.locators.appConsoleLoc.*;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import com.model.locators.reportCenterLoc.Portal_ReportCenter_All_Locators;
import com.model.locators.supportCenterLoc.HelpDesk_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.base.Constants.IC_Roles.*;

public class rolesTilesLinks_StepDefinition extends BasePage {

    PortalPages portalPages =new PortalPages();
    CustAttrListLinkage custAttrListLinkFunc = new CustAttrListLinkage();
    Portal_MyPortal_Locators locators = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    ApplicationsManagement_Locators alocators = PageFactory.initElements(driver, ApplicationsManagement_Locators.class);
    Portal_ReportCenter_All_Locators reportLocator = PageFactory.initElements(driver, Portal_ReportCenter_All_Locators.class);
    HelpDesk_Locators supportLocator = PageFactory.initElements(driver, HelpDesk_Locators.class);
    Portal_AppConsole_Home_Locators appConsoleHome = PageFactory.initElements(driver, Portal_AppConsole_Home_Locators.class);
    Portal_AppConsole_RequestAccess_Locators appConsoleRequest = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
    Portal_AppConsole_ConfirmAccess_Locators appConsoleConfirm = PageFactory.initElements(driver, Portal_AppConsole_ConfirmAccess_Locators.class);
    Portal_AppConsole_DelegateAccess_Locators appConsoleDelegate = PageFactory.initElements(driver, Portal_AppConsole_DelegateAccess_Locators.class);
    Portal_AppConsole_Emails_Locators emailLoc = PageFactory.initElements(driver, Portal_AppConsole_Emails_Locators.class);


    @Given("^user is logged in as \"([^\"]*)\"$")
    public void user_is_logged_in_as(String expectedRole) throws Throwable {
        portalPages.icUserRoleVerification(expectedRole);
    }

    @And("^will see \"([^\"]*)\"link$")
    public void will_see_somethinglink(String strArg1) throws Throwable {
        click(locators.myProfileDropDownLink);
        isElementPresent(locators.myHelpDesk);
    }

    @And("^click CMS profile menu$")
    public void click_CMS_profile_menu() throws Throwable {
        click(locators.myProfileDropDownLink);
    }

    @And("^click My Access link$")
    public void click_My_Access_link() throws Throwable {
        click(locators.myAccessLink);
    }

    @And("^can see Add Application tile$")
    public void can_see_add_app_tile() {
        isElementPresent(locators.addAppsTile);
        highLightElement(locators.addAppsTile);
    }

    @Given("^click View Apps tile$")
    public void click_View_Apps_tile() {
        isElementPresent(locators.viewAppTile);
    }

    @And("^can see Business Intelligence tile$")
    public void can_see_business_intelligence_tile() throws Throwable {
        isElementPresent(locators.businessIntellTile);
        highLightElement(locators.businessIntellTile);
        click(locators.businessIntellTile);
    }

    @Then("^clicks on \"([^\"]*)\" in \"([^\"]*)\" tile and sees reports$")
    public void clicks_on_in_tile_and_sees_reports(String arg1, String arg2) throws Throwable {
        click(locators.businessIntellTile);
        click(locateElementByXPathText(arg1));
        driver.switchTo().frame(driver.findElement(By.tagName("object")));
        longWaitForElementAllowNull("mstrWeb_content", "id");
        isElementPresent(locateElementByID("mstrWeb_content"));
    }

    @Then("^clicks on \"([^\"]*)\" in \"([^\"]*)\" tile and views reports$")
    public void clicks_on_in_tile_and_views_reports(String arg1, String arg2) throws Throwable {
        click(locators.cpcDftTile);
        click(locateElementByXPathText(arg1));
        driver.switchTo().frame(driver.findElement(By.tagName("object")));
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("menuTabs")));
        isElementPresent(locateElementByID("menuTabs"));
        isElementPresent(locateElementByID("dktpSectionView"));
    }

    @Then("^\"([^\"]*)\" will see correct tile on the portal page$")
    public void something_will_see_correct_tile_on_the_portal_page(String role) {
        if (role.equalsIgnoreCase("Application Approver")) {
            verifyIfElementPartofIframeGetText(locators.ApprovalsTile);
        }
        if (role.equalsIgnoreCase(Constants.IC_Roles.HELPDESK)) {
            verifyIfElementPartofIframeGetText(locators.helpDeskTile);
        }
    }

    @Then("^\"([^\"]*)\" sees \"([^\"]*)\" after clicking \"([^\"]*)\"$")
    public void sees_after_clicking(String role, String content, String link) throws Throwable {
        System.out.println("The role is = " + role);
        switch (role) {
            case APPROVER:              //Innovation Center Application Approver
                if (link.equalsIgnoreCase("Report Center")) {
                    wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportLink));
                    getTextofWebElementSimple(reportLocator.lastAccessReportLink);
                    getTextofWebElementSimple(reportLocator.userAccessReportLink);
                    getTextofWebElementSimple(reportLocator.appSummaryText);
                    getTextofWebElementSimple(reportLocator.appUsageText);
                    getTextofWebElementSimple(reportLocator.customAttributeText);
                }
                if (link.equalsIgnoreCase("Application Console")) {
                    wait.until(ExpectedConditions.visibilityOf(appConsoleHome.homeTab));
                    click(appConsoleHome.homeTab);
                    isElementPresent(appConsoleHome.homeForm);
                    System.out.println("************************Verifying Request Access*********************");
                    System.out.println(" ");
                    scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                    click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                    scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                    isElementPresent(appConsoleRequest.requestAccessForm);
                    click(appConsoleRequest.requestNewAccessLink);
                    isElementPresent(appConsoleRequest.selectAppDropDown);

                    System.out.println("************************Verifying Confirm Access*********************");
                    System.out.println(" ");
                    click(appConsoleConfirm.confirmAccessTab);
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript("window.scrollBy(0, -2000);");
                    isElementPresent(appConsoleConfirm.confirmAccessForm);


                    System.out.println("************************Verifying Delegate Access*********************");
                    System.out.println(" ");
                    jse.executeScript("window.scrollBy(0, -2000);");

                    click(appConsoleDelegate.delegateAccessTab);
                    jse.executeScript("window.scrollBy(0, -2000);");
                    isElementPresent(appConsoleDelegate.delegateAccessForm);

                    System.out.println("************************Verifying EmailNotifications*********************");
                    System.out.println(" ");
                }
                break;
            case ADMIN_HD:              //Innovation Center Administrator + Innovation Center Helpdesk User
                if (link.equalsIgnoreCase("Administration Console")) {
                    verifyIfElementPartofIframe(alocators.cmmiAppMan);
                    highLightElement(alocators.cmmiHDLable);
                    wait.until(ExpectedConditions.visibilityOf(alocators.cmmiAppMan));
                    isElementPresent(alocators.cmmiAppMan);
                }
                if (link.equalsIgnoreCase("Report Center")) {
                    wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportLink));
                    getTextofWebElementSimple(reportLocator.lastAccessReportLink);
                    getTextofWebElementSimple(reportLocator.adminActivityLink);
                    getTextofWebElementSimple(reportLocator.listActivityLink);
                    getTextofWebElementSimple(reportLocator.appMonitoringLink);
                    getTextofWebElementSimple(reportLocator.privUserLink);
                    getTextofWebElementSimple(reportLocator.userAccessReportLink);
                    getTextofWebElementSimple(reportLocator.appSummaryText);
                    getTextofWebElementSimple(reportLocator.appUsageText);
                    getTextofWebElementSimple(reportLocator.customAttributeText);
                }
                if (link.equalsIgnoreCase("Support Center")) {
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.searchSubPage));

                    System.out.println("Assign role tab");
                    clickWithJSE(supportLocator.assignRoleTab);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.assignRoleSubpage));
                    isElementPresent(supportLocator.assignRoleSubpage);
                    /*         User Profile tab         */
                    System.out.println("User Profile tab");
                    clickWithJSE(supportLocator.userProfileTab);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.userProfSubPage));
                    isElementPresent(supportLocator.userProfSubPage);
                    /*         Warning State tab      */
                    System.out.println("Warning State tab");
                    clickWithJSE(supportLocator.warningTab);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.warningRun));
                    isElementPresent(supportLocator.warningRun);
                    /*         Email Sender tab         */
                    //Email Sender tab ->dev0
                    clickWithJSE(supportLocator.emailSender);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.emailSenderHeader));
                    isElementPresent(supportLocator.emailSenderHeader);
                    //Batch Operations tab
                    clickWithJSE(supportLocator.batchOperationsTab);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.batchOperSubPage));
                    isElementPresent(supportLocator.batchOperSubPage);
                    //Search Users tab
                    clickWithJSE(supportLocator.searchUsersTab);
                    wait.until(ExpectedConditions.visibilityOf(supportLocator.searchSubPage));
                    isElementPresent(supportLocator.searchSubPage);
                }
                break;
            case ADMIN:                 //Innovation Center Administrator
                if (link.equalsIgnoreCase("Administration Console")) {
                    wait.until(ExpectedConditions.visibilityOf(alocators.cmmiAppMan));
                    isElementPresent(alocators.cmmiAppMan);
                }
                if (link.equalsIgnoreCase("Report Center")) {
                    wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportLink));
                    getTextofWebElementSimple(reportLocator.lastAccessReportLink);
                    getTextofWebElementSimple(reportLocator.adminActivityLink);
                    getTextofWebElementSimple(reportLocator.listActivityLink);
                    getTextofWebElementSimple(reportLocator.appMonitoringLink);
                    getTextofWebElementSimple(reportLocator.privUserLink);
                    getTextofWebElementSimple(reportLocator.userAccessReportLink);
                    getTextofWebElementSimple(reportLocator.appSummaryText);
                    getTextofWebElementSimple(reportLocator.appUsageText);
                    getTextofWebElementSimple(reportLocator.customAttributeText);
                }
                break;
            case PV_REPORT_USER:        //Innovation Center Privileged User + Innovation Center Report User
                if (link.equalsIgnoreCase("Application Console")) {
                    isElementPresent(appConsoleHome.homeTab);
                    System.out.println("************************Verifying Request Access*********************");
                    System.out.println(" ");
                    click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                    scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
                    isElementPresent(appConsoleRequest.requestAccessForm);
                    click(appConsoleRequest.requestNewAccessLink);
                    isElementPresent(appConsoleRequest.requestNewAccessForm);
                    System.out.println("************************Verifying Confirm Access*********************");
                    System.out.println(" ");
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript("window.scrollBy(0, -2000);");
                    click(appConsoleConfirm.confirmAccessTab);
                    jse.executeScript("window.scrollBy(0, -2000);");
                    isElementPresent(appConsoleConfirm.confirmAccessForm);
                    System.out.println("************************Verifying EmailNotifications*********************");
                    System.out.println(" ");
                    ((JavascriptExecutor) driver).executeScript("scroll(0,-10000)");
                    click(emailLoc.emailTab);
                    isElementPresent(emailLoc.emailText);
                }
                break;
        }
    }

    // Custom Attribute Lists Linkage feature
    @Then("^navigates to the \"([^\"]*)\" page, and sees the table data and \"([^\"]*)\" button$")
    public void navigates_to_the_page_and_sees_the_table_data_and_button(String portletLabel, String feature) throws Throwable {
        scroll_Into_View(locateElementByXPathContainsText(portletLabel));
        highLightElement(locateElementByXPathContainsText(portletLabel));
        highLightElement(locateElementByXPath("//*[@class='container-fluid cicdim-report-outline ']"));
        highLightElement(locateElementByCSS("#list-group-view_filter > label > input"));
        highLightElement(locateElementByXPathText(feature));
    }

    @Then("^user clicks on \"([^\"]*)\" button and see the below:$")
    public void user_clicks_on_button_and_see_the_below(String featurebtn, DataTable lineData) throws Throwable {
        custAttrListLinkFunc.customAttributeListsLinkagePageFeatures(featurebtn, lineData);
    }

    @And("^clicks on \"([^\"]*)\" button and see the below:$")
    public void clicks_on_button_and_see_the_below(String featurebtn, DataTable lineData) throws Throwable {
        custAttrListLinkFunc.newUpdateCustAttrListsLinkPageFeatures(featurebtn,lineData);
     }

    @And("^clicks on Support Center link in Innovation Center$")
    public void clicks_on_Support_Center_link_in_Innovation_Center() throws Throwable {
        System.out.println("The user clicks on Support Center link in Innovation Center");
        switchToTheModule("Support Center");
    }

    @Then("^user navigates back, clicks \"([^\"]*)\" button and views \"([^\"]*)\" page elements$")
    public void user_navigates_back_clicks_button_and_views_page_elements(String editBtn, String lableElement) throws Throwable {
        click(locateElementByXPathText(" Back"));
        highLightElement(locateElementByXPathText(" Custom Attribute Lists Linkage "));
        click(locateElementByXPath("//table/tbody/tr[1]/td[5]/div/button"));
        highLightElement(locateElementByXPathText(lableElement));
        highLightElement(locateElementByCSS("#skipToContnet > div:nth-child(3)"));
        highLightElement(locateElementByCSS("#skipToContnet > div.actions"));
        click(locateElementByXPathText(" Back"));
    }

    @And("^will see Home tab$")
    public void will_see_home_tab() throws Throwable {
        System.out.println("************************Verifying Home Tab*********************");
        click(appConsoleHome.homeTab);
    }


    @And("^\"([^\"]*)\" will be taken to \"([^\"]*)\" after clicking \"([^\"]*)\"$")
    public void something_will_be_taken_to_something_after_clicking_something(String role, String page, String link) {
        if (link.equalsIgnoreCase("Administration Console")) {
            isElementPresent(alocators.cmmiAppMan);
        } else {
            System.out.println("Do not click on the link");
        }
    }

    @And("^\"([^\"]*)\" will see \"([^\"]*)\"$")
    public void something_will_see_something(String role, String content) throws Throwable {
        isElementPresent(appConsoleHome.homeTab);
        System.out.println("************************Verifying Request Access*********************");
        System.out.println(" ");
        click(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        scroll_Into_View(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        isElementPresent(appConsoleRequest.requestAccessForm);
        click(appConsoleRequest.requestNewAccessLink);
        isElementPresent(appConsoleRequest.selectAppDropDown);

        System.out.println("************************Verifying Confirm Access*********************");
        System.out.println(" ");
        click(appConsoleConfirm.confirmAccessTab);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, -2000);");
        isElementPresent(appConsoleConfirm.confirmAccessForm);

        System.out.println("************************Verifying Delegate Access*********************");
        System.out.println(" ");
        jse.executeScript("window.scrollBy(0, -2000);");
        click(appConsoleDelegate.delegateAccessTab);
        jse.executeScript("window.scrollBy(0, -2000);");
        isElementPresent(appConsoleDelegate.delegateAccessForm);

        System.out.println("************************Verifying Home Tab*********************");
        System.out.println(" ");
        click(appConsoleHome.homeTab);
    }

    @And("^can access role page for \"([^\"]*)\" application$")
    public void can_access_role_page_for_something_application(String appName) throws Throwable {
        ((JavascriptExecutor) driver).executeScript("scroll(0,-5000)");
        alocators.applSearchbox.sendKeys(appName);
        System.out.println(Model_CommonFunctions.readTable(appName, alocators.appTable, 5));
        wait.until(ExpectedConditions.visibilityOf(alocators.rolesTable));
        isElementPresent(alocators.rolesTable);
    }

    @And("^clicks on Report Center link in Innovation Center$")
    public void clicks_on_report_center_link() throws Throwable {
        wait(2000);
        switchToTheModule("Report Center");
    }

    @And("^clicks on Administration Console link in Innovation Center$")
    public void clicks_on_Administration_Console_link_in_Innovation_Center() throws Throwable {
        System.out.println("The user clicks on Administration Console link in Innovation Center");
        switchToTheModule("Administration Console");
    }


/* //TODO: DATA: Remove data-driven logic from Java
    @And("^\"([^\"]*)\" will click on \"([^\"]*)\" after clicking \"([^\"]*)\"$")
    public void will_click_on_something_after_clicking(String role, String content, String link) throws Throwable {
        //this legacy code
        if (role.equalsIgnoreCase(Constants.IC_Roles.APPROVER)) {
            wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportLink));
            reportLocator.lastAccessReportLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportTotal));
            scroll_Into_View(reportLocator.lastAccessReportTotal);
            String lastAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.lastAccessReportTotal);
            System.out.println("Last Accessed Report: " + lastAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.userAccessReportLink.click();
            wait(5000);//
            wait.until(ExpectedConditions.visibilityOf(reportLocator.userAccessReportTotal));
            scroll_Into_View(reportLocator.userAccessReportTotal);
            String userAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.userAccessReportTotal);
            System.out.println("User Access Report: " + userAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            //app Summary report
            // Commented due to the bug in download feature
            reportLocator.appSummaryDropDown.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appSummaryDropDown);
            reportLocator.appSummaryViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
            scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
            highLightElement(reportLocator.appSummUserRoleReportTotal);
            String appSummNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
            System.out.println("App Summary(User Role Details) Report: " + appSummNumOfRecords);

            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.appUsageText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appUsageDropDown);
            reportLocator.appUsageViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageReportTotal));
            scroll_Into_View(reportLocator.appUsageReportTotal);
            highLightElement(reportLocator.appUsageReportTotal);
            String appUsageNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appUsageReportTotal);
            System.out.println("App Usage Report: " + appUsageNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.customAttributeText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.custAttrDropDown);
            reportLocator.custAttrViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.custAttrReportTotal));
            scroll_Into_View(reportLocator.custAttrReportTotal);
            highLightElement(reportLocator.custAttrReportTotal);
            String custAttrNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.custAttrReportTotal);
            System.out.println("Custom Attribute Report: " + custAttrNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));
        }
        if (role.equalsIgnoreCase(Constants.IC_Roles.ADMIN_HD)) {
            //Last Access Report
            highLightElement(reportLocator.lastAccessReportLink);
            reportLocator.lastAccessReportLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportTotal));
            scroll_Into_View(reportLocator.lastAccessReportTotal);
            highLightElement(reportLocator.lastAccessReportTotal);
            String lastAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.lastAccessReportTotal);
            System.out.println("Last Accessed Report: " + lastAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            //Admin Activity Report
            reportLocator.adminActivityLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.adminActivityReportTotal));
            scroll_Into_View(reportLocator.adminActivityReportTotal);
            String adminActivityNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.adminActivityReportTotal);
            System.out.println("Admin Activity Report: " + adminActivityNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.listActivityLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.listActivityReportTotal));
            scroll_Into_View(reportLocator.listActivityReportTotal);
            highLightElement(reportLocator.listActivityReportTotal);
            String listActivityNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.listActivityReportTotal);
            System.out.println("List Activity Report: " + listActivityNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.appMonitoringLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appMonitICuserCountReportTotal));
            scroll_Into_View(reportLocator.appMonitICuserCountReportTotal);
            highLightElement(reportLocator.appMonitICuserCountReportTotal);
            String appMonitICUserNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appMonitICuserCountReportTotal);
            System.out.println("App Monitoring (IC User) Report: " + appMonitICUserNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.privUserLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.privUserReportTotal));
            scroll_Into_View(reportLocator.privUserReportTotal);
            highLightElement(reportLocator.privUserReportTotal);
            String privUserNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.privUserReportTotal);
            System.out.println("PV user Report: " + privUserNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.userAccessReportLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.userAccessReportTotal));
            scroll_Into_View(reportLocator.userAccessReportTotal);
            highLightElement(reportLocator.userAccessReportTotal);
            String userAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.userAccessReportTotal);
            System.out.println("User Access Report: " + userAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            //Application Reports
            reportLocator.appSummaryDropDown.click();
            selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appSummaryDropDown);
            reportLocator.appSummaryViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
            scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
            highLightElement(reportLocator.appSummUserRoleReportTotal);
            String appSummNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
            System.out.println("App Summary(User Role Details) Report: " + appSummNumOfRecords);
            reportLocator.downloadappSummLink.click();
//            String DownloadedRec = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
//            System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec);
//            System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("Application Summary Report.xlsx", driver));
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.appUsageText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appUsageDropDown);
            reportLocator.appUsageViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageReportTotal));
            scroll_Into_View(reportLocator.appUsageReportTotal);
            highLightElement(reportLocator.appUsageReportTotal);
            String appUsageNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appUsageReportTotal);
            System.out.println("App Usage Report: " + appUsageNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.customAttributeText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.custAttrDropDown);
            reportLocator.custAttrViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.custAttrReportTotal));
            scroll_Into_View(reportLocator.custAttrReportTotal);
            highLightElement(reportLocator.custAttrReportTotal);
            String custAttrNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.custAttrReportTotal);
            System.out.println("Custom Attribute Report: " + custAttrNumOfRecords);
            driver.navigate().back();
        }
        if (role.equalsIgnoreCase(ADMIN)) {
            //Last Access Report
            highLightElement(reportLocator.lastAccessReportLink);
            reportLocator.lastAccessReportLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.lastAccessReportTotal));
            scroll_Into_View(reportLocator.lastAccessReportTotal);
            highLightElement(reportLocator.lastAccessReportTotal);
            String lastAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.lastAccessReportTotal);
            System.out.println("Last Accessed Report: " + lastAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            //Admin Activity Report
            reportLocator.adminActivityLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.adminActivityReportTotal));
            scroll_Into_View(reportLocator.adminActivityReportTotal);
            String adminActivityNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.adminActivityReportTotal);
            System.out.println("Admin Activity Report: " + adminActivityNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.listActivityLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.listActivityReportTotal));
            scroll_Into_View(reportLocator.listActivityReportTotal);
            highLightElement(reportLocator.listActivityReportTotal);
            String listActivityNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.listActivityReportTotal);
            System.out.println("List Activity Report: " + listActivityNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.appMonitoringLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appMonitICuserCountReportTotal));
            scroll_Into_View(reportLocator.appMonitICuserCountReportTotal);
            highLightElement(reportLocator.appMonitICuserCountReportTotal);
            String appMonitICUserNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appMonitICuserCountReportTotal);
            System.out.println("App Monitoring (IC User) Report: " + appMonitICUserNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.privUserLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.privUserReportTotal));
            scroll_Into_View(reportLocator.privUserReportTotal);
            highLightElement(reportLocator.privUserReportTotal);
            String privUserNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.privUserReportTotal);
            System.out.println("PV user Report: " + privUserNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.userAccessReportLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.userAccessReportTotal));
            scroll_Into_View(reportLocator.userAccessReportTotal);
            highLightElement(reportLocator.userAccessReportTotal);
            String userAccessNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.userAccessReportTotal);
            System.out.println("User Access Report: " + userAccessNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            //Application Reports
            reportLocator.appSummaryDropDown.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appSummaryDropDown);
            reportLocator.appSummaryViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
            scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
            highLightElement(reportLocator.appSummUserRoleReportTotal);
            String appSummNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
            System.out.println("App Summary(User Role Details) Report: " + appSummNumOfRecords);
            reportLocator.downloadappSummLink.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDownlTotal));
            highLightElement(reportLocator.appSummaryDownlTotal);
            String DownloadedRec = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
            System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec);
            System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("Application Summary Report.xlsx", driver));
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.appUsageText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.appUsageDropDown);
            reportLocator.appUsageViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageReportTotal));
            scroll_Into_View(reportLocator.appUsageReportTotal);
            highLightElement(reportLocator.appUsageReportTotal);
            String appUsageNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appUsageReportTotal);
            System.out.println("App Usage Report: " + appUsageNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));

            reportLocator.customAttributeText.click();
            Model_CommonFunctions.selectDropDownOption_ByVisibleText("Old New Year Display", reportLocator.custAttrDropDown);
            reportLocator.custAttrViewBtn.click();
            wait.until(ExpectedConditions.visibilityOf(reportLocator.custAttrReportTotal));
            scroll_Into_View(reportLocator.custAttrReportTotal);
            highLightElement(reportLocator.custAttrReportTotal);
            String custAttrNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.custAttrReportTotal);
            System.out.println("Custom Attribute Report: " + custAttrNumOfRecords);
            clickWithJSE(locateElementByXPathText("Back"));
        }
    }

    //REVIEW: is this still needed?
    //this method created for VAL --> Feature: IC HD and Admin role can access Admin page, nine reports from Report Center, four tabs in Support Center
    @Given("^\"([^\"]*)\" will click on \"([^\"]*)\" after clicking \"([^\"]*)\", download the reports for \"([^\"]*)\"$")
    public void will_click_on_after_clicking_download_the_reports_for(String role, String content, String link, String appName) throws Throwable {
        reportLocator.appSummaryDropDown.click();
        Model_CommonFunctions.selectDropDownOption_ByVisibleText(appName, reportLocator.appSummaryDropDown);
        reportLocator.appSummaryViewBtn.click();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
        scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
        highLightElement(reportLocator.appSummUserRoleReportTotal);
        String appSummNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
        System.out.println("App Summary(User Role Details) Report: " + appSummNumOfRecords);
        reportLocator.downloadappSummLink.click();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDownlTotal));
        highLightElement(reportLocator.appSummaryDownlTotal);
        String DownloadedRec = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
        System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec);
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("Application Summary Report.xlsx", driver));
        clickWithJSE(locateElementByXPathText("Back"));


        reportLocator.appUsageText.click();
        Model_CommonFunctions.selectDropDownOption_ByVisibleText(appName, reportLocator.appUsageDropDown);
        reportLocator.appUsageViewBtn.click();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appUsageReportTotal));
        scroll_Into_View(reportLocator.appUsageReportTotal);
        highLightElement(reportLocator.appUsageReportTotal);
        String appUsageNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appUsageReportTotal);
        System.out.println("App Usage Report: " + appUsageNumOfRecords);
        clickWithJSE(locateElementByXPathText("Back"));

        reportLocator.customAttributeText.click();
        Model_CommonFunctions.selectDropDownOption_ByVisibleText(appName, reportLocator.custAttrDropDown);
        reportLocator.custAttrViewBtn.click();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.custAttrReportTotal));
        scroll_Into_View(reportLocator.custAttrReportTotal);
        highLightElement(reportLocator.custAttrReportTotal);
        String custAttrNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.custAttrReportTotal);
        System.out.println("Custom Attribute Report: " + custAttrNumOfRecords);
        clickWithJSE(locateElementByXPathText("Back"));
    }

    @Given("^user clicks on Application summary select dropdown and select \"([^\"]*)\" and clicks on View report button$")
    public void user_clicks_on_Application_summary_select_dropdown_and_select_and_clicks_on_View_report_button(String appName) throws Throwable {
        wait(2000);
        wait(2000);
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDropDown));
        highLightElement(reportLocator.appSummaryDropDown);
        reportLocator.appSummaryDropDown.click();
        System.out.println("The user selects " + appName);
        selectDropDownOption_ByVisibleText(appName, reportLocator.appSummaryDropDown);
        clickWithJSE(reportLocator.appSummaryViewBtn);
        System.out.println("The user clicked on View report button...>>>");
        //Showing 1 to 10 of ...... entries
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummUserRoleReportTotal));
        scroll_Into_View(reportLocator.appSummUserRoleReportTotal);
        highLightElement(reportLocator.appSummUserRoleReportTotal);
    }

    @Then("^user clicks on Download Report button and sees the Application Report is downloaded$")
    public void user_clicks_on_Download_Report_button_and_sees_the_Application_Report_is_downloaded() throws Throwable {
        String appSummNumOfRecords = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummUserRoleReportTotal);
        System.out.println("App Summary (User Role Details) Report: " + appSummNumOfRecords);
        reportLocator.downloadappSummLink.click();
        wait.until(ExpectedConditions.visibilityOf(reportLocator.appSummaryDownlTotal));
        highLightElement(reportLocator.appSummaryDownlTotal);
        String DownloadedRec = Model_CommonFunctions.getTextofWebElementSimple(reportLocator.appSummaryDownlTotal);
        System.out.println("Total downloaded for App Summary (User Role Details for Application) : " + DownloadedRec);
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("Application Summary Report.xlsx", driver));
        clickWithJSE(locateElementByXPathText("Back"));
    }
 */
}
