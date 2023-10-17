@regression
@Admin

Feature: An Admin can access 11 User Detailed and Application Reports
  Background:
    Given TestName "Admin can see the User Detailed Reports" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "Report Center" link

  Scenario Outline: Verify an IC Admin can see 6 User Detailed Reports
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Administrator Activity |
      | User Access            |
      | Privileged User        |
      | Application Monitoring |
      | Last Accessed          |
      | List Activity          |
    Then logout
    Examples:
      | role                            | application              |
      | Administrator | AMR Verification Testing |
#
  Scenario Outline: Verify an Admin can see 5 Application Reports
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Application Summary |
      | Application Usage   |
      | Custom Attribute    |
      | User Inactivity     |
      | User Verification   |
    Then logout

    Examples:
      | role                            | application |
      | Administrator | PCF_Dummy   |