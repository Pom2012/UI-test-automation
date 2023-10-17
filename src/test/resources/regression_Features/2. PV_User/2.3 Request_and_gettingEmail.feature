@regression
@PV
Feature: IC PV user-requester and user-approver/rejector can receive emails with Pending, Needs Action, Approved and Rejected status

  Scenario Outline: 1) Verify an IC PV User can request an access and receive an email for Pending request
    Given TestName "an IC Privileged User can a request for access" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    When user "<row_index>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    When logged into personal email and looks for the request ID and the condition below
      | sender email ID   | CMMI-IC-noreply@cms.hhs.gov          |
      | inbox folder name | Pending                              |
      | email subject     | Innovation Center: Request - Pending |
    Then logout
    Examples:
      | row_index | app                  | app_role            |
      | 3         | Old New Year Display | Old New Year User 3 |

  Scenario Outline: Verify IC PV User as a BO can receive an email when approves and rejects the request
    Given TestName "Privileged User as a IC app BO can get an email, approve/reject the request" the landing page is loaded
    When logged into personal email and looks for the request ID and the condition below
      | sender email ID   | CMMI-IC-noreply@cms.hhs.gov    |
      | inbox folder name | NeedsActionPending             |
      | email subject     | Pending Request - Needs Action |
    Then a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Confirm Access page
    Then approves the request
    And user is on "Confirm Access" page
    And request has been approved
    When logged into personal email and looks for the request ID and the condition below
      | sender email ID   | CMMI-IC-noreply@cms.hhs.gov           |
      | inbox folder name | Approved                              |
      | email subject     | Innovation Center: Request - Approved |
    Then rejects the request
    When logged into personal email and looks for the request ID and the condition below
      | sender email ID   | CMMI-IC-noreply@cms.hhs.gov           |
      | inbox folder name | Rejected                              |
      | email subject     | Innovation Center: Request - Rejected |
    Then logout
    Examples:
      | row_index |
      | 5         |