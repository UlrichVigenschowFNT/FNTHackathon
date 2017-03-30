package com.fntsoftware.hackathon.devobst.billing.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fntsoftware.hackathon.devobst.billing.boundary.model.InvoiceModel;
import com.fntsoftware.hackathon.devobst.billing.entity.Invoice;
import com.fntsoftware.hackathon.devobst.billing.entity.InvoiceItem;

/**
 * Controller for invoices (including invoice items)
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class InvoiceCtrl {

	@PersistenceContext(unitName = "devobst-billing-persistence-unit")
	EntityManager em;

	@Inject
	ProductCtrl productCtrl;

	/**
	 * Returns all invoices
	 * 
	 * @return list of invoices
	 */
	public List<Invoice> listInvoices() {
		final TypedQuery<Invoice> findAll = em.createNamedQuery(Invoice.FIND_ALL, Invoice.class);

		List<Invoice> invoices = null;
		try {
			invoices = findAll.getResultList();
		} catch (final NoResultException e) {
			return new ArrayList<>();
		}

		return invoices;
	}

	Invoice findInvoice(final String invoiceUuid) {

		final TypedQuery<Invoice> findByUUIDQuery = em.createNamedQuery(Invoice.FIND_BY_UUID, Invoice.class);
		findByUUIDQuery.setParameter("uuid", invoiceUuid);

		Invoice invoice = null;
		try {
			invoice = findByUUIDQuery.getSingleResult();
		} catch (final NoResultException e) {
			// invoice not found - this should be logged (not in scope of this
			// hackathon)
		}

		return invoice;
	}

	/**
	 * Gets the invoice identified by it's uuid
	 * 
	 * @param invoiceUuid
	 *            uuid of invoice
	 * 
	 * @return invoice
	 * @throws IllegalArgumentException
	 *             if invoiceUuid doesn't exist
	 */
	public Invoice getInvoice(String invoiceUuid) {

		final Invoice invoice = findInvoice(invoiceUuid);
		if (invoice == null) {
			// in real life this should be a more precise exception
			throw new IllegalArgumentException();
		}

		return invoice;
	}

	public Invoice registerInvoice(InvoiceModel invoiceModel) {

		Invoice invoice = new Invoice();
		invoice.setBillingDate(invoiceModel.getBillingDate());

		invoiceModel.getInvoiceItems().forEach(invoiceItemModel -> {
			InvoiceItem invoiceItem = new InvoiceItem();
			invoiceItem.setAmount(invoiceItemModel.getAmount());
			invoiceItem.setPrice(invoiceItemModel.getPrice());
			invoiceItem.setProduct(productCtrl.getActiveProduct(invoiceItemModel.getProductUuid()));
			invoice.addInvoiceItem(invoiceItem);
		});

		em.persist(invoice);
		return invoice;
	}

}
