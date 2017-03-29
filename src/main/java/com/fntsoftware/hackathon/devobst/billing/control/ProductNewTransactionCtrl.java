package com.fntsoftware.hackathon.devobst.billing.control;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 * Control for products. This is a wrapper that creates a new transaction.
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProductNewTransactionCtrl {

	@Inject
	ProductCtrl productCtrl;

	public void registerNewProduct(String uuid, String name) {
		productCtrl.registerNewProduct(uuid, name);
	}

	public void updateProductName(String uuid, String name) {
		productCtrl.updateProductName(uuid, name);
	}

	public void deprecateProduct(String uuid) {
		productCtrl.deprecateProduct(uuid);
	}
}
