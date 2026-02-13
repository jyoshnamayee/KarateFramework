Feature: Get request list of all objects

Background:
* def testData = read("testData.csv")

Scenario: Get the list of all objects
	Given  url 'https://api.restful-api.dev'
	And path  'objects'
	When  method get
	Then status 200

Scenario: Get the list of all objects
	Given  url 'https://api.restful-api.dev'
	And path  'objects'
	When  method get
	Then status 200

@TokenBased
Scenario: Get the bearer token from bearer token API and use it in the main API
 	* def bearerTokenPath = call read('classpath:com/api/karate/automation/requests/BearerToken.feature@Token')
 	* def bearerToken = bearerTokenPath.Token
 	* def authHeader = 'Bearer ' + bearerToken
 	And print authHeader
 	Given url 'https://www.quickpickdeal.com/api/Customer/'
	And path 'GetAllCustomers'
 	And headers {"Authorization" : '#(authHeader)', "Content-Type" : "application/json"}
 	When  method get
 	Then status 200
 	And print response
 	* def user = karate.filter(response.Data, function(x){ return x.Id == 2 })[0]
 	And print user
 	* def emailtobeused = user.Email
 	And path 'CreateCustomer'
 	And headers {"Authorization" : '#(authHeader)', "Content-Type" : "application/json"}
 	And request 
 	"""{
                    "email": '#(emailtobeused)',
                    "firstName": "CarryHans",
                    "lastName": "Zing"

 	}"""
 	When  method POST
 	Then status 200