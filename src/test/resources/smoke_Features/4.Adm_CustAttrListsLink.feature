@smoke
@cmmi-lm
Feature: IC Admin user can access List Management page
  As an IC Administrator role user, I want to see and use List Management page functionalities

  Background:
    Given TestName "Custom Attribute Lists Linkage" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center

  Scenario Outline: Verify IC Admin can access Custom Attribute Lists Linkage
    Then navigates to the "<portlet>" page, and sees the table data and "<feature>" button
    And user clicks on "<feature>" button and see the below:
      | Feature's subpage    | CMMI Application Role Custom Attribute |
      | Feature's title      | Custom Attribute Lists Linkage         |
      | Feature's text field | Search                                 |
      | Feature's button     | Custom Attribute Lists Linkage         |
      | Feature's table data | Custom Attribute List Linkage          |
      | Feature's button     | Back                                   |
    And clicks on "Custom Attribute Lists Linkage" button and see the below:
      | Feature's title | New Custom Attribute Lists Linkage |
      | Select dropdown | Target List                        |
      | Select dropdown | Source List                        |
      | Select checkbox | Disable                            |
      | Button          | Submit                             |
      | Button          | Clear                              |
      | Button          | Back                               |
    And clicks on "edit" button and see the below:
      | Feature's title | Update Custom Attribute Lists Linkage |
      | Select dropdown | Target List                           |
      | Select dropdown | Source List                           |
      | Select checkbox | Disable                               |
      | Button          | Submit                                |
      | Button          | Clear                                 |
      | Button          | Back                                  |
    Then logout

    Examples:
      | feature                       | portlet                                          |
      | Custom Attribute List Linkage | List Management of Application Custom Attributes |