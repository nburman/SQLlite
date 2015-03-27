package code;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Random;

public class ConnectSQLite3 {
	
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        
        String sSql = null;
        
        //note a single Random object is reused here
        Random randomGenerator = new Random();
        
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\Users\\Neil\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\a5uc61x8.default\\cookies.sqlite");
            connection.setAutoCommit(false); // COMMIT required
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT VALUE FROM moz_cookies");
            while (resultSet.next()) {
                System.out.println("VALUE:" + resultSet.getString("VALUE"));
            }

            sSql = "UPDATE moz_cookies SET VALUE = 'i don''t want your cookies';";
            statement.executeUpdate(sSql);

            sSql = "UPDATE moz_cookies SET "
            		+ "appId = " + randomGenerator.nextInt(1000000) + ", "
            		+ "inBrowserElement = " + randomGenerator.nextInt(1000000) + ", "
            		//+ "name = 'nobcheese',"
            		//+ "path = '',"
            		+ "isSecure = " + randomGenerator.nextInt(1000000) + ", "
            		+ "isHttpOnly = " + randomGenerator.nextInt(1000000) + ", "
            		+ "lastAccessed = " + randomGenerator.nextInt(1000000) + ", "
            		+ "creationTime = " + randomGenerator.nextInt(1000000);
            statement.executeUpdate(sSql);
            
            // Commit it
            connection.commit();
            
            System.out.println("\nQueries successfully completed!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
