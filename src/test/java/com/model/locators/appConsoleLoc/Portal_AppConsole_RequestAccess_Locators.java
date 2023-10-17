package com.model.locators.appConsoleLoc;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_AppConsole_RequestAccess_Locators {

	@FindBy(how=How.XPATH, using="//a[contains(text(), 'Request Access')]")
	public static WebElement requestAccessTab;
	
	@FindBy(how=How.XPATH, using="//div[@id='requestAccessView']")
	public WebElement requestAccessForm;
	
	@FindBy(how=How.XPATH, using="//a[@id='request-link']")
	public WebElement requestNewAccessLink;
	
	@FindBy(how=How.XPATH, using="//form[@name='newRequestForm']")
	public WebElement requestNewAccessForm;	
	
	@FindBy(how=How.XPATH, using="//select[@id='newRequestForm_ic-select_applicationId_1']")
	public WebElement selectAppDropDown;
	
	@FindBy(how=How.XPATH, using="//select[@id='newRequestForm_ic-select_roleId_2']")
	public WebElement selectRoleDropDown;

	@FindBy(how=How.XPATH, using="(//div[@id='addReqAccess']//select)[3]")
	public WebElement selectModelIDDropDown;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id, 'newRequestForm')]")
	public WebElement attrAMRtextBox;
	
	@FindBy(how=How.XPATH, using="//button[@name='add']")
	public WebElement addButton;

	@FindBy(how=How.XPATH, using="//div[@class='col-sm-8 control-holder']//input[@type='text']")
	public WebElement resTextAttr;

	@FindBy(how=How.XPATH, using="//ul[@class='dropdown-menu ng-isolate-scope']")
	public WebElement attrList;


	@FindBy(how=How.XPATH, using="//button[@title='Submit Access Request']")
	public WebElement confirmButton;

	@FindBy(how=How.CSS, using="button[type='submit']")
	public WebElement submitButton;

	@FindBy(how=How.XPATH, using="//div[contains(text(),'You have successfully submitted')]")
	public WebElement confirmPopUp;

	@FindBy(how=How.XPATH, using="//*[@id='reqConfirmMsg']/parent::req-modal//div[@class='modal-body']//div")
	public WebElement confirmPopUp2;
	
	@FindBy(how=How.XPATH, using="//div[@class='col-sm-9 pull-right']//button[@class='btn-as-link']")
	public WebElement okBtn;
	
	@FindBy(how=How.XPATH, using="//input[@placeholder='Search']")
	public WebElement searchTextBox;

	@FindBy(how=How.XPATH, using="//*[text()=' Pending ']")
	public WebElement pendingTab;

	@FindBy(how=How.XPATH, using="//*[text()=' Approved ']")
	public WebElement approvedTab;
	
	@FindBy(how=How.XPATH, using="//button[@title='Search']")
	public WebElement searchBtn;

	@FindBy(how=How.XPATH, using="//textarea[@placeholder='Enter justification']")
	public WebElement justificationTextBoxReq;

	@FindBy(how=How.XPATH, using="//button[@class='btn-as-link']//*[text()='Confirm']")
	public WebElement confirmBtnReq;

	@FindBy(how=How.XPATH, using="//*[text()=' All ']")
	public WebElement allTab;

	@FindBy(how=How.XPATH, using="//div[@id='reqtable-frame-ra']//td//button")
	public WebElement cancelBtn;

	@FindBy(how=How.XPATH, using="//div[@class='confirm-access-dialog ac-font ng-scope']")
	public WebElement cancelReqDialogBox;

	@FindBy(how = How.XPATH, using = "//div[@class='confirm-access-dialog ac-font ng-scope']//textarea")
	public WebElement cancelReqJustification;
}
