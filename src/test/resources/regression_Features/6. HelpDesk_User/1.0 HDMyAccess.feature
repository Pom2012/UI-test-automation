@regression
@hd
Feature: IC Helpdesk role user can see IC role in My Access tab

  Scenario Outline: Verify IC Helpdesk Role in My Access Tab
    Given TestName "IC Administrator role user can see IC role in My Access tab" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And user is logged in as "<role>"
    Then logout
    Examples:
      | row_index | role                            |
      | 8         | Innovation Center Helpdesk User |