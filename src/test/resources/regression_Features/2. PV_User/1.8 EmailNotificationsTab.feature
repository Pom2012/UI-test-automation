@regression
@PV
Feature: IC PV user can view and manage Email Notifications

  Background:
    Given TestName "Email Notification options" the landing page is loaded
    When a user "19" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "Email Notifications" tab
    Then "Email Notifications | Innovation Center" title is displayed

  Scenario: Verify the  Email Notifications options are displayed
    Then the below Email Notifications options are displayed:
      | You request a role                                   |
      | Your role request is approved or rejected            |
      | You need to take an action on someone else's request |
      | Our system administrator assigns you a role          |
      | You will receive no email notifications              |
    Then logout

  Scenario: Verify the default de/selected Email Notifications options
    Then the below the default de/selected Email Notifications options are displayed:
      | You request a role                                   | deselected |
      | Your role request is approved or rejected            | deselected |
      | You need to take an action on someone else's request | deselected |
      | Our system administrator assigns you a role          | deselected |
      | You will receive no email notifications              | selected   |
    Then logout

#  Scenario: Verify selected receive no email notifications option
#    And selects the below and verify the rest options are unselected
#      | You will receive no email notifications    | selected   | Y |
#      | You request a role                         | unselected |   |
#      | Your role request is approved or rejected  | unselected |   |
#      | You need to take an action on someone      | unselected |   |
#      | You need to take an action on someone else | unselected |   |
#    Then logout
#
#  Scenario: Verify confirm for selected receive no email notifications
#    And selects the below and verify the rest options are unselected
#      | You will receive no email notifications | selected | confirm |
#    Then user clicks on "Confirm" button
#    And alert message "Your email preference has been updated" is displayed
#    Then logout
#
#  Scenario: Verify confirm for select everything options and unselect receive no email notifications
#    And unselects the below and selects the rest options
#      | You will receive no email notifications |
#    Then user clicks on "Confirm" button
#    And alert message "Your email preference has been updated" is displayed
#    Then logout
