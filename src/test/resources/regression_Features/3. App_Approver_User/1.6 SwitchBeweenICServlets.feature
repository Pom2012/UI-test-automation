@regression
@IC_Approver
Feature: IC Application Approver can switch from an IC servlet to another

  Scenario Outline: Verify an IC Application can switch from an IC servlet to another
    Given TestName "Switch from an IC servlet to another" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    Then clicks on the "My Apps" header button, and in Section "Innovation Center", clicks "Application Console"
    Then logout
    Examples:
      | row_index |
      | 2         |

  Scenario Outline: Verify an IC Application can switch from an IC servlet to another
    Given TestName "Switching from AppCons to Approvals" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    Then clicks on the "My Apps" header button, and in Section "IDM", clicks "Approvals"
    And views "My Pending Approvals" title and pages
    Then logout
    Examples:
      | row_index |
      | 2         |