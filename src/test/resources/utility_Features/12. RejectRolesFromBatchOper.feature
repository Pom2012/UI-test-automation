Feature: Reject IC application roles from Batch Operations tab using utility class

  Scenario: Reject IC application roles using utility class
    Given TestName "Reject IC application roles using utility class" the landing page is loaded
    When a user "1" logs in with "non-MFA"
    And clicks on "Support Center" link in Innovation Center
    And clicks on "Batch Operations" and see the page
    When searches "ACO" in Application Name field of "Batch Operations"
    Then selects requests with Pending and Approved status and rejects them
    Then logout
