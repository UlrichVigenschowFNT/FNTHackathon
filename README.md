# FNTHackathon
FNT Hackathon - 01-APR-2017

## Microservice DevObst Catalog 

### Chapters
#### 01 REST
Implementation of the REST interface with JAX-RS.

The REST interface has these methods:
  
*  Create fruit

URL example:  POST http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits
JSON payload:

    {	   
	   "name":"Apfel",
	   "color":"Rot",
	   "species":"Elstar"
    }    
    
HTTP Response with Status 201 Created and Location Header content, e.g. http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits/dummyId

*  Update fruit

URL example:  PUT http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits/dummyId
JSON payload:

    {	   
	   "name":"Apfel",
	   "color":"Rot/Grün",
	   "species":"Elstar"
    }    
    
HTTP Response with Status 200 OK and Location Header content, e.g. http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits/dummyId

*  List all fruits

URL example:  GET http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits
JSON payload: none    
HTTP Response with Status 200 OK and JSON body

    [
	  {
	    "_uuid": "dummyId",
	    "color": "Rot",
	    "name": "Apfel",
	    "species": "Elstar"
	  },
	  {
	    "_uuid": "dummyId2",
	    "color": "Grün",
	    "name": "Apfel",
	    "species": "Granny Smith"
	  }
	]

* Get a specific fruit

URL example:  GET http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits/dummyId
JSON payload: none
HTTP Response with Status 200 OK and JSON body

    {
	  "_uuid": "dummyId",
	  "color": "Rot",
	  "name": "Apfel",
	  "species": "Elstar"
	}
	
* Delete a specific fruit

URL example:  DELETE http://localhost:8080/dev-obst-catalog-1.0.0-SNAPSHOT/rs/fruits/dummyId
JSON payload: none
HTTP Response with Status 204 No Content


#### 02 Database
Database Schema definition with Liquibase
#### 03 JPA JMS
Configuration of JPA/JMS with implementation of the JPA entities
#### 04 Business Logic
Implementation of the business logic


