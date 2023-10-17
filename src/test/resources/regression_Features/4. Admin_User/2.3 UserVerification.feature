@ICAdmin
@regression
Feature: IC Admin user can access "User Verification Schedule" page

  Background:
    Given TestName "IC Admin User Verification Page" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
    When types "Accountable Health Communities" in search text box, the system displays the "Accountable Health Communities (AHC)"
    And clicks on "Accountable Health Communities (AHC)" shield icon

  Scenario Outline: Verify "Practice Users" tab page's features in IC Admin user account is displaying
    Then "User Verification Management" page for "Participant Users" tab with the below is displayed:
      | Tab's title name   | Associated Users      | <AppName> | -          | -        | -      |
      | Search text field  | Search Schedule       | -         | -          | -        | -      |
      | Table columns name | My Practice Selection | QTR/Year  | Start Date | End Date | Action |
      | Buttons            | Add New Quarter       | Back      | -          | -        | -      |
    Then logout
    Examples:
      | AppName                              |
      | Accountable Health Communities (AHC) |

  Scenario Outline: Verify "Non Practice Users" tab page's features in IC Admin user account is displaying
    And "User Verification Management" and the "<AppName>" are displayed
    When clicks on "Non Participant Users" tab
    Then "User Verification Management" page for "Non Participant Users" tab with the below is displayed:
      | Tab's title name   | Non Participant Users | <AppName>      | -           | -          | -        | -      |
      | Search text field  | Search Schedule       | -              | -           | -          | -        | -      |
      | Table columns name | Role Name             | Attribute Name | Period/Year | Start Date | End Date | Action |
      | Buttons            | Add New Period        | Back           | -           | -          | -        | -      |
    Then logout
    Examples:
      | AppName                              |
      | Accountable Health Communities (AHC) |

  Scenario Outline: Verify "Add New Period" page's features in IC Admin user account is displaying
    And "User Verification Management" and the "<AppName>" are displayed
    When clicks on "Non Participant Users" tab
    Then "User Verification Management" page for "Non Participant Users" tab with the below is displayed:
      | Tab's title name | Non Participant Users | <AppName> | - | - | - | - |
    When clicks on "Add New Period" button
    Then "<AppName>"'s "Add New Period" page for "Non Practice Users:" is displayed:
      | Select      | Role Name         | Select Year | Select Period |
      | Date picker | Start Date        | End Date    | -             |
      | Check box   | Bulk Verification | Disable     | -             |
      | Buttons     | Back              | cancel      | confirm       |
    Then logout
    Examples:
      | AppName                              |
      | Accountable Health Communities (AHC) |