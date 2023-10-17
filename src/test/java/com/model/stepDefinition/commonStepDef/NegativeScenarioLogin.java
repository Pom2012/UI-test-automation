package com.model.stepDefinition.commonStepDef;

import com.github.javafaker.Faker;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.BasePage_Locators;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NegativeScenarioLogin extends BasePage {
    Faker faker = new Faker();
    BasePage_Locators login = PageFactory.initElements(driver, BasePage_Locators.class);

    @When("^a user enters random value to \"([^\"]*)\" fields$")
    public void a_user_enters_random_value_to_fields(String arg1) {
        System.out.println("... >>>Login box is present ...>>>");
        Model_CommonFunctions.type(login.userID, faker.name().username());

    }

    @When("^enters random value to \"([^\"]*)\" fields$")
    public void enters_random_value_to_fields(String arg1) throws Throwable {
        Model_CommonFunctions.type(login.password, faker.idNumber().invalidSvSeSsn());
    }

    @When("^selects \"([^\"]*)\" checkbox and clicks on \"([^\"]*)\" button$")
    public void selects_checkbox_and_clicks_on_button(String arg1, String arg2) throws Throwable {
        log.info("\nAgree checkbox is not selected = " + login.checkbox.isSelected());
        if (!login.checkbox.isSelected()) {
            click(login.agreeCheckbox);
        }
        click(login.loginButton);
    }

    @When("^leaves deselected \"([^\"]*)\" checkbox and clicks on \"([^\"]*)\" button$")
    public void leaves_deselected_checkbox_and_clicks_on_button(String arg1, String arg2) throws Throwable {
        log.info("\nAgree checkbox is not selected = " + login.checkbox.isSelected());
        click(login.loginButton);
    }

    @Then("^\"([^\"]*)\" dialog is popped up$")
    public void dialog_is_popped_up(String arg1) throws Throwable {
        wait.until(ExpectedConditions.visibilityOf(locateElementByXPath("//div[@id='cms-terms-agree']//h4")));
        String text = locateElementByXPath("//div[@id='cms-terms-agree']//h4").getText();
        highLightElement(locateElementByXPath("//div[@id='cms-terms-agree']//h4"));
        verifyActualText(text, arg1);
    }

    @Then("^the application displays \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_application_displays_and(String arg1, String arg2) {
        String text2 = locateElementByCSS("div[aria-label='Invalid combination of User ID and Password. Enter valid User ID and Password and try again.']").getText();
        Assert.assertTrue(text2.contains(arg2));
        System.out.println("Expected error message >>" + arg2);
        System.out.println("Actual error message >>" + text2);
    }
}
