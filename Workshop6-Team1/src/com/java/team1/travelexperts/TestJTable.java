package com.java.team1.travelexperts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.util.List;

import oracle.net.aso.s;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class TestJTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private List<?> prodlist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJTable frame = new TestJTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestJTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		String[] columnNames = {"First Name",
				"Last Name",
				"Sport",
				"# of Years",
		"Vegetarian"};
		Object[][] data = {
				{"Kathy", "Smith",
					"Snowboarding", new Integer(5), new Boolean(false)},
					{"John", "Doe",
						"Rowing", new Integer(3), new Boolean(true)},
						{"Sue", "Black",
							"Knitting", new Integer(2), new Boolean(false)},
							{"Jane", "White",
								"Speed reading", new Integer(20), new Boolean(true)},
								{"Joe", "Brown",
									"Pool", new Integer(10), new Boolean(false)}
		};
		//instance table model
		DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
		};

		//getPackageProducts(p);
		table = new JTable(data, columnNames);
		//table.setModel(tableModel);
		//table.setCellSelectionEnabled(false);
		//table.setCellEditor(null);
		table.enableInputMethods(false);
		contentPane.add(table, BorderLayout.CENTER);
	}
	protected List<?> getPackageProducts(Package p)
	{
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
		{}
		finally
		{session.close();}
		return prodlist;
	}
}
