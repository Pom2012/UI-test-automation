@regression
@IC_Approver

Feature: IC Application Approver can see error messages in Request Access

  Scenario Outline: Verify IC Application Approver can see error messages in Request Access
    Given TestName " IC app Approver can see error messages in RA" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    And opens Request New Access page
    And clicks on "Confirm" button without filling the fields
    Then views the below errors in request access module:
      | Application Name is invalid! | Application Name is required |
      | Role is invalid!             | Role is required             |
      | Justification is invalid!    | Justification is required    |
    Then logout

    Examples:
      | row_index |
      | 2         |