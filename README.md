# FNTHackathon
FNT Hackathon - 01-APR-2017

## Microservice DevObst Billing

The DevObst Billing Service listens to the events in the topic *devobstTopic* for events with *JMSType*:

* FRUIT_CREATED: A new product will be registered with the same UUID and the provided name.
* FRUIT_UPDATED: If the product is known (by UUID) the name is updated.
* FRUIT_DELETED: If the product is known (by UUID) the product will be flagged disabled.

A Product has has the attributes: *UUID, name, active*.


