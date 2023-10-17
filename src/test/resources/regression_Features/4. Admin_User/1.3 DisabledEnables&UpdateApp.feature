@regression
@ICAdmin
Feature: IC Admin user can disabled, enabled, and update an IC application
  Background:
    Given TestName "IC Admin role user can disabled an application" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center

  Scenario: Verify IC Admin user can disabled an application
    And searches for the test application, sees it is "Enabled", and clicks "Update"
    When user scroll down, select "Disable" from Application Status and clicks on "Update Application"
    Then user navigates back to data table, search for the newly "Disabled" application and see "red" icon
    Then logout

  Scenario: Verify IC Admin user can enabled an application
    And searches for the test application, sees it is "Disabled", and clicks "Update"
    When user scroll down, select "Enable" from Application Status and clicks on "Update Application"
    Then user navigates back to data table, search for the newly "Enabled" application and see "green" icon
    Then logout

#//TODO: TC: This test needs major refactoring
#  Scenario Outline: 3) Verify IC Administrator user can update a role
#    Given TestName "IC Administrator role user can disable an application" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on Administration Console link in Innovation Center
#    And "<role>" sees "Admin page" after clicking "Administration Console"
#    And clicks on roles icon after searching the application in search text field
#    Then sees "Update Application Role Information" page after clicking on "Update" icon from a "<modelRole>" table row
#    When scrolling down and select "Enabled" from "Status" select dropdown
#    Then see "<modelRole>" after clicking on "Update" button
#
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#    Examples:
#      | row_index | role                            | modelRole  |
#      | 7         | Innovation Center Administrator | Model User |