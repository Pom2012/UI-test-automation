Feature: IC HD and Administrator role user can update a IC User password

  Scenario Outline: Verify if an IC HD role user can update an IC User password
    Given TestName "IC HD and Administrator role user can update a IC User password" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Help Desk / Manage Users" tile and views the page

    And clicks on "Enterprise Search" and views the fields
#//TODO: DATA: taking row# out of Java - should be populated from the Feature file datatable
#  And fillout out the "UserID" field for searching the given "<row_index2>" and clicks on "Search" button
    And the table fields with the given IC user are displayed
    And clicks on "Select Action" and selects "Update Email"
#    Then clicks on "<feature>", justifies and clicks on "Generate Password"
#    And submit the "<feature>" confirmation message
#    Then "Temporary Password" text is generated
#    Then logout

    Examples:
      | row_index | row_index2 |
#      for DEV
      | 1         | 1          |
#      for VAL
#      | 19         | 1          | Generate Temporary Password |

#  Scenario Outline: Verify if an IC user can logs in with the updated password
#    Given TestName "IC HD and Administrator role user can update a IC User password" the landing page is loaded
#    When fills out <row_index2> with the "UserID"
#    And user without MFA loggs in
#    Then "Reset Password" box with fields displayed
#    And fills out with <row_index2> the "Enter Current Password", "Enter New Password" and "Confirm New Password"
#    And clicks on "Reset Password"
#
#    Then user logs in and sees "My Portal"
#
#    Then logout
#
#    Examples:
#      | row_index2 |
#      | 1          |






