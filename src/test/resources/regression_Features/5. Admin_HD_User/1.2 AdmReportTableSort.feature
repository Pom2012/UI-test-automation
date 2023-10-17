@501
@ICAdmin_HD
Feature: IC Admin + IC HD User role can sort User Role Details for Application

  Scenario Outline: Verify IC Admin + HD User role can sort User Role Details for Application
    Given TestName "IC Admin + HD User can can sort User Role Details" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA status"
    And clicks on "Report Center" link in Innovation Center
    And selects the below values from "Application Reports" dropdown of an "application" and clicks on "View Report":
      | Application Summary | Perseverance |
    Then scrolls down to "User Role Details for Application" after "Application Summary Report" table is displayed
    And clicks on the below sortable "<report>" columns and order it in "descending" and "ascending" sequence:
      | Request ID |
    And clicks the "CMS Enterprise Portal" button by title
    And logout

    Examples:
      | row_index | report                            |
      | 1         | User Role Details for Application |