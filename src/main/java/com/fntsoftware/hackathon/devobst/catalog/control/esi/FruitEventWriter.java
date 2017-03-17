package com.fntsoftware.hackathon.devobst.catalog.control.esi;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

/**
 * JSON writer for the fruit events
 *
 */
public class FruitEventWriter {

	public String toJSON(Fruit fruit){
		final JsonObjectBuilder fruitEventObjectBuilder = Json.createObjectBuilder();
		
		fruitEventObjectBuilder.add("name", fruit.getName());
		fruitEventObjectBuilder.add("color", fruit.getColor());
		fruitEventObjectBuilder.add("species", fruit.getSpecies());
		fruitEventObjectBuilder.add("uuid", fruit.getUuid());		

		final JsonObject fruitEventObject = fruitEventObjectBuilder.build();

		return fruitEventObject.toString();
	}
}
