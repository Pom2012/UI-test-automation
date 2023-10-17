@regression
@IC_Approver
Feature: IC Application Approver can access User Verification - Non Participant Users report

  Background:
    Given TestName "An IC Admin can access reports" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | User Verification - Non Participant Users | PCF_Dummy |

  Scenario Outline: The Summary and Detailed History are displaying in "User Verification - Non Participant Users" report
    Then sees "<Application>" and the below features of the "<SubReport>":
      | Sub report name    | <SubReport> | -         | -          | -        | -                 | -                   |
      | Select dropdown    | Year        | Show      | entries    | -        | -                 | -                   |
      | Search text field  | Search      | -         | -          | -        | -                 | -                   |
      | Table columns name | Role        | Attribute | Start Date | End Date | Completed Reviews | Not Started Reviews |
      | Buttons            | Search      | Excel     | Download   | -        | -                 | -                   |
    When scrolls down to "<SubReport2>"
    Then sees "<Application>" and the below features of the "<SubReport2>":
      | Sub report name    | <SubReport2> | -       | -          | -         | -     | -    | -         | -           | -           | -                | -             | -           |
      | Select dropdown    | Year         | Show    | entries    | -         | -     | -    | -         | -           | -           | -                | -             | -           |
      | Search text field  | Search       | -       | -          | -         | -     | -    | -         | -           | -           | -                | -             | -           |
      | Table columns name | Request ID   | User ID | First Name | Last Name | Email | Role | Attribute | User Status | Review Date | Reviewer User ID | Reviewer Name | Year/Period |
      | Buttons            | Search       | Excel   | Download   | Back      | -     | -    | -         | -           | -           | -                | -             | -           |
    Then logout
    Examples:
      | Application | SubReport | SubReport2       |
      | PCF_Dummy   | Summary   | Detailed History |

  Scenario Outline: The Select entries from Detailed History sub report in "User Verification - Non Participant Users" report
    When scrolls down to "<SubReport2>"
    Then selects the below numbers entries from "<SubReport2>" and sees the dynamic showing entries:
      | 10 | 25 | 50 | 100 | 250 | 500 | 1000 |
    Then logout

    Examples:
      | SubReport2       |
      | Detailed History |

#  Scenario Outline: 3) Verify Excel and Download Report features files from Summary and Detailed History in "User Verification - Non Participant Users" report
#    Then "<Report>" from "<reporttype>" page is displayed
#    When scrolls down to "<SubReport2>"
#    And clicks on the below "<buttonName>" and see the desktop "<downloaded_file_name>":
#      | buttonName           | Excel                            |
#      | downloaded_file_name | User Verification History Report |
#    And clicks on the below "<buttonName>" and see the desktop "<downloaded_file_name>":
#      | buttonName           | Download Report       |
#      | downloaded_file_name | ApplicationUserDetail |
#    Then logout


#    Examples:
#      | Report    | SubReport | SubReport2       | reporttype                                |
#      | PCF_Dummy | Summary   | Detailed History | User Verification - Non Participant Users |