package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

public class Monthincome {
	
	public static double getMonthincome () {
		
		double monthIncome = 0.0;
        
		try {
            DBConek link = new DBConek();
            Connection conn = link.getConnection();

            // Get the current month
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

         // Prepare the SQL query with the conditions for current month, non-null reference_number, and excluded payment_status
            String query = "SELECT SUM(total) FROM transact WHERE YEAR(`current_date`) = ? AND MONTH(`current_date`) = ? AND ref_num IS NOT NULL AND payment_status IS NOT NULL AND payment_status <> 'Declined'";


            // Create a prepared statement
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, currentYear);
            statement.setInt(2, currentMonth);

            // Execute the query and retrieve the result
            ResultSet resultSet = statement.executeQuery();

            // Retrieve the total income value
            if (resultSet.next()) {
                monthIncome = resultSet.getDouble(1);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error calculating month's income: " + e.getMessage());
        }

        return monthIncome;

	}

}
