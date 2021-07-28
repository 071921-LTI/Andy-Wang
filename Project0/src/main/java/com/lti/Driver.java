package com.lti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lti.controllers.MenuScreen;
import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.UserNotFoundException;

public class Driver {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "anw15102";
		try(Connection con = DriverManager.getConnection(url,username,password)) {
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuScreen.display();
	}

}