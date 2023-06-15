/*package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerList {

	ObservableList<Transaction> TransctionList = FXCollections.observableArrayList();
	
	ObservableList<Transaction> fetchConfirmData() {
	    
		TransctionList = FXCollections.observableArrayList();
	    try {
	        DBConek link = new DBConek();
	        Connection conn = link.getConnection();
	        
	        String query = "SELECT * FROM transact";
	        PreparedStatement ps = conn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            String trans_id = rs.getString("transact_id");
	            String cx_id = rs.getString("customer_id");
	            String fromTo = rs.getString("from_loc");
	            String goTo = rs.getString("to_loc");
	            String date_To_day = rs.getString("date");
	            String t_ime = rs.getString("time");
	            String busType = rs.getString("btype");
	            String fare_cost = rs.getString("fare");
	            String book_cost = rs.getString("book_fee");
	            String tCost = rs.getString("total");
	            String pymnt_no = rs.getString("gach_num");
	            String refferNum = rs.getString("ref_num");
	            String status = rs.getString("payment_status");
	            
	            Transaction trans_List = new Transaction(trans_id, cx_id, fromTo, goTo, date_To_day, t_ime, busType, fare_cost, book_cost, tCost, pymnt_no, refferNum, status);
	            
	            TransctionList.add(trans_List);
	        }
	    } catch (SQLException e) {
	        System.out.println(e);
	    }   
	    return TransctionList;
	
}*/
