@regression
@ICAdmin
Feature: IC Admin user can disabled and enabled a Role

  Background:
    Given TestName "IC Administrator role user can modify a role" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center

  Scenario Outline: Verify IC Administrator user can disabled a role
    And clicks on roles icon after searching the application in search text field
    Then sees "Update Application Role Information" page after clicking on "Update" icon from a "<modelRole>" table row
    When scrolling down and select "Disabled" from "Status" select dropdown
    Then see "<modelRole> (Disabled)" after clicking on "Update" button
    Then logout
    Examples:
      | modelRole  |
      | Model User |

  Scenario Outline: Verify IC Administrator user can enabled a role
    And clicks on roles icon after searching the application in search text field
    Then sees "Update Application Role Information" page after clicking on "Update" icon from a "<modelRole>" table row
    When scrolling down and select "Enabled" from "Status" select dropdown
    Then see "<modelRole>" after clicking on "Update" button
    Then logout
    Examples:
      | modelRole  |
      | Model User |

  Scenario: Verify IC Administrator user can see Application User Role Hierarchy web table
    When searches "Argonauts" application in search bar, clicks on roles icon and see "User Roles" page
    Then user scrolls down and see the "Application User Role Hierarchy" web table