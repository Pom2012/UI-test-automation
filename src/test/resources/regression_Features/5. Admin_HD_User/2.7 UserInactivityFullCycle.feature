@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can access "User inactivity" page

  Background:
    Given TestName "IC Admin -> User Inactivity Page" the landing page is loaded
    When a user "1" logs in with "non-MFA status"

  Scenario Outline: 1) Verify IC Admin user can set up UI templates
    When clicks on "Administration Console" link in Innovation Center
    And types in search box, sees "<AppName>" and clicks on envelop icon
    Then  "<role>" is on "<AppName>"'s "Email Template Builder" tab page
    When clicks on "User Inactivity" tab
    Then "User Inactivity Management" page and the "<AppName>" are displayed
    When selects "Monitor User Inactivity" check box
    And enters text values for the below text fields and clicks on "Continue" button:
      | Flag user as inactive after: | 4 |
      | Number of emails to send:    | 3 |
      | Send an email every:         | 1 |
    Then verified the page does not content any warning email templates
    When clicks on "Add New Template" button and "Email Template" dialog is popped up
    Then enters values for the below fields, selects "Warning State" and "Save" it
      | Template Name  | Warning                                               | 1-3 |
      | Subject Line   | Notice: AMR Verification Testing Portal Inactive User |     |
      | Message        | enter a message with a macros                         |     |
      | Warning State: | 1-3                                                   |     |
    Then enters values for the below fields, selects "Rejection" and "Save" it
      | Template Name  | Rejection                                                           | 4 |
      | Subject Line   | Final Notice: AMR Verification Testing Portal Inactive User Removed |   |
      | Message        | enter a message with a macros                                       |   |
      | Warning State: | 4                                                                   |   |
    And clicks on "Save" button
    Then "Success Your Changes Have Been Saved!" is displayed and user clicks on "Close"
    Then logout

    Examples:
      | role                            | AppName                  |
      | Innovation Center Administrator | AMR Verification Testing |


  Scenario: 2) Verify the User Inactivity reports generate the data
    When clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | User Inactivity | AMR Verification Testing |
    Then verifies the user inactivity template is displayed in "User Inactivity Status Summary" tab
    Then logout