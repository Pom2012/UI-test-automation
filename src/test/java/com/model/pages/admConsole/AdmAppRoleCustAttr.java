package com.model.pages.admConsole;

import com.model.pages.listMngmnt.ListManagementPages;
import com.model.base.BasePage;
import com.model.locators.adminConsoleLoc.ApplicationsManagement_Locators;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class AdmAppRoleCustAttr extends BasePage {
    private static final String tableIDWE = "//table[@id='group-option-detail-view']";
    public List<String> list = new ArrayList<>();
    ListManagementPages listManagMeths = new ListManagementPages();

    public static void simpleListManipulation(DataTable reports) throws Exception {
        cucTabledata = reports.asLists(String.class);
        String entryIdentifier, entryStatusBefore;
        for (List<String> cucTabledatum : cucTabledata) {
            status = cucTabledatum.get(0);
            justification = cucTabledatum.get(1);
            entryIdentifier = locateElementByXPath("(" + tableIDWE + "//td[1])[1]").getText();
            entryStatusBefore = locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[2]/span").getText();
            clickWithJSE(locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[3]/a"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("status")));
            selectFromDropDownMenu(locateElementByID("status"), status);
            type(locateElementByCSS("#rspnsJustification"), justification);
            simpleClick(locateElementByCSS("button[title='Updates selected list entry']"));
            handleAlert(); //occurs when a new list created
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//td[normalize-space()='" + entryIdentifier + "']")));
            highLightElement(locateElementByXPath("//td[normalize-space()='" + entryIdentifier + "']"));
            String listStatusAfter = locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[2]/span").getText();
            Assert.assertEquals(status, listStatusAfter);
        }
        simpleClick(locateElementByXPath("//button[normalize-space()='Update List']"));
    }

    public static void nestedListManipulation(DataTable reports) throws Exception {
        cucTabledata = reports.asLists(String.class);
        String entryIdentifier, entryStatusBefore, listStatusAfter;
        for (List<String> cucTabledatum : cucTabledata) {
            status = cucTabledatum.get(0);
            justification = cucTabledatum.get(1);
            entryIdentifier = locateElementByXPath("(" + tableIDWE + "//td[1])[1]").getText();
            entryStatusBefore = locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[4]/span").getText();
            clickWithJSE(locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[5]/a"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID("status")));
            selectFromDropDownMenu(locateElementByID("status"), status);
            type(locateElementByCSS("#rspnsJustification"), justification);
            simpleClick(locateElementByCSS("button[title='Updates selected list entry']"));
            handleAlert();
            wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//td[normalize-space()='" + entryIdentifier + "']")));
            highLightElement(locateElementByXPath("//td[normalize-space()='" + entryIdentifier + "']"));
            listStatusAfter = locateElementByXPath("(" + tableIDWE + "//td[1])[1]/following-sibling::td[4]/span").getText();
            Assert.assertEquals(status, listStatusAfter);
        }
        simpleClick(locateElementByXPath("//button[normalize-space()='Update List']"));
        System.out.println("Nested List manipulation is over........... ");
    }

    public void moveArrowUpDownInCusAttr(String arrow, String routingCustAttr) throws Exception {
        List<WebElement> beforeMovingList = getElementList("//table[@id='role-attribute-view']//td[4]", "xpath");
        List<String> firstOrderedList = new LinkedList<>();
        beforeMovingList.forEach(a -> {
            firstOrderedList.add(a.getText());
        });
        System.out.print("insertion Order List = " + Collections.singletonList(firstOrderedList));
        String actualElement;
        if (arrow.equalsIgnoreCase("down")) {
            actualElement = firstOrderedList.get(0);
            System.out.println("actualElement = " + actualElement);
            verifyActualText(actualElement, routingCustAttr);
        }
        if (arrow.equalsIgnoreCase("up")) {
            actualElement = firstOrderedList.get(1);
            System.out.println("actualElement = " + actualElement);
            verifyActualText(actualElement, routingCustAttr);
        }
        if (arrow.equalsIgnoreCase("down")) {
            click(locateElementByXPath("//td[contains(text(),'" + routingCustAttr + "')][2]/following-sibling::td[4]//a"));
        }
        if (arrow.equalsIgnoreCase("up")) {
            click(locateElementByXPath("(//td[contains(text(),'" + routingCustAttr + "')][2]/following-sibling::td[4]//a)[1]"));
        }
        waitForCommonPageLoadingElements();
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("(//div[@class='cicdim-title'])[1]")));
    }

    public void selects_sees(String btnOrLinkTxt, String title) throws Throwable {
        click(locateElementByXPath("//a[contains(normalize-space(), '" + btnOrLinkTxt + "')]"));
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@class='cicdim-title' and contains(normalize-space(), '" + title + "')]")));
    }

    public void deleteCustAttrIfPresented() throws Exception {
        List<WebElement> webElements = locateElementsByCss("[id='cmmi'] table tbody tr");
        List<String> rowList = new ArrayList<>();
        if (webElements.size() >= 1 && areElementsPresent(locateElementsByCss("[id='cmmi'] table tbody tr td:nth-of-type(6)"))) {
            log.info("Custom attribute is presented");
            webElements.forEach(each -> rowList.add(each.getText()));
            log.info(rowList.size() + " custom attributes are presented");
            for (int i = 0; i < rowList.size(); i++) {
                simpleClick(locateElementByCSS("[id='cmmi'] table tbody tr td:nth-of-type(6) em"));
                handleAlert();
                waitForCommonPageLoadingElements();
            }
        } else {
            log.info("Custom attribute is not presented");
        }
    }

    public void selectsAddNewAttribute(String btnOrLinkTxt, String title, DataTable dataValues) throws Throwable {
        cucTabledata = dataValues.asLists(String.class);
        deleteCustAttrIfPresented();
        waitForCommonPageLoadingElements();
        String leftCol, rightCol, justif;
        for (List<String> cucTabledatum : cucTabledata) {
            selects_sees(btnOrLinkTxt, title);
            leftCol = cucTabledatum.get(0);
            rightCol = cucTabledatum.get(1);
            justif = leftCol + " & " + rightCol;
            System.out.print("left: " + leftCol + " right: " + rightCol + "\n");
            listManagMeths.createCustomAttributeDefault(leftCol, rightCol, justif);
            simpleClick(locateEleByXPathContainsNormSpaceAttr("button", "Add Attribute"));
            handleAlert();
            waitForCommonPageLoadingElements();
        }
    }

    public void verifyTheMovedArrowUpDownInCusAttr(String routingCustAttr, String arrow) {
        List<WebElement> afterMovingList = getElementList("//table[@id='role-attribute-view']//td[4]", "xpath");
        List<String> secondOrderedList = new LinkedList<>();
        afterMovingList.forEach(a -> {
            secondOrderedList.add(a.getText());
            highLightElement(a);
        });
        System.out.print("insertionOrderList = " + Collections.singletonList(secondOrderedList));
        String actualElement;
        if (arrow.equalsIgnoreCase("down")) {
            actualElement = secondOrderedList.get(1);
            verifyActualText(actualElement, routingCustAttr);
        }
        if (arrow.equalsIgnoreCase("up")) {
            actualElement = secondOrderedList.get(0);
            verifyActualText(actualElement, routingCustAttr);
        }
    }

    public void verifyDynamicCustAttrFeatures(DataTable dataTable) {
        cucTabledata = dataTable.asLists(String.class);
        list.add(locateElementByCSS("[id='dynamicDriverId-outer'] label").getText());
        list.add(locateElementByCSS("[id='dynamicOptionGroupId-selection'] label").getText());
        isElementPresent(locateElementByCSS("[id='dynamicDriverId-outer'] select"));
        isElementPresent(locateElementByCSS("[id='dynamicOptionGroupId-selection'] select"));
        verifyActualText(cucTabledata.get(0).get(0), removeAllAfterString(list.get(0), "*"));
        verifyActualText(cucTabledata.get(0).get(1), removeAllAfterString(list.get(1), "*"));
    }

    public void verifyCustomAttrPage(String strArg1) throws Exception {
        actualText = locateElementByCSS("[id='cmmi'] [title='Custom Attribute table']").getText();
        Assert.assertTrue(actualText.contains("Custom Attribute"));
        Assert.assertTrue(actualText.contains(application));
    }

    public void clickAndVerifDynCusAttr(String string) throws Exception {
        click(locateElementByID("isDynamicHide"));
        waitForCommonPageLoadingElements();
        boolean isDynamicHide = verifyCheckboxIsSelected("isDynamicHide");
        if (isDynamicHide) log.info("checkbox not selected");
    }

    public void selectsAddNewAttributeAndSeeCheckBoxes(String btnOrLinkTxt, String string2) throws Exception {
        click(locateElementByXPath("//a[contains(normalize-space(), '" + btnOrLinkTxt + "')]"));
        waitForCommonPageLoadingElements();
        boolean isDynamicHide = verifyCheckboxIsSelected("isDynamicHide");
        if (!isDynamicHide) System.out.println("checkbox is not selected");
    }

    public void appsRolesPageselecting(ApplicationsManagement_Locators alocators, String appName, String pageName) throws Exception {
        application = appName;
        wait.until(ExpectedConditions.visibilityOf(alocators.applSearchbox));
        searchValue(alocators.applSearchbox, appName);
        click(locateElementByXPath("(//div[@id='cicdim-app-table_wrapper']//i)[3]"));
        String actualPage = locateElementByXPath("//div[@class='cicdim-title-container']//div").getText();
        verifyActualText(actualPage, pageName);
    }
}
