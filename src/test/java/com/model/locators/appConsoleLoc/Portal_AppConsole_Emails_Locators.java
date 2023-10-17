package com.model.locators.appConsoleLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_AppConsole_Emails_Locators {
	@FindBy(how=How.XPATH, using="//a[contains(text(), 'EmailNotifications')]")
	public WebElement emailTab;
	
	@FindBy(how=How.XPATH, using="//div[@class='ng-scope']/*[text()='EmailNotifications']")
	public WebElement emailText;
	
	
}
