package com.model.pages.admConsole;

import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CustAttrListLinkage extends BasePage {

    public void customAttributeListsLinkagePageFeatures(String featurebtn, DataTable lineData) throws Exception {
        List<List<String>> data = lineData.asLists(String.class);
        click(locateElementByXPathText(featurebtn));
        //ENH: WORKAROUND: List Management does not always load the first time
        Exception potentialException = null;
        int retries = 3;
        boolean isListManagementLoaded = false;
        while (retries > 0) {
            try {
/* //TODO: WORKAROUND: Seems wrong in VALAWS -- unclear state in other envs -- wait for a 10th row to be populated instead
                    scroll_Into_View(locateElementByXPathText(data.get(0).get(1)));
                    highLightElement(locateElementByXPathText(data.get(0).get(1)));
 */
                waitForExpectedElement(locateElementByXPath("//tr[1]"));
                isListManagementLoaded = true;
            } catch (Exception e) { potentialException = e; }
            if (isListManagementLoaded) {
                break;
            } else {
                log.warn("WARN: WORKAROUND: List Management does not always load the first time");
                recoverBrowserAfterError();
                click(locateElementByXPathText(featurebtn));
                retries--;
            }
        }
        if (!isListManagementLoaded) Assert.fail("FAIL: List Management did not load correctly : Exception: " + potentialException);
//ENH: redo as generic iterator for page elements
        //header
        highLightElement(locateElementByTypeClassAndContainsText("div", "header-label", data.get(1).get(1)));
        //search label and text field
        highLightElement(locateElementByXPathText(data.get(2).get(1) + ": "));
        highLightElement(locateElementByID("mat-input-0"));
        // Feature's button     | Custom Attribute Lists Linkage
        highLightElement(locateElementByTypeClassAndContainsText("span", "mat-button-wrapper", data.get(3).get(1)));
        // Feature's table data      |
        highLightElement(locateElementByCSS("tbody[role='rowgroup']"));
        // Feature's button     | Back                                   |
        highLightElement(locateElementByTypeClassAndContainsText("span", "mat-button-wrapper", data.get(5).get(1)));
    }

    public void newUpdateCustAttrListsLinkPageFeatures(String featurebtn, DataTable lineData) throws Exception {
        List<List<String>> data = lineData.asLists(String.class);
        click(locateElementByXPathText(" " + featurebtn));
        System.out.println(" Clicked the " + featurebtn + " button");
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPathText(data.get(0).get(1))));
        for (int i = 0; i < data.get(1).size(); i++) {
            if (i == 0 || i == 1 || i == 2 || i == 3) {
                highLightElement(locateElementByXPathText(data.get(i).get(1)));
            }
            if (i == 5 || i == 6) {
                highLightElement(locateElementByXPathText(" " + data.get(i).get(1)));
            }
        }
        click(locateElementByXPathText(" " + data.get(6).get(1)));

    }
}
