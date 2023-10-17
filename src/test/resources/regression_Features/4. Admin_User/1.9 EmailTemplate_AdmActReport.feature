@regression
@ICAdmin
Feature: IC Admin creates, updates and deletes a new email template and changes generated in Admin Activity Report

  Background:
    Given TestName "Verify Admin Activity Report captures the email template changes" the landing page is loaded
    When a user "1" logs in with "non-MFA status"

  Scenario Outline: Verify if IC Admin user can create an email template in Admin Console
    And clicks on "Administration Console" link in Innovation Center
    When types in searchbox "Perseverance", the system displays "Perseverance"
    And clicks on envelop icon for entering "Perseverance" email template builder
    And views "Add New Template" after clicking on "Add New Template"
    When the user inputs "<Template1>", "<SubjectLine>", "<From>", "<BodyText>", "<Enable/Disable>", "<HTML>"
    And clicks on "Add New Template" and views "Template"
    Then logout
    Examples:
      | Template1   | SubjectLine             | From            | BodyText                | Enable/Disable | HTML |
      | NewTemplate | Creating a new template | email@email.com | Creating a new template | Enable         | no   |

  Scenario Outline: Verify if IC Admin user can update an email template
    And clicks on "Administration Console" link in Innovation Center
    When types in searchbox "Perseverance", the system displays "Perseverance"
    And clicks on envelop icon for entering "Perseverance" email template builder
    And views "Update Template" after clicking on "edit"
    When the user inputs "<Template1>", "<SubjectLine>", "<From>", "<BodyText>", "<Enable/Disable>", "<HTML>"
    And clicks on "Update Template" and views "Template"
    Then logout
    Examples:
      | Template1   | SubjectLine               | From            | BodyText                | Enable/Disable | HTML |
      | NewTemplate | Updating the new template | email@email.com | Updating a new template | Enable         | no   |

  Scenario Outline: Verify if IC Admin user can disable the email template
    And clicks on "Administration Console" link in Innovation Center
    When types in searchbox "Perseverance", the system displays "Perseverance"
    And clicks on envelop icon for entering "Perseverance" email template builder
    And views "Update Template" after clicking on "edit"
    When the user inputs "<Template1>", "<SubjectLine>", "<From>", "<BodyText>", "<Enable/Disable>", "<HTML>"
    And clicks on "<clickBtn>" and views "Template"
    Then logout
    Examples:
      | Template1   | SubjectLine               | From            | BodyText                | Enable/Disable | HTML | clickBtn        |
      | NewTemplate | Deleting the new template | email@email.com | Deleting a new template | Disable        | no   | Update Template |

  Scenario: Verify the changes in email template are captured in Administrator Activity Report
    And clicks on "Report Center" link in Innovation Center
    When clicks on "Administrator Activity" report button
    And provides the below values and filter the results:
      | Start Date       | Today        |
      | End Date         | Today        |
      | Application Name | Perseverance |
    Then sees the changes in below columns:
      | Date & Time (EST) |
      | Old Value         |
      | New Value         |
    Then logout