@regression
@hd
Feature: IC Helpdesk role user can see Page Content on User Details Screen in "Search Users" tab

  Scenario Outline: Verify IC Help Desk role user can see Page Content on User Details Screen
    Given TestName "IC Help Desk role user can see Page Content on User Details Screen" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And user is logged in as "<role>"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    Then clicks on user ID hyperlink after the search result displayed a web table
    And see the below content:
      | User full name                                  |
      | Basic Information                               |
      | Email Preference                                |
      | Requests Made by this User                      |
      | Requests Assigned to this User                  |
      | Application Role Requests Assigned to this User |
    Then logout

    Examples:
      | row_index | role                            |
      | 8         | Innovation Center Helpdesk User |