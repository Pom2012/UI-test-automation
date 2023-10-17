package com.model.locators.userRegistrationLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_UserRegistration_Step2_Locators {
	
	@FindBy(how=How.ID,using="cms-newuser-firstName")
	public WebElement fName;
	
	@FindBy(how=How.ID,using="cms-newuser-middleName")
	public WebElement mName;
	
	@FindBy(how=How.ID,using="cms-newuser-lastName")			
	public WebElement lName;	
	
	@FindBy(how=How.ID,using="cms-newuser-nameSuffix")
	public WebElement suffixDropDown;
	
	@FindBy(how=How.ID,using="cms-newuser-ssn")
	public WebElement socialSecNumber;
	
	@FindBy(how=How.ID,using="cms-newuser-birthMonth")
	public WebElement birthMonthDropDown;
	
	@FindBy(how=How.XPATH, using="//label[@class='cms-select-opt' and contains(text(), 'Birth Month')]")
	public WebElement birthMonthDropDownBlocker;
	
	@FindBy(how=How.ID,using="cms-newuser-birthDate")
	public WebElement birthDateDropDown;
	
	@FindBy(how=How.XPATH, using="//label[@class='cms-select-opt' and contains(text(), 'Birth Date')]")
	public WebElement birthDateDropDownBlocker;
	
	@FindBy(how=How.ID,using="cms-newuser-birthYear")
	public WebElement birthYearDropDown;
	
	@FindBy(how=How.ID,using="cms-newuser-usBased")
	public WebElement usBasedYesRadioButton;
	
	@FindBy(how=How.ID,using="cms-newuser-notusBased")
	public WebElement usBasedNoRadioButton;
	
	@FindBy(how=How.ID,using="cms-newuser-homeAddressLine1")
	public WebElement homeAddress;
	
	@FindBy(how=How.ID,using="cms-newuser-homeAddress1-foreign")
	public WebElement foreignAddress;
	
	@FindBy(how=How.ID,using="cms-newuser-homeAddress2-foreign")
	public WebElement foreignAddressOpt;
	
	
	@FindBy(how=How.ID,using="cms-newuser-homeAddress2")
	public WebElement homeAddressOpt;
	
	@FindBy(how=How.ID,using="cms-newuser-usCity")
	public WebElement city;
	
	@FindBy(how=How.ID,using="cms-newuser-city-foreign")
	public WebElement foreignCity;
	
	@FindBy(how=How.ID,using="cms-newuser-usState")
	public WebElement state;
	
	@FindBy(how=How.ID,using="cms-newuser-state-foreign")
	public WebElement foreignState;	
	
	@FindBy(how=How.ID,using="cms-newuser-province-region-foreign")
	public WebElement foreignProvince;		
	
	@FindBy(how=How.ID,using="cms-newuser-usZipCode")
	public WebElement zipCode;
	
	@FindBy(how=How.ID,using="cms-newuser-zipcode-foreign")
	public WebElement foreignZipCode;	
	
	@FindBy(how=How.ID,using="cms-newuser-zipcode2")
	public WebElement zipCodeOpt;
	
	@FindBy(how=How.ID,using="cms-newuser-zip-ext-foreign")
	public WebElement foreignZipCodeOpt;
	
	@FindBy(how=How.ID,using="cms-newuser-countrycode-foreign")
	public WebElement foreignCountryCodeOpt;	
	
	@FindBy(how=How.ID,using="cms-newuser-usEmail")
	public WebElement eMail;
	
	@FindBy(how=How.ID,using="cms-newuser-email-foreign")
	public WebElement foreignEmail;		
	
	@FindBy(how=How.ID,using="cms-newuser-usConfirmEmail")
	public WebElement eMailConfirm;
	
	@FindBy(how=How.ID,using="cms-newuser-email-foreign-confirm")
	public WebElement foreigneMailConfirm;
	
	@FindBy(how=How.ID,using="cms-newuser-usPhoneNumber")
	public WebElement phoneNumber;
	
	@FindBy(how=How.ID,using="cms-newuser-primary-phone-foreign")
	public WebElement foreignPhoneNumber;
		
	@FindBy(how=How.ID,using="step2BackButton")
	public WebElement step2BackLink;
	
	@FindBy(how=How.ID,using="step2NextButton")
	public WebElement step2NextLink;
	
	@FindBy(how=How.ID,using="step2NextButton")
	public WebElement step2NextLink2;
	
	@FindBy(how=How.ID,using="step2-cancel")
	public WebElement step2CancelLink;	
	
	@FindBy(how=How.XPATH, using="//label[@class='check-radio' and text()='No']")
	public WebElement nonUsBasedRadioButton;
	
	@FindBy(how=How.ID,using="cms-newuser-country-foreign")
	public WebElement country;	
		
	
    ////////////ERROR MESSAGES & EMPTY FIELDS: ///////////////////	
	
	@FindBy(how=How.ID, using="cms-newuser-firstName-error")
	public WebElement fNameError;
	
	@FindBy(how=How.ID,using="cms-newuser-firstName-error-508")
	public WebElement fNameError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-firstName') and contains(@class,'cms-error-border')]")
	public WebElement fNameEmpty;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-lastName') and contains(@class,'cms-error-border')]")
	public WebElement lNameEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-lastName-error")
	public WebElement lNameError;
	
	@FindBy(how=How.ID,using="cms-newuser-lastName-error-508")
	public WebElement lNameError508;	
	
	@FindBy(how=How.XPATH, using="//select[contains(@id,'cms-newuser-birthMonth') and contains(@class,'cms-error-border')]")
	public WebElement birthMonthDropDownEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-birthMonth-error")
	public WebElement birthMonthDropDownError;
	
	@FindBy(how=How.ID,using="cms-newuser-birthMonth-error-508")
	public WebElement birthMonthDropDown508;
	
	@FindBy(how=How.XPATH, using="//select[contains(@id,'cms-newuser-birthDate') and contains(@class,'cms-error-border')]")
	public WebElement birthDateDropDownEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-birthDate-error")
	public WebElement birthDateDropDownError;
	
	@FindBy(how=How.ID,using="cms-newuser-birthDate-error-508")
	public WebElement birthDateDropDown508;
	
	@FindBy(how=How.XPATH, using="//select[contains(@id,'cms-newuser-birthYear') and contains(@class,'cms-error-border')]")
	public WebElement birthYearDropDownEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-birthYear-error")
	public WebElement birthYearDropDownError;
	
	@FindBy(how=How.ID,using="cms-newuser-birthYear-error-508")
	public WebElement birthYearDropDown508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-homeAddress1') and contains(@class,'cms-error-border')]")
	public WebElement homeAddressEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-homeAddress1-error")
	public WebElement homeAddressError;
	
	@FindBy(how=How.ID,using="cms-newuser-homeAddress1-error-508")
	public WebElement homeAddressError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-city') and contains(@class,'cms-error-border')]")
	public WebElement cityEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-city-error")
	public WebElement cityError;
	
	@FindBy(how=How.ID,using="cms-newuser-city-error-508")
	public WebElement cityError508;
	
	@FindBy(how=How.XPATH, using="//select[contains(@id,'cms-newuser-state') and contains(@class,'cms-error-border')]")
	public WebElement stateEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-state-error")
	public WebElement stateError;
	
	@FindBy(how=How.ID,using="cms-newuser-state-error-508")
	public WebElement stateError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-zipcode') and contains(@class,'cms-error-border')]")
	public WebElement zipCodeEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-zipcode-error")
	public WebElement zipCodeError;
	
	@FindBy(how=How.ID,using="cms-newuser-zipcode-error-508")
	public WebElement zipCodeError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-email') and contains(@class,'cms-error-border')]")
	public WebElement eMailEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-email-error")
	public WebElement eMailError;
	
	@FindBy(how=How.ID,using="cms-newuser-email-error-508")
	public WebElement eMailError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-email-confirm') and contains(@class,'cms-error-border')]")
	public WebElement eMailConfirmEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-email-confirm-error")
	public WebElement eMailConfirmError;
	
	@FindBy(how=How.ID,using="cms-newuser-email-confirm-error-508")
	public WebElement eMailConfirmError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-newuser-phoneNumber') and contains(@class,'cms-error-border')]")
	public WebElement phoneNumberEmpty;
	
	@FindBy(how=How.ID, using="cms-newuser-phoneNumber-error")
	public WebElement phoneNumberError;
	
	@FindBy(how=How.ID,using="cms-newuser-phoneNumber-error-508")
	public WebElement phoneNumberError508;
	
	@FindBy(how=How.ID, using="cms-alert-box")
	public WebElement alertBox508;
	
	@FindBy(how=How.XPATH, using="//*[text()='Our records indicate that you are already registered with CMS Enterprise Portal.']")
	public WebElement accountExistsAlert;
	
	
	
	
	
	

}
