@regression
@ICAdmin_HD
Feature: IC Admin + IC HD role role user can see IC roles in My Access tab

  Scenario Outline: Verify IC Administrator + Helpdesk role user can see IC roles in My Access tab
    Given TestName "IC Admin + HD role user can see roles in My Access tab" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And user is logged in as "<role>"
    Then logout
    Examples:
      | row_index | role                                                              |
      | 1         | Innovation Center Administrator + Innovation Center Helpdesk User |