@regression
@ICAdmin
Feature: IC Admin can create a new quarter/period for User Verification Schedule

  Background:
    Given TestName "Verify an IC Admin can create a new quarter for a practice" the landing page is loaded
    When a user "1" logs in with "non-MFA status"
    And clicks on "Administration Console" link in Innovation Center
    When types "AMR Verification Testing" in search text box, the system displays the "AMR Verification Testing"
    And clicks on "AMR Verification Testing" shield icon
    And "User Verification Management" and the "AMR Verification Testing" are displayed

  Scenario: Verify an IC Admin can create a new quarter for User Verification practice
    When Clicks on "Add New Quarter" button and see "Associated Users:" page
    And selects given information for the below features and confirm it:
      | My Practice Selection | Ohio      |
      | Select year           | this year |
      | Select quarter        | 3         |
      | Start Date            | Today     |
      | End Date              | Today     |
    Then logout

  Scenario: Verify an IC Admin can update a new quarter for User Verification practice
    And clicks on "edit" button after searching for the below practice:
      | My Practice Selection | Ohio      |
      | Select year           | this year |
      | Select quarter        | 3         |
      | Start Date            | Today     |
      | End Date              | Today     |
    Then logout
