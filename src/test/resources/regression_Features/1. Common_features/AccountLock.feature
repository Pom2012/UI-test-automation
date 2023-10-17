@accountLock
Feature: Account Lock

  Scenario Outline: User logs in succesfully without MFA

    Given TestName "loginMFA" the landing page is loaded
    And fillout data SetLogIn "<row_index>" and "UserName" from excel sheet
    And fillout data SetLogIn "<row_index>" and "Password" from excel sheet
    And click I agree to terms and conditions textbox
    And click login button
    And click login button
    And click login button
    Then user is locked
    Examples:
      | row_index |
      | 27        |
#      | 12        |
#      | 13        |


   
   

        
  
 
 
    
    
    
 

       
