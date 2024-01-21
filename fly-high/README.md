
# FlyHigh Airlines

This project uses JWT token for security and exposes 2 sets of APIS
 a. Flight related: Header: Authentication : Bearer <token value>
        Get /flightDetails to get list of all of today's flights 
        Get /flightDetails/{airlineCode} to get list of all of today's flights  based on airlineCode
        Post /flightDetails to save a flight

b. JWT related
        Get  /register to register a user   content-type application/json
        Get  /authenticate to het jwt token content-type application/json


## Running Tests

This project has 2 sets of Tests

a. Individual class leve Tests
b. Integeration test

Note; Both type of tests can be ran by running as junit tests
 or by running maven clean install

Note: testing can also be done via swagger. http://localhost:8081/flyHigh/swagger-ui.html

this is test