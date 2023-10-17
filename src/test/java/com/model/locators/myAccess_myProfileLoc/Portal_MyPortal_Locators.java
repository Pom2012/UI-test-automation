package com.model.locators.myAccess_myProfileLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_MyPortal_Locators {
	//************************************** EIDM ****************************************************
	@FindBy(how=How.ID, using="cms-homepage-header-logo-auth")
	public WebElement myPortalLink;

	@FindBy(how=How.ID, using="cms-myprofile-menu" )    //li[@id='cms-welcome-user']
	public WebElement myProfileDropDownLink;
	
	@FindBy(xpath="//*[@id='cms-header-myaccess-link']/a")//a[@title='My Access']
	public WebElement myAccessLink;
	
	@FindBy(how=How.ID, using="My Helpdesk")
	public WebElement myHelpDesk;
	@FindBy(how=How.XPATH,using="//a[@id='cms-viewallapps-maintile']")
	public WebElement viewAppTile;

	@FindBy(how=How.ID,using="cms-bi-tile")//cms_BusinessIntelligence_tileid
	public WebElement businessIntellTile;

	@FindBy(how=How.ID,using="cms-cpcdft-tile")
	public WebElement cpcDftTile;

	@FindBy(how=How.ID,using="cms-landingpages-mp-addapp")
	public WebElement addAppsTile;

	@FindBy(how=How.ID,using="logoutlink")
	public WebElement logOutlink;

	//************************************** IDM ****************************************************
	@FindBy(how=How.XPATH,using="//a[@title='Approvals']")
	public WebElement ApprovalsTile;

	@FindBy(how=How.XPATH,using="//a[@title='Help Desk / Manage Users']")
	public WebElement helpDeskTile;

/*
	@FindBy(how=How.XPATH, using="//*[@id='cms-landingpages-mp']")
	public WebElement myPortalLable;

	@FindBy(how=How.XPATH,using="//div[@id='cms-Innovation Center']")   
	public WebElement innovationCenterTileDev;
	
	@FindBy(how=How.XPATH,using="//a[@title='Innovation Center']")
	public WebElement innovationCenterTileVal;
	
	@FindBy(how=How.XPATH,using="//a[@title='Innovation Center - VAL1']")
	public WebElement innovationCenterTileVal1;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(), 'Administration Console')]")
    public WebElement adminConsoleLink;
	
	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter-VAL1_AdministrationConsole_pidb']")
    public WebElement adminConsoleLinkval1;	
	
	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter-DEV1_AdministrationConsole_pidb']")
    public WebElement adminConsoleLinkdev1;
	
	@FindBy(how=How.XPATH,using="//span[contains(text(), 'Report Center')]")
	public WebElement reportCenterLink;
	
	@FindBy(how=How.ID,using="cms_InnovationCenter-VAL1_ReportCenter_pidb")
	public WebElement reportCenterLinkval1;	
	
	
	@FindBy(how=How.XPATH,using="//span[contains(text(), 'Support Center')]")
	public WebElement supportCenterLink;
	
	@FindBy(how=How.ID,using="cms_InnovationCenter-VAL1_SupportCenter_pidb")
	public WebElement supportCenterLinkval1;	
	
	
	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter_ApplicationConsole_pidb']")
	public WebElement appConsoleLink;
	
	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter-DEV1_ApplicationConsole_pidb']")
	public WebElement appConsoleLinkDev1;
	
	
	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter_ApplicationConsole_pidb']")
	public WebElement appConsoleLinkval;
	
	@FindBy(how=How.ID,using="cms_InnovationCenter-VAL1_ApplicationConsole_pidb")
	public WebElement appConsoleLinkval1;	
 */

//	@FindBy(how=How.XPATH, using="//li[@id='cms-welcome-user-eidm']//a[@id='cms-myprofile-menu']")
//	public WebElement myProfileDropDownLink;
//	
//	@FindBy(how=How.ID, using="myaccess")//a[@title='My Access']
//	public WebElement myAccessLink;
	
//	@FindBy(how=How.ID, using="My Helpdesk")
//	public WebElement myHelpDesk;  	
	
//	@FindBy(how=How.XPATH,using="//div[@id='cms-Innovation Center']")   //OR: //div[@title='Innovation Center']
//	public WebElement innovationCenterTileDev;
//	
//	@FindBy(how=How.XPATH,using="//div[@title='Innovation Center - DEV1']")
//	public WebElement innovationCenterTileDev1;
//	
//	@FindBy(how=How.XPATH,using="//div[@id='cms-Innovation Center']")   //OR: //div[@title='Innovation Center']
//	public WebElement innovationCenterTileVal;
//	
//	@FindBy(how=How.XPATH,using="//div[@title='Innovation Center - VAL1']")
//	public WebElement innovationCenterTileVal1;
	
//	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter_ApplicationConsole_pidh']")    
//	public WebElement appConsoleLinkdev0;
//	
//	@FindBy(how=How.XPATH,using="//a[@id='cms_InnovationCenter_ApplicationConsole_pidh']")
//	public WebElement appConsoleLinkdev1;
//	
	
	
//	@FindBy(how=How.XPATH,using="//a[@title='Administration Console']")
//	public WebElement adminConsoleLink;
	
//	@FindBy(how=How.XPATH,using="//a[@title='Report Center']")
//	public WebElement reportCenterLink;
	
//	@FindBy(how=How.XPATH,using="//a[@title='Support Center']")
//	public WebElement supportCenterLink;

//	@FindBy(how=How.XPATH,using="//a[@title='Administration Console']")
//	public List<WebElement> linkListAdminConsoleLink;
	
//	@FindAll({
//		   @FindBy(how=How.XPATH,using="//a[@title='Administration Console']"),
//		   @FindBy(how=How.XPATH,using="//a[@title='Report Center']")
//		})
//	public List<WebElement> linkList;
}
