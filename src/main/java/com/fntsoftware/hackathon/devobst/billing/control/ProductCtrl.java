package com.fntsoftware.hackathon.devobst.billing.control;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Gets the product identified by it's uuid
	 * 
	 * @param productUuid
	 *            uuid of product
	 * 
	 * @return product
	 * @throws IllegalArgumentException
	 *             if productUuid doesn't exist
	 */
	public Product getProduct(String productUuid) {

		final Product product = findProduct(productUuid);
		if (product == null) {
			// in real life this should be a more precise exception
			throw new IllegalArgumentException();
		}

		return product;
	}

	/**
	 * Returns all products
	 * 
	 * @return list of products
	 */
	public List<Product> listProducts() {
		final TypedQuery<Product> findAll = em.createNamedQuery(Product.FIND_ALL, Product.class);

		List<Product> products = null;
		try {
			products = findAll.getResultList();
		} catch (final NoResultException e) {
			return new ArrayList<>();
		}

		return products;
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
