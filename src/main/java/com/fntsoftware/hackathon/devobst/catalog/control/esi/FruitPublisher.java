package com.fntsoftware.hackathon.devobst.catalog.control.esi;

import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

/**
 * External system interface to publish fruit changes to a topic
 *
 */
public interface FruitPublisher {

	void publishCreate(Fruit fruit);
	
	void publishUpdate(Fruit fruit);
	
	void publishDelete(String fruitUuid);
}
