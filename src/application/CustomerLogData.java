package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerLogData {
	
	static ObservableList<CustomerLog> log = FXCollections.observableArrayList();
	
	static ObservableList<CustomerLog> fetchCustomerLog(){
		String accNumber = " ";
    	String id = " ";
    	String user = " ";
    	String time = " ";
		try {
			DBConek link = new DBConek();
	        Connection conn = link.getConnection();
	        
	        String query = "SELECT * FROM customer_log";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	
	        	 accNumber = rs.getString("acc_num");
	        	 id = rs.getString("customer_id");
	        	 user = rs.getString("username");
	        	 time = rs.getString("time_log");
	        	
	        	CustomerLog custom_Log = new CustomerLog(accNumber, id, user, time);
	        	log.add(custom_Log);
	        }
	        conn.close();
	        ps.close();
	        rs.close();
	        System.out.println("Customer Log Established");
	        
	        //Delete when done testing.
	        System.out.println("\n\nFetch Account Number: " + accNumber);
	        System.out.println("Fetch Accoung ID: " + id);
	        System.out.println("Fetch Username: " + user);
	        System.out.println("Fetch Current Time: " + time);
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("\nCustomer Log Failed To Established");
		}
		return log;
	}
}
