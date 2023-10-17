@regression
@IC_Approver
Feature: IC Application Approver can see and use the User Role Details for Application of Application Summary Report

  Background: Verify IC Application Approver can see the updated ASR with new columns
    Given TestName "The updated Application Summary Report" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | Application Summary | Old New Year Display |
    Then scrolls down to "User Role Details for Application" after "Application Summary Report" table is displayed

  Scenario: Verify IC Application Approver can see the table headings are displayed in ASR
    Then the below report table columns name are displayed:
      | Request ID | User ID | First & Last Name | Email | Role Display Name | Custom Attribute | Request Status | Last Action Type | Approval Date | Rejection Date | Justification |
    Then logout

  Scenario: Verify IC Application Approver can see the table's select options in ASR
    Then selects the below numbers entries from "User Role Details for Application" and sees the dynamic showing entries:
      | 10 | 25 | 50 | 100 | 250 | 500 | 1000 |
    Then logout

  Scenario: Verify IC Application Approver can see the search bar engine in ASR
    And searches the below values in the search bar of the "User Role Details" Report:
      | Request ID        | (1st Row)                   |
      | User ID           | (1st Row)                   |
      | First & Last Name | (1st Row)                   |
      | Email             | nw_tester@outlook.com       |
      | Custom Attribute  | (1st Row that is not empty) |
      | Justification     | (1st Row)                   |
    Then logout

  Scenario: Verify IC Application Approver can see the options from Request Status are displayed in ASR
    And selects the below option from Request Status and the option is displayed:
      | APPROVED | REJECTED | PENDING |
    Then logout

  Scenario: Verify IC Application Approver can see the "Next", "Previous", "First", and "Last" buttons in ASR
    And clicks on the below buttons and see the Report table changes:
      | Next | Previous | First | Last |
    Then logout

  Scenario Outline: Verify IC Application Approver can sort the report column
    And clicks on the below sortable "<report>" columns and order it in "descending" and "ascending" sequence:
      | Request ID |
    Then logout
    Examples:
      | report              |
      | Application Summary |

  Scenario: Verify IC Application Approver can download and see the "Excel" and "Download Report" in ASR
    And clicks on the below "<buttonName>" and see the desktop "<downloaded_file_name>":
      | buttonName           | Excel                           |
      | downloaded_file_name | Application User Details Report |
    And clicks on the below "<buttonName>" and see the desktop "<downloaded_file_name>":
      | buttonName           | Download Report        |
      | downloaded_file_name | ApplicationUserDetails |
    Then logout