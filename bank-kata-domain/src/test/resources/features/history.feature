Feature: Account operations history

  Scenario: History of account operations is reported
    Given that user's account number is 123456
    When the user makes a deposit of 10.0
    When the user makes a withdrawal of 5.0
    When the user makes a deposit of 17.0
    Then account 123456 has a balance of 22.0
    Then account has 3 operations in its history