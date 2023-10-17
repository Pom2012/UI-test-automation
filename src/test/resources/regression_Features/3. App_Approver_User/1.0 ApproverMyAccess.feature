@regression
@IC_Approver
Feature: IC Application Approver can see "My Access", "Approvals", and "Annual Role Certifications" tiles

  Scenario Outline: Verify IC app. Approver role in "My Access", "Approvals" and "Annual Role Certifications" tiles
    Given TestName "IC app Approver role tiles" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    Then user successfully logged in
    And user is logged in as "<role>"
    Then views "Approvals" tile, clicks on it and see "My Pending Approvals"
    Then clicks on "Annual Role Certifications" tile and see the below:
      | Header | Manage Annual Role Certifications |
      | Tab 1  | Annual Role Certifications List   |
      | Tab 2  | Search Annual Role Certifications |
    Then logout
    Examples:
      | role                                   |
      | Innovation Center Application Approver |