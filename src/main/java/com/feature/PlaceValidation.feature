Feature: Validating place API
@AddPlaceAPI @SanityTest
Scenario Outline: Verify that add place api is working or not
   Given Add place payload with "<Name>" "<Address>" "<Language>"
   When user calls "AddPlaceAPI" with "POST" http request
   Then the api call is sucess with the status code with 200
   And "status" in response is "OK"
   And "scope" in response is "APP"
   And verify the place_id created that maps to "<name>" using "GetPlaceAPI"
   
Examples:
 |Name |Address |Language|
 |Shiva|Gachbowli|Telugu|
# |Bharat|Gul Mohar Park|Malayalam|

# Important Note: If first scenario executes successfully and second scenario depend on first scenario then depend variables or methods should declare as static. If not then values are not assigned to second scenario
@DeletePlaceAPI @RegressionTest
Scenario: Verify whether delete place API working or not
     Given DeletePlace Payload
     When user calls "DeletePlaceAPI" with "POST" http request
     Then the api call is sucess with the status code with 200
     And "status" in response is "OK"
    