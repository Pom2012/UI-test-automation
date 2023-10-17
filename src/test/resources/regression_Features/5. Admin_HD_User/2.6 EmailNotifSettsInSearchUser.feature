@regression
@ICAdmin_HD
Feature: IC Admin + IC HD role user can view and select/deselect the Email Notification options in Search User tab

  Background:
    Given TestName "IC Admin + IC HD role user can view and select/deselect the Email Notification options" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    Then "CMMI Helpdesk" page is loaded
    When clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table


  Scenario: Verify Email Notification window dialog box is displayed
    When clicks on Email Preference settings icon
    Then "Email Notifications" options is popped-up with below select options:
      | Requests a role                                 |
      | Role request is approved or rejected            |
      | Needs to take an action on another request      |
      | Our system administrator assigns a role request |
      | User will receive no email notifications        |
    Then logout

  Scenario: Verify Email Notification window dialog box options are selected by default
    When clicks on Email Preference settings icon
    Then sees "selected" the below "Email Notifications" options:
      | Requests a role                                 |
      | Role request is approved or rejected            |
      | Needs to take an action on another request      |
      | Our system administrator assigns a role request |
    Then sees "unselected" the below "Email Notifications" options:
      | User will receive no email notifications |
    Then logout

  Scenario: Verify Email Notification cehck box options can be selected
    When clicks on Email Preference settings icon
    When clicks on "User will receive no email notifications" checkbox
    Then sees "unselected" the below "Email Notifications" options:
      | Requests a role                                 |
      | Role request is approved or rejected            |
      | Needs to take an action on another request      |
      | Our system administrator assigns a role request |
    Then logout