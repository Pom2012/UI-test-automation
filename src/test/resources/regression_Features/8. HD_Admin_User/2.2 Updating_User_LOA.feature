Feature: IC HD and Administrator role user can update a IC User LOA

  Scenario Outline: Verify IC HD role user can update an IC User LOA
    Given TestName "IC HD and Administrator role user can update a IC User LOA" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "Help Desk / Manage Users" tile and views the page
    And clicks on "Enterprise Search" and views the fields
    And fillout out the "UserName" field for searching the "<row_index2>" and clicks on "Search" button
    And the table fields with the given IC user are displayed
    And clicks on "Select Action" and selects "Update LOA"
    And selects: "3" from "LOA", "Manually vetted by Application Help Desk" and justified the change
    And enters the <row_index2> "SSNumber"
    Then the submit the LOA changes
    And navigates back

    Then logout

    Examples:
      | row_index | row_index2 |
      | 17        | 1          |