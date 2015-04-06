package com.java.team1.travelexperts;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtilities {

	//create session factory and make it accessible
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;  //hibernate 4

	    static
	    {
	        try
	        {
//	          Configuration configuration = new Configuration();
	            Configuration configuration = new Configuration().configure();

	            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	        }
	        catch (HibernateException he)
	        {
	            System.err.println("Error creating Session: " + he);
	            throw new ExceptionInInitializerError(he);
	        }
	    }

	    public static SessionFactory getSessionFactory()
	    {
	        return sessionFactory;
	    } 

	    public static Session getSession()
	    {
	    	Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session session = sessionFactory.openSession();
            return session;
	    }
	    public void testConnection() throws Exception 
	    {

            //logger.info("Trying to create a test connection with the database.");
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session session = sessionFactory.openSession();
            //logger.info("Test connection with the database created successfuly.");
    }
} //end class
