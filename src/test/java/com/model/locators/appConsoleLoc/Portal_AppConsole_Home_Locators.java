package com.model.locators.appConsoleLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Portal_AppConsole_Home_Locators {

	@FindBy(how=How.XPATH, using="//a[contains(text(), 'Home')]")
	public WebElement homeTab;
	
	@FindBy(how=How.XPATH, using="//li[contains (@class,'active')]//a[contains(text(), 'Home')]")
	public WebElement homeTabSelected;
	
	@FindBy(how=How.XPATH, using="//div//*[@class='acHeader']//*[contains(text(),'Home')]")
	public WebElement homeHeader;
	
	@FindBy(how=How.XPATH, using="//div//*[@class='acHeader']//*[contains(text(),'CMS Innovation Center')]")
	public WebElement cmsICHeader;
	
	@FindBy(how=How.XPATH, using="//div[@id='achome']")
	public WebElement homeForm;

	/////////////  CMMI APPLICATIONS ////////////////////////////////////
	
	@FindBy(how=How.CLASS_NAME, using = "uc-app-acronym-holder")
	public List<WebElement> cmmiApplications;

	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='AHC']")
	public WebElement AHC_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']/span[contains(text(),'BPCI')]")   //div[@class='uc-app-acronym-holder']//span[text()='BPCI_Adv']
	public WebElement BPCI_Application;
	
		
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[contains(text(),'biv')]")
	public WebElement BIV_ApplicationDEV0;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[contains(text(),'BIV')]")
	public WebElement BIV_ApplicationVAL0;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[contains(text(),'CDX')]")
	public WebElement CDX_Application;	
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='CJR']")
	public WebElement CJR_Application;	

	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='CPC+']")
	public WebElement CPCplusApplication;
	
	@FindBy(how=How.XPATH, using="//div[@name='title']//a[text()=' Comprehensive Primary Care (CPC) '] ")
	public WebElement CPCplusLink;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='eDFR']")
	public WebElement EDFR_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='Enh_MTM']")
	public WebElement Enh_MTMApplication;
	
	@FindBy(how=How.XPATH, using="//div[@name='portlet-section-body']/div[@name='title']/a[text()=' Part D Enhanced Medication Therapy Management ']")
	public WebElement Enh_MTMlink;
		
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='ET3']")
	public WebElement ET3_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='HDR']")
	public WebElement HDR_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='HHVBP']")
	public WebElement HHVB_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='HPI']")
	public WebElement HPI_Application;

	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='InCK']")
	public WebElement InCK_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='IPC Portal']")
	public WebElement IPC_Application;	
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='LSDM_DMARS']")
	public WebElement LSDM_DMARSApplication;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='MDPCP']")
	public WebElement MDPCP_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='MHDR']")
	public WebElement MHDR_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='MOM']")
	public WebElement MOM_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='OCMR']")
	public WebElement OCMR_Application;	
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='OCM+']")
	public WebElement OCM_Plus_Application;		
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='PCF/SIP']")
	public WebElement PCF_SIPApplication;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='ROM']")
	public WebElement ROM_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='RFAA']")
	public WebElement RFAA_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='TCPI']")
	public WebElement TCPI_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='SAPT4']")
	public WebElement SAPT4_Application;
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='CPCBIR']")
	public WebElement CPCBIR_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='ICBIR']")
	public WebElement ICBIR_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='MDPCPBIR']")
	public WebElement MDPCPBIR_Application;	
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='QMAT']")
	public WebElement QMAT_Application;
	
	@FindBy(how=How.XPATH, using="//div[@class='uc-app-acronym-holder']//span[text()='QMATR']")
	public WebElement QMATR_Application;
		
}
