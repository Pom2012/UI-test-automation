@smoke
@cmmi-ac
Feature: IC PV user can request access to application and IC approver approves/rejects
  As an IC PV role user, I want to request access to an IC application,
  so IC approver role user  approves/rejects it

  Background:
    Given TestName "Request and confirm access cycle" the landing page is loaded

  Scenario: Verify user can request access to application
    When a user "15" logs in with "non-MFA"
    And clicks the "CMS Enterprise Portal" button by title
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "My Requests" tab
    When gets data from "testData.xlsx" and makes sure the role does not exist
    When clicks on "Request Access" tab
    When "user" get data from "testData.xlsx" and submit request to IC app
    Then logout

  Scenario: Verify approver user can approve the request
    When a user "19" logs in with "non-MFA"
    And clicks the "CMS Enterprise Portal" button by title
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "Approve Requests" tab
    And searches for request ID with "Pending" status and "Approve" it
    Then logout

  Scenario: Verify approver user can reject the request
    When a user "19" logs in with "non-MFA"
    And clicks the "CMS Enterprise Portal" button by title
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "Approve Requests" tab
    And searches for request ID with "Approved" status and "Reject" it
    Then logout