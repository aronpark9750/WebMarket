package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	final static String USER_DATABASE = "jdbc:mysql://localhost:3306/productDB";
	final static String USER_NAME = "root";
	final static String USER_PASSWORD ="1234";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(USER_DATABASE, USER_NAME, USER_PASSWORD);
	}
}
