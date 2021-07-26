package com.lti;

import com.lti.controllers.MenuScreen;
import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.UserNotFoundException;

public class Driver {

	public static void main(String[] args) {
		MenuScreen.display();
	}

}