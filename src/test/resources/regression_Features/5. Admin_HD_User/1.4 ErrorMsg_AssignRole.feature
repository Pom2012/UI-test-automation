@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can see the Error Messages in Assign Role / Support Center

  Scenario Outline: Verify IC Admin + HD can see Error Messages in Assign Role / Support Center
    Given TestName "Verify IC Admin + HD can see Error Messages in Assign Role / Support Center" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Assign Role" and see the page
    When clicks on "Assign" button
    Then views the below errors:
      | User ID is invalid!          | User ID is required          |
      | Application Name is invalid! | Application Name is required |
      | Role is invalid!             | Role is required             |
      | Justification is invalid!    | Justification is required    |
    Then logout

    Examples:
      | row_index |
      | 1         |