Feature: Log in to IC Portal with deselected "Agree" checkbox

  Scenario Outline: Verify if a user can log into IC Portal with deselected Agree checkbox
    Given TestName "Deselected Agree checkbox verification" the landing page is loaded
    When "<row_index>" enters the required value for "UserName"
    And "<row_index>" enters the required value for "Password"
    And leaves deselected "I agree to terms and conditions" checkbox and clicks on "Login" button
    Then "Please Agree to the Terms & Conditions." dialog is popped up
    Examples:
      | row_index |
      | 1        |