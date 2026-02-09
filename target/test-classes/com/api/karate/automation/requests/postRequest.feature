Feature: Post request list of all objects

Scenario: Get the list of all objects
	Given  url 'https://api.restful-api.dev'
	And path  'objects'
	And request
	"""{ "name": "Apple MacBook Pro 16",
   "data": {
      "year": 2019,
      "price": 1849.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB"
   }
	}"""
	When  method get
	Then status 200
	And print response
	