package com.java.team1.travelexperts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

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
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
	private JComboBox cboPkgID;
	private JTextField txtPkgname;
	//private JTextField textField_1;
	JDateChooser dtStartDate;
	JDateChooser dtEndDate;
	private JTextField txtDescription;
	private JTextField txtPrice;
	private JTextField txtCommission;
	private JButton btnAddProducts;
	private JButton btnAdd; 
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnSave;
	private int selectedTab;
	Session session;

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
		setBounds(100, 100, 1055, 640);
		
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
		//cboSelectAgent.insertItemAt("", 0);
		cboSelectAgent.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				// when the user selects an agent, run this method 
				
				//get the selected agent object and fill textboxes with data 
				System.out.println(cboSelectAgent.getSelectedItem());
				Agent a = (Agent) cboSelectAgent.getSelectedItem();
				
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
				System.out.println("Agency Selected Index is " + cboAgencyID.getSelectedIndex());
				System.out.println("Agency Selected Item is " + cboAgencyID.getSelectedItem());
				//System.out.println("Agency Selected Object is " + cboAgencyID.getSelectedObjects().toString());
				
				// enable the sidebar Edit/Save/Delete buttons
				enableButtons();
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
		
		JLabel lblPackageId = new JLabel("Package ID:");
		lblPackageId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPackageInfo.add(lblPackageId, "2, 2, right, default");
		
		cboPkgID = new JComboBox();
		panelPackageInfo.add(cboPkgID, "4, 2, fill, default");
		
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
		layeredPane.setBounds(443, 50, 324, 413);
		tabPackages.add(layeredPane);
		layeredPane.setVisible(false);
		
		JButton btnSaveProducts = new JButton("Save Products to Package");
		btnSaveProducts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				// attempt to save the values to the DB
				// need to write the method 
				
				// show a message confirming success
				//JOptionPane.showMessageDialog
				
				// make the panel invisible 
				layeredPane.setVisible(false);
				
				// enable the add button
				btnAddProducts.setEnabled(true);
			}
		});
		btnSaveProducts.setBounds(86, 360, 186, 25);
		layeredPane.add(btnSaveProducts);
		
		JLabel lblAddProducts = new JLabel("Add Products");
		lblAddProducts.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddProducts.setBounds(100, 26, 159, 20);
		layeredPane.add(lblAddProducts);
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 75, 262, 239);
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblProductName, "2, 4, right, default");
		
		JComboBox cboProducts = new JComboBox();
		panel.add(cboProducts, "4, 4, fill, default");
		
		JLabel lblSupplier = new JLabel("Supplier:");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblSupplier, "2, 8, right, default");
		
		JComboBox cboSupplier = new JComboBox();
		panel.add(cboSupplier, "4, 8, fill, default");
		
		btnAddProducts = new JButton("Add Products");
		btnAddProducts.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				// This button will open the layered pane containing controls for adding products to a package
				layeredPane.setVisible(true);
				
				// disable the button
				btnAddProducts.setEnabled(false);
			}
		});
		btnAddProducts.setBounds(160, 411, 132, 25);
		tabPackages.add(btnAddProducts);
		
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
					// set the agent form fields to enabled
					enableAgentFormFields();
					// set the selected Agent to null
					cboSelectAgent.setSelectedIndex(-1);
					
				}
				else if (selectedTab == 1) // Packages Tab
				{
					// set the packages form fields to enabled
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
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(23, 165, 101, 40);
		panelButtons.add(btnDelete);
		
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
					//make sure all items are text, then update the agent object
					
					// call the Save Agent to DB method
					
				}
				else if (selectedTab == 1) // Packages Tab
				{
					// validate entries
					
					// call the Save Package to DB method
					
				} //end elseif
			} //end mouse click 
		});
		btnSave.setEnabled(false);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(23, 218, 101, 40);
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
		btnDelete.setEnabled(true);
	}
	
	protected void disableButtons() 
	{
		// method to turn the sidebar buttons to disabled
		// trigger when the user has saved a row. 
		//btnAdd.setEnabled(true);
		btnEdit.setEnabled(false);
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
	}

	protected void enableAgentFormFields() 
	{
		System.out.println("in the enableAgentFormFields() method");
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
		System.out.println("in the enablePackagesFormFields() method");
		// method to turn the package form fields to enabled
		// trigger when the user selects "Add" while on the Packages Tab
		cboPkgID.setEditable(true); 
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
				System.out.println("Agency # " + agcy.getAgencyId() + " is in " + agcy.getAgncyCity());
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

}
