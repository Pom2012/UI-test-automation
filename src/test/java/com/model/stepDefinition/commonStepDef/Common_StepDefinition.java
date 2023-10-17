package com.model.stepDefinition.commonStepDef;

import com.model.pages.admConsole.AdmAppRoleCustAttr;
import com.model.pages.admConsole.AdminCenterPages;
import com.model.pages.appConsole.ApplicationConsolePages;
import com.model.pages.prtlModule.PortalPages;
import com.model.pages.supCntr.AssignRoleTab;
import com.model.pages.supCntr.SupportCenterModule;
import com.model.base.BasePage;
import com.model.locators.WelcomePage_Locators;
import com.model.locators.PortalUnavailable_Locators;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_ConfirmAccess_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_DelegateAccess_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_Home_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyAccess_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import com.model.locators.reportCenterLoc.Portal_ReportCenter_All_Locators;
import com.model.locators.supportCenterLoc.HelpDesk_Locators;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Common_StepDefinition extends BasePage {
    WelcomePage_Locators welcome;
    PortalUnavailable_Locators noPortal;
    Portal_MyPortal_Locators locators;
    ApplicationsManagement_Locators alocators;
    Portal_MyAccess_Locators accessLocators;
    Portal_ReportCenter_All_Locators reportLocator;
    HelpDesk_Locators hlocators;
    Portal_AppConsole_Home_Locators appConsoleHome;
    Portal_AppConsole_RequestAccess_Locators appConsoleRequest;
    Portal_AppConsole_ConfirmAccess_Locators appConsoleConfirm;
    Portal_AppConsole_DelegateAccess_Locators appConsoleDelegate;
    AdminCenterPages adminCenter = new AdminCenterPages();
    AdmAppRoleCustAttr adminRoleCA = new AdmAppRoleCustAttr();
    PortalPages portalPages = new PortalPages();
    AssignRoleTab assignRoleMeth = new AssignRoleTab();
    ApplicationConsolePages appConMeths = new ApplicationConsolePages();
    SupportCenterModule supCentTabs = new SupportCenterModule();

    public Common_StepDefinition() {
        welcome = PageFactory.initElements(driver, WelcomePage_Locators.class);
        noPortal = new PortalUnavailable_Locators();
        locators = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
        alocators = PageFactory.initElements(driver, ApplicationsManagement_Locators.class);
        accessLocators = PageFactory.initElements(driver, Portal_MyAccess_Locators.class);
        reportLocator = PageFactory.initElements(driver, Portal_ReportCenter_All_Locators.class);
        hlocators = PageFactory.initElements(driver, HelpDesk_Locators.class);
        appConsoleHome = PageFactory.initElements(driver, Portal_AppConsole_Home_Locators.class);
        appConsoleRequest = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
        appConsoleConfirm = PageFactory.initElements(driver, Portal_AppConsole_ConfirmAccess_Locators.class);
        appConsoleDelegate = PageFactory.initElements(driver, Portal_AppConsole_DelegateAccess_Locators.class);
    }

    @And("^clicks My Portal link$")
    public void clicks_My_portal_link() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cms-homepage-header-logo-auth")));
        locators.myPortalLink.click();
    }


    @Given("^TestName \"([^\"]*)\" the landing page is loaded$")
    public void testname_the_landing_page_is_loaded(String testName) {
        BasePage.testName = testName;
        openPortal(testName);
    }


    @Then("{string} page is loaded")
    public void page_is_loaded(String pageHeader) {
        if (pageHeader.contains("Applications")) appConMeths.applicationConsolePageIsLoaded(pageHeader);
        if (pageHeader.contains("Helpdesk")) supCentTabs.supCentPageIsLoaded(pageHeader);
    }

    @And("^clicks back browser button$")
    public void clicks_back_browser_button() {
        driver.navigate().refresh();
        wait(5000);
        driver.navigate().back();
        driver.findElement(By.name("menu")).click();
        System.out.println("Clicked Back browser button");
        wait(2000);
    }

    @And("^clicks \"([^\"]*)\" radio button$")
    public void clicks_link(String text) throws Throwable {
        click_bytext(text);
    }

    @Given("^user is on \"([^\"]*)\" page$")
    public void user_is_on_something_page(String pagetitle) throws Throwable {
        if (pagetitle.contains("Manage Applications")) adminCenter.verifyManageAppPage(pagetitle);
        if (pagetitle.contains("Custom Attribute")) adminRoleCA.verifyCustomAttrPage(pagetitle);
    }

    @And("^clicks the \"([^\"]*)\" button by title again$")
    public void clicks_the_something_button_by_title_again(String strArg1) throws Throwable {
        if (isElementPresent("//a[@id='cms-viewallapps-maintile']", "xpath")) {
            System.out.println("User is on main portal page. Let's click on a tile");
        } else {
            click_byTitle("CMS Enterprise Portal");
        }
    }

    @And("^views \"([^\"]*)\" title and pages$")
    public void views_title_and_pages(String titleName) {
//ENH: does nothing        verifyTitleByText(titleName);
    }

    @And("clicks on {string} button")
    public void clicks_on_button(String btnText) throws Exception {
        BasePage.button = btnText;
        clickOnButton(btnText);
    }

    @Then("user is on the Innovation Center's {string} module page")
    public void user_is_on_the_innovation_center_module_page(String expectedICModuleTitleName) {
        moduleTitleNameVerification(expectedICModuleTitleName);
    }

    public void clickOnButton(String btnText) throws Exception {
        if (btnText.equalsIgnoreCase("Assign")) {
            wait(2000);
            click((locateEleByXPathTextNormSpace(btnText)));
        } else if (btnText.equalsIgnoreCase("Verify")) {
            click(locateElementByXPath("//button[normalize-space()='" + btnText + "']"));
        } else if (btnText.equalsIgnoreCase("Add Attribute List")) {
            click(locateElementByCSS("button[type='submit']"));
        } else if (btnText.equalsIgnoreCase("Add New Attribute")
                || btnText.equalsIgnoreCase("Add Attribute")) {
            click(locateElementByXPath("//a[contains(normalize-space(), '" + btnText + "')]"));
        } else {
            click(locateEleByXPathTextNormSpace(btnText));
        }
        waitForCommonPageLoadingElements();
    }

    @And("^clicks on \"([^\"]*)\" tab$")
    public void clicks_on_tab(String arg1) throws Throwable {
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByXPathText(arg1));
        click(locateElementByXPathText(arg1));
    }

    @When("clicks on IC tile and selects an IC role related {string}")
    public void clicks_on_ic_tile_and_selects_an_ic_role_related_modules(String portlet) throws Exception {
        switchToTheModule(portlet);
    }

    @When("opens a new window tabs, enters the below url links and views {string}:")
    public void opens_a_new_window_tabs_enters_the_below_url_links_and_views(String systemMessage, DataTable dataTable) {
        portalPages.verifyIcLinks(systemMessage, dataTable);
    }

    @When("sets the below information:")
    public void sets_the_below_information(DataTable dataTable) throws Throwable {
        assignRoleMeth.assignSingleRole(dataTable);
    }
}
