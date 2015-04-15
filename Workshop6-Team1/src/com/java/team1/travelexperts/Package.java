package com.java.team1.travelexperts;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the packages database table.
 * 
 */
@Entity
@Table(name="packages")
@NamedQuery(name="Package.findAll", query="SELECT p FROM Package p")
public class Package implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int packageId;

	private BigDecimal pkgAgencyCommission;

	private BigDecimal pkgBasePrice;

	private String pkgDesc;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgEndDate;

	private String pkgName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pkgStartDate;

	//bi-directional many-to-many association to ProductsSupplier
	@ManyToMany (fetch = FetchType.EAGER) //remove this fetch stuff if the class break
	@JoinTable(
			name="packages_products_suppliers"
			, joinColumns={
					@JoinColumn(name="PackageId")
			}
			, inverseJoinColumns={
					@JoinColumn(name="ProductSupplierId")
			}
			)
	private List<ProductsSupplier> productsSuppliers;

	public Package() {
	}

	public int getPackageId() {
		return this.packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public BigDecimal getPkgAgencyCommission() {
		return this.pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission) {
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public BigDecimal getPkgBasePrice() {
		return this.pkgBasePrice;
	}

	public void setPkgBasePrice(BigDecimal pkgBasePrice) {
		this.pkgBasePrice = pkgBasePrice;
	}

	public String getPkgDesc() {
		return this.pkgDesc;
	}

	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}

	public Date getPkgEndDate() {
		return this.pkgEndDate;
	}

	public void setPkgEndDate(Date pkgEndDate) {
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgName() {
		return this.pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Date getPkgStartDate() {
		return this.pkgStartDate;
	}

	public void setPkgStartDate(Date pkgStartDate) {
		this.pkgStartDate = pkgStartDate;
	}

	public List<ProductsSupplier> getProductsSuppliers() {
		return this.productsSuppliers;
	}

	public void setProductsSuppliers(List<ProductsSupplier> productsSuppliers) {
		this.productsSuppliers = productsSuppliers;
	}

	// Override toString Method
	@Override
	public String toString() 
	{
		return getPkgName();
	}
	
	public List<?> getPackageProducts(Package p)
	{
		System.out.println("Getting package products");
		Session session = null;
		List<?> prodlist = null;
		try
		{
			session = HibernateUtilities.getSession();
			List<?> prod_sups = p.getProductsSuppliers();
			for (int i=0; i<prod_sups.size(); i++)
			{
				ProductsSupplier ps = (ProductsSupplier) prod_sups.get(i);
				//for each row, store the product name
				Query prod_query = session.createQuery("from Product where ProductId = :id ");
				prod_query.setParameter("id", ps.getProductId());
				prodlist = prod_query.list();  //this is a list of all of these package's products
				for (int j=0; j<prodlist.size(); j++)
				{
					Product pr = (Product) prodlist.get(j);
					System.out.println(pr.getProdName());
				}
			}
		}//end try
		catch (HibernateException e)
		{
			e.printStackTrace();
			
		}
		finally
		{session.close();}
		return prodlist;
	}
	
	public List<?> getPackageProductSuppliers(List<?> packageproducts)
	{
		// pass in a list of products in the desired package, return a list of suppliers for those products
		System.out.println("Getting package product suppliers");
		Session session = null;
		List<?> suplist = null;
		try
		{
			session = HibernateUtilities.getSession();
			for (int i=0; i<packageproducts.size(); i++)
			{
				Product p = (Product) packageproducts.get(i);
				suplist = p.getSuppliers();
			}
			// print out the result to check
			for (int i=0; i<suplist.size(); i++)
			{
				Supplier s = (Supplier) suplist.get(i);
				System.out.println(s.getSupName());
			}
		}//end try
		catch (HibernateException e)
		{
			e.printStackTrace();
			
		}
		finally
		{session.close();}
		return suplist;
	}
	
	// Garima Code 

	// This method will update an package object and save to DB
	// pass form values into this method to update a row in the Package take
	// Adapted from http://www.tutorialspoint.com/hibernate/hibernate_examples.htm
	public boolean updatePackage(int packageId,String pkgName,Date pkgStartDate,Date pkgEndDate,String pkgDesc,BigDecimal pkgAgencyCommission,BigDecimal pkgBasePrice)
	{

		//create session and transaction objects
		Session session = HibernateUtilities.getSession();
		Transaction tx = null;
		// change the object values
		try
		{
			tx = session.beginTransaction();
			Package pkg = (Package)session.get(Package.class, packageId);
			pkg.setPkgName(pkgName);
			pkg.setPkgStartDate(pkgStartDate);
			pkg.setPkgEndDate(pkgEndDate);
			pkg.setPkgDesc(pkgDesc);
			pkg.setPkgAgencyCommission(pkgAgencyCommission);
			pkg.setPkgBasePrice(pkgBasePrice);
			session.update(pkg);
			tx.commit();
			return true;
		}
		catch (HibernateException e)
		{
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		}
		finally
		{
			session.close();
		}
	} //end method

	public boolean createPackage(String pkgName,Date pkgStartDate,Date pkgEndDate,String pkgDesc,BigDecimal pkgAgencyCommission,BigDecimal pkgBasePrice)
	{
		//create session and transaction objects
		Session session = HibernateUtilities.getSession();
		Transaction tx = null;
		// change the object values
		try
		{
			tx = session.beginTransaction();
			Package pkg = new Package();
			// the database handles the autoincrement for the pkgId
			pkg.setPkgName(pkgName);
			pkg.setPkgStartDate(pkgStartDate);
			pkg.setPkgEndDate(pkgEndDate);
			pkg.setPkgDesc(pkgDesc);
			pkg.setPkgAgencyCommission(pkgAgencyCommission);
			pkg.setPkgBasePrice(pkgBasePrice);
			session.save(pkg);
			tx.commit();
			return true;
		}
		catch (HibernateException e)
		{
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		}
		finally
		{
			session.close();
		}
	} //end method


}