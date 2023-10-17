package com.model.pages.userVerif;

import com.model.utility.FileActions;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.model.utility.DataHelper.*;
import static com.model.utility.ExcelFile.writeUVRevSts;

public class UserVerificationPages extends BasePage {
    private final String app = null;
    private static String user = null;
    private String rowText = null;
    private String orgLabels = null;
    private String value = null;
    private final String uvRootWE = "[id='uv-root']";
    private final String uvRootWE1 = "[@id='uv-root']";
    private final String uvPractcheckBox = "(//div[@class='mat-radio-outer-circle'])";
    private final String rewStSelOpts = "//mat-option[@role='option']";
    private final String selectWe = "(//mat-select[@placeholder='Select'])[1]";
    private final String selectWe2 = "(//mat-select[@placeholder='Select'])[2]";
    private final List<String> colVal;
    private final List<String> justOts = new ArrayList<>(Arrays.asList("currentUser", "pastUser", "notApprovedUser"));
    static String expectedInfoIcon = null;

    public UserVerificationPages() throws Exception {
        colVal = new ArrayList<>(Arrays.asList(uvRowVal(1), uvRowVal(2), uvRowVal(3), uvRowVal(4)));
    }

    public void userVerificationSchedulePage(String pageHeader) throws Exception {
        WebElement ele;
        waitForCommonPageLoadingElements();
        int retries = 2;
        while (retries > 0) {
            if (!BasePage.testName.contains("Non-Participant")) {
                ele = longWaitForElementAllowNull("//table[@title='" + pageHeader + "']//tbody//tr[1]", "xpath");
                if (ele != null) break;
            } else {
                ele = longWaitForElementAllowNull("[class='mat-tab-header']", "css");
                if (ele != null) break;
            }
            recoverBrowserAfterError();
            wait(200);
            waitForCommonPageLoadingElements();
            retries--;
        }
        Assert.assertTrue(locateElementByID("uv-root").isDisplayed());
        String text = locateElementByCSS("" + uvRootWE + " h1").getText();
        Assert.assertTrue(text.contains(pageHeader));
//        if (!BasePage.testName.contains("Non-Participant")) {
//            String[] values = {pageHeader + " | ", "#download-section", " Review"};
//            for (String value : values) {
//                if (value.equalsIgnoreCase("#download-section")) {
//                    highLightElement(locateElementByCSS(value));
//                    Assert.assertTrue(locateElementByCSS(value).isDisplayed());
//                    continue;
//                }
//                highLightElement(locateElementByXPathText(value));
//            }
//        }
    }

    public void userVerAndAssosUsPage(String pageHeader) {
        waitForCommonPageLoadingElements();
        String text = locateElementByCSS("[class='main-verification'] h1").getText();
        Assert.assertTrue(text.contains(pageHeader));
        isElementPresent(locateElementByCSS("div.info-section"));
        isElementPresent(locateElementByCSS("#download-section"));
    }

    public void userVerifAssocUsersPage(String arg1, DataTable dt) throws Exception {
        List<List<String>> data = dt.asLists(String.class);
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*" + uvRootWE1 + "//table/tbody")));
        List<WebElement> infoText = locateElementsByXPath("//div[@class='info-section']//div");
        for (int i = 1, k = 0; i <= infoText.size() || k < colVal.size(); i++, k++) {
            orgLabels = locateElementByXPath("//div[@class='info-section']//div[" + i + "]").getText();
            System.out.println("Actual = " + orgLabels.replaceAll("^[^:]*", "").replaceAll(":", "").replaceAll("^[.^]*", "").trim());
            System.out.println("Expected = " + colVal.get(k));
//            Assert.assertTrue("The data does not match", orgLabels.contains(colVal.get(k)));
        }
    }

    public void userVerifAssocUsersWTable(String assosUsrs, DataTable dt) {
        List<List<String>> data = dt.asLists(String.class);
        waitForCommonPageLoadingElements();
        List<WebElement> infoText = getElementList(uvRootWE + " table thead th", "css");
        isElementPresent(locateEleByXPathTextNormSpaceAttr("b", assosUsrs));
        for (int i = 0; i < data.size(); i++) {
            isElementPresent(locateEleByXPathTextNormSpace(data.get(0).get(i)));
        }
    }

    public void userVerifScheduleWebTable(String string, DataTable dataTable) throws Exception {
        List<List<String>> expColNameList1 = dataTable.asLists(String.class);
        List<WebElement> actColNameList = getElementList(uvRootWE + " thead tr th", "css");
        String titleName = null;
        if (!string.contains("Review")) {
            titleName = locateElementByCSS(uvRootWE + " h1").getText();
        } else {
            titleName = locateElementByXPath("//div[2]/h1").getText();
        }
        Assert.assertTrue(titleName.contains(string));
        isElementPresent(locateElementByCSS(uvRootWE + " table"));
        for (int i = 0; i < expColNameList1.get(0).size(); i++) {
            wait(200);
            locateEleByXPathTextNormSpace(actColNameList.get(i).getText()).isDisplayed();
            Assert.assertTrue(actColNameList.get(i).getText().equalsIgnoreCase(expColNameList1.get(0).get(i)));
        }
    }

    public void userVerifScheduleFeatures(String string, DataTable dataTable) throws Exception {
        Map<String, String> keyValues = dataTable.asMap();
        String value;
        for (String key : keyValues.keySet()) {
            value = keyValues.get(key);
            if (value.equals("Search feature")) {
                isElementPresent(locateElementByXPath("//*" + uvRootWE1 + "//div[@class='search-filter']//label"));
            }
            if (value.equals("Button")) {
                isElementPresent(locateElementByXPath("//*" + uvRootWE1 + "//button[@role='button']"));
                Assert.assertTrue(locateElementByXPath("//*" + uvRootWE1 + "//button[@role='button']").getText().equalsIgnoreCase(value));
            }
            if (value.equals("Select dropdown")) {
                locateElementByXPath("//*" + uvRootWE1 + "//mat-select[@role='listbox']").isDisplayed();
                isElementPresent(locateElementByXPath("//*" + uvRootWE1 + "//mat-select[@role='listbox']"));
                wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*" + uvRootWE1 + "//mat-select[@role='listbox']"))).click();
                locateElementByXPath("//*" + uvRootWE1 + "//mat-select[@role='listbox']").click();
            }
        }
    }

    public void reviewUserVerifPractice(DataTable arg1) throws Exception {
        List<List<String>> data = arg1.asLists(String.class);
        //TODO: DATA: Users should not be hard-coded or by row#
        String[] users =
                {readDataFromExcel(11, "UserName", "testData2", "" + env + ""),//IC_VAL_USER2,IC_DEV_USER6
                        readDataFromExcel(14, "UserName", "testData2", "" + env + ""),//IC_VAL_USER3,IC_DEV_USER4
                        readDataFromExcel(10, "UserName", "testData2", "" + env + "") //IDM_VAL_HD2, XXX_DEV_MP3
                };
        String activeUser, pastUserUser;
        for (int i = 1, k = 1; i <= 3 || k < 3; i++, k++) {
            activeUser = data.get(i).get(0);//no
            pastUserUser = data.get(i).get(1);
            if (activeUser.equals("Yes") && pastUserUser.equals("N/A")) {
                user = users[0];//IC_VAL_USER2,IC_DEV_USER6
            } else if (activeUser.equals("No") && pastUserUser.equals("Yes")) {
                user = users[1];//IC_VAL_USER3,IC_DEV_USER4
            } else if (activeUser.equals("No") && pastUserUser.equals("No")) {
                user = users[2];//IC_VAL_USER3,IC_DEV_USER4
            } else {
                System.out.println("No such data");
            }
            String dt = locateElementByXPath("//tbody/tr[1]/td[5]").getText();
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//table[@id='download-section']//mat-select"))).click();
            waitForCommonPageLoadingElements();
            clickWithJSE(locateElementByXPath("//span[normalize-space()='Not Started']"));
            searchBar("(//div[@class='main-verification']//input)[1]", user);
            wait(500);
            waitForCommonPageLoadingElements();
            highLightElement(locateElementByXPath("//*[text() = ' " + user + " ']"));
            switch (activeUser) {
                case "Yes":
                    click(locateElementByXPath("" + uvPractcheckBox + "[1]"));
                    writeUVRevSts("reqUserVerif.xlsx", "UVRevSts", "APPROVED", "" + user + "", "Completed", "" + dt + "", "Yes", "");
                    break;
                case "No":
                    click(locateElementByXPath("" + uvPractcheckBox + "[2]"));
                    switch (pastUserUser) {
                        case "Yes":
                            clickWithJSE(locateElementByXPath("" + uvPractcheckBox + "[3]"));
                            writeUVRevSts("reqUserVerif.xlsx", "UVRevSts", "REJECTED", "" + user + "", "Completed", "" + dt + "", "No", "Yes");
                            break;
                        case "No":
                            clickWithJSE(locateElementByXPath("" + uvPractcheckBox + "[4]"));
                            writeUVRevSts("reqUserVerif.xlsx", "UVRevSts", "REJECTED", "" + user + "", "Completed", "" + dt + "", "No", "No");
                            break;
                    }
            }
            click(locateElementByXPath("//div[@class='mat-checkbox-inner-container']"));
            locateElementByXPath("//textarea[@placeholder='Enter Justification']").sendKeys(data.get(i).get(2));
            click(locateElementByXPath("//div[@class='reviewer-action']//button[2]"));
            isElementPresent(locateElementByCSS("[class='success-message ng-star-inserted']"));
        }
    }

    public void downloadUVSFileVerification(String fileFormat, String pageName) throws Exception {
        waitForCommonPageLoadingElements();
        String btnWE = "//button//span[contains(text(), '" + fileFormat + "')]";
        isElementPresent(locateElementByXPath(btnWE));
        click(locateElementByXPath(btnWE));
        waitForCommonPageLoadingElements();
        System.out.println(FileActions.getDownloadedFileNameByDefaultFileName("User Verification.xlsx", driver));
    }

    public void accessVerificationReportingPage(DataTable arg1) throws Exception {
        cucTabledata = arg1.asLists(String.class);
        expectedText = cucTabledata.get(0).get(0);
        waitForCommonPageLoadingElements();
        String expectedNote = cucTabledata.get(1).get(0) + " " + cucTabledata.get(2).get(0) + " " + cucTabledata.get(3).get(0);
        isElementPresent(locateElementByXPathContainsText(expectedText));
        isElementPresent(locateElementByXPathContainsText(expectedNote));
        Assert.assertEquals(locateElementByXPath("//*" + uvRootWE1 + "//div[contains(@class,'no-records')]").getText(), expectedText);
        Assert.assertEquals(locateElementByCSS(uvRootWE + " div[class='footer-note']").getText(), expectedNote);
    }

    public void reviewStatusSelectOptsVer(String string2, DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        isElementPresent(locateEleByXPathTextNormSpaceAttr("th", string2));
        click(locateElementByXPath("//div[@class='review-status']//span"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(rewStSelOpts)));
        List<WebElement> webElements = locateElementsByXPath(rewStSelOpts);
        for (int i = 1, k = 0; i <= webElements.size() || k < cucTabledata.size(); i++, k++) {
            isElementPresent(locateElementByXPath(rewStSelOpts + "[" + i + "]"));
            System.out.println("Actual review status =   " + locateElementByXPath(rewStSelOpts + "[" + i + "]").getText());
            System.out.println("Expected review status = " + cucTabledata.get(k).get(0));
        }
    }

    public void clickOnUserVerSchInfoIcon() throws Exception {
        click(locateElementByXPath("//*" + uvRootWE1 + "//span[@role='button']"));
    }

    public void verifyUserVerSchInfoIconText(DataTable dataTable) throws Exception {
        List<List<String>> lists = dataTable.asLists();
    }

    public void uvInfoIconVerification(String page, String refText) throws Exception {
        waitForCommonPageLoadingElements();
        String refInfoIcon = locateEleByXPathTextNormSpace(refText).getText();
        Assert.assertEquals(refInfoIcon, refText);
        expectedInfoIcon = readInfoIconData("newApps", "InfoIcon", page, refInfoIcon);
        click(locateElementByXPath("//*[normalize-space(text())='" + refInfoIcon + "']//following-sibling::span"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//*" + uvRootWE1 + "//p[not(contains(@id, 'activeNoSelected'))]")));
        actualText = locateElementByXPath("//*" + uvRootWE1 + "//p[not(contains(@id, 'activeNoSelected'))]").getText();
//        Assert.assertEquals(expectedInfoIcon, actualText);
        click(locateElementByXPath("//*[@class='info-message ng-star-inserted']//span"));
    }

    public void clickOnUVSelectedReviewBtn(String revBtn, String app, String org, String qtr, String stDate) throws Exception {
        waitForCommonPageLoadingElements();
        waitForElementsVisibility(locateElementsByTagName("tr"));
        type(locateElementByCSS("input[placeholder='Search']"), colVal.get(1));
        for (int i = 1; i <= locateElementsByCss(uvRootWE + " tbody tr").size(); i++) {
            if (locateElementsByCss(uvRootWE + " tbody tr").size() == 1) {
                System.out.println(locateElementsByCss(uvRootWE + " tbody tr").size() + " is presented");
                click(locateElementByCSS(uvRootWE + " tbody tr button"));
            } else {
                rowText=locateElementByCSS("[id='uv-root'] tbody tr").getText();
                System.out.println("rowText = " + rowText);
                for (int j = 0; j <= locateElementsByCss(uvRootWE + " tbody tr td").size(); j++) {
                    if (rowText.contains(env2) && rowText.contains(colVal.get(1)) &&
                            rowText.contains(colVal.get(2)) && rowText.contains(colVal.get(3))) {
                        System.out.println("rowText is presented= " + rowText);
                        click(locateElementByCSS(" tbody tr:nth-of-type(" + i + ") button"));
                        System.out.println(" clikced...");
                        rowText = locateElementByCSS(uvRootWE + " tbody tr:nth-of-type(" + i + ")").getText();
                        break;
                    }
                }
            }

        }
    }

    public void userStatusSelectOptsVer(String string, String string2, DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOfAllElements(locateElementByXPath(selectWe)));
        for (int i = 0; i < cucTabledata.size(); i++) {
            if (!cucTabledata.get(i).get(0).equalsIgnoreCase("Unauthorized")) {
                click(locateElementByXPath(selectWe));
                locateEleByXPathTextNormSpaceAttr("span", cucTabledata.get(i).get(0)).click();
                Assert.assertTrue(locateElementByID(justOts.get(i)).getText().contains(cucTabledata.get(i).get(1)));
            } else {
                click(locateElementByXPath(selectWe));
                waitForExpectedElement(locateEleByXPathTextNormSpaceAttr("span", cucTabledata.get(i).get(0)));
                clickWithJSE(locateEleByXPathTextNormSpaceAttr("span", cucTabledata.get(i).get(0)));
                click(locateElementByXPath(selectWe2));
                waitForExpectedElement(locateEleByXPathTextNormSpaceAttr("span", cucTabledata.get(i).get(1))).click();
                wait(1000);
//                Assert.assertTrue(locateElementByID(justOts.get(i)).getText().contains(cucTabledata.get(i).get(1)));
            }
        }
    }

    public void attstnChkboxUnselctdSubmitBtnDisabled(String string, String string2) throws Exception {
        boolean attesCheckBoxDisabled = !locateElementByCSS("input[type='checkbox']").isEnabled();
        boolean submitBtnDisabled = !locateElementByXPath("(//span//button)[3]").isEnabled();
        System.out.println("Check box disabled = " + attesCheckBoxDisabled + "\n" + "Submit button disabled = " + submitBtnDisabled);
    }

    public void selectAnOptionFromReviewStatus(String activeOptd, String string2) throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(selectWe))).click();
        wait.until(ExpectedConditions.visibilityOf(locateEleByXPathTextNormSpaceAttr("span", activeOptd))).click();
    }

    public void clickOnAttestCheckbox(String string) {
        boolean attesCheckBoxDisabled = locateElementByCSS("input[type='checkbox']").isEnabled();
        System.out.println("Check box enabled = " + attesCheckBoxDisabled);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//div[@class='mat-checkbox-inner-container']"))).click();
        } catch (ElementClickInterceptedException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void submitBtnEnabled(String string) throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("(//span//button)[3]")));
        boolean submitBtnDisabled = locateElementByXPath("(//span//button)[3]").isEnabled();
        System.out.println("Submit button disabled = " + submitBtnDisabled);
    }

    public void clickOnNPrtcptUssAndSee(String string, DataTable dataTable) throws Exception {
        click(locateEleByXPathTextNormSpace(string));
    }

    public void nonPrtcptUssReveiwPage(String string, DataTable dataTable) {
        cucTabledata = dataTable.asLists();
        for (WebElement element : locateElementsByCss("div mat-label")) {
            cucTabledata.contains(element.getText());
        }
    }

    public void verifyNPUtitle(String title) throws Exception {
        String str = locateElementByXPath("//*[@class='main-verification']//*[@class='header-label']//h1").getText();
        if (BasePage.button.contains("Review")) {
            verifyActualText(str, title + " | CMS Innovation Center");
        } else {
            verifyActualText(str, title + " | CMS Innovation Center");
        }
    }

    public void selDropDwnVer(DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        for (int i = 0; i < cucTabledata.get(0).size(); i++) {
            for (int j = 0; j < cucTabledata.size(); j++) {
                value = cucTabledata.get(j).get(i);
                if (value.contains("-")) continue;
                if (j == j && i == 0) {
                    if (value.equalsIgnoreCase("Bulk Verification")) {
                        click(locateElementByCSS("[placeholder='" + value + "']"));
                    }
                    wait.until(ExpectedConditions.visibilityOfAllElements(locateElementsByCss("[class='mat-option-text']")));
                    highLightElement(locateElementByCSS("[aria-label='" + value + "']"));
                }
                if (j == j && i == 1) {
                    System.out.println("second column cell values " + cucTabledata.get(j).get(i));
                    if (value.equalsIgnoreCase("Review Status")) {
                        highLightElement(locateElementByCSS("[class='review-status']"));
                        continue;
                    }
                    if (value.equalsIgnoreCase("All")) {
                        clickWithJSE(locateElementByXPath("(//mat-select[@placeholder='" + value + "']//div)[1]"));
                    }
                    highLightElement(locateElementByXPath("//mat-option//span[normalize-space(text())='" + value + "']"));
                }
            }
        }
    }

    public void verifyBtns(DataTable dataTable) {
        waitForCommonPageLoadingElements();
        for (String value : dataTable.asList()) {
            wait(200);
            highLightElement(locateEleByXPathTextNormSpace(value));
        }
    }

    //TODO: WIP
    public void cancelAndBackBtnsVer(String yesBtn, String noBtn, String button, DataTable dataTable) throws
            Exception {
        String windBox = "//mat-dialog-container[contains(@id, 'mat-dialog')]";
        List<String> list = dataTable.asList();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath(windBox)));
        highLightElement(locateElementByXPath(windBox));
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) value = "h1";
            else value = "p";
            isElementPresent(locateElementByXPath(windBox + "//" + value));
        }
    }

    public void nagigatesToUVSchedule(String string) {
        waitForCommonPageLoadingElements();
        isElementPresent(locateEleByXPathContainsNormSpaceAttr("h1", string));
        highLightElement(locateEleByXPathContainsNormSpaceAttr("h1", string));
    }
}