@icMaintenance
Feature: 7f) Monthly update of automation user passwords By Excel filename and tab
#    Description: In this only for internal usage.
#    Update Test User passwords to avoid expiring, causing Test Causes to fail
#//ENH: If first (3) row(s) are OK, assume all others are...?
# If running all, can this do some checks like User Permissions, etc, for healthCheck
Scenario Outline: Update automation users passwords By Excel filename and tab
    Given TestName "Update automation user passwords By Excel filename and tab" the landing page is loaded
    When from Tab "<tabName>" in "<filename>": for each row, Login in. If needed, change the password
    Examples:
    |  filename        |  tabName  |
    |  testData2.xlsx  |  env      |

#mvn clean verify -Dbrowser=Chrome -Denvironment=DEVSBTEST -Dcucumber.filter.tags=@icMaintenance -DvarSrc=CMD_LN
#mvn clean verify -Dbrowser=Chrome -Denvironment=VALAWS -Dcucumber.filter.tags=@icMaintenance -DvarSrc=CMD_LN