Feature: 0.1.1 A user creates an account in Innovation Center

  Scenario Outline: New User is able to register through CMS Enterprise Portal
    Given TestName "New User Registration" the landing page is loaded
    When clicks the "New User Registration" button by title
    And clicks the "Choose Your Application" dropdown menu
    And selects "IC-Innovation Center" from the "Select Your Application"  dropdown menu
    And clicks "I agree to the Terms and Conditions" checkbox
    And clicks "Next" button
    And enters and/or selects data for "FirstName" from excel "<row_index>"
    And enters and/or selects data for "LastName" from excel "<row_index>"
    And enters and/or selects data for "BirthMonth" from excel "<row_index>"
    And enters and/or selects data for "BirthDate" from excel "<row_index>"
    And enters and/or selects data for "BirthYear" from excel "<row_index>"
    And selects "Yes" from "Is Your Home Address U.S. Based?"
    And enters and/or selects data for "Street" from excel "<row_index>"
    And enters and/or selects data for "City" from excel "<row_index>"
    And enters and/or selects data for "State" from excel "<row_index>"
    And enters and/or selects data for "ZipCode" from excel "<row_index>"
    And enters and/or selects data for "Email" from excel "<row_index>"
    And enters and/or selects data for "ConfirmEmail" from excel "<row_index>"
    And enters and/or selects data for "PhoneNumber" from excel "<row_index>"
    Then click step 2 Next button
    And enters and/or selects data for "UserName" from excel "<row_index>"
    And enters and/or selects data for "Password" from excel "<row_index>"
    And enters and/or selects data for "ConfirmPassword" from excel "<row_index>"
    And enters and/or selects data for "Challenge_Question_1" from excel "<row_index>"
    And enters and/or selects data for "Challenge_Question_1_Answer" from excel "<row_index>"
    Then click step 3 Next button
    And click submit user button
    Then message "Your User ID has been successfully registered" displays
    And clicks "login" link

    Examples:
      | row_index |
      | 1         |
      | 2         |
      | 3         |