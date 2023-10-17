@regression
@ICAdmin
#//TODO: Refactor for better flexibility for env and test data management
Feature: IC Admin user can create, update, disable and terminate a simple lists

  Background:
    Given TestName "IC Administrator role user can create a simple list" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center

#    to avoid creating a additional data for backend, run this scenario once
#  Scenario Outline: 1) Verify IC Administrator user can create a simple list
#    Then scrolls into view "<module>" and selects "Simple" list and submits it
#    Then "Add Application Role Custom Attribute List" page is displayed
#    When provides a "Simple" list name, "Test automation for Simple list" and adds "5" value for List entries text fields
#    And clicks on Add Attribute list button
#    Then users is on "List Management of Application Custom Attributes" pages
#    And verifies the "Simple" list is stored
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
##
#    Examples:
#      | module                                          |
#      | List Management of Application Custom Attributes |


#    Scenario Outline: 1) Verify IC Administrator user can create a simple list
#    Then scrolls into view "<module>" and selects "From CSV (simple)" list and submits it
#    Then "Add Application Role Custom Attribute List" page is displayed
#    When provides a "From CSV (simple)" list name, "Test automation for Simple CSV list" and adds "5" value for List entries text fields
#    And clicks on Add Attribute list button
#    Then users is on "List Management of Application Custom Attributes" pages
#    And verifies the "Simple" list is stored
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#
#    Examples:
#      | module                                          |
#      | List Management of Application Custom Attributes |


  Scenario Outline: 2) Verify IC Administrator user can update, disable and terminate a simple list
    And scrolls into view "<module>", searches the "Simple", clicks on update icon
    Then "Update List Details" page, list name and entries are displayed
    And clicks on update icon and sequentially performs and submits the manipulation for "simple" list:
      | Enabled    | Updating the simple list    |
      | Disabled   | Disabling the simple list   |
      | Terminated | Terminating the simple list |
    Then logout
#
    Examples:
      | module                                           |
      | List Management of Application Custom Attributes |



#  Scenario: 4) Verify submit "From CSV (simple)" list user interface elements
#    When selects "From CSV (simple)" from dropdown and clicks on "Submit" button
#    Then "Add Application Role Custom Attribute List" page with the below elements is displayed:
#      | List Name        |
#      | List Description |
#      | List Entries     |
#      | Paste CSV data   |
#    When provides a "SimpleCSV" list name, "Test automation for Simple list" and adds "5" value for List entries text fields