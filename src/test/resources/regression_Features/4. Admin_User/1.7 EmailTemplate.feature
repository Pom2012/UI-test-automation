@regression
@ICAdmin
Feature: IC Admin user can see an email template features

  Scenario Outline: Verify IC Admin user can see email template builder feature in Admin console
    Given TestName "IC Admin user can send an email to an IC user" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
    When types in searchbox "<application ID>", the system displays "<application display name>"
    And clicks on envelop icon for entering "<application display name>" email template builder
    Then "Email Template Builder" label, "<application ID>" name, "Search" box, "<feature>" and the table is displayed
    And views "<feature>" after clicking on "<feature>"
    And value fields, "<feature>", "Clear" and "Cancel" buttons are displayed
    Then logout

    Examples:
      | row_index | application ID | application display name | feature          |
      | 1         | Perseverance   | Perseverance             | Add New Template |