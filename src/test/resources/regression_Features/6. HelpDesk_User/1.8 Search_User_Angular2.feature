Feature: Angular 2 Search module

  Background:
    Given TestName "Angular 2 Search Users module" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    Then user is on the Innovation Center's "Support Center" module page

  Scenario: Verify empty search result
    Then "CMMI Helpdesk" banner, "Search Users" tab page and the below tabs are displayed:
      | Assign Role      |
      | User Profile     |
      | Warning State    |
      | Email Sender     |
      | Email Log        |
      | Batch Operations |
      | Search Users     |
    And the below texts and fields are displayed:
      | Text field name | User ID                  | Email Address       | First Name       | Last Name       |
      | Text box info   | Enter User ID            | Enter Email Address | Enter First Name | Enter Last Name |
      | Dropdown name   | Application Name         | Role                | -                | -               |
      | Dropdown info   | -- Select an application | -                   | -                | -               |
      | Buttons         | Search                   | Reset               | -                | -               |
    And logout




#    When clicks on "Search" button and see the below alert message:
#      | Please enter at least one search term to perform search |
#    And logout
#
#  Scenario Outline: Verify search result for invalid data
#    When typed a invalid value in "User ID" search text field
#    Then clicks on "Search" button and see the below alert message:
#      | <alert_message> |
#    When clicks on "Reset" button
#    When typed a invalid value in "Email Address" search text field
#    Then clicks on "Search" button and see the below alert message:
#      | Please enter a valid email |
#    When clicks on "Reset" button
#    When typed a invalid value in "First Name" search text field
#    Then clicks on "Search" button and see the below alert message:
#      | <alert_message>                                                                                            |
#    When clicks on "Reset" button
#    When typed a invalid value in "Last Name" search text field
#    Then clicks on "Search" button and see the below alert message:
#      | <alert_message> |
#    And logout
#    Examples:
#      | alert_message                                                                                                |
#      | The user you are searching for does not have any request associated with the applications you are supporting |