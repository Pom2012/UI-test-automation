@IC_Approver
@regression

Feature: IC Application Approver can see, download and use User verification report

  Scenario: Verify IC app Approver can see the changes in the User Verification report
    Given TestName "IC app Approver can see the changes in the reports" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    And selects an IC application from "User Verification" dropdown and clicks on "View Report"
    Then scrolls down to "User Verification History Report" and search for "<practice>"
    When counts the numbers of the Report entries
    And clicks on "Download Report" button for "User Verification"
    Then see the same amounts of entries in "UserVerificationReport.csv" file as it is in the report web table
    And logout



# EXPLANATION: before to run this feature, you need to check and update:
#         as an IC Admin: the practice name, practice Quarter, Start Date & End Date, YEAR
#         as an IC user: Users

#  THIS SCENARIO CAN BE RUN ONLY ONCE A MONTH NOT TO STORE DATA IN DB

#  Scenario Outline: 1) Verify an IC Admin can create a new quarter for User Verification practice
#    Given TestName "Verify an IC Admin can create a new quarter for a practice" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on "Administration Console" link in Innovation Center
#    When "<role>" types in searchbox "<AppName>" for user verification, the system displays "<AppName>"
#    Then  "<role>" clicks on shield icon for checking "<AppName>" user verification
#    And "User Verification Management" and the "<AppName>" are displayed
#    When Clicks on "Add New Quarter" button and see "Associated Users:" page
#    And selects given information for the below features and confirm it:
#      | My Practice Selection | Virginia  |
#      | Select year           | 2022      |
#      | Select quarter        | 1         |
#      | Start Date            | Today     |
#      | End Date              | 12 months |
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#    Examples:
#      | row_index | role                            | AppName                  |
#      | 1         | Innovation Center Administrator | AMR Verification Testing |

#  Scenario Outline: 2) Verify an IC PV user can request an access to a practice for which the user verification is enabled
#    Given TestName "Verify an IC PV user can request an access to a practice for which the user verification is enabled" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA"
#    And clicks on "Application Console" link in Innovation Center
#    And opens "Request Access" page
#    And search and cancel one's own previous requests for "AMR Verification Testing" app
#    And selects the below features:
#      | Application name | AMR Verification Testing |
#      | Role             | Practice User            |
#      | Entry identify   | VA                       |
#      | List Entry       | Virginia                 |
#    And Clicks on "Add", provides justification and submit the request
#    Then "<row_index>" as a "<role>" received conformation message for "<appAcronym>"
#    Then logout
#    Examples:
#      | row_index | user | role                              | appAcronym |
#      | 14        |      | Innovation Center Privileged User | AMRVT      |
#
#  Scenario Outline: 3) Verify an IC PV user-approver can approve the request submitted by PV user for user verif. practice
#    Given TestName "Verify an approver approves PV user for a Practice user role" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA"
#    And clicks on "Application Console" link in Innovation Center
#    And opens Confirm Access page
#    Then IC "<role>" approves the "PENDING" request for the model "<application>" for user "<assignee_row_index>"
#    Then logout
#    Examples:
#      | row_index | assignee_row_index| role                                   | application |
#      | 2         | 14                | Innovation Center Application Approver | AMRVT       |
#
#  Scenario Outline: 4) Verify an IC Admin+IC HD role user can assign a practice model role to an IC user
#    Given TestName "IC Admin+HD user assigns a practice role to an IC User " the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA"
#    And clicks on "Support Center" link in Innovation Center
#    And clicks on "Assign Role" and see the page
#    Then enters the user ID, selects the below data, clicks on "Add" button and submit the operation
#      | Application              | Model Role    | Entry Identify | List Entry | User ID |
#      | AMR Verification Testing | Practice User | VA             | Virginia   |         |
#    Then logout
#    Examples:
#      | row_index |
#      | 1         |
#
#  Scenario Outline: 5) Verify IC PV user - a practice reviewer can review the users approved/assigned before the current practice
#    Given TestName "IC practice reviewer can review IC practice" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA"
#    And clicks on "User Verification" link in Innovation Center
#    Then sees "User Verification Schedule" page
#    When clicks on "Review" button
#    Then views "Associated Users" page with the below data:
#      | Application              | My Practice | QTR/Year: | User ID | Association Start Date |
#      | AMR Verification Testing | VA-Virginia |           | users   | approved date          |
#    When searches the user, provides the below action to review, selects the checkbox, justify and confirm it:
#      | Active Practice User | Past Users | Justification                                                          |
#      | Yes                  | N/A        | The approved/assigned user positively reviewed for user verification   |
#      | No                   | Yes        | The approved/assigned past user rejected for this practice             |
#      | No                   | No         | The approved/assigned new user rejected for user verification practice |
#    Then logout
#    Examples:
#      | row_index | role                 |
#      | 14        | Innovation Center PV |
#
#  Scenario Outline: 6) Verify IC app Approver can see the changes in Application Summary report
#    Given TestName "IC app Approver can see the changes in the reports" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on "Report Center" link in Innovation Center
#    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
#      | Application Summary | AMR Verification Testing |
#    Then scrolls down to "User Role Details for Application" after "<report>" table is displayed
#    And views the user ID, below data when searches the request ID in "<report>":
#      | Role Display Name | Custom Attribute  | Request Status | Last Action Type | Approval Date  | Rejection Date |
#      | Practice User     | State:VA-Virginia | REJECTED       | Standard         | user appr date | user rej date  |
#      | Practice User     | State:VA-Virginia | APPROVED       | Standard         | user appr date | blank          |
#      | Practice User     | State:VA-Virginia | REJECTED       | Standard         | user appr date | user rej date  |
#    Then logout
#    Examples:
#      | row_index | report                     |
#      | 2         | Application Summary Report |
#
#  Scenario Outline: 7) Verify IC app Approver can see the changes in the User Verification report
#    Given TestName "IC app Approver can see the changes in the reports" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on "Report Center" link in Innovation Center
#    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
#      | User Verification | AMR Verification Testing |
#    Then scrolls down to "User Verification History Report" and search for "<practice>"
#    And views the user ID, below data when searches the request ID in "<report>":
#      | Active Practice Users | Past Users |
#      | Yes                   | N/A        |
#      | No                    | Yes        |
#      | No                    | No         |
#    Then logout
#    Examples:
#      | row_index | report            | practice      |
#      | 2         | User Verification | VA - Virginia |
#
#  Scenario: 8) Verify IC Admin + IC HD role user cancels the assigned users' access to the practice
#    Given TestName "The assigned user access rejected by IC Admin + IC HD" the landing page is loaded
#    When a user "1" logs in with "non-MFA"
#    And clicks on "Support Center" link in Innovation Center
#    When clicks on "Batch Operations" and selects the below:
#      | Application Name | AMR Verification Testing |
#      | Role             | Practice User            |
#      | State            | VA                       |
#    And selects "APPROVED" options from "Status" dropdown, clicks on the first row checkbox and rejects the request
#    Then logout


