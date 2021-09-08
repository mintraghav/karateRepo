Feature: check returned users

Background: 
* url 'https://reqres.in/'
* header accept = 'application/json'

@second
Scenario: get list of all users
Given path '/api/users/2'
When method get
Then status 200
And print response
And match response.data.first_name != null
And match response.data.first_name == 'Janet'