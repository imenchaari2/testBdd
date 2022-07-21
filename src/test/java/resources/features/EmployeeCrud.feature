Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario Outline: As an admin I want to create a new employee.
    Given I can create a new employee
    And I'm sending post to be created with id <id>, firstName <firstName>, lastName <lastName> and email <email>
    Then I should be able to see my newly created employee and the response will return status 200

    Examples:
    |id | firstName | lastName          | email                     |
    |12yy | mohammed  | dammak            |mohammed.dammak@beprime.com|

#  Scenario: Update Employee record
#    Given I Set PUT employee service api endpoint
#    When I Set update request HEADER
#    And Send a PUT HTTP request
#    Then I receive valid http Response code 200
#
#  Scenario: Get Employee record
#    Given I Set GET employee service api endpoint
#    When I Set request HEADER
#    And Send a GET HTTP request
#
#    Then I receive valid http Response code 200
#
#  Scenario: DELETE Employee record
#    Given I Set DELETE employee service api endpoint
#    When I Set DELETE request HEADER
#    Then I receive valid http Response code 200
#
