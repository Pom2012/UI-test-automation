@regression
@IC_Approver
Feature: IC Application Approver can request an access for an app role with attribute

  Scenario Outline: Verify IC Application Approver can request access for an application "ONYD" role with attribute
    Given TestName "IC app Approver can request access for an app role with attribute" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    When user "<row_index>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    When user cancels request
    Then logout
    Examples:
      | row_index | app                  | app_role            |
      | 2         | Old New Year Display | Old New Year User 3 |