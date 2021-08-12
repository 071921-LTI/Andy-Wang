package com.lti.services;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class AuthServiceImpl implements AuthServices {

	@Override
	public User login(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authorize(String token) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}


}
