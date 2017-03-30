package com.fntsoftware.hackathon.devobst.billing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Invoice is a collection of InvoiceItems
 *
 */
@Entity
@Table(name = "T_INVOICE")
@EntityListeners(InvoiceUUIDEntityListener.class)
@NamedQueries({ @NamedQuery(name = Invoice.FIND_BY_UUID, query = "SELECT e FROM Invoice e WHERE e.uuid = :uuid"),
		@NamedQuery(name = Invoice.FIND_ALL, query = "SELECT e FROM Invoice e") })
public class Invoice implements Serializable {

	private static final long serialVersionUID = 788539558069589717L;

	public static final String FIND_ALL = "Invoice.findAll";

	public static final String FIND_BY_UUID = "Invoice.findByUUID";

	@Id
	@TableGenerator(name = "T_INVOICE", table = "T_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "ID_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "T_INVOICE")
	private Long id;

	private String uuid;

	@Column(name = "BILLING_DATE")
	private Date billingDate;

	@OneToMany
	@JoinColumn(name = "INVOICE_ID", nullable = false)
	private List<InvoiceItem> invoiceItems = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public void addInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItems.add(invoiceItem);
	}

	public void removeInvoiceItem(InvoiceItem invoiceItem) {
		this.invoiceItems.remove(invoiceItem);
	}

	public String getUuid() {
		return uuid;
	}

	protected void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
