@regression
@ICAdmin
Feature: IC Admin user can see Error Messages in Nested List

  Scenario Outline: Verify Error Messages in Nested List in IC Admin account
    Given TestName "Error Messages in Nested List" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "List Management" link in Innovation Center
    Then scrolls into view "<portlet>" and selects "Nested" list and submits it
    And see "Add Application Role Custom Attribute List" for "Nested" page
    And performs some below manipulations and sees the errors
      | leaves empty all fields        | Missing value for required field List Name. Please enter the missing information to proceed further. |
      | fills only the List Name field | The option group parent id is required. Please select one.                                           |

    Examples:
      | row_index |  portlet                                          |
      | 1         |  List Management of Application Custom Attributes |
