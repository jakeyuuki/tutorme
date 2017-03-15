package tutorme.models.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection connection;
	static String DBPath = "jdbc:mysql://localhost:3307/";
	static String DBName = "tutorme";
	static String password = "";
//	static String DBPath = "jdbc:mysql://tutorme.c2a8la3i1scy.ap-southeast-2.rds.amazonaws.com/";
//	static String DBName = "Tutor_Me";
	static String username = "root";
//	static String password = "abcd1234";

	static Connection getNewDBConnection(int retry) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(DBPath + DBName, username, password);
		} catch (Exception e) {
			if(retry != 0)
				return getNewDBConnection(--retry);
			else
				e.printStackTrace();
		}
		return connection;
	}

	// DBConnection Method to connect to  Database
	public static Connection connect(){
		if(JUnit.ON)
			return JUnit.getDBConnection(DBPath, DBName, username, password);
		try {
			return connection != null && connection.isValid(10) ? connection : (connection = getNewDBConnection(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return (connection = getNewDBConnection(5));
		}
	}

}
