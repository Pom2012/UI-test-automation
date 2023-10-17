@regression
@IC_Approver
Feature: IC Application Approver user role can release, approve, and reject an IC user's request

  Scenario Outline: Verify IC user can submit a request to an application
    Given TestName "IC user can submit a request to an application" the landing page is loaded
    When a user "5" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    When user "<row_index>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    Then logout
    Examples:
      | row_index | app                  | app_role            |
      | 5         | Old New Year Display | Old New Year User 4 |


  Scenario: Verify IC application Approver can release, approve and reject an IC user's request
    Given TestName "IC application Approver can approve a request" the landing page is loaded
    When a user "2" logs in with "non-MFA"
    And user is logged in as "Innovation Center Application Approver"
    And clicks on "Application Console" link in Innovation Center
    And opens Confirm Access page
    Then approves the request
    Then rejects the request
    Then logout
