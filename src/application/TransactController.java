package application;

import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.util.ResourceBundle;

import application.ShowProfileController.UserData;
import javafx.collections.ObservableMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TransactController   {
    private static final String URL = "jdbc:mysql://localhost:3306/monitoring";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345ning";
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label Labelfrom_d;

    @FXML
    private Label Labelto_d;

    @FXML
    private Label LabelBType;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelTime;
    
    @FXML
    private Label LabelBookingFee;
    
    @FXML
    private Label LabelTotal;
    
    @FXML
	private Label firstname;
	@FXML
	private Label lastname;
	@FXML
	private Label email;
	@FXML
	private Label contactnum;
	@FXML
	private Label user_name;
    
    @FXML
    private ChoiceBox<String> loc_choice;

    @FXML
    private ChoiceBox<String> loc_choice1;

    @FXML
    private ChoiceBox<String> time_chc;

    @FXML
    private ChoiceBox<String> btype_chc;

    @FXML
    private DatePicker date_chc;
    
    @FXML
    private Tab CURRENT;
    
    @FXML
    private TableView<?> tv_current;
    
    @FXML
    private Label username_wc;
    

    private String[] destination = { "Argao", "Cebu City" };

    private String[] destination1 = { "Argao", "Carcar", "Cebu City", "Minganilla", "Naga", "San Fernando", "Sibonga",
            "Talisay" };

    private String[] time = { "4 AM", "5 AM", "6 AM", "7 AM", "8 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM", "4 PM",
            "5 PM", "6 PM", "7 PM", "8 PM" };

    private String[] bustype = { "AC", "NON AC" };
    
    public void initialize(URL arg0, ResourceBundle arg1) {

		transactionInitializer();
		
	}

    //SETTERS, AND GETTERS
    public void setValues(String fromLocation, String toLocation, String busType, String date, String time) {
        Labelfrom_d.setText(fromLocation);
        Labelto_d.setText(toLocation);
        LabelBType.setText(busType);
        LabelDate.setText(date);
        LabelTime.setText(time);
    }


    public void setValues(String transactionId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a database connection
            DBConek dbConek = new DBConek();
            connection = dbConek.getConnection();

            // Prepare and execute the SQL statement
            String query = "SELECT from_loc, to_loc, date, time, btype FROM transact WHERE transact_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, transactionId); // Set the value for the id parameter
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the values from the result set
                String fromLocation = resultSet.getString("from_loc");
                String toLocation = resultSet.getString("to_loc");
                String busType = resultSet.getString("btype");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                // Call the overloaded setValues method with the retrieved values
                setValues(fromLocation, toLocation, busType, date, time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here or throw it further if needed
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   
    public void setItems() {
    	loc_choice.getItems().addAll(destination);
        loc_choice1.getItems().addAll(destination1);
        time_chc.getItems().addAll(time);
        btype_chc.getItems().addAll(bustype);
    }
    
    
    // TO BOOK METHODS
    
    @FXML
    private Label LabelFare;
    
    public void setFare(double fare) {
        LabelFare.setText(Double.toString(fare));
    }
  
    private double bookingFee = 10.0;
    public void setBookingFee(double bookingFee) {
        this.bookingFee = bookingFee;
        LabelBookingFee.setText(Double.toString(bookingFee)); // Update the label to display the booking fee
    }
   
    
    private double total;
    public void setTotal(double total) {
        this.total = total;
        LabelTotal.setText(Double.toString(total)); // Update the label to display the total
    }

         
    @FXML
    void Proceed(ActionEvent event) throws IOException {
    	// Get the values from the choice boxes and labels
        String fromLocation = loc_choice.getValue();
        String toLocation = loc_choice1.getValue();
        LocalDate date = date_chc.getValue();
        String time = time_chc.getValue();
        String busType = btype_chc.getValue();
        
        // Retrieve the fare from the pricelist
        double fare = getPricelistFare(fromLocation, toLocation, date, time, busType);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Book.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        TransactController ticketWindowController = loader.getController();

        // Set the values in the TransactController
        ticketWindowController.setValues(fromLocation, toLocation, busType, date.toString(), time);
        ticketWindowController.setFare(fare);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public double getPricelistFare(String fromLocation, String toLocation, LocalDate date, String time, String busType) {
        double fare = 0.0;
        DBConek dbConek = new DBConek();
        Connection connection = dbConek.getConnection();

        if (connection != null) {
            try {
                String query = "SELECT set_price FROM pricelist WHERE from_location = ? AND to_location = ? AND btype = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, fromLocation);
                statement.setString(2, toLocation);
                statement.setString(3, busType);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    fare = resultSet.getDouble("set_price");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception here or throw it further if needed
            }
        } else {
            System.out.println("Failed to establish a database connection");
        }

        return fare;
    }
    
    public Customer getCustomer() {
    	return customer;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Prepare the SQL statement
            String deleteQuery = "DELETE FROM transact WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);

            // Get the customer ID from the TransactController
            int customerId = TransactController.getInstance().getCustomer().getCustomerId();

            // Set the parameter value
            statement.setInt(1, customerId);

            // Execute the SQL statement
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Recent data in transact table removed successfully.");
            } else {
                System.out.println("Failed to remove recent data in transact table.");
            }

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRoute.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Retrieve the controller after loading the FXML file
        TransactController controller = loader.getController();
        controller.setItems();
    }

    public void SwitchToWelcome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToAbout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("About.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    
    //sign up and log in logs logics
    private static TransactController instance;
    public static TransactController getInstance() {
        return instance;
    }
    
    @FXML
    public void initialize() {
        instance = this;
    }
    
    
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
    
    private int customerId;
    private String username;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    	
    private static Customer customer;
    
    public static void setCustomer(Customer customer) {
        TransactController.customer = customer;
    }
    
    @FXML
    void signUp(ActionEvent event) {
        String contactNum = Contactnum.getText();
        String email = Email.getText();
        String firstName = Firstname.getText();
        String lastName = Lastname.getText();
        String password = Password.getText();
        String username = Username.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || contactNum.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Sign Up", "Error", "Please fill in all the required fields.");
            return;
        }

        if (!contactNum.matches("\\d+")) {
            showAlert(Alert.AlertType.ERROR, "Sign Up", "Error", "Please enter a valid contact number.");
            return;
        }
        
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            // Check if the username, email, or contact number already exist
            String checkQuery = "SELECT * FROM signup WHERE username = ? OR email = ? OR contact_num = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            checkStatement.setString(2, email);
            checkStatement.setString(3, contactNum);
            ResultSet checkResult = checkStatement.executeQuery();

            if (checkResult.next()) {
                showAlert(Alert.AlertType.ERROR, "Sign Up", "Error", "Username, email, or contact number already exists.");
                return;
            }

            // Prepare the SQL statement
            String insertQuery = "INSERT INTO signup (firstname, lastname, username, password, contact_num, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery, new String[]{"customer_id"});

            String displacement = LockV2.lockString(password);
            
            // Set the parameter values
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, displacement);
            statement.setString(5, contactNum);
            statement.setString(6, email);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int customerId = generatedKeys.getInt(1);
                    insertLogin(connection, customerId, username, password);
                    
                    
                  
                }
                showAlert(Alert.AlertType.INFORMATION, "Sign Up", "Success", "You signed up successfully.");
                System.out.println("User sign-up successful.");
            } else {
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
            root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Button buttonconfirm;
    @FXML
    private PasswordField ConfirmNewPassword;
    
    /*@FXML
    private TextField Username;*/
    
    @FXML
    private TextField NewPassword;
    
    @FXML
    private TextField ConfirmNewpass;

    /*@FXML
    private TextField Email;*/

    public void forgotPass(ActionEvent event) {
        String username = Username.getText();
        String email = Email.getText();
        String newPassword = NewPassword.getText();
        String confirmNewPassword = ConfirmNewPassword.getText();

        if (username.isEmpty() || email.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "New password and confirm password do not match.");
            return;
        }

        try {
            // Connect to MySQL database
        	Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // Check if user exists
            String query = "SELECT * FROM signup WHERE username = ? AND email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Generate a hashed version of the new password (using your preferred hashing algorithm)
                String hashedPassword = generateHash(newPassword);

                // Update user's record with the new password
                String updateQuery = "UPDATE signup SET password = ? WHERE username = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, hashedPassword);
                updateStatement.setString(2, username);
                updateStatement.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Success", "Password changed successfully!");

                // Close resources
                resultSet.close();
                statement.close();
                connection.close();

                try {
                    SwitchToChangeSuccess(event); // Navigate to the success screen
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "You don't have an account.");
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while connecting to the database.");
        }
    }
    
    private String generateHash(String password) {
        // Implement your password hashing logic here
        // You can use libraries like BCrypt or MessageDigest for secure password hashing
        return LockV2.lockString(password); // This is a placeholder, you should replace it with your actual hashing logic
    }

    private String hashPassword(String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
    
    private void showAlert(Alert.AlertType alertType, String title, String header) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        String content = null;
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
    
  //SWITCH TO FORGOT PASSWORD
    public void SwitchToForgotPass(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("ForgotPass.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
		
		}
    //SWITCH TO CHANGE SUCCESS OR PASSWORD CHANGED SUCCESSFULLY
    public void SwitchToChangeSuccess(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ChangeSuccess.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    @FXML
    private Button buttonLogin; 
    
    @FXML
    void logIn(ActionEvent event) throws IOException {
        String username = Username.getText();
        String password = Password.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Log In", "Error", "Please fill in both Username and Password.");
            return;
        }

	    try {
	        DBConek link = new DBConek();
	        // Connect to the database
	        Connection connection = link.getConnection();

	        // Create a SQL statement
	        Statement statement = connection.createStatement();

	        // Construct the SQL query with placeholders
	        String queryCustomer = "SELECT * FROM signup WHERE username = '%s' AND password = '%s'";
	        queryCustomer = String.format(queryCustomer, username, LockV2.lockString(password));
	        
	        // Execute the customer query
	        ResultSet customerResult = statement.executeQuery(queryCustomer);	
	        
	        if (customerResult.next()) {
	        	
                System.out.println("User log-in successful.");
                showAlert(Alert.AlertType.INFORMATION, "Log In", "Success", "User log-in successful.");

                // Create a new Customer object and set its properties
                Customer customer = new Customer();
                customer.setCustomerId(TransactController.getInstance().getCustomerId(username));
                customer.setUsername(username);

                TransactController transactController = TransactController.getInstance();
                transactController.setCustomer(customer);
                
                // Load the FXML file and get the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TiksHistory.fxml"));
                Parent root = loader.load();
                //TransactController tiksHistoryController = loader.getController();
               // tiksHistoryController.displayusername (username);
                
                // Pass the customer ID to the TiksHistoryController
               // tiksHistoryController.setCustomerId(customer.getCustomerId());

                // Create a new stage and set the scene
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                
             // Display username in an alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tickets");
                alert.setHeaderText("Welcome, " + username + "!");
                alert.setContentText("See your tickets here");
                alert.showAndWait();


                // Close the current stage
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            }else if (customerResult.next() == false)
            {
            	AdminEmployee(event);
            }
	        // Close the database resources
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle database connection or query error
	    }
    }
    
    public void AdminEmployee (ActionEvent event) throws SQLException, IOException {
    	 String username = Username.getText();
         String password = Password.getText();
    	
         try {
    	 DBConek link = new DBConek();
	        // Connect to the database
	        Connection connection = link.getConnection();

	        // Create a SQL statement
	        Statement statement = connection.createStatement();

	        // Construct the SQL query with placeholders
	        String queryAdmin = "SELECT * FROM admin WHERE email = '%s' AND password = '%s'";
	        queryAdmin = String.format(queryAdmin, username, password);

	        String queryEmployee = "SELECT * FROM employee WHERE email = '%s' AND password = '%s'";
	        queryEmployee = String.format(queryEmployee, username, password);
    	
    	 // Execute the admin query
        ResultSet adminResult = statement.executeQuery(queryAdmin);
        
        // Check if any rows were returned for admin
        if (adminResult.next()) {
        	System.out.println("Admin log-in successful.");
            showAlert(Alert.AlertType.INFORMATION, "Log In", "Success", "User log-in successful.");
            // Switch to admin FXML window
            Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Close the database resources
            adminResult.close();
            statement.close();
            connection.close();
            return;
        }  
        // Execute the employee query
        ResultSet employeeResult = statement.executeQuery(queryEmployee);
        
        //if (employeeResult.next()) {
        
        // Check if any rows were returned for employee
        if (employeeResult.next()) {
        	System.out.println("Staff log-in successful.");
            showAlert(Alert.AlertType.INFORMATION, "Log In", "Success", "User log-in successful.");
            // Switch to staff FXML window
            Parent root = FXMLLoader.load(getClass().getResource("Staff.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Close the database resources
            employeeResult.close();
            statement.close();
            connection.close();
            return;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            showAlert(Alert.AlertType.ERROR, "Log In", "Error", "Invalid credentials. Please try again.");
        }
        } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle database connection or query error
	    }
    }


    private void insertLogin(Connection connection, int customerId, String username, String password) {
        try {
            // Prepare the SQL statement
            String insertQuery = "INSERT INTO customer_log (customer_id, username, time_log) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            // Set the parameter values
            statement.setInt(1, customerId);
            statement.setString(2, username);
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Login details logged successfully.");
            } else {
                System.out.println("Failed to log login details.");
            }
         // Create a new Customer object and set its properties
            Customer customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setUsername(username);

            // Set the customer object in the TransactController
            TransactController.setCustomer(customer);
            // Close resources
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }
  
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
    
    
    //SWITCHSCENES
    public void SwitchToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BUS.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToTickets(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TiksHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void displayusername (String username) {
    	username_wc.setText(username + "!");
    }

    
    public void SwitchToProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProfile.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void SwitchToSearchRoute(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRoute.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Retrieve the controller after loading the FXML file
        TransactController controller = loader.getController();
        controller.setItems();
        
	    DatePicker datePicker = controller.getDatePicker();
	    datePicker.setDayCellFactory(date_chc -> new DateCell() {
	    	@Override
	        public void updateItem(LocalDate item, boolean empty) {
	            super.updateItem(item, empty);

	            LocalDate today = LocalDate.now();
	            LocalDate tomorrow = today.plusDays(1);
	            LocalDate dayAfterTomorrow = today.plusDays(2);
	            LocalDate lastDay = today.plusDays(3);

	            // Disable dates before today and after the day after tomorrow
	            if (item.isBefore(today) || item.isAfter(lastDay)) {
	                setDisable(true);
	                setStyle("-fx-background-color: #ffc0cb;"); // Set disabled dates' background color
	            }
	        }
	    });
    }
    
    public DatePicker getDatePicker() {
        return date_chc;
    }

    
   //TRANSACT METHPODS
  
    
    public void SwitchToOperator(ActionEvent event) throws IOException, SQLException {
        if (customer != null) {
          

            String fromLocation = loc_choice.getValue();
            String toLocation = loc_choice1.getValue();
            LocalDate date = date_chc.getValue();
            String time = time_chc.getValue();
            String busType = btype_chc.getValue();
            
            
            DBConek dbConek = new DBConek();
            Connection connection = dbConek.getConnection();

            if (connection != null) {
                try {
                    double fare = getPricelistFare(fromLocation, toLocation, date, time, busType);
                    int recentTransactionId = getMostRecentTransactionId();
                    String transactionId = String.valueOf(recentTransactionId);
                    int customerId = getCustomerId(customer.getUsername());;
                    
                    double bookfee = bookingFee;
                    double total = fare + bookfee;

                    insertTransaction(connection,customerId, fromLocation, toLocation, date, time, busType, fare, bookfee, total);

                    transactionId = getTransactionId(connection, fromLocation, toLocation, date, time, busType);

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketWindow.fxml"));
                    Parent root = loader.load();
                    TransactController ticketWindowController = loader.getController();

                    ticketWindowController.setValues(transactionId);
                    ticketWindowController.setFare(fare);
                    ticketWindowController.setBookingFee(bookfee);
                    ticketWindowController.setTotal(total);

                   
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Database Error", "Failed to insert transaction data.");
                } finally {
                    connection.close();
                }
            } else {
                System.out.println("Failed to establish a database connection");
            }
        } else {
            System.out.println("Customer object is null");
        }
    }


  
    private int getCustomerId(String username) throws SQLException {
    	int customerId = 0;
        String query = "SELECT customer_id FROM customer_log WHERE username = ? ORDER BY customer_id DESC LIMIT 1";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customerId = resultSet.getInt("customer_id");
                }
            }
        }
        return customerId;
    }
   

 
    private String getTransactionId(Connection connection, String fromLocation, String toLocation, LocalDate date, String time, String busType) throws SQLException {
        String transactionId = null;

        // Prepare and execute the SQL statement
        String query = "SELECT transact_id FROM transact WHERE from_loc = ? AND to_loc = ? AND date = ? AND time = ? AND btype = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, fromLocation);
        statement.setString(2, toLocation);
        statement.setDate(3, java.sql.Date.valueOf(date));
        statement.setString(4, time);
        statement.setString(5, busType);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            transactionId = resultSet.getString("transact_id");
        }

        resultSet.close();
        statement.close();

        return transactionId;
    }
     
    private void insertTransaction(Connection connection, int customerId, String fromLocation, String toLocation,
            LocalDate date, String time, String busType, double fare, double bookfee, double total) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO transact (customer_id, from_loc, to_loc, `date`, time, btype, fare, book_fee, total, `current_date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE())")) {

            // Set the parameter values
            statement.setInt(1, customerId);
            statement.setString(2, fromLocation);
            statement.setString(3, toLocation);
            statement.setDate(4, java.sql.Date.valueOf(date));
            statement.setString(5, time);
            statement.setString(6, busType);
            statement.setDouble(7, fare);
            statement.setDouble(8, bookfee);
            statement.setDouble(9, total);

            // Execute the SQL statement
            statement.executeUpdate();
        }
    }
    
    // SWITCH TO BOOKING
    public void SwitchToBook(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Book.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    
    //PAYMENT METHODS
    
    @FXML
    private TextField tf_gcachnum;

    @FXML
    private TextField tf_rn;
    
  //ALERT! ALERT!	
    private boolean ticketBookingCanceled = false;


    public void bookTicket(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Book Ticket");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to book the ticket?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {

                System.out.println("Ticket booked!");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentForm.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception or display an error message
                }
            } else if (response == ButtonType.NO) {
                // User clicked NO
            	ticketBookingCanceled = true;
                System.out.println("Ticket booking canceled");
                try {
                    back(event);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception or display an error message
                }

            } else {
                // User closed the dialog

                System.out.println("Ticket booking canceled (dialog closed)");
            }
        });
    }

   public void SwitchToPayment(ActionEvent event) throws IOException {
        bookTicket(event);

        // Check if the ticket booking is canceled
        if (ticketBookingCanceled) {
            return; // Exit the method
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentForm.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    public void Payment(ActionEvent event) throws IOException {
        String gachNum = tf_gcachnum.getText();
        String refNum = tf_rn.getText();
        if (gachNum.isEmpty() || refNum.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Can't process request.", "Please fill in the required fields.");
            return;
        }
        int recentTransactionId = getMostRecentTransactionId();
        // Update the 'transact' table with the entered values
        updateTransactTable(recentTransactionId, gachNum, refNum);
        
        // Load and display the PaymentForm.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TiksHistory.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    
    private int getMostRecentTransactionId() {
        int transactionId = 0;
        String sql = "SELECT MAX(transact_id) AS recent_transaction_id FROM transact";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                transactionId = rs.getInt("recent_transaction_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionId;
    }

    private void updateTransactTable(int transactionId, String gachNum, String refNum) {
        String sql = "UPDATE transact SET gach_num = ?, ref_num = ? WHERE transact_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gachNum);
            stmt.setString(2, refNum);
            stmt.setInt(3, transactionId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Transact table updated successfully.");
            } else {
                System.out.println("No rows were updated in the transact table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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
    private TableColumn<Transaction, String> cust_Id;
    
    @FXML
    private TableColumn<Transaction, String> busType_Trans;
    
    @FXML
    private TableView<Transaction> trans_Table;
    
    public void transactionInitializer() {

		TransactController cx_details = new TransactController();
		//cx_details.customer_Identification(0);
        TransactionListData myTransaction = new TransactionListData();
        ObservableList<Transaction> trans = myTransaction.fetchConfirmData();
        
        trans_ID.setCellValueFactory(new PropertyValueFactory<>("transId"));
     //   cust_Id.setCellValueFactory(new PropertyValueFactory<>("cxId"));
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
        
        trans_Table.setItems(trans); // Set items with the ObservableList<Transaction>
        System.out.println("Display successful");
        
    }
	@FXML
	public void customer_cancel(ActionEvent event) {
		try {
	        DBConek link = new DBConek();
	        Connection conn = link.getConnection();

	        // Get the selected transaction from the TableView
	        Transaction selectedTransaction = trans_Table.getSelectionModel().getSelectedItem();

	        // Update the "Status" column for the selected transaction
	        String updateQuery = "UPDATE transact SET payment_status = ? WHERE transact_id = ?";
	        PreparedStatement updatePs = conn.prepareStatement(updateQuery);
	        updatePs.setString(1, "Cancelled");
	        updatePs.setString(2, selectedTransaction.getTransId());

	        updatePs.executeUpdate();
	        updatePs.close();
	        
	        selectedTransaction.setStat("Cancelled");
	        trans_Table.refresh();
	        conn.close();

	        System.out.println("Data updated");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Button clicked");
	}
    
}
