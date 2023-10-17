package com.model.pages.supCntr;

import com.model.utility.DataHelper;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

import static com.model.pages.supCntr.SupportCenterPages.locateSelection;
import static com.model.utility.Alerts_and_Requests.handleAlertBatchOperGetReq;
import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.utility.DataHelper.readInfoIconData;
import static com.model.base.Constants.HDTabs.BtchOp;
import static com.model.base.Constants.HDTabs.SrchUs;

public class SearchUsersPages extends SupportCenterModule {
    public static String rejectedRequestID = null;
    private List<List<String>> listEmailNots = null;
    List<String> actENOpts = new ArrayList<>();
    List<String> list = new ArrayList<>();
    SupportCenterPages sptMeth = new SupportCenterPages();
    BatchOperations batchOpers = new BatchOperations();
    String searchResTableAttrID = "#hdSearchResultTable",
            appRoleReqInfoTableAttrID = "#hdbatchresult",
            histReqPopUpAttrID = "#hdRequestHistoryTable thead",
            userFullName = "h2[class='ng-binding']",
            userDetailsScreenID = "userdetails-header",
            reqRadBntsXpath = "//*[@class='requests-buttons']",
            containsReqID = null,
            sUsBox = "searchusers",
            sBtn = "[id='" + sUsBox + "'] button[type='submit']",
            sResults = "searchResults", usDetScreen = "userdetails-header",
            usDetScrWebTable = "hdRequestTable", winDialBox = "app-email-preferences";
    int envir = getEnvVal();

    public void searchUsrs(String tabName, DataTable values) throws Throwable {
        Iterator<List<String>> iterator = values.asLists(String.class).iterator();
        while (iterator.hasNext()) {
            List<String> row = iterator.next();
            if (row.get(0).contains("Typed")) {
                type(locateElementByXPath("//label[contains(text(), '" + row.get(1) + "')]/parent::div//input"), dataToBeSearched(row.get(1)));
            }
            if (row.get(0).contains("Selected") && row.get(1).contains("Application Name")) {
                selectFromDropDownMenu(locateElementByXPath("//label[contains(text(), '" + row.get(1) + "')]/parent::div//select"), dataToBeSearched(row.get(1)));
            }
            if (row.get(0).contains("Typed")) {
                type(locateElementByXPath("//label[contains(text(), '" + row.get(1) + "')]/parent::div//input"), dataToBeSearched(row.get(1)));
            }
            if (row.get(0).contains("Selected") && row.get(1).contains("Role")) {
                selectFromDropDownMenu(locateElementByXPath("//label[contains(text(), '" + row.get(1) + "')]/parent::div//select"), dataToBeSearched(row.get(1)));
                wait.until(ExpectedConditions.elementToBeSelected(locateElementByXPath("//label[contains(text(), '" + row.get(2) + "')]/parent::div//select")));
                selectFromDropDownMenu(locateElementByXPath("//label[contains(text(), '" + row.get(2) + "')]/parent::div//select"), dataToBeSearched(row.get(2)));
            }
            click(locateElementByCSS("button[type='submit']"));
            waitForCommonPageLoadingElements();
            if (tabName.equalsIgnoreCase(BtchOp)) isElementPresent(locateElementByXPath(sptMeth.hdTableWe));
            if (tabName.equalsIgnoreCase(SrchUs)) isElementPresent(locateElementByCSS(searchResTableAttrID));
            click(locateElementByCSS("button[type='reset']"));
            waitForCommonPageLoadingElements();
        }
    }

    public String dataToBeSearched(String searchData) {
        List<HashMap<String, String>> inputRows = DataHelper.data("supportCenterData.xlsx", env2);
        String searchValue = null, modified = null;
        for (HashMap<String, String> rowData : inputRows) {
            modified = searchData.replaceAll(" ", "_");
            searchValue = rowData.get(modified).trim();
            break;
        }
        return searchValue;
    }

    public void searchCAs(String tabName, DataTable value) throws Exception {
        for (List<String> list : value.asLists()) {
            System.out.println("list.get(0) = " + list.get(0));
            if (!list.get(0).equalsIgnoreCase("Participant ID") && !list.get(0).equalsIgnoreCase("Model ID")) {
                wait(2000);
                selectFromDropDownMenu(locateElementByXPath(locateSelection(tabName, list.get(0))), readDataFromExcel(envir, list.get(0), "newApps", "Batch"));
            }
            if (list.get(0).equalsIgnoreCase("Model ID")) {
                wait(2000);
                selectFromDropDownMenu(locateElementByXPath("//*[normalize-space(text())='" + list.get(0) + "']/parent::div//select"), readDataFromExcel(envir, list.get(0), "newApps", "Batch"));
            }
            if (list.get(0).equalsIgnoreCase("Participant ID")) {
                type(locateElementByXPath("//*[normalize-space(text())='" + list.get(0) + "']/parent::div//input"), readDataFromExcel(envir, list.get(0), "newApps", "Batch"));
                wait(2000);
                routingElemHandlingHelper(locateElementByXPath("//ul[@class='dropdown-menu ng-isolate-scope']"), readDataFromExcel(envir, "RoutingCA", "newApps", "Batch"));
            }
        }
        click(locateElementByCSS("button[type='submit']"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(sptMeth.hdTableWe)));
        locateElementsByXPath("//*[@id='" + usDetScrWebTable + "']//td[6]").forEach(p -> {
            System.out.println("\nAct = " + p.getText().replaceAll("^.*/", "") + "\nExp = " + readDataFromExcel(envir, "RoutingCA", "newApps", "Batch"));
        });
    }

    public void searchAValueInSearchUserTab(String searchValue) throws Exception {
        waitForCommonPageLoadingElements();
        type(locateElementByXPath("//*[@id='" + sUsBox + "']//input[contains(@id,'input_eidmId')]"), searchValue);
        click(locateElementByCSS(sBtn));
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("#" + sResults)));
        highLightElement(locateElementByXPathText(searchValue));
    }

    public void userDetailsScreenDisplayed() {
        waitForCommonPageLoadingElements();
        highLightElement(locateElementByID(userDetailsScreenID));
        wait.until(ExpectedConditions.visibilityOf(locateElementByID(usDetScrWebTable)));
    }

    public void verifyColumnDataIsSorted(List<String> list, String sequence) {
        String order;
        order = sequence;
        for (int i = 0; i < list.size(); i++) {
            if (order.equalsIgnoreCase("ascending")) {
                if (Integer.parseInt(list.get(i)) > Integer.parseInt(list.get(i - 1))) {
                     return;
                }
            } else {
                if (Integer.parseInt(list.get(i)) > Integer.parseInt(list.get(i - 1))) {
                    return;
                }
            }
        }
    }

    public void verifySorting(String sequence, DataTable columnName) {
        waitForCommonPageLoadingElements();
        List<String> collist = columnName.asList();
        collist.forEach(th -> locateElementsByXPath("//*[@id='" + usDetScrWebTable + "']//th[contains(@aria-sort, 'non') or contains(@aria-sort,'descending') or contains(@aria-sort,'ascending')]")
                .forEach(p -> {
                    if (p.getText().contains(th)) {
                        try {
                            textA = p.getAttribute("id");
                            click(p);
                            if (sequence.contains("descending")) click(p);
                            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='" + usDetScrWebTable + "']")));
                            boolean enabled = locateElementByCSS("button[aria-label='Next page']").isEnabled();
                            while (enabled) {
                                locateElementsByXPath("//*[@id='c" + textA + "']//ancestor::table//td[" + textA + "]").forEach(c -> list.add(c.getText()));
                                enabled = locateElementByCSS("button[aria-label='Next page']").isEnabled();
                                if (enabled) {
                                    click(locateElementByCSS("button[aria-label='Next page']"));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }));
        verifyColumnDataIsSorted(list, sequence);
    }

    public void userInfoVerificationInSearchUserScreen(DataTable options) throws Exception {
        cucTabledata = options.asLists(String.class);
        scroll_Into_View(locateElementByCSS(userFullName));
        highLightElement(locateElementByCSS(userFullName));
        highLightElement(locateElementByXPathText(cucTabledata.get(1).get(0)));
        highLightElement(locateElementByCSS("div[class='cicdim-search-detail ng-scope'] div:nth-child(5) div:nth-child(1)"));
        highLightElement(locateElementByXPath("(" + reqRadBntsXpath + ")[1]"));
        highLightElement(locateElementByXPath("(" + reqRadBntsXpath + ")[2]"));
        click(locateElementByXPath("(" + reqRadBntsXpath + "//i)[1]"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@class='info-message ng-scope']")));
        highLightElement(locateElementByXPath("//div[@class='info-message ng-scope']"));
        click(locateElementByXPath("(" + reqRadBntsXpath + "//i)[2]"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@ng-if='assignedMessage']")));
        highLightElement(locateElementByXPath("//div[@ng-if='assignedMessage']"));
        highLightElement(locateElementByCSS(batchOpers.getBoWebTblID()));
    }

    public void apprReqIDinSearchTab() throws Exception {
        System.out.println("Approving...............");
        String rejRequestID = locateElementByXPath("(//table//tr[@ng-repeat='brequest in requests']/td)[1]").getText();
        System.out.println("rejected Request ID = " + rejRequestID);
        rejectedRequestID = rejRequestID;
        selectFromDropDownMenu(locateElementByCSS("select[st-search='status']"), "REJECTED");
        wait(2000);
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("(//input[@type='checkbox'])[2]"))).click();
        click(locateElementByCSS("#assign-btn"));
        highLightElement(locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea"));
        locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea").sendKeys("Approved...");
        simpleClick(locateEleByXPathTextNormSpace("Submit"));
        String requestIDFromAlert = handleAlertBatchOperGetReq();
        System.out.println("request ID from alert = " + requestIDFromAlert);
    }

    public void rejReqIDinSearchTab() throws Exception {
        System.out.println("Rejecting...............");
        try {
            selectFromDropDownMenu(locateElementByCSS("select[st-search='status']"), "APPROVED");
            wait.until(ExpectedConditions.elementToBeClickable(
                    locateElementByXPath("//*[normalize-space(text())='" + rejectedRequestID + "']/parent::td/following-sibling::td/input"))).click();
        } catch (Exception e) {
            e.getStackTrace();
        }
        click(locateElementByCSS("#reject-btn"));
        highLightElement(locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea"));
        locateElementByXPath("//*[normalize-space(text())='Justification']/parent::div//div/textarea").sendKeys("Rejected...");
        simpleClick(locateEleByXPathTextNormSpace("Submit"));
        handleAlert();
        wait(2000);
    }

    public void reqIDPopUpInSearchTab() throws Exception {
        requestID = wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//table[@id='" + usDetScrWebTable + "']//tr//a)[1]"))).getText();
        clickWithJSE(locateElementByXPath("//a[normalize-space()='" + requestID + "']"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByCSS(".ic_modal h3"))).getText();
        containsReqID = locateElementByCSS(".ic_modal h3").getText();
        Assert.assertTrue(containsReqID.contains(requestID));
        highLightElement(locateElementByCSS("#hdRequestHistoryTable"));
    }

    public void searchResultsAndTable(String user4Search, String table, DataTable dataTable) throws Exception {
        List<List<String>> lists = dataTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        if (table.contains("Search")) {
            textVal = searchResTableAttrID;
            textA = "th";
        }
        if (table.contains("User Screen")) {
            textVal = appRoleReqInfoTableAttrID;
            textA = "*";
        }
        if (table.contains("History of Request ID")) {
            textVal = histReqPopUpAttrID;
            textA = "*";
        }
        highLightElement(locateElementByCSS(textVal));
        for (int i = 0; i < lists.get(0).size(); i++) {
            Assert.assertTrue(locateEleByXPathTextNormSpaceAttr(textA, lists.get(0).get(i)).isDisplayed());
        }
        if (table.contains("History of Request ID")) {
            Assert.assertTrue(containsReqID.contains(requestID));
            Assert.assertTrue(containsReqID.contains(table));
            click(locateElementByXPath("//button[normalize-space()='Ok']"));
        } else if (table.contains("User Screen")) {
            Assert.assertTrue(locateEleByXPathTextNormSpaceAttr("div", user4Search).isDisplayed());
        } else {
            Assert.assertTrue(locateEleByXPathTextNormSpace(user4Search).isDisplayed());

        }
    }

    public void selectdropdownsVerification(DataTable dataTable) throws Exception {
        String selDrDown = appRoleReqInfoTableAttrID + " select";
        cucTabledata = dataTable.asLists(String.class);
        for (int i = 0; i < cucTabledata.get(0).size(); i++) {
            System.out.println("cucTabledata.get(0).size() = " + cucTabledata.get(0).size());
            if (i == 0) continue;
            System.out.println("i = " + i);
            actualText = cucTabledata.get(0).get(i);
            System.out.println("textVal = " + actualText);
            locateElementsByCss("#hdbatchresult select option").forEach(r -> {
                        if (r.getText().contains(actualText)) {
                            try {
                                selectFromDropDownMenu(locateElementByCSS(selDrDown), actualText);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            locateElementsByCss("table[id='" + usDetScrWebTable + "'] tbody tr  td:nth-of-type(7)").forEach(p -> {
                                expectedText = p.getText();
                                verifyActualText(actualText, expectedText);
                            });
                        }
                    }
            );
        }
    }

    public void selectRadioButtons(String string, DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        Assert.assertTrue(locateElementByID("userdetails-header").isDisplayed());
        cucTabledata = dataTable.asLists();
        for (WebElement element : locateElementsByXPath("//div[normalize-space(text())='" + cucTabledata.size() + "']//input")) {
            System.out.println("cucTabledata.size() = " + cucTabledata.size());
            System.out.println("radio button text = " + element.getText());
            if (element.isSelected()) {
                continue;
            } else {
                if (locateElementByXPath("(//div[@ui-view='userDetails']//div)[26]").getText().contains("There are no requests assigned to this user"))
                    continue;
                else {
                    try {
                        element.click();
                    } catch (ElementClickInterceptedException e) {
                        e.getMessage();
                    }
                }
                highLightElement(locateElementByXPath("//div[@id='hdbatchresult']"));
            }
        }
    }

    public void verifyInfoIcon(DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        cucTabledata = dataTable.asLists(String.class);
        String infoIcon;
        for (int i = 0; i < cucTabledata.size(); i++) {
            infoIcon = "//*[normalize-space(text())='" + cucTabledata.get(i).get(0) + "']//i";
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath(infoIcon))).click();
            actualText = wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@class='info-message ng-star-inserted']"))).getText();
            expectedText = readInfoIconData("newApps", "InfoIcon", "Search Users", cucTabledata.get(i).get(0));
            Assert.assertEquals(expectedText, actualText);
            Assert.assertTrue(locateElementByXPath("//div[@class='info-message ng-star-inserted']").isDisplayed());
            click(locateElementByXPath(infoIcon));
        }
    }

    public void selectAndSeeReqIDAndStatus(String expectedStatus) {
        waitForCommonPageLoadingElements();
        System.out.println("Expected Request ID = " + BasePage.requestID);
        System.out.println("Actual status = " + status);
        selectFromStatusOptions(expectedStatus);
        locateElementsByCss("table[id='" + usDetScrWebTable + "'] tbody tr  td:nth-of-type(1)").forEach(
                p -> {
                    if (p.getText().contains(BasePage.requestID)) {
                        System.out.println("Expected Request ID is = " + BasePage.requestID);
                        System.out.println("Expected Request status is = " + BasePage.requestID);
                    }
                }
        );
    }

    public void selectFromStatusOptions(String selOpt) {
        locateElementsByCss("#hdbatchresult select option").forEach(r -> {
            if (r.getText().contains(selOpt)) {
                try {
                    selectFromDropDownMenu(locateElementByCSS(appRoleReqInfoTableAttrID + " select"), selOpt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                locateElementsByCss("table[id='" + usDetScrWebTable + "'] tbody tr  td:nth-of-type(7)").forEach(p -> {
                    expectedText = p.getText();
                    verifyActualText(selOpt, expectedText);
                });
            }
        });
    }

    public void selectOptsAndSeeWTab(DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists();
        waitForCommonPageLoadingElements();
        Assert.assertTrue(locateElementByID(usDetScreen).isDisplayed());
        for (int i = 0; i < cucTabledata.size(); i++) {
            isElementPresent(locateEleByXPathTextNormSpaceAttr("div", cucTabledata.get(i).get(0)));
            webElement = locateElementByXPath("//*[normalize-space(text())='" + cucTabledata.get(i).get(0) + "']//input");
            if (webElement.isSelected()) {
                isElementPresent(locateEleByXPathTextNormSpace(cucTabledata.get(i).get(1)));
            } else {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
                    waitForCommonPageLoadingElements();
                    isElementPresent(locateEleByXPathTextNormSpace(cucTabledata.get(i).get(1)));
                    wait.until(ExpectedConditions.visibilityOf(locateElementByID(usDetScrWebTable))).isDisplayed();
                } catch (ElementClickInterceptedException e) {
                    e.getMessage();
                }
            }
        }
    }

    public void verifyAlertText4EmptySearch(String searchBtn, DataTable dataTable) {
        wait.until(ExpectedConditions.elementToBeClickable(locateEleByXPathTextNormSpace(searchBtn))).click();
        String actualAlertText = handleAlert();
        verifyActualText(dataTable.toString().substring(1, dataTable.toString().length() - 2).trim(), actualAlertText);
    }

    public void typedInvalidUserIDValue(String textField) {
        try {
            type(locateElementByCSS("[placeholder='Enter " + textField + "']"), BasePage.faker.random().toString());
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }
    }

    public void hdPageAndTabsVerification(String pagebanner, String defaultPage, DataTable dataTable) {
        listOfWE = locateElementsByCss(sptCntTbsWElNames);
        waitForCommonPageLoadingElements();
        for (List<String> list : dataTable.asLists(String.class)) {
            for (WebElement element : listOfWE) {
                if (element.getText().contains(list.get(0))) {
                    verifyActualText(element.getText(), list.get(0));
                    break;
                }
            }
        }
    }

    public void searchUsersTabPageVer(DataTable dataTable) {
        Iterator<List<String>> iterator = dataTable.asLists(String.class).iterator();
        while (iterator.hasNext()) {
            List<String> row = iterator.next();
            locateElementsByCss(sptCntTbsWElNames).forEach(p -> {
                        highLightElement(p);
                        if (p.getText().contains(row.get(0))) {
                            highLightElement(p);
                            verifyActualText(p.getText(), p.getText());
                        }
                    }
            );
        }
    }

    public void clicksOnEmailNotificationIcon() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByCSS("[id='" + hdMod + "'] a i"))).click();
        waitForCommonPageLoadingElements();
    }

    public void verifyEmailNotCheckBox(String arg1, DataTable emNtCheckBoxes) throws Exception {
        List<List<String>> expENOpts = emNtCheckBoxes.asLists();
        wait.until(ExpectedConditions.visibilityOf(getElementByLocatorAndSearchType(winDialBox, "tagname")));
        isElementPresent(getElementByLocatorAndSearchType(winDialBox, "tagname"));
        verifyActualText(arg1, locateElementByCSS(winDialBox + " h3").getText());
        locateElementsByCss("app-email-preferences label").forEach(p -> actENOpts.add(p.getText()));
        expENOpts.forEach(v -> verifyActualText(v.listIterator().next(), actENOpts.listIterator().next()));
    }

    public void deselectedEmNotCheckBoxOpts(String arg1) throws Exception {
        click(locateElementByXPath("//span[normalize-space()='" + arg1 + "']//../preceding-sibling::input"));
        for (List<String> listEmailNot : listEmailNots) {
            if (listEmailNot.get(0).contains(arg1)) {
                break;
            }
            highLightElement(locateElementByXPath("//span[normalize-space()='" + listEmailNot.get(0) + "']"));
            click(locateElementByXPath("//span[normalize-space()='" + listEmailNot.get(0) + "']//../preceding-sibling::input"));
        }
    }

    public void clicksOnEmNotUpdate() throws Exception {
        clickWithJSE(locateElementByCSS("button[title='Update Email Preference']"));
        handleAlert();
    }

    public void verifyDefaulSelection(String checkBoxStatus, String s1, DataTable emNtCheckBoxes) {
        waitForCommonPageLoadingElements();
        List<List<String>> expENOpts = emNtCheckBoxes.asLists();
        wait.until(ExpectedConditions.visibilityOf(getElementByLocatorAndSearchType(winDialBox, "tagname")));
        expENOpts.forEach(checkBox -> {
            if (checkBoxStatus.equalsIgnoreCase("selected")) {
                locateElementsByXPath("//label[contains(normalize-space(), '" + checkBox.listIterator().next() + "')]/preceding-sibling::input")
                        .forEach(WebElement::isSelected);

            } else {
                locateElementsByXPath("//label[contains(normalize-space(), '" + checkBox.listIterator().next() + "')]/preceding-sibling::input")
                        .forEach(act -> {
                                    boolean t = !act.isSelected();
                                    System.out.println("not selected = " + t);
                                }
                        );
            }
        });
    }

    public void selectACheckBoc(String arg1) throws Exception {
        click(locateElementByXPath("//label[contains(normalize-space(), '" + arg1 + "')]/preceding-sibling::input"));
    }

    public String searchUser(int user){
        return readDataFromExcel(user, "UserName", "testData2", env);
    }
}
