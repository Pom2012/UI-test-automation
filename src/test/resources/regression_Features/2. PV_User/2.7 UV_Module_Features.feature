@PV
@regression
Feature: IC PV - Participant user can access to "Participant Users" tabs

  Background:
    Given TestName "User Verification module testing" the landing page is loaded

  Scenario: Verify User Verification Schedule page features in "Participant Users" tab
    When a user "14" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    Then views "User Verification Schedule" page title and the below "web table columns":
      | Application | Organization | Year/QTR | Start Date | End Date | Review Status | Action |
    And views "User Verification Schedule" page title and the below "features":
      | Search feature  | Search Schedule |
      | Button          | Review          |
      | Select dropdown | Items per page  |
    Then logout

  Scenario: Verify unconfigured User's access verification reporting page for "Participant Users"
    When a user "20" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then views a blank page with the below text:
      | The User Access Verification reporting is not configured for your model(s).   |
      | Note: CPC+ and MDPCP model participants, please select the CPC+ or MDPCP      |
      | application widget on the Innovation Center Landing page and navigate to the  |
      | User Access Verification page to complete User Access Verification reporting. |
    Then logout

  Scenario Outline: Verify information icon in User Verification Schedule page for "Participant Users"
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks "<infoicon>" from "<page>" and sees the icon content
    Then logout
    Examples:
      | page                       | infoicon                                                      |
      | User Verification Schedule | View the user verification schedule for your organization(s). |

  Scenario Outline: Verify User Verification page's info icon #1 for "Participant Users"
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button
    When clicks "<infoicon>" from "<page>" and sees the icon content
    Then logout
    Examples:
      | page              | infoicon                                              |
      | User Verification | Complete the user verification for your organization. |

  Scenario Template: Verify User Verification page & "Associated Users" web table for "Participant Users"
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button for "<App>", "<Organization>", "<Year/QTR>" and "<Start Date>" columns
    Then views "User Verification | CMS Innovation Center" page with the below data:
      | Application | Organization | Year/QTR | User Verification Window |
    And "Associated Users" web table with the below columns:
      | User ID | First Name | Last Name | Email | Association Start Date | User Status | Justification | Review Status |
    Then logout
    Examples:
      | App              | Organization      | Year/QTR         | Start Date                               |
      | application name | organization name | year and quarter | start date of the organization's quarter |

  Scenario Outline: Verify user User Verification page info icon #2 for "Participant Users"
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button
    When clicks "<infoicon>" from "<page>" and sees the icon content
    Then logout
    Examples:
      | page              | infoicon                  |
      | User Verification | User Verification Window: |

  Scenario Outline: Verify Participant user can download Associated Users' excel file from User Verification page
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button for "<App>", "<Organization>", "<Year/QTR>" and "<Start Date>" columns
    And clicks on "Excel" file from "Associated Users" page and views the desktop version of the file
    Then logout
    Examples:
      | App              | Organization      | Year/QTR         | Start Date                               |
      | application name | organization name | year and quarter | start date of the organization's quarter |

  Scenario Outline: Verify Review Status select options in User Verification page for Participant user
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button for "<App>", "<Organization>", "<Year/QTR>" and "<Start Date>" columns
    Then selects the below option from "Review Status" select dropdown and see the table changes:
      | All         |
      | Completed   |
      | Not Started |
    Then logout
    Examples:
      | App              | Organization      | Year/QTR         | Start Date                               |
      | application name | organization name | year and quarter | start date of the organization's quarter |

  Scenario Outline: Verify User Status select options in User Verification page for Participant user
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button for "<App>", "<Organization>", "<Year/QTR>" and "<Start Date>" columns
    Then sees "User Verification" page
    And selects the below option from "Review Status" select dropdown and see the changes in "Justification" col:
      | Active       | Current User      |
      | Inactive     | Past User         |
      | Unauthorized | Unknown User      |
      | Unauthorized | Not Approved User |
    Then logout
    Examples:
      | App              | Organization      | Year/QTR         | Start Date                               |
      | application name | organization name | year and quarter | start date of the organization's quarter |

  Scenario Outline: Verify Attestation checkbox in User Verification page for Participant user
    When a user "11" logs in with "non-MFA status"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    When clicks on "Review" button for "<App>", "<Organization>", "<Year/QTR>" and "<Start Date>" columns
    Then sees "User Verification" page
    And the "Attestation" checkbox remains unselected and "Submit" button disabled
    When selects "Active" option from "Review Status" select dropdown
    Then clicks on selectable "Attestation" checkbox
    And "Submit" button becomes enabled
    Then logout
    Examples:
      | App              | Organization      | Year/QTR         | Start Date                               |
      | application name | organization name | year and quarter | start date of the organization's quarter |
