package com.fntsoftware.hackathon.devobst.catalog.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "T_FRUIT")
@EntityListeners(FruitUUIDEntityListener.class)
@NamedQueries({ @NamedQuery(name = Fruit.FIND_BY_UUID, query = "SELECT e FROM Fruit e WHERE e.uuid = :uuid"),
	@NamedQuery(name = Fruit.FIND_ALL, query = "SELECT e FROM Fruit e")})
public class Fruit implements Serializable {	

	private static final long serialVersionUID = 7844035896862409759L;
	
	public static final String FIND_BY_UUID = "Fruit.findByUUID";
	
	public static final String FIND_ALL = "Fruit.findAll";
	
	@Id
	@TableGenerator(name = "T_FRUIT", table = "T_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "ID_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "T_FRUIT")
	private Long id;
	
	private String uuid;
	
	private String name;
	
	private String color;
	
	private String species;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getUuid() {
		return uuid;
	}
	
	protected void setUuid(String uuid){
		this.uuid = uuid;
	}
}
