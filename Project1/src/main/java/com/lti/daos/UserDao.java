package com.lti.daos;

import java.util.List;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public interface UserDao {

	// if no user is found
	User getUserById(int id) throws UserNotFoundException;
	// if no user is found
	User getUserByUsername(String username) throws UserNotFoundException;
	List<User> getUsers();
	// Should return the id generated
	int addUser(User user);
	// if no user is found
	int deleteUser(int id) throws UserNotFoundException;
	String getUserRole(int id);
	int updateUser(User user);
}
