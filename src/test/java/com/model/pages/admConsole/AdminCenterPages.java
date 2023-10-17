package com.model.pages.admConsole;

import com.model.base.BasePage;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.model.pages.repCenter.ReportsCenterPages.templates;
import static com.model.utility.DataHelper.*;
import static com.model.utility.ExcelFile.writeAppDataToExc;
import static com.model.base.Constants.userInactiveMessage.msgForUserInactWrn;

public class AdminCenterPages extends BasePage {
    public String appDispName = null;
    public String appAcroName = null;
    public String appName = null;
    public Date date3 = new Date();
    public Date year = new Date();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat dayForm = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat timeForm = new SimpleDateFormat("HHmmss");//
    public String timeAsNum = timeForm.format(date3);
    public String date = dayForm.format(date3);
    public String xPathVal4SelectWE = "//*[@id='cicdim-app-table_length']//select";
    public String showingEntriesWebEl = "cicdim-app-table_info";
    public String paginationWebEleID = "cicdim-app-table_paginate";
    private final static String expectedNewRole = "Updated Custom Attribute";
    ApplicationsManagement_Locators alocators = PageFactory.initElements(driver, ApplicationsManagement_Locators.class);

    public static void deleteCustomAttribute(String colName) throws Exception {
        listOfWE = getElementList("//table[@id='role-attribute-view']//td[4]", "xpath");
        int listSize = listOfWE.size();
        for (int i = 1; i <= listSize; i++) {
            System.out.println("list.size() = " + listOfWE.size());
            try {
                simpleClick(locateElementByXPath("(//table[@id='role-attribute-view']//td[6]//a)[1]"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            handleAlert();
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//div[@class='cicdim-title'])[1]")));
        }
    }

    public static void updateCustomAttribute(String colName) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//div[@class='cicdim-title'])[1]")));
        clickWithJSE(locateElementByXPath("(//table[@id='role-attribute-view']//td[5])[1]/a"));
        waitForCommonPageLoadingElements();
        type(locateElementByID("attributeName"), expectedNewRole);
        type(locateElementByID("attributeShortName"), "UCA");
        simpleClick(locateEleByXPathContainsNormSpaceAttr("button", "Update"));
        handleAlert();
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//div[@class='cicdim-title'])[1]")));
        String actualNewRole = locateElementByXPath("//td[contains(text(),'" + expectedNewRole + "')]").getText();
        highLightElement(locateElementByXPath("//td[contains(text(),'" + expectedNewRole + "')]"));
        verifyActualText(expectedNewRole, actualNewRole);
    }

    public static void cucumberDataTableReadingMeths(String a, DataTable cucData) {
        cucTabledata = cucData.asLists(String.class);
        highLightElement(locateEleByXPathTextNormSpace(a));
        if (a.equalsIgnoreCase("Associated Users:")) {
            for (int i = 1; i <= 5; i++) {
                try {
                    highLightElement(locateElementByXPath("(//mat-form-field)[" + i + "]"));
                    if (i <= 3)
                        highLightElement(locateElementByXPathText(" " + cucTabledata.get(0).get(i)));
                    if (i <= 2) {
                        highLightElement(locateElementByXPathText(cucTabledata.get(1).get(i)));
                        highLightElement(locateElementByXPathText(cucTabledata.get(2).get(i)));
                    }
                    if (i == 1) {
                        highLightElement(locateElementByXPathText(cucTabledata.get(3).get(i)));
                        highLightElement(locateElementByXPathText(cucTabledata.get(4).get(i)));
                        highLightElement(locateElementByXPathText(cucTabledata.get(5).get(i)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void roleMappingVerification(String btn, DataTable tableValue) throws Throwable {
        cucTabledata = tableValue.asLists(String.class);
        switch (btn) {
            case "Role Mapping":
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='cicdim-app-table_wrapper']//tbody")));
                locateElementByXPathText(btn).click();
                waitForCommonPageLoadingElements();
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("*[id='skipToContnet'] tbody")));

                System.out.println("\nbutton clicked = " + btn + "\n");
                for (int i = 0; i < cucTabledata.size(); i++) {
                    if (i == 0) {
                        highLightElement(locateEleByXPathTextNormSpace(cucTabledata.get(i).get(1)));
                        System.out.println("i = " + i + " value: " + cucTabledata.get(i).get(1));
                    }
                    if (i == 1) {
                        highLightElement(locateElementByXPath("//*[@id='skipToContnet']//table"));
                    }
                    if (i == 2) {
                        highLightElement(locateElementByXPathContainsText(cucTabledata.get(i).get(1)));
                        System.out.println("i = " + i + " value: " + cucTabledata.get(i).get(1));
                    }
                    if (i == 3) {
                        highLightElement(locateElementByXPath("(//button//span[@class='mat-button-wrapper'])[2]"));
                    }
                    if (i == 4) {
                        highLightElement(locateElementByXPath("(//button//span[@class='mat-button-wrapper'])[3]"));
                    }
                    if (i == 5) {
                        highLightElement(locateElementByCSS("div[class='actions'] button"));
                        highLightElement(locateElementByXPath("//*[@id='skipToContnet']//input"));
                    }
                }
                break;
            case "Add New Role Mapping":
                click(locateElementByXPathContainsText(btn));
                waitForCommonPageLoadingElements();
                System.out.println("\nbutton clicked = " + btn + "\n");
                for (int i = 0; i < cucTabledata.size(); i++) {
                    if (i == 0)
                        highLightElement(locateElementByCSS("div.header-label"));
                    if (i >= 1 && i <= 4)
                        highLightElement(locateElementByXPath("//div[normalize-space()='" + cucTabledata.get(i).get(1) + "']"));
                    if (i >= 5)
                        highLightElement(locateElementByXPathContainsText(cucTabledata.get(i).get(1)));
                }
                click(locateElementByXPathContainsText("Back"));
                break;
            case "Map":
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='skipToContnet']//table")));
                clickWithJSE(locateElementByXPath("(//button//span[@class='mat-button-wrapper'])[2]"));
                waitForCommonPageLoadingElements();
                System.out.println("\nbutton clicked = " + btn + "\n");
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='skipToContnet']//table")));
                for (int i = 0; i < cucTabledata.size(); i++) {
                    if (i == 0)
                        highLightElement(locateElementByCSS("div.header-label"));
                    if (i >= 1)
                        highLightElement(locateElementByXPathContainsText(cucTabledata.get(i).get(1)));
                }
                break;
            case "Add Custom Attributes Role Mapping":
                click(locateElementByXPathContainsText(btn));
                System.out.println("\nbutton clicked = " + btn + "\n");
                for (int i = 0; i < cucTabledata.size(); i++) {
                    if (i == 0)
                        highLightElement(locateElementByCSS("div.header-label"));
                    if (i >= 1)
                        highLightElement(locateElementByXPathContainsText(cucTabledata.get(i).get(1)));
                }
                click(locateElementByXPathContainsText("Back"));
                break;
        }
    }

    public void creatingApps(DataTable appfields) throws Throwable {
        cucTabledata = appfields.asLists(String.class);
        appName = (cucTabledata.get(0).get(1) + " " + timeAsNum);
        appDispName = (cucTabledata.get(1).get(1) + " " + timeAsNum);
        appAcroName = (cucTabledata.get(2).get(1) + timeAsNum);
        appDipAcroName = (cucTabledata.get(3).get(1) + timeAsNum);

        //TODO: DATA: Users should not be hard-coded or by row#
        userID = readDataFromExcel(1, "UserName", "testData2", "" + env + "");
        System.out.println(cucTabledata.size());
        for (int i = 0; i < cucTabledata.size(); i++) {
            if (i == 0 || i == 1) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), cucTabledata.get(i).get(1) + " " + timeAsNum);
            } else if (i == 2 || i == 3) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), cucTabledata.get(i).get(1) + timeAsNum);
            } else if (i == 5) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), date);
            } else if (i == 7) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), cucTabledata.get(i).get(1) + appDipAcroName);
            } else if (i == 8) {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), userID);
            } else {
                type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(i).get(0) + "']"), cucTabledata.get(i).get(1));
            }
        }
        wait(300);
        writeAppDataToExc("newApps.xlsx", "App&Env2", "" + date + "",
                "" + appName + "", "" + appDipAcroName + "", "" + userID + "",
                "" + environment + "", "Enabled", "Disbled", "Enabled");
    }

    public void creatingRoles(DataTable rolefields) throws Throwable {
        cucTabledata = rolefields.asLists(String.class);
        for (int i = 0; i < modelRoles.length; i++) {
            type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(0).get(0) + "']"), modelRoles[i]);
            type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(1).get(0) + "']"), modelRoles[i]);
            selectFromDropDownMenu(locateElementByID("parentId"), appDipAcroName + " Business Owner");
            type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(3).get(0) + "']"), modelRolesAcronym[i]);
            type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(4).get(0) + "']"), cucTabledata.get(4).get(1));
            type(locateElementByXPath("//*[@placeholder='" + cucTabledata.get(5).get(0) + "']"), cucTabledata.get(5).get(1));
            simpleClick(locateElementByXPath("//button[normalize-space()='Add Application Role']"));
            handleAlert();
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//button[normalize-space()='Add New Role']")));
            simpleClick(locateElementByXPath("//button[normalize-space()='Add New Role']"));
        }
    }

    public void userInactivityAmdManagement(DataTable cucData) {
        cucTabledata = cucData.asLists(String.class);
        for (int i = 1; i <= 3; i++) {
            try {
                highLightElement(locateElementByXPathText(cucTabledata.get(0).get(i)));
                highLightElement(locateElementByXPath("(//div[@class='ng-star-inserted']//mat-label/following-sibling::input)[" + i + "]"));
                highLightElement(locateElementByXPath("(//div[@class='ng-star-inserted']//button)[" + i + "]"));
                highLightElement(locateElementByXPathText(cucTabledata.get(2).get(i)));
                if (i <= 2) {
                    highLightElement(locateElementByXPathText(cucTabledata.get(3).get(i)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appManagementColNamesVer(DataTable dataTable) {
        cucTabledata = dataTable.asLists(String.class);
        listOfWE = locateElementsByCss("[id='cicdim-app-table'] th");
        for (int i = 0; i < cucTabledata.size(); i++) {
            actualText = listOfWE.get(i).getText().replaceAll("[^:]+:", "").trim();
            expectedText = String.valueOf(cucTabledata.get(i)).replace("[", "").replace("]", "");
            waitForElementsVisibility(locateElementsByTagName("th"));
            wait(1000);
            System.out.println("actualText: " + actualText + ", expectedText: " + expectedText);
            verifyActualText(expectedText, actualText);
        }
    }

    public void searchAppAndVerifyStatus(String arg1) throws Exception {
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(alocators.applSearchbox));
        String application = getAppNameFromExcFile("newApps", "Apps", env2);
        type(alocators.applSearchbox, application);
        //ENH: The color and hex values may be changed.  May want to use different criteria
        if (arg1.equalsIgnoreCase("red")) {
            String color = locateElementByXPath("//i[@class=' button-red fa fa-times ']").getCssValue("color");
            getColorValue(color, "#ff0000");
        } else if (arg1.equalsIgnoreCase("green")) {
            String color = locateElementByXPath("//i[@class=' button-green fa fa-check ']").getCssValue("color");
            getColorValue(color, "#008000");
        }
        click(locateElementByXPath("//*[@id='cicdim-app-table']/tbody/tr/td[5]"));
    }

    public void searchAppCheckStatusAndClick(String application, String currentStatus, String actionText) throws Exception {
        //ENH: Replace above color-based with the text of button/link
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(alocators.applSearchbox));
        type(alocators.applSearchbox, application);
        waitForCommonPageLoadingElements();
        waitForExpectedElement(locateElementByXPath("//table/tbody/tr[contains(., '" + application + "')]"));
        verifyAppCurrentStatus(application, currentStatus);
        click(locateElementByXPath("//table/tbody/tr[contains(., '" + application + "')]//a[contains(@title, '" + actionText.toLowerCase() + "')]"));
    }

    public void emailTempleteBuilderInAdminManage(String label, String appName, String search, String addFeature) throws Exception {
        highLightElement(locateElementByXPathText(label));
        highLightElement(locateElementByXPathText(appName));
        highLightElement(locateElementByXPathText(search));
        highLightElement(locateElementByXPathText(" " + addFeature));
        highLightElement(locateElementByXPathText("Back"));
        highLightElement(locateElementByCSS(".mat-elevation-z8 table"));
    }

    public void emailTempleteViews(String addFeature, String addFeature2) throws Exception {
        switch (addFeature) {
            case "Add New Template":
                locateElementByXPathText(" " + addFeature).click();
                isElementPresent(locateElementByXPathText(" " + addFeature + " "));
                isElementPresent(locateElementByXPath("HTML"));
                break;
            case "Update Template":
                locateElementByXPath("(//input[@placeholder='Search'])[1]").sendKeys(templateName);
                click(locateElementByXPathText(" edit"));
                isElementPresent(locateElementByXPathText(" Update Template "));
                break;
        }
    }

    public void clickAndSwichEmailTempleteViews(String clickBtn, String arg2) throws Exception {
        switch (clickBtn) {
            case "Add New Template":
                locateElementByXPathText(" " + clickBtn).click();
                break;
            case "Update Template":
                click(locateElementByXPathText(" Update Template"));
                break;
        }
    }

    public void appUserRoleHierarchyTable(String string) throws Exception {
        waitForCommonPageLoadingElements();
        wait(3000);
        scroll_Down(locateElementByXPath("//table[@class='cicdim-role-table']"));
        wait(2000);
        isElementPresent(locateElementByXPath("//table[@class='cicdim-role-table']"));
        String appUserRoleHierarchyTitle = locateElementByXPath("//table[@class='cicdim-role-table']//th").getText();
        System.out.println("appUserRoleHierarchyTitle = " + appUserRoleHierarchyTitle);
        isElementPresent(locateElementByID("role_tree_div"));
        scroll_Into_View(locateElementByID("role_tree_div"));
        Assert.assertTrue(appUserRoleHierarchyTitle.contains(string));
    }

    public void verifyFirstAndPreviousIsDisabled(String first, String previous, String disabled, String page, String selectedEntryValue) throws Exception {
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByXPath(xPathVal4SelectWE), selectedEntryValue);
        type(locateElementByXPath("//*[@id='" + paginationWebEleID + "']//input"), page);
        String totalPages = locateElementByXPath("(//*[@id='" + paginationWebEleID + "']//span)[2]").getText();
        System.out.println("Total pages = " + totalPages);
        String actualEntry = locateElementByID(showingEntriesWebEl).getText();
        String entryValue = getEntryValue(actualEntry, "to", "of");
        System.out.println("Full text = " + actualEntry);
        System.out.println("text = " + entryValue);
        verifyActualText(selectedEntryValue, entryValue);
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='" + paginationWebEleID + "']//button"));
        for (WebElement element : elements) {
            String elAttribute = element.getAttribute("class");
            if (elAttribute.contains(disabled)) {
                System.out.println("The " + element.getText().replaceAll("Application", "").trim()
                        + " button is " + disabled);
            }
        }
    }

    public void verifyNextPreviousFirstLastBts(String selectedEntryValue, DataTable pagesTextValue) throws Exception {
        List<List<String>> lists = pagesTextValue.asLists(String.class);
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByXPath(xPathVal4SelectWE), selectedEntryValue);
        scroll_Into_View(locateElementByID(showingEntriesWebEl));
        waitForCommonPageLoadingElements();
        wait(1000);
        String actualEntry = locateElementByID(showingEntriesWebEl).getText();
        String entryValue = getEntryValue(actualEntry, "to", "of");
        verifyActualText(selectedEntryValue, entryValue);
    }

    public void verifyAppStatusAfterUpdatingIt(String application, String expectedStatus, String expectedColor) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(alocators.applSearchbox));
        type(alocators.applSearchbox, application);
        Assert.assertTrue(expectedColor.equalsIgnoreCase(verifyAppCurrentStatus(application, expectedStatus)));
    }

    public String verifyAppCurrentStatus(String application, String currentStatus) throws Exception {
        String color = currentStatus.equalsIgnoreCase("enabled") ? "green" : "red";
        Assert.assertNotNull(waitForExpectedElement(locateElementByXPath("//tr//td[contains(.,'" + application + "')]/parent::tr//td" +
                "//*[contains(@class, 'button-" + color + "')]")));
        return color;
    }

    public void verifyManageAppPage(String string) {
        waitForCommonPageLoadingElements();
        waitForElementsVisibility(locateElementsByTagName("tr"));
        System.out.println("Page title is: " + locateElementByCSS("div [class='cicdim-title add-app-info']").getText());
    }

    public void userVerificationManagement(DataTable practice) throws Throwable {
        String uvDate2 = dateFormat.format(date3), mainWE = "//div[@id='skipToContnet']//form//mat-form-field";
        String uvYear = yearFormat.format(year);
        List<List<String>> practiceData = practice.asLists(String.class);
        type(locateElementByXPath(mainWE + "[1]//input"), practiceData.get(0).get(1));
        locateElementByXPath(mainWE + "[1]//input").sendKeys(Keys.TAB);
        wait(2000);
        click(locateElementByXPath("//div[@id='skipToContnet']//form//mat-form-field[2]//mat-select"));//
        wait(200);
        click(locateElementByXPath("//span[normalize-space()='" + uvYear + "']"));
        click(locateElementByXPath(mainWE + "[3]//mat-select"));//
        wait(200);
        click(locateElementByXPath("//span[@class='mat-option-text'][normalize-space()='" + practiceData.get(2).get(1) + "']"));
        type(locateElementByCSS("#mat-input-3"), uvDate2);//start Date
        type(locateElementByCSS("#mat-input-4"), uvDate2);//End Date
        click(locateElementByCSS(".mat-focus-indicator.add-button.mat-raised-button.mat-button-base"));
        waitForCommonPageLoadingElements();
        for (int i = 1; i <= 3; i++) {
            String text = locateElementByXPath("(//table[@role='grid'])[1]//td[1]").getText();
            if (text.contains("Hollywood Primary Care")) {
                highLightElement(locateElementByXPath("(//table[@role='grid'])[1]//td[1]"));
                break;
            }
        }
    }

    public void searchApp4UV(String appId, String appName) throws Exception {
        isElementPresent(alocators.applSearchbox);
        alocators.applSearchbox.sendKeys(appId);
        highLightElement(locateElementByXPathText(appName));
    }

    public void clickOnShieldIcon(String appName) throws Exception {
        isElementPresent(locateElementByXPath("//td[normalize-space()='" + appName + "']"));
        isElementPresent(locateElementByCSS("i[class='fa fa-shield updateLink']"));
        clickWithJSE(locateElementByCSS("i[class='fa fa-shield updateLink']"));
    }

    public void uvScheduleTabsPages(String userVerificationPage, String appName) {
        isElementPresent(getElementByLocatorAndSearchType("//*[text() = '" + userVerificationPage + " | ']", "xpath"));
        isElementPresent(locateEleByXPathTextNormSpace(appName));
    }

    public void navigateToUsInactPage(String appId) throws Exception {
        highLightElement(alocators.applSearchbox);
        alocators.applSearchbox.sendKeys(appId);
        highLightElement(locateElementByXPathText(appId));
        webElement = locateElementByCSS("tbody>tr>:nth-child(7),tbody>td>:nth-child(7)");
        highLightElement(webElement);
        click(webElement);
    }

    public void clickAndSeePageRoleMap(String btn, String page) throws Exception {
        switch (btn) {
            case "Role Mapping":
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPathText(btn))).click();
                highLightElement(locateEleByXPathTextNormSpace(page));
                type(locateElementByXPath("//*[@id='skipToContnet']//input"), "");
                break;
            case "Map":
                break;
            case "Add Custom Attribute Mapping":
                break;
        }
    }

    public void editingRoleMapp(String tarApp, String srcApp, String btn) throws Exception {
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*[@id='skipToContnet']//table")));
        type(locateElementByXPath("//*[@id='skipToContnet']//input"), srcApp);
        //ENH: TABLE: HEADER: may return > 1 row, or switch target and src; search for //td[count(//table[contains(@aria-label for better way.
        click(locateElementByXPath("//tr[contains(.,'" + tarApp + "') and contains(.,'" + srcApp + "')]//button[contains(.,'" + btn + "')]"));
        wait(300);
    }

    public void disableAppsRoleMapp(String checkBox, String page) throws Exception {
        waitForCommonPageLoadingElements();
        click(locateElementByXPathContainsText(page));
        highLightElement(locateElementByXPathContainsText(checkBox));
        if (!locateElementByID("mat-checkbox-1-input").isSelected())
            clickWithJSE(locateElementByID("mat-checkbox-1-input"));
        click(locateElementByXPathContainsText("Submit"));
    }

    public void deselectDisableStatusFromAppsRoleMap(String checkBox, String page) throws Exception {
        highLightElement(locateElementByCSS("div.header-label"));
        click(locateElementByXPathContainsText(page));
        highLightElement(locateElementByXPathContainsText(checkBox));
        if (locateElementByID("mat-checkbox-3-input").isSelected())
            clickWithJSE(locateElementByID("mat-checkbox-3-input"));
        click(locateElementByXPathContainsText("Submit"));
    }

    public void roleMappVerifInUserProfile(String sentBtn, DataTable dataValues) throws Exception {
        cucTabledata = dataValues.asLists(String.class);
        wait(3000);
        //TODO: DATA: Users should not be hard-coded or by row#
        userID = readDataFromExcel(5, "UserName", "testData2", env);
        System.out.println("userID = " + userID);
        locateElementByID("userProfileForm_ic-input_eidmId_0").sendKeys(userID);
        wait(1000);
        selectFromDropDownMenu(locateElementByID("userProfileForm_ic-select_appShortName_1"), cucTabledata.get(1).get(1));
        selectFromDropDownMenu(locateElementByID("userProfileForm_ic-select_version_2"), cucTabledata.get(2).get(1));
        click_bytext(sentBtn);
        wait(5000);
    }

    public void jsonTextVerificationInUserProfile(DataTable dataValues) {
        List<List<String>> cucTabledata = dataValues.asLists(String.class);
        String usrProfileJson = locateElementByID("usrProfileJson").getText();
        System.out.println("usrProfileJson: " + usrProfileJson);
        for (List<String> keyValue : cucTabledata) {
            String findEntry = "\"" + keyValue.get(0) + "\": \"" + keyValue.get(1) + "\"";
            log.info("JSON: Contains: " + findEntry + " : " + usrProfileJson.contains(findEntry));
            Assert.assertTrue("FAILED: Expected JSON to Contain: " + findEntry + " : " + usrProfileJson.contains(findEntry), usrProfileJson.contains(findEntry));
        }
    }

    public void prtcpntUsersTabPageVerif(String string, String string2, DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        log.info(string + " page is loading");
        actualText = locateEleByXPathContainsNormSpaceAttr("h1", string).getText();
        Assert.assertTrue(actualText.contains(string));
        if (string2.equalsIgnoreCase("Practice Users")) {
            Assert.assertTrue(locateElementByXPath("(//div[@class='mat-tab-label-content'])[1]").getText().contains(string2));
        } else {
            Assert.assertTrue(locateElementByXPath("(//div[@class='mat-tab-label-content'])[2]").getText().contains(string2));
        }
        List<List<String>> dtInf = dataTable.asLists(String.class);
        for (int cols = 0; cols < dtInf.size(); cols++) {
            for (int rows = 1; rows <= dtInf.get(0).size() - 1; rows++) {
                if (dtInf.get(cols).get(rows).contains("-")) {
                    continue;
                } else {
                    highLightElement(locateElementByXPathContainsText(dtInf.get(cols).get(rows)));
                    wait(500);
                }
            }
        }
        wait(1000);
    }

    public void verifyAddNewQuarter4NonPrtUssPage(String string, String string2, String string3, DataTable dataTable) throws Exception {
        List<List<String>> dtInf = dataTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        isElementPresent(locateEleByXPathTextNormSpace(string));
        isElementPresent(locateEleByXPathTextNormSpace(string2));
        isElementPresent(locateEleByXPathTextNormSpace(string3));
        for (cols = 0; cols < dtInf.size(); cols++) {
            System.out.println("Table Cols size= " + (dtInf.size()) + "\nTable Rows size " + (dtInf.get(0).size() - 1));
            for (rows = 1; rows <= dtInf.get(0).size() - 1; rows++) {
                System.out.println("cols = " + cols + " rows =" + rows);
                if (dtInf.get(cols).get(rows).contains("-")) {
                    continue;
                } else {
                    if (dtInf.get(cols).get(rows).contains("cancel")) {
                        highLightElement(locateElementByXPath("//button//*[contains(text(), '" + dtInf.get(cols).get(rows) + "')]"));
                    } else {
                        highLightElement(locateElementByXPathContainsText(dtInf.get(cols).get(rows)));
                    }
                    wait(500);
                }
            }
        }
        wait(1000);
    }

    public void updateExistedPractice(String string, DataTable dt) throws Exception {
        Map<String, String> key = dt.asMap();
        type(locateElementByCSS("input[placeholder='Search']"), key.get("My Practice Selection"));
        List<WebElement> pracList = locateElementsByCss("[role='grid'] tbody tr td");
        for (WebElement element : pracList) {
            highLightElement(element);
        }
        click(locateElementByXPath("//*[normalize-space(text())='" + string + "']//span"));
        waitForCommonPageLoadingElements();
    }

    public void selectAppProperties(DataTable dataTable) {
        List<List<String>> dtInf = dataTable.asLists(String.class);
        for (List<String> list : dtInf) {
            System.out.println("list = " + list);
        }

    }

    public void inputDateForUserInactivity(String conBtn, DataTable arg1) throws Throwable {
        cucTabledata = arg1.asLists(String.class);
        for (int i = 2, j = 0; i <= 4 && j <= 2; i++, j++) {
            locateElementByXPath("(//div[@class='ng-star-inserted']//input)[" + i + "]").clear();
            locateElementByXPath("(//div[@class='ng-star-inserted']//input)[" + i + "]").sendKeys(cucTabledata.get(j).get(1));
        }
        clickWithJSE(locateEleByXPathTextNormSpace(conBtn));
    }

    public void userInactivityTemplateSetupVerification() throws Exception {
        String rootWE = "class='email-table ng-star-inserted'";
        if (isElementPresent("//*[contains(@class, 'email-table')]//tbody//tr[1]", "xpath")) {
            if (locateElementByCSS("[" + rootWE + "] tbody").isDisplayed()) {
                int size = locateElementsByXPath("//*[@" + rootWE + "]//td[6]").size();
                for (int i = size; i >= 1; i--) {
                    click(locateElementByXPath("(//*[@" + rootWE + "]//td[6])[" + i + "]"));
                }
            }
        }
    }

    public void creatingUsInacWarning(String wrnSt, String saveBtn, DataTable cucTab) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String uvdate = dateFormat.format(date3);
        cucTabledata = cucTab.asLists(String.class);
        String template;
        switch (wrnSt) {
            case "Warning State":
                for (int i = 1; i <= 3; i++) {
                    template = cucTabledata.get(0).get(1) + " " + i + " " + uvdate + "";
                    templates.add(template);
                    clickWithJSE(locateEleByXPathTextNormSpace(textA));
                    highLightElement(locateEleByXPathTextNormSpace(textB));
                    locateElementByXPath("(//*[normalize-space(text())='" + cucTabledata.get(0).get(0) + "']/ancestor::div//input)[" + 2 + "]").sendKeys(template);
                    locateElementByXPath("(//*[normalize-space(text())='" + cucTabledata.get(1).get(0) + "']/ancestor::div//input)[" + 3 + "]").sendKeys(cucTabledata.get(1).get(1));
                    locateElementByXPath("//*[@placeholder='" + cucTabledata.get(2).get(0) + "']/ancestor::div//textarea").sendKeys(msgForUserInactWrn);
                    locateElementByXPath("//*[normalize-space(text())='" + cucTabledata.get(3).get(0) + "']//mat-select").click();
                    wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//div[@class='cdk-overlay-container']//mat-option[@ng-reflect-value='" + i + "']"))).click();
                    clickWithJSE(locateEleByXPathTextNormSpaceAttr("button", saveBtn));
                }
                break;
            case "Rejection":
                template = cucTabledata.get(0).get(1) + " " + 4 + " " + uvdate + "";
                templates.add(template);
                clickWithJSE(locateEleByXPathTextNormSpace(textA));
                highLightElement(locateEleByXPathTextNormSpace("Email Template"));
                locateElementByXPath("(//*[normalize-space(text())='" + cucTabledata.get(0).get(0) + "']/ancestor::div//input)[" + 2 + "]").sendKeys(template);
                locateElementByXPath("(//*[normalize-space(text())='" + cucTabledata.get(1).get(0) + "']/ancestor::div//input)[" + 3 + "]").sendKeys(cucTabledata.get(1).get(1));
                locateElementByXPath("//*[@placeholder='" + cucTabledata.get(2).get(0) + "']/ancestor::div//textarea").sendKeys(msgForUserInactWrn);
                locateElementByXPath("//*[normalize-space(text())='" + cucTabledata.get(3).get(0) + "']//mat-select").click();
                wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//div[@class='cdk-overlay-container']//mat-option[@ng-reflect-value='" + 4 + "']"))).click();
                clickWithJSE(locateEleByXPathTextNormSpaceAttr("button", saveBtn));
                break;
        }
    }


}

