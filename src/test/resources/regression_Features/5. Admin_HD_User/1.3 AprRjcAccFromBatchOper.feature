@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user approves and rejects an access from Batch Operations and see it in Admin Activity Report

  Background:
    Given TestName "IC Admin & HD User can approve/reject from Batch Operation" the landing page is loaded
    When a user "1" logs in with "non-MFA status"

  Scenario: Verify if IC Admin + IC HD user can approve or reject access from Batch Operations
    And clicks on "Support Center" link in Innovation Center
    And from "Batch Operations" tab, selects "Old New Year Display" app "Old New Year User 4" role, and clicks "Search"
    Then "Application Role Request Information" table is displayed
    When selects "REJECTED" status, clicks on first row checkbox and clicks on "Approve Request(s)" button
    Then clicks on "Submit" and handle the alert after "Provide Justification for Approving" message is popped up
    And selects "APPROVED" from Status dropdown and see the approved role request
    When clicks to select the same request and clicks on "Reject Request(s)" button
    And sees the rejected request
    Then logout

  Scenario: Verify IC Admin + HD user can see the changes in Application Summary Report
    And clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | Application Summary | Old New Year Display |
    Then see the changes made in Batch Operations
    Then logout