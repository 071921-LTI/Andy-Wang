package com.lti.daos;

import java.util.List;

import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class UserDB implements UserDao{
//will access the database by using queries
	@Override
	public User getUser(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(User user) {                           
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findUser(String username) throws UserInvalidException {
		// TODO Auto-generated method stub
		return false;
	}

}
