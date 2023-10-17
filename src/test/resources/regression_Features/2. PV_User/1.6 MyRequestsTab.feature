@regression
@PV
Feature: IC PV user role can see and use My Requests page features

  Background:
    Given TestName "Request Access tabs in IC PV user account" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed
    When clicks on "My Requests" tab
    Then "My Requests | CMS Innovation Center" title is displayed

  Scenario: Verify requests status tabs on "My Requests" tab page
    Then the below tabs are displayed on "My Requests" page:
      | All | Pending | Approved | Rejected |
    Then logout

  Scenario: Verify Search on "My Requests" tab page
    When clicks on "My Requests" tab
    When searches the below and the system returns results for the requested data:
      | 30456 |
    Then logout






#  Scenario: Verify Request Access | CMS Innovation Center page features
#    When clicks on "Request Access" tab
#    Then clicks on and views "Request New Access" page
#    And see the below features:
#      | All fields are required unless specified as optional. |
#      | Application Name                                      |
#      | Role                                                  |
#      | Justification                                         |
#      | 500 Character(s) remaining                            |
#      | Cancel                                                |
#      | Confirm                                               |
#    Then logout
#
#  Scenario: Verify "Cancel" functionality in Request Access | CMS Innovation Center page features
#    When clicks on "Request Access" tab
#    Then clicks on and views "Request New Access" page
#    When clicks on "Cancel" button
#    Then the below request status tabs in "Request Access" page is displayed:
#      | All | Pending | Approved | Rejected |
#    Then logout
#
#  Scenario: Verify requirements to justification text field
#    When clicks on "Request Access" tab
#    Then clicks on and views "Request New Access" page
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