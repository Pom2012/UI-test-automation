@regression
@hd
Feature:IC Helpdesk role user can Search by each field on IC Search Users screen

  Background:
    Given TestName "IC Helpdesk role user can Search by each field on Search Users screen" the landing page is loaded
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page

  Scenario: Verify IC Helpdesk role user can Search by each field on Search Users screen
    Then searches the given information in below fields in "Search Users":
      | User ID          |
      | Email Address    |
      | First Name       |
      | Last Name        |
      | Application Name |
      | Role             |
    Then logout

  Scenario: Verify Search returned table and results in Search Users module
    When typed a "user" ID in search text box
    Then views the below columns and values in "Search":
      | User Login | First Name | Last Name | Email Address |
    Then logout

  Scenario: Verify Search returned table and results in Search Users module
    When typed a "user" ID in search text box
    Then views the below columns and values in "Search":
      | User Login | First Name | Last Name | Email Address |
    Then logout
