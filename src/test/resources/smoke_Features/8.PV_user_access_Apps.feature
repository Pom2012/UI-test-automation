@smoke
@cmmi-ac
Feature: IC PV user can access downstream application
  As an IC PV role user, I want to have access to downstream application
  Background:
    Given TestName "IC applications access" the landing page is loaded

  Scenario Outline: Verify PV user can access IC downstream application
    When a user <user> logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    Then "Applications | CMS Innovation Center" page is loaded
    When selects <AppName> tile and views <AppDisplayName> and <AddHeading>
    Then logout
    Examples:
      | user | AppName | AppDisplayName | AddHeading |
      | "5"  | "AHC"   | "AHC"          | "Home"     |