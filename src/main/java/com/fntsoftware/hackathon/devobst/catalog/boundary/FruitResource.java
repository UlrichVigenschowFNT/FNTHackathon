package com.fntsoftware.hackathon.devobst.catalog.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fntsoftware.hackathon.devobst.catalog.boundary.model.FruitModel;

/**
 * Boundary Service for fruits.
 */
@Stateless
@Path("fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {
	
	@GET
	public Response listFruits(){
		 List<FruitModel> fruitModels = new ArrayList<>();
		
		return Response.ok(new GenericEntity<List<FruitModel>>(fruitModels) {
		}).build();
	}

}
