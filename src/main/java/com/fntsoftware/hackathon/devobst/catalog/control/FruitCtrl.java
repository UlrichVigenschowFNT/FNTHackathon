package com.fntsoftware.hackathon.devobst.catalog.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

/**
 * Control for fruits
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class FruitCtrl {
	
	@PersistenceContext(unitName = "devobst-catalog-persistence-unit")
	EntityManager em;
	
	/**
	 * Creates a new fruit
	 * 
	 * @param fruit
	 * 
	 * @return created fruit
	 */
	public Fruit createFruit(Fruit fruit){
		em.persist(fruit);
		return fruit;
	}
	
	
	/**
	 * Gets the fruit identified by it's uuid
	 * 
	 * @param fruitUuid uuid of fruit
	 * 
	 * @return fruit
	 * @throws IllegalArgumentException if fruitUuid doesn't exist
	 */
	public Fruit getFruit(String fruitUuid){
		
		final Fruit fruit = findFruit(fruitUuid);
		if (fruit == null) {
			// in real life this should be a more precise exception
			throw new IllegalArgumentException();
		}

		return fruit;		
	}
	
	
	Fruit findFruit(final String fruitUuid) {

		final TypedQuery<Fruit> findByUUIDQuery = em.createNamedQuery(Fruit.FIND_BY_UUID, Fruit.class);
		findByUUIDQuery.setParameter("uuid", fruitUuid);

		Fruit fruit = null;
		try {
			fruit = findByUUIDQuery.getSingleResult();
		} catch (final NoResultException e) {
			// fruit not found - this should be logged (not in scope of this hackathon)
		}

		return fruit;
	}
	
	/**
	 * Returns all fruits
	 * 
	 * @return list of fruits
	 */
	public List<Fruit> listFruits(){
		final TypedQuery<Fruit> findAll = em.createNamedQuery(Fruit.FIND_ALL, Fruit.class);

		List<Fruit> fruits = null;
		try {
			fruits = findAll.getResultList();
		} catch (final NoResultException e) {			
			return new ArrayList<>();
		}

		return fruits;
	}
	
	/**
	 * Updates a existing fruit
	 * 
	 * @param fruitUuid uuid of the fruit
	 * @param updatedFruit updated fruit
	 * @return updated fruit object
	 */
	public Fruit updateFruit(String fruitUuid,Fruit updatedFruit){
		Fruit existingFruit = getFruit(fruitUuid);
		
		em.detach(existingFruit);
		
		existingFruit.setColor(updatedFruit.getColor());
		existingFruit.setName(updatedFruit.getName());
		existingFruit.setSpecies(updatedFruit.getSpecies());
		
		return em.merge(existingFruit);
	}
	
	
	/**
	 * Removes a fruit by it's given uuid
	 * 
	 * @param fruitUuid uuid of existing fruit
	 */
	public void removeFruit(String fruitUuid){
		final Fruit fruitToDelete = getFruit(fruitUuid);
		em.remove(fruitToDelete);
	}
}
