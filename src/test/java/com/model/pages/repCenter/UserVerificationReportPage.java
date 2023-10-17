package com.model.pages.repCenter;

import com.model.base.BasePage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.model.utility.DataHelper.*;

public class UserVerificationReportPage extends BasePage {
    private final String repWEID="user-verification-history-report_wrapper";
    String actPracUser, pastUser;
    //TODO: DATA: Users should not be hard-coded or by row#
    private final String[] users =
            {
                    readDataFromExcel(14, "UserName", "testData2", "" + env + ""),
                    readDataFromExcel(11, "UserName", "testData2", "" + env + ""),
                    readDataFromExcel(10, "UserName", "testData2", "" + env + "")
            };

    public void userVerificInUVReport(DataTable dataTable) throws Exception {
        //TODO: DATA: Should not be hard-coded in Java. Request Id should not be passed by Excel.
        // The verification points should be reviewed. Currently there is no unique value in the report.
        // Loop should not be a fixed size.
        cucTabledata = dataTable.asLists(String.class);
        for (int i = 1, k = 0; i <= 3 || k < 3; i++, k++) {
            userID = users[k];
            actPracUser = cucTabledata.get(i).get(0);
            pastUser = cucTabledata.get(i).get(1);
            requestID = readUVRepData2("reqUserVerif","AMRVT", userID, env2);
            System.out.println("userID = " + userID+" requestID = " + requestID);
            searchBar("(//div[@id='" + repWEID + "']//input)[1]", requestID);
            clickWithJSE(locateElementByXPath("(//div[@id='" + repWEID + "']//button)[1]"));
            wait.until(ExpectedConditions.visibilityOf(locateElementByID(repWEID)));
            actualText = locateElementByXPath("(//div[@id='user-verification-history-report_wrapper']//td)[6]").getText();
            System.out.println("Expected text = " + expectedText+" Actual text "+actualText);
            highLightElement(locateElementByXPath("//div[@id='" + repWEID + "']//td[contains(text(),'" + userID + "')]"));
            System.out.println("Expected: " + readUVRepData("reqUserVerif", "AMRVT", "actPracUser"));
            System.out.println("Expected: " + readUVRepData("reqUserVerif", "AMRVT", "pastUser"));
        }
    }
}
