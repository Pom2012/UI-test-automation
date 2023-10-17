@PV
@regression
@KNOWN_ISSUE
Feature: IC User can log into Innovation Center through Unauth page

  Scenario Outline: Verify an IC User can log into Innovation Center through Unauth page
    Given TestName "IC PV User can log in through the IC Unauth page" the landing page is loaded
    When a "user" clicks on CMS "Applications" and select "IC-Innovation Center" from "Select Your Application"
    Then "IC-Innovation Center" with texts pops up
    And user scroll down to "Get Started" button and navigate to "Applications" page in another windows tab
    When enter "<appName>" in "Search for an application" text field and click on it
    Then user is redirected to login box
    When a user "<row_index>" logs in with "non-MFA"
    Then the application main page and "<appName>" is displayed
    Then logout
    Examples:
      | row_index | appName                                      |
      | 5         | Emergency Triage, Treat, and Transport (ET3) |