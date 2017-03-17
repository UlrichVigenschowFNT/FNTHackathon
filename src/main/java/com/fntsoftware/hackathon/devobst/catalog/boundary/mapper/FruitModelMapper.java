package com.fntsoftware.hackathon.devobst.catalog.boundary.mapper;

import com.fntsoftware.hackathon.devobst.catalog.boundary.model.FruitModel;
import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

/**
 * This is a mapper to map from the boundary model to the jpa entities and vice versa 
 *
 */
public class FruitModelMapper {

	public Fruit mapToEntity(FruitModel fruitModel){
		Fruit fruit = new Fruit();
		fruit.setName(fruitModel.getName());
		fruit.setSpecies(fruitModel.getSpecies());		
		fruit.setColor(fruitModel.getColor());		
		return fruit;
	}
	
	public FruitModel mapToModel(Fruit fruit){
		FruitModel fruitModel = new FruitModel();
		fruitModel.setUuid(fruit.getUuid());
		fruitModel.setName(fruit.getName());
		fruitModel.setColor(fruit.getColor());
		fruitModel.setSpecies(fruit.getSpecies());		
		return fruitModel;
	}
}
