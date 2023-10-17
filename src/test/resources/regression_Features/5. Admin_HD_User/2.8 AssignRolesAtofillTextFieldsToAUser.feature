@regression
@ICAdmin_HD
Feature: IC Admin + HD user can see "First name", "Last name", and "Role" text autofill fields when types a user ID

  Background:
    Given TestName "First name, Last name and Role text autofill fields in Assign role tab" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Assign Role" and see the page

  Scenario: 1) Verify if an IC HD Admin role user can see the user data when enter user ID
    Then enters and selects values for the below fields for a role:
      | user ID     | a user's ID    |
    Then logout
