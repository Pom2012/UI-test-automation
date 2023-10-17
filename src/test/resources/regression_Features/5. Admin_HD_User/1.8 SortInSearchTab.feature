@PV
@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can sort User details in Search users screen

  Scenario: Verify IC Admin + IC HD user can sort User details in Search users screen
    Given TestName "IC Admin + HD user can sort User details in Search users screen" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    And clicks on user ID hyperlink after the search result displayed a web table
    Then sorts the below columns and orders it in "ascending" sequence:
      | Application Name      |
    And sorts the below columns and orders it in "descending" sequence:
      | Application Name      |
    Then logout