# standardc

## About
* User signs up for using the system.
* User needs to login to use the system. Otherwise he api's will reject his request.
* Currently there is no timeout implemented in the system
* User needs to submit his request using compute api
* User can query his previous requests using two api's - previous-bydate, previous-byrange
* previous-bydate - User can specify from and to date , or just to date or just from date or just on date
* previous-byrange - User can specify from and to range of requests , or just from, or just two or last #n requests.

## Implementation
* springboot template project has been used for providing the api's.
* ComputeController.java & UserController.java have the API end points listed.
* Business logic is written in ComputeService.java and UserService.java
* An attempt was made by me to use vaadin for building the UI for this project. But I couldn't complete it by Monday morning.
* H2 database is used for storing the requests. IT can be easily replaced by mysql or any database as springboot provides the JPA for interacting with most of the databases. 

## API Calls

### Compute call
*Pass x,a,b,c 
*Response is response object with error code and error message . eg, If success error code is 99 and error message is "success"

POST /compute HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache.

Postman-Token: 57030a99-2390-4c6d-8f9a-a39aef830932.

{.
    "x":20 ,.
    
    "a":10 ,.
    
    "b":10, .
    
    "c":10.
    
}.

### Get previous requests from 01/01/2019
* Get all events from 01/01/20019 onwards. Note that username has to be passed as only if the user is logged the request is allowed

GET /previous-bydate?from=01/01/2019&amp; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: b1e62a59-a9a5-4a8a-b45c-57a07ed52ea0

### Get Previous requests from 01/01/2019 until 02/02/2019
* Get all events from 01/01/20019 until 02/20/2019. Note that username has to be passed as only if the user is logged the request is allowed

GET /previous-bydate?from=01/01/2019&to=02/20/2019; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: b1e62a59-a9a5-4a8a-b45c-57a07ed52ea0

### Get Previous requests on 02/20/2019
* Get all events on 02/20/2019. Note that username has to be passed as only if the user is logged the request is allowed

GET /previous-bydate?ondate=02/20/2019&amp; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: b1e62a59-a9a5-4a8a-b45c-57a07ed52ea0

### Get all the events from range 10 to 0

GET /previous-byrange?beforen=10&amp; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: 50774156-9e02-4e50-b081-de786583f1a6


### Get all the events from range 100 to 5

GET /previous-byrange?beforen=100&afteren=5&amp; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: 50774156-9e02-4e50-b081-de786583f1a6

### Get last 30 events
GET /previous-byrange?lastn=30&amp; username=sharatc HTTP/1.1

Host: localhost:8080

Content-Type: application/json

cache-control: no-cache

Postman-Token: 50774156-9e02-4e50-b081-de786583f1a6






