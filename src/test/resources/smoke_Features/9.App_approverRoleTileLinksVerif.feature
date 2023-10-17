@smoke
Feature: IC App Approver role can access Application Console and Report Center
  As an IC Application Approver role user, I want an access to IC Application Console and Report Center modules

  Background:
    Given TestName "App Approver role user verification" the landing page is loaded
    When a user "2" logs in with "non-MFA"

  @cmmi-ac
  Scenario: Verify Application Console page and tabs
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    Then the below tabs are displayed on "Application Console" page:
      | Applications | Request Access | My Requests | Approve Requests | Email Notifications |
    Then logout

  @cmmi-rp
  Scenario Outline: Verify IC App Approver role can access Report Center
    And clicks on "Report Center" link in Innovation Center
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Last Accessed       |
      | User Access         |
      | Application Summary |
      | Application Usage   |
      | Custom Attribute    |
      | User Inactivity     |
      | User Verification   |
    Then logout
    Examples:
      | role                    | application                                       |
      | IC Application Approver | Innovation Payment Contractor Portal (IPC Portal) |