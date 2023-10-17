#@PV
#  this feature is not available in IC
Feature: IC PV - Non-Participant Approver can access and see “Non-Participant Users” tab's page

  Background:
    Given TestName "UV Non-Participant Approver" the landing page is loaded
    When a user "15" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page

  Scenario Outline: Verify UV tabs pages for Non-Participant Approver
    And "<tab1>" and "<tab2>" are displaying
    Then views "<header>" page title and the below "web table columns":
      | Application | Organization | Year/QTR | Start Date | End Date | Review Status | Action |
    And views "User Verification Schedule" page title and the below "features":
      | Search feature  | Search Schedule |
      | Select dropdown | Items per page  |
    When clicks on "<tab2>" and sees the table of the following columns:
      | Application | Role | Attributes | Year/Period | Start Date | End Date | Review Status | Action |
    Then views "User Verification Schedule" page title and the below "features":
      | Search feature  | Search Schedule |
      | Button          | Approve         |
      | Select dropdown | Items per page  |
    Then logout
    Examples:
      | header                     | tab1              | tab2                  |
      | User Verification Schedule | Participant Users | Non Participant Users |

  Scenario Outline: Verify select dropdowns in "<title>" page for Non-Participant Approver
    And "<tab1>" and "<tab2>" are displaying
    When clicks on "<tab2>" and sees the table of the following columns:
      | Application | Role | Attributes | Year/Period | Start Date | End Date | Review Status | Action |
    When clicks on "Approve" button
    Then see the page "<title>" and below data info:
      | Application | Role | Year/Period | User Verification Window |
    And "Associated Users" web table with the below "Select dropdowns" features:
      | Bulk Verification         | Review Status |
      | Verify users on this page | All           |
      | Verify users on all pages | Completed     |
      | -                         | Not Started   |
    Then logout
    Examples:
      | tab1              | tab2                  | title                          |
      | Participant Users | Non Participant Users | Non Participant Users - Review |

  Scenario Outline: Verify table column header's name in "<title>" page for Non-Participant Approver
    And "<tab1>" and "<tab2>" are displaying
    When clicks on "<tab2>" and sees the table of the following columns:
      | Application | Role | Attributes | Year/Period | Start Date | End Date | Review Status | Action |
    When clicks on "Approve" button
    Then see the page "<title>" and below data info:
      | Application | Role | Year/Period | User Verification Window |
    And views "<title>" page title and the below "web table columns":
      | User ID | First Name | Last Name | Email | Start Date | User Status | Justification | Review Status |
    Then logout
    Examples:
      | tab1              | tab2                  | title                          |
      | Participant Users | Non Participant Users | Non Participant Users - Review |

  Scenario Outline: Verify Non Participant Approver can see buttons in "<title>" page
    And "<tab1>" and "<tab2>" are displaying
    When clicks on "<tab2>" and sees the table of the following columns:
      | Application | Role | Attributes | Year/Period | Start Date | End Date | Review Status | Action |
    When clicks on "Approve" button
    Then views "<title>" page title and the below "buttons":
      | Excel  |
      | Back   |
      | Cancel |
      | Submit |
    Then logout
    Examples:
      | tab1              | tab2                  | title                          |
      | Participant Users | Non Participant Users | Non Participant Users - Review |

  Scenario Outline: Verify Non Participant Approver can download Excel file in "<title>" page
    And "<tab1>" and "<tab2>" are displaying
    When clicks on "<tab2>" and sees the table of the following columns:
      | Application | Role | Attributes | Year/Period | Start Date | End Date | Review Status | Action |
    When clicks on "Approve" button
    And clicks on "Excel" file from "<title>" page and views the desktop version of the file
    Then logout
    Examples:
      | tab1              | tab2                  | title                          |
      | Participant Users | Non Participant Users | Non Participant Users - Review |