package com.fntsoftware.hackathon.devobst.billing.boundary;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.fntsoftware.hackathon.devobst.billing.boundary.mapping.InvoiceModelMapper;
import com.fntsoftware.hackathon.devobst.billing.boundary.model.InvoiceModel;
import com.fntsoftware.hackathon.devobst.billing.control.InvoiceCtrl;
import com.fntsoftware.hackathon.devobst.billing.entity.Invoice;

/**
 * Boundary Service for invoices. (REST)
 */
@Stateless
@Path("invoices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceResource {

	@Context
	protected UriInfo uriInfo;

	@Inject
	InvoiceModelMapper modelMapper;

	@Inject
	InvoiceCtrl invoiceCtrl;

	@GET
	public Response listInvoices() {

		List<Invoice> invoiceList = invoiceCtrl.listInvoices();

		List<InvoiceModel> invoiceModels = invoiceList.stream().map(p -> modelMapper.mapToModel(p))
				.collect(Collectors.toList());

		return Response.ok(new GenericEntity<List<InvoiceModel>>(invoiceModels) {
		}).build();
	}

	@GET
	@Path("{uuid}")
	public Response getInvoice(@PathParam("uuid") final String uuid) {

		Invoice invoice = invoiceCtrl.getInvoice(uuid);

		return Response.ok(modelMapper.mapToModel(invoice)).build();
	}

	@POST
	public Response registerInvoice(InvoiceModel invoiceModel) {

		// HINT: Mapping is out-of scope, in this case we simplified and put
		// model to controller

		Invoice createdInvoice = invoiceCtrl.registerInvoice(invoiceModel);

		final ResponseBuilder responseBuilder = Response.status(Status.CREATED);
		responseBuilder.location(javax.ws.rs.core.UriBuilder.fromUri(uriInfo.getBaseUri()).path(InvoiceResource.class)
				.path("/{id}").build(createdInvoice.getUuid()));
		return responseBuilder.build();
	}

}
