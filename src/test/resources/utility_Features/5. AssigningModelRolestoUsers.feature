#//REVIEW: This version is likely outdated -  9.1f) Parallel - Assign a model role from multiple excel tab and filename by Parallel test running
Feature: 5) Assigning a model role to an IC User/IC PV user from Support Center

  Scenario Outline: IC HD and Administrator role user can assign a simple model role to an IC User

    Given TestName "IC HD and Administrator role user can assign a model role to an IC User " the landing page is loaded
    When a user "1" logs in with "non-MFA status"

    And clicks on Support Center link in Innovation Center
    And clicks on "Assign Role" and see the page
    Then user fills out the "<users>", "Argonauts", "Role"
    And clicks the "CMS Enterprise Portal" button by title
    And logout

    Examples:
      | users |
      | 175   |


  Scenario: Assign a IPC model role to an IC User
    Given TestName "Assign a IPC model role to an IC User" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Assign Role" and see the page
    Then user fills out the below data:
      | users number     | 500                                               |
      | Application Name | Innovation Payment Contractor Portal (IPC Portal) |
      | Role             | Designated Official                               |
      | MODEL            | PCF                                               |
      | PARTICPANT_ID    | IPC200-IPC Model Test                             |
      | NPI              | 123123123                                         |
      | Justification    | Approving for SR-0983, IPC performance testing    |
    And clicks the "CMS Enterprise Portal" button by title
    And logout




#  Scenario Outline: Verify if an IC HD and Administrator role user can assign a model role to an IC User
#
#    Given TestName "IC HD and Administrator role user can assign a model role to an IC User " the landing page is loaded
#    When fillout data SetLogIn "<row_index>" and "UserName" from excel sheet
#    And fillout data SetLogIn "<row_index>" and "Password" from excel sheet
#    And user without MFA loggs in

#    And clicks on Support Center link in Innovation Center
#    And clicks on "Assign Role" and see the page
#
#    And "<role>" clicks on "Assign Role" and see the page
#
#    And fills out the "User ID" field with "<row_index2>"
#    And selects "<AppName>" from "Application Name" select drop down field
#    And selects "User role" from "<modelRole>" field
#    And "<modelRole>" fills out the rest "<attr>" field with "<shortCutHint>" and selects the value
#    And writes in the justification text box and clicks on "Assign" button
#    Then "Confirmation" message with "Request ID" is displayed

#    And logout

#    Examples:
#      | row_index | row_index2 | role                  | AppName | modelRole | attr | shortCutHint |
#      | 19        | 1          | IC Admin + IC HD User | 1       | 1         | 1    | 1            |
#      | 1         | 2          | IC Admin + IC HD User | 2       | 2         | 2    | 2            |
#      | 19         | 1          | IC Admin + IC HD User | 1       | 1         | 1    | 1            |








