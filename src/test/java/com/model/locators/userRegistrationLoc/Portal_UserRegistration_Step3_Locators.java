package com.model.locators.userRegistrationLoc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Portal_UserRegistration_Step3_Locators {

	@FindBy(how=How.CSS,using="#cms-newuser-newuserId")
	public WebElement uName;
	
	@FindBy(how=How.ID, using="cms-newuser-password")
	public WebElement passw;
	
	@FindBy(how=How.CSS, using= "#cms-newuser-confirmPassword")
	public WebElement passwConf;


	@FindBy(how=How.ID, using= "cms-newuser-securityQuestion")
	public WebElement secQuestion;
	@FindBy(how=How.ID, using= "cms-newuser-securityAnswer")
	public WebElement secQuestionAnswer;

	@FindBy(how=How.ID, using= "questionOne")
	public WebElement questionOne;

	@FindBy(how=How.ID, using= "questionTwoAnswer")
	public WebElement questionTwoAnswer;
	
	@FindBy(how=How.ID, using= "questionThreeAnswer")
	public WebElement questionThreeAnswer;
	
	@FindBy(how=How.ID, using= "questionOneAnswer")
	public WebElement questionOneAnswer;
	
	//questionOneAnswer
	@FindBy(how=How.ID, using= "questionTwo")
	public WebElement questionTwo;
	
	@FindBy(how=How.ID, using= "questionThree")
	public WebElement questionThree;
	
	
	@FindBy(how=How.ID, using= "cms-nur-question")
	public WebElement quest1;
	
	@FindBy(how=How.ID, using= "cms-nur-question-answer")
	public WebElement answ1;
	
	@FindBy(how=How.ID, using= "step3buttonBack")
	public WebElement step3BackLink;
	
	@FindBy(how=How.ID, using= "step3btnNxt")
	public WebElement step3NextLink;
	
	@FindBy(how=How.ID, using= "step3-cancel")
	public WebElement step3CancelLink;
	
	@FindBy(how=How.ID, using= "submitUser")
	public WebElement submitUser;
	
	 ////////////ERROR MESSAGES & EMPTY FIELDS: ///////////////////	
	
	@FindBy(how=How.ID, using="cms-user-id-error")
	public WebElement uNameError;
		
	@FindBy(how=How.ID,using="cms-user-id-error-508")
	public WebElement uNameError508;
		
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-user-id') and contains(@class,'cms-error-border')]")
	public WebElement uNameEmpty;
	
	@FindBy(how=How.ID, using="cms-user-password-error")
	public WebElement passwError;
	
	@FindBy(how=How.ID,using="cms-user-password-error-508")
	public WebElement passwError508;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-user-password') and contains(@class,'cms-error-border')]")
	public WebElement passwEmpty;
	
	@FindBy(how=How.ID, using="cms-user-password-conf-error")
	public WebElement passwConfError;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'cms-user-password-conf') and contains(@class,'cms-error-border')]")
	public WebElement passwConfEmpty;
	
	@FindBy(how=How.ID,using="cms-user-password-conf-error-508")
	public WebElement passwConfError508;
	
	@FindBy(how=How.ID, using="questionOne-error")
	public WebElement quest1Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionOne') and contains(@class,'cms-error-border')]")
	public WebElement quest1Empty;
	
	@FindBy(how=How.ID,using="questionOne-error-508")
	public WebElement quest1Error508;
	
	@FindBy(how=How.ID, using="questionTwo-error")
	public WebElement quest2Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionTwo') and contains(@class,'cms-error-border')]")
	public WebElement quest2Empty;
	
	@FindBy(how=How.ID,using="questionTwo-error-508")
	public WebElement quest2Error508;
	
	@FindBy(how=How.ID, using="questionThree-error")
	public WebElement quest3Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionThree') and contains(@class,'cms-error-border')]")
	public WebElement quest3Empty;
	
	@FindBy(how=How.ID,using="questionThree-error-508")
	public WebElement quest3Error508;
	
	@FindBy(how=How.ID, using="questionOneAnswer-error")
	public WebElement answ1Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionOneAnswer') and contains(@class,'cms-error-border')]")
	public WebElement answ1Empty;
	
	@FindBy(how=How.ID,using="questionOneAnswer-error-508")
	public WebElement answ1Error508;
	
	@FindBy(how=How.ID, using="questionTwoAnswer-error")
	public WebElement answ2Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionTwoAnswer') and contains(@class,'cms-error-border')]")
	public WebElement answ2Empty;
	
	@FindBy(how=How.ID,using="questionTwo-error-508")
	public WebElement answ2Error508;
	
	@FindBy(how=How.ID, using="questionThreeAnswer-error")
	public WebElement answ3Error;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id,'questionThreeAnswer') and contains(@class,'cms-error-border')]")
	public WebElement answ3Empty;
	
	@FindBy(how=How.ID,using="questionThree-error-508")
	public WebElement answ3Error508;

	@FindBy(how=How.ID,using="cms-newuser-newuserId")
	public WebElement userID;
	
	
	 ////////////ERROR MESSAGES wrong input FIELDS: ///////////////////	
	
	
	@FindBy(how=How.ID,using="cms-user-id-error-format")
	public WebElement userIDerror;
	
	@FindBy(how=How.XPATH, using="//*[contains(text(),'Invalid format. Choose a different User ID based on the guidelines provided')]")
	public WebElement userIDinvalidFormat;
	
	//*[contains(text(),'Invalid format. Choose a different User ID based on the guidelines provided')
	
}



 
