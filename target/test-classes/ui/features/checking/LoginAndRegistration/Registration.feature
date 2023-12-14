@Registration
Feature: Digital bank registration bank

  Background:
    Given The user with "jack@test.co" is not in DB

    And User navigates to Digital Bank signup page

  @Test
  Scenario: Positive Case: As a user I create to create Digital BAnk account

    #Given User navigates to Digital Bank signup page (we put it in the background)
    When User creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email        | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 | jack@test.co | Tester123 | 12 Main St | City     | CA     | 99921      | US      | 2146591008 | 2136591208  | 1126593000 | true           |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email        | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Mr.   | Jack      | Test     | M      | 12/12/1990 | 123-44-2235 | jack@test.co | Tester123 | 12 Main St | City     | CA     | 99921      | US      | 2146591008 | 2136591208  | 1126593000 | true              | true             | true                  | true    |


  @NegativeRegistrationCases
  Scenario Outline: Negative Case: As a Digital Bank Admin I want to make sure users can not register without providing all valid data

    #Given User navigates to Digital Bank signup page (we put it in the background)
    When User creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then User should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob | ssn | email | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckMark | fieldWithError | errorMessage                        |
      |       |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | title          | Please select an item in the list.  |
      | Mr.   |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | firstName      | Please fill out this field.         |
      | Mr.   | Jack      |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | lastName       | Please fill out this field.         |
      | Mr.   | Jack      | Bai      |        |     |     |       |          |         |          |        |            |         |           |             |           |                | gender         | Please select one of these options. |