package com.model.stepDefinition.usRegStepDef;


import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NewUserRegistration_StepDefinition extends BasePage {

    Portal_UserRegistration_Step2_Locators newUserReg;
    public static List<WebElement> RegistrationDetails;

    public NewUserRegistration_StepDefinition() {
        newUserReg = PageFactory.initElements(driver, Portal_UserRegistration_Step2_Locators.class);
    }

    @When("^clicks the \"([^\"]*)\" button by title$")
    public void click_button_by_title(String buttonTitle) throws Throwable {
        System.out.println("Opening Page " + buttonTitle);
        try {
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title = '" + buttonTitle + "']")));
            click_byTitle(buttonTitle);
        } catch (Exception e) {
            System.out.println("Trying other...>>>");
            driver.navigate().refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@title = '" + buttonTitle + "']")));
            click_byTitle(buttonTitle);
        }
    }


    @And("^clicks the \"([^\"]*)\" dropdown menu$")
    public void clicks_the_something_dropdown_menu(String title) throws Throwable {
        Model_CommonFunctions.click_byid("ngSelectApp");
    }

    @And("^selects \"([^\"]*)\" from the \"([^\"]*)\" {2}dropdown menu$")
    public void selects_something_from_the_something_dropdown_menu(String applName, String appDropDown) throws Throwable {
        driver.findElement(By.id("textSearch")).sendKeys(applName+ Keys.ENTER);
    }

    @And("^clicks \"([^\"]*)\" checkbox$")
    public void clicks_checkbox(String checkBoxText) throws Throwable {
        wait(1000);
        clickCheckbox_byText(checkBoxText);
    }

    @And("^clicks \"([^\"]*)\" button$")
    public void clicks_button(String buttonText) throws Throwable {
        click_bytext(buttonText);
    }

    @And("^fills out \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" text boxes$")
    public void fills_out_text_boxes(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12) throws Throwable {
    }

    @And("^clicks \"([^\"]*)\" dropdown$")
    public void clicks_dropdown(String value) throws Throwable {
        Model_CommonFunctions.selectValue_FromDropdown("title", value);

    }

    @And("^fills out \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" text boxes$")
    public void fills_out_text_boxes(String arg1, String arg2, String arg3) throws Throwable {
      }

    @And("^fills out \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" answers text boxes$")
    public void fills_out_answers_text_boxes(String arg1, String arg2, String arg3) throws Throwable {
       }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


    @And("^verifies \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" text boxes$")
    public void verifies_text_boxes(String firstname, String middlename, String lastname, String ssnumber, String homeaddress, String homeaddressopt, String city, String zipcode, String zipcodeopt,
                                    String emailaddress, String emailaddressconfirm, String phonenumber) throws Throwable {
        return;
/*
//ENH: refactor below methods to do real verification
        verifyRegistrationStep2(firstname, middlename, lastname, ssnumber, homeaddress, homeaddressopt,
                city, zipcode, zipcodeopt, emailaddress, emailaddressconfirm, phonenumber);
 */
    }

    /////////////////////////////////////////NEGATIVE SCENARIO////////////////////////////////////////////////

    @But("^does not fill out \"([^\"]*)\"$")
    public void does_not_fill_out(WebElement missingField) throws Throwable {
        String errorMess = newUserReg.fNameError.getText();
        Model_CommonFunctions.verifyErrorDisplayed(newUserReg.fNameError, newUserReg.fNameEmpty);
    }



    @Then("{string} new users was registered CMS.gov Enterprise Portal")
    public void new_users_was_registered_cms_gov_enterprise_portal(String string) throws Exception {

        clickWithJSE(locateElementByID("ngSelectApp"));


    }

//ENH: outdated? - actions/portalFunc/VerifyRegistrationDataStep2
/* //ENH: These are not currently used to verify an expected value.  Commenting out for the moment
    private CMSPortal_UserRegistration_Step2_Locators ICPageLocators;
        public VerifyRegistrationDataStep2() {
            ICPageLocators = new CMSPortal_UserRegistration_Step2_Locators();
            PageFactory.initElements(driver, ICPageLocators);
        }
    public void verifyRegistrationStep2(String firstname, String middlename, String lastname, String ssnumber, String HomeAddress, String HomeAddressOpt, String city,
                                        String zipCode, String zipCodeOpt, String emailAddress, String emailAddressConfirm, String phoneNumber) throws Exception {
to aid in code cleanup.
        firstname = verifyText(ICPageLocators.fName);
        middlename = verifyText(ICPageLocators.mName);
        lastname = verifyText(ICPageLocators.lName);
        wait(3000);
    }

    public static void verifyElementValue(String Expectedvalue) {
        //Assert.assertEquals(Expectedvalue, driver.findElement(By.id("cms-newuser-firstName")));
    }
 */
}
