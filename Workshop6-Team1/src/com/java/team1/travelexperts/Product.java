package com.java.team1.travelexperts;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int productId;

	private String prodName;

	//bi-directional many-to-many association to Supplier
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(
		name="products_suppliers"
		, joinColumns={
			@JoinColumn(name="ProductId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="SupplierId")
			}
		)
	private List<Supplier> suppliers;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public List<Supplier> getSuppliers() {
		return this.suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	// Override toString Method
	@Override
	public String toString() 
	{
		return getProdName();
	}

}