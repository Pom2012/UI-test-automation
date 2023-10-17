@regression
@IC_Approver
Feature: IC Application Approver role can request an access and cancel it

  Scenario Outline: Verify IC application Approver user can request an access and cancel it
    Given TestName "IC app. Approver can request an access and cancel it" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    When user "<row_index>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    When user cancels request
    Then logout
    Examples:
      | row_index | app                  | app_role            |
      | 2         | Old New Year Display | Old New Year User 4 |
