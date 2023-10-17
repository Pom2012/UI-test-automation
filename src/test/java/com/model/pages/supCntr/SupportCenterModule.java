package com.model.pages.supCntr;

import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.model.pages.supCntr.AssignRoleTab.assignRoleAssignClearBtn;
import static com.model.base.Constants.HDTabs.*;
import static com.model.base.Constants.fields.*;

public class SupportCenterModule extends BasePage {

    String sptCntTbsWElNames="div[id='cmmi-hd'] li a", hdMod="cmmi-hd", asRole="assignhd-component", userPro="app-user-profile-details",
            warState="warning-state-component", emailSndr ="email-sender-component", emailLog ="emaillog-component",
            btchOper="app-batch-search", srchUs ="app-user-search";

    public void clickATabSuppCent(String tabName) throws Exception {
        click(getElementByLocatorAndSearchType("//a[text()='" + tabName + "']", "xpath"));
        waitForCommonPageLoadingElements();
        switch (tabName) {
            case AsnRole:
                int retries = 3;
                while (retries > 0) {
                    if (isElementPresentJSEByXPath("//label[normalize-space(text())='"+RID+"")) wait(2000);
                    try {
                        highLightElement(locateEleByXPathTextNormSpaceAttr("label",JST));
                        highLightElement(locateEleByXPathTextNormSpace("Assign"));
                        if (!locateElementByXPath("//*[normalize-space(text())='"+UID+"']/parent::div//input").getText().isEmpty()) {
                            click(locateElementByXPath(assignRoleAssignClearBtn));
                            wait(200);
                        }
                       return;
                    } catch (Exception ignored) {}
                    //ENH: WORKAROUND: Assign Roles tab does not always load the first time
                    click(locateElementByXPath("//a[text()='" + BtchOp + "']"));
                    waitForCommonPageLoadingElements();
                    click(getElementByLocatorAndSearchType("//a[text()='" + tabName + "']", "xpath"));
                    waitForCommonPageLoadingElements();
                    retries--;
                }
                break;
            case UsProf:
                highLightElement(locateElementByXPath("//label[contains(@for, 'version')]"));
                highLightElement(locateEleByXPathTextNormSpaceAttr("button","Send"));
                break;
            case WrnSt:
                highLightElement(locateEleByXPathTextNormSpaceAttr("button","Run Warning State"));
                highLightElement(locateElementByXPath("//pre"));
                break;
            case EmSnd:
                highLightElement(locateElementByCSS("#"+hdMod+" "+ emailSndr +" input"));
                highLightElement(locateEleByXPathTextNormSpaceAttr("button","Send Email"));
                break;
            case EmLog:
                highLightElement(locateElementByCSS("#"+hdMod+" "+ emailLog +" mat-select"));
                highLightElement(locateEleByXPathTextNormSpaceAttr("button","Search"));
                break;
            case BtchOp:
                highLightElement(locateElementByXPath("//input[@placeholder='Enter Request ID']"));
                break;
            case SrchUs:
                highLightElement(locateElementByCSS("#"+hdMod+" "+ srchUs +" div[id='searchusers']"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tabName);
        }
    }

    public void supCenTabsVer(DataTable data) throws Exception {
        List<List<String>> supCenterTabs = data.asLists(String.class);
        supCenterTabs.forEach(p -> p.get(0));
//        for (List<String> supCenterTab : supCenterTabs) {
//            clickATabSuppCent(supCenterTab.get(0));
//        }
    }

    public void searchUsersInBatchOperTab(String batchTab, DataTable data) throws Exception {
        cucTabledata = data.asLists(String.class);
        wait(1500);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//div[@id='searchusers']//form)[1]")));
        clickWithJSE(locateElementByXPathText(batchTab));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='searchusers']//form)[1]")));
        selectFromDropDownMenu(locateElementByXPath("(//*[@ui-view='batch']//select)[1]"), cucTabledata.get(0).get(1));
        selectFromDropDownMenu(locateElementByXPath("(//*[@ui-view='batch']//select)[2]"), cucTabledata.get(1).get(1));
        System.out.println("text = " + cucTabledata.get(2).get(1));
        wait(500);
        type(locateElementByXPath("(//*[@ui-view='batch']//input)[6]"), cucTabledata.get(2).get(1));
        routingElemHandlingHelper(locateElementByXPath("//ul[@class='dropdown-menu ng-isolate-scope']"), "Virginia");
        click(locateElementByXPathText("Search"));
    }

    public void rejOrApprAccsInBatchOpTab(String opt, String dropdown) throws Exception {
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByXPath("//select[@ng-model='" + dropdown + "']"), opt);
        waitForCommonPageLoadingElements();
        click(locateElementByXPath("//td/a//following::input[1]"));
        click(locateElementByXPathText("Reject Request(s)"));
        type(locateElementByXPath("//*[@class='modal-content']//textarea"),"Testing UV feature");
        wait.until(ExpectedConditions.elementToBeClickable(locateEleByXPathTextNormSpace("Submit"))).click();
        handleAlert();
    }

    public void userProfileHandling() {
        String userProfileJsonBody = locateElementByID("usrProfileJson").getText();
        System.out.println("User Profile Json Body is = \n" + userProfileJsonBody + "\n");
        String[] splited = userProfileJsonBody.split(" ");
    }

    public void supCentPageIsLoaded(String pageHeader) {
        boolean isUIUpdated = false;
        waitForCommonPageLoadingElements();
        try {
            locateElementByID("cmmi-hd").isDisplayed();
            highLightElement(locateElementByID("cmmi-hd"));
            locateElementByCSS("div h2").getText().contains(pageHeader);
            isUIUpdated = true;
        } catch (Exception ignored) {
        }
        if (!isUIUpdated) {
            recoverBrowserAfterError();
            locateElementByID("cmmi-hd").isDisplayed();
            try {
                highLightElement(locateElementByID("cmmi-hd"));
                locateElementByCSS("div h2").getText().contains(pageHeader);
            } catch (Exception ignored) {
            }
        }
    }
}
