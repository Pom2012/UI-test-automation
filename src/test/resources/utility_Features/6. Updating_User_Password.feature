#//REVIEW: This file is likely replaced by 7f.MonthlyUpdatingPwds for a utility
# If similar is needed for Test purposes, the method of looping users should be revised
#Feature: 6) Updating multiple IC Users' password
#  Scenario Outline: Updating multiple IC Users' password
#    Description: In this scenario, IC Admin+HD/IC HD user searches for users in Enterprise Search?Help Desk Tile
#    resets passwords, generating Temporary Passwords. The code retrieves users ID from Excel file and repeats the
#    action. The Temporary Passwords stores in the file. You need to point to numbers of users that match with
#    numbers of for loop iteration.
#    Updated August 4th, 2021....

#    Given TestName "Updating multiple IC Users' password" the landing page is loaded
#    When a user "1" logs in with "non-MFA"
#    And clicks on "Help Desk / Manage Users" tile and views the page
#    And iterates the updating process for "<userNumbers>"
#    Then logout
#    Examples:
#      | userNumbers |
#      | 10          |

#    Description: In this scenario, the users, that passwords changed for, updating the passwords, retrieving and using
#    the Temporary Passwords from excel file, created above. You need to point to numbers of users that match with
#    numbers of for loop iteration.
#    This is ONLY for users that does not have MFA!!!!!!!!!!!!!!

#  Scenario Outline: IC HD Admin updates IC Users password
#    Given TestName "IC User updates password" the landing page is loaded
#    When IC user fills the login page input boxes for "<userNumbers>"
#    Examples:
#      | userNumbers |
#      | 18          |
#
#
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
#      | 1       |
