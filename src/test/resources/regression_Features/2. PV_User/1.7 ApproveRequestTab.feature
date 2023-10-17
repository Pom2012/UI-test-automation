@regression
@PV
Feature: IC PV user approver can see and use Approve Requests tab functionality

  Background:
    Given TestName "Confirm Access tab page features" the landing page is loaded
    When a user "5" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "Approve Requests" tab
    Then "Approve Requests | Innovation Center" title is displayed

  Scenario: Verify Approve Requests tab page and features

    Then logout


  Scenario: Verify IC PV user can see view options in Confirm Access tab as an application BO
    Then selects the below options from select drop-down and views different page options:
      | View All             |
      | View Assigned To All |
      | View Assigned To Me  |
      | Business Owner View  |
    Then logout
