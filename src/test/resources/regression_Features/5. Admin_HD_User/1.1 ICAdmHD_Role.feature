@regression
@ICAdmin_HD
Feature: IC Admin + IC HD role user can access "Support Center" and see all the tabs

  Scenario Outline: Verify IC Admin + IC HD role user can access Support Center and see all the tabs
    Given TestName "IC Admin + HD can access Support Center and see the tabs" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And user is logged in as "<role>"
    And clicks on "Support Center" link in Innovation Center
    Then "<role>" sees the below tabs in "Support Center" module:
      | Search Users    |
      | Assign Role     |
      | User Profile    |
      | Warning State   |
      | Email Sender    |
      | Email Log       |
      | Batch Operations |
    Then logout

    Examples:
      | row_index | role                                                              |
      | 1         | Innovation Center Administrator + Innovation Center Helpdesk User |