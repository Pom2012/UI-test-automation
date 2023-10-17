Feature: 3) IC users roles

  Scenario: Request multiple access to IC app
    Given TestName "Request multiple access to the application" the landing page is loaded
    When a user "4" logs in with "non-MFA"
    And clicks on "Application Console" link in Innovation Center
    When submit request access to applications and roles
    Then logout