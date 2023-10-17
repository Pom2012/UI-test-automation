@regression
@ICAdmin_HD
@403
Feature: IC Admin + IC HD user can assign a model role with or without attribute to an IC User and reject it

  Background:
    Given TestName "IC Admin + IC HD role user can assign a model role without attr" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Assign Role" and see the page

  Scenario Outline: Verify if an IC HD Admin role user can assign a model role without attributes to an IC User
    When sets the below information:
      | User ID          | 17                                                                         |
      | Application Name | <application>                                                              |
      | Role             | <modelRole>                                                                |
      | Justification    | Test IC Admin + IC HD Support Center Assign Role without Custom Attributes |
    And clicks Assign, then receives a message for "<application>" with the Request ID
    When enter the "Request ID" and "Search" it after clicking on "Batch Operations"
    Then a table with the Request ID, "<application>", "<modelRole>" is displayed
    When selects check in checkbox and clicks on "Reject Request(s)" button
    Then provide justification and "Submit" the alert with the "Request ID"
    Then logout
    Examples:
      | application | modelRole   |
      | Argonauts   | Participant |

  Scenario Outline: Verify if an IC HD Admin role user can assign a model role with attributes to an IC User
    When sets the below information:
      | User ID          | 17                                                                      |
      | Application Name | <application>                                                           |
      | Role             | <modelRole>                                                             |
      | Simple attribute | Maryland                                                                |
      | Justification    | Test IC Admin + IC HD Support Center Assign Role with Custom Attributes |
    And clicks Assign, then receives a message for "<application>" with the Request ID
    When enter the "Request ID" and "Search" it after clicking on "Batch Operations"
    Then a table with the Request ID, "<application>", "<modelRole>" is displayed
    When selects check in checkbox and clicks on "Reject Request(s)" button
    Then provide justification and "Submit" the alert with the "Request ID"
    Then logout
    Examples:
      | application | modelRole    |
      | Argonauts   | Model User 2 |

  Scenario: Verify the system automatically generates a user info in Assign Role tab
    Then see the below "input" fields are "disabled and unpopulated":
      | First Name    |
      | Last Name     |
      | Current Roles |
    When sets the below information:
      | User ID | 17 |
    Then see the below "input" fields are "enabled and populated":
      | First Name    |
      | Last Name     |
      | Current Roles |
    Then clicks "button" with "title" "Clear Assign Role"
    Then see the below "input" fields are "disabled and unpopulated":
      | First Name    |
      | Last Name     |
      | Current Roles |
    Then logout
