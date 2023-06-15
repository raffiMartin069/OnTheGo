package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogginController {

    // MySQL database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/signup";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345ning";

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
    void switchToSignIn(ActionEvent event) {
        // Code to switch to the sign-in scene
    }

    @FXML
    void switchToWelcome(ActionEvent event) {
        // Code to switch to the welcome scene
    }

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
            String insertQuery = "INSERT INTO users (first_name, last_name, username, password, contact_num, email) VALUES (?, ?, ?, ?, ?, ?)";
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
                System.out.println("User sign-up successful.");
            } else {
                System.out.println("Failed to sign up user.");
            }

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        


    }
}
