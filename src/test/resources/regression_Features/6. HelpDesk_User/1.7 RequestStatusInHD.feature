@HD
@regression
Feature: IC HD role user can see all request's status for an IC user from Confirm Access tab

  Background:
    Given TestName "IC HD user can see a request for an access" the landing page is loaded

  Scenario: Verify an IC PV requestor can a request to an application
    When a user "4" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    And selects the below features:
      | Application name     | Old New Year Display                  |
      | Role                 | Old New Year User 5                   |
      | Model ID #2          | Emergency Triage, Treat and Transport |
      | Participant ID       | 12                                    |
      | Participant ID value | ET3-0012 - Testing Org Legal Name12   |
    And provides justification and clicks on "Confirm" button
    Then confirmation message will appear and the request will show as pending
    Then logout

  Scenario Outline: Verify the HD table displays PENDING request's status
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table
    Then selects the below radio buttons' options in user screen and see dynamic "<web table>":
      | All Requests available to this user for approval |
    And selects from dropdown the status and see the request ID as "<status>"
    Then logout
    Examples:
      | status  |
      | PENDING |

  Scenario: Verify IC PV approver can approve a request
    When a user "5" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Confirm Access page
    Then approves the request
    Then logout

  Scenario Outline: Verify the HD table displays APPROVED request's status
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table
    Then selects the below radio buttons' options in user screen and see dynamic "<web table>":
      | All Requests available to this user for approval |
    And selects from dropdown the status and see the request ID as "<status>"
    Then logout
    Examples:
      | status  |
      | APPROVED |

  Scenario: Verify IC PV approver can reject a request
    When a user "5" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Confirm Access page
    Then rejects the request
    Then logout

  Scenario Outline: Verify the HD table displays REJECTED request's status
    When a user "8" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table
    Then selects the below radio buttons' options in user screen and see dynamic "web table":
      | All Requests available to this user for approval |
    And selects from dropdown the status and see the request ID as "<status>"
    Then logout
    Examples:
      | status  |
      | REJECTED |