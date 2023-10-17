@regression
@ICAdmin
#//TODO: Refactor for better flexibility for env and test data management
Feature: IC Admin user can create, update, disable and terminate a nested lists

  Background:
    Given TestName "IC Administrator role user can create, update, disable and terminate a nested list" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center
#    to avoid creating a lot of data we run this scenario once
#  Scenario Outline: 1) Verify IC Administrator user can create a nested list
#    And scrolls into view "<Portlet>" and selects "Nested" list and submits it
#    And "Add Application Role Custom Attribute List" page is displayed
#    Then provides "Nested" name, selects a list from "Parent Attribute List" and adds "4" New Entries
#    And clicks on Add Attribute list button
#    Then users is on "<Portlet>" pages
#    And verifies the "Nested" list is stored
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#
#    Examples:
#      | Portlet                                          |
#      | List Management of Application Custom Attributes |

  Scenario Outline: Verify IC Administrator user can update, disable and terminate a nested list
    And scrolls into view "<Portlet>", searches the "Nested", clicks on update icon
    Then "Update List Details" page, list name and entries are displayed
    And clicks on update icon and sequentially performs and submits the manipulation for "nested" list:
      | Enabled    | Updating the nested list    |
      | Disabled   | Disabling the nested list   |
      | Terminated | Terminating the nested list |
    Then logout

    Examples:
      | Portlet                                          |
      | List Management of Application Custom Attributes |

#  Scenario Outline: Verify IC Administrator user can manually update a nested list
#    And scrolls into view "<Portlet>", searches the "Simple", clicks on update icon
#    Then "Update List Details" page, list name and entries are displayed
#    When selects "Add New Entry" from "Use CSV Data" and clicks on the next arrow icon
#
#    Then logout
#
#    Examples:
#      | Portlet                                          |
#      | List Management of Application Custom Attributes |