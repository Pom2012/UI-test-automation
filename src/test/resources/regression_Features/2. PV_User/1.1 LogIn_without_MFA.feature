@PV
@regression
@sanityCheck
@healthCheck
Feature: IC PV user can login and access IC Application Console

  Scenario Outline: IC PV User logs in successfully without MFA
    Given TestName "IC PV user logs in without MFA" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    Then user successfully logged in
    And clicks on "Application Console" link in Innovation Center
    Then logout

    Examples:
      | row_index |
      | 11        |