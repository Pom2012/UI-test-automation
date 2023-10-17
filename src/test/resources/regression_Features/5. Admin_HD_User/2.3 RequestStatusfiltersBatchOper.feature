@regression
@ICAdmin_HD
@4031
Feature: IC Admin + IC HD user can see Request Status filters in Batch Operations tab

  Scenario Outline: Verify IC Admin + IC HD user can see Request Status filters on Batch Operations tab
    Given TestName "IC Admin + IC HD user can see Request Status filters on Batch Operations tab" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Batch Operations" and see the page
    And selects "<application>" and clicks search button
    Then see the "Application Role Request Information" table
    And see the Request Status dropdown with the below filters:
      | APPROVED |
      | REJECTED |
      | PENDING  |
      | All      |
    Then logout

    Examples:
      | row_index | application |
      | 1         | Argonauts   |