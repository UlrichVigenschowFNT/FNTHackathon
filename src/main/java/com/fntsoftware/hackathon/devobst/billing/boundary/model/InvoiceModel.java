package com.fntsoftware.hackathon.devobst.billing.boundary.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Model for a invoice including the invoice items
 *
 */
@XmlType(name = "")
public class InvoiceModel {

	@XmlElement(name = "_uuid")
	private String uuid;

	private Date billingDate;

	private List<InvoiceItemModel> invoiceItems = new ArrayList<>();

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public List<InvoiceItemModel> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItemModel> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
