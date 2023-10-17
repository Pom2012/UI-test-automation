package com.model.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PortalUnavailable_Locators {
	
	@FindBy(how=How.XPATH,using="//*[contains(text(),'We apologize for any inconvenience')]")
	public WebElement loginBox;

	@FindBy(how=How.XPATH,using="//*[contains(text(),'We apologize for any inconvenience')]")
	public WebElement portalUnavailableMessage;

	@FindBy(how=How.XPATH,using="//*[contains(text(), 'CMS Enterprise Portal is unable to process your request')]")
	public WebElement portalInternalError;
	
}
