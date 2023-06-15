package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TransactionListData {
	
	static ObservableList<Transaction> TransctionList = FXCollections.observableArrayList();
	
	static ObservableList<Transaction> fetchConfirmData() {
		
		  	String trans_id = " ";
	    	String cx_id = " ";    
	        String fromTo = " ";
	        String goTo = " ";
	        String date_To_day = " ";
	        String t_ime = " ";
	        String busType = " ";
	        String fare_cost = " ";
	        String book_cost = " ";
	        String tCost = " ";
	        String pymnt_no = " ";
	        String refferNum = " ";
	        String status = " ";
	    
		//TransctionList = FXCollections.observableArrayList();
	    try {
	        DBConek link = new DBConek();
	        Connection conn = link.getConnection();
	        
	        String query = "SELECT * FROM transact";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	             trans_id = rs.getString("transact_id");
	             cx_id = rs.getString("customer_id");
	             fromTo = rs.getString("from_loc");
	             goTo = rs.getString("to_loc");
	             date_To_day = rs.getString("date");
	             t_ime = rs.getString("time");
	             busType = rs.getString("btype");
	             fare_cost = rs.getString("fare");
	             book_cost = rs.getString("book_fee");
	             tCost = rs.getString("total");
	             pymnt_no = rs.getString("gach_num");
	             refferNum = rs.getString("ref_num");
	             status = rs.getString("payment_status");
	            
	            Transaction trans_List = new Transaction(trans_id, cx_id, fromTo, goTo, date_To_day, t_ime, busType, fare_cost, book_cost, tCost, pymnt_no, refferNum, status);
	            
	            TransctionList.add(trans_List);
	        }
	        
	        System.out.println("\n\nFetch Transaction ID: " + trans_id);
	        System.out.println("Fetch Customer ID: " + cx_id);
	        System.out.println("Fetch Starting Point: " + fromTo);
	        System.out.println("Fetch End Point: " + goTo);
	        System.out.println("Fetch Date: " + date_To_day);
	        System.out.println("Fetch Time: " + t_ime);
	        System.out.println("Fetch Bus Type AC/Non-AC: " + busType);
	        System.out.println("Fetch Fare: " + fare_cost);
	        System.out.println("Fetch Booking Fee/Tax: " + book_cost);
	        System.out.println("Fetch Total: " + tCost);
	        System.out.println("Fetch GCash Number: " + pymnt_no);
	        System.out.println("Fetch Reference Number: " + refferNum);
	        System.out.println("Fetch Payment Status: " + status);
	        
	    } catch (SQLException e) {
	        System.out.println(e);
	    }   
	    return TransctionList;
	}
}
