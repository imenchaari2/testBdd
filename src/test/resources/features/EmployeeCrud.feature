Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario Outline: As an admin I want to create a new employee.
    Given I can create a new employee
    And I sending post to be created with post id <id>, firstName <firstName>, lastName <lastName>, email <email>, cin <cin>, grade <grade>, phone <phone>, gotLeaveDays <gotLeaveDays> and recruitDay <recruitDay>
    Then I should be able to see my newly created employee

    Examples:
    |id | firstName | lastName          |email | cin | grade | phone | gotLeaveDays | recruitDay |
    |12yy | mohammed  | dammak            | mohammed.dammak@beprime.com | 11112335| developer | 50222355 | 9 |12/20/2022 |


  Scenario: GET collection of employees
    Given I perform GET operation for "/all"
    Then The employees are listed

  Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation with body as
      |id | firstName | lastName          |email | cin | grade | phone | gotLeaveDays | recruitDay |
      |12tt | amin  | dammak            | amin.dammak@beprime.com | 11112335| developer | 50222355 | 9 |12/20/2022 |
    Then I should be able to see my newly created employee to ensure my post operation for id "12tt"

    And  I Perform DELETE operation for "/delete/{id}"
      | id |
      | 12tt      |
    And I perform GET operation with path parameter for "/find/{id}"
      | id |
      | 12tt     |
    And I should not see the body with email as "amin.dammak@beprime.com"
