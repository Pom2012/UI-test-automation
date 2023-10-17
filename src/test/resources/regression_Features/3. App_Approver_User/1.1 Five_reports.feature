@regression
@IC_Approver
Feature: IC Application Approver can access Last Accessed, User Access, App Usage, Custom Attribute & App Summary reports

  Background:
    Given TestName "An IC Admin can access reports" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center

  Scenario Outline: IC Application Approver can access Reports module
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Last Accessed       |
      | User Access         |
      | Application Usage   |
      | Custom Attribute    |
      | Application Summary |
    Then logout
    Examples:
      | application          |
      | Old New Year Display |