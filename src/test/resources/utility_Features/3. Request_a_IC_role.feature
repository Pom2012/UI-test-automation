#//REVIEW: This version is likely outdated -  9.1f) Parallel - Assign a model role from multiple excel tab and filename by Parallel test running
# Unless the Email Inbox is needed
Feature: 3) IC users roles

#  Scenario: Request an IC role
#    Given TestName "users requests an IC role" the landing page is loaded
#    When "128" users log into the app and request an IC role "with" MFA
#
#
#  Scenario: Remove the IC user role
#    Given TestName "remove the IC role" the landing page is loaded
#    When remove the IC "Innovation Center Privileged User" role


  Scenario Outline: Multiple newly created IC users request a role
    Given TestName "Multiple role" the landing page is loaded
    When a user "<userRow>" logs into CMS Portal with "MFA"
    And selects "IC-Innovation Center" after clicking on "Add Application"
    And selects a Role for "<userRow>" and clicks on "Next"
    And justify it and clicks "Submit"
    Then confirmation message will appear
    Then logout
    Examples:
      | userRow |
#      | 1       |
#      | 2       |
#      | 3       |
#      | 4       |
#      | 5       |
#      | 6       |
#      | 7       |
#      | 8       |
#      | 9       |
#      | 10      |
#      | 11      |
#      | 12      |
#      | 13      |
#      | 14      |
#      | 15      |
#      | 16      |
#      | 17      |
#      | 18      |
#      | 19      |
#      | 20      |
#      | 21      |
#      | 22      |
#      | 23      |
#      | 24      |
#      | 25      |
#      | 26      |
#      | 27      |
#      | 28      |
#      | 29      |
#      | 30      |

