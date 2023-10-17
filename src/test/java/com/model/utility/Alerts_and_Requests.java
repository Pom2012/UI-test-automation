package com.model.utility;

import com.model.base.BasePage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alerts_and_Requests extends BasePage {

    public static String handleAlertAndReturnText() {
        Alert alert = null;
        int retries = 3; // x 20s wait
        while (retries > 0) {
            try {
                alert = wait.until(ExpectedConditions.alertIsPresent());
            } catch (Exception ignored) {}
            if (alert != null) break;
            wait(1000);
            retries--;
        }
        if (alert == null) {
            BasePage.log.error("ERROR: Alert was not retrieved. Returning a blank string");
            return "";
        }
        String alertText = alert.getText().trim();
        BasePage.log.info("The alert message: " + alertText);
        alert.accept();
        return alertText;
    }

    public static List<String> handleAlertAndReturnRequestIDsAndErrors() {
        String alertText = handleAlertAndReturnText();
        String reqExists = "Request for access was rejected for this user as a request with these values already exists.";
        List<String> allMatches = new ArrayList<>();
        //Single Request is REJECTED as EXISTS:
        if (alertText.equalsIgnoreCase(reqExists)) {
            BasePage.log.info("EXISTS: A single request was rejected as already exists.");
            allMatches.add("EXISTS");
            return allMatches;
        }
        //Multiple Requests:
        String errors;
        if (alertText.contains("Unsuccessful Request")) {
            errors = alertText.substring(alertText.indexOf("Unsuccessful Request(s):"))
                    .replaceAll("Unsuccessful Request\\(s\\):\\s+", "")
                    .replaceAll("Other error\\(s\\):\\s+", "")
                    .replaceAll("[\\n\\r]", "; ")
                    .trim() + "; ";
            BasePage.log.info("Alert Request ID Error(s):\n" + errors);
            if (StringUtils.countMatches(errors, reqExists) == StringUtils.countMatches(errors, "; ")
             && !alertText.contains("Successful Request(s):") ) {
                allMatches.add("EXISTS");
                return allMatches;
            }
            BasePage.log.info("REJECTED: Of Multiple Requests, the ERROR(S) section:\n" + errors);
            allMatches.add("ERROR(S): " + errors);
            alertText = alertText.substring(0, alertText.indexOf("Unsuccessful Request(s):"));
        }
        Matcher m = Pattern.compile("Request ID: (\\d+),")
                .matcher(alertText);
        while (m.find()) {
            allMatches.add(m.group().replaceAll("Request ID: ", "").replaceAll(",", ""));
        }
        m = Pattern.compile("request is (\\d+)")
                .matcher(alertText);
        while (m.find()) {
            allMatches.add(m.group().replaceAll("request is ", ""));
        }
        return allMatches;
    }

    public static String handleAlertAndGetRequest() {
        String reqID;
        String alertText = handleAlertAndReturnText();
        reqID = extractReq(alertText);
        log.info("Request ID:" + reqID);
        return reqID;
    }

    public static String handleAlertGetRequest() {
        String alertText = handleAlertAndReturnText();
        return alertText.replaceAll(".*(?= ID: )", "").replaceAll("[^,]*$", "").replaceAll("[^\\d]", "").substring(2).trim();
    }

    public static String handleAlertBatchOperGetReq() {
        String alertText = handleAlertAndReturnText();
        return alertText.replaceAll(".*(?= requests: )", "").replaceAll("[^?]*$", "").replaceAll("[^\\d]", "").trim();
    }

    //TODO: If the App or Role have #s, this will make them part of the Request ID.  Is it filtered before or after?
    static String extractReq(String str) {
        str = str.replaceAll("[^\\d]", " ").replaceAll(" +", " ").trim();
        if (str.equals(""))
            return "-1";
        return str;
    }

    public static String getThe1or2WordFromString(String stringValue, int numberOfOccurrences) {
        String word = null;
        if (numberOfOccurrences == 1) word = stringValue.replaceAll("\\s.*", "");
        if (numberOfOccurrences == 2) word = stringValue.substring(stringValue.indexOf(' ')).trim();
        return word;
    }
}
