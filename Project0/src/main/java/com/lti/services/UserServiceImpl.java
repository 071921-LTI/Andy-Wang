package com.lti.services;

import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class UserServiceImpl implements UserService {
	private String fileName;
	private UserDao ud;

	public UserServiceImpl(String file) {
		this.fileName = file;
		this.ud = new UserFile(file);
	}
	@Override
	public boolean addUser(User user) {
		//Maybe have some additional business logic here
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
