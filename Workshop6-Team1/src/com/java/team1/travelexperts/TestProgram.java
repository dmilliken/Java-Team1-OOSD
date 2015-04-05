package com.java.team1.travelexperts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TestProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Session session = HibernateUtilities.getSessionFactory().openSession();
//		session.close();
//		
		AnnotationConfiguration config = new AnnotationConfiguration();
		// add annotated classes
		config.addAnnotatedClass(Agency.class);
		config.configure("hibernate.cfg.xml"); // read the configuration xml file 
		
		//make sql statements, and execute them to the database
		// the next line creates a table from the annotated class above
		//new SchemaExport(config).create(true,true); //pass the config object
		
		// - - - - - - -
		// exmaple to create a new object and insert
		
		// to save an object to a table we need a session factory and a session
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		// now associate the session with an object
		// tell hibernate the object exists by using a transaction
		
		session.beginTransaction();
		
		// create the object 
		Agency agency = new Agency();
		agency.setAgencyId(11);
		agency.setAgncyProv("BC");
		
		session.save(agency); //pass the object to the session
		session.getTransaction().commit(); //this saves the object to the DB 
		
		
		
	}

}
