package com.fntsoftware.hackathon.devobst.billing.boundary.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

/**
 * Model representing one invoice item
 *
 */
@XmlType(name = "")
public class InvoiceItemModel {

	private Integer amount;

	private BigDecimal price;

	private String productUuid;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductUuid() {
		return productUuid;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}
}
