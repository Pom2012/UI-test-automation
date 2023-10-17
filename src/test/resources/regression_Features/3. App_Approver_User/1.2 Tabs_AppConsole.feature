@regression
@IC_Approver
Feature: IC Application Approver user role can access 4 tabs in Application Console

  Scenario Outline: Verify user with IC Application Approver role can access access 4 tabs in Application Console
    Given TestName "App Approver can access 4 tabs in Application Console" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    Then sees the below tabs as an IC application BO:
      | Home            |
      | Request Access  |
      | Confirm Access  |
    Then logout

    Examples:
      | row_index |
      | 2         |