package com.fntsoftware.hackathon.devobst.billing.entity;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

/**
 * This generator can be used to generate new uuid values of version 4 (random
 * uuid)
 */
@ApplicationScoped
public class UUIDGenerator {

	public UUID createNewUuid() {
		return UUID.randomUUID();
	}
}
