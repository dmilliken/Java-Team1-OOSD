package com.java.team1.travelexperts;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;



/**
 * The persistent class for the agents database table.
 * Darcie Milliken
 * 
 */

@NamedQueries({
	// call this query when the user selects an agent from the drop down
	@NamedQuery(
			name = "findAgentByID",
			query = "SELECT a from Agent a where a.agentId = :agentId"
			),
			// call this query in the search box 
			@NamedQuery(
					name = "findAgentByLastName",
					query = "SELECT a from Agent a where a.agtLastName = :agtLastName"
					)
})

@Entity
@Table(name="agents")
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	private int agentId;

	private int agencyId;

	private String agtBusPhone;

	private String agtEmail;

	private String agtFirstName;

	private String agtLastName;

	private String agtMiddleInitial;

	private String agtPosition;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="agent")
	private List<Customer> customers;

	public Agent() 
	{	}

	public int getAgentId() 
	{
		return this.agentId;
	}

	public void setAgentId(int agentId) 
	{
		this.agentId = agentId;
	}

	public int getAgencyId() 
	{
		return this.agencyId;
	}

	public void setAgencyId(int agencyId) 
	{
		this.agencyId = agencyId;
	}

	public String getAgtBusPhone() 
	{
		return this.agtBusPhone;
	}

	public void setAgtBusPhone(String agtBusPhone) 
	{
		this.agtBusPhone = agtBusPhone;
	}

	public String getAgtEmail() 
	{
		return this.agtEmail;
	}

	public void setAgtEmail(String agtEmail) 
	{
		this.agtEmail = agtEmail;
	}

	public String getAgtFirstName() 
	{
		return this.agtFirstName;
	}

	public void setAgtFirstName(String agtFirstName) 
	{
		this.agtFirstName = agtFirstName;
	}

	public String getAgtLastName() 
	{
		return this.agtLastName;
	}

	public void setAgtLastName(String agtLastName) 
	{
		this.agtLastName = agtLastName;
	}

	public String getAgtMiddleInitial() 
	{
		return this.agtMiddleInitial;
	}

	public void setAgtMiddleInitial(String agtMiddleInitial) 
	{
		this.agtMiddleInitial = agtMiddleInitial;
	}

	public String getAgtPosition() 
	{
		return this.agtPosition;
	}

	public void setAgtPosition(String agtPosition) 
	{
		this.agtPosition = agtPosition;
	}

	public List<Customer> getCustomers() 
	{
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) 
	{
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) 
	{
		getCustomers().add(customer);
		customer.setAgent(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) 
	{
		getCustomers().remove(customer);
		customer.setAgent(null);

		return customer;
	} 

	// Override toString Method
	@Override
	public String toString() {
		return getAgtFirstName() + " " + getAgtLastName();
	}

	// This method will update an agent object and save to DB
	// pass form values into this method to update a row in the Agent take
	// Adapted from http://www.tutorialspoint.com/hibernate/hibernate_examples.htm
	public boolean updateAgent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String position, int agencyId)
	{

		//create session and transaction objects
		Session session = HibernateUtilities.getSession();
		Transaction tx = null;
		// change the object values
		try
		{
			tx = session.beginTransaction();
			Agent agt = (Agent)session.get(Agent.class, agentId); 
			agt.setAgtFirstName(agtFirstName);
			agt.setAgtMiddleInitial(agtMiddleInitial);
			agt.setAgtLastName(agtLastName);
			agt.setAgtBusPhone(agtBusPhone);
			agt.setAgtEmail(agtEmail);
			agt.setAgtPosition(position);
			agt.setAgencyId(agencyId);
			session.update(agt); 
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
	
	public boolean createAgent(String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String position, int agencyId)
	{
		//create session and transaction objects
		Session session = HibernateUtilities.getSession();
		Transaction tx = null;
		// change the object values
		try
		{
			tx = session.beginTransaction();
			Agent agt = new Agent();
			// the database handles the autoincrement for the agentID
			agt.setAgtFirstName(agtFirstName);
			agt.setAgtMiddleInitial(agtMiddleInitial);
			agt.setAgtLastName(agtLastName);
			agt.setAgtBusPhone(agtBusPhone);
			agt.setAgtEmail(agtEmail);
			agt.setAgtPosition(position);
			agt.setAgencyId(agencyId);
			session.save(agt); 
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
} //end class