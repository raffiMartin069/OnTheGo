package application;

public class CustomerLog {
	
	private String account_number;
	private String customer_ID;
	private String customer_username;
	private String time;
	public CustomerLog(String account_number, String customer_ID, String customer_username, String time) {
		super();
		this.account_number = account_number;
		this.customer_ID = customer_ID;
		this.customer_username = customer_username;
		this.time = time;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getCustomer_ID() {
		return customer_ID;
	}
	public void setCustomer_ID(String customer_ID) {
		this.customer_ID = customer_ID;
	}
	public String getCustomer_username() {
		return customer_username;
	}
	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


}
