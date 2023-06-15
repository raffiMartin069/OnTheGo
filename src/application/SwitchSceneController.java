package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ButtonBar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class SwitchSceneController
{
	// MySQL database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/monitoring";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345ning";
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
		@FXML
	    private Button CUSTOMER_bttn;
	 
	    @FXML
	    private Button EDIT_bttn;
	    	
	    @FXML
	    private AnchorPane customer_form;
	 	    
	    @FXML
	    private AnchorPane edit_form;
	    	   	    
	    @FXML
	    void Proceed(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("Book.fxml"));
		   	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		   	scene = new Scene(root);
		  	stage.setScene(scene);
	 		stage.show();
	    }

	    @FXML
	    void back(ActionEvent event) throws IOException {
	    	root = FXMLLoader.load(getClass().getResource("SearchRoute.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    
	    
	    //sign up and log in logs logics

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
	    void signUp(ActionEvent event) {
	        String contactNum = Contactnum.getText();
	        String email = Email.getText();
	        String firstName = Firstname.getText();
	        String lastName = Lastname.getText();
	        String password = Password.getText();
	        String username = Username.getText();

	        try {
	            // Establish a database connection
	            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	            // Prepare the SQL statement
	            String insertQuery = "INSERT INTO signup (firstname, lastname, username, password, contact_num, email) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(insertQuery);

	            // Set the parameter values
	            statement.setString(1, firstName);
	            statement.setString(2, lastName);
	            statement.setString(3, username);
	            statement.setString(4, password);
	            statement.setString(5, contactNum);
	            statement.setString(6, email);

	            // Execute the SQL statement
	            int rowsInserted = statement.executeUpdate();

	            if (rowsInserted > 0) {
	            	showAlert(Alert.AlertType.INFORMATION, "Sign Up", "Success", "You signed up successfully.");
	            	System.out.println("User sign-up successful.");
	            }else {
	            	 showAlert(Alert.AlertType.ERROR, "Sign Up", "Error", "Failed to sign up user.");
	            	 System.out.println("Failed to sign up user.");
	            }

	            // Close resources
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        try {
	        {
	    		root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
	    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.show();
	    	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	

	    }
	   
	    
	    
	    
	   /* @FXML
	    void logIn(ActionEvent event) throws IOException {
	        String username = Username.getText();
	        String password = Password.getText();

	        try {
	            // Establish a database connection
	            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	            // Prepare the SQL statement
	            String selectQuery = "SELECT * FROM signup WHERE username = ? AND password = ?";
	            
	            PreparedStatement statement = connection.prepareStatement(selectQuery);

	            // Set the parameter values
	            statement.setString(1, username);
	            statement.setString(2, password);

	            // Execute the SQL statement
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                System.out.println("User log-in successful.");
	                showAlert(Alert.AlertType.INFORMATION, "Log In", "Success", "User log-in successful.");
	                
	                // Call the logLoginDetails method to log the login details
	                logLoginDetails(connection, username);
	               
	                SwitchToTickets(event);
	            } else {
	                System.out.println("Invalid credentials. Please try again.");
	                showAlert(Alert.AlertType.ERROR, "Log In", "Error", "Invalid credentials. Please try again.");
	            }

	            // Close resources
	            resultSet.close();
	            statement.close();
	            connection.close();	          	 
	     
	        } catch (SQLException e) {
	            e.printStackTrace();
	            showAlert(Alert.AlertType.ERROR, "Log In", "Error", "Failed to log in user.");
	        }
	    }
	    
	    
	    private void logLoginDetails(Connection connection, String username) {
	    	try {
	            // Establish a database connection
	            

	            // Prepare the SQL statement
	            String insertQuery = "INSERT INTO customer_log (username, time_log) VALUES (?, ?)";
	            PreparedStatement statement = connection.prepareStatement(insertQuery);

	            // Set the parameter values
	            statement.setString(1, username);
	            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	            // Execute the SQL statement
	            int rowsInserted = statement.executeUpdate();
	            
	            if (rowsInserted > 0) {
	                System.out.println("Login details logged successfully.");
	            } else {
	                System.out.println("Failed to log login details.");
	            }
	           
	            // Close resources
	            statement.close();
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	*/    
	    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        
	        
	        // Set alert dialog style
	        DialogPane dialogPane = alert.getDialogPane();
	        alert.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        dialogPane.getStyleClass().add("custom-alert");

	        // Customize the button text
	        ButtonType okayButton = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
	        alert.getButtonTypes().setAll(okayButton);

	        // Show the alert and wait for button click
	       
	        alert.showAndWait();
	    }	    	   	    	    	    
	    
	    //switch scenes area
	public void SwitchToWelcome (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	  
	public void SwitchToAbout (ActionEvent event) throws IOException
		{
			root = FXMLLoader.load(getClass().getResource("About.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	    
	public void SwitchToSignIn (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToSignUp (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("BUS.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToTickets (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("TiksHistory.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void SwitchToProfile (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void SwitchToSearchRoute (ActionEvent event) throws IOException
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRoute.fxml"));
	    root = loader.load();
	    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();

	}
	
	
	public void SwitchToOperator (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("TicketWindow.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	 public void SwitchToBook (ActionEvent event) throws IOException
	 {
  		root = FXMLLoader.load(getClass().getResource("Book.fxml"));
	   	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	   	scene = new Scene(root);
	  	stage.setScene(scene);
 		stage.show();
	  }
	 
	 public void SwitchToPayment (ActionEvent event) throws IOException
	 {
  		root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
	   	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	   	scene = new Scene(root);
	  	stage.setScene(scene);
 		stage.show();
	  }
	 
	// Admin controller
	 
	 

	 
	 
	 
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
	    
	  
	    @FXML
	    private ChoiceBox<String> loc_choice;
	    
	    @FXML
	    private ChoiceBox<String> loc_choice1;
	    
	    @FXML
	    private ChoiceBox<String> time_chc;
	    
	    @FXML
	    private ChoiceBox<String> btype_chc;
	    

	    
	    
	    private String[] destination = {"Argao", "Cebu City"};
	    
	    private String[] destination1 = {"Argao", "Carcar", "Cebu City", "Minganilla", "Naga", "San Fernando", "Sibonga", "Talisay"};

	    private String[] time = {"4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "4 PM", "5 PM", "6 PM", "7 PM", "8 PM"};
	    
	    private String[] bustype = {"AC", "NON AC"};
	    
	    public void setItems() {
	        loc_choice.getItems().addAll(destination);
	        
	        loc_choice1.getItems().addAll(destination1);
	        
	        time_chc.getItems().addAll(time);
	        
	        btype_chc.getItems().addAll(bustype);
	    }
	    

	    
	    
	    private ObservableList<Prices> priceList = FXCollections.observableArrayList();

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
}