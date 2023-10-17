@regression
@PV
Feature: IC PV user requests an access to the application with Dynamic Custom Attribute model role

  Scenario Outline: Verify an IC PV user can request an access to the app with dynamic custom attribute role
    Given TestName "Requesting an access to the application with DCA role" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    When verified the below does not exist with "PENDING" request status:
      | Role | Designated Official |
    And opens Request New Access page
    And selects the below features:
      | Application name       | Innovation Payment Contractor Portal (IPC Portal) |
      | Role                   | Designated Official                               |
      | MODEL                  | CJR                                               |
      | PARTICPANT_ID shortcut | CJR                                               |
      | PARTICPANT_ID          | 010024 - 010024-Jackson Hospital and Clinic, Inc. |
      | NPI                    | 12345678                                          |
    And provides a justification and clicks on "Confirm" button
    Then confirmation message for "IPC" is appeared and the request will show as pending
    Then searches for the request ID and cancel this request
    Then logout
    Examples:
      | row_index |
      | 11        |