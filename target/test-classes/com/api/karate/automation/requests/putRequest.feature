Feature: Put request list of all objects

Background:
 Given  url 'https://api.restful-api.dev'

Scenario: Put the list of all objects
	And path  'objects/ff8081819782e69e019c40f10e582bb6'
	And request
	"""{
   "name": "Apple MacBook Pro 17",
   "data": {
      "year": 2024,
      "price": 2049.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB",
      "color": "silver"
   }
}"""
	When  method patch
	Then status 200
	And print response
	And match response.data.year == 2024