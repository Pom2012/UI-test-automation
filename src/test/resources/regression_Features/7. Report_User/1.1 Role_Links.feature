@regression
@Report
Feature: IC Report + PV role user can see reports in Business Intelligence tile

  Scenario Outline: Verify IC Report + PV user role can see reports in Business Intelligence tile
    Given TestName "IC PV & Report role user links verification" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And user is logged in as "<role>"
    Then clicks on "Enterprise MicroStrategy Reports" in "Business Intelligence" tile and sees reports
    Then logout
    Examples:
      | row_index | role                                                              |
      | 3         | Innovation Center Report User + Innovation Center Privileged User |


  Scenario Outline: Verify IC Report + PV user role can see Application Console page
    Given TestName "IC PV & Report role user links verification" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And "<role>" sees "Application Console page" after clicking "Application Console"
    Then logout
    Examples:
      | row_index | role                                                              |
      | 3         | Innovation Center Report User + Innovation Center Privileged User |