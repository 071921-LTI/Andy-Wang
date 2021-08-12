package com.lti.daos;

import java.util.List;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.models.UserRoles;

public interface UserDao {

	// if no user is found
	User getUserById(int id) throws UserNotFoundException;
	// if no user is found
	User getUserByUsername(String username) throws UserNotFoundException;
	List<User> getUsers();
	// Should return the id generated
	User addUser(User user);
	// if no user is found
	void updateUser(User user);
	void deleteUser(User user) throws UserNotFoundException;
//	UserRoles getUserRole(User user);
}
