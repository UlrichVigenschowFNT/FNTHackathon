package com.fntsoftware.hackathon.devobst.billing.boundary;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fntsoftware.hackathon.devobst.billing.boundary.mapping.ProductModelMapper;
import com.fntsoftware.hackathon.devobst.billing.boundary.model.ProductModel;
import com.fntsoftware.hackathon.devobst.billing.control.ProductCtrl;
import com.fntsoftware.hackathon.devobst.billing.entity.Product;

/**
 * Boundary Service for products. (REST)
 */
@Stateless
@Path("products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Context
	protected UriInfo uriInfo;

	@Inject
	ProductModelMapper modelMapper;

	@Inject
	ProductCtrl productCtrl;

	@GET
	public Response listProducts() {

		List<Product> productList = productCtrl.listProducts();

		List<ProductModel> productModels = productList.stream().map(p -> modelMapper.mapToModel(p))
				.collect(Collectors.toList());

		return Response.ok(new GenericEntity<List<ProductModel>>(productModels) {
		}).build();
	}
}
