#@UtilAssignRoleParallel
#Feature: 9.1) Parallel - Assign a model role from multiple excel tabs by Parallel test running
#
#  Scenario Outline: Parallel <tabName> - is Test Data <isTestDataToRejectYN>
#    Given TestName "Parallel <tabName> - is Test Data <isTestDataToRejectYN>" the landing page is loaded
#    When a user "1" logs in with "non-MFA"
#    And clicks on "Support Center" link in Innovation Center
#    And clicks on "Assign Role" and see the page
#    And from Tab "<tabName>": for each row, Assign the Role, and then if "<isTestDataToRejectYN>" is Y: reject it by Request ID
#    Then logout current user
#    Examples:
## tabName: short env name _ tabName ; ex.running DEV0, tab 1  tabNum 1 = DEV_1 in Excel
## isTestDataToRejectYN - "Y" after assign, REJECT reusable Test Data.
#    #  "UI" - Enter Data in UI but do not click Assign ~ useful when using a mass of unknown Custom Attributes
#      | tabName | isTestDataToRejectYN |
#      | ACO5    | N                    |

#    mvn clean verify -Dbrowser=ChromeIncognito -Denvironment=DEVSBTEST -Dcucumber.filter.tags="(@UtilAssignRoleParallel)" -DvarSrc=CMD_LN

#  mvn clean verify -Dbrowser=ChromeIncognito -Denvironment=VALAWS -Dcucumber.filter.tags="(@UtilAssignRoleParallel)" -DvarSrc=CMD_LN