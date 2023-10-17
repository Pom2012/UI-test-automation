@regression
@PV
Feature: IC PV user can request an access to an application, receive email, and cancel the request

  Background:
    Given TestName "PV user can request for an IC app, cancel it, and receive email" the landing page is loaded
    When a user "14" logs in with "non-MFA"
    Then user successfully logged in
    And clicks on "Application Console" link in Innovation Center

  Scenario Outline: Verify PV user can request for an IC app, cancel it, and receive email
    And opens Request New Access page
    When user "<user>" submits new access request for app "<app>", role "<app_role>"
    Then confirmation message will appear and the request will show as pending
    When user cancels request
    Then logout
    Examples:
      | user | app                  | app_role            |
      | 14   | Old New Year Display | Old New Year User 4 |

  Scenario: Verify PV user can request to AHC application SNS model role and cancel it
    When verified the below does not exist with "PENDING" request status:
      | Role | AHC Screening/Navigation Site |
    And opens Request New Access page
    And selects the below features:
      | Application name          | Accountable Health Communities (AHC) |
      | Role                      | AHC Screening/Navigation Site        |
      | Screening/Navigation Site | 12                                   |
      | B64 - Large SNS list      | a value from a list                  |
    And Clicks on "Add", provides justification and submit the request
    Then confirmation message will appear and the request will show as pending
    Then user cancels request
    Then logout

  Scenario: Verify IC PV user can request to CDX for ET3/PCF custom attribute roles and cancel them
    When verified the below does not exist with "PENDING" request status:
      | Participant ID value | Testing Name 1         |
      | Participant ID value | Alliance Senior Health |
    And opens Request New Access page
    And selects the below features:
      | Application name     | CMMI Centralized Data Exchange (CDX)  |
      | Role                 | Model Participant                     |
      | Model ID #2          | Emergency Triage, Treat and Transport |
      | Participant ID       | 123                                   |
      | Participant ID value | 23456789 - Testing Name 1             |
      | Model ID #1          | Primary Care First                    |
      | Participant ID       | PC                                    |
      | Participant ID value | AR3679 - Alliance Senior Health       |
    And provides justification and clicks on "Confirm" button
    Then request confirmation message with "2" requests for a role is appeared
    Then logout

  Scenario: Verify IC PV user can request an access to OCM application and cancel it
    When verified the below does not exist with "PENDING" request status:
      | OCM ID value | OCM Data Registry Point of Contact |
    And opens Request New Access page
    And selects the below features:
      | Application name | Oncology Care Model (OCM)                          |
      | Role             | OCM Data Registry Point of Contact                 |
      | OCM ID           | OCM- 123                                           |
      | OCM ID value     | Golden Triangle Radiation Oncology, PLLC |
    And provides justification and clicks on "Confirm" button
    Then confirmation message for "OCM" is appeared and the request will show as pending
    Then logout

  Scenario: Verify IC PV user can request for a IPC/CPC+ role with Dynamic Custom Attribute and cancel it
    When verified the below does not exist with "PENDING" request status:
      | NPI | T1GB6123 |
    And opens Request New Access page
    And selects the below features:
      | Application name       | Innovation Payment Contractor Portal (IPC Portal) |
      | Role                   | Designated Official                               |
      | MODEL                  | CPC+                                              |
      | PARTICPANT_ID shortcut | 123                                               |
      | PARTICPANT_ID          | T1GB6123                                          |
      | NPI                    | 12345678                                          |
    And provides a justification and clicks on "Confirm" button
    Then confirmation message for "IPC" is appeared and the request will show as pending
    Then logout

  Scenario: Verify IC PV user can request for a IPC/CJR role with Dynamic Custom Attribute and cancel it
    When verified the below does not exist with "PENDING" request status:
      | PARTICPANT_ID | Jackson Hospital and Clinic |
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