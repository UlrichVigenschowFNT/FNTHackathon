package com.fntsoftware.hackathon.devobst.billing.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "T_PRODUCT")
@NamedQueries({ @NamedQuery(name = Product.FIND_BY_UUID, query = "SELECT e FROM Product e WHERE e.uuid = :uuid")})
public class Product implements Serializable {	

	private static final long serialVersionUID = 7844035896862409459L;
	
	public static final String FIND_BY_UUID = "Fruit.findByUUID";	
	
	@Id
	@TableGenerator(name = "T_PRODUCT", table = "T_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "ID_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "T_PRODUCT")
	private Long id;
	
	private String uuid;
	
	private String name;	
	
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

	public String getUuid() {
		return uuid;
	}
	
	protected void setUuid(String uuid){
		this.uuid = uuid;
	}
}
