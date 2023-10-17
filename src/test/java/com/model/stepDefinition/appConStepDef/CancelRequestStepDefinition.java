package com.model.stepDefinition.appConStepDef;

import com.model.pages.appConsole.CancelRequest;
import com.model.base.BasePage;
import com.model.locators.appConsoleLoc.Portal_AppConsole_RequestAccess_Locators;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

public class CancelRequestStepDefinition extends BasePage {
    public CancelRequest requestAcc = new CancelRequest();
    public Portal_AppConsole_RequestAccess_Locators RequestAccess_Locators;
    ///////////////////////////////////////////////////////////////////////////////////

    public CancelRequestStepDefinition() {
        this.RequestAccess_Locators = new Portal_AppConsole_RequestAccess_Locators();
        PageFactory.initElements(driver, RequestAccess_Locators);
    }


    @When("^user cancels request$")
    public void user_cancels_request() throws Throwable {
        click(locateElementByXPathText("Request Access"));
        requestAcc.cancelRequest();
    }

    @Then("^user cancels two requests ID$")
    public void user_cancels_two_requests_ID() throws Throwable {
        requestAcc.cancelRequests();
    }
}
