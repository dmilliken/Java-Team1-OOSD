package com.java.team1.travelexperts;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.HibernateException;
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
	@ManyToMany
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