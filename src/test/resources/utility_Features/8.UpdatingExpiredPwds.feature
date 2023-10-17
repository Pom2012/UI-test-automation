#//REVIEW: This file is likely replaced by 7f.MonthlyUpdatingPwds for a utility
# If similar is needed for Test purposes, the method of looping users should be revised
Feature: Updating expired users' passwords

  Scenario: Updating IC Users' expired passwords
    Given TestName "Updating multiple IC Users' password" the landing page is loaded
    When updating "25" users' password with "non-MFA" status
