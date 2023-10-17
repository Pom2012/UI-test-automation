@smoke
@cmmi-am
Feature: IC Admin user can access "Role mapping", "User inactivity" and "User verification"
  As an IC Administrator role user, I want to have access to and use
  IC "Role mapping", "User inactivity" and "User verification" modules

  Background:
    Given TestName "Manage Applications module features" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center

  Scenario: 1) Verify Applications Role Mapping page and features are displaying
    Then user is on "Manage Applications" page
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
    Then logout

  Scenario Outline: 2) Verify User Inactivity & Email Template Builder page
    When types in search box, sees "<AppName>" and clicks on envelop icon
    Then  "<role>" is on "<AppName>"'s "Email Template Builder" tab page
    When clicks on "User Inactivity" tab
    Then "User Inactivity Management" page and the "<AppName>" are displayed
    And user see the below features:
      | Text fields        | Flag user as inactive after: | Number of emails to send: | Send an email every: |
      | Buttons            | Clear                        | Cancel                    | Continue             |
      | User InaAct points | Inactivity Rules             | Email Templates           | Done                 |
      | Select boxes       | Monitor User Inactivity      | Don't send when late      |                      |
    Then logout

    Examples:
      | role                            | AppName   |
      | Innovation Center Administrator | Argonauts |

  Scenario Outline: 3) Verify User Verification Management page
    When types "<text4Search>" in search text box, the system displays the "<AppName>"
    And clicks on "<AppName>" shield icon
    And "User Verification Management" and the "<AppName>" are displayed
    And User sees "Search Schedule" search box, "Add New Quarter" button
    When clicks on "Add New Quarter" button
    And user see the below features in "Associated Users:" page:
      | Buttons     | Back                  | cancel         | confirm |
      | Selections  | Select Year           | Select Quarter |         |
      | Date picker | Start Date            | End Date       |         |
      | Subtitle    | Add New Quarter       |                |         |
      | Text field  | My Practice Selection |                |         |
      | Check box   | Default               |                |         |
    Then logout
    Examples:
      | text4Search | AppName   |
      | Argonauts   | Argonauts |