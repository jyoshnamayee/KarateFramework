
Feature: Get request list of all objects

Scenario: Get the list of all objects
	Given  url 'https://api.restful-api.dev'
	And path  'objects'
	When  method get
	Then status 200




