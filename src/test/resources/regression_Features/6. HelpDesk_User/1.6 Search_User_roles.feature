@regression
@HD
Feature: IC Helpdesk role user can see error messages when searching invalid data

  Background:
    Given TestName "IC Helpdesk role user can Search" the landing page is loaded
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page

  Scenario: Verify empty search result
    When clicks on "Search" button and see the below alert message:
      | Please enter at least one search term to perform search |
    And logout

  Scenario Outline: Verify search result for invalid data
    When typed a invalid value in "User ID" search text field
    Then clicks on "Search" button and see the below alert message:
      | Search did not return any results |
    When clicks on "Reset" button
    When typed a invalid value in "Email Address" search text field
    Then clicks on "Search" button and see the below alert message:
      | Please enter a valid email |
    When clicks on "Reset" button
    When typed a invalid value in "First Name" search text field
    Then clicks on "Search" button and see the below alert message:
      | <alert_message> |
    When clicks on "Reset" button
    When typed a invalid value in "Last Name" search text field
    Then clicks on "Search" button and see the below alert message:
      | <alert_message> |
    And logout
    Examples:
      | alert_message                                                                                                |
      | The user you are searching for does not have any request associated with the applications you are supporting |