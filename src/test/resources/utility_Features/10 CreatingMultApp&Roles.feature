#//REVIEW: This version is likely outdated
#@ICAdmin
#Feature: creating a multiple Applications and Roles
#
#  Scenario Outline: creating a multiple Applications and Roles
#    Given TestName "Creating a multiple Applications and Roles" the landing page is loaded
#    And clicks on "Administration Console" link in Innovation Center
#    When clicks on "Add New Application" button, then see "Add Innovation Center Application Information" page
#    And provide the information for the below text fields and select dropdowns:
#      | Application Name-Required            | Role Name - Required             |
#      | Application Display Name-Required    | Role Display Name - Required     |
#      | Application Acronym-Required         | Role Approver                    |
#      | Application Display Acronym-Required | Role Acronym - Required          |
#      | Application Version                  | Role Hierarchy                   |
#      | Application Start Date(MM/DD/YYYY)   | Role Description-Required        |
#      | Application Description-Required     | Accept Multiple Attribute Values |
#      | Application URL-Required             | Is Evaluator                     |
#      | User ID-Required                     | Status                           |
#    And clicks on "Verify", selecting "Enabled" allow mapped role and clicks on "Add Application"
#    Then user is on "User roles" page and see data table
#    When the user clicks on "Add New Role" button
#    Then views "Add Application Role Information" page
#    And provide the role information for text fields and select from dropdowns:
#      | Role Name - Required         | Role                                        |
#      | Role Display Name - Required | Role                                        |
#      | Role Approver                | Regression Business Owner                   |
#      | Role Acronym - Required      | roleShortName                               |
#      | Role Hierarchy               | 1                                           |
#      | Role Description-Required    | This role created for Regression Test suite |
#    And clicks the "CMS Enterprise Portal" button by title
#    Then logout
#    Examples:
#      | row_index | role                            |
#      | 7         | Innovation Center Administrator |