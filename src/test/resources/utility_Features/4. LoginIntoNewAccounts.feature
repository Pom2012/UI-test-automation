#//REVIEW: Is this still neeeded?  Unclear what it'd do, as this is not the current Inbox.
# Likely should be combined with another utility such as the create so the flow makes the users complete, instead of needing this step.
#Feature: 4) Multiple IC PV users log in with MFA
#DEV,VAL, CHROME,FF

#  Scenario Outline: Verify IC PV User logs in successfully with MFA
#    Given TestName "IC PV user logs in with MFA" the landing page is loaded
#    When a user "<row_index>" logs with "icp_app@outlook.com" and "Bdj12#13a!" in with "MFA" successfully
#    Then logout

#    Examples:
#      | row_index |
#      | 1         |
#      | 2         |
