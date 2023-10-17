@regression
@Report
Feature: IC Report + PV role user can see IC role in My Access tab

  Scenario Outline: Verify IC Report +PV Role in My Access Tab
    Given TestName "IC Report +PV role user can see IC role in My Access tab" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And user is logged in as "<role>"
    Then logout
    Examples:
      | row_index | role                                                              |
      | 3         | Innovation Center Report User + Innovation Center Privileged User |