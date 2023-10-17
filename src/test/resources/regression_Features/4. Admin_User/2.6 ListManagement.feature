@regression
@ICAdmin
Feature: IC Admin user can see and manipulate List Management module elements

  Background:
    Given TestName "IC Administrator role user can see a simple/nested list" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center

  Scenario Outline: Verify all IC List Management pages' features are displayed
    Then "<module>" page with the below features is displayed:
      | select dropdown | Add New List                  |
      | select dropdown | Show  X entries               |
      | search engine   | Search                        |
      | button          | Submit                        |
      | button          | Custom Attribute List Linkage |
      | button          | Update                        |
      | pagination      | First                         |
      | pagination      | Previous                      |
      | pagination      | Next                          |
      | pagination      | Last                          |
      | column          | List Name                     |
      | column          | List Description              |
      | column          | List Type                     |
      | column          | Update                        |
    Then logout
    Examples:
      | module                                           |
      | List Management of Application Custom Attributes |

  Scenario Outline: Verify "Add New List" select dropdown and "Submit" button in the "Module"
    And views "Add New List" select dropdown and inactive "Submit" button in the "<module>"
    When views the below options when clicks on "Add New List":
      | Simple            |
      | Nested            |
      | From CSV (simple) |
      | From CSV (nested) |
    When the "Submit" button activates when the user selects the below options:
      | Simple            |
      | Nested            |
      | From CSV (simple) |
      | From CSV (nested) |
    Then logout
    Examples:
      | module                                           |
      | List Management of Application Custom Attributes |

  Scenario: Verify "Simple" list user interface elements
    When selects "Simple" from dropdown and clicks on "Submit" button
    Then "Add Application Role Custom Attribute List" page with the below elements is displayed:
      | List Name        |
      | List Description |
      | List Entries     |
      | Entry Identifier |
      | List Entry       |
      | Add New Entry    |
    Then logout

  Scenario: Verify "Nested" list user interface elements
    When selects "Nested" from dropdown and clicks on "Submit" button
    Then "Add Application Role Custom Attribute List" page with the below elements is displayed:
      | List Name             |
      | Parent Attribute List |
      | List Description      |
      | List Entries          |
      | Entry Identifier      |
      | List Entry            |
      | Parent Identifier     |
      | Parent Entry          |
      | Add New Entry         |
    Then logout

  Scenario: Verify "Simple CSV" list user interface elements
    When selects "From CSV (simple)" from dropdown and clicks on "Submit" button
    Then "Add Application Role Custom Attribute List" page with the below elements is displayed:
      | List Name        |
      | List Description |
      | List Entries     |
      | Paste CSV data   |
    Then logout

  Scenario: Verify "Nested CSV" list user interface elements
    When selects "From CSV (nested)" from dropdown and clicks on "Submit" button
    Then "Add Application Role Custom Attribute List" page with the below elements is displayed:
      | List Name             |
      | Parent Attribute List |
      | List Description      |
      | List Entries          |
      | Paste CSV data        |
    Then logout

  Scenario: Verify errors are displayed when user try to create empty Simple list name
    When selects "Simple" from dropdown and clicks on "Submit" button
    And clicks on "Add Attribute List" button
    Then "Missing value for required field List Name. Please enter the missing information to proceed further." is displayed

  Scenario: Verify errors are displayed when user try to create empty Nested list name
    When selects "Nested" from dropdown and clicks on "Submit" button
    And clicks on "Add Attribute List" button
    Then the below text is displayed:
      | Missing value for required field List Name. Please enter the missing information to proceed further. |
      | The option group parent id is required. Please select one.                                           |
    Then logout

