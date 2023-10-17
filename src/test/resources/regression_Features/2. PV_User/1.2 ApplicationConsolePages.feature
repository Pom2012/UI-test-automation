@regression
@PV
Feature: IC PV BO user role can view Home, Request Access, and Confirm Access tabs

  Background:
    Given TestName "Home, Request, Confirm, Access tabs" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center
    Then "Innovation Center | Application Console" title is displayed

  Scenario: Verify Innovation Center | Application Console tabs page
    Then the below tabs are displayed on "Application Console" page:
      | Applications | Request Access | My Requests | Approve Requests | Email Notifications |

  Scenario: Verify Innovation Center | Application Console tab's page title
    Then "Applications | CMS Innovation Center" title is displayed
    When clicks on "Request Access" tab
    Then "Request Access | CMS Innovation Center" title is displayed
    When clicks on "My Requests" tab
    Then "My Requests | CMS Innovation Center" title is displayed
    When clicks on "Approve Requests" tab
    Then "Approve Requests | CMS Innovation Center" title is displayed
    When clicks on "Email Notifications" tab
    Then "Email Notifications | CMS Innovation Center" title is displayed
    Then logout

#  Scenario: Verify Request Access tab's features
#    Then sees the below tabs as an IC application BO:
#      | Home            |
#      | Request Access  |
#      | Confirm Access  |
#    Then clicks on "Request Access" tab and on the below subtabs and sees:
#      | All      | There are X requests          |
#      | Pending  | There are X Pending requests  |
#      | Approved | There are X Approved requests |
#      | Rejected | There are X Rejected requests |
#    And also views the below features:
#      | button        | Request New Access          |
#      | email icon    | Email Notification Settings |
#      | search engine | Search                      |
#    Then logout
#
#  Scenario: Verify Confirm Access tab's features
#    Then clicks on "Confirm Access" tab and on the below subtabs and sees:
#      | All      | There are X requests          |
#      | Pending  | There are X Pending requests  |
#      | Approved | There are X Approved requests |
#      | Rejected | There are X Rejected requests |
#    And selects and sees the below view options from select dropdown:
#      | View All             | All | Pending | Approved | Rejected |
#      | Business Owner View  | All | Pending | Approved | Rejected |
#    Then logout