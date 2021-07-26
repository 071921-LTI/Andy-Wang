package com.lti.services;

import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.AuthException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class AuthServiceImpl implements AuthService{
	
	private UserDao ud = new UserFile();
	
	// With this implementation AuthException isn't needed
	@Override
	public boolean login(User user) throws AuthException {
		/*
		 * Compare incoming user info with user info persisted
		 */
		try {
			User persistedUser = ud.getUser(user.getUsername());
			if(persistedUser.getPassword().equals(user.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
