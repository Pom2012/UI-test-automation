package com.model.pages.appConsole;

import com.model.utility.DataHelper;

import com.model.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;


public class MyRequests extends ApplicationConsolePages {


    public void searchInMyRequests(String dataSource) throws Exception {
        List<HashMap<String, String>> inputRows = DataHelper.data(dataSource, env2);
        for (HashMap<String, String> rowData : inputRows) {
            waitForCommonPageLoadingElements();
            BasePage.log.info("START: search for Request Role: " + rowData);
            waitForAppearance(By.cssSelector(acModuleRootWE + " table"));
            type(locateElementByCSS(acModuleRootWE + " input"), rowData.get("Custom Attr Value 1").trim());
            click(locateElementByCSS(acModuleRootWE + " mat-icon"));
            waitForAppearance(By.cssSelector(acModuleRootWE + " table"));
            if (locateElementsByCss("[id='requestAccessView'] button[class='btn-as-link button-red']").size() > 0) {
                locateElementByCSS("#cmmi-ac strong").click();
                type(locateElementByCSS("mat-dialog-container textarea"), "Cancelled the request ");
                wait.until(ExpectedConditions.elementToBeClickable(
                        locateEleByXPathContainsNormSpaceAttr("mat-dialog-container//button", "Yes"))).click();
            }
        }
    }
}
