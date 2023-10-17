@UpdatePwdParallel
Feature: Updating multiple IC Users' password

#  Scenario Outline: Updating multiple IC Users' password
#    Given TestName "Updating multiple IC Users' password" the landing page is loaded
#    When a user "1" logs in with "non-MFA"
#    And clicks on "Help Desk / Manage Users" tile and views the page
#    And iterates the updating process for "<userNumbers>" and "<tab1>" and "<tab2>"
#    Then logout
#    Examples:
#      | userNumbers | tab1 | tab2 |
#      | 100         | OW4  | NW4  |

  Scenario Outline: IC HD Admin updates IC Users password
    Given TestName "IC User updates password" the landing page is loaded
    When IC user fills the login page input boxes for "<userNumbers>" and "<tab1>" and "<tab2>"
    Then logout
    Examples:
      | userNumbers | tab1 | tab2 |
      | 161           | NW4  | ND3  |

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
