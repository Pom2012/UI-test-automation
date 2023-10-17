package com.model.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotUserID_Locators {
  
	@FindBy(how=How.ID,using="cms-forgotid-firstName")
	public WebElement fNameForgotID;
	
	@FindBy(how=How.ID,using="cms-forgotid-lastName")			
	public WebElement lNameForgotID;	
	
	@FindBy(how=How.ID,using="cms-forgotid-birthMonth")
	public WebElement birthMonthDropDownForgotID;	
	
	@FindBy(how=How.ID,using="cms-forgotid-birthDate")
	public WebElement birthDateDropDownForgotID;
	
	@FindBy(how=How.ID,using="cms-forgotid-birthYear")
	public WebElement birthYearDropDownForgotID;
	
	@FindBy(how=How.ID,using="cms-forgotid-forgotEmail")
	public WebElement eMailForgotID;	
	
	@FindBy(how=How.ID,using="cms-forgotid-usBased")
	public WebElement usBasedYesRadioButtonForgotID;
	
	@FindBy(how=How.XPATH,using="//label[@for='cms-forgotid-notusBased']")
	public WebElement usBasedNoRadioButtonForgotID;
	
	@FindBy(how=How.ID,using="cms-forgotid-usZipCode")
	public WebElement zipCodeForgotID;
	
	@FindBy(how=How.ID, using= "cms-forgotid-submit-fk")
	public WebElement submitUserForgotID;
	
	@FindBy(how=How.NAME,using="Cancel")
	public WebElement CancelLinkForgotID;
	
	
	
	
	
	
	
	
	
}
