package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerInfo {
	 
	private int customer_id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private String contact_num;
	
	public CustomerInfo(int customer_id, String username, String firstname, String lastname, String password, String email, String contact_num) {
		this.customer_id = customer_id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.contact_num =  contact_num;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
	    return firstname;
	}

	public void setFirstname(String firstname) {
	    this.firstname = firstname;
	}

	public String getLastname() {
	    return lastname;
	}

	public void setLastname(String lastname) {
	    this.lastname = lastname;
	}

	public String getContact_num() {
	    return contact_num;
	}

	public void setContact_num(String contact_num) {
	    this.contact_num = contact_num;
	}	

}
