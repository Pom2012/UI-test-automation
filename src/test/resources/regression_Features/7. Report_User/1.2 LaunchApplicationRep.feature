@regression
@Report
Feature: IC Report+PV role user launch IC downstream application

  Background:
    Given TestName "IC Report+  PV user launch the application" the landing page is loaded
    When a user "3" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center

  Scenario Outline: Verify Report+PV role user can access IC downstream applications
    Then "Home | CMS Innovation Center" page is loaded
    When selects <AppName> tile and views <AppDisplayName> and <AddHeading>
    Then logout
    Examples:
      | AppName    | AppDisplayName                         | AddHeading           |
      | "ET3"      | "ET3"                                  | "Home and Resources" |
      | "HDR"      | "HDR"                                  | "Home"               |
      | "CDX"      | "CMMI Centralized Data Exchange (CDX)" | "Home"               |
      | "eDFR"     | "Data Feedback Tool (DFT)"             | "Primary Care First" |
      | "BPCI"     | "BPCI Advanced"                        | "Download Files"     |
      | "InCK"     | "Integrated Care for Kids (InCK) Home" | "Home"               |
      | "ICBIR"    | "IC_REPORTS"                           | "Home"               |
      | "MDPCPBIR" | "MDPCP"                                | "Home"               |
      | "PCF"      | "Primary Care First (PCF) Home"        | "Home"               |