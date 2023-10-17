@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can see Page Content on User Details Screen

  Scenario Outline: Verify IC Admin + IC HD user can see Page Content on User Details Screen
    Given TestName "IC Admin + IC HD user can see Page Content on User Details Screen" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
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
      | row_index |
      | 1         |