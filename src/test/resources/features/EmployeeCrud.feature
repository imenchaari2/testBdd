Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario: GET collection of employees
    Given I perform GET operation for "/all"
    Then The employees are listed

  Scenario: Verify PUT operation after POST
#    Given I ensure to add an employee with unique email
    Given I ensure to Perform POST operation with body as
      | id   | firstName | lastName | email                   | password|cin      | grade     | phone    | gotLeaveDays | recruitDay |city|street|postalCode|archivedDay|isArchived|
      | abcd| amin      | dammak   | amin.dammak@beprime.com | 12539    |11112335 | developer | 50222355 | 9            | 2022-09-12 |sfax|rte tunis|3042| 2022-09-12    |false|
    And  I Perform PUT operation for "/update"
      | id   | firstName | lastName | email              | password      | cin      | grade     | phone    | gotLeaveDays | recruitDay |city|street|postalCode|archivedDay|isArchived|
      | abcd| nessrine      | aloulou   | amin.dammak@beprime.com | 12539    | 11112335 | developer | 50222355 | 9            | 2022-09-12 |tunis|rte tunis|3042|2022-09-12|false|
    And I perform GET operation after PUT with path parameter for "/find/"
      | id   |
      | abcd |
    Then I should see the body with email as "amin.dammak@beprime.com" and firstName as "nessrine"

  Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation with body as
      | id   | firstName | lastName | email                   | password    | cin      | grade     | phone    | gotLeaveDays | recruitDay |city|street|postalCode|archivedDay|isArchived|
      | 12tt | amal      | dammak   | amin.dammak@beprime.com |12539    | 11112335 | developer | 50222355 | 9            | 2022-09-12 |sfax|rte tunis|3042| 2022-09-12|false|
    Then I should be able to see my newly created employee to ensure my post operation for id "12tt"

    And  I Perform DELETE operation for "/delete/"
      | id   |
      | 12tt |
    And I perform GET operation after DELETE
      | id   |
      | 12tt |
    And if the Post operation is done then I should not see the body with email as "amin.dammak@beprime.com"
      | id   |
      | 12tt |
