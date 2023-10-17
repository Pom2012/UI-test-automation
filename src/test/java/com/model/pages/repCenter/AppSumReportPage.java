package com.model.pages.repCenter;

import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.apache.commons.collections4.list.TreeList;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.model.pages.repCenter.ReportsCenterPages.*;
import static com.model.utility.DataHelper.readDataFromExcel;

public class AppSumReportPage extends BasePage {
    final String detlASR = "//table[@id='applicationUserDetailReport']",
            userRoleDetRepSelDrop = "#applicationUserDetailReport_length select",
            userRoleDetRepShowEntr = "#applicationUserDetailReport_info",
            userRoleDetRepTableId = "applicationUserDetailReport";

    List<String> actualValues = null;
    List<String> expectedValues = null;
    boolean hasNext = false;
    int curEnt = 0, totEnt = 0;
    private final String[] users =
            {
                    readDataFromExcel(14, "UserName", "testData2", "" + env + ""),
                    readDataFromExcel(11, "UserName", "testData2", "" + env + ""),
                    readDataFromExcel(10, "UserName", "testData2", "" + env + "")
            };

//    public void selectEntriesAndShowingEntriesDynText(String arg1, String arg2, DataTable arg3) throws Exception {
//        cucTabledata = arg3.asLists(String.class);
//        for (int i = 0; i < cucTabledata.get(0).size(); i++) {
//            selectFromDropDownMenu(locateElementByCSS(userRoleDetRepSelDrop), cucTabledata.get(0).get(i));
//            waitForCommonPageLoadingElements();
//            String showingEntries = wait.until(ExpectedConditions.visibilityOf(locateElementByCSS(userRoleDetRepShowEntr))).getText();
//            isElementPresent(locateElementByCSS(userRoleDetRepShowEntr));
//        }
//        selectFromDropDownMenu(locateElementByCSS(userRoleDetRepSelDrop), cucTabledata.get(0).get(0));
//    }

    public void userVerificationInAppSumReport(DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        String webAttr = "applicationUserDetailReport_wrapper", practiceRole, customAttr;
        for (int i = 1, k = 0; i <= 3 || k < 3; i++, k++) {
            userID = users[k];
            practiceRole = cucTabledata.get(i).get(0);
            customAttr = cucTabledata.get(i).get(1);
            actualStatus = cucTabledata.get(i).get(2);
            requestID = readDataFromExcel(i, "ReqID", "reqUserVerif", "AMRVT");
            searchBar("(//div[@id='" + webAttr + "']//input)[1]", requestID);
            clickWithJSE(locateElementByXPath("(//div[@id='" + webAttr + "']//button)[1]"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("" + webAttr + "")));
            expectedText = locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + practiceRole + "')]/following-sibling::td[contains(text(),'" + actualStatus + "')]").getText();
            highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + userID + "')]"));
            highLightElement(locateElementByXPath("//div[@id='" + webAttr + "']//td[contains(text(),'" + customAttr + "')]"));
            verifyActualText(expectedText, actualStatus);
            waitForCommonPageLoadingElements();
        }
    }


    public void searchValueInUserRoleDetailsReport(String report, DataTable colsValues) throws Exception {
        cucTabledata = colsValues.asLists(String.class);
        for (List<String> cucTabledatum : cucTabledata) {
            String key = cucTabledatum.get(0);
            String value = cucTabledatum.get(1);
            if (!key.equalsIgnoreCase("Email")) {
                for (int i = 1; i <= 10; i++) {
                    value = getTableCellTextByReportColAriaLabelAndRow(report, key, Integer.toString(i));
                    if (!value.equals("")) break;
                }
            }
            log.info("Search: " + key + " : " + value);
            locateElementByXPath("//*[@id='" + userRoleDetRepTableId + "_filter']//input").clear();
            type(locateElementByXPath("//*[@id='" + userRoleDetRepTableId + "_filter']//input"), value);
            clickWithJSE(locateElementByXPath("//button[@id='user_detail_btn']"));
            waitForCommonPageLoadingElements();
            longWait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@class='cmmi-banner']")));
            highLightElement(locateElementByXPath("//div[@class='cmmi-banner']"));
            highLightElement(locateElementByID(userRoleDetRepTableId + "_info"));
            highLightElement(locateElementByXPath("//table[@id='" + userRoleDetRepTableId + "']//td[normalize-space()='" + value + "']"));
            //ENH: TABLE: filter should have total row changes (less for on, more for off) - 1 if unique, like Request ID
        }
        locateElementByXPath("//*[@id='" + userRoleDetRepTableId + "_filter']//input").clear();
        clickWithJSE(locateElementByCSS("[id='user_detail_btn']"));
        wait(1000);
        waitForCommonPageLoadingElements();
        waitForExpectedElement(locateElementByID(userRoleDetRepTableId + "_info"));
    }

    public void requestStatusSelectOptioninASR(DataTable arg1) throws Exception {
        cucTabledata = arg1.asLists(String.class);
        for (int i = 0; i < 3; i++) {
            try {
                waitForCommonPageLoadingElements();
                scroll_Down(locateElementByCSS("[id='roleStatus']"));
                selectFromDropDownMenu(locateElementByCSS("[id='roleStatus']"), cucTabledata.get(0).get(i));
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            wait(2000);
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//strong[contains(text(),'User Role Details for')]")));
            scroll_Down(locateElementByCSS("[id='roleStatus']"));
            for (int j = 1; j < 10; j++) {
                try {
                    highLightElement(locateElementByXPath("(//td[normalize-space()='" + cucTabledata.get(0).get(i) + "'])[" + j + "]"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sortTabCols(String desc, String asc, DataTable colsName) throws Exception {
        actualValues = new ArrayList<>();
        expectedValues = new TreeList<>();
        waitForCommonPageLoadingElements();
//        boolean hasPage=getElementByLocatorAndSearchType("#applicationUserDetailReport_paginate","id").isDisplayed();
//        System.out.println("hasPage = " + hasPage);
        hasNext = locateElementByCSS("#applicationUserDetailReport_next").getAttribute("aria-disabled").contains("false");
        System.out.println("hasNext = " + hasNext);
//        selectFromDropDownMenu(locateElementByXPath("//select[@name='applicationUserDetailReport_length']"), "1000");
//        longWait.until(ExpectedConditions.visibilityOf(locateElementByXPath(detlASR)));
        click(locateElementByXPath(detlASR + "//thead//th[" + colHdrNme(colsName) + "]"));
        System.out.println("current entry number = " + currentEntries(showEntriesText));
        System.out.println("total number of entries = " + totalEntriesByXPath(showEntriesText));
        int i = 1;
        while (hasNext) {
            curEnt = currentEntries(showEntriesText);
            totEnt = totalEntriesByXPath(showEntriesText);
            System.out.println("hasNext = " + hasNext);
            wait(3000);
            longWait.until(ExpectedConditions.visibilityOf(locateElementByXPath(detlASR)));
            for (WebElement element : locateElementsByCss(("[id='applicationUserDetailReport'] tbody tr td:nth-of-type(1)"))) {
                wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("[id='applicationUserDetailReport']")));
                try {
                    actualValues.add(element.getText());
                    expectedValues.add(element.getText());
                    System.out.println("element.getText() = " + element.getText());
                } catch (StaleElementReferenceException e) {
                    e.getMessage();
                }
            }
            wait(3000);
            System.out.println("current entries = " + currentEntries(showEntriesText) + " total entries " + totalEntriesByXPath(showEntriesText));
            if (currentEntries(showEntriesText) == totalEntriesByXPath(showEntriesText)) break;
            if (!hasNext) break;
            locateElementByCSS("#applicationUserDetailReport_next").click();
            i++;
        }
        Collections.sort(actualValues);
        Collections.reverse(actualValues);
        Assert.assertEquals(actualValues, expectedValues);
        wait(3000);
    }

    public void navigateToTheReport(String subRepName, String repName) throws Exception {
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpace(repName)));
        scroll_Down(locateEleByXPathTextNormSpace(subRepName));
        highLightElement(locateEleByXPathTextNormSpace(subRepName));
    }
}