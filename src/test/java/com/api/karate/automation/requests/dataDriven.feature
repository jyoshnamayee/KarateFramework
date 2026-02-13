Feature: Test a data driven use case using a csv file

@Data
 Scenario Outline: Post a request with multiple users
 	* def testData = read("testData.csv")
 	* def bearerTokenPath = call read('classpath:com/api/karate/automation/requests/BearerToken.feature@Token')
 	* def bearerToken = bearerTokenPath.Token
 	* def authHeader = 'Bearer ' + bearerToken
 	And print authHeader
 	Given url 'https://www.quickpickdeal.com/api/Customer/'
 	And path 'CreateCustomer'
 	And headers {"Authorization" : '#(authHeader)', "Content-Type" : "application/json"}
 	 And request
    """
    {
      "email": <email>,
      "firstName": <firstName>,
      "lastName": <lastName>,
      "isMaster": false
    }
    """
 	When  method POST
 	Then status 200
 	
 	Examples:
 	| read('testData.csv') |