package com.java.team1.travelexperts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
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

@SuppressWarnings("serial")
public class MainForm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtAgencyID;
	private JTextField txtBusPhone;
	private JTextField txtFirstName;
	private JTextField txtMiddleInitial;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
		
		JPanel panelAgents = new JPanel();
		panelAgents.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAgents.setBounds(180, 57, 824, 525);
		contentPane.add(panelAgents);
		panelAgents.setLayout(null);
		
		Panel PanelAgentData = new Panel();
		PanelAgentData.setBounds(10, 10, 390, 493);
		panelAgents.add(PanelAgentData);
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
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblFirstName, "2, 2, right, default");
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		panelAgentTextFields.add(txtFirstName, "6, 2, fill, default");
		
		JLabel lblMiddleInitial = new JLabel("Middle Initial:");
		lblMiddleInitial.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblMiddleInitial, "2, 4, right, default");
		
		txtMiddleInitial = new JTextField();
		txtMiddleInitial.setColumns(10);
		panelAgentTextFields.add(txtMiddleInitial, "6, 4, fill, default");
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblLastName, "2, 6, right, default");
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		panelAgentTextFields.add(txtLastName, "6, 6, fill, default");
		
		JLabel lblBusinessPhone = new JLabel("Business Phone:");
		lblBusinessPhone.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblBusinessPhone, "2, 8");
		
		txtBusPhone = new JTextField();
		txtBusPhone.setColumns(10);
		panelAgentTextFields.add(txtBusPhone, "6, 8, fill, default");
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblEmail, "2, 10, right, default");
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		panelAgentTextFields.add(txtEmail, "6, 10, fill, default");
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblPosition, "2, 12, right, default");
		
		txtPosition = new JTextField();
		txtPosition.setColumns(10);
		panelAgentTextFields.add(txtPosition, "6, 12, fill, default");
		
		JLabel lblAgencyId = new JLabel("Agency ID:");
		lblAgencyId.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelAgentTextFields.add(lblAgencyId, "2, 14, right, default");
		
		txtAgencyID = new JTextField();
		panelAgentTextFields.add(txtAgencyID, "6, 14, fill, default");
		txtAgencyID.setColumns(10);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelButtons.setBounds(12, 57, 156, 525);
		contentPane.add(panelButtons);
		panelButtons.setLayout(null);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(41, 13, 60, 20);
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(lblActions);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(23, 59, 101, 40);
		panelButtons.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(23, 112, 101, 40);
		panelButtons.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(23, 165, 101, 40);
		panelButtons.add(btnDelete);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(23, 218, 101, 40);
		panelButtons.add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(23, 459, 101, 40);
		panelButtons.add(btnExit);
		
		JPanel panelViews = new JPanel();
		panelViews.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelViews.setBounds(12, 10, 992, 41);
		contentPane.add(panelViews);
		panelViews.setLayout(null);
		
		JLabel lblSelectATable = new JLabel("Select a Table to View or Edit:");
		lblSelectATable.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSelectATable.setBounds(12, 10, 286, 16);
		panelViews.add(lblSelectATable);
		
		JButton btnAgents = new JButton("Agents");
		btnAgents.setBounds(270, 5, 97, 25);
		panelViews.add(btnAgents);
		
		JButton btnPackages = new JButton("Packages");
		btnPackages.setBounds(374, 5, 97, 25);
		panelViews.add(btnPackages);
		
		JLabel lblSearch = new JLabel("Search: ");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSearch.setBounds(676, 10, 79, 20);
		panelViews.add(lblSearch);
		
		textField = new JTextField();
		textField.setBounds(751, 8, 229, 22);
		panelViews.add(textField);
		textField.setColumns(10);
	}
}
