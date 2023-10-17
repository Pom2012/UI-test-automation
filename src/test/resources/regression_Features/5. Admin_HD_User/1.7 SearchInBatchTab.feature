@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can search on Batch Operations tab page

  Background:
    Given TestName "IC Admin + IC HD user can Search by each field on Batch Operations screen" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Batch Operations" and see the page

  Scenario: Verify search functions by text each field on Batch Operations tab page
    Then searches the given information in below fields in "Batch Operations":
      | Request ID       |
      | User ID          |
      | Email Address    |
      | First Name       |
      | Last Name        |
      | Application Name |
      | Role             |
    Then logout

  Scenario: Verify search function by custom attribute filter on Batch Operations tab page
    Then searches a certain custom attribute in "Batch Operations":
      | Application Name |
      | Role             |
      | Model ID         |
      | Participant ID   |
    Then logout

  Scenario Outline: Verify downloaded file matches with web table data in Batch Operations tab page
    When selects an application "<from>" dropdown and clicks on "Search" button in "Batch Operations"
    Then "Application Role Request Information" web table with the below columns is displayed:
      | Request ID            |
      | User ID	Name          |
      | Application Name      |
      | Application Role Name |
      | Custom Attribute      |
      | Requested Date        |
      | Status                |
      | Select                |
    When clicks on "DownLoad" button
    And The total requests in the ALL status is displayed in "Processed records"
    Then verified the downloaded "HelpDeskSearchResult.csv" file is matched with web table data
    Then logout
    Examples:
      | from             |
      | Application Name |