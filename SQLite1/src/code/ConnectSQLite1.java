package code;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.ResultSetMetaData;

public class ConnectSQLite1 {
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
			resultSet = statement.executeQuery("SELECT * FROM moz_cookies");

			String[] columnNames = getColumnNameArray(resultSet);
			String[] columnTypes = getColumnTypeArray(resultSet);


			while(resultSet.next())
			{
				// now walk each column in the array...
				for (int i = 1; i < columnNames.length; i++)
				{
					Object oColValue = resultSet.getObject(columnNames[i]);
					System.out.println(columnNames[i] + "\t" + columnTypes[i] + "\t" + oColValue.toString());
				}

				System.out.println("\n");
			}

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

	public static String[] getColumnNameArray(ResultSet rs) {
		String sArr[] = null;
		try {
			ResultSetMetaData rm = rs.getMetaData();
			String sArray[] = new String[rm.getColumnCount()];
			for (int ctr = 1; ctr <= sArray.length; ctr++) {
				String s = rm.getColumnName(ctr);
				sArray[ctr - 1] = s;
			}
			return sArray;
		} catch (Exception e) {
			System.out.println(e);
			return sArr;
		}
	}

	public static String[] getColumnTypeArray(ResultSet rs) {
		String sArr[] = null;
		try {
			ResultSetMetaData rm = rs.getMetaData();
			String sArray[] = new String[rm.getColumnCount()];
			for (int ctr = 1; ctr <= sArray.length; ctr++) {
				String s = rm.getColumnTypeName(ctr);
				sArray[ctr - 1] = s;
			}
			return sArray;
		} catch (Exception e) {
			System.out.println(e);
			return sArr;
		}
	}
}
