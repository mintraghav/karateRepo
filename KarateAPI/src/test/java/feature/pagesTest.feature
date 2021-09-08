Feature: get pages from api
  I want to use this template for my feature file

Background:
* url 'https://reqres.in'
* header accept = 'application/json' 
 
  Scenario: get pages
    Given path '/api/users'
    And param page = '2'
    When method get
    Then status 200
    
    And match response.data[*].first_name contains ["Michael"]
    And match response.data[*].first_name contains ['Michael','Lindsay']
    And match response.data..first_name contains ['Michael','Lindsay']
    And match response.data[0] == {"id": 7,"email": "michael.lawson@reqres.in","first_name": "Michael","last_name": "Lawson","avatar": "https://reqres.in/img/faces/7-image.jpg"}
 
 #ignore   
  Scenario: tc2 Create a user
Given path '/api/users/2'
And request {"name": "raghav","job": "leader"}     
When method put
Then status 200