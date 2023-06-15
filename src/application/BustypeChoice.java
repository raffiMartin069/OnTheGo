package application;

import java.io.IOException;
import java.time.format.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BustypeChoice implements Initializable {
	 private static final String URL = "jdbc:mysql://localhost:3306/monitoring";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "12345ning";
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private TableView<Bus> table;

	@FXML
	private TableColumn<Bus, String> date;

	@FXML
	private TableColumn<Bus, String> bus_Type;

	@FXML
	private TableColumn<Bus, String> bus_Cap;

	@FXML
	private TableColumn<Bus, String> bus_Stat;

	@FXML
	private TableColumn<Bus, String> bus_no;

	@FXML
	private TableColumn<Bus, String> depart_time;

	@FXML
	private TableColumn<Bus, String> route_from;

	@FXML
	private TableColumn<Bus, String> route_to;

	@FXML
	private Button delete;

	
	 @FXML
	 private TextField search_customer;

	@FXML
    private TableColumn<Transaction, String> busType_Trans;
	
	@FXML
    private TableColumn<Transaction, String> cust_Id;
	
	@FXML
    private TableColumn<Transaction, String> date_Trans;
	
	@FXML
    private TableColumn<Transaction, String> fare_Trans;

    @FXML
    private TableColumn<Transaction, String> from_Trans;
    
    @FXML
    private TableColumn<Transaction, String> gcashNum_Trans;

    @FXML
    private TableColumn<Transaction, String> ref_number_Trans;
    
    @FXML
    private TableColumn<Transaction, String> tax_Trans;

    @FXML
    private TableColumn<Transaction, String> time_Trans;
    
    @FXML
    private TableColumn<Transaction, String> to_Trans;
    
    @FXML
    private TableColumn<Transaction, String> total_Trans;
    
    @FXML
    private TableColumn<Transaction, String> trans_ID;
    
    @FXML
    private TableColumn<Transaction, String> trans_Status;
    
    @FXML
    private Button confirm_Status;
    
    @FXML
    private TableView<Transaction> trans_Table;
	
	@FXML
	private TextField seat_capacity;

	@FXML
	private DatePicker date_Picker;

	@FXML
	private TextField bus_num;

	@FXML
	private Button addBus;

	@FXML
	private Button CONFIRM_bttn;

	@FXML
	private Button CUSTOMER_bttn;

	@FXML
	private Button BOOKED_bttn;

	@FXML
	private Button EDIT_bttn;

	@FXML
	private AnchorPane confirm_form;

	@FXML
	private AnchorPane customer_form;

	@FXML
	private AnchorPane booked_form;

	@FXML
	private AnchorPane edit_form;

	@FXML
	private ChoiceBox<String> fromchc;

	@FXML
	private ChoiceBox<String> tochc;

	@FXML
	private ChoiceBox<String> bustype_chc;

	@FXML
	private ChoiceBox<String> timechc;

	@FXML
	private TextField search_bar;

	@FXML
	private TextField Driver;

	@FXML
	private Label warningMessage;

	@FXML
	private Label date_Time;

	@FXML
	private Button search_Button;

	@FXML
    private TextField search_transid;
	
	@FXML
    private TableColumn<Transaction, String> busType_Trans1;
	
	@FXML
    private TableColumn<Transaction, String> cust_Id1;
	
	@FXML
    private TableColumn<Transaction, String> date_Trans1;
	
	@FXML
    private TableColumn<Transaction, String> fare_Trans1;

    @FXML
    private TableColumn<Transaction, String> from_Trans1;
    
    @FXML
    private TableColumn<Transaction, String> gcashNum_Trans1;

    @FXML
    private TableColumn<Transaction, String> ref_number_Trans1;
    
    @FXML
    private TableColumn<Transaction, String> tax_Trans1;

    @FXML
    private TableColumn<Transaction, String> time_Trans1;
    
    @FXML
    private TableColumn<Transaction, String> to_Trans1;
    
    @FXML
    private TableColumn<Transaction, String> total_Trans1;
    
    @FXML
    private TableColumn<Transaction, String> trans_ID1;
    
    @FXML
    private TableColumn<Transaction, String> trans_Status1;
    
	@FXML
	private TableView<Transaction> book_Table;
	
	@FXML
	private TableColumn<Bus, String> driver_name;

	@FXML
    private TableView<Transaction> trans_Table1;
	
	private String[] from = { "Cebu City", "Argao" };

	private String[] to = { "Cebu City", "Argao" };

	private String[] type = { "AIRCONDITIONED", "NON-AIRCONDITIONED" };

	private String[] time = { "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "4 PM",
			"5 PM", "6 PM", "7 PM", "8 PM" };

	ObservableList<Bus> BusList = FXCollections.observableArrayList();

	
	ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
	
	private ObservableList<CustomerInfo> customerInfo = FXCollections.observableArrayList();
	
	  @FXML
	    private TableColumn<CustomerInfo, Integer> CLcust_num;

	    @FXML
	    private TableColumn<CustomerInfo, String> CLemail;

	    @FXML
	    private TableColumn<CustomerInfo, String> CLfirstname;

	    @FXML
	    private TableColumn<CustomerInfo, String> CLlastname;

	    @FXML
	    private TableColumn<CustomerInfo, Integer> CLphonenum;

	    @FXML
	    private TableColumn<CustomerInfo, String> CLusername;

	    @FXML
	    private TableView<CustomerInfo> tv_customer;
	    
	    @FXML
	    private TextField search_idconfirm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// setDayCellFactory allows to edit the date.
		// Allow user to enter current and proceeding days.
		date_Picker.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date.isBefore(LocalDate.now())) {
					// Gray out previous days
					setDisable(true);
					setStyle("-fx-text-fill: gray");
				}
			}
		});

		fromchc.getItems().addAll(from);
		tochc.getItems().addAll(to);
		bustype_chc.getItems().addAll(type);
		timechc.getItems().addAll(time);

		date_Picker.setValue(LocalDate.now());
		bus_no.setCellValueFactory(new PropertyValueFactory<>("busNumber"));
		driver_name.setCellValueFactory(new PropertyValueFactory<>("DriverInfo"));
		route_from.setCellValueFactory(new PropertyValueFactory<>("routeFrom"));
		route_to.setCellValueFactory(new PropertyValueFactory<>("routeTo"));
		bus_Type.setCellValueFactory(new PropertyValueFactory<>("busType"));
		bus_Cap.setCellValueFactory(new PropertyValueFactory<>("seatCapacity"));
		date.setCellValueFactory(new PropertyValueFactory<>("Date"));
		depart_time.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
		
		CLcust_num.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        CLusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        CLfirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        CLlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        CLemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        CLphonenum.setCellValueFactory(new PropertyValueFactory<>("contact_num"));
        customerinfo();
        tv_customer.setItems(customerInfo);
		

		// Add a listener to the table's selection model
		// obs refers to Observable value
		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// Populate the fields with data from the selected row
				Bus selectedBus = table.getSelectionModel().getSelectedItem();
				bus_num.setText(selectedBus.getBusNumber());
				Driver.setText(selectedBus.getDriverInfo());
				fromchc.setValue(selectedBus.getRouteFrom());
				tochc.setValue(selectedBus.getRouteTo());
				bustype_chc.setValue(selectedBus.getBusType());
				seat_capacity.setText(selectedBus.getSeatCapacity());
				date_Picker.setValue(LocalDate.parse(selectedBus.getDate()));
				timechc.setValue(selectedBus.getDepartureTime());
			}
		});
		
		search_customer.textProperty().addListener((observable, oldValue, newValue) -> {
            searchCustomer(newValue);
        });
		
		search_transid.textProperty().addListener((observable, oldValue, newValue) -> {
	            searchTransaction(newValue);
	        });

		fetchData();
		table.setItems(BusList);
		booking_Initiliazer();
		//trans_Table1.setItems(transactionList);
		transactionInitializer();
	}
	
	public void transactionInitializer() {
	    TransactionListData myTransaction = new TransactionListData();
	    ObservableList<Transaction> trans = myTransaction.fetchConfirmData();

	    // Filter out rows with null ref_number and gcashNum_Trans
	    trans = trans.filtered(t ->
	            t.getRefferNum() != null && t.getPymntNo() != null);

	    trans_ID.setCellValueFactory(new PropertyValueFactory<>("transId"));
	    cust_Id.setCellValueFactory(new PropertyValueFactory<>("cxId"));
	    from_Trans.setCellValueFactory(new PropertyValueFactory<>("fromTo"));
	    to_Trans.setCellValueFactory(new PropertyValueFactory<>("goTo"));
	    date_Trans.setCellValueFactory(new PropertyValueFactory<>("dateToDay"));
	    time_Trans.setCellValueFactory(new PropertyValueFactory<>("time"));
	    busType_Trans.setCellValueFactory(new PropertyValueFactory<>("busType"));
	    fare_Trans.setCellValueFactory(new PropertyValueFactory<>("fareCost"));
	    tax_Trans.setCellValueFactory(new PropertyValueFactory<>("bookCost"));
	    total_Trans.setCellValueFactory(new PropertyValueFactory<>("total"));
	    gcashNum_Trans.setCellValueFactory(new PropertyValueFactory<>("pymntNo"));
	    ref_number_Trans.setCellValueFactory(new PropertyValueFactory<>("refferNum"));
	    trans_Status.setCellValueFactory(new PropertyValueFactory<>("stat"));

	    trans_Table.setItems(trans); // Set items with the filtered ObservableList<Transaction>
	    System.out.println("Display successful");
	}



	public void getfrom(ActionEvent event) {
		String frm = fromchc.getValue();
		// myLabel.setText(myDestination);
	}

	public void getto(ActionEvent event) {
		String destTo = tochc.getValue();
		// myLabel.setText(myDestination);
	}

	public void gettype(ActionEvent event) {
		String btype = bustype_chc.getValue();
		// myLabel.setText(myDestination);
	}

	public void gettime(ActionEvent event) {
		String btype = timechc.getValue();
		// myLabel.setText(myDestination);
	}

	public void Staff(ActionEvent event) throws IOException {

		if (event.getSource() == EDIT_bttn) {
			edit_form.setVisible(true);
			booked_form.setVisible(false);
			confirm_form.setVisible(false);
			customer_form.setVisible(false);
		} else if (event.getSource() == BOOKED_bttn) {
			edit_form.setVisible(false);
			booked_form.setVisible(true);
			confirm_form.setVisible(false);
			customer_form.setVisible(false);
		} else if (event.getSource() == CONFIRM_bttn) {
			edit_form.setVisible(false);
			booked_form.setVisible(false);
			confirm_form.setVisible(true);
			customer_form.setVisible(false);
		} else if (event.getSource() == CUSTOMER_bttn) {
			edit_form.setVisible(false);
			booked_form.setVisible(false);
			confirm_form.setVisible(false);
			customer_form.setVisible(true);
		}
	}
	public void SwitchToSignIn(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	@FXML
	void add_Bus(ActionEvent event) {
		LocalDate select_Date = date_Picker.getValue();

		String busNumber = bus_num.getText();
		String DriverInfo = Driver.getText();
		String busType = bustype_chc.getValue().toString();
		String routeFrom = fromchc.getValue().toString();
		String routeTo = tochc.getValue().toString();
		String seatCapacity = seat_capacity.getText();
		String Date = select_Date.toString();
		String DepartureTime = timechc.getValue().toString();

		if (InformationCheck(busNumber)) {
			warningMessage.setText("Information already exist");
			return;
		}
		try {
			DBConek link = new DBConek();
			Connection conn = link.getConnection();

			String query = "INSERT INTO bus (bus_number, driver_name, bus_route_from, bus_route, bus_type, bus_capacity, departure_date, departure_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(query);

			// String displace = Lock.hashString(DriverInfo);
			String displacement = LockV2.lockString(DriverInfo);
			// Creates a paremeter to be passed to MySQL Table.
			// Identifies the number of rows 1 - 8.
			statement.setString(1, busNumber);
			statement.setString(2, displacement);
			statement.setString(3, routeFrom);
			statement.setString(4, routeTo);
			statement.setString(5, busType);
			statement.setString(6, seatCapacity);
			statement.setString(7, Date);
			statement.setString(8, DepartureTime);

			// Used to excute the PreparedStatement and inserts to MySQL.
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Data inserted successfully.");

				// Add the new bus to the UI table in real time.
				Bus bus = new Bus(busNumber, DriverInfo, routeFrom, routeTo, busType, seatCapacity, Date,
						DepartureTime);
				BusList.add(bus);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("Error inserting data: " + e.getMessage());
		}
		InformationCheck(busNumber);
	}
	private boolean InformationCheck(String busNumber) {

		try {
			warningMessage.setText(null);
			DBConek link = new DBConek();
			Connection conn = link.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM bus WHERE bus_number = ?");
			statement.setString(1, busNumber);

			// Forms a tabular data and iterates to each rows.
			ResultSet resultSet = statement.executeQuery();

			boolean exist = resultSet.next();

			resultSet.close();
			statement.close();
			conn.close();

			return exist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	@FXML
	void Update(ActionEvent event) {
		Bus selectedBus = table.getSelectionModel().getSelectedItem();

		// Holds the updated fields or data
		if (selectedBus != null) {

			String updatedBusNumber = bus_num.getText();
			String updatedDriverInfo = Driver.getText();
			String updatedRouteFrom = fromchc.getValue();
			String updatedRouteTo = tochc.getValue();
			String updatedBusType = bustype_chc.getValue();
			String updatedSeatCapacity = seat_capacity.getText();
			String updatedDate = date_Picker.getValue().toString();
			String updatedDepartureTime = timechc.getValue();

			try {

				DBConek link = new DBConek();
				Connection conn = link.getConnection();
				String displacement = LockV2.lockString(updatedDriverInfo);
				// WHERE keyword used to identify which row will be populated with data.
				String query = "UPDATE bus SET bus_number = ?, driver_name = ?, bus_route_from = ?, bus_route = ?, bus_type = ?, bus_capacity = ?, departure_date = ?, departure_time = ? WHERE bus_number = ?";

				// Prepares a paremeter that will be sent to SQL through conn.
				PreparedStatement statement = conn.prepareStatement(query);

				// Retrieve existing values from the selected bus object, previous value or
				// value that is not updated yet.
				String existingBusNumber = selectedBus.getBusNumber();
				String existingDriverInfo = selectedBus.getDriverInfo();
				String existingRouteFrom = selectedBus.getRouteFrom();
				String existingRouteTo = selectedBus.getRouteTo();
				String existingBusType = selectedBus.getBusType();
				String existingSeatCapacity = selectedBus.getSeatCapacity();
				String existingDate = selectedBus.getDate();
				String existingDepartureTime = selectedBus.getDepartureTime();

				// Use existing values if the corresponding fields are not updated
				statement.setString(1, updatedBusNumber != null ? updatedBusNumber : selectedBus.getBusNumber());
				statement.setString(2, updatedDriverInfo != null ? displacement : existingDriverInfo);
				statement.setString(3, updatedRouteFrom != null ? updatedRouteFrom : existingRouteFrom);
				statement.setString(4, updatedRouteTo != null ? updatedRouteTo : existingRouteTo);
				statement.setString(5, updatedBusType != null ? updatedBusType : existingBusType);
				statement.setString(6, updatedSeatCapacity != null ? updatedSeatCapacity : existingSeatCapacity);
				statement.setString(7, updatedDate != null ? updatedDate : existingDate);
				statement.setString(8, updatedDepartureTime != null ? updatedDepartureTime : existingDepartureTime);
				statement.setString(9, selectedBus.getBusNumber());
	
				int rowsUpdated = statement.executeUpdate();
				// Refreshes entire table and display the real time changes.
				if (rowsUpdated > 0) {
					System.out.println("Data updated successfully.");

					// Update the selected bus object in the UI table
					selectedBus.setBusNumber(updatedBusNumber != null ? updatedBusNumber : existingBusNumber);
					selectedBus.setDriverInfo(updatedDriverInfo != null ? updatedDriverInfo : existingDriverInfo);
					selectedBus.setRouteFrom(updatedRouteFrom != null ? updatedRouteFrom : existingRouteFrom);
					selectedBus.setRouteTo(updatedRouteTo != null ? updatedRouteTo : existingRouteTo);
					selectedBus.setBusType(updatedBusType != null ? updatedBusType : existingBusType);
					selectedBus
							.setSeatCapacity(updatedSeatCapacity != null ? updatedSeatCapacity : existingSeatCapacity);
					selectedBus.setDate(updatedDate != null ? updatedDate : existingDate);
					selectedBus.setDepartureTime(
							updatedDepartureTime != null ? updatedDepartureTime : existingDepartureTime);

					// Refresh the table view to reflect the changes
					table.refresh();
				}

				conn.close();
			} catch (SQLException e) {
				System.out.println("Error updating data: " + e.getMessage());
			}
		}
	}

	private void fetchData() {
		// Clear existing data.
		// Ensure up-to-date data.
		BusList.clear();

		try {
			DBConek link = new DBConek();
			Connection conn = link.getConnection();

			String query = "SELECT * FROM bus";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			// String formatter = Reformat.decryptDriverName(DriverInfo);
			// Process the result set
			while (resultSet.next()) {
				String busNumber = resultSet.getString("bus_number");

				// Retrieve hashed DriverInfo from DB.
				String hashedDriverInfo = resultSet.getString("driver_name");

				// Display the decrypted Driver Name by calling the method that stores the
				// decryption process.
				String DriverInfo = Key.unlockString(hashedDriverInfo);
				String routeFrom = resultSet.getString("bus_route_from");
				String routeTo = resultSet.getString("bus_route");
				String busType = resultSet.getString("bus_type");
				String seatCapacity = resultSet.getString("bus_capacity");
				String Date = resultSet.getString("departure_date");
				String departureTime = resultSet.getString("departure_time");

				Bus bus = new Bus(busNumber, DriverInfo, routeFrom, routeTo, busType, seatCapacity, Date,
						departureTime);
				BusList.add(bus);
			}
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error fetching data: " + e.getMessage());
		}
	}

	@FXML
	void delete_data(ActionEvent event) {
		Bus selectedBus = table.getSelectionModel().getSelectedItem();
		if (selectedBus != null) {
			try {
				DBConek link = new DBConek();
				Connection conn = link.getConnection();

				String query = "DELETE FROM bus WHERE bus_number = ?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, selectedBus.getBusNumber());

				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) {
					System.out.println("Data deleted successfully.");
					BusList.remove(selectedBus); // Remove the bus from the observable list
				}

				conn.close();
			} catch (SQLException e) {
				System.out.println("Error deleting data: " + e.getMessage());
			}
		}
	}

	@FXML
	void refresh_fields(ActionEvent event) {
		// Clear the text or value properties of the input fields

		bus_num.setText(""); // Clear the bus number text field
		Driver.setText(""); // Clear the driver text field
		fromchc.setValue(null); // Clear the from choice box
		tochc.setValue(null); // Clear the to choice box
		bustype_chc.setValue(null); // Clear the bus type choice box
		seat_capacity.setText(""); // Clear the seat capacity text field
		date_Picker.setValue(null); // Clear the date picker
		timechc.setValue(null); // Clear the time choice box

	}

	@FXML
	void search_data(ActionEvent event) {
		String searchQuery = search_bar.getText().trim();
		warningMessage.setText(null);
		if (!searchQuery.isEmpty()) {
			// Iterate through the BusList to find a matching row
			for (int i = 0; i < BusList.size(); i++) {
				Bus bus = BusList.get(i);
				if (bus.getBusNumber().equals(searchQuery) || bus.getDriverInfo().equals(searchQuery)) {
					// Select the matching row in the table
					table.getSelectionModel().select(i);

					// Scroll to the selected row
					table.scrollTo(i);

					return; // Exit the method once a match is found
				}
			}

			// No match found, display a message
			warningMessage.setText("NO RESULTS FOUND");
			System.out.println("No results found.");
		}
	}
	@FXML
	void ConfirmPayment(ActionEvent event) {
	    try {
	    	DBConek link = new DBConek();
	        Connection conn = link.getConnection();

	        // Get the selected transaction from the TableView
	        Transaction selectedTransaction = trans_Table.getSelectionModel().getSelectedItem();

	        // Update the "Status" column for the selected transaction
	        String updateQuery = "UPDATE transact SET payment_status = ? WHERE transact_id = ?";
	        PreparedStatement updatePs = conn.prepareStatement(updateQuery);
	        updatePs.setString(1, "Confirmed");
	        updatePs.setString(2, selectedTransaction.getTransId());

	        updatePs.executeUpdate();
	        updatePs.close();

	        // Insert the confirmed transaction into the "confirm" table
	        String insertQuery = "INSERT INTO confirm SELECT * FROM transact WHERE payment_status = ?";
	        PreparedStatement insertPs = conn.prepareStatement(insertQuery);
	        insertPs.setString(1, "Confirmed");
	        insertPs.executeUpdate();
	        insertPs.close();

	        trans_Table.refresh();
	        conn.close();

	        // Update the filtered list for trans_Table1
	        //TransactionListData transactionListData = new TransactionListData();
	        //ObservableList<Transaction> allTransactions = transactionListData.fetchConfirmData();
	        
	        // Create a filtered list that dynamically updates with "Confirmed" transactions
	        FilteredList<Transaction> filteredTransactions = new FilteredList<>(transactionList);
	        filteredTransactions.predicateProperty().bind(Bindings.createObjectBinding(() ->
	                transaction -> "Confirmed".equals(transaction.getStat()), trans_Status1.textProperty()));

	        // Set the filtered list as the items of trans_Table1
	        trans_Table1.setItems(filteredTransactions);

	        System.out.println("Data updated");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Button clicked");
	}

	void booking_Initiliazer() {
		TransactionListData myTransaction = new TransactionListData();
	    ObservableList<Transaction> allTransactions = myTransaction.fetchConfirmData();
	    
	    // Filter transactions with "Confirmed" status
	    ObservableList<Transaction> confirmedTransactions = allTransactions.stream()
	            .filter(transaction -> "Confirmed".equals(transaction.getStat()))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList));
	 
	    trans_Table1.setItems(confirmedTransactions);

	    trans_ID1.setCellValueFactory(new PropertyValueFactory<>("transId"));
	    cust_Id1.setCellValueFactory(new PropertyValueFactory<>("cxId"));
	    from_Trans1.setCellValueFactory(new PropertyValueFactory<>("fromTo"));
	    to_Trans1.setCellValueFactory(new PropertyValueFactory<>("goTo"));
	    date_Trans1.setCellValueFactory(new PropertyValueFactory<>("dateToDay"));
	    time_Trans1.setCellValueFactory(new PropertyValueFactory<>("time"));
	    busType_Trans1.setCellValueFactory(new PropertyValueFactory<>("busType"));
	    fare_Trans1.setCellValueFactory(new PropertyValueFactory<>("fareCost"));
	    tax_Trans1.setCellValueFactory(new PropertyValueFactory<>("bookCost"));
	    total_Trans1.setCellValueFactory(new PropertyValueFactory<>("total"));
	    gcashNum_Trans1.setCellValueFactory(new PropertyValueFactory<>("pymntNo"));
	    ref_number_Trans1.setCellValueFactory(new PropertyValueFactory<>("refferNum"));
	    trans_Status1.setCellValueFactory(new PropertyValueFactory<>("stat"));

	    
	    //trans_Table1.setItems(confirmedTransactions); // Set items with the filtered list of confirmed transactions
	    System.out.println("Booking Displayed successful");
		
	}

	@FXML
	void decline_status(ActionEvent event) {
		
		try {
			DBConek link = new DBConek();
			Connection conn = link.getConnection();
			
			Transaction selectedTransaction = trans_Table.getSelectionModel().getSelectedItem();
			String query = "UPDATE transact SET payment_status = ? WHERE transact_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "Declined");
			ps.setString(2,  selectedTransaction.getTransId());
			
			ps.executeUpdate();
			trans_Table.refresh();
			ps.close();
			conn.close();
			
			TransactionListData transListData = new TransactionListData();
			ObservableList<Transaction> updatedTransctionList = transListData.fetchConfirmData();
			trans_Table.setItems(updatedTransctionList);
			
			System.out.println("Declined Payment");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void clear_status(ActionEvent event) {
		try {
			DBConek link = new DBConek();
			Connection conn = link.getConnection();
			
			Transaction selectedTransaction = trans_Table.getSelectionModel().getSelectedItem();
			String query = "UPDATE transact SET payment_status = ? WHERE transact_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, " ");
			ps.setString(2,  selectedTransaction.getTransId());
			
			ps.executeUpdate();
			trans_Table.refresh();
			ps.close();
			conn.close();
			
			TransactionListData transListData = new TransactionListData();
			ObservableList<Transaction> updatedTransctionList = transListData.fetchConfirmData();
			trans_Table.setItems(updatedTransctionList);
			
			System.out.println("Clear Payment");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void customerinfo() {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT customer_id, firstname, lastname, username, password, email, contact_num FROM signup";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear existing data in priceList
            customerInfo.clear();

            // Fetch data from ResultSet and add to priceList
            while (resultSet.next()) {
                int cust_id = resultSet.getInt("customer_id");
                String fn = resultSet.getString("firstname");
                String ln = resultSet.getString("lastname");
                String usern = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String em = resultSet.getString("email");
                String num = resultSet.getString("contact_num");

                CustomerInfo customers = new CustomerInfo(cust_id, fn, ln, usern, pass, em, num);
                customerInfo.add(customers);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error loading data from the database: " + e.getMessage());
        }
    }
	
	private void searchCustomer(String username) {
        // Clear the TableView selection
        tv_customer.getSelectionModel().clearSelection();

        // If the search query is empty, show all customers
        if (username.isEmpty()) {
            tv_customer.setItems(customerInfo);
            return;
        }

        // Create a filtered list to store the matching customers
        ObservableList<CustomerInfo> filteredList = FXCollections.observableArrayList();

        // Loop through the customerInfo list and add the matching customers to the filtered list
        for (CustomerInfo customer : customerInfo) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                filteredList.add(customer);
            }
        }

        // Set the filtered list as the items in the TableView
        tv_customer.setItems(filteredList);
    }
	
	private void searchTransaction(String transId) {
    	
    	TransactionListData myTransaction = new TransactionListData();
        ObservableList<Transaction> allTransactions = myTransaction.fetchConfirmData();
        
        ObservableList<Transaction> confirmedTransactions = allTransactions.stream()
                .filter(transaction -> "Confirmed".equals(transaction.getStat()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        
        // Clear the TableView selection
        trans_Table1.getSelectionModel().clearSelection();

        // If the search query is empty, show all transactions
        if (transId.isEmpty()) {
            trans_Table1.setItems(confirmedTransactions);
            return;
        }

        // Create a filtered list to store the matching transactions
        ObservableList<Transaction> filteredList = FXCollections.observableArrayList();

        // Loop through the confirmedTransactions list and add the matching transactions to the filtered list
       for (Transaction transaction : confirmedTransactions) {
            if (transaction.getTransId().equalsIgnoreCase(transId)) {
                filteredList.add(transaction);
            }
        }

        // Set the filtered list as the items in the TableView
        trans_Table1.setItems(filteredList);
    }
}