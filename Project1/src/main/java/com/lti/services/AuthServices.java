package com.lti.services;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public interface AuthServices {
	public abstract User login(String username, String password) throws UserNotFoundException;
	public abstract boolean authorize(String token) throws UserNotFoundException;
}
