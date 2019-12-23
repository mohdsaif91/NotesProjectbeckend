package com.exapmle.demo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	static Connection con;
	static String url="jdbc:postgresql://localhost:5432/Notes";
	static String password="postgres";
	static String username="postgres";
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection(url, username, password);
		return con;
	}

}
