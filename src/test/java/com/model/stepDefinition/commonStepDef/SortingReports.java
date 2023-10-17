package com.model.stepDefinition.commonStepDef;

import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class SortingReports extends BasePage {

    ArrayList<String> actualAscSortedList = null;
    ArrayList<String> actualDescSortedList = null;
    ArrayList<String> expectedDescendSortedList = null;
    ArrayList<String> expectedAscSortedList = null;

    @Then("^user scroll down and sees the \"([^\"]*)\"$")
    public void user_scroll_down_and_sees_the(String arg1) {
        scroll_Down(locateElementByID("appDetailAriaMessageBox"));
    }

    @Then("^clicks on the sortable columns name to order it in \"([^\"]*)\" and \"([^\"]*)\" sequence:$")
    public void clicks_on_the_sortable_columns_name_to_order_it_in_and_sequence(String descending, String ascending, DataTable colsName) throws Throwable {
        cucTabledata = colsName.asLists(String.class);
        actualAscSortedList = new ArrayList<>();
        actualDescSortedList = new ArrayList<>();
        waitForCommonPageLoadingElements();
        String reportTableID = "applicationUserDetailReport";
        int totalSortableColumnNumbers = getTotalSortableColumnNumbers("//table[@id='" + reportTableID + "']//thead//th");
        System.out.println("total sortable columns numbers is = " + totalSortableColumnNumbers);
        getTotalSortableColumnNames("//table[@id='" + reportTableID + "']//thead//th");
        String showingEntries = getTextofWebElementSimple(locateElementByID("applicationUserDetailReport_info"));
        System.out.println("Total Entries = " + totalEntriesNum(showingEntries));
        if (totalEntriesNum(showingEntries) < 1000) {
            List<WebElement> tHeadsValue = driver.findElements(By.xpath("//div[@id='applicationUserDetailReport_wrapper']//thead//th"));
            for(WebElement el:tHeadsValue){
                String tVal=el.getText();
                    if(tVal.equalsIgnoreCase("Request ID"))
                        highLightElement(el);
                }
            }
        System.out.println("Selecting 1000 entries >>>");
        selectFromDropDownMenuByValueAndElement("1000", driver.findElement(By.name("applicationUserDetailReport_length")));
        showingEntries = wait.until(ExpectedConditions.visibilityOf(locateElementByID("applicationUserDetailReport_info"))).getText();
        System.out.println("Showing entries: " + showingEntries);
        scroll_Down(locateElementByXPath("//strong[contains(text(),'User Role Details for')]"));
        System.out.println(cucTabledata.size());
        wait(20000);


//        for (int i = 0; i <cucTabledata.size(); i++) {
//            colName = cucTabledata.get(i).get(0);
//            System.out.println("colName = " + colName);
//            isElementPresent(locateElementByXPath("//div[@id='applicationUserDetailReport_wrapper']//th[contains(text(), '" + colName + "')]"));
//        }
//        for (int i = 0; i < cucTabledata.size(); i++) {
//            List<WebElement> columnsListDesc = null;
//            List<WebElement> columnsListAsc = null;
//            colName = cucTabledata.get(i).get(0);
//            if (i == 5 || i == 11) {continue;}
//            if (colName.equalsIgnoreCase("Role Display Name") || colName.equalsIgnoreCase("Justification")){continue;}
//            System.out.println("Column #"+i+" >> " + colName);
//
////            System.out.println(locateElementByXPath("//th[@aria-label='Request ID: activate to sort column descending']").getText());
////            highLightElement(locateElementByXPath("//div[@id='applicationUserDetailReport_wrapper']//th[contains(text(), '" + colName + "')]"));
//////            //Sorting the column in Descending order
////            clickWithJSE(locateElementByXPath("//table[@id='applicationUserDetailReport']/thead/tr/th[contains(text(), '" + colName + "')]"));
////            wait.until(ExpectedConditions.visibilityOf(locateElementByID("applicationUserDetailReport_info"))).getText();
//////            System.out.println("Clicking to sort the " + colName + " column by descending order");
//////            for (int j = 1; j < 12; j++) {
//////                columnsListDesc = driver.findElements(By.xpath("//table[@id='applicationUserDetailReport']/tbody/tr/td[" + j + "]"));
//////                System.out.println("Retrieving elements from >>>> " + colName);
//////                columnsListDesc.forEach(each -> actualDescSortedList.add(each.getText()));
//////                Collections.sort(expectedDescendSortedList);
//////                if (i == 1) {
//////                    Collections.reverse(expectedDescendSortedList);
//////                }
//////                System.out.println("-- First actual value: " + actualDescSortedList.get(0) + " -- First expected value: " + expectedDescendSortedList.get(0));
//////                System.out.println("-- Last actual value: " + actualDescSortedList.get(actualDescSortedList.size() - 1) + " -- Last expected value: " + expectedDescendSortedList.get(expectedDescendSortedList.size() - 1));
//////                Assert.assertEquals("sorted does not match to actual", actualDescSortedList, expectedDescendSortedList);
//////                System.out.println("The " + colName + " sorted in descending order as expected");
//////
//////                /** Sorting the column in Ascending order */
//////
//////                click(locateElementByXPath("//table[@id='applicationUserDetailReport']/thead/tr/th[contains(text(), '" + colName + "')]"));
//////                wait(3000);
//////                System.out.println("Clicking to sort by ascending order");
//////                columnsListAsc = driver.findElements(By.xpath("//table[@id='applicationUserDetailReport']/tbody/tr/td[" + j + "]"));
//////                ArrayList<String> actualAscSortedList = new ArrayList<>();
//////                columnsListAsc.forEach(each -> actualAscSortedList.add(each.getText()));
//////
//////                ArrayList<String> expectedAscendSortedList = new ArrayList<>(actualAscSortedList);
//////                if (i == 1) {
//////                    Collections.sort(expectedAscendSortedList);
//////                }
//////                System.out.println("-- First actual value: " + actualAscSortedList.get(0) + " -- First expected value: " + expectedAscendSortedList.get(0));
//////                System.out.println("-- Last actual value: " + actualAscSortedList.get(actualAscSortedList.size() - 1) + " -- Last expected value: " + expectedAscendSortedList.get(expectedAscendSortedList.size() - 1));
//////                Assert.assertEquals("sorted does not match to actual", actualAscSortedList, expectedAscendSortedList);
//////                System.out.println("The " + colName + " sorted in ascending order as expected");
//////
//////                System.out.println(i);
//////            }
////
//        }
    }
}
