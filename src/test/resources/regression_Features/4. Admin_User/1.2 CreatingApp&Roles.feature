#Feature: 4.1.2 IC Admin user can create a new application and roles
##DEV0
##  Explanation: should run once a month
##  check and make ready the excel file, that stored the previous run
##  src\test\resources\externFiles\newApp.xsl
#  Scenario Outline: Verify IC Administrator user can create an application and roles
#    Given TestName "IC Administrator role user can create an app & roles" the landing page is loaded
#    When a user "<row_index>" logs in with "non-MFA status"
#    And clicks on "Administration Console" link in Innovation Center
#    When clicks on "Add New Application" button, then see "Add Innovation Center Application Information" page
#    And provide the information for text fields and select dropdowns:
#      | Application Name-Required            | ABC_UVNPU                                        |
#      | Application Display Name-Required    | ABC_UVNPU                                        |
#      | Application Acronym-Required         | ABC_UVNPU                                        |
#      | Application Display Acronym-Required | ABC_UVNPU                                        |
#      | Application Version                  | 1                                                |
#      | Application Start Date(MM/DD/YYYY)   | Today                                            |
#      | Application Description-Required     | This app. created for Regression Testing purpose |
#      | Application URL-Required             | /wps/portal/unauthportal/                        |
#      | User ID-Required                     | user ID                                          |
#    And clicks on "Verify" button
#    And selects from the below select dropdowns:
#      | Application Status                                                              | Enabled  |
#      | Multiple Application Role Indicator (Allow both parent and child role requests) | Enabled  |
#      | Unauthorized Portal Application Display                                         | Enabled  |
#      | Level of Assurance*                                                             | LOA2     |
#      | Combine EUA Info*                                                               | Disabled |
#      | Allow Mapped Role*                                                              | Disabled |
#      | Fetch Label Attribute*                                                          | Disabled |
#      | Evaluation*                                                                     | Disabled |
#      | Filter Null Attribute*                                                          | Disabled |
#    And clicks on "Add Application"
#
#    Then user is on "User roles" page and see data table
#    When the user clicks on "Add New Role" button
#    Then views "Add Application Role Information" page
#    And provide the role information for text fields and select from dropdowns:
#      | Role Name - Required         | Role                                        |
#      | Role Display Name - Required | Role                                        |
#      | Role Approver                | ABC_UVNPU Business Owner                    |
#      | Role Acronym - Required      | roleShortName                               |
#      | Role Hierarchy               | 1                                           |
#      | Role Description-Required    | This role created for Regression Test suite |
#    Then logout
#    Examples:
#      | row_index | role                            |
#      | 7         | Innovation Center Administrator |