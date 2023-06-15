package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AdminController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/monitoring";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345ning";
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
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
    
    private ObservableList<CustomerInfo> customerInfo = FXCollections.observableArrayList();
    
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
    private TableView<Transaction> trans_Table;
    
    ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
    //transactionListData.fetchConfirmData();
    
    //ObservableList<Transaction> TransctionList = FXCollections.observableArrayList();

    @FXML
    private Button DASHBOARD_bttn;

    @FXML
    private Button PRICE_bttn;

    @FXML
    private Button Transaction_bttn;

    @FXML
    private Button adminCUSTOMER_bttn;

    @FXML
    private AnchorPane admincustomer_form;

    @FXML
    private Button bttn_add;

    @FXML
    private Button bttn_del;

    @FXML
    private Button bttn_update;

    @FXML
    private TextField search_customer;
    
    @FXML
    private TextField search_transid;
    
    @FXML
    private ChoiceBox<String> bustypechc;

    @FXML
    private TableColumn<Prices, String> col_btype;

    @FXML
    private TableColumn<Prices, String> col_from;

    @FXML
    private TableColumn<Prices, Integer> col_price;

    @FXML
    private TableColumn<Prices, String> col_to;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private ChoiceBox<String> fromchc;

    @FXML
    private AnchorPane price_form;

    @FXML
    private TextField tf_setprice;

    @FXML
    private ChoiceBox<String> tochc;

    @FXML
    private AnchorPane transaction_form;

    @FXML
    private TableView<Prices> tv_prices;

    private ObservableList<Prices> priceList = FXCollections.observableArrayList();
    
    //private ObservableList<Transaction> trans = FXCollections.observableArrayList();
    
    @FXML
 	private Label bus_count;
    
    @FXML
    private Label today_income;

    @FXML
    private Label total_income;
    
    @FXML
    private Label month_income;
    
    @FXML
    private Button LOGOUT_bttn;
    
    @FXML
    private TextField Contactnum;

    @FXML
    private TextField Email;

    @FXML
    private TextField Firstname;

    @FXML
    private TextField Lastname;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;
    
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
    private TableView<Transaction> trans_Table1;
    
    @FXML
    void Logtout(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private String[] from = { "Cebu City", "Argao" };
    private String[] to = { "Argao", "Carcar", "Cebu City", "Minganilla", "Naga", "San Fernando", "Sibonga", "Talisay" };
    private String[] type = { "AC", "NON AC" };

    public void initialize(URL arg0, ResourceBundle arg1) {
        fromchc.getItems().addAll(from);
        fromchc.setOnAction(this::getfrom);

        tochc.getItems().addAll(to);
        tochc.setOnAction(this::getto);

        bustypechc.getItems().addAll(type);
        bustypechc.setOnAction(this::gettype);

        // Bind the priceList to the TableView
        col_from.setCellValueFactory(new PropertyValueFactory<>("fromLocation"));
        col_to.setCellValueFactory(new PropertyValueFactory<>("toLocation"));
        col_btype.setCellValueFactory(new PropertyValueFactory<>("busType"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadDataFromDatabase();
        tv_prices.setItems(priceList);
        
        CLcust_num.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        CLusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        CLfirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        CLlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        CLemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        CLphonenum.setCellValueFactory(new PropertyValueFactory<>("contact_num"));
        customerinfo();
        tv_customer.setItems(customerInfo);
        
        
       booking();
        
        int count = BusCount.getCount();
        bus_count.setText(String.valueOf(count));

        double incometoday = CurrentDateincome.getTodayIncome();
        today_income.setText(String.valueOf(incometoday));
        
        double incomemonth = Monthincome.getMonthincome();
        month_income.setText(String.valueOf(incomemonth));
        
        double totalincome = Totalincome.getTotal();
        total_income.setText(String.valueOf(totalincome));
        
        search_customer.textProperty().addListener((observable, oldValue, newValue) -> {
            searchCustomer(newValue);
        });
        
       search_transid.textProperty().addListener((observable, oldValue, newValue) -> {
            searchTransaction(newValue);
        });
        
        //transactionInitializer();
    }
    

    public void getfrom(ActionEvent event) {
        String frm = fromchc.getValue();
        // myLabel.setText(myDestination);
    }

    public void getto(ActionEvent event) {
        String todestination = tochc.getValue();
        // myLabel.setText(myDestination);
    }

    public void gettype(ActionEvent event) {
        String btype = bustypechc.getValue();
        // myLabel.setText(myDestination);
    }

    public void Admin(ActionEvent event) throws IOException {
        if (event.getSource() == DASHBOARD_bttn) {
            dashboard_form.setVisible(true);
            transaction_form.setVisible(false);
            price_form.setVisible(false);
            admincustomer_form.setVisible(false);
        } else if (event.getSource() == Transaction_bttn) {
            dashboard_form.setVisible(false);
            transaction_form.setVisible(true);
            price_form.setVisible(false);
            admincustomer_form.setVisible(false);
        } else if (event.getSource() == PRICE_bttn) {
            dashboard_form.setVisible(false);
            transaction_form.setVisible(false);
            price_form.setVisible(true);
            admincustomer_form.setVisible(false);
        } else if (event.getSource() == adminCUSTOMER_bttn) {
            dashboard_form.setVisible(false);
            transaction_form.setVisible(false);
            price_form.setVisible(false);
            admincustomer_form.setVisible(true);
        }
    }

    @FXML
    void add_price(ActionEvent event) {
        int setPrice = Integer.parseInt(tf_setprice.getText());

        String frm = fromchc.getValue();
        String todestination = tochc.getValue();
        String btype = bustypechc.getValue();
        int price = setPrice;

        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "INSERT INTO pricelist (from_location, to_location, btype, set_price) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, frm);
            statement.setString(2, todestination);
            statement.setString(3, btype);
            statement.setInt(4, price);
            statement.executeUpdate();
            System.out.println("Data inserted successfully.");

            connection.close();

            // Reload data from the database
            loadDataFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    @FXML
    void delete_price(ActionEvent event) {
        Prices selectedPrice = tv_prices.getSelectionModel().getSelectedItem();
        if (selectedPrice == null) {
            // No price selected, show an error or notification to select a price
            return;
        }

        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "DELETE FROM pricelist WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, selectedPrice.getId()); // Assuming you have an 'id' column in your 'pricelist' table
            statement.executeUpdate();
            System.out.println("Data deleted successfully.");

            connection.close();

            // Reload data from the database
            loadDataFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error deleting data: " + e.getMessage());
        }
    }

    @FXML
    void update_price(ActionEvent event) {
        // Get the selected price from the table
        Prices selectedPrice = tv_prices.getSelectionModel().getSelectedItem();
        if (selectedPrice == null) {
            System.out.println("No price selected.");
            return;
        }

        try {
            // Get the new value from the input field
            String newPriceStr = tf_setprice.getText();
            int newPrice = Integer.parseInt(newPriceStr);

            // Update the price field in the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "UPDATE pricelist SET set_price = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newPrice);
            statement.setInt(2, selectedPrice.getId()); // Assuming you have an 'id' field in the Prices class
            statement.executeUpdate();
            System.out.println("Price updated successfully.");

            connection.close();

            // Reload data from the database
            loadDataFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error updating price: " + e.getMessage());
        }
    }

    private void loadDataFromDatabase() {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            String query = "SELECT id, from_location, to_location, btype, set_price FROM pricelist";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear existing data in priceList
            priceList.clear();

            // Fetch data from ResultSet and add to priceList
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String from = resultSet.getString("from_location");
                String to = resultSet.getString("to_location");
                String busType = resultSet.getString("btype");
                int price = resultSet.getInt("set_price");

                Prices prices = new Prices(id, from, to, busType, price);
                priceList.add(prices);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error loading data from the database: " + e.getMessage());
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
    
    
   public void booking() {
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


}
