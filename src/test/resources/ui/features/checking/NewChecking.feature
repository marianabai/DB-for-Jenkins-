Feature: Create a new checking account

  Scenario: Create a standard individual checking account
    #stepdefenitions below Given When Then And
    Given the user logs in as "manajam@ukr.net" "6Black6Hole6"
    When the user creates a new checking account with the following data
      | checkingAccountType | accountOwnership | accountName               | initialDepositAmount |
      | Standard Checking   | Individual       | Elon Musk Second Checking | 1000000.0            |
    Then the user should see the green confirmation message "Successfully created new Standard Checking account named Elon Musk Second Checking"
    And the user should see a newly added account card
      | accountName               | accountType       | ownership  | accountNumber | interestRate | balance    |
      | Elon Musk Second Checking | Standard Checking | Individual | 486132917     | 0.0%         | 1000000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount    | balance   |
      | 2023-10-17 03:58 | Income   | 845323932 (DPT) - Deposit | 1000000.0 | 1000000.0 |