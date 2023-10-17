@smoke
@demo
@UserVerificationSchedule
@cmmi-uv
Feature: IC PV role user can access User Verification page
  As an IC PV role user, I want to have access to User Verification page

  Scenario Outline: Verify Privileged role user can access User Verification module
    Given TestName "User verification access page" the landing page is loaded
    When a user "<row_index>" logs in with "non-MFA"
    And clicks on "User Verification" link in Innovation Center
    Then sees "User Verification Schedule" page
    And clicks the "CMS Enterprise Portal" button by title
    Then logout
    Examples:
      | row_index |
      | 14        |