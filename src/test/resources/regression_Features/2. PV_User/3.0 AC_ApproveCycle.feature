Feature: IC PV user can submit multiple role requests to IC applications

  Scenario: Verify IC PV user approver can approve multiple role requests to IC applications
    Given TestName "IC PV user submits multiple role requests to IC apps" the landing page is loaded
    When a user "19" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    When get access to Angular update for "Application console"
    Then the below tabs are displayed on "Application Console" page:
      | Applications | Request Access | My Requests | Approve Requests | Email Notifications |
    When clicks on "Approve Requests" tab
    When "user" get data from "XXXX.xlsx" and submit request to IC app
    Then logout