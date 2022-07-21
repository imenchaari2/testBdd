Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario Outline: As an admin I want to create a new employee.
    Given I can create a new employee
    And I sending post to be created with post id <id>, firstName <firstName>, lastName <lastName>, email <email>, cin <cin>, grade <grade>, phone <phone>, gotLeaveDays <gotLeaveDays> and recruitDay <recruitDay>
    Then I should be able to see my newly created employee

    Examples:
    |id | firstName | lastName          |email | cin | grade | phone | gotLeaveDays | recruitDay |
    |12yy | mohammed  | dammak            | mohammed.dammak@beprime.com | 11112335| developer | 50222355 | 9 |12/20/2022 |

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
