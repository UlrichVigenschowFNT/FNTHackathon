package com.fntsoftware.hackathon.devobst.billing.boundary.mapping;

import com.fntsoftware.hackathon.devobst.billing.boundary.model.InvoiceItemModel;
import com.fntsoftware.hackathon.devobst.billing.boundary.model.InvoiceModel;
import com.fntsoftware.hackathon.devobst.billing.entity.Invoice;

/**
 * This is a mapper to map from the jpa entities to the boundary model
 *
 */
public class InvoiceModelMapper {

	public InvoiceModel mapToModel(Invoice invoice) {
		InvoiceModel invoiceModel = new InvoiceModel();
		invoiceModel.setBillingDate(invoice.getBillingDate());
		invoiceModel.setUuid(invoice.getUuid());

		invoice.getInvoiceItems().forEach(invoiceItem -> {
			InvoiceItemModel invoiceItemModel = new InvoiceItemModel();
			invoiceItemModel.setAmount(invoiceItem.getAmount());
			invoiceItemModel.setPrice(invoiceItem.getPrice());
			invoiceItemModel.setProductUuid(invoiceItem.getProduct().getUuid());
			invoiceModel.getInvoiceItems().add(invoiceItemModel);
		});

		return invoiceModel;
	}
}
