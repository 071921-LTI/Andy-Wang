package com.lti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lti.controllers.MenuScreen;
import com.lti.daos.ItemsDB;
import com.lti.daos.ItemsDao;
import com.lti.daos.UserDB;
import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.UserNotFoundException;

public class Driver {

	public static void main(String[] args) {
		UserDao ud = new UserDB();
		ItemsDao id = new ItemsDB();
		MenuScreen.display();
	}

}