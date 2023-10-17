@regression
@ICAdmin_HD
@403
Feature: IC Admin + IC HD user can see a Request History is popped up on Batch Operations Screen

  Scenario Outline: Verify IC Admin + IC HD user can see Request History PopUp on Batch Operations Screen
    Given TestName "IC Admin + IC HD user can see Request History PopUp on Batch Operations Screen" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Batch Operations" and see the page
    And selects "<application>" and clicks search button
    Then see the "Application Role Request Information" table
    When clicks on the first request ID of the table
    Then the "History of Request ID" is popped up
    Then logout
    Examples:
      | row_index | application |
      | 1         | Argonauts   |