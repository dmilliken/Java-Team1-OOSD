package com.java.team1.travelexperts;

import java.util.List;

import oracle.net.aso.s;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class TestSelectWithHibernate 
{

	public static void main(String[] args) 
	{		
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
		Session session = sessionFactory.openSession();

		// now lets pull events from the database and list them
		session = sessionFactory.openSession();
		session.beginTransaction();
//		List result = session.createQuery( "from Agent" ).list();
//		for ( Agent a : (List<Agent>) result ) {
//			System.out.println( a.getAgtFirstName() + " " + a.getAgtLastName() );
//		}
		
		//session.get(Agent.class, 1);

		// using an un-named query
//		Query query = session.createQuery("from Agent where agentId = :id ");
//		query.setParameter("id", 5);
//		
//		List<?> list = query.list();
//		if (!list.isEmpty()) 
//		{
//			Agent a = (Agent) list.get(0);
//			System.out.println(a.getAgentId() + " " + a.getAgtFirstName());
//		}
		// try to find an agent by last name using the named query

//		Query query = session.getNamedQuery("findAgentByLastName")
//				.setString("agtLastName", "Dahl");
//
//		List<?> list = query.list();
//		if (!list.isEmpty()) 
//		{
//			Agent a = (Agent) list.get(0);
//			System.out.println(a);
//		}
		
		// find all agents and display
		Query queryFindAll = session.getNamedQuery("Agent.findAll");
		List<?> allAgents = queryFindAll.list();
		if (!allAgents.isEmpty()) 
		{
			// if the list of agents is nonempty, loop through it
			for (int i = 0; i < allAgents.size(); i++) 
			{
				Agent a = (Agent) allAgents.get(i);
				//System.out.println("Agent number "+ a.getAgentId() + " is " + a.getAgtFirstName() + " " + a.getAgtLastName());
			}
		}
		
		System.out.println("Overriding toString for Agent: ");
		//System.out.println(allAgents.get(0)); //this prints a bunch of garbage
		System.out.println(allAgents.get(0).toString()); //this is what we want to put in the JcomboBox
						
		// the following code gets an agent by ID and then gets a list of all that agent's customers. 
		Query query = session.getNamedQuery("findAgentByID")
				.setInteger("agentId", 4);

		List<?> list = query.list();
		if (!list.isEmpty()) 
		{
			Agent a = (Agent) list.get(0);
			System.out.println("Agent number "+ a.getAgentId() + " is " + a.getAgtFirstName());
			
			//use the customer method generated in the entity class
			List<Customer> customers = a.getCustomers();
			//How many customers does this agent have?
			System.out.println("This Agent has " + customers.size() + " customers.");
			// Display all of them
			for (int i = 0; i < customers.size(); i++) {
				Customer oneCustomer = (Customer) customers.get(i);
				System.out.println(oneCustomer.getCustomerId() + " " + oneCustomer.getCustFirstName());
			}
			//Customer oneCustomer = (Customer) customers.get(0);
			//Customer anotherCustomer = customers.get()
			//System.out.println(oneCustomer.getCustomerId() + " " + oneCustomer.getCustFirstName() );
		}
		
		// fetching information about packages
		
//		Query pkg_query = session.getNamedQuery("Package.findAll");
//		List<?> allPackages = pkg_query.list();
//		
//		if (!allPackages.isEmpty()) 
//		{
//			// if the list of agents is nonempty, loop through it
//			for (int i = 0; i < allPackages.size(); i++) 
//			{
//				Package p = (Package) allPackages.get(i);
//				List<?> PkgProdSuppliers = p.getProductsSuppliers();
//				System.out.println("Package number "+ p.getPackageId() + " has " + PkgProdSuppliers.size() + " ProductSuppliers");
//				
////				for (int j = 0; j < PkgProdSuppliers.size(); j++) 
////				{
////					ProductsSupplier ps = (ProductsSupplier) PkgProdSuppliers.get(i);
////					System.out.println(ps.getProductId());
////				}
//			}
//		}
		Product p0 = null;
		Query pkg_query = session.createQuery("from Package where PackageId = :id ");
		pkg_query.setParameter("id", 4);
		List<?> pkglist = pkg_query.list();
		if (!pkglist.isEmpty()) 
		{
			Package pkg = (Package) pkglist.get(0);
			System.out.println(pkg.getPkgName());
			System.out.println("Products:");
			List<?> prodsuplist = pkg.getProductsSuppliers();
			
			for (int i = 0; i < prodsuplist.size(); i++) 
			{
				//for each product supplier, 
				ProductsSupplier ps = (ProductsSupplier) prodsuplist.get(i);
				// get a list of product objects
				Query prod_query = session.createQuery("from Product where ProductId = :id ");
				prod_query.setParameter("id", ps.getProductId());
				List<?> prodlist = prod_query.list();
				//System.out.println("The list has size :" + prodlist.size());
				for (int j = 0; j < prodlist.size(); j++) 
				{
					//get supplier info
					Query sup_query = session.createQuery("from Supplier where SupplierId = :id ");
					sup_query.setParameter("id", ps.getSupplierId());
					List<?> suplist = sup_query.list();
					
					//System.out.println("We are at index :" + j);
					Product p = (Product) prodlist.get(j);
					p0 = (Product) prodlist.get(0);
					List<Supplier> p0_sups = p0.getSuppliers();
					//Supplier s = (Supplier) suplist.get(0);
					//System.out.println("Id: " + p.getProductId() + "is " + p.getProdName() + " has Supplier: " + s.getSupName() );
				}		
			}
		}
		List<Supplier> p0_sups = p0.getSuppliers();
		System.out.println("The product is :" + p0.getProdName());
		System.out.println("And the suppliers are: ");
		for (int j = 0; j < p0_sups.size(); j++) 
		{
			
			Supplier s0 = p0_sups.get(j);
			System.out.println(s0.getSupName());
			//System.out.println(p0_sups.get(j));
			
		}
		// fetch an agent and update their record
		session.getTransaction().commit();
		session.close();
	}
}
