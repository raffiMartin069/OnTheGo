package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConek {
	
	//This class creates connection to Database.
	public  Connection dbLink;

	public Connection getConnection() {
		String DBname = "monitoring";
		String DBUser = "root";
		String DBAccessKey = "12345ning";
		String  URL  =  "jdbc:mysql://localhost/" + DBname;


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbLink = DriverManager.getConnection(URL, DBUser, DBAccessKey);
			System.out.println("Connected to database successfully");

		}catch(Exception e) {
			e.printStackTrace();
		}
		return dbLink;
	}
	
}