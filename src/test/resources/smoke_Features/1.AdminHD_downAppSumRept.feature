@smoke
@ReportCenter
@cmmi-rp
@amHealthCheck
Feature: IC Admin user can see and download Application Summary Report
  As an IC Administrator user, I want to review and download Application Summary Report

  Scenario Outline: Verify downloaded User Detailed Reports of Application Summary Report
    Given TestName "User Role Details for App Rep" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Application Summary |
    Then logout
    Examples:
      | role                            | application                                       |
      | Innovation Center Administrator | Innovation Payment Contractor Portal (IPC Portal) |