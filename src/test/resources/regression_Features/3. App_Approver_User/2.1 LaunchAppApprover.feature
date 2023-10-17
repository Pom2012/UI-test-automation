@IC_Approver
Feature: IC Application Approver launch IC applications

  Background:
    Given TestName "IC Application approver launch IC applications" the landing page is loaded
    When a user "2" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center

  @Verify_RFAA
  Scenario: IC Application approver accesses RFAA applications successfully
    And click RFAA_Application link
    Then RFAA application page is open
    Then logout

  @Verify_QMAT
  Scenario: IC report user accesses QMAT applications successfully
    And click QMAT_Application link
    Then QMAT application page is open
    Then logout

  @Verify_CPCBIR
  Scenario: IC report user accesses CPCBIR applications successfully
    And click CPCBIR_Application link
    Then CPCBIR application page is open
    Then logout

  @Verify_MDPCPBIR
  Scenario: IC report user accesses MDPCPBIR applications successfully
    And click MDPCPBIR_Application link
    Then MDPCPBIR application page is open
    Then logout

  @Verify_QMATR
  Scenario: IC report user accesses QMATR applications successfully
    And click QMATR_Application link
    Then QMATR application page is open
    Then logout

  @Verify_ICBIR
  Scenario: IC report user accesses ICBIR applications successfully
    And click ICBIR_Application link
    Then ICBIR application page is open
    Then logout
