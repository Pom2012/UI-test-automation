@regression
@PV
Feature: IC PV user role can see Request Access page features

  Background:
    Given TestName "Request Access tabs in IC PV user account" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "Request Access" tab
    Then "Request Access | CMS Innovation Center" title is displayed

  Scenario: Verify Request Access | CMS Innovation Center page features
    And see the below features:
      | All fields are required unless specified as optional. |
      | Application Name                                      |
      | Role                                                  |
      | Justification                                         |
      | 500 Character(s) remaining                            |
      | Cancel                                                |
      | Submit                                                |
    Then logout

  Scenario: Verify Request Access tab page and features
    When clicks on "Request Access" tab
    Then "Request Access | CMS Innovation Center" title is displayed
    Then The system displays the "Request Access |  Innovation Center" page with the below text and fields:
      | Texts            | All fields are required unless specified as optional. | Application Name     | Role | Justification | 500 Character(s) remaining |
      | Select dropdowns | please select an application                          | please select a role | -    | -             | -                          |
      | Buttons          | Submit                                                | Cancel               | -    | -             | -                          |
    Then logout

  Scenario:  Verify the Justification's "500 Character(s) remaining" text on Request Access page
    When clicks on "Request Access" tab
    Then the "500 Character(s) remaining" changed after entering the bellow into Justification:
      | 500 | 34 | 13 | 9 | 501 | 700 |

  Scenario:  Verify error messages on Request Access page
    When clicks on "Request Access" tab
    And clicks on "Submit" button
    Then views the below errors in request access module:
      | Application Name is invalid! | Application Name is required |
      | Role is invalid!             | Role is required             |
      | Justification is invalid!    | Justification is required    |
    Then logout

  Scenario:  Verify "Cancel" button functionality on Request Access page
    When clicks on "Request Access" tab
    And selects the below features for and application:
      | Application name | Accountable Health Communities (AHC) |
      | Role             | AHC Screening/Navigation Site        |
    When clicks on "Cancel" button
    Then the system removes all selected and entered fields
    Then logout


#  Scenario: Verify "Cancel" functionality in Request Access | CMS Innovation Center page features
#    When clicks on "Request Access" tab
#    Then clicks on and views "Request New Access" page
#    When clicks on "Cancel" button
#    Then the below request status tabs in "Request Access" page is displayed:
#      | All | Pending | Approved | Rejected |
#    Then logout

#  Scenario: Verify requirements to justification text field
##    When clicks on "Request Access" tab
##    Then clicks on and views "Request New Access" page
#    When enter a proper amount text value to justification text field
#    Then the dynamic "500 Character(s) remaining" text is changed
#    Then logout
#
#  Scenario: Verify tooltip texts in Request Access tab page
#    When clicks on "Request Access" tab
#    Then points to feature text and sees the tooltip text below in "Request Access":
#      | Request New Access link | Click to request access to an application and a specific role |
#      | Email icon              | Change email notification setting                             |
#      | Search text field       | Search for Request Access Info                                |
#      | Search button           | Search                                                        |
#    Then logout
#
#  Scenario: Verify tooltip texts in Confirm Access tab page
#    When clicks on "Confirm Access" tab
#    Then points to feature text and sees the tooltip text below in "Confirm Access":
#      | Search text field | Search for Confirm Access Info |
#      | Search button     | Search                         |
#    Then logout