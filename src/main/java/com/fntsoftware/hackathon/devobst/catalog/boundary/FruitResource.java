package com.fntsoftware.hackathon.devobst.catalog.boundary;

import java.util.List;
import java.util.stream.Collectors;

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
import javax.ws.rs.core.UriInfo;

import com.fntsoftware.hackathon.devobst.catalog.boundary.mapper.FruitModelMapper;
import com.fntsoftware.hackathon.devobst.catalog.boundary.model.FruitModel;
import com.fntsoftware.hackathon.devobst.catalog.control.FruitCtrl;
import com.fntsoftware.hackathon.devobst.catalog.entity.Fruit;

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
	
	@Inject
	FruitModelMapper modelMapper;
	
	@Inject
	FruitCtrl fruitCtrl;
	
	@GET
	public Response listFruits(){
		
		List<Fruit> fruitList = fruitCtrl.listFruits();
		
		List<FruitModel> fruitModels = fruitList.stream().map(p -> modelMapper.mapToModel(p)).collect(Collectors.toList());
		
		return Response.ok(new GenericEntity<List<FruitModel>>(fruitModels) {
		}).build();
	}
	
	@GET
	@Path("{uuid}")
	public Response getFruit(@PathParam("uuid") final String uuid){
		
		Fruit fruit = fruitCtrl.getFruit(uuid);		
		
		return Response.ok(modelMapper.mapToModel(fruit)).build();
	}
	
	@PUT
	@Path("{uuid}")
	public Response updateFruit(@PathParam("uuid") final String fruitUuid, FruitModel fruitModel){		
		
		Fruit fruit = modelMapper.mapToEntity(fruitModel);
		
		Fruit updatedFruit = fruitCtrl.updateFruit(fruitUuid, fruit);		
		
		final ResponseBuilder responseBuilder = Response.status(Status.OK);
		responseBuilder.location(javax.ws.rs.core.UriBuilder.fromUri(uriInfo.getBaseUri()).path(FruitResource.class).path("/{id}").build(updatedFruit.getUuid()));
		
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("{uuid}")
	public Response removeFruit(@PathParam("uuid") final String fruitUuid){		

		fruitCtrl.removeFruit(fruitUuid);
		
		return Response.status(Status.NO_CONTENT).build();		
	}	
	
	@POST
	public Response createFruit(FruitModel fruitModel){
		
		Fruit fruit = modelMapper.mapToEntity(fruitModel);
		
		Fruit createdFruit = fruitCtrl.createFruit(fruit);			
		
		final ResponseBuilder responseBuilder = Response.status(Status.CREATED);
		responseBuilder.location(javax.ws.rs.core.UriBuilder.fromUri(uriInfo.getBaseUri()).path(FruitResource.class).path("/{id}").build(createdFruit.getUuid()));
		return responseBuilder.build();				
	}

}
