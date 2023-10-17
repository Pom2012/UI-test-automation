@regression
@ICAdmin_HD
Feature: IC Admin + IC HD user can Approve and Reject a Request in User Details / Search Users page

  Scenario Outline: Verify IC Admin + IC HD user can Approve and Reject a Request in User Details page
    Given TestName "IC Admin + HD user can Approve and Reject a Request in User Details page" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Search Users" and see the page
    When typed a "user" ID in search text box
    Then clicks on user ID hyperlink after the search result displayed a web table
    And approves a rejected request selected from rejected requests
    Then rejects the newly approved request from approved requests
    Then logout

    Examples:
      | row_index |
      | 1         |