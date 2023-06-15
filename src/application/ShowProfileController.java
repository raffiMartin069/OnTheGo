package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.ShowProfileController.UserData;

public class ShowProfileController {
	
	private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Label firstname;
    @FXML
    public Label lastname;
    @FXML
    public Label email;
    @FXML
    public Label contactnum;
    @FXML
    public Label user_name;

    public void initialize() {
        // Fetch the data from the signup table in your database
        UserData userData = fetchUserDataFromDatabase();

        if (userData != null) {
            // Update the labels with the retrieved user data
            firstname.setText(userData.getFirstName());
            lastname.setText(userData.getLastName());
            user_name.setText(userData.getUsername());
            email.setText(userData.getEmail());
            contactnum.setText(userData.getContactNum());
        }
    }
    
   /* public void SwitchToProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowProfile.fxml"));
        Parent root = loader.load();

        ShowProfileController profile = loader.getController();

        // Fetch user data from database
        UserData userData = fetchUserDataFromDatabase();

        if (userData != null) {
            // Update labels with retrieved data
            profile.firstname.setText(userData.getFirstName());
            profile.lastname.setText(userData.getLastName());
            profile.user_name.setText(userData.getUsername());
            profile.email.setText(userData.getEmail());
            profile.contactnum.setText(userData.getContactNum());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    public UserData fetchUserDataFromDatabase() {
        UserData userData = null; // Initialize with null value

        try {
            DBConek link = new DBConek();
            Connection conn = link.getConnection();

            // Prepare the SQL query to retrieve user data from the signup table
            String query = "SELECT firstname, lastname, username, email, contact_num FROM signup WHERE username = ?";

            // Create a prepared statement
            PreparedStatement statement = conn.prepareStatement(query);
            // Set any necessary parameters for the query
            statement.setInt(1, 1); // Assuming you want to retrieve the data for a specific ID

            // Execute the query and retrieve the result
            ResultSet resultSet = statement.executeQuery();
            
            System.out.println(resultSet);

            // Retrieve the user data
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String contactNum = resultSet.getString("contact_num");

                // Create the UserData object with the retrieved values
                userData = new UserData(firstName, lastName, username, email, contactNum);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error fetching user data from database: " + e.getMessage());
        }

        return userData;
    }

    // Event Listener on Button.onAction
    @FXML
    public void SwitchToSignIn(ActionEvent event) throws IOException {
        // TODO Autogenerated
    	root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    }

    // Event Listener on Button.onAction
    @FXML
    public void SwitchToTickets(ActionEvent event) throws IOException {
        // TODO Autogenerated
    	root = FXMLLoader.load(getClass().getResource("TiksHistory.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Define a UserData class to hold the user data
    public class UserData {
        private String firstName;
        private String lastName;
        private String username;
        private String email;
        private String contactNum;

        public UserData(String firstName, String lastName, String username, String email, String contactNum) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.email = email;
            this.contactNum = contactNum;
        }

        // Getters and setters
        // ...
        
        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }

        public String getContactNum() {
            return contactNum;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setContactNum(String contactNum) {
            this.contactNum = contactNum;
        }
    }



    }