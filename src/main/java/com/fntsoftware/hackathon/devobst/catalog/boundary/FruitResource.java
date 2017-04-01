package com.fntsoftware.hackathon.devobst.catalog.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.fntsoftware.hackathon.devobst.catalog.boundary.model.FruitModel;

/**
 * Boundary Service for fruits. (REST)
 */
@Stateless
@Path("fruits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {
	
	@Context
	protected UriInfo uriInfo;
	
	@GET
	public Response listFruits(){
		List<FruitModel> fruitModels = new ArrayList<>();
		
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		
		FruitModel fruitModel = new FruitModel();
		fruitModel.setUuid(uuid1.toString());
		fruitModel.setName("Apfel");
		fruitModel.setSpecies("Elstar");		
		fruitModel.setColor("Rot");
		fruitModels.add(fruitModel);
		
		fruitModel = new FruitModel();
		fruitModel.setUuid(uuid2.toString());
		fruitModel.setName("Apfel");
		fruitModel.setSpecies("Granny Smith");		
		fruitModel.setColor("Grün");
		fruitModels.add(fruitModel);
		
		// TODO get fruits from controller
		
		return Response.ok(new GenericEntity<List<FruitModel>>(fruitModels) {
		}).build();
	}
	
	@GET
	@Path("{uuid}")
	public Response getFruit(@PathParam("uuid") final String uuid){
		FruitModel fruitModel = new FruitModel();
		fruitModel.setUuid(uuid);
		fruitModel.setName("Apfel");
		fruitModel.setSpecies("Elstar");		
		fruitModel.setColor("Rot");
		
		// TODO get concrete fruit from controller and remove the dummy
		
		return Response.ok(fruitModel).build();
	}
	
	@PUT
	@Path("{uuid}")
	public Response updateFruit(@PathParam("uuid") final String fruitUuid, FruitModel fruitModel){		
		
		// TODO update concrete fruit with controller
		
		final ResponseBuilder responseBuilder = Response.status(Status.OK);
		responseBuilder.location(javax.ws.rs.core.UriBuilder.fromUri(uriInfo.getBaseUri()).path(FruitResource.class).path("/{id}").build(fruitUuid));
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("{uuid}")
	public Response removeFruit(@PathParam("uuid") final String fruitUuid){
		
		// TODO delete the selected fruit
		
		return Response.status(Status.NO_CONTENT).build();		
	}	
	
	@POST
	public Response createFruit(FruitModel fruitModel){
		
		String fruitUuid = "dummyId";
		
		// TODO create the fruit
		
		final ResponseBuilder responseBuilder = Response.status(Status.CREATED);
		responseBuilder.location(javax.ws.rs.core.UriBuilder.fromUri(uriInfo.getBaseUri()).path(FruitResource.class).path("/{id}").build(fruitUuid));
		return responseBuilder.build();				
	}

}
