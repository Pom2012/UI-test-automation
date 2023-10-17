package com.model.pages.appConsole;

import com.model.utility.Model_CommonFunctions;
import com.model.utility.Screenshot;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_ConfirmAccess_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.base.Constants.ApplicationConsoleTabs.*;
import static com.model.base.Constants.IC_Servlets.AppCon;
import static com.model.base.Constants.ModelApplications.CDX;
import static com.model.base.Constants.RequestStatusTabs.allTab;

public class ApplicationConsolePages extends BasePage {
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    public Portal_AppConsole_ConfirmAccess_Locators ConfirmAccess_Locators;
    public final String acModuleRootWE = "#cmmi-ac";
    public String raSrchBxElID = "raSearchField";
    public String raSrchBtnElID = "raSearchButton";
    public String caSrchBxElID = "caSearchField";
    public String caSrchBtnElID = "caSearchIcon";
    public String reqNumbMsgRA = "message-ra-view";
    public String reqNumbMsgCA = "message-ca-view";
    static String reqConfAccWE = "@id='reqtable-frame-";
    protected String winDialBox = "[class='cdk-overlay-pane'] mat-dialog-container";
    private static final String reqStatusTabsRootWE = "//uib-tab-heading[@class='ng-scope']";

    public ApplicationConsolePages() {
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
        this.ConfirmAccess_Locators = new Portal_AppConsole_ConfirmAccess_Locators();
        PageFactory.initElements(driver, ConfirmAccess_Locators);
    }

    public void selectAppTile(String appName) throws Exception {
        if (!env2.equalsIgnoreCase("SB") && !appName.equalsIgnoreCase("CDX") ||
                !env2.equalsIgnoreCase("SB") && !appName.equalsIgnoreCase("HDR") ||
                !env2.equalsIgnoreCase("SB") && !appName.equalsIgnoreCase("eDFR")) {
            accessApplication(locateElementByXPath("//div[@class='uc-app-acronym-holder']//span[text()='" + selectICApp(appName) + "']"));
        }
    }

    public static String reqRootWE(String apptab) {
        return "[" + reqConfAccWE + req_conf(apptab) + "']";
    }

    public void appConsTabsTitleNameVerification(String string) {
        WebElement element = locateElementByCSS(acModuleRootWE + " h1");
        wait.until(ExpectedConditions.textToBePresentInElement(element, string));
        String str = locateElementByCSS(acModuleRootWE + " h1").getText();
        Assert.assertTrue(str.contains(string));
    }

    public void applicationTileIsPresentInHomePage(String appName) {
        List<WebElement> appList = locateElementsByXPath("//*[@id='consoleHome']//div[@class='uc-app-name ng-binding']");
        appList.forEach(p -> {
                    if (p.getText().contains(appName)) {
                        highLightElement(p);
                        verifyActualText(appName, p.getText());
                    }
                }
        );
        isElementPresent(locateEleByXPathTextNormSpaceAttr("div", appName));
    }

    public static void appConsTabsVerification(DataTable cucTabTable) {
        cucTabledata = cucTabTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        waitForAppearance(By.id("cmmi-ac"));
        Assert.assertTrue(locateElementByID("cmmi-ac").isDisplayed());
        String appConsoleTab;
        for (List<String> cucTabledatum : cucTabledata) {
            appConsoleTab = cucTabledatum.get(0);
            isElementPresent(locateElementByCSS("li[heading='" + appConsoleTab + "'] a[class='ng-binding']"));
        }
    }

    public static void appConsTabsPagesVer(String string, DataTable cucTabTable) throws Exception {
        cucTabledata = cucTabTable.asLists(String.class);
        String appConsoleTab;
        for (List<String> cucTabledatum : cucTabledata) {
            appConsoleTab = cucTabledatum.get(0);
            System.out.println("Application console tab: " + appConsoleTab);
            highLightElement(locateElementByCSS("li[heading='" + appConsoleTab + "'] a[class='ng-binding']"));
            clickWithJSE(locateElementByCSS("li[heading='" + appConsoleTab + "'] a[class='ng-binding']"));
            waitForCommonPageLoadingElements();
            wait(800);
        }
    }

    public static void appConsoleTabsNameVerification(String appTabs) {
        String actText = "";
        try {
            if (!appTabs.equalsIgnoreCase("Home")) {
                actText = waitForExpectedElement(locateElementByCSS("#" + modifyAppConsHeader(appTabs) + "View>div>h1>b")).getText();
            } else {
                actText = waitForExpectedElement(locateElementByCSS("#ac" + modifyAppConsHeader(appTabs) + " div>h1>b")).getText();
            }
        } catch (StaleElementReferenceException e) {
            e.getStackTrace();
        }
        verifyActualText(actText, appTabs);
    }

    public static void reqStatusTabsInReqAccVerification(String appConTab, DataTable cucTabTable) throws Exception {
        cucTabledata = cucTabTable.asLists(String.class);
        appConsoleTabsNameVerification(appConTab);
        String actualReqStatusTabValue;
        for (int i = 0; i < cucTabledata.size(); i++) {
            waitForCommonPageLoadingElements();
            actualReqStatusTabValue = cucTabledata.get(i).get(0);
            System.out.println(cucTabledata.size() + " request status tabs are appeared");
            if (actualReqStatusTabValue.equalsIgnoreCase(allTab)) continue;
            try {
                click(locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]"));
            } catch (ElementClickInterceptedException e) {
                e.getMessage();
            }
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("reqtable-frame-ra")));
            String ExpectedReqStatusValue = locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]").getText();
            highLightElement(locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]/span[1]"));
            System.out.println("ExpectedReqStatusValue = " + ExpectedReqStatusValue);
            getRequestNumbersFromReqStatusTabs(appConTab, actualReqStatusTabValue);
        }
    }

    public static void reqStatusTabsInConfAccVerification(String appConTab, DataTable cucTabTable) throws Exception {
        cucTabledata = cucTabTable.asLists(String.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//h1[@class='acHeader']/b")));
        } catch (StaleElementReferenceException e) {
            e.getStackTrace();
        }
        appConsoleTabsNameVerification(appConTab);
        String actualReqStatusTabValue;
        for (int i = 0; i < cucTabledata.size(); i++) {
            wait(500);
            actualReqStatusTabValue = cucTabledata.get(i).get(0);
            System.out.println(cucTabledata.size() + " request status tabs are appeared");
            try {
                click(locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]"));
            } catch (ElementClickInterceptedException e) {
                e.getMessage();
            }
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("reqtable-frame-ca")));
            String ExpectedReqStatusValue = locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]").getText();
            System.out.println("ExpectedReqStatusValue = " + ExpectedReqStatusValue);
            highLightElement(locateElementByXPath("" + reqStatusTabsRootWE + "[contains(text(),'" + actualReqStatusTabValue + "')]/span[1]"));
            getRequestNumbersFromReqStatusTabs(appConTab, actualReqStatusTabValue);
        }
    }

    public static void getRequestNumbersFromReqStatusTabs(String appConTab, String stsNumbs) {
        String reqResText;
        int reqNumbers;
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("message-" + req_conf(appConTab) + "-view")));
        highLightElement(locateElementByID("message-" + req_conf(appConTab) + "-view"));
        reqResText = locateElementByID("message-" + req_conf(appConTab) + "-view").getText();
        reqNumbers = Integer.parseInt(reqResText.replaceAll("[^\\d]", ""));
        System.out.println("request numbers= " + reqNumbers + " for " + stsNumbs);
    }

    public static int getRequestsNumberFromResult(String appConTab, String stsNumbs) {
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("message-" + req_conf(appConTab) + "-view")));
        return Integer.parseInt(locateElementByID("message-" + req_conf(appConTab) + "-view")
                .getText().replaceAll("[^\\d]", ""));
    }

    public void simpleCancelRequest(String justification) throws Exception {
        click(RequestAccess_Locators.cancelBtn);
        isElementPresent(RequestAccess_Locators.cancelReqDialogBox);
        type(RequestAccess_Locators.justificationTextBoxReq, justification);
        RequestAccess_Locators.okBtn.click();
        handleAlert();
    }

    public WebElement verifyAppIsPresent(String appTileName) {
//TODO: This may need rethought if the Multiple-per-env, ex. _VAL1 or  - DEV1 , links are still relevant
        WebElement appLink = null;
        int retries = 10;
        while (retries > 0) {
            try {
                waitForCommonPageLoadingElements();
                appLink = locateElementByXPath("//a[contains(@title, '" + appTileName + "')]");
            } catch (Exception ignored) {
            }
            if (appLink != null) break;
            wait(2000);
            retries--;
        }
        if (appLink == null) Assert.fail("ERROR: APP NOT FOUND: as a App Con tile: " + appTileName);
        System.out.println(appTileName + " is present in the CMS Innovation Center home page");
        verifyActualTextContainsExpectedAsSubstring(appLink.getText(), appTileName);
        return appLink;
    }

    public void getAccessAndVerifyApp(String appTileName) throws Exception {
        switch (appTileName) {
            case "HDR":
                isElementPresent(locateElementByXPath("//span[normalize-space()='Health Data Reporting']"));
                break;
            case "CDX":
                isElementPresent(locateElementByXPath("//h1[contains(text(),'" + CDX + "')]"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + appTileName);
        }
        isElementPresent(locateElementByID("main-content"));
    }

    public void rejectOwnPreviousRequestsForAccessInAppCon(String app) throws Exception {
        waitForCommonPageLoadingElements();
        WebElement[] tabs = {RequestAccess_Locators.approvedTab, RequestAccess_Locators.pendingTab};
        for (WebElement tab : tabs) {
            click(tab);
            waitForCommonPageLoadingElements();
            type(RequestAccess_Locators.searchTextBox, app);
            clickWithJSE(RequestAccess_Locators.searchBtn);
            waitForCommonPageLoadingElements();
            wait.until(ExpectedConditions.visibilityOf(locateElementByID(reqNumbMsgRA)));
            String msg = locateElementByID(reqNumbMsgRA).getText();
            int num = Integer.parseInt(msg.replaceAll("[^0-9]", ""));
            log.info("REQUESTS: " + tab.getText() + ": " + num);
            //TODO: REVIEW THIS LOGIC -- putting in a little defensive code just in case
            if (isElementPresentJSEByXPath("(//button[@class='btn-as-link button-red'])[1]")) {
                wait(1000);
                if (!isElementPresent("(//button[@class='btn-as-link button-red'])[1]", "xpath")) break;
                click(locateElementByXPath("(//button[@class='btn-as-link button-red'])[1]"));
                type(RequestAccess_Locators.justificationTextBoxReq, "IC automation test for user verification testing");
                RequestAccess_Locators.confirmBtnReq.click();
                handleAlert();
                waitForCommonPageLoadingElements();
                wait(1000);
                log.info("REQUESTS: Removed 1, expecting additional: " + (--num));
                if (!isElementPresent("(//button[@class='btn-as-link button-red'])[1]", "xpath")) break;
                if (num == 0) {
                    log.warn("WARN: REQUESTS is 0. Did not expect to see this line as a break was expected.");
                    Screenshot.captureScreen(driver, "WARN", testName);
                }
                if (num < 0) {
                    log.error("ERROR: REQUESTS < 0. DEFINITELY Did not expect to see this line - exiting now to avoid rejecting the wrong thing");
                    Screenshot.captureScreen(driver, "ERROR", testName);
                    return;
                }
            }
        }
    }

    public static String selectICApp(String appName) {
        return readDataFromExcel(getEnvVal(), appName, "newApps", "RepAndEnv");
    }

    public void requestNewAccessPageFeaturesVerification(DataTable dataTable) {
        waitForCommonPageLoadingElements();
        List<List<String>> lists = dataTable.asLists(String.class);
        for (List<String> list : lists) {
            isElementPresent(locateEleByXPathTextNormSpace(list.get(0)));
        }
        locateElementsByCss("div select").forEach(Model_CommonFunctions::isElementPresent);
        isElementPresent(locateElementByCSS("div textarea"));
    }

    public void paginationInRequestAndConfirmAccesstab(String page, String tab) throws Exception {
        if (tab.equalsIgnoreCase(confAccTab)) {
            click(locateEleByXPathTextNormSpace(allTab));
        }
        scroll_Down(locateElementByXPath("//div[@class='req-table-wrap']/ul"));
        WebElement element = locateElementByXPath("//div[@class='req-table-wrap']/ul//a[@aria-label='" + page + "']");
        if (element.isDisplayed() && element.isEnabled()) {
            wait(500);
            click(element);
        }
    }

    public void unsuccessfulReqsMesRorReqWithPendStatus(String winDial, DataTable dataTable) {
        listValue = dataTable.asLists(String.class);
        String actual, expected;
        String text = wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(winDial))).getText();
        System.out.println("text = " + text);
        verifyActualText(text, winDial);
        String text1 = listValue.get(0).get(0);
        actual = text1.replaceAll(" ", "");
        String text2 = locateEleByXPathTextNormSpace(text1).getText();
        expected = text2.replaceAll(" ", "");
//        highLightElement(locateEleByXPathTextNormSpace(text1));
//        verifyActualText(expected, actual);
    }

    public void requestStatusTabs(String appConTab, DataTable cucTabTable) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByID("skipToContent")));
        clickWithJSE(locateElementByCSS("li[alt='" + appConTab + "'] a[class='ng-binding']"));
        waitForCommonPageLoadingElements();
        switch (appConTab) {
            case reqAccTab:
                reqStatusTabsInReqAccVerification(appConTab, cucTabTable);
                break;
            case confAccTab:
                reqStatusTabsInConfAccVerification(appConTab, cucTabTable);
                break;
        }
    }

    public static void verifyRequestStatusText(String accessTab, String requestStatusTab, String user, DataTable dataTable) {
        List<WebElement> webElements = locateElementsByXPath("(//div" + reqRootWE(accessTab) + "//table[@class='table req-table']//div)[3]//div");
        List<String> list = new ArrayList<>();
        for (WebElement ele : webElements) {
            list.add(ele.getText());
        }
        System.out.println("list = " + list);
        System.out.println("user = " + user);
        for (Map<String, String> map : mapList) {
            Assert.assertTrue(list.toString().contains(map.get("Application name")));
            if (!accessTab.equals("Request Access")) Assert.assertTrue(list.toString().contains(user));
            Assert.assertTrue(list.toString().contains(map.get("Request Status")));
        }
    }

    public boolean errorIsNotOccured(String errWebEl, String errWebElText) {
        boolean errorIsPresent = locateElementsByCss(errWebEl).size() > 0;
        if (errorIsPresent) {
            System.out.println("error is presented while loading the app ");
            locateElementsByCss(errWebElText).forEach(p -> {
                p.getText().contains("Status");
                System.out.println("p = " + p.getText());
            });
            return true;
        }
        return false;
    }

    public void applicationConsolePageIsLoaded(String pageHeader) {
        boolean isUIUpdated = false;
        waitForCommonPageLoadingElements();
        try {
            locateElementByID("achome").isDisplayed();
            highLightElement(locateElementByID("achome"));
            locateElementsByCss("[id='achome'] h1 > *").forEach(e -> Assert.assertTrue(pageHeader.contains(e.getText())));
            isUIUpdated = true;
        } catch (Exception ignored) {
        }
        if (!isUIUpdated) {
            recoverBrowserAfterError();
            waitForCommonPageLoadingElements();
            locateElementByID("achome").isDisplayed();
            try {
                highLightElement(locateElementByID("achome"));
                locateElementsByCss("[id='achome'] h1 > *").forEach(e -> Assert.assertTrue(pageHeader.contains(e.getText())));
            } catch (Exception ignored) {
            }
        }
    }

    public static void main(String[] args) {

            String s1 = new String("softrams");
            String s2 = new String("SOFTRAMS");
            System.out.println(s1 = s2);

    }
    public void downstreamAppPageLoaded(String appName, String appDisplayName, String addHeading) throws Exception {
        int retries = 3;
        while (retries > 0) {
            if (!env2.equalsIgnoreCase("SB") && !appName.equalsIgnoreCase("HDR") || !env2.equalsIgnoreCase("SB")
                    && !appName.equalsIgnoreCase("eDFR")) {
                locateElementsByCss("[class='uc-app-acronym-holder'] span").forEach(p -> {
                    application = selectICApp(appName);
                    try {
                        if (p.getText().contains(application))
                            accessApplication(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                waitForCommonPageLoadingElements();
                if (!isUIHanging("Application")) {
                    if (longWait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(addHeading))).isDisplayed()) {
                        isElementPresent(locateEleByXPathTextNormSpace(addHeading));
                        highLightElement(locateEleByXPathTextNormSpace(addHeading));
                    } else {
                        System.out.println("the page was not displays = ");
                    }
                    break;
                } else {
                    continue;
                }
            }
            retries++;
        }
    }

    public void logOut() {
        driver.switchTo().parentFrame();
        ((JavascriptExecutor) driver).executeScript("scroll(0,-10000)");
        try {
            click(driver.findElement(By.id("logoutlinkHidden")));
        } catch (Exception e) {
            System.out.println("ERROR WITH LOGOUT: " + e.getMessage());
        }
    }

    public void appConsoleTabsAngUpt(String page, DataTable dataTable) {
        textVal = null;
        cucTabledata = null;
        if (page.contains(AppCon)) textVal = " li a";
        if (page.contains(myReqs)) textVal = " span button";
        dataTable.asLists().get(0).forEach(p -> {
            locateElementsByCss(acModuleRootWE + textVal).forEach(tab -> {
                        if (tab.getText().contains(p)) {
                            Assert.assertTrue(tab.getText().contains(p));
                        }
                    }
            );
        });
    }

    public void ac_BannerIsDisplayed(String banner) {
        expectedText = banner;
        waitForCommonPageLoadingElements();
        longWaitForElementAllowNull("//div[@class='cmmi-banner']", "xpath");
        highLightElement(Portal_AppConsole_RequestAccess_Locators.requestAccessTab);
        waitForAppearance(By.cssSelector(acModuleRootWE));
        if (expectedText.contains(AppCon) && !expectedText.contains("CMS")) {
            isElementPresent(locateEleByXPathContainsNormSpaceAttr("h1", expectedText));
            Assert.assertTrue(AppCon + " is not displayed", locateElementByCSS(acModuleRootWE).isDisplayed());
        } else {
            locateElementsByCss("[class='acHeader'] b").forEach(p -> {
                Assert.assertTrue("No such banner is displayed ", expectedText.contains(p.getText().trim()));
            });
        }
    }
}
