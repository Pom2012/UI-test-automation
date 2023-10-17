package com.model.stepDefinition.usRegStepDef;


import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step3_Locators;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class NewUserRegistrationNegative_StepDefinition extends BasePage {

	Portal_UserRegistration_Step2_Locators newUserReg;
	Portal_UserRegistration_Step3_Locators newUserReg3;
	public static List<WebElement> RegistrationDetails;
	public List<HashMap<String, String>> datamap;

	public NewUserRegistrationNegative_StepDefinition() {
		newUserReg = PageFactory.initElements(driver, Portal_UserRegistration_Step2_Locators.class);
		newUserReg3 = PageFactory.initElements(driver, Portal_UserRegistration_Step3_Locators.class);
		//datamap = DataHelper.data("testData.xlsx", "REGISTRATION_NEGAT");
	}

   ////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////

	@And("^Verify step two required fields errors on top$")
	public void verify_step_two_required_fields_errors_on_top() {
		assertTrue(isElementPresent((newUserReg.fNameError508)));
		assertTrue(isElementPresent((newUserReg.lNameError508)));
		assertTrue(isElementPresent(newUserReg.birthMonthDropDown508));
		assertTrue(isElementPresent(newUserReg.birthDateDropDown508));
		assertTrue(isElementPresent(newUserReg.birthYearDropDown508));
		assertTrue(isElementPresent(newUserReg.homeAddressError508));
		assertTrue(isElementPresent(newUserReg.cityError508));
		assertTrue(isElementPresent(newUserReg.stateError508));
		assertTrue(isElementPresent(newUserReg.zipCodeError508));
		assertTrue(isElementPresent(newUserReg.eMailError508));
		assertTrue(isElementPresent(newUserReg.eMailConfirmError508));
		assertTrue(isElementPresent(newUserReg.phoneNumberError508));
	}

	@And("^Verify step two required fields validation messages$")
	public void verify_step_two_required_fields_validation_message() {
		waitForCommonPageLoadingElements();
		Model_CommonFunctions.scroll_Into_View(newUserReg.fNameEmpty);
		verifyErrorDisplayed (newUserReg.fNameError, newUserReg.fNameEmpty );
		verifyErrorDisplayed(newUserReg.lNameError, newUserReg.lNameEmpty);
		verifyErrorDisplayed(newUserReg.birthMonthDropDownError, newUserReg.birthMonthDropDownEmpty);
		verifyErrorDisplayed(newUserReg.birthDateDropDownError, newUserReg.birthDateDropDownEmpty);
		verifyErrorDisplayed(newUserReg.birthYearDropDownError, newUserReg.birthYearDropDownEmpty);
		verifyErrorDisplayed(newUserReg.homeAddressError, newUserReg.homeAddressEmpty);
		verifyErrorDisplayed(newUserReg.cityError, newUserReg.cityEmpty);
		verifyErrorDisplayed(newUserReg.stateError, newUserReg.stateEmpty);
		verifyErrorDisplayed(newUserReg.zipCodeError, newUserReg.zipCodeEmpty);
		verifyErrorDisplayed(newUserReg.eMailError, newUserReg.eMailEmpty);
		verifyErrorDisplayed(newUserReg.eMailConfirmError, newUserReg.eMailConfirmEmpty);
		verifyErrorDisplayed(newUserReg.phoneNumberError, newUserReg.phoneNumberEmpty);
	}

	 @And("^Verify step two required fields errors on top are not displayed$")
	 public void verify_step_two_required_fields_errors_on_top_are_not_displayed() throws Throwable {
		 click(newUserReg.eMail);
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.fNameError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.lNameError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.birthMonthDropDown508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.birthDateDropDown508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.birthYearDropDown508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.homeAddressError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.cityError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.stateError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.zipCodeError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.eMailError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.eMailConfirmError508)));
//	     assertFalse(isElementPresentNoSuchElement((newUserReg.phoneNumberError508)));
	 }

	@And("^verify step two required fields validation messages are not displayed$")
	public void verify_step_two_required_fields_validation_messages_are_not_displayed() {
		waitForCommonPageLoadingElements();
//		assertFalse( isElementPresentNoSuchElement((newUserReg.fNameError)));
		assertFalse(isElementPresent((newUserReg.lNameEmpty)));
		assertFalse(isElementPresent((newUserReg.birthMonthDropDownEmpty)));
		assertFalse(isElementPresent((newUserReg.birthDateDropDownEmpty)));
		assertFalse(isElementPresent((newUserReg.birthYearDropDownEmpty)));
		assertFalse(isElementPresent((newUserReg.homeAddressEmpty)));
		assertFalse(isElementPresent((newUserReg.cityEmpty)));
		assertFalse(isElementPresent((newUserReg.stateEmpty)));
		assertFalse(isElementPresent((newUserReg.zipCodeEmpty)));
		assertFalse(isElementPresent((newUserReg.cityEmpty)));
		assertFalse(isElementPresent((newUserReg.eMailEmpty)));
		assertFalse(isElementPresent((newUserReg.eMailConfirmEmpty)));
		assertFalse(isElementPresent((newUserReg.phoneNumberEmpty)));
	}

	@And("^Verify step three required fields errors on top$")
	public void verify_step_three_required_fields_errors_on_top() {
		waitForCommonPageLoadingElements();
		assertTrue(isElementPresent(newUserReg3.uNameError508));
		assertTrue(isElementPresent(newUserReg3.passwError508));
		assertTrue(isElementPresent(newUserReg3.passwConfError508));
		assertTrue(isElementPresent(newUserReg3.quest1Error508));
		assertTrue(isElementPresent(newUserReg3.quest2Error508));
		assertTrue(isElementPresent(newUserReg3.quest3Error508));
		assertTrue(isElementPresent(newUserReg3.answ1Error508));
		assertTrue(isElementPresent(newUserReg3.answ2Error508));
		assertTrue(isElementPresent(newUserReg3.answ3Error508));
	}

	@And("^Verify step three required fields validation messages$")
	public void verify_step_three_required_fields_validation_message() {
		waitForCommonPageLoadingElements();
		Model_CommonFunctions.scroll_Into_View(newUserReg3.passwEmpty );
		verifyErrorDisplayed (newUserReg3.uNameError, newUserReg3.uNameEmpty );
		verifyErrorDisplayed (newUserReg3.passwError, newUserReg3.passwEmpty );
		verifyErrorDisplayed (newUserReg3.passwConfError, newUserReg3.passwConfEmpty );
		verifyErrorDisplayed (newUserReg3.quest1Error, newUserReg3.quest1Empty );
		verifyErrorDisplayed (newUserReg3.quest2Error, newUserReg3.quest2Empty );
		verifyErrorDisplayed (newUserReg3.quest3Error, newUserReg3.quest3Empty );
		verifyErrorDisplayed (newUserReg3.answ1Error, newUserReg3.answ1Empty );
		verifyErrorDisplayed (newUserReg3.answ2Error, newUserReg3.answ2Empty );
		verifyErrorDisplayed (newUserReg3.answ3Error, newUserReg3.answ3Empty );
	}
}

