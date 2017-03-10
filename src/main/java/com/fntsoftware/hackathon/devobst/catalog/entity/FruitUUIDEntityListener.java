package com.fntsoftware.hackathon.devobst.catalog.entity;

import javax.inject.Inject;
import javax.persistence.PrePersist;

/**
 * JPA entity listener for UUID generation on persist.
 */
public class FruitUUIDEntityListener {

	@Inject
	UUIDGenerator generator;

	@PrePersist
	public void generateUUID(final Fruit fruit) {
		fruit.setUuid(generator.createNewUuid().toString());
	}
}
