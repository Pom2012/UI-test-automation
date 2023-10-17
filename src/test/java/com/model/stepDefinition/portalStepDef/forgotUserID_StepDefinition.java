package com.model.stepDefinition.portalStepDef;

import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.base.Constants;
import com.model.locators.BasePage_Locators;
import com.model.locators.ForgotUserID_Confirmation_Locators;
import com.model.locators.ForgotUserID_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class forgotUserID_StepDefinition extends BasePage {

    BasePage_Locators login;
    ForgotUserID_Locators forgotUID;
    ForgotUserID_Confirmation_Locators forgotUserIDConf;
    public List<HashMap<String, String>> datamap;


    public forgotUserID_StepDefinition() {
        datamap = DataHelper.data("testData2.xlsx", "FORGOT_UN");
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        forgotUID = PageFactory.initElements(driver, ForgotUserID_Locators.class);
        forgotUserIDConf = PageFactory.initElements(driver, ForgotUserID_Confirmation_Locators.class);
    }

    @When("^clicks Forgot User ID link$")
    public void clicks_Forgot_User_ID_link() throws Throwable {
        click(login.forgotID);
    }

    @And("^fillout data SetForgotUserID \"([^\"]*)\" and \"([^\"]*)\" from excel sheet$")
    public void fillout_data_SetForgotUserID_and_from_excel_sheet(String rowIndexStr, String colName) throws Throwable {
        int rowIndex = Integer.parseInt(rowIndexStr) - 1;
        String value = datamap.get(rowIndex).get(colName);

        if (colName.equalsIgnoreCase("FirstName")) {
            Model_CommonFunctions.type(forgotUID.fNameForgotID, Constants.AccountCreationData.firstname);
        } else if (colName.equalsIgnoreCase("LastName")) {
            Model_CommonFunctions.type(forgotUID.lNameForgotID, value);
        } else if (colName.equalsIgnoreCase("BirthMonth")) {
            Model_CommonFunctions.type(forgotUID.birthMonthDropDownForgotID, value);
        } else if (colName.equalsIgnoreCase("BirthDate")) {
            Model_CommonFunctions.type(forgotUID.birthDateDropDownForgotID, value);
        } else if (colName.equalsIgnoreCase("BirthYear")) {
            Model_CommonFunctions.type(forgotUID.birthYearDropDownForgotID, value);
        } else if (colName.equalsIgnoreCase("Email")) {
            Model_CommonFunctions.type(forgotUID.eMailForgotID, value);
        } else if (colName.equalsIgnoreCase("ZipCode")) {
            Model_CommonFunctions.type(forgotUID.zipCodeForgotID, value);
        } else {
            new Throwable(colName + " is not available");
        }
    }

    @And("^clicks Submit button$")
    public void clicks_Submit_button() throws Throwable {
        click(forgotUID.submitUserForgotID);
    }

    @Then("^confirmation message displays$")
    public void message_displays() throws Throwable {
        String actualVal1 = Model_CommonFunctions.getTextofWebElementSimple(forgotUserIDConf.forgotUIDConf);
        String actualVal2 = Model_CommonFunctions.getTextofWebElementSimple(forgotUserIDConf.forgotUIDConfMessage);
        String expectedVal1 = "Confirmation";
        String expectedVal2 = "Your information has been successfully verified, check your Email Account for the requested information. You can now login by clicking";

        if (actualVal2.equalsIgnoreCase(expectedVal2)) {
            assertEquals(forgotUserIDConf.forgotUIDConfMessage.getAttribute("value"), expectedVal2);
            System.out.println("verified " + actualVal2 + ":" + expectedVal2);
        }

        if (actualVal1.contains(expectedVal1)) {
            // search for substring in string
            int indexFirst = actualVal1.indexOf("Confirmation");
            int indexLast = actualVal1.lastIndexOf("Confirmation");
            String subActualVal1 = actualVal1.substring(indexFirst, indexLast);
            System.out.println(subActualVal1);
        }
    }

}

