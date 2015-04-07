package com.java.team1.travelexperts;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.*;

public class TestCalendar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestCalendar frame = new TestCalendar();
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
	public TestCalendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/** the day chooser */
		//JDayChooser dayChooser;

		/** indicates if weeks of year shall be visible */
		//boolean weekOfYearVisible = true;

		/** the locale */
		//Locale locale;

		/** the month chooser */
		//JMonthChooser monthChooser;

		/** the year chooser */
		//JYearChooser yearChooser;

		final JButton todayButton;

		final JButton nullDateButton;

		JCalendar startDate = new JCalendar();
		
	}

}
