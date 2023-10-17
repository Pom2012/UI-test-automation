
Feature: 0.1.8 IC PV user can log in with MFA as IC PV user

  @sanityCheck
    @MyRun
  Scenario Outline: Verify IC PV User logs in successfully with MFA
    Given TestName "IC PV user logs in with MFA" the landing page is loaded
    When a user "<row_index>" logs in with "MFA"
#    Then user successfully logged in
#    And user is logged in as "<role>"
#    Then logout
    Examples:
      | row_index | role                              |
      | 10        | Innovation Center Privileged User |

#  Scenario Outline: Verify a user with an invalid MFA code cannot log in
#    Given TestName "A User cannot log in with invalid MFA" the landing page is loaded
#    When "<row_index>" enters the required value for "UserName"
#    And "<row_index>" enters the required value for "Password"
#    And selects "I agree to terms and conditions" checkbox and clicks on "Login" button
#    And clicks on Send MFA Code, then enters an invalid MFA code
#    Then "System Error" and "MFA Code is incorrect." message is displayed
#    Examples:
#      | row_index |
#      | 10        |