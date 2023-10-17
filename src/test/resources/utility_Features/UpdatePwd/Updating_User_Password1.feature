Feature: Updating multiple IC Users' password

#  Scenario Outline: Updating multiple IC Users' password
#    Given TestName "Updating multiple IC Users' password" the landing page is loaded
#    When a user "1" logs in with "non-MFA"
#    And clicks on "Help Desk / Manage Users" tile and views the page
#    And iterates the updating process for "<userNumbers>" and "<tab1>" and "<tab2>"
#    Then logout
#    Examples:
#      | userNumbers | tab1 | tab2 |
#      | 100         | OW1  | NW1  |

#  mvn clean verify -Dbrowser=ChromeIncognito -Denvironment=VALAWS -Dcucumber.filter.tags="(@UpdatePwdParallel)" -DvarSrc=CMD_LN

#  Scenario Outline: IC HD Admin updates IC Users password
#    Given TestName "IC User updates password" the landing page is loaded
#    When IC user fills the login page input boxes for "<userNumbers>" and "<tab1>" and "<tab2>"
#    Then logout
#    Examples:
#      | userNumbers | tab1 | tab2 |
#      | 167           | NW1  | ND1  |


Scenario Outline: IC PV User logs in successfully without MFA
Given TestName "IC PV user logs in without MFA" the landing page is loaded
When a user "<row_index>" logs in with "non-MFA"
Then user successfully logged in
Then logout

Examples:
| row_index |
| 11        |



#  Scenario Outline: Regular changing of IC test automation user's passwords
#    Given TestName "IC User updates password" the landing page is loaded
#    When a user "<userName>" logs in with "non-MFA"
#    When selects "My Profile" from the user's welcome dropdown feature
#    Then views "My Profile" page and clicks on "Change Password"
#    And "<userName>" enters data for the below text fields and submits the changes:
#      | Current Password | New Password | Confirm New Password |
#    Then logout
#    Examples:
#      | userName |
#      | 1        |
