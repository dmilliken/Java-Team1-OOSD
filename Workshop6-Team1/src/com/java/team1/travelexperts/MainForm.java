package com.java.team1.travelexperts;

// Darcie and Garima

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.SwingUtilities;

import java.awt.Panel;
import java.awt.Label;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.toedter.calendar.JCalendar;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLayeredPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MainForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox cboSelectAgent;
	private JTextField txtBusPhone;
	private JTextField txtFirstName;
	private JTextField txtMiddleInitial;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPosition;
	private JComboBox cboAgencyID;
	private JComboBox cboSelectPackage;
	private JTextField txtPkgname;
	private JDateChooser dtStartDate;
	private JDateChooser dtEndDate;
	private JTextField txtDescription;
	private JTextField txtPrice;
	private JTextField txtCommission;
	private JButton btnAddProducts;
	private JButton btnAdd; 
	private JButton btnEdit;
	private JButton btnInactive;
	private JButton btnSave;
	private int selectedTab;
	JLayeredPane layeredPaneReassignCustomers;
	JComboBox cboSelectNewAgent;
	Session session;
	private JComboBox cboProducts;
	private JComboBox cboSupplier;
	private List<?> all_products = Collections.emptyList();
	private List<?> package_products = Collections.emptyList();
	private List<?> product_suppliers = Collections.emptyList();
	private List<?> package_product_suppliers = Collections.emptyList();
	private JTable table;
	private DefaultTableModel model;
	private JButton btnSaveProducts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 640);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenu mnAgents = new JMenu("Agents");
		mnView.add(mnAgents);

		JMenu mnPackages = new JMenu("Packages");
		mnView.add(mnPackages);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(175, 51, 798, 518);
		tabbedPane.setFont( new Font( "Tahoma", Font.BOLD, 14 ) );
		contentPane.add(tabbedPane);

		JPanel tabAgents = new JPanel();
		tabbedPane.addTab("Agents", null, tabAgents, null);
		tabAgents.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabAgents.setLayout(null);

		Panel PanelAgentData = new Panel();
		PanelAgentData.setBounds(10, 10, 390, 493);
		tabAgents.add(PanelAgentData);
		PanelAgentData.setLayout(null);

		JLabel lblAgents = new JLabel("Agents");
		lblAgents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAgents.setBounds(148, 13, 60, 20);
		PanelAgentData.add(lblAgents);

		Panel panelAgentTextFields = new Panel();
		panelAgentTextFields.setBounds(10, 69, 370, 396);
		PanelAgentData.add(panelAgentTextFields);
		panelAgentTextFields.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblAgentId = new JLabel("Select Agent:");
		lblAgentId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblAgentId, "2, 2, right, default");

		// fill the list allAgents with data from the agent class

		List<?> allAgentsList = getAllTravelAgents();

		cboSelectAgent = new JComboBox(allAgentsList.toArray());
		cboSelectAgent.setEditable(true);
		//cboSelectAgent.insertItemAt("", 0);
		cboSelectAgent.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{

				// when the user selects an agent, run this method 

				//get the selected agent object and fill textboxes with data 
				//System.out.println(cboSelectAgent.getSelectedItem());
				Agent a = (Agent) cboSelectAgent.getSelectedItem();
				if (cboSelectAgent.getSelectedIndex() == -1 || cboSelectAgent.getSelectedIndex() == 0)
				{
					return;
				}
				//System.out.println("Name is " + a.getAgtFirstName());

				// fill the textboxes with the data
				txtFirstName.setText(a.getAgtFirstName());
				txtMiddleInitial.setText(a.getAgtMiddleInitial());
				txtLastName.setText(a.getAgtLastName());
				txtBusPhone.setText(a.getAgtBusPhone());
				txtPosition.setText(a.getAgtPosition());
				txtEmail.setText(a.getAgtEmail());
				cboAgencyID.setSelectedIndex(a.getAgencyId());

				// get data for agencies combobox
				//cboAgencyID.setModel(getAllTravelAgencies().toArray());
				//int agencyID = a.getAgencyId();
				//List<?> allAgencies = getAllTravelAgencies();

				// info we'll need later for saving the agency to the agent object 
				//System.out.println("Agency Selected Index is " + cboAgencyID.getSelectedIndex());
				//System.out.println("Agency Selected Item is " + cboAgencyID.getSelectedItem());
				//System.out.println("Agency Selected Object is " + cboAgencyID.getSelectedObjects().toString());

				// enable the required buttons
				enableButtons();

				btnInactive.setEnabled(true);
				//cboAgencyID.setEnabled(true);


				// refresh the frame
				SwingUtilities.updateComponentTreeUI(tabbedPane);
			}

		});
		panelAgentTextFields.add(cboSelectAgent, "6, 2, fill, default");

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblFirstName, "2, 4, right, default");

		txtFirstName = new JTextField();
		txtFirstName.setEditable(false);
		txtFirstName.setColumns(10);
		panelAgentTextFields.add(txtFirstName, "6, 4, fill, default");

		JLabel lblMiddleInitial = new JLabel("Middle Initial:");
		lblMiddleInitial.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblMiddleInitial, "2, 6, right, default");

		txtMiddleInitial = new JTextField();
		txtMiddleInitial.setEditable(false);
		txtMiddleInitial.setColumns(10);
		panelAgentTextFields.add(txtMiddleInitial, "6, 6, fill, default");

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblLastName, "2, 8, right, default");

		txtLastName = new JTextField();
		txtLastName.setEditable(false);
		txtLastName.setColumns(10);
		panelAgentTextFields.add(txtLastName, "6, 8, fill, default");

		JLabel lblBusinessPhone = new JLabel("Business Phone:");
		lblBusinessPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblBusinessPhone, "2, 10");

		txtBusPhone = new JTextField();
		txtBusPhone.setEditable(false);
		txtBusPhone.setColumns(10);
		panelAgentTextFields.add(txtBusPhone, "6, 10, fill, default");

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblEmail, "2, 12, right, default");

		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		panelAgentTextFields.add(txtEmail, "6, 12, fill, default");

		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblPosition, "2, 14, right, default");

		txtPosition = new JTextField();
		txtPosition.setEditable(false);
		txtPosition.setColumns(10);
		panelAgentTextFields.add(txtPosition, "6, 14, fill, default");

		JLabel lblAgencyId = new JLabel("Agency:");
		lblAgencyId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblAgencyId, "2, 16, right, default");

		cboAgencyID = new JComboBox(getAllTravelAgencies().toArray());
		panelAgentTextFields.add(cboAgencyID, "6, 16, fill, default");

		btnInactive = new JButton("Make Inactive");
		panelAgentTextFields.add(btnInactive, "6, 20");
		btnInactive.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{

				// Event handler for the make inactive button

				// if it's the agent tab, check if the selected agent has customers
				// if no, delete the agent/inactive
				// if yes, assign their customers to Agent # 1
				// or pop up a menu and let them pick (so make a new form to do this part) 

				// show the pane
				layeredPaneReassignCustomers.setVisible(true);
				// get the current agent
				Agent a = (Agent) cboSelectAgent.getSelectedItem();
				List<Customer> customers = a.getAgentCustomers(a);
				if(customers.isEmpty())
					// if empty, ask the user if they would like to delete the agent
					System.out.println("Customer List is empty");

				else 
				{
					//Agent has customers, ask user which agent they want to reassign the customers to
					System.out.println("Customer List is not empty");
					// if yes, assign their customers to Agent # 1
					// or pop up a menu and let them pick (so make a new form to do this part)
				}


			}
		});
		btnInactive.setEnabled(false);
		btnInactive.setFont(new Font("Tahoma", Font.BOLD, 14));

		layeredPaneReassignCustomers = new JLayeredPane();
		layeredPaneReassignCustomers.setBounds(443, 10, 324, 453);
		tabAgents.add(layeredPaneReassignCustomers);

		JLabel lblReassignCustomers = new JLabel("Reassign Customers");
		lblReassignCustomers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReassignCustomers.setBounds(81, 13, 194, 20);
		layeredPaneReassignCustomers.add(lblReassignCustomers);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// this button will add the inactive agent's customers to a new agent

				//get customer list from old agent
				Agent agtold = (Agent) cboSelectAgent.getSelectedItem();
				List<Customer> customers = agtold.getAgentCustomers(agtold);                   
				//select new agent
				Agent agtnew = (Agent) cboSelectNewAgent.getSelectedItem();

				// check to make sure the old agent is different from the new agent
				if (agtold.equals(agtnew))
				{
					System.out.println("The agents are the same");
					JOptionPane.showMessageDialog(tabbedPane, "The Agents you selected are the same! Please choose a different agent","Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					// agents are different
					Session session = null;
					Transaction tx = null;
					try
					{
						//set NEW agent id in old agent's customer list
						// get Session and transaction
						session = HibernateUtilities.getSession();
						tx = session.beginTransaction();
						for (int i = 0; i < customers.size(); i++)
						{
							System.out.println("Updating customer list to new agent");
							System.out.println("Before update");
							Customer oneCustomer = (Customer) customers.get(i);  
							System.out.println(oneCustomer.getCustomerId()+ " " +oneCustomer.getAgent().getAgentId());
							oneCustomer.setAgent(agtnew);
							System.out.println("After update");
							System.out.println(oneCustomer.getCustomerId()+ " " +oneCustomer.getAgent().getAgentId());
							session.update(oneCustomer); 
						}
						tx.commit();
						JOptionPane.showMessageDialog(tabbedPane, "Successfully updated the customers. ");

						// make panel invisible
						layeredPaneReassignCustomers.setVisible(false);

					}
					catch (HibernateException e) 
					{
						JOptionPane.showMessageDialog(tabbedPane, "Oops! There was a problem updating the agent's customers. ","Error", JOptionPane.ERROR_MESSAGE);
						if (tx!=null) tx.rollback();
						e.printStackTrace(); 
					}
					finally
					{
						session.close();
					}
				}

			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConfirm.setBounds(52, 297, 101, 40);
		layeredPaneReassignCustomers.add(btnConfirm);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// make this panel invisible
				layeredPaneReassignCustomers.setVisible(false);

			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(186, 297, 101, 40);
		layeredPaneReassignCustomers.add(btnCancel);

		cboSelectNewAgent = new JComboBox(allAgentsList.toArray());
		cboSelectNewAgent.setBounds(69, 78, 185, 22);
		layeredPaneReassignCustomers.add(cboSelectNewAgent);
		layeredPaneReassignCustomers.setVisible(false);

		JPanel tabPackages = new JPanel();
		tabbedPane.addTab("Packages", null, tabPackages, null);
		tabPackages.setLayout(null);

		JLabel lblPackages = new JLabel("Travel Packages");
		lblPackages.setBounds(130, 26, 159, 20);
		lblPackages.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabPackages.add(lblPackages);

		JPanel panelPackageInfo = new JPanel();
		panelPackageInfo.setBounds(10, 70, 370, 215);
		tabPackages.add(panelPackageInfo);
		panelPackageInfo.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblPackageId = new JLabel("Select Package:");
		lblPackageId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblPackageId, "2, 2, right, default");

		List<?> allPackagesList = getAllPackages();
		cboSelectPackage = new JComboBox(allPackagesList.toArray());
		cboSelectPackage.setEditable(true);
		cboSelectPackage.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				// event handler for the Select Package combo box 

				if (cboSelectPackage.getSelectedIndex() == -1 || cboSelectPackage.getSelectedIndex() == 0)
				{
					return;
				}
				//get the selected package object and fill textboxes with data 
				System.out.println(cboSelectPackage.getSelectedItem());
				Package pkg = (Package) cboSelectPackage.getSelectedItem();

				//System.out.println("Name is " + a.getAgtFirstName());

				// fill the textboxes with the data
				txtPkgname.setText(pkg.getPkgName());
				dtStartDate.setDate(pkg.getPkgStartDate());
				dtEndDate.setDate(pkg.getPkgEndDate());
				txtDescription.setText(pkg.getPkgDesc());

				//format the prices

				BigDecimal bd = pkg.getPkgBasePrice();
				bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				df.setMinimumFractionDigits(2);
				df.setGroupingUsed(false);

				//String result = df.format(bd);
				String PkgBasePrice = df.format(bd);
				String PkgCommission = df.format(pkg.getPkgAgencyCommission());

				txtPrice.setText(PkgBasePrice);
				txtCommission.setText(PkgCommission);

				// enable the required buttons
				enableButtons();
				btnAddProducts.setEnabled(true);

				// get the data for the table model

				// Problem: We need to deal with the case when the model will be empty
				if (!pkg.getProductsSuppliers().isEmpty())
				{
					package_products = pkg.getPackageProducts(pkg);
					//package_product_suppliers = pkg.getPackageProductSuppliers(package_products);
					package_product_suppliers = pkg.getPackageProductSuppliers(pkg);
				}
				else
				{
					//clear the model
					package_products = Collections.emptyList();
					package_product_suppliers = Collections.emptyList();

					for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
						model.removeRow(i);
					}
					model.setRowCount(0);
				}

				System.out.println("Before: products has " + package_products.size() + " elements.");
				model = getPkgTableModel(package_products,package_product_suppliers);
				table.setModel(model);

				// refresh the frame
				SwingUtilities.updateComponentTreeUI(tabbedPane);
			}
		});
		panelPackageInfo.add(cboSelectPackage, "4, 2, fill, default");

		JLabel lblPackageName = new JLabel("Package Name:");
		lblPackageName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblPackageName, "2, 4, right, default");

		txtPkgname = new JTextField();
		txtPkgname.setEditable(false);
		txtPkgname.setColumns(10);
		panelPackageInfo.add(txtPkgname, "4, 4, fill, default");

		JLabel lblStartDate = new JLabel("Start Date:");
		lblStartDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblStartDate, "2, 6, right, default");

		dtStartDate = new JDateChooser();
		dtStartDate.setEnabled(false);
		panelPackageInfo.add(dtStartDate, "4, 6, fill, fill");

		//JCalendar startDate = new JCalendar();
		//panel.add(startDate, "4, 6, fill, default");

		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblEndDate, "2, 8, right, default");

		dtEndDate = new JDateChooser();
		dtEndDate.setEnabled(false);
		panelPackageInfo.add(dtEndDate, "4, 8, fill, fill");

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblDescription, "2, 10, right, default");

		txtDescription = new JTextField();
		txtDescription.setEditable(false);
		txtDescription.setColumns(10);
		panelPackageInfo.add(txtDescription, "4, 10, fill, default");

		JLabel lblRetailPrice = new JLabel("Retail Price:");
		lblRetailPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblRetailPrice, "2, 12, right, default");

		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		panelPackageInfo.add(txtPrice, "4, 12, fill, default");

		JLabel lblCommission = new JLabel("Commission:");
		lblCommission.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblCommission, "2, 14, right, default");

		txtCommission = new JTextField();
		txtCommission.setEditable(false);
		txtCommission.setColumns(10);
		panelPackageInfo.add(txtCommission, "4, 14, fill, default");

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(407, 50, 360, 413);
		tabPackages.add(layeredPane);
		layeredPane.setVisible(false);

		JLabel lblAddProducts = new JLabel("Add Products");
		lblAddProducts.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddProducts.setBounds(100, 26, 159, 20);
		layeredPane.add(lblAddProducts);

		JPanel panel = new JPanel();
		panel.setBounds(12, 75, 348, 239);
		layeredPane.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblProductName, "2, 4, right, default");

		all_products = getAllProducts();
		cboProducts = new JComboBox(all_products.toArray());

		cboSupplier = new JComboBox(product_suppliers.toArray());
		panel.add(cboSupplier, "4, 8, fill, default");

		cboProducts.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Product selectedProduct = (Product) cboProducts.getSelectedItem();
				product_suppliers = getProductSuppliers(selectedProduct);

				if (!product_suppliers.isEmpty())
				{
					DefaultComboBoxModel model = new DefaultComboBoxModel( product_suppliers.toArray() );
					cboSupplier.setModel( model );
				}

				panel.add(cboSupplier, "4, 8, fill, default");

				btnSaveProducts.setEnabled(true);
				// refresh the frame
				SwingUtilities.updateComponentTreeUI(tabbedPane);
			}
		});
		panel.add(cboProducts, "4, 4, fill, default");

		JLabel lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblSupplier, "2, 8, right, default");

		btnSaveProducts = new JButton("Save Products to Package");
		btnSaveProducts.setEnabled(false);
		panel.add(btnSaveProducts, "4, 12");

		JButton btnCancelSaveProducts = new JButton("Cancel");
		btnCancelSaveProducts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// cancel saving a product to a package
				layeredPane.setVisible(false);
				btnAddProducts.setEnabled(true);
				cboSupplier.removeAllItems();
			}
		});
		panel.add(btnCancelSaveProducts, "4, 14");
		btnSaveProducts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// save product/supplier to database table packages_product_supplier
				Product selectedProduct = (Product) cboProducts.getSelectedItem();
				Supplier selectedSupplier = (Supplier) cboSupplier.getSelectedItem();
				Package selectedPackage =(Package) cboSelectPackage.getSelectedItem();
				
				// SELECT p FROM ProductsSupplier p
				int ProductSupplierId = ProductsSupplier.getProductSupplier(selectedProduct.getProductId(), selectedSupplier.getSupplierId());
				if (ProductSupplierId != -1)
				{
					if (Package.createPkgProdSup(selectedPackage.getPackageId(), ProductSupplierId))
					{
						// if the insert was successful 
						// show a message confirming success
						JOptionPane.showMessageDialog(tabbedPane, "Successfully added the product! ");

						// make the panel invisible 
						layeredPane.setVisible(false);
						// enable buttons
						btnAddProducts.setEnabled(true);
						cboSupplier.removeAllItems();
						
						//update the model
						//model.insertRow(model.getRowCount()+1, new String[] {selectedProduct.getProdName() , selectedSupplier.getSupName()});
						 model.addRow(new String[] {selectedProduct.getProdName() , selectedSupplier.getSupName()});
						// values.add(new String[] {p.getProdName() , s.getSupName()});
						
						//refresh the frame and JTable 
						SwingUtilities.updateComponentTreeUI(tabbedPane);
						model.fireTableDataChanged();
						table.repaint();
					}
				}
				
				
			}
		});

		//panel.add(cboSupplier, "4, 8, fill, default");

		btnAddProducts = new JButton("Add Products");
		btnAddProducts.setBounds(157, 438, 132, 25);
		btnAddProducts.setEnabled(false);
		btnAddProducts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// don't allow the user to click on it if it's disabled
				if (!btnAddProducts.isEnabled()) 
				{
					return;
				}
				// This button will open the layered pane containing controls for adding products to a package
				layeredPane.setVisible(true);

				// disable the button
				btnAddProducts.setEnabled(false);
			}
		});
		tabPackages.add(btnAddProducts);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 303, 360, 111);
		tabPackages.add(scrollPane);

		// data for the table
		String[] columnNames = new String[] {"Product", "Supplier"};
		Object[][] products = new Object[][] 
				{
				//fill with data from the products supplier list
				};

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelButtons.setBounds(12, 57, 156, 525);
		contentPane.add(panelButtons);
		panelButtons.setLayout(null);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(41, 13, 60, 20);
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblActions);

		btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// this is the event handler for btnAdd

				//check to see which tab is open 
				selectedTab = tabbedPane.getSelectedIndex();
				//System.out.println(selectedTab);
				// Tab 0 is the agent tab
				// Tab 1 is the packages tab

				if (selectedTab == 0) //Agents Tab
				{
					//System.out.println(cboSelectAgent.getSelectedIndex());
					// set the agent form fields to enabled
					enableAgentFormFields();
					resetAgentForm();

					btnEdit.setEnabled(false);
					// set the selected Agent to null
					cboSelectAgent.setSelectedIndex(0);
					//cboSelectAgent.setEnabled(false);
					//cboSelectAgent.setEditable(false);
					cboAgencyID.setEnabled(true);
					btnInactive.setEnabled(false);
					//btnInactive.setVisible(false);

				}
				else if (selectedTab == 1) // Packages Tab
				{
					// set the packages form fields to enabled
					resetPackageForm();
					enablePackagesFormFields();

				} //end elseif
				//enable the save button
				btnSave.setEnabled(true);
			} //end mouseclick
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(23, 59, 101, 40);
		panelButtons.add(btnAdd);

		btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//event handler for the edit button

				//detect which tab is open
				selectedTab = tabbedPane.getSelectedIndex();
				//System.out.println(selectedTab);
				// Tab 0 is the agent tab
				// Tab 1 is the packages tab

				if (selectedTab == 0) //Agents Tab
				{
					// set the agent form fields to enabled
					enableAgentFormFields();

				}
				else if (selectedTab == 1) // Packages Tab
				{
					// set the packages form fields to enabled
					enablePackagesFormFields();
				} //end elseif

			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(23, 112, 101, 40);
		panelButtons.add(btnEdit);

		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedTab = tabbedPane.getSelectedIndex();

				if (selectedTab == 0) //Agents Tab
				{
					// validate entries
					// System.out.println("Selected index is " + cboSelectAgent.getSelectedIndex() );

					if (Validator.isPresent(txtFirstName, "First Name") &&
							Validator.isPresent(txtLastName, "Last Name") &&
							Validator.isPresent(txtBusPhone, "Phone") &&
							Validator.isPresent(txtEmail, "E-Mail") &&
							Validator.isPresent(txtPosition, "Position"))
					{
						//System.out.println("Agent is Valid!");
						//}					
						// detect if the agent is new or existing
						if (cboSelectAgent.getSelectedIndex() == -1)
						{
							// new agent
							//System.out.println("creating a new agent");
							Agent a = new Agent();


							// get the text field values
							String AgentFirstName = txtFirstName.getText();
							String AgentMiddleInitial = txtMiddleInitial.getText();
							String AgentLastName = txtLastName.getText();
							String AgentPhone = txtBusPhone.getText();
							String AgentEmail = txtEmail.getText();
							String AgentPosition = txtPosition.getText();
							int agencyID = cboAgencyID.getSelectedIndex();

							if (a.createAgent(AgentFirstName, AgentMiddleInitial, AgentLastName, AgentPhone, AgentEmail, AgentPosition, agencyID))
							{
								// show a success message to the user
								JOptionPane.showMessageDialog(tabbedPane, "Created the agent successfully! ");

								// disable the form fields and refresh the pane
								disableAgentForm();

								// reset the sidebar buttons
								disableButtons();

							}
							else
							{
								JOptionPane.showMessageDialog(tabbedPane, "Oops, there was an error saving that agent. ");
							}
						} //end new agent if
						else //edit existing agent
						{

							// then we are editing an existing agent 
							//System.out.println("editing an existing agent");

							// get the current agent 
							Agent a = (Agent) cboSelectAgent.getSelectedItem();

							//get the new values and store

							String newAgentFirstName = txtFirstName.getText();
							String newAgentMiddleInitial = txtMiddleInitial.getText();
							String newAgentLastName = txtLastName.getText();
							String newAgentPhone = txtBusPhone.getText();
							String newAgentEmail = txtEmail.getText();
							String newAgentPosition = txtPosition.getText();
							int newAgency = cboAgencyID.getSelectedIndex();
							//System.out.println("Getting the new values:"+ newAgentFirstName);

							// signature public void updateAgent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String position, int agencyId)
							if (a.updateAgent(a.getAgentId(), newAgentFirstName, newAgentMiddleInitial, newAgentLastName, newAgentPhone, newAgentEmail, newAgentPosition, newAgency))
							{
								//show a success message to user
								//System.out.println("Saved the new agent! ");
								JOptionPane.showMessageDialog(tabbedPane, "Saved the agent successfully! ");

								//reset the form to it's default state
								resetAgentForm();

								// reset the sidebar buttons
								disableButtons();

							}
							else
							{
								JOptionPane.showMessageDialog(tabbedPane, "Oops, there was an error saving that agent. ");
							}

						}
					}


				}
				else if (selectedTab == 1) // Packages Tab
				{
					// validate entries
					if (Validator.isPresent(txtPkgname, "Package Name") && 
							Validator.isPresent(txtDescription, "Description") &&
							Validator.isPresent(txtPrice, "Price") &&
							Validator.isPresent(txtCommission, "Commission") &&
							Validator.dateCompare(dtStartDate.getDate(), dtEndDate.getDate()) &&
							Validator.isPositiveDouble(txtPrice, "Price") &&
							Validator.isPositiveDouble(txtCommission, "Commission") &&
							Validator.isValidPrice(txtPrice, txtCommission, "Price", "Commission")
							)
					{
						//System.out.println("Valid!");
						// valid, so now determine if it's a new or existing package 
						if (cboSelectPackage.getSelectedIndex() == -1)
						{
							// create new package
							Package newPkg = new Package();
							if (newPkg.createPackage(txtPkgname.getText(),dtStartDate.getDate(), dtEndDate.getDate(), txtDescription.getText(),BigDecimal.valueOf(Double.parseDouble(txtPrice.getText())),BigDecimal.valueOf(Double.parseDouble(txtCommission.getText()))))
							{
								JOptionPane.showMessageDialog(tabbedPane, "Successfully created the package! ");

								disablePackageForm();

								disableButtons();
							}
						}
						else
						{
							// update existing package
							// get the current agent 
							Package pkg = (Package) cboSelectPackage.getSelectedItem();

							if (pkg.updatePackage(pkg.getPackageId(),txtPkgname.getText(),dtStartDate.getDate(), dtEndDate.getDate(), txtDescription.getText(),BigDecimal.valueOf(Double.parseDouble(txtPrice.getText())),BigDecimal.valueOf(Double.parseDouble(txtCommission.getText()))))
							{
								JOptionPane.showMessageDialog(tabbedPane, "Successfully saved the package! ");

								disablePackageForm();

								disableButtons();
							}
							else
							{
								JOptionPane.showMessageDialog(tabbedPane, "Oops, there was an error when saving the package. ");
							}

						}


					}
					else
					{
						//System.out.println("Invalid!");
					}

				} //end elseif
			} //end mouse click 


		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(23, 165, 101, 40);
		panelButtons.add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(23, 459, 101, 40);
		panelButtons.add(btnExit);

		JButton btnResetForm = new JButton("Cancel");
		btnResetForm.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// reset the form
				selectedTab = tabbedPane.getSelectedIndex();

				if (selectedTab == 0) //Agents Tab
				{
					resetAgentForm();
					disableAgentForm();		
					layeredPaneReassignCustomers.setVisible(false);
				}
				else if (selectedTab == 1) //Packages Tab
				{
					resetPackageForm();
					disablePackageForm();	
					layeredPane.setVisible(false);
				}
				disableButtons();

			}
		});
		btnResetForm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnResetForm.setBounds(23, 218, 101, 40);
		panelButtons.add(btnResetForm);

		JPanel panelViews = new JPanel();
		panelViews.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelViews.setBounds(12, 10, 992, 559);
		contentPane.add(panelViews);
		panelViews.setLayout(null);

		JLabel lblSelectATable = new JLabel("Welcome! Select a Table to View or Edit:");
		lblSelectATable.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSelectATable.setBounds(12, 12, 377, 16);
		panelViews.add(lblSelectATable);

		JLabel lblSearch = new JLabel("Search: ");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearch.setBounds(676, 10, 79, 20);
		panelViews.add(lblSearch);

		textField = new JTextField();
		textField.setBounds(751, 8, 229, 22);
		panelViews.add(textField);
		textField.setColumns(10);
	}

	protected void enableButtons() 
	{
		// method to turn the sidebar buttons to enabled
		// trigger when the user selects a row to edit
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		btnSave.setEnabled(true);
		btnInactive.setEnabled(true);
	}

	protected void disableButtons() 
	{
		// method to turn the sidebar buttons to disabled
		// trigger when the user has saved a row. 
		//btnAdd.setEnabled(true);
		btnEdit.setEnabled(false);
		btnSave.setEnabled(false);
		btnInactive.setEnabled(false);
	}

	protected void enableAgentFormFields() 
	{
		//System.out.println("in the enableAgentFormFields() method");
		// method to turn the agent form fields to enabled
		// trigger when the user selects "Add" while on the Agents Tab
		cboSelectAgent.setEnabled(true);
		txtFirstName.setEditable(true);
		txtMiddleInitial.setEditable(true);
		txtLastName.setEditable(true);
		txtBusPhone.setEditable(true);
		txtEmail.setEditable(true);
		txtPosition.setEditable(true);
		cboAgencyID.setEnabled(true);
		//cboAgencyID.setEditable(true);
	}

	protected void enablePackagesFormFields() 
	{
		//System.out.println("in the enablePackagesFormFields() method");
		// method to turn the package form fields to enabled
		// trigger when the user selects "Add" while on the Packages Tab
		cboSelectPackage.setEditable(true); 
		txtPkgname.setEditable(true);
		dtStartDate.setEnabled(true);
		dtEndDate.setEnabled(true);
		txtDescription.setEditable(true);
		txtPrice.setEditable(true);
		txtCommission.setEditable(true);

	}

	protected List<?> getAllTravelAgencies()
	{
		//begin session and transaction
		session = HibernateUtilities.getSession();

		Query queryFindAllAgencies = session.getNamedQuery("Agency.findAll");
		List<?> allAgenciesList = queryFindAllAgencies.list();

		if (!allAgenciesList.isEmpty()) 
		{
			// if the list of agents is nonempty, loop through it
			for (int i = 0; i < allAgenciesList.size(); i++) 
			{
				Agency agcy = (Agency) allAgenciesList.get(i);
				//System.out.println("Agency # " + agcy.getAgencyId() + " is in " + agcy.getAgncyCity());
			}
		}
		session.close();

		//insert a null agency
		allAgenciesList.add(0,null);

		return allAgenciesList;	
	}
	protected List<?> getAllTravelAgents()
	{
		// refactor this section if time permits 
		//begin session and transaction
		session = HibernateUtilities.getSession();

		Query queryFindAllAgents = session.getNamedQuery("Agent.findAll");
		List<?> allAgentsList = queryFindAllAgents.list();

		session.close();

		//insert a null agent
		//Agent nullAgent = new Agent();

		allAgentsList.add(0, null);

		return allAgentsList;	
	}

	protected void resetAgentForm()
	{
		// after adding, saving, or deleting, call this method to reset the form

		//cboSelectAgent.setSelectedItem(-1);
		cboSelectAgent.setSelectedIndex(-1);
		txtFirstName.setText("");
		txtMiddleInitial.setText("");
		txtLastName.setText("");
		txtBusPhone.setText("");
		txtPosition.setText("");
		txtEmail.setText("");
		cboAgencyID.setSelectedIndex(0);

	}
	protected void disableAgentForm()
	{
		cboSelectAgent.setEnabled(true);
		txtFirstName.setEditable(false);
		txtMiddleInitial.setEditable(false);
		txtLastName.setEditable(false);
		txtBusPhone.setEditable(false);
		txtPosition.setEditable(false);
		txtEmail.setEditable(false);
		cboAgencyID.setEnabled(false);
	}

	// methods for the packages tab

	protected List<?> getAllPackages()
	{
		// refactor this section if time permits 
		//begin session and transaction
		session = HibernateUtilities.getSession();

		Query queryFindAllPackages = session.getNamedQuery("Package.findAll");
		List<?> allPkgList = queryFindAllPackages.list();

		session.close();

		//insert a null agent
		//Agent nullAgent = new Agent();

		allPkgList.add(0, null);

		return allPkgList;	
	}

	protected void disablePackageForm()
	{
		txtPkgname.setEditable(false);
		dtStartDate.setEnabled(false);
		dtEndDate.setEnabled(false);
		txtCommission.setEditable(false);
		txtPrice.setEditable(false);
		txtDescription.setEditable(false);
	}

	protected void resetPackageForm()
	{
		// after adding, saving, or deleting, call this method to reset the form

		cboSelectPackage.setSelectedIndex(0);
		txtPkgname.setText("");
		dtStartDate.setDate(null);
		dtEndDate.setDate(null);
		txtCommission.setText("");
		txtPrice.setText("");
		txtDescription.setText("");

		// clear the model
		package_products = Collections.emptyList();
		package_product_suppliers = Collections.emptyList();

		for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			model.removeRow(i);
		}
		model.setRowCount(0);
	}

	protected List<?> getAllProducts()
	{
		//Garima
		//begin session and transaction
		session = HibernateUtilities.getSession();

		Query queryFindAllProducts = session.getNamedQuery("Product.findAll");
		List<?> allPrdctList = queryFindAllProducts.list();

		session.close();

		//insert a null agent
		//Agent nullAgent = new Agent();

		allPrdctList.add(0, null);

		return allPrdctList;      
	}

	protected List<?> getProductSuppliers(Product p)
	{
		return p.getSuppliers();
	}

	public static DefaultTableModel getPkgTableModel(List<?> products, List<?> suppliers)
	{
		// http://stackoverflow.com/questions/11095802/populate-jtable-using-list

		List<String> columns = new ArrayList<String>();
		columns.add("Product");
		columns.add("Supplier");
		List<String[]> values = new ArrayList<String[]>();

		//deal with the case where the model will be empty
		//System.out.println("products has " + products.size() +" elements");
		//System.out.println("suppliers has " + suppliers.size() +" elements");
		if (products.isEmpty())
		{
			values.clear();
			values.add(new String[] {" ", " "});
		}
		else
		{
			for (int i = 0; i<products.size();i++)
			{	
				if (products.get(i) != null)
				{
					Product p = (Product) products.get(i);
					Supplier s = (Supplier) suppliers.get(i);
					values.add(new String[] {p.getProdName() , s.getSupName()});
				}
			}
		}

		DefaultTableModel model = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());

		return model;
	}

}
