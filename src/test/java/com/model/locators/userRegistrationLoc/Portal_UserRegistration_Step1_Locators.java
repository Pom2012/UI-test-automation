package com.model.locators.userRegistrationLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_UserRegistration_Step1_Locators {
	
	//List <WebElement> appDropDownList = 
	@FindBy(how=How.XPATH,using="//span[text()='Select Your Application']")  
	public WebElement appDropDown;
	
	@FindBy(how=How.XPATH,using="//option[text()='IC: IC-Innovation Center']")      
	public WebElement appDropDownValue;	
	
	@FindBy(how=How.ID,using="cmsTermsAndConditionsBox")			
	public WebElement termsAndConditions;	
	
	@FindBy(how=How.ID,using="cms-tos-reg")
	public WebElement termsAndConditionsCheckBox;
	
	@FindBy(how=How.ID,using="termsAndCondNext")  //button[@title= 'Next']
	public WebElement nextLink;
	
	@FindBy(how=How.ID,using="toc-cancel")
	public WebElement cancelLink;	

}
