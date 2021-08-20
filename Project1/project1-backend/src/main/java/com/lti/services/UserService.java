package com.lti.services;

import java.util.List;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public interface UserService {

	public abstract boolean addUser(User user);
	public abstract List<User> getUsers();
	public abstract User getUserById(int id) throws UserNotFoundException;	
	public abstract User getUserByUsername(String username) throws UserNotFoundException;
	public abstract boolean deleteUser(User user);
	public abstract boolean updateUser(User user);
}
