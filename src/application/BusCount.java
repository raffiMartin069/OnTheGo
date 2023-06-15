package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusCount {
    public static int getCount() {
    	
        try {
        	DBConek link = new DBConek();
            Connection conn = link.getConnection();

            //Acquire from data from database.
            String query = "SELECT COUNT(*) FROM bus";
            
            //This holds the query where a statement for SQL execution is stored.
            PreparedStatement statement = conn.prepareStatement(query);

            //Statement will be executed in SQL.
            ResultSet resultSet = statement.executeQuery();
            
            //Checks first column and store it in count
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                conn.close();
                return count;
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error counting buses: " + e.getMessage());
        }

        return 0;
    }
}
