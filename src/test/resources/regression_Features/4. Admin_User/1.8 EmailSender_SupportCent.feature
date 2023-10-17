@regression
@ICAdmin
Feature: IC Admin user can create an email template and send an email to a user from Support Center

  Background:
    Given TestName "Admin user can can create an email template" the landing page is loaded
    When a user "1" logs in with "non-MFA status"

  Scenario Outline: Verify if an Admin user can create an email template in Admin Console
    And clicks on "Administration Console" link
    When types in searchbox "Perseverance", the system displays "Perseverance"
    And clicks on envelop icon for entering "Perseverance" email template builder
    And views "Add New Template" after clicking on "Add New Template"
    When the user inputs "<Template1>", "<SubjectLine>", "<From>", "<BodyNumber>", "<Enable/Disable>", "<HTML>"
    And clicks on "<addFeature>" and views "Template"
    Then logout
    Examples:
      | Template1 | SubjectLine | From            | BodyNumber | Enable/Disable | HTML | addFeature       |
      | Template  | Template    | email@email.com | Hello      | Enable         | no   | Add New Template |

  Scenario: Verify if Admin user can send an email to an IC user from Support Center
    And clicks on "Support Center" link
    And user clicks on "Email Sender" tab and fills out the json body field and "send email"
    Then user views "200" status code and "Successful post message" message in the json body
    Then logout

  Scenario Outline: Verify if IC Admin user can disable the email template in Admin Console
    And clicks on "Administration Console" link
    When types in searchbox "<AppId>", the system displays "<AppFullName>"
    And clicks on envelop icon for entering "<AppName>" email template builder
    And select the email template for "<AppId>" and clicks on "edit" button
    Then views "Update Template"
    When selects "Disable" from "Enable/Disable Template" and clicks on "Update Template" button
    Then user navigates to "Email Template Builder" page
    Then logout

    Examples:
      | AppId        | AppFullName  |
      | Perseverance | Perseverance |