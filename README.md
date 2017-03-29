# FNTHackathon
FNT Hackathon - 01-APR-2017

## Microservice DevObst Catalog 

### Chapters
#### 01 REST
Implementation of the REST interface with JAX-RS.

The REST interface has three methods:
  
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

For the devobst_catalog service there are two database tables needed:

* T\_ID\_GENERATOR

```
GENERATOR_NAME VARCHAR(255)
ID_VALUE BIGINT
```    

* T_FRUIT

```
ID BIGINT
UUID VARCHAR(36)
NAME VARCHAR(80)
COLOR VARCHAR(80)
SPECIES VARCHAR(80)
```


#### 03 JPA JMS
Configuration of JPA/JMS with implementation of the JPA entities

**JPA Entity: Fruit**

The implementation of the Fruit JPA Entity is done in the package *com.fntsoftware.hackathon.devobst.catalog.entity* and just named *Fruit*.

Hints on the implementation:

* For the UUID an entity listener (*@EntityListeners*) that creates a new uuid on *prepersist* is used
* A named query *Fruit.findByUUID* is prepared
* A named query *Fruit.findAll* is prepared
* For internal id generation a table generator is used (used annotations: *@TableGenerator*, *@GeneratedValue*, *@Id*) 

**Controller: FruitCtrl**

The implementation of the fruit controller is done in the package *com.fntsoftware.hackathon.devobst.catalog.control* and named *FruitCtrl*. 

Hints on the implementation:

* The controller is implemented as stateless bean (*@Stateless*)
* All methods need an existing transaction. This is forced by the *@TransactionAttribute(TransactionAttributeType.MANDATORY)*
* The JPA Entity Manager can be injected using the *@PersistenceContext* annotation
* Methods that are implemented (with rudimentary implementation):
    * *public Fruit getFruit(String fruitUuid)*
    * *public Fruit createFruit(Fruit fruit)*
    * *Fruit findFruit(final String fruitUuid)*
    * *public List<Fruit> listFruits()*
    * *public Fruit updateFruit(String fruitUuid,Fruit updatedFruit)*
    * *public void removeFruit(String fruitUuid)*	

#### 04 Business Logic
Implementation of the business logic

**Controller: Update Fruit**

The attributes *color*,*name* and *species* are updateable.

Hints on the implementation:

* Get JPA entity and detach, update the attributes and merge again.
* detach - merge is not needed at this point, this is helpful for a future optimistic locking strategy.

**Event notfications**

There should be events triggered if the fruit was created, updated or deleted:

JMSType:

* FRUIT_CREATED
* FRUIT_UPDATED
* FRUIT_DELETED

Text content example for created or updated: 


	{
	"name":"Apple",
	"color":"Green",
	"species":"Granny Smith",
	"uuid":"2bafdc45-6d83-47c8-b45e-83309fbda752"
	}

In case of delete, just send the uuid:

	2bafdc45-6d83-47c8-b45e-83309fbda752
	
Hints on the implementation:

* Interface *FruitPublisher* with methods for created, updated and deleted
* Implementation under *com.fntsoftware.hackathon.devobst.catalog.control.esi* in class *FruitPublisherImpl*
    * Inject resources with *@Resource(name="devobstConnectionFactory")* and *@Resource(name="devobstTopic")*
* Class *FruitEventWriter* is responsible to write the JSON text for the event body
* A *glassfish-web.xml* is required that references the resource and the message destination
