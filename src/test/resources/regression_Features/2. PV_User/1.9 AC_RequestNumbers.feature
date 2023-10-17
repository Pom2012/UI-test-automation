@regression
@PV
Feature: IC PV user can see the number of requests and pagination in Request/Confirm Access tabs

  Background:
    Given TestName "IC PV User can see empty portlet messages" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    Then user successfully logged in
    And clicks on "Application Console" link in Innovation Center

  Scenario: Verify the number of requests in All, Pending, Approved, Rejected tabs are correct in Request Access
    Then clicks on "Request Access" tab and see the numbers of requests in below subtabs:
      | All      | There are X requests          |
      | Rejected | There are X Rejected requests |
      | Approved | There are X Approved requests |
      | Pending  | There are X Pending requests  |
    Then logout

  Scenario: Verify the number of requests in All, Pending, Approved, Rejected tabs are correct in Confirm Access
    Then clicks on "Confirm Access" tab and see the numbers of requests in below subtabs:
      | All      | There are X requests          |
      | Rejected | There are X Rejected requests |
      | Approved | There are X Approved requests |
      | Pending  | There are X Pending requests  |
    Then logout

  Scenario: Verify pagination in Request Access tab
    Then clicks on "Request Access" tab
    And clicks on "Last Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "First Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "Next Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "Previous Page" button from pagination feature and see the "Request Access" tab page
    Then clicks on "Confirm Access" tab
    And clicks on "Last Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "First Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "Next Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "Previous Page" button from pagination feature and see the "Confirm Access" tab page
    Then logout

  Scenario: Verify pagination in Confirm Access tab
    Then clicks on "Confirm Access" tab
    And clicks on "Last Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "First Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "Next Page" button from pagination feature and see the "Request Access" tab page
    And clicks on "Previous Page" button from pagination feature and see the "Request Access" tab page
    Then clicks on "Confirm Access" tab
    And clicks on "Last Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "First Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "Next Page" button from pagination feature and see the "Confirm Access" tab page
    And clicks on "Previous Page" button from pagination feature and see the "Confirm Access" tab page
    Then logout
