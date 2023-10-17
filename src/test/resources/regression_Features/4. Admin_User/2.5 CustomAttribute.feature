@regression
@ICAdmin
Feature: Admin user can create a Custom Attribute

  Background:
    Given TestName "Administrator role user can create, manipulate, and delete a Custom Attribute" the landing page is loaded
    When a user "7" logs in with "non-MFA status"
    And clicks on "Administration Console" link
    Then searches "Argonauts" application in search bar, clicks on roles icon and see "User Roles" page
    When clicks on "Add Custom Attribute" icon for "Participant 3" user
    Then user is on "Custom Attribute - Participant 3 role for Argonauts application" page

  Scenario: Verify IC Admin user can create Custom Attributes with Attribute Function Types
    When selects "Add New Attribute", sees "Add Custom Attribute Information", and creates default custom attributes of type:
      | Routing                                     | Drop Down       |
      | Simple (Allow Multiple Requests + Required) | Restricted Text |
      | Simple (Required)                           | Text Box        |
      | Simple                                      | Text Box        |
    Then logout

  Scenario: Verify IC Admin user can view the Attribute list is popped up
    When clicks on view icon for "Routing" "Attribute Function Type" from "View List Items"
    Then a windows of an attribute with the list values is popped up
    Then logout

  Scenario: Verify the Attribute Move Arrow Down and Up functionality
    When clicks on the arrow "down" icon for "Routing" custom attribute of the table
    Then "Routing" custom attribute changes its position in the table and moves "down"
    When clicks on the arrow "up" icon for "Routing" custom attribute of the table
    Then "Routing" custom attribute changes its position in the table and moves "up"
    Then logout

  Scenario: Verify IC Admin user can update the simple Custom Attribute
    Then clicks on update icon from "Update" column and update the custom attributes
    Then logout

  Scenario: Verify IC Admin user can delete the simple Custom Attribute
    Then clicks on delete icon from "Remove Attribute" column and deletes the custom attributes
    Then logout

  Scenario: Verify IC Admin user can create Custom Attributes with Nested Attribute Function Type
    When selects "Add New Attribute", sees "Add Custom Attribute Information", and creates default custom attributes of type:
      | Nested | Drop Down       |
      | Nested | Restricted Text |
      | Nested | Filtered Text   |
      | Nested | Reference       |
    Then logout

  Scenario: Verify IC Admin user can see checked and unchecked Dynamic Custom Attribute
    Then selects "Add New Attribute" and views unchecked "Enable Dynamic Custom Attribute"
    When selects "Enable Dynamic Custom Attribute" checkbox
    Then the below select dropdowns is appeared:
      | Parent Custom Attribute | Dynamic List Name |
    Then logout

  Scenario: Verify IC Admin user can delete the nested Custom Attribute
    Then clicks on delete icon from "Remove Attribute" column and deletes the custom attributes
    Then logout
