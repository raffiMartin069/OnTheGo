package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CurrentDateincome {
	
	public static double getTodayIncome() {
	    double todayIncome = 0.0;

	    try {
	        DBConek link = new DBConek();
	        Connection conn = link.getConnection();

	        // Get the current date
	        LocalDate currentDate = LocalDate.now();

	        // Prepare the SQL query with the conditions for current date, non-null reference_number, and excluded payment_status
	        String query = "SELECT SUM(total) FROM transact WHERE DATE(`current_date`) = ? AND ref_num IS NOT NULL AND payment_status IS NOT NULL AND payment_status <> 'Declined'";

	        // Create a prepared statement
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setDate(1, java.sql.Date.valueOf(currentDate));

	        // Execute the query and retrieve the result
	        ResultSet resultSet = statement.executeQuery();

	        // Retrieve the total income value
	        if (resultSet.next()) {
	            todayIncome = resultSet.getDouble(1);
	        }

	        conn.close();
	    } catch (SQLException e) {
	        System.out.println("Error calculating today's income: " + e.getMessage());
	    }

	    return todayIncome;
	}

}
