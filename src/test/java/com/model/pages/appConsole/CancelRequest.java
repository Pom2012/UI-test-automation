package com.model.pages.appConsole;

import com.model.utility.randomData;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_ConfirmAccess_Locators;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class CancelRequest extends ApplicationConsolePages {
    Portal_AppConsole_RequestAccess_Locators appConsoleRequest;
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    public Portal_AppConsole_ConfirmAccess_Locators ConfirmAccess_Locators;
    public static String justification = null;
    public static String status = null;

    public CancelRequest() {
        appConsoleRequest = PageFactory.initElements(driver, Portal_AppConsole_RequestAccess_Locators.class);
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
        this.ConfirmAccess_Locators = new Portal_AppConsole_ConfirmAccess_Locators();
        PageFactory.initElements(driver, ConfirmAccess_Locators);
    }

    public void cancelRequest() throws Exception {
        //TODO: EXCEL: Reading last row from Excel is currently unreliable as not linked to current TC
        if (BasePage.requestID.isEmpty()) Assert.fail("FAIL: No Request ID was available.");
        if (longWaitForElementAllowNull("//span[contains(text(),'" + requestID + "')]", "xpath") == null) {
            Assert.assertTrue(isElementPresent(locateElementByXPathContainsText(requestID)));
        }
        wait(500);
        String actualStatus = locateElementByXPath("(//span[contains(text(), '" + requestID + "')]/parent::div//span)[5]").getText();
        System.out.println("actualStatus = " + actualStatus);
        highLightElement(locateElementByXPath("(//span[contains(text(), '" + requestID + "')]/parent::div//span)[5]"));
        System.out.println("The request " + requestID + " is " + actualStatus);
        //CANCEL REQUEST:
        click(locateElementByXPath("//button[contains(@title,'Cancel for request id " + requestID + "')]"));
        isElementPresent(ConfirmAccess_Locators.approveAccessDialogBox);
        type(ConfirmAccess_Locators.justificationTextBox, new randomData(null).createStr(3));
        click(ConfirmAccess_Locators.confirmBtn);
        handleAlert();
        click(ConfirmAccess_Locators.rejectedTab);
        status = "REJECTED";
        Assert.assertTrue(isElementPresent(driver.findElement(By.xpath("//span[contains(text(),'" + requestID + "')]"))));
        actualStatus = locateElementByXPath("//span[contains(text(),'" + status + "')]").getText();
        highLightElement(driver.findElement(By.xpath("//span[contains(text(),'" + status + "')]")));
        System.out.println("The request " + requestID + " is " + actualStatus);
    }

    public void cancelRequests() throws Exception {
        click(locateElementByXPathText("Request Access"));
        waitForCommonPageLoadingElements();
        click(RequestAccess_Locators.allTab);
        requests.forEach(req -> {
            System.out.println("requests ID = " + req);
            RequestAccess_Locators.searchTextBox.clear();
            RequestAccess_Locators.searchTextBox.sendKeys(String.valueOf(req));
            RequestAccess_Locators.searchBtn.click();
            waitForCommonPageLoadingElements();
            try {
                click(locateElementByXPath("//button[contains(@title,'Cancel for request id " + req + "')]"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            type(RequestAccess_Locators.justificationTextBoxReq, IC + " automation test");
            RequestAccess_Locators.confirmBtnReq.click();
            try {
                click(locateElementByXPath("//button[contains(@title,'Cancel for request id " + req + "')]"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            waitForCommonPageLoadingElements();
        });
    }
}