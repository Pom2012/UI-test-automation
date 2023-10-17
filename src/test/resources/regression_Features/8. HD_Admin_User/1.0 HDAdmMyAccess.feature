Feature: IC Helpdesk Administrator role user can see IC role in My Access tab

  Scenario Outline: Verify Helpdesk Administrator Role in My Access Tab
    Given TestName "Helpdesk Administrator role user can see the role in My Access tab" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And user is logged in as "<role>"
    Then logout
    Examples:
      | row_index | role                                     |
      | 17        | Innovation Center Helpdesk Administrator |