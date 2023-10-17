package com.model.locators.appConsoleLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_AppConsole_ConfirmAccess_Locators {

	@FindBy(how=How.XPATH, using="//a[contains(text(), 'Confirm Access')]")
	public WebElement confirmAccessTab;
	
	@FindBy(how=How.XPATH, using="//div[@id='confirmAccessView']")
	public WebElement confirmAccessForm;
		
	@FindBy(how=How.XPATH, using="//input[@placeholder='Search']")
	public WebElement searchTextBox;
	
	@FindBy(how=How.XPATH, using="//button[@title='Search']")
	public WebElement searchBtn;
	
	@FindBy(how=How.XPATH, using="//button[@title='This link will assign pending request to you for approval or rejection']")
	public WebElement assignBtn;
	
	@FindBy(how=How.XPATH, using="//button[@title='This will allow you to approve this role request']")
	public WebElement approveBtn;
	
	@FindBy(how=How.XPATH, using="//button[@title='This will allow you to reject this role request']")
	public WebElement rejectBtn;
	
	@FindBy(how=How.XPATH, using="//button[@title='This will cancel the assignment of this role so it can be approved by another user']")
	public WebElement releaseBtn;
	
	@FindBy(how=How.XPATH, using="//div[@class='confirm-access-dialog ac-font ng-scope']")
	public WebElement approveAccessDialogBox;
	
	@FindBy(how=How.XPATH, using="//textarea[@placeholder='Enter justification']")			
	public WebElement justificationTextBox;
	
	@FindBy(how=How.XPATH, using="//button[@class='btn-as-link']//*[text()='Confirm']")
	public WebElement confirmBtn;
	
	@FindBy(how=How.XPATH, using="//*[text()=' Approved ']")
	public WebElement approvedTab;
	
	@FindBy(how=How.XPATH, using="//*[text()=' Rejected ']")
	public WebElement rejectedTab;

	@FindBy(how=How.XPATH, using="//*[text()=' Pending ']")
	public WebElement pendingTab;




}
