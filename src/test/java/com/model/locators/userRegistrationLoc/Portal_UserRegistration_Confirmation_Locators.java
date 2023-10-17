package com.model.locators.userRegistrationLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_UserRegistration_Confirmation_Locators {
		
	@FindBy(how=How.ID,using="happyDIV")
	public WebElement regConfirmation;	
	
	@FindBy(how=How.XPATH,using="//a[@id='homePageURL']")
	public WebElement loginLink;
	
	
}
