package com.model.stepDefinition.commonStepDef;

import com.model.utility.DataHelper;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import com.model.locators.myAccess_myProfileLoc.Portal_MyPortal_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

public class InvalidMFACode extends BasePage {
    BasePage_Locators login;
    Portal_MyPortal_Locators login2;
    public List<HashMap<String, String>> envData;

    public InvalidMFACode() {
        envData = DataHelper.data("testData2.xlsx", env);
        login = PageFactory.initElements(driver, BasePage_Locators.class);
        login2 = PageFactory.initElements(driver, Portal_MyPortal_Locators.class);
    }

    @When("{string} enters the required value for {string}")
    public void enters_the_required_value_for(String rowIndex, String colName) {
        log.info(Date + "... >>>Login box is present ...>>>");
        int index = Integer.parseInt(rowIndex) - 1;
        isElementPresent(login.loginBox);
        userID = envData.get(index).get("UserName");
        String pwd = getExpectedPwd();
        switch (colName){
            case "UserName":
                type(login.userID, userID);
                break;
            case "Password":
                type(login.password, pwd);
                break;
        }
    }

    @And("clicks on Send MFA Code, then enters an invalid MFA code")
    public void clicks_on_and_enter_the_code_to_the_field() throws Throwable {
        click(locateElementByID("cms-send-code-phone"));
        wait(200);
        type(locateElementByID("cms-verify-securityCode"), "12424556");
        click(locateElementByID("cms-verify-code-submit"));
    }

    @Then("^\"([^\"]*)\" and \"([^\"]*)\" message is displayed$")
    public void and_message_is_displayed(String arg1, String arg2) {
        String text = locateElementByCSS(".cms-h2-interior").getText();
        String text2 = locateElementByCSS("#sadDsIV").getText();
        System.out.println(text);
        System.out.println(text2);
    }
}
