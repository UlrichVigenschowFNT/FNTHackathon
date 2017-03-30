package com.fntsoftware.hackathon.devobst.billing.entity;

import javax.inject.Inject;
import javax.persistence.PrePersist;

/**
 * JPA entity listener for UUID generation on persist.
 */
public class InvoiceUUIDEntityListener {

	@Inject
	UUIDGenerator generator;

	@PrePersist
	public void generateUUID(final Invoice invoice) {
		invoice.setUuid(generator.createNewUuid().toString());
	}
}
