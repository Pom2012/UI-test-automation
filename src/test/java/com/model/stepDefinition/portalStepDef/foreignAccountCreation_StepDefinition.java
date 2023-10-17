package com.model.stepDefinition.portalStepDef;

import com.model.utility.DataHelper;
import com.model.utility.Model_CommonFunctions;
import com.model.base.BasePage;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step2_Locators;
import com.model.locators.userRegistrationLoc.Portal_UserRegistration_Step3_Locators;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;


public class foreignAccountCreation_StepDefinition extends BasePage {
	Portal_UserRegistration_Step2_Locators nonUSUserReg;
	Portal_UserRegistration_Step3_Locators nonUSUserReg2;

	public List<HashMap<String, String>> datamap;

	public foreignAccountCreation_StepDefinition() {
		datamap = DataHelper.data("testData2.xlsx", "IDM_NON_US_REG");
		nonUSUserReg = PageFactory.initElements(driver, Portal_UserRegistration_Step2_Locators.class);
		nonUSUserReg2 = PageFactory.initElements(driver, Portal_UserRegistration_Step3_Locators.class);
	}

	@And("^fillout foreign data Set \"([^\"]*)\" and \"([^\"]*)\" from excel sheet$")
	public void fillout_foreign_data_set_something_and_something_from_excel_sheet(String strArg1, String attribute) //attribute = value; strArg1 = row index
		throws Exception {		
		
		int index = Integer.parseInt(strArg1) - 1;
		String value = datamap.get(index).get(attribute);
		
		if (attribute.equalsIgnoreCase("FirstName")) {
			Model_CommonFunctions.type(nonUSUserReg.fName, value);
		} else if (attribute.equalsIgnoreCase("LastName")) {
			Model_CommonFunctions.type(nonUSUserReg.lName, value);
		} else if (attribute.equalsIgnoreCase("SSNumber")) {
			Model_CommonFunctions.type(nonUSUserReg.socialSecNumber, value);
		} else if (attribute.equalsIgnoreCase("BirthMonth")) {
			selectFromDropDownMenu(nonUSUserReg.birthMonthDropDown, value);
		} else if (attribute.equalsIgnoreCase("BirthDate")) {
			selectFromDropDownMenu(nonUSUserReg.birthDateDropDown, value);
		} else if (attribute.equalsIgnoreCase("BirthYear")) {
			selectFromDropDownMenu(nonUSUserReg.birthYearDropDown, value);
		} else if (attribute.equalsIgnoreCase("Street")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignAddress, value);
		} else if (attribute.equalsIgnoreCase("City")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignCity, value);
		} else if (attribute.equalsIgnoreCase("ZipCode")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignZipCode, value);
		} else if (attribute.equalsIgnoreCase("Country")) {
			Model_CommonFunctions.type(nonUSUserReg.country, value);
		} else if (attribute.equalsIgnoreCase("CountryCode")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignCountryCodeOpt, value);
		} else if (attribute.equalsIgnoreCase("PhoneNumber")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignPhoneNumber, value);
		} else if (attribute.equalsIgnoreCase("Email")) {
			Model_CommonFunctions.type(nonUSUserReg.foreignEmail, value);
		} else if (attribute.equalsIgnoreCase("ConfirmEmail")) {
			Model_CommonFunctions.type(nonUSUserReg.foreigneMailConfirm, value);
		} else if (attribute.equalsIgnoreCase("UserName")) {
			Model_CommonFunctions.type(nonUSUserReg2.uName, value);
		} else if (attribute.equalsIgnoreCase("Password")) {
			Model_CommonFunctions.type(nonUSUserReg2.passw, value);
		} else if (attribute.equalsIgnoreCase("ConfirmPassword")) {
			Model_CommonFunctions.type(nonUSUserReg2.passwConf, value);
		} else if (attribute.equalsIgnoreCase("Challenge_Question_1")) {
			Model_CommonFunctions.type(nonUSUserReg2.quest1, value);
		} else if (attribute.equalsIgnoreCase("Challenge_Question_1_Answer")) {
			Model_CommonFunctions.type(nonUSUserReg2.answ1, value);
		} else {
			new Throwable(attribute + " is not available");
		}
	}

	public String getCurrentSelected_state() {
		return new Select(nonUSUserReg.state).getFirstSelectedOption().getText();
	}

	public String getCurrentSelected_birthMonth() {
		return new Select(nonUSUserReg.birthMonthDropDown).getFirstSelectedOption().getText();
	}

	public String getCurrentSelected_birthDate() {
		return new Select(nonUSUserReg.birthDateDropDown).getFirstSelectedOption().getText();
	}
}
