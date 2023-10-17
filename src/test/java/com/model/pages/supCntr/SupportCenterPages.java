package com.model.pages.supCntr;

import com.model.utility.Data;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.model.utility.DataHelper.readDataFromExcel;
import static com.model.utility.Model_CommonFunctions.*;
import static com.model.base.BasePage.templateName;

public class SupportCenterPages {
    //TODO: DATA: Users should not be hard-coded or by row#
    // Is this only by search? Double-check this user is not being dynamically set for any role etc by multiple Test Suites / Feature files.
    private static String userID = readDataFromExcel(5, "UserName", "testData2", "" + env + "");
    final String hdTableWe = "//*[@id='hdRequestTable']";
    final String psgntnModule = "//ul[@class='pagination']";
    String successTextMsg;

    public static String locateSelection(String tab, String inputVal) {
        String s = null;
        String[] tb = tab.split(" ", 2);
        if (inputVal.equalsIgnoreCase("Application Name")) {
            s = "//div[@ui-view='" + tb[0].toLowerCase() + "']//option[@label='--Select an " + inputVal.replaceAll("\\s.*", "").toLowerCase() + "--']//ancestor::select";
        }
        if (inputVal.equalsIgnoreCase("Role")) {
            s = "//div[@ui-view='" + tb[0].toLowerCase() + "']//option[@label='Please Select User " + inputVal + "']//ancestor::select";
        }
        return s;
    }

    public void verifyEmailSenterEndpoint(String emailTab, String sendBtn) throws Exception {
        click(locateElementByXPathText(emailTab));
        locateElementByCSS("textarea").clear();
        Data templateData=new Data("PRS", templateName, userID);
//        System.out.println(textAsJsonBody("PRS", templateName, userID));
        locateElementByCSS("textarea").sendKeys(templateData.toString());
        locateElementByXPathText(sendBtn).click();
        try {
            wait(3000);
        } catch (IllegalMonitorStateException ie) {
            ie.getStackTrace();
        }
    }

    public void successMessageText(String statusCode, String message) throws Exception {
        wait.until(ExpectedConditions.textToBePresentInElement(locateElementByXPath("//pre[@class='ng-binding']"), message));
        successTextMsg = locateElementByXPath("//pre[@class='ng-binding']").getText().trim();
        System.out.println("jsonBody1 = " + successTextMsg);
        Assert.assertTrue("the json body does not contains 200 status code", successTextMsg.contains(statusCode));
        Assert.assertTrue("the json body does not contains Successful post message", successTextMsg.contains(message));
    }

    public static String locateLocally(String tab, String inputVal) {
        String s;
        String[] tb = tab.split(" ", 2);
        s = "//div[@ui-view='" + tb[0].toLowerCase() + "']//input[@placeholder='Enter " + inputVal + "']";
        return s;
    }
   }
