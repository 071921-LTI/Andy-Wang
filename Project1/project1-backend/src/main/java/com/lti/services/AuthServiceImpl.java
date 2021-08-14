package com.lti.services;

import com.lti.daos.UserDao;
import com.lti.daos.UserHibernate;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class AuthServiceImpl implements AuthServices {
	UserDao ud = new UserHibernate();

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		User user = ud.getUserByUsername(username);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public boolean authorizeManager(String token) throws UserNotFoundException {
		String[] stringArr = token.split(":");
		int id = Integer.parseInt(stringArr[0]);
		String role = stringArr[1];
		role = role.toLowerCase();
		User user = ud.getUserById(id);
		if(user.getRoleid().getRole().equals(role) && role.equals("manager")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean authorize(String token) throws UserNotFoundException {
		String[] stringArr = token.split(":");
		int id = Integer.parseInt(stringArr[0]);
		String role = stringArr[1];
		role = role.toLowerCase();
		User user = ud.getUserById(id);
		if(user.getRoleid().getRole().equals(role)) {
			return true;
		} else {
			return false;
		}
	}


}
