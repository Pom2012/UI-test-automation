@regression
@ICAdmin
Feature: IC Admin user can access "Role mapping" page and use the functionality

  Background:
    Given TestName "IC Admin Role Mapping Page" the landing page is loaded

  Scenario Outline: Verify the Role Mapping page and its features in IC Admin account
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
    Then clicks on "Role Mapping" button and see the below features:
      | page title | Applications Role Mapping |
      | web table  | Applications Role Mapping |
      | button     | Add New Role Mapping      |
      | button     | Map                       |
      | button     | edit                      |
      | button     | Back                      |
      | search bar | Search                    |
    When clicks on "Add New Role Mapping" button and see the below features:
      | page title | Add New Applications Role Mapping |
      | text field | Source Application Name           |
      | text field | Target Application Name           |
      | text field | Source Role Name                  |
      | text field | Target Role Name                  |
      | button     | Back                              |
      | button     | Clear                             |
      | button     | Submit                            |
      | text field | Disable                           |
      | text field | Show Mapped Widget                |
    When clicks on "Map" button and see the below features:
      | page title | Custom Attributes Mapping          |
      | web table  | Custom Attributes Mapping          |
      | button     | Add Custom Attributes Role Mapping |
      | button     | Back                               |
    When clicks on "Add Custom Attributes Role Mapping" button and see the below features:
      | page title            | Add New Custom Attributes Mapping           |
      | select dropdown       | Target Custom Attribute Mapping Information |
      | check box             | Literal Value                               |
      | dependable text field | Enter Value:                                |
      | check box             | Label                                       |
      | select dropdown       | Source Custom Attribute Name                |
      | button                | Back                                        |
      | button                | Clear                                       |
      | text field            | Cancel                                      |
      | button                | Submit                                      |
    Then logout
    Examples:
      | row_index |
      | 1         |

#//TODO: TC: This test needs major refactoring
#  Scenario Outline: 2) Verify IC Admin can mapped Application's roles
#    Given TestName "IC Admin can mapped Application's roles" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on "Administration Console" link in Innovation Center
#    And "<role>" sees "Admin page" after clicking "Administration Console"
#    When clicks on "Role Mapping" button and sees "Applications Role Mapping" page
#    And clicks on "Add New Role Mapping" button and see "Add New Applications Role Mapping" page
#    When provides the below data for each fields "Add New Applications Role Mapping" page:
#      | Select Application | Argonauts            |
#      | Select Target      | Old New Year         |
#      | Select Role        | Model User           |
#      | Select Role        | Old New Year User  3 |
#    Then user searches and sees the mapped "Argonauts" on "Applications Role Mapping" page after clicking on "Submit"
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#    Examples:
#      | row_index | role                            |
#      | 1         | Innovation Center Administrator |

  Scenario Outline: Verify IC Admin can disabled and enabled the mapped applications roles
    Given TestName "IC Admin can mapped Application's roles" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
    When clicks on "Role Mapping" button and sees "Applications Role Mapping" page
    Then searching by Target "Old New Year" and Source "Argonauts", click "edit"
    And selects "Disable" and submit the change on "Update Applications Role Mapping" page
    Then searching by Target "Old New Year" and Source "Argonauts", click "edit"
    And deselects "Disable" and submit the change on "Update Applications Role Mapping" page
    Then logout
    Examples:
      | row_index |
      | 1         |

  Scenario Outline: Verify if an IC HD Admin role user see the roles mapped in User profile
    Given TestName "IC HD and Admin role user can assign a model role with attr" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "User Profile" and see the page
    When provides the below data to each field below and clicks on "Send" button:
      | User ID          | a user    |
      | Application Name | Argonauts |
      | Version          | version 3 |
    Then the json body of the user is displayed
      | applicationName | Argonauts |
    Then logout
    Examples:
      | row_index |
      | 1         |