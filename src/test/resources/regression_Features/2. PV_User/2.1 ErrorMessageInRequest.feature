@regression
@PV
Feature: User can see error messages on app page

  Background:
    Given TestName "Error Messages in Request Access" the landing page is loaded
    When a user "12" logs in with "non-MFA"
    And clicks on "Application Console" link
    And opens Request New Access page

  Scenario: Verify an user 1 cannot submit unselected Request and see an Error Messages in Request Access
    And clicks on "Confirm" button without filling the fields
    Then views the below errors in request access module:
      | Application Name is invalid! | Application Name is required |
      | Role is invalid!             | Role is required             |
      | Justification is invalid!    | Justification is required    |
    Then logout

  Scenario Outline: Verify the system does not allow requests with empty Selected values(s) box.
    When selects options from the below features:
      | Application Name |
      | Role             |
      | Model ID         |
      | Participant ID   |
    And types justification and clicks on "Confirm" button
    Then accepts the alert box with the "<text>" when it is displayed
    Then logout
    Examples:
      | text                                               |
      | Please add at least one custom attribute value(s). |

  Scenario: Verify "All fields are required unless specified as optional" text is displaying
    Then "All fields are required unless specified as optional." text is displaying
    Then logout

