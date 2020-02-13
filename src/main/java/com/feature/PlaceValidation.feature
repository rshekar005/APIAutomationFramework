Feature: Validating place API
Scenario Outline: Verify that add place api is working or not
   Given Add place payload with "<Name>" "<Address>" "<Language>"
   When user calls "AddPlaceAPI" with post http request
   Then the api call is sucess with the status code with 200
   And "status" in response is "OK"
   And "scope" in response is "APP"
   
Examples:
 |Name |Address |Language|
 |Shiva|Gachbowli|Telugu|
 |Bharat|Gul Mohar Park|Malayalam|