package com.lti.daos;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public interface UserDao {
	public abstract User getUser(String username) throws UserNotFoundException;
	public abstract boolean addUser(User user);
}
