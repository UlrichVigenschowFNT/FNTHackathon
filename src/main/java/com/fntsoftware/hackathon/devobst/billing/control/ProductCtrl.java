package com.fntsoftware.hackathon.devobst.billing.control;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.fntsoftware.hackathon.devobst.billing.entity.Product;

/**
 * Controller for products
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ProductCtrl {

	@PersistenceContext(unitName = "devobst-billing-persistence-unit")
	EntityManager em;

	Product findProduct(final String productUuid) {

		final TypedQuery<Product> findByUUIDQuery = em.createNamedQuery(Product.FIND_BY_UUID, Product.class);
		findByUUIDQuery.setParameter("uuid", productUuid);

		Product product = null;
		try {
			product = findByUUIDQuery.getSingleResult();
		} catch (final NoResultException e) {
			// product not found - this should be logged (not in scope of this
			// hackathon)
		}

		return product;
	}

	public void registerNewProduct(String uuid, String name) {
		Product product = new Product();
		product.setName(name);
		product.setUuid(uuid);
		product.setActive(Boolean.TRUE);
		em.persist(product);
	}

	public void updateProductName(String uuid, String name) {
		Product existingProduct = findProduct(uuid);

		em.detach(existingProduct);
		existingProduct.setName(name);
		em.merge(existingProduct);
	}

	public void deprecateProduct(String uuid) {
		Product existingProduct = findProduct(uuid);

		em.detach(existingProduct);
		existingProduct.setActive(Boolean.FALSE);
		em.merge(existingProduct);
	}
}
