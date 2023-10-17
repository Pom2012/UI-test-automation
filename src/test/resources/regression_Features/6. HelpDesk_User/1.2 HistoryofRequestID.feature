@regression
Feature: IC Helpdesk role user can see and use the User Details Screen features

  Background:
    Given TestName "IC Helpdesk role user can see User Details Screen" the landing page is loaded
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    Then user is on the Innovation Center's "Support Center" module page
    When typed a "approver" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table
    Then user details screen is displayed

  Scenario: Verify the radio button options in User Details Screen
    And selects the below radio button options in user details screen and see the web table:
      | Requests Made by this User                       | Application Role Request Information                          |
      | Requests Assigned to this User                   | Application Role Requests Assigned to this User               |
      | All Requests available to this user for approval | Application Role Requests Available to this User for Approval |
    Then logout

  Scenario: Verify the info icon and its text for the radio button option "All requests available to this user for approval"
    Then clicks on and see a text as expected for the below:
      | Requests Made by this User                       |
      | Requests Assigned to this User                   |
      | All Requests available to this user for approval |
    Then logout

  Scenario: Verify the table columns' headers and values in "Application Role Request Information" table
    Then views the below columns and values in "User Screen":
      | Request ID | Application Name | Application Role Name | Custom Attribute | Requested Date | Last Action Date | Status |
    Then logout

  Scenario: Verify Request History PopUp in User Details  screen / Search Users module
    And clicks on a request and see the "History of Request ID" is popped up
    Then views the below columns and values in "History of Request ID":
      | Date | Status | Performed By | Assigned To | Comments |
    Then logout

  Scenario: Verify the filters/select dropdown for Status column in "Application Role Request Information" table
    Then selects the below options and the system displays requests' status accordingly:
      | All | PENDING | APPROVED | REJECTED |
    Then logout