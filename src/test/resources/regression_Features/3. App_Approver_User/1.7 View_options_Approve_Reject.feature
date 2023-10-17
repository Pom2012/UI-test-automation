@regression
@IC_Approver

Feature: IC Application Approver as an app BO can see view options in in Application console

  Scenario Outline: Verify view options in Approve/Reject portlet as IC Application Approver
    Given TestName "IC Application Approver can use view options in Approve/Reject portlets" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    When user clicks on "Confirm Access" as a app BO
    Then selects the below options from select drop-down and views different page options:
      | View All             |
      | View Assigned To All |
      | View Assigned To Me  |
      | Business Owner View  |
    Then logout

    Examples:
      | row_index |
      | 2         |