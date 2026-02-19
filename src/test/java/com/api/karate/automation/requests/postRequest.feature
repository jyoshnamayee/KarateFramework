Feature: Post request list of all objects

Background:
 * def testData = read("testData.csv")

Scenario: Post the list of all objects
 	Given  url 'https://api.restful-api.dev'
	And path  'objects'
	And request
	"""{ "name": "Apple MacBook Pro 17",
    "data": {
      "year": 2024,
      "price": 1849.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB"
   }
	}"""
	When  method post
	Then status 200
	And print response
	And match response.data.year == 2024
	