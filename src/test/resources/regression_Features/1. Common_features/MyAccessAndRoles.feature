@regression
Feature: Application users' role access control verification

  Scenario Outline: Verify broken access control for users
    Given TestName "Verify broken access control for users" the landing page is loaded
    When a user "<userRowIndex>" logs in with "non-MFA"
    Then user successfully logged in
    When clicks on IC tile and selects an IC role related "<module>"
    When opens a new window tabs, enters the below url links and views "You don't have authorization to view this page":
      | <link1> | <link2> | <link3> | <link4> | <link5> |
    Examples:
      | userRowIndex | module                 | link1 | link2 | link3 | link4 | link5 |
      | 5            | Application Console    | am    | rp    | hd    | lm    | -     |
      | 7            | Administration Console | ac    | uv    | hd    | -     | -     |
      | 6            | Report Center          | am    | uv    | hd    | lm    | -     |
      | 8            | Support Center         | am    | uv    | ac    | lm    | rp    |







#//TODO: Refactor role to be driven from Excel - Loop the (stable/already assigned all roles) users per env tab
#  Scenario Outline: Verify a <role> user can login and see their role(s) in My Access
#    Given TestName "Users see the roles in My Access page" the landing page is loaded
#    When a user "<index>" logs in with "non-MFA"
#    And user is logged in as "<role>"
#    Then logout
#    Examples:
#      | index | role                                                              |
#      | 1     |  Administrator + Helpdesk User |
#      | 2     |  Application Approver                            |
#      | 3     |  Report User + Privileged User |
#      | 4     |  User                                            |
#      | 8     | Helpdesk User                                   |
#      | 9     | Administrator                                   |
#      | 11    | Privileged User                                 |
