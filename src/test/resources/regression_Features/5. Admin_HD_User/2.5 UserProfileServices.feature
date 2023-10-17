@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can see and use User Profile Services

  Scenario Outline: Verify IC Admin + IC HD user can see amd use User Profile Services
    Given TestName "User Profile Services in Support Center" the landing page is loaded
    When a user "<login_row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "User Profile" and see the page
    When enters the username of row "<search_row_index>", selects "<application>" and "<version>"
    And clicks on "Send" button
    Then user see the user profile json body
    Then logout

    Examples:
      | login_row_index | search_row_index | application                                  | version   |
      | 1               | 5                | Emergency Triage, Treat, and Transport (ET3) | version 1 |