package com.model.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ForgotUserID_Confirmation_Locators {
	
	@FindBy(how=How.ID,using="happyDIVContainer")
	public WebElement forgotUIDConf;
	
	@FindBy(how=How.ID,using="happyDIV")
	public WebElement forgotUIDConfMessage;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(), 'here')]")
	public WebElement linkToHomePage;
	
	     
}
