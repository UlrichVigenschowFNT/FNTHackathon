package com.fntsoftware.hackathon.devobst.billing.boundary.mapping;

import com.fntsoftware.hackathon.devobst.billing.boundary.model.ProductModel;
import com.fntsoftware.hackathon.devobst.billing.entity.Product;

/**
 * This is a mapper to map from the jpa entities to the boundary model
 *
 */
public class ProductModelMapper {

	public ProductModel mapToModel(Product product) {
		ProductModel productModel = new ProductModel();
		productModel.setUuid(product.getUuid());
		productModel.setName(product.getName());
		productModel.setActive(product.getActive());
		return productModel;
	}
}
