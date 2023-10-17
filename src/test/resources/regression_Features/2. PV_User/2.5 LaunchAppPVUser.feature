Feature: IC Privileged user launch IC applications

  Background:
    Given TestName "IC Privileged user launch IC applications" the landing page is loaded
    When a user "5" logs in with "non-MFA status"
    And clicks on "Application Console" link in Innovation Center

  @Verify_AHC
  Scenario: 1) Verify IC Privileged user accesses AHC applications successfully
    And clicks "AHC" application link
    Then AHC application page is open
    Then logout

#  @Verify_IPC
#  Scenario: 2) Verify IC Privileged user accesses IPC applications successfully
#    And clicks "IPC" application link
#    Then IPC application page is open
#    Then logout
#
#  @Verify_MOM
#  Scenario: 3) Verify IC Privileged user accesses MOM applications successfully
#    And click MOM_Application link
#    Then MOM application page is open
#    Then logout

  @Verify_BPCI
  Scenario: 4) Verify IC Privileged user accesses BPCI applications successfully
    And click BPCI_Application link
    Then BPCI application page is open
    Then logout

#    report+PV
  @Verify_CDX
  Scenario: 5) Verify IC Privileged user accesses CDX applications successfully
    And click CDX_Application link
    Then CDX application page is open
    Then logout

  @Verify_CJR
  Scenario: 6) Verify IC Privileged user accesses CJR applications successfully
    And click CJR_Application link
    Then CJR application page is open
    Then logout

  @Verify_CPC+
  Scenario: 7) Verify IC Privileged user accesses CPCplus applications successfully
    And click CPCplus_Application link
    Then CPCplus application page is open
    Then logout

  @Verify_EnhMTM
  Scenario: 8) Verify IC Privileged user accesses EnhMTM applications successfully
    And click EnhMTM_Application link
    Then EnhMTM application page is open
    Then logout

  @Verify_ET3
  Scenario: 8) Verify IC Privileged user accesses ET3 applications successfully
    And click ET3_Application link
    Then ET3 application page is open
    Then logout

  @Verify_HHVBP
  Scenario: 9) Verify IC Privileged user accesses HHVBP applications successfully
    And click HHVBP_Application link
    Then HHVBP application page is open
    Then logout

  @Verify_HPI
  Scenario: 10) Verify IC Privileged user accesses HPI applications successfully
    And click HPI_Application link
    Then HPI application page is open
    Then logout

  @Verify_InCK
  Scenario: 11) Verify IC Privileged user accesses ICK applications successfully
    And click InCK_Application link
    Then InCK application page is open
    Then logout

  @Verify_MDPCP
  Scenario: 12) Verify IC Privileged user accesses MDPCP applications successfully
    And click MDPCP_Application link
    Then MDPCP application page is open
    Then logout

  @Verify_OCM+
  Scenario: 13) Verify IC Privileged user accesses OCM+  applications successfully
    And click OCM_Plus_Application link
    Then OCM_Plus application page is open
    Then logout

  @Verify_PCF
  Scenario: 14) Verify IC Privileged user accesses PCFsip applications successfully
    And click PCFsip_Application link
    Then PCFsip application page is open
    Then logout

  @Verify_eDFR
  Scenario: 15) Verify IC Privileged user accesses EDFR applications successfully
    And click EDFR_Application link
    Then EDFR application page is open
    Then logout

  @Verify_HDR
  Scenario: 17) Verify IC Privileged user accesses HDR applications successfully
    And click HDR_Application link
    Then HDR application page is open
    Then logout

  @Verify_SAPT4
  Scenario: 18) Verify IC Privileged user accesses ICSampleApp applications successfully
    And click ICSampleApp_Application link
    Then ICSampleApp application page is open
    Then logout

#  @Verify_MH
#  Scenario: 19) Verify IC Privileged user accesses MH applications successfully
#    And click MH_Application link
#    Then MH application page is open
#    Then logout
#
#  @Verify_OCM
#  Scenario: 20) Verify IC Privileged user accesses OCM applications successfully
#    And click OCM_Application link
#    Then OCM application page is open
#    Then logout

#		@Verify_LSDM 
#		Scenario: IC Privileged user accesses LSDM applications successfully 
#			And clicks the "Link to Enterprise Portal Home Page" button by title 
#			And click Application Console link in Innovation Center 
#			And click LSDM_Application link 
#			Then LSDM application page is open


