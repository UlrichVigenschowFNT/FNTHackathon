package com.fntsoftware.hackathon.devobst.billing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * A item of the invoice. Describes the amount, price and references to a
 * product.
 */
@Entity
@Table(name = "T_INVOICE_ITEM")
public class InvoiceItem implements Serializable {

	private static final long serialVersionUID = -2981789530682768978L;

	@Id
	@TableGenerator(name = "T_INVOICE_ITEM", table = "T_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "ID_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "T_INVOICE_ITEM")
	private Long id;

	private Integer amount;

	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
