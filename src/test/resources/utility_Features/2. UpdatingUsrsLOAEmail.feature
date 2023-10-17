#//REVIEW: This version is likely outdated
Feature: 2) Stepping up multiple IC users' LOA and email

  Scenario Outline: Stepping up multiple IC Users' LOA

    Given TestName "Stepping up multiple IC Users' LOA" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Help Desk / Manage Users" tile and views the page
    Then performs updating LOA for "<userNumbers>" users
    And clicks the "CMS Enterprise Portal" button by title
#
    Then logout
    Examples:
      | userNumbers |
      | 2        |

  Scenario Outline: Stepping up multiple IC Users' email
    Given TestName "Stepping up multiple IC Users' LOA" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Help Desk / Manage Users" tile and views the page
    Then performs updating "Email" for "<userNumbers>" users
    And clicks the "CMS Enterprise Portal" button by title
    Then logout
    Examples:
      | userNumbers |
      | 30        |