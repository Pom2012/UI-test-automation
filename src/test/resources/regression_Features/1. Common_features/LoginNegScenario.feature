Feature: Invalid combination of User ID and Password -> negative scenario for login feature

  Scenario Outline: Verify a user logs in with invalid combination of User ID and Password unsuccessfully
    Given TestName "login With invalid user ID and Password" the landing page is loaded
    When a user enters random value to "Username" fields
    And enters random value to "Password" fields
    And selects "I agree to terms and conditions" checkbox and clicks on "Login" button
    Then the application displays "System Error" and "<Message>"
    Examples:
      | Message                                                                                      |
      | Invalid combination of User ID and Password. Enter valid User ID and Password and try again. |