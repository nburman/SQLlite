package code;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectSQLite2 {
	
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\Users\\Neil\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\a5uc61x8.default\\cookies.sqlite");
            connection.setAutoCommit(false); // COMMIT required
                        
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM moz_cookies");
            
            resultSet = statement.executeQuery("SELECT changes()");
            System.out.println("Rows deleted : " + resultSet.getInt(1));

            // Commit it
            connection.commit();

            System.out.println("\nDelete successfully completed!");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
