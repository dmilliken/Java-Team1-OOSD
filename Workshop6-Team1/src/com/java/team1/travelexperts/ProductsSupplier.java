package com.java.team1.travelexperts;

import java.io.Serializable;

import javax.persistence.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


/**
 * The persistent class for the products_suppliers database table.
 * 
 */
@NamedQueries({
	@NamedQuery(
			name = "findProdSup",
			query = "SELECT p from ProductsSupplier p where p.productId = :productId and p.supplierId = :supplierId"
			)
})

@Entity
@Table(name="products_suppliers")
@NamedQuery(name="ProductsSupplier.findAll", query="SELECT p FROM ProductsSupplier p")
public class ProductsSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productSupplierId;

	private int productId;

	private int supplierId;

	//bi-directional many-to-many association to Package
	@ManyToMany(mappedBy="productsSuppliers")
	private List<Package> packages;

	public ProductsSupplier() {
	}

	public int getProductSupplierId() {
		return this.productSupplierId;
	}

	public void setProductSupplierId(int productSupplierId) {
		this.productSupplierId = productSupplierId;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public List<Package> getPackages() {
		return this.packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public static int getProductSupplier(int ProductID, int SupplierId)
	{

		//SELECT * FROM `products_suppliers` WHERE `ProductId` = 1 AND `SupplierId` = 5492
		// returns the product supplierID given a productID and supplierID
		Session session = HibernateUtilities.getSession();
		int ProductSupplierId = -1;
		try
		{
			Query query = session.getNamedQuery("findProdSup");
			query.setInteger("productId", ProductID);
			query.setInteger("supplierId", SupplierId);

			List<?> list = query.list();
			if (!list.isEmpty()) 
			{
				ProductsSupplier ps = (ProductsSupplier) list.get(0);
				ProductSupplierId = ps.getProductSupplierId();
			}
		}//end try
		catch (HibernateException e)
		{}
		finally
		{
			session.close();
		}
		System.out.println("PS ID is " + ProductSupplierId);
		return ProductSupplierId;
	}
}