@regression
@ICAdmin
Feature: IC Admin user can access "User inactivity" page

  Scenario Outline: Verify the User Inactivity page in IC Admin user account is displaying
    Given TestName "IC Admin -> User Inactivity Page" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
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
      | row_index | role                            | AppName   |
      | 1         | Innovation Center Administrator | Argonauts |