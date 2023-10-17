package com.model.locators.appConsoleLoc;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_AppConsole_DelegateAccess_Locators {

	@FindBy(how=How.XPATH, using="//a[contains(text(), 'Delegate Access')]")
	public WebElement delegateAccessTab;
	
	@FindBy(how=How.XPATH, using="//div[@id='da-form']")
	public WebElement delegateAccessForm;
	
	
		
}
