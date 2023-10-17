package com.model.pages.listMngmnt;

import com.model.utility.DataHelper;
import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static com.model.utility.DataHelper.*;
import static com.model.utility.DataHelper.getCsvValue;
import static com.model.base.Constants.UIControlType.tBox;

public class ListManagementPages extends BasePage {

    String titleWE = "//*[@class='cicdim-title']";
    String submitButton = "//*[@id='selected-option']";
    String addNewListSelDropDown = "[id='optionId']";
    String showEntriesSelDropDown = "#list-group-view_length select";
    String searchBoxWE = "input[type='search']";
    String custAttrListLinkBtnWE = "#listlink-btn";
    private final String listSearchBox = "//input[@title='Search within List Management of Application Custom Attributes']";

    public void verifyListOptsFromSelectDropDown(String string, String string2, String string3) throws Exception {
        waitForCommonPageLoadingElements();
        String title = locateElementByXPath(titleWE).getText().substring(0, 48);
        verifyActualText(title, string3);
        highLightElement(locateEleByXPathTextNormSpace(string));
        highLightElement(locateElementByXPath(submitButton));
        String text = locateElementByXPath(submitButton).getText();
        verifyActualText(text, string2);
        Assert.assertFalse(locateElementByXPath(submitButton).isEnabled());
    }

    public void verifyListManagementPage(String string, DataTable dataTable) throws Exception {
        waitForCommonPageLoadingElements();
        String title = locateElementByXPath(titleWE).getText().substring(0, 48);
        verifyActualText(title, string);
        for (List<String> asList : dataTable.asLists(String.class)) {
            if (asList.get(0).contains("select")) {
                isElementPresent(locateElementByCSS(addNewListSelDropDown));
                highLightElement(locateElementByCSS(addNewListSelDropDown));
                isElementPresent(locateElementByCSS(showEntriesSelDropDown));
                highLightElement(locateElementByCSS(showEntriesSelDropDown));
            }
            if (asList.get(0).contains("search")) {
                isElementPresent(locateElementByCSS(searchBoxWE));
                highLightElement(locateElementByCSS(searchBoxWE));
            }
            if (asList.get(0).contains("button")) {
                isElementPresent(locateElementByXPath(submitButton));
                highLightElement(locateElementByXPath(submitButton));
                isElementPresent(locateElementByCSS(custAttrListLinkBtnWE));
                highLightElement(locateElementByCSS(custAttrListLinkBtnWE));
            }
            if (asList.get(0).contains("pagination")) {
                isElementPresent(locateElementByXPath("(//*[@id='list-group-view']//i)[1]"));
                for (WebElement element : locateElementsByXPath("//div[@id='list-group-view_paginate']//button")) {
                    isElementPresent(element);
                    highLightElement(element);
                }
            }
            if (asList.get(0).contains("column")) {
                isElementPresent(locateElementByXPath("//table[@id='list-group-view']//th"));
                for (WebElement element : locateElementsByXPath("//div[@id='list-group-view_paginate']//button")) {
                    isElementPresent(element);
                    highLightElement(element);
                }
            }
        }
    }

    public void verifySubmitBtnActive(String string, DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        for (List<String> list : cucTabledata) {
            selectFromDropDownMenu(locateElementByCSS(addNewListSelDropDown), list.get(0));
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath(submitButton)));
            Assert.assertTrue(locateElementByXPath(submitButton).isEnabled());
        }
    }

    public void verifyAddAppRoleCustomAttributeListPage(String string, DataTable dataTable) {
        cucTabledata = dataTable.asLists(String.class);
        waitForCommonPageLoadingElements();
        highLightElement(locateEleByXPathTextNormSpace(string));
        IntStream.range(0, cucTabledata.size()).forEach(p -> {
            try {
                highLightElement(locateEleByXPathTextNormSpace(cucTabledata.get(p).get(0)));
                isElementPresent(locateEleByXPathTextNormSpace(cucTabledata.get(p).get(0)));
                wait(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createCustomAttributeDefault(String leftCol, String rightCol, String justif) throws Exception {
        String s = (leftCol + rightCol).replaceAll(" ", "").replaceAll("\\+", "");
        String s2 = (leftCol + rightCol).replaceAll("[^A-Z]", "");
        System.out.println("Attribute Display Name = " + s);
        System.out.println("Attribute Acronym = " + s2);
        waitForCommonPageLoadingElements();
        type(locateElementByID("attributeName"), s);
        type(locateElementByID("attributeShortName"), s2);
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByID("attribute-control-type"), leftCol);
        waitForCommonPageLoadingElements();
        selectFromDropDownMenu(locateElementByID("control-type"), rightCol);
        waitForCommonPageLoadingElements();
        if (!rightCol.equalsIgnoreCase(tBox)) {
            selectFromDropDownMenu(locateElementByID("list-option"), "States");
        }
        waitForCommonPageLoadingElements();
        type(locateElementByID("attributeDescription"), justif);
    }

    public void errorsInNestedListVerification(DataTable opts) throws Exception {
        cucTabledata = opts.asLists(String.class);
        String expectedErrorText1 = cucTabledata.get(0).get(1), expectedErrorText2 = cucTabledata.get(1).get(1);
        click(locateElementByXPath("//button[normalize-space()='Add Attribute List']"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='attrOptionListDto.errors']")));
        String actualErrorText1 = locateElementByXPath("//div[@id='attrOptionListDto.errors']").getText();
        System.out.println("Actual error text #1= \n" + actualErrorText1 + "\n");
        System.out.println("Expected error text #1= \n" + expectedErrorText1);
        locateElementByXPath("//input[@id='optionGroupName']").sendKeys("Application Role Custom Attribute Nested List");
        click(locateElementByXPath("//button[normalize-space()='Add Attribute List']"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='attrOptionListDto.errors']")));
        String actualErrorText2 = locateElementByXPath("//div[@id='attrOptionListDto.errors']").getText();
        System.out.println("\nActual error text #2 = \n" + actualErrorText2 + "\n");
        System.out.println("Expected error text #2 = \n" + expectedErrorText2 + "\n");
    }

    public void verifyAndSelectListPage(String expectedPortletName, String listSelectOption) throws Exception {
        highLightElement(locateElementByCSS("div[class='cicdim-title']"));
        String actualPortletName = locateElementByCSS("div[class='cicdim-title']").getText();
        System.out.println("\tActual Portlet name   " + actualPortletName);
        System.out.println("\tExpected Portlet name " + expectedPortletName);
        selectFromDropDownMenu(locateElementByXPath("//select[@id='optionId']"), listSelectOption);
        click(locateElementByXPath("//button[normalize-space()='Submit']"));
    }

    public void addApplRoleCustAttrList(String expectedListPageTitle, String listType) throws Exception {
        String actualListPageTitle = locateElementByXPath("//div[@class='cicdim-title']").getText();
        highLightElement(locateElementByXPath("//div[@class='cicdim-title']"));
        System.out.println("\t Actual List Page Title =   " + actualListPageTitle);
        System.out.println("\t Expected List Page Title = " + expectedListPageTitle);
    }

    public void errorsAreDisplayed(DataTable dataTable) {
        cucTabledata = dataTable.asLists(String.class);
        for (List<String> verifiableText : cucTabledata) {
            verifyElementByTypeClassAndContainsText("div", "error", verifiableText.get(0));
        }
    }

    public void selectCustAttr4Role(String caIcon, String modelRole) throws Exception {
        waitForCommonPageLoadingElements();
        verifyActualText(locateElementByXPath("//td[contains(text(),'" + modelRole + "')][1]").getText(), modelRole);
        highLightElement(locateElementByXPath("//td[contains(text(),'" + modelRole + "')][1]"));
        click(locateElementByXPath("//td[contains(text(),'" + modelRole + "')][1]/following-sibling::td[7]/a"));
        waitForCommonPageLoadingElements();
    }

    public void viewIcon(String attrType, String arg2, String arg3) throws Exception {
        String text = locateElementByXPath("//td[contains(text(),'" + attrType + "')]").getText();
        Assert.assertTrue(text.contains(attrType));
        highLightElement(locateElementByXPath("//td[contains(text(),'" + attrType + "')]"));
        click(locateElementByXPath("//td[contains(text(),'" + attrType + "')]/following-sibling::td[5]/a"));
        waitForCommonPageLoadingElements();
    }

    public void viewIconWinDialoge() throws Exception {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//span[@class='ui-dialog-title']/ancestor::div[2]")));
        highLightElement(locateElementByXPath("//span[@class='ui-dialog-title']/ancestor::div[2]"));
        waitForCommonPageLoadingElements();
        String text = locateElementByCSS("[class='ui-dialog-title']").getText();
        Assert.assertTrue(text.contains("Routing"));
        click(locateElementByXPath("(//span[@class='ui-dialog-title']/ancestor::div[2]//button)[2]"));
        waitForCommonPageLoadingElements();
    }

    public void updateAList(String arg1, String listType) throws Exception {
        envList = getListData("generetedData", "list", listType, env2, "Enabled");
        System.out.println("envList = " + envList);
        type(locateElementByXPath("//input[contains(@title, 'Search')]"), envList);
        highLightElement(locateElementByXPath("//td[normalize-space()='" + envList + "']"));
        click(locateElementByCSS("i[class='fa fa-pencil-square-o']"));
    }

    public void updateUtilList(String arg1, String listType) throws Exception {
        waitForCommonPageLoadingElements();
        type(locateElementByXPath("//input[contains(@title, 'Search')]"), listType);
        click(locateElementByCSS("i[class='fa fa-pencil-square-o']"));
    }

    public void utilUpdatingOfList(String string, DataTable dataTable) throws Exception {
        List<List<String>> lists = dataTable.asLists(String.class);
        List<HashMap<String, String>> userRows = DataHelper.data(string, "Sheet1");
        String searchValue = lists.get(0).get(0), newValue = lists.get(0).get(1);
        for (HashMap<String, String> rowData : userRows) {
            waitForCommonPageLoadingElements();
            wait(2000);
            type(locateElementByCSS("input[type='search']"), rowData.get(searchValue));
            click(locateElementByCSS("button[id='list-item-search-btn']"));
            wait(500);
            click(locateElementByCSS("td a i"));
            waitForCommonPageLoadingElements();
            type(locateElementByCSS("#optionValue"), rowData.get(newValue));
            type(locateElementByCSS("#rspnsJustification"), "SR-1471");
            click(locateElementByCSS("button[type='submit'][title='Updates selected list entry']"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByCSS("table[id='group-option-detail-view']")));
        }
    }

    public void creatingSimpleList(String simlist, String desc, int num, String time) throws Exception {
        listName = simlist + "_" + "list" + time;
        type(locateElementByID("optionGroupName"), listName);
        type(locateElementByID("optionGroupDesc"), desc);
        if (simlist.equalsIgnoreCase("Simple")) {
            for (int i = 0; i <= num; i++) {
                click(locateElementByCSS("#addValueButton"));
                type(locateElementByID("optionValues[" + i + "].optionKey"), "1234" + i + "");
                type(locateElementByID("optionValues[" + i + "].optionValue"), faker.address().fullAddress());
                System.out.println("List Entry #" + i + " key and value were added");
            }
            setListData("generetedData", "list", simlist, listName, env2, "Enabled");
            System.out.println("List Name: " + listName);
        }
        if (simlist.equalsIgnoreCase("From CSV (simple)")) {
            wait(200);
            click(locateElementByCSS("[id='csvToTableView']"));
            wait(10000);
        }
    }

    public void createListFromCsv(String listType, String topLvl, int num, String time) throws Exception {
        //ENH: Review to make more reusable
        String csvFile = listType.toLowerCase() + ".csv";
        listName = csvFile + "_" + "list" + time;
        type(locateElementByID("optionGroupName"), listName);
        selectFromDropDownMenu(locateElementByID("optionGroupParentId"), "States");
        type(locateElementByID("optionGroupDesc"), "New " + listType + " list: " + listName);
        for (int i = 0; i < num; i++) {
            click(locateElementByCSS("#addValueButton"));
            type(locateElementByID("optionValues[" + i + "].optionKey"), "123456" + i + "");
            type(locateElementByID("optionValues[" + i + "].optionValue"), faker.address().fullAddress());
            type(locateElementByID("optionValues[" + i + "].optionNestingGroup"), getCsvValue(csvFile, i, 0));
            type(locateElementByID("optionValues[" + i + "].optionNestingGroupDesc"), getCsvValue(csvFile, i, 1));
            System.out.println("List Entry #" + i + " key and value were added");
        }
        setListData("generetedData", "list", csvFile, listName, env2, "Enabled");
    }

    public void verifyListIsStored() throws Exception {
        scroll_Into_View(locateElementByXPath(listSearchBox));
        type(locateElementByXPath(listSearchBox), listName);
        highLightElement(locateElementByXPath("//td[normalize-space()='" + listName + "']"));
        System.out.println("Creating of a list is over.........................");
    }

    public void listIsStoredInEnv(String list) throws Exception {
        envList = getListData("generetedData", "list", list, env2, "Enabled");
        type(locateElementByXPath(listSearchBox), envList);
        highLightElement(locateElementByXPath("//td[normalize-space()='" + envList + "']"));
    }

    public void verifyAddNewListDropDownOptions(String string, DataTable dataTable) throws Exception {
        cucTabledata = dataTable.asLists(String.class);
        for (List<String> list : cucTabledata) {
            selectFromDropDownMenu(locateElementByCSS("[id='optionId']"), list.get(0));
            wait.until(ExpectedConditions.elementToBeClickable(locateElementByXPath("//*[@id='selected-option']")));
        }
    }
}
