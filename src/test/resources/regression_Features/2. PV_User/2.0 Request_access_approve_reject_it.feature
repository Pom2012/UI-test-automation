@regression
@PV
Feature: IC PV user as an application BO can release, approve, and reject a request of an IC user

  Background:
    Given TestName "IC PV user can a request for an access" the landing page is loaded

  Scenario Outline: Verify an IC PV user can a request to an application
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    When user "<row_index>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    Then logout
    Examples:
      | row_index | app                  | app_role            |
      | 3         | Old New Year Display | Old New Year User 4 |

  Scenario Outline: Verify IC PV user as a model app BO can approve and reject a request
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Confirm Access page
    Then approves the request
    And user is on "Confirm Access" page
    And request has been approved
    Then rejects the request
    Then logout
    Examples:
      | row_index |
      | 5         |