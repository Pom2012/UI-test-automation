package com.model.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage_Locators {
	
	
	//********************  EIDM ***********************************
	
	@FindBy(how=How.XPATH,using="//select[@id='cms-mfa-selectbox']")
	public WebElement MFADropDown;
	
	@FindBy(how=How.ID,using="cms-sendmfa-login-button-email")
	public WebElement sendMFAemail;


	//*[@id='cms-send-code-phone']

	@FindBy(xpath="//*[@id='cms-send-code-phone']")
	public WebElement sendMFAcode;


	@FindBy(how=How.XPATH,using="//input[@id='cms-mfa-securitycode-email']")
	public WebElement securityCode;

	@FindBy(how=How.XPATH,using="//input[@id='cms-verify-securityCode']")
	public WebElement mfaCodeFromEmail;

	//input[@id='cms-verify-securityCode']
	
	
	//********************  IDM ***********************************
	@FindBy(id="loginAction")//userid-label
	public WebElement loginBox;

	@FindBy(css="input#cms-login-userId")//userid-label
	public WebElement userID;
	
	@FindBy(css="input#cms-login-password")
	public WebElement password;

	@FindBy(css="#cms-myprofile-oldPwd")
	public WebElement oldPassword;

	@FindBy(css="#cms-myprofile-newPwd")
	public WebElement newPassword;

	@FindBy(css="#cms-myprofile-confNewPwd")
	public WebElement confirmNewPassword;

	@FindBy(how=How.ID,using="checkd")//input#checkd
	public WebElement checkbox;

	@FindBy(how=How.XPATH,using="//*[@id='cms-label-tc']")//input#checkd
	public WebElement agreeCheckbox;

	@FindBy(how=How.CSS,using="#cms-alert-box-container")//error meassage
	public WebElement systemError;
	
	@FindBy(how=How.XPATH,using="//option[@value='email']")
	public WebElement MFADropDownEmailOption;
	
	@FindBy(how=How.ID,using="cms-login-mfa-email")
	public WebElement securityCodeWarn;	
	
	@FindBy(how=How.ID,using="cms-email-factor")
	public WebElement emailSendingMess;		

	@FindBy(how=How.ID,using="cms-login-submit")
	public WebElement loginButton;	
	
	@FindBy(how=How.XPATH,using="//*[@id='cms-verify-code-submit']")
	public WebElement verifyButton;	
	
	@FindBy(how=How.ID,using="sadDIV")
	public WebElement incorrectCredentialsError;	
	
	@FindBy(how=How.XPATH,using="//span[@id='sadDIVeidm']")
	public WebElement incorrectMFAError;
	
	@FindBy(how=How.XPATH,using="//a[@id='cms-close-eidm']")
	public WebElement closeError;	
	
	@FindBy(how=How.ID, using="cms-newuser-reg")
	public WebElement newUserRegLink;
	
	@FindBy(how=How.ID, using="cms-forgot-userid")
	public WebElement forgotID;
	
	@FindBy(how=How.ID, using="cms-eidm-error")
	public WebElement authenticationError;
	
	
	
	
	
	
	
	
	
	
}