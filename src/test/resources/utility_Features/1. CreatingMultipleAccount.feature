@UTIL
Feature: 1) Creating multiple IC users account

  Scenario Outline: New User is able to register through CMS Enterprise Portal
    Given TestName "New User Registration" the landing page is loaded
    When clicks the "New User Registration" button by title
    And clicks the "Choose Your Application" dropdown menu
    And selects "IC-Innovation Center" from the "Select Your Application"  dropdown menu
    And clicks "I agree to the Terms and Conditions" checkbox
    And clicks "Next" button
    And "<user>" entered "FirstName" data
    And "<user>" entered "LastName" data
    And "<user>" entered "BirthMonth" data
    And "<user>" entered "BirthDate" data
    And "<user>" entered "BirthYear" data

    And "<user>" entered "Street" data
    And "<user>" entered "City" data
    And "<user>" entered "State" data
    And "<user>" entered "ZipCode" data
    And "<user>" entered "Email" data
    And "<user>" entered "ConfirmEmail" data
    And "<user>" entered "PhoneNumber" data

    Then click step 2 Next button

    And "<user>" entered "UserName" data
    And "<user>" entered "Password" data
    And "<user>" entered "ConfirmPassword" data
    And "<user>" entered "Challenge_Question_1" data
    And "<user>" entered "Challenge_Question_1_Answer" data

    Then click step 3 Next button

    And click submit user button
    Then message "Your User ID has been successfully registered" displays
    And clicks "login" link


    Examples:
      | user |
      | 1         |
#      | 2         |
#      | 3         |
#      | 4         |
#      | 5         |
#      | 6         |
#      | 7         |
#      | 8         |
#      | 9         |
#      | 10        |
#      | 11        |
#      | 12        |
#      | 13        |
#      | 14        |
#      | 15        |
#      | 16        |
#      | 17        |
#      | 18        |
#      | 19        |
#      | 20        |
#      | 21        |
#      | 22        |
#      | 23        |
#      | 24        |
#      | 25        |
#      | 26        |
#      | 27        |
#      | 28        |
#      | 29        |
#      | 30        |
#      | 31        |
#      | 32        |
#      | 33        |
#      | 34        |
#      | 35        |
#      | 36        |
#      | 37        |
#      | 38        |
#      | 39        |
#      | 40        |
#      | 41        |
#      | 42        |
#      | 43        |
#      | 44        |
#      | 45        |
#      | 46        |
#      | 47        |
#      | 48        |
#      | 49        |
#      | 50        |
