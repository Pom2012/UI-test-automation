package com.model.locators.supportCenterLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class HelpDesk_Locators {

    //******************** EIDM + IDM ***********************

    @FindBy(how = How.XPATH, using = "//a[text()='Assign Role']")
    public WebElement assignRoleTab;

    @FindBy(how = How.XPATH, using = "//select[@id='assignRoleForm_ic-select_applicationId_5']")
    public WebElement assignRoleApplName;

    @FindBy(how = How.NAME, using = "assignRoleForm")
    public WebElement assignRoleSubpage;

    @FindBy(how = How.XPATH, using = "//a[text()='User Profile']")
    public WebElement userProfileTab;

    @FindBy(how = How.ID, using = "userProfile")
    public WebElement userProfSubPage;

    @FindBy(how = How.NAME, using = "hdForm")
    public WebElement batchOperSubPage;

    @FindBy(how = How.XPATH, using = "//a[text()='Batch Operations']")
    public WebElement batchOperationsTab;

    @FindBy(how = How.XPATH, using = "//a[text()='Search Users']")
    public WebElement searchUsersTab;

    @FindBy(how = How.XPATH, using = "//a[text()='Warning State']")
    public WebElement warningTab;

    @FindBy(how = How.ID, using = "searchusers")
    public WebElement searchSubPage;

    @FindBy(how = How.XPATH, using = "//button[text()='run warning state']")
    public WebElement warningRun;

    @FindBy(how = How.XPATH, using = "//a[text()='Email Sender']")
    public WebElement emailSender;

    @FindBy(how = How.XPATH, using = "//h2[text()='Email Sender']")
    public WebElement emailSenderHeader;


    @FindAll({
            @FindBy(how = How.XPATH, using = "//a[text()='Assign Role']"),
            @FindBy(how = How.XPATH, using = "//a[text()='User Profile']"),
            @FindBy(how = How.XPATH, using = "//a[text()='Batch Operations']"),
            @FindBy(how = How.XPATH, using = "//a[text()='Warning State']")
    })
    public List<WebElement> tabList;

}
