package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketWindow {
    @FXML
    private Label LabelFromLocation;

    @FXML
    private Label LabelToLocation;

    @FXML
    private Label LabelBusType;

    @FXML
    private Label LabelDate;

    @FXML
    private Label LabelTime;

    public void setValues(String fromLocation, String toLocation, String busType, String date, String time) {
        LabelFromLocation.setText(fromLocation);
        LabelToLocation.setText(toLocation);
        LabelBusType.setText(busType);
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
            String query = "SELECT from_loc, to_loc, date, time, btype FROM transact WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, transactionId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the values from the result set
                String fromLocation = resultSet.getString("from_loc");
                String toLocation = resultSet.getString("to_loc");
                String busType = resultSet.getString("btype");
                String date = resultSet.getDate("date").toString();
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
}
