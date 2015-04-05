package com.java.team1.travelexperts;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class TestSelectWithHibernate {

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
		List result = session.createQuery( "from Agent" ).list();
		for ( Agent a : (List<Agent>) result ) {
			System.out.println( "Event (" + a.getAgtFirstName() + ") : " + a.getAgtLastName() );
		}
		session.getTransaction().commit();
		session.close();

	}
}
