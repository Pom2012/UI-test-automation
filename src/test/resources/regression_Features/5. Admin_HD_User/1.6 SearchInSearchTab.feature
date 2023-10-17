@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can Search by each field on Search Users screen

  Scenario: Verify IC Admin + IC HD user can Search by each field on Search Users screen
    Given TestName "IC Admin + IC HD user can search by each field on SU screen" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    Then searches the given information in below fields in "Search Users":
      | Typed in      | User ID          | -    |
      | Typed in      | Email Address    | -    |
      | Typed in      | First Name       | -    |
      | Typed in      | Last Name        | -    |
      | Selected from | Application Name | -    |
      | Selected from | Application Name | Role |
    Then logout