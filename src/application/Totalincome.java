package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Totalincome {

	public static double getTotal() {
		
		double totalIncome = 0.0;

        try {
            DBConek link = new DBConek();
            Connection conn = link.getConnection();

         // Acquire data from the database, excluding rows with null reference_number and excluded payment_status
            String query = "SELECT SUM(total) AS total_income FROM transact WHERE ref_num IS NOT NULL AND payment_status IS NOT NULL AND payment_status <> 'Declined'";


            // Create a statement and execute the query
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Retrieve the result using the alias
            if (resultSet.next()) {
                totalIncome = resultSet.getDouble("total_income");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error calculating total income: " + e.getMessage());
        }
        return totalIncome;
	}
}
