@smoke
@cmmi-ac
Feature: IC PV + Report user can access applications
  IAs an C PV + Report user role user, I want to have access and use IC downstream applications

  Background:
    Given TestName "PV & Report role user verification" the landing page is loaded

  Scenario: 1) Verify IC PV + Report user role can see Business Intelligence
    When a user "3" logs in with "non-MFA"
    And clicks the "CMS Enterprise Portal" button by title
    And can see Add Application tile
    Then can see Business Intelligence tile
    Then logout

  Scenario Outline: 2) Verify IC user can access IC applications
    When a user <user> logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    Then "Applications | CMS Innovation Center" page is loaded
    When selects <appName> tile and views <appDisplayName> and <addHeading>
    Then logout
    Examples:
      | user | appName    | appDisplayName                         | addHeading          |
#      | "3"  | "ET3"      | "ET3"                                  | "Home and Resources" |
#      | "3"  | "HDR"      | "HDR"                                  | "Home"               |
      | "3"  | "CDX"      | "CMMI Centralized Data Exchange (CDX)" | "Home"               |
#      | "3"  | "eDFR"     | "Data Feedback Tool (DFT)"             | "Primary Care First" |
#      | "3"  | "BPCI"     | "BPCI Advanced"                        | "Download Files"     |
#      | "3"  | "InCK"     | "Integrated Care for Kids (InCK) Home" | "Home"               |
#      | "3"  | "ICBIR"    | "IC_REPORTS"                           | "Home"               |
#      | "3"  | "MDPCPBIR" | "MDPCP"                                | "Home"               |
#      | "3"  | "PCF"      | "Primary Care First (PCF) Home"        | "Home"               |