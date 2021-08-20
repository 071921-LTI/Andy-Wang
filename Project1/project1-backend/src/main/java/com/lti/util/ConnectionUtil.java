package com.lti.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionUtil {
	
private static Connection con;
private static Logger log = LogManager.getRootLogger();
	
	public static Connection getConnectionFromEnv() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			log.error("Exception was thrown: " + e.fillInStackTrace());
		}
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		if(con == null || con.isClosed()) {
			con = DriverManager.getConnection(url, username, password);
		} 
		
		return con;
	}
}
