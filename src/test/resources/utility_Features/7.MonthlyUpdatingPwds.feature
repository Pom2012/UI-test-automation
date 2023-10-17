#//REVIEW: This version is likely outdated - replaced with 7f version that takes the filename only, and can do MFA as well.
#Feature: 7) Monthly updating passwords of automation users'

#  Scenario Outline: Changing IC Users' password
#    Description: In this only for internal usage. It is for monthly updating of pwds for users have no MFA
#    form "testData2" excel file to avoid the failing of the tests due to expired passwords.
#    You need to copy and paste it from TestData2Folder (src/test/resources/externFiles/TestData2/testData2.xlsx)

#    Given TestName "Updating multiple IC Users' password" the landing page is loaded
#    When a user "<usersNum>" logs in with "non-MFA"
#    And navigates to "My Profile" page and clicks on "Change Password" tab
#    And "<usersNum>" enters the current the password and the new password "#001" and submit it
#    Then the confirmation massage is displayed
#    Then logout
#    Examples:
#      | usersNum |
#      | 1     |
#      | 2     |
