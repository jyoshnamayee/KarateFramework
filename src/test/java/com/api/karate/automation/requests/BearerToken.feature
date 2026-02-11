Feature: Post request along with bearer token

@Token
Scenario: Use bearer token API to get the bearer token 
	Given url 'https://www.quickpickdeal.com/api/Auth/GetBearerToken'
    And header Content-Type = 'application/json'
    When method GET
    Then status 200
    And print response 
    * def Token = response.Data.Token
    And print Token
