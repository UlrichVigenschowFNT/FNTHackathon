# FNTHackathon
FNT Hackathon - 01-APR-2017

## Microservice DevObst Billing

The DevObst Billing Service listens to the events in the topic *devobstTopic* for events with *JMSType*:

* FRUIT_CREATED: A new product will be registered with the same UUID and the provided name.
* FRUIT_UPDATED: If the product is known (by UUID) the name is updated.
* FRUIT_DELETED: If the product is known (by UUID) the product will be flagged disabled.

A Product has has the attributes: *UUID, name, active*.

### REST
The REST interface has these methods:

*  List products

URL example: GET http://localhost:8080/dev-obst-billing-1.0.0-SNAPSHOT/rs/products   
JSON payload: none  
HTTP Response with Status 200 OK and JSON body  
    
    [
     {
      "_uuid": "29a65d66-f651-41e5-b787-a7351bae6bae",
      "active": true,
      "name": "Apfel"
     },
     {
      "_uuid": "29a65d66-f651-41e5-b787-a7351bae7bae",
      "active": true,
      "name": "Banane"
     }
    ]

* Register invoice

URL example: POST http://localhost:8080/dev-obst-billing-1.0.0-SNAPSHOT/rs/invoices  
JSON payload:   

     {
      "billingDate":"2017-04-01T08:25:43-05:00",
      "invoiceItems":[
    	 {
    	 	"amount":5,
    	 	"price":1.99,
    	 	"productUuid":"29a65d66-f651-41e5-b787-a7351bae6bae"
    	 }
      ]
    }
    
HTTP Response with Status 201 Created and Location Header content, e.g.  
http://localhost:8080/dev-obst-billing-1.0.0-SNAPSHOT/rs/invoices/4fe00dcb-0357-49dc-a42e-2be18ae80bcb

* List Invoices

URL example: GET http://localhost:8080/dev-obst-billing-1.0.0-SNAPSHOT/rs/invoices  
JSON payload: none  
HTTP Response with Status 200 OK and JSON body  

    [     
     {
      "_uuid": "92f1d8c9-a5d0-48de-94b1-9bbd95aeff5b",
      "billingDate": "2017-04-01T00:00:00+02:00",
      "invoiceItems": [
       {
        "amount": 20,
        "price": 5.99,
        "productUuid": "29a65d66-f651-41e5-b787-a7351bae7bae"
       },
       {
        "amount": 3,
        "price": 0.20,
        "productUuid": "29a65d66-f651-41e5-b787-a7351bae8bae"
       }
      ]
     },
     {
      "_uuid": "4fe00dcb-0357-49dc-a42e-2be18ae80bcb",
      "billingDate": "2017-04-01T00:00:00+02:00",
      "invoiceItems": [
       {
        "amount": 5,
        "price": 1.99,
        "productUuid": "29a65d66-f651-41e5-b787-a7351bae6bae"
       }
      ]
     }
    ]
    
* Get a specific invoice

URL example: GET http://localhost:8080/dev-obst-billing-1.0.0-SNAPSHOT/rs/invoices/4fe00dcb-0357-49dc-a42e-2be18ae80bcb  
JSON payload: none  
HTTP Response with Status 200 OK and JSON body  
      
    {
     "_uuid": "4fe00dcb-0357-49dc-a42e-2be18ae80bcb",
     "billingDate": "2017-04-01T00:00:00+02:00",
     "invoiceItems": [
      {
       "amount": 5,
       "price": 1.99,
       "productUuid": "29a65d66-f651-41e5-b787-a7351bae6bae"
      }
     ]
    }
    
### Database

For the devobst_billing service these database tables are needed:

* T\_ID\_GENERATOR

```
GENERATOR_NAME VARCHAR(255)
ID_VALUE BIGINT
```
    
* T\_PRODUCT

```
ID BIGINT
UUID VARCHAR(36)
NAME VARCHAR(80)
ACTIVE BOOLEAN
```

* T\_INVOICE\_ITEM

```
ID BIGINT
AMOUNT NUMERIC
PRICE NUMERIC
PRODUCT_ID BIGINT
INVOICE_ID BIGINT
```

* T\_INVOICE

```
ID BIGINT
UUID VARCHAR(36)
BILLING_DATE DATE
```



