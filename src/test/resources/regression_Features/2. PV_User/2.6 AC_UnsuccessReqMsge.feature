@regression
@PV
Feature: IC PV user can see unsuccessful request messages

  Background:
    Given TestName "Unsuccessful Request(s) messages" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page

  Scenario: Verify Unsuccessful Request(s) message for a request with "Pending" status
    And selects the below features:
      | Application Name | Old New Year Display    |
      | Role             | Old New Year User 4     |
      | Simple AMR Text  | Re-request verification |
    And Clicks on "Add", provides justification and submit the request
    Then The below "Request Confirmation Message" is displayed:
      | Unsuccessful Request(s): 1 requests for following values already exist: Attributes: Simple AMR Text:Re-request verification- |
    And clicks on "OK" and close the window dialog
    Then logout

  Scenario: Verify Unsuccessful Request(s) message for a request with "APPROVED" status
    And selects the below features:
      | Application name  | Argonauts    |
      | Role              | Model User 2 |
      | Simple attribute: | Maryland     |
    And provides justification and clicks on "Confirm" button
    Then The below "Request Confirmation Message" is displayed:
      | Request for access was rejected as a request with these values already exists. |
    And clicks on "OK" and close the window dialog
    Then logout