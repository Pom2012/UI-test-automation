package com.model.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



public class WelcomePage_Locators {
	
	
	/////////////////////////APPLICATIONS ACCESSED BY IC PRIVILEGED USER ////////////////////////////////////////////
	

	@FindBy(how=How.XPATH, using="//*[text()='AHC' and @class= 'ng-binding']")  
	public WebElement AHCWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[ contains(text(),'Validate Bank Information')]")  
	public WebElement BIVWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='BPCI Advanced' and @class= 'ng-binding']")  
	public WebElement BPCIWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='CMMI Centralized Data Exchange (CDX)']")  
	public WebElement CDXWelcomePage;	
	
	@FindBy(how=How.XPATH, using="//*[text()='CJR' and @class= 'ng-binding']")  
	public WebElement CJRWelcomePage;
	
	@FindBy(how=How.ID, using="CPCMainContent") //a[@title='Home - Home page of the Comprehensive Primary Care Plus (CPC+) application']
	public WebElement CPCplWelcomePage;
	
	@FindBy(how=How.XPATH, using="//span[@id='CPC+ CMS View']")//span[text()='Looker Explore']") //iframe  //span[text()='Expanded Data Feedback Reporting (eDFR)']
	public WebElement eDFRWelcomePage;   
	
	@FindBy(how=How.XPATH, using="//*[text()='Enh. MTM' and @class= 'ng-binding']")  
	public WebElement Enh_MTMRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='Emergency Triage, Treat, and Transport (ET3)' = 'ng-binding']")
	public WebElement ET3WelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='ET3' and @class= 'ng-binding']")  
	public WebElement ET3WelcomePage1;

	////*[text()='Participant Management' and @id= 'rfNav2831016']

	@FindBy(how=How.XPATH, using="//div[@class='usa-logo']//a[@title='Health Data Reporting']")  
	public WebElement HDRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='HHVBP' and @class= 'ng-binding']")  
	public WebElement HHVBPWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='HPI' and @class= 'ng-binding']")  
	public WebElement HPIWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='Integrated Care for Kids (InCK) Home' and contains(@class, 'ng-binding')]")  
	public WebElement InCKWelcomePage;
	
//	@FindBy(how=How.XPATH, using="//*[contains(text(),'Innovation Payment Contractor Portal (IPC Portal)')]")  
//	public WebElement IPCWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='Validate Bank Information']")  
	public WebElement IPCWelcomePage;
		
	
	@FindBy(how=How.XPATH, using="//*[text()='LSDM_DMARS' and contains(@class, 'ng-binding')]")  
	public WebElement LSDM_DMARSWelcomePage;
	
	@FindBy(how=How.XPATH, using="//a[@title='Home - Home page of the Maryland Primary Care Program (MDPCP) application selected']") //VAL  
	public WebElement MDPCPWelcomePage;//a[@id='mdpcpHomeID']
	
	@FindBy(how=How.XPATH, using="//*[text()='MHDR' and contains(@class, 'ng-binding')]")  
	public WebElement MHDRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[contains(text(),'FAQ') and contains(@class, 'ng-binding')]")  
	public WebElement MHDRWelcomeFAQPage;	
	
	@FindBy(how=How.XPATH, using="//*[text()='Future home of Maternal Opioid Misuse (MOM) Model']") 
	public WebElement MOMWelcomePage;
	
	
	@FindBy(how=How.XPATH, using="//*[@class='ng-binding'and text()='MOM']")  
	public WebElement MOMWelcomePage1;
		
	
	@FindBy(how=How.XPATH, using="//h1[contains(text(),'Welcome to the OCM Data Registry!')]")  
	public WebElement OCMRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='OCM +' and contains(@class, 'ng-binding')]")  
	public WebElement OCM_PlusWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='OCP' and contains(@class, 'ng-binding')]")  
	public WebElement OCM_PlusWelcomePageVAL1;
	
	@FindBy(how=How.XPATH, using="//div[@name='title']//a[contains(text(),'Oncology Care First Model')]")  
	public WebElement OCM_Pluslink;
	
	
	
	@FindBy(how=How.ID, using="btnSwitchToLab")
	public WebElement OCMRSwitchButton;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Home')and contains(@href,'OCM')]")  
	public WebElement OCMRHomeButton;	
	
	
	@FindBy(how=How.XPATH, using="//*[text()='PCF' and @class= 'ng-binding']")  
	public WebElement PCFWelcomePage;
	
	@FindBy(how=How.XPATH, using="//h1[@class='ng-binding' and text()='RO']")  
	public WebElement ROMWelcomePage;
	
	@FindBy(how=How.XPATH, using="//*[text()='RF' and @class= 'ng-binding']")  
	public WebElement RFAAWelcomePage;
	
	@FindBy(how=How.XPATH, using="//h1[text()='Transforming Clinical Practice Initiative Data Hub']")  
	public WebElement TCPIWelcomePage;	
		
	@FindBy(how=How.XPATH, using="//h1[text()='Transforming Clinical Practice Initiative Data Hub']")  
	public WebElement SAPT4WelcomePage;
	
    /////////////////////////APPLICATIONS ACCESSED BY IC USER ////////////////////////////////////////////
	
	@FindBy(how=How.XPATH, using="//div[@id='qmat_home_menu']")  
	public WebElement QMATWelcomePage;	
	
	
    /////////////////////////APPLICATIONS ACCESSED BY IC REPORT USER ////////////////////////////////////////////
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'User Activity Report')]")  //iframe
	public WebElement QMATRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//a[text()='ICBIR Report']")  //iframe
	public WebElement ICBIRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//div[@class='mstrLargeIconViewItemName']//a[@title='CPCBIR Report']")  //iframe
	public WebElement CPCBIRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//div[@class='mstrLargeIconViewItemName']//a[@title='MDPCPBIR Report']")  //iframe
	public WebElement MDPCPBIRWelcomePage;
	
	@FindBy(how=How.XPATH, using="//div[@class='mstrLargeIconViewItemName']//a[@title='Practice Reporting Reports']")  //iframe
	public WebElement PractRepReports;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Open folder' and @class='mstrLargeIconViewItemLink' and contains(@href,'folderID=9F')]")  //iframe
	public WebElement PractRepReportsHoverOver;
	
	@FindBy(how=How.XPATH, using="//a[@title='Practice Reporting Response Count']")  //iframe
	public WebElement PractRepReportsCount;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Run Report' and @class='mstrLargeIconViewItemLink' and contains(@href, 'reportID=EF98')]")  //iframe
	public WebElement PractRepReportsCountHoverOver;	
		
	@FindBy(how=How.XPATH, using="//div[text()='Shared Reports']")  //iframe
	public WebElement SharedReports;
	
	@FindBy(how=How.XPATH, using="//a[@class='mstr-dskt-lnk shared']")  //iframe
	public WebElement SharedReportsLink;	
	
	@FindBy(how=How.XPATH, using="//div[text()='Run reports and share reports with others.']")  //iframe
	public WebElement ShareReportsWithOthers;
	
	@FindBy(how=How.XPATH, using="//div[text()='My Reports']")  //iframe
	public WebElement MyReports;
	
	@FindBy(how=How.XPATH, using="//div[text()='Report Home']")  //iframe
	public WebElement reportHome;
	
	@FindBy(how=How.XPATH, using="//table[@class='mstrListCart' and contains(@id,'id_mstr')]")  //iframe
	public WebElement yearQuaterTable;
	
	@FindBy(how=How.XPATH, using="//input[@value='Run Report']")  //iframe
	public WebElement runReport;
	
	@FindBy(how=How.XPATH, using="//*[@value='Run Document']")  //iframe
	public WebElement runDocument;	
	
	@FindBy(how=How.XPATH, using="//div[@id='id_mstr67']")  //iframe
	public WebElement addCPCBIR;
	
	@FindBy(how=How.XPATH, using="//div[@id='id_mstr50']")  //iframe
	public WebElement addQMATR;
	
	@FindBy(how=How.XPATH, using="//div[@class='mstrLargeIconViewItemName']//a[@title='Application User Usage Statistics']")  //iframe
	public WebElement userUsageStat;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Run Report' and @class='mstrLargeIconViewItemLink' and contains(@href,'reportID=A6')]")  //iframe
	public WebElement userUsageStatHoverOver;
	
	@FindBy(how=How.XPATH, using="//a[@title='Practice Reporting Response Details']")  //iframe
	public WebElement responseDetails;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Run Report' and @class='mstrLargeIconViewItemLink' and contains(@href, 'reportID=9C')]")  //iframe
	public WebElement responseDetailsHoverOver;
	
	@FindBy(how=How.XPATH, using="//a[@title='Practice Information Reports' and @class='mstrLink']")  //iframe
	public WebElement infoReports;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Open folder' and @class='mstrLargeIconViewItemLink' and contains(@href, 'folderID=D78')]")  //iframe
	public WebElement infoReportsHoverOver;
	
	@FindBy(how=How.XPATH, using="//a[@title='Staff Roster Details' and @class='mstrLink']")  //iframe
	public WebElement rosterDetails;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Run Report' and @class='mstrLargeIconViewItemLink' and contains(@href, 'reportID=2B')]")  //iframe
	public WebElement rosterDetailsHoverOver;
	
	@FindBy(how=How.XPATH, using="//a[@title='User Activity Report']")  //iframe
	public WebElement userActivity;
	
	@FindBy(how=How.XPATH, using="//a[@alt='Run Document' and @class='mstrLargeIconViewItemLink' and contains(@href, 'documentID=AD')]")  //iframe
	public WebElement userActivityHoverOver;
	
	
	
	
	/////////////////////////////  ERRORS  ///////////////////////////////
	
		
	@FindBy(how=How.XPATH, using="//h1[@class='cms-h1-interior']")  
	public WebElement pageNotFoundGeneric;
	
	@FindBy(how=How.XPATH, using="//div[@id='content']/following::text()[1]")  
	public WebElement pageNotFound;
	
	@FindBy(how=How.XPATH, using="//div[@class='pageHeaderText aligncenter' and contains(text(),'Access Terminated')]")  
	public WebElement accessTerminated;
	
	@FindBy(how=How.XPATH, using="//div[@class='col']//h1[text()='Transforming Clinical Practice Initiative Data Hub']")  
	public WebElement accessTerminatedMHDR;
	
	@FindBy(how=How.ID, using="unexpectedError")
	public WebElement unexpectedError;
	
	/////////////////////////    OTHER    ////////////////////////////////////////////////
	
	@FindBy(how=How.XPATH, using="//*[@title='CMS Enterprise Portal']")  
	public WebElement portalLink;
	
	@FindBy(how=How.XPATH, using="//*[@title='Link to Enterprise Portal Home Page']")  
	public WebElement portalLinkCMS;
	
	
	
	
	
	
	
	
	
	
	
	
	
}
