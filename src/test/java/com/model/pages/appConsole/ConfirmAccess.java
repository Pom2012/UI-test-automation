package com.model.pages.appConsole;

import com.model.utility.randomData;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_ConfirmAccess_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.base.Constants.ApplicationConsoleTabs.confAccTab;
import static com.model.base.Constants.RequestStatus.pend;


public class ConfirmAccess extends ApplicationConsolePages {
    public randomData randomData = new randomData(null);
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    public Portal_AppConsole_ConfirmAccess_Locators ConfirmAccess_Locators;

    public ConfirmAccess() {
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
        this.ConfirmAccess_Locators = new Portal_AppConsole_ConfirmAccess_Locators();
        PageFactory.initElements(driver, ConfirmAccess_Locators);
    }

    public void approveRequest() throws Exception {
        approveRequest(pend);
    }

    public void approveRequest(String startStatus) throws Exception {
        //TODO: EXCEL: Reading last row from Excel is currently unreliable as not linked to current TC
        if (BasePage.requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        ConfirmAccess_Locators.searchTextBox.clear();
        wait(1000);
        typeonForm(ConfirmAccess_Locators.searchTextBox, requestID);
        click(ConfirmAccess_Locators.searchBtn);
        if (longWaitForElementAllowNull("//span[contains(text(),'" + requestID + "')]", "xpath") == null) {
            Assert.assertTrue(isElementPresent(locateElementByXPathContainsText(requestID)));
        }
        String actualStatus = locateElementByXPath("//span[contains(text(),'" + startStatus + "')]").getText();
        highLightElement(locateElementByXPathContainsText(startStatus));
        System.out.println("The request " + requestID + " is " + actualStatus);
        clickWithJSE(ConfirmAccess_Locators.assignBtn);
        wait(3000);
        clickWithJSE(ConfirmAccess_Locators.approveBtn);
        isElementPresent(ConfirmAccess_Locators.approveAccessDialogBox);
        justification = randomData.createStr(3);
        type(ConfirmAccess_Locators.justificationTextBox, justification);
        ConfirmAccess_Locators.confirmBtn.click();
        handleAlert();
        ConfirmAccess_Locators.approvedTab.click();
        wait(3000);
        status = "APPROVED";
        isElementPresent(driver.findElement(By.xpath("//span[contains(text(),'" + requestID + "')]")));
        wait(3000);
        actualStatus = driver.findElement(By.xpath("//span[contains(text(),'" + status + "')]")).getText();
        highLightElement(driver.findElement(By.xpath("//span[contains(text(),'" + status + "')]")));
        System.out.println("The request " + requestID + " is " + actualStatus);
    }

    public void rejectRequest() throws Exception {
        //TODO: EXCEL: Reading last row from Excel is currently unreliable as not linked to current TC
        if (BasePage.requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        typeonForm(ConfirmAccess_Locators.searchTextBox, requestID);
        wait(3000);
        click(ConfirmAccess_Locators.searchBtn);
        waitForCommonPageLoadingElements();
        click(locateEleByXPathTextNormSpace("All"));
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByXPathContainsText(requestID));
        String actualStatus = locateElementByXPathContainsText(status).getText();
        highLightElement(locateElementByXPathContainsText(status));
        System.out.println("The request " + requestID + " is " + actualStatus);
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmAccess_Locators.assignBtn));
        clickWithJSE(ConfirmAccess_Locators.assignBtn);
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmAccess_Locators.rejectBtn));
        clickWithJSE(ConfirmAccess_Locators.rejectBtn);
        isElementPresent(ConfirmAccess_Locators.approveAccessDialogBox);
        justification = randomData.createStr(5);
        type(ConfirmAccess_Locators.justificationTextBox, justification);
        simpleClick(ConfirmAccess_Locators.confirmBtn);
        handleAlert();
        click(ConfirmAccess_Locators.rejectedTab);
        status = "REJECTED";
        wait(2000);
        actualStatus = locateElementByXPathContainsText(status).getText();
        highLightElement(locateElementByXPathContainsText(status));
        System.out.println("The request " + requestID + " is " + actualStatus);
    }

    public void confirmPendRequest(String assigneeRowID, String approverICrole, String reqStatus, String app) throws Exception {
        //TODO: DATA: Hard-coded on a Req that was made in a previous TC in the same Test Suite / Feature file.
        if (BasePage.requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        String reqValueFromExFile = BasePage.requestID;
        clickWithJSE(locateElementByCSS("li[heading='Confirm Access'] a[class='ng-binding']"));
        waitForCommonPageLoadingElements();
        typeonForm(ConfirmAccess_Locators.searchTextBox, reqValueFromExFile);
        waitForCommonPageLoadingElements();
        ConfirmAccess_Locators.searchBtn.click();
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByXPath("//span[contains(text(),'" + reqStatus + "')]"));
        clickWithJSE(ConfirmAccess_Locators.assignBtn);
        waitForCommonPageLoadingElements();
        clickWithJSE(ConfirmAccess_Locators.approveBtn);
        isElementPresent(ConfirmAccess_Locators.approveAccessDialogBox);
        type(ConfirmAccess_Locators.justificationTextBox, "This role approved for model practice by " + approverICrole);
        ConfirmAccess_Locators.confirmBtn.click();
        handleAlert();
        waitForCommonPageLoadingElements();
    }

    public void viewOptions(DataTable opts) throws Exception {
        cucTabledata = opts.asLists(String.class);
        String actualReqStatusTabValue;
        String selectionOptionValue;
        for (int k = 0; k < cucTabledata.size(); k++) {
            selectionOptionValue = cucTabledata.get(k).get(0);
//TODO: DATA: SB env will not continue for the moment. Would be better not to store in Java though
            if (selectionOptionValue.equalsIgnoreCase("Business Owner View")
                    && environment.equalsIgnoreCase("DEVSB")) {
                log.warn("WARN: ENV: SG does not perform this test: " + selectionOptionValue);
                break;
            }
            System.out.println("Selected " + selectionOptionValue + " option...");
            try {
                selectFromDropDownMenu(locateElementByCSS("#requestViewOption"), selectionOptionValue);
            } catch (StaleElementReferenceException e) {
                log.error("ERROR: " + e.getMessage());
                e.getStackTrace();
            }
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#"+reqNumbMsgCA+"")));
            for (int i = 1; i < cucTabledata.get(0).size(); i++) {
                actualReqStatusTabValue = cucTabledata.get(0).get(i);
                System.out.println(cucTabledata.size() + " request status tabs are appeared");
                click(locateElementByXPath("//uib-tab-heading[@class='ng-scope'][contains(text(),'" + actualReqStatusTabValue + "')]"));
                wait(500);
                wait.until(ExpectedConditions.visibilityOf(locateElementByID("reqtable-frame-ca")));
                highLightElement(locateElementByXPath("//uib-tab-heading[@class='ng-scope'][contains(text(),'" + actualReqStatusTabValue + "')]/span[1]"));
                getRequestNumbersFromReqStatusTabs(confAccTab, actualReqStatusTabValue);
            }
        }
    }

    public void viewOptionsInConfirmAccess(DataTable voptions) throws Exception {
        //TODO: Is this redundant to the above method?
        cucTabledata = voptions.asLists(String.class);
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//b[text() = '"+confAccTab+"']")));
        for (int i = 0; i < cucTabledata.size(); i++) {
            selectFromDropDownMenu(locateElementByID("requestViewOption"), cucTabledata.get(i).get(0));
            highLightElement(locateElementByCSS("#"+reqNumbMsgCA+""));
            wait(500);
        }
    }
}
