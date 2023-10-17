package com.model.locators.adminConsoleLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ApplicationsManagement_Locators {
	
	//************************  EIDM + IDM ***************************************

	@FindBy(how=How.XPATH, using="//*[text()=' Application Management']")  
	public WebElement cmmiAppMan;

	@FindBy(id="portlet-title-header-container")
	public WebElement cmmiHDLable;

	@FindBy(how=How.CSS, using="input[title='Search within Application Management']")
	public WebElement applSearchbox;
	////div[@id="cicdim-app-table_wrapper"]//input[@type='search']
	@FindBy(how=How.XPATH, using="//table[@id='cicdim-app-table']")
	public WebElement appTable;

	@FindBy(how=How.XPATH, using="//table[@id='user-role-view']")
	public WebElement rolesTable;


	@FindBy(how=How.XPATH, using="//table[@id='user-role-view']")  
	public WebElement roleMapping;

	@FindBy(css="#landingpages-mp > div.row.pb-3.justify-content-center")
	public WebElement tilesLable;

}
