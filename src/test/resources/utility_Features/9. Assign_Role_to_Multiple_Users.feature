@assignquick
Feature: 9) IC HD and Admin role user can assign a model role to multiple IC Users

  Scenario Outline: Assign a model role to multiple IC Users
    Given TestName "IC HD and Administrator role user can assign a model role with/without attributes to an IC User" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Assign Role" and see the page
    Then for each row, Assign the Role, and then if "<isTestDataToRejectYN>" is Y: reject it by Request ID
    Then logout current user
    Examples:
      | isTestDataToRejectYN |
      | Y                    |
#    mvn clean verify -Dbrowser=ChromeIncognito -Denvironment=DEVSBTEST -Dcucumber.filter.tags="(@assignquick)" -DvarSrc=CMD_LN

#  mvn clean verify
#  -Dbrowser=ChromeIncognito
#  -Denvironment=DEVSBTEST
#  -Dcucumber.filter.tags="(@assignquick)"
#  -DvarSrc=CMD_LN