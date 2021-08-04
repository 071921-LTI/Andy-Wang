package com.lti.services;

import com.lti.daos.UserDB;
import com.lti.daos.UserDao;
import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class UserServiceImpl implements UserService {
	private UserDao ud;

	public UserServiceImpl(String file) {
		this.ud = new UserDB(file);
	}
	
	@Override
	public boolean addUser(User user) {
		return ud.addUser(user);
	}

	@Override
	public User getUser(String username) throws UserNotFoundException {
		return ud.getUser(username);
	}
	@Override
	public boolean findUser(String username) throws UserInvalidException {
		
		return ud.findUser(username);
	}

}
