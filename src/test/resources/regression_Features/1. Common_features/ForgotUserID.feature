@forgotUserID
Feature: Forgot user ID


  @tag1
  Scenario Outline: US User retrieves forgotten user ID successfully
    Given TestName "forgotUserID" the landing page is loaded

    When clicks Forgot User ID link

    And fillout data SetForgotUserID "<row_index>" and "FirstName" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "LastName" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "BirthMonth" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "BirthDate" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "BirthYear" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "Email" from excel sheet
    And fillout data SetForgotUserID "<row_index>" and "ZipCode" from excel sheet

    And clicks Submit button

    Then confirmation message displays

    Examples:
      | row_index |
      | 1         |

  

  
