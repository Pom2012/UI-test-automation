@hd
@regression
Feature: IC Helpdesk user can Sort the Search Results in Search Users Screen

  Background:
    Given TestName "IC Help Desk user can Sort the Search Results in Search Users Screen" the landing page is loaded
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box

  Scenario: Verify Application Role Request Information web table columns is sortable
    Then clicks on user ID hyperlink after the search result displayed a web table
    And selects the below radio button options in user details screen and see the web table:
      | Requests Made by this User | Application Role Request Information |
    Then sorts the below columns and orders it in "ascending" sequence:
      | Request ID |
    And sorts the below columns and orders it in "descending" sequence:
      | Request ID |
    Then logout

  Scenario: Verify All Requests available to this user for approval web table columns is sortable
    Then clicks on user ID hyperlink after the search result displayed a web table
    And selects the below radio button options in user details screen and see the web table:
      | All Requests available to this user for approval | Application Role Requests Available to this User for Approval |
    Then sorts the below columns and orders it in "ascending" sequence:
      | Request ID |
    And sorts the below columns and orders it in "descending" sequence:
      | Request ID |
    Then logout
