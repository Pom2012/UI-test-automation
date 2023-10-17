@regression
@ICAdmin
Feature: IC Admin user can see the Manage Applications module's functionalities

  Background:
    Given TestName "Manage Applications module's functionalities" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center

  Scenario: Verify the "Show entries" select dropdown in Manage Applications module
    When selects the below values from "Show entries" and see the results in showing entries:
      | 10 | 25 | 50 | 100 |
    Then logout

  Scenario: Verify the "First" & "Previous" buttons are disabled if the "1" page is selected for "10" entries
    And see the "First" & "Previous" are "disabled" when the "1" page is selected for "10" entries
    Then logout

  Scenario: Verify the "Next", "Previous", "First", and "Last" buttons
    And selects "10" entries, clicks on the below buttons and see the showing entries value:
      | Next | Previous | First| Last |
    Then logout