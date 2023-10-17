package com.model.locators.myAccess_myProfileLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_MyAccess_Locators {
	
	//******************  EIDM  *******************************************************

	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center User')]")
	public WebElement icRole;
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Administrator')]")
	public WebElement icAdminRole;
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Application Approver')]")
	public WebElement icAppApprover;
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Helpdesk User')]")
	public WebElement icHelpDesk;

	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Helpdesk Administrator')]")
	public WebElement icHelpDeskAdmin;

	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Portal Operational Intelligence')]")
	public WebElement icPOIUser;

	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Privileged User')]")
	public WebElement icPVuser;
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Innovation Center Report User')]")
	public WebElement icReportuser;
	
	
	//******************  IDM   *******************************************************

	
//	@FindBy(how=How.XPATH, using="//div[contains(text(),'Innovation Center ')]") //div[@class='cms-hd-usr-det']
//	public WebElement icRole;
//	
//	@FindBy(how=How.XPATH, using="//div[contains(text(),'Innovation Center Administrator ')]") 
//	public WebElement icAdminRole;
//	
//	@FindBy(how=How.XPATH, using="//div[contains(text(),'Application Approver')]") 
//	public WebElement icAppApprover;
//	
//	@FindBy(how=How.XPATH, using="//div[contains(text(),' Innovation Center Helpdesk User ')]") 
//	public WebElement icHelpDesk;
	
	
}
