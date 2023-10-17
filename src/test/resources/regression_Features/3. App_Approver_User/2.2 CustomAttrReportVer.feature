@regression
@IC_Approver
Feature: IC Application Approver can see the updated version of Custom Attribute Report

  Background: Verify IC Application Approver can get access to the updated CAR
    Given TestName "The new updates CA Report" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | Custom Attribute | Argonauts |

  Scenario Outline: IC Application Approver can see "Custom Attribute Summary" Report
    Then sees "<Application>" and the below features of the "<SubReport>":
      | Sub report name    | <SubReport> | -                | -         | -                |
      | Select dropdown    | Show        | entries          | -         | -                |
      | Search text field  | Search      | -                | -         | -                |
      | Table columns name | Role        | Custom Attribute | List Name | Parent List Name |
    Then logout
    Examples:
      | Application | SubReport                |
      | Argonauts   | Custom Attribute Summary |

  Scenario Outline: IC Application Approver can see and select "Custom Attribute Values for Application" Reports
    Then sees "<Application>" and the below features of the "<SubReport>":
      | Sub report name    | <SubReport> | -                | -         | -                |
      | Select dropdown    | Show        | entries          | -         | -                |
      | Search text field  | Search      | -                | -         | -                |
      | Table columns name | Role        | Custom Attribute | List Name | Parent List Name |
    When scrolls into view the "<SubReport2>"
    Then sees "<Application>" and the below features of the "<SubReport2>":
      | Sub report name    | <SubReport2> | -        | -           | -               | -                  | -      |
      | Select dropdown    | Show         | entries  | -           | -               | -                  | -      |
      | Search text field  | Search       | -        | -           | -               | -                  | -      |
      | Table columns name | List Name    | Entry ID | Entry Value | Parent Entry ID | Parent Entry Value | Status |
      | Buttons            | Search       | Excel    | -           | Back            | -                  | -      |
    Then selects the below numbers entries from "<SubReport2>" and sees the dynamic showing entries:
      | 10 | 25 | 50 | 100 | 250 | 500 | 1000 |
    Then logout

    Examples:
      | Application | SubReport                | SubReport2                              |
      | Argonauts   | Custom Attribute Summary | Custom Attribute Values for Application |