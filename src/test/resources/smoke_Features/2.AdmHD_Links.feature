@smoke
Feature: IC Admin+HD user can access Admin Console, Report, and Support Centers
  As an IC Admin and HD role user, I want to see and use Admin Console,
  Report Center, Support Center functionality

  Background:
    Given TestName "Admin+HD user" the landing page is loaded
    When a user "1" logs in with "non-MFA"

  @cmmi-am
  Scenario: Verify Admin Console for IC Admin user
    And clicks on "Administration Console" link in Innovation Center
    Then user is on "Manage Applications" page
    And see the below columns name:
      | Application Id           |
      | Application Display Name |
      | URL                      |
      | Status                   |
      | Update                   |
      | Roles                    |
      | Email                    |
      | User Review              |
    Then logout

  @cmmi-rp
  Scenario Outline: Verify Report Center for IC Admin user
    And clicks on "Report Center" link in Innovation Center
    When "<role>" clicks on and sees the below reports for an "<application>":
      | Administrator Activity |
      | List Activity          |
      | Application Monitoring |
      | Last Accessed          |
      | Privileged User        |
      | User Access            |
      | Application Usage      |
      | Custom Attribute       |
      | User Inactivity        |
      | User Verification      |
    Then logout
    Examples:
      | role                            | application          |
      | Innovation Center Administrator | Old New Year Display |

  @cmmi-hd
  Scenario Outline: Verify Support Center for IC Admin + HD user (Angular 2)
    And clicks on "Support Center" link in Innovation Center
    Then "<role>" sees the below tabs in "Support Center" module:
      | Search Users     |
      | Assign Role      |
      | User Profile     |
      | Warning State    |
      | Email Sender     |
      | Email Log        |
      | Batch Operations |
    Then logout
    Examples:
      | role                                                              |
      | Innovation Center Administrator + Innovation Center Helpdesk User |
