package com.java.team1.travelexperts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// - - - - - - - - - - - - - - - - - - - - - - - - 
// Darcie Milliken
// This file provides a connection to the travel experts mysql database.
// - - - - - - - - - - - - - - - - - - - - - - - - 

public class TravelExpertsDB {

	public static Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");			
			return conn;
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	} // end method	
	


}//end class
	


