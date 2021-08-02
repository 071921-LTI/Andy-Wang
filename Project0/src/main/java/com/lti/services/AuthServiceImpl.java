package com.lti.services;

import com.lti.daos.UserDB;
import com.lti.daos.UserDao;
import com.lti.daos.UserFile;
import com.lti.exceptions.AuthException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;

public class AuthServiceImpl implements AuthService{
	
	private UserDao ud;
	private String user;
	
	public AuthServiceImpl(String file) {
		this.user = file;
		this.ud = new UserDB(file);
	}
	
	// With this implementation AuthException isn't needed
	@Override
	public boolean login(User loguser) throws AuthException {
		/*
		 * Compare incoming user info with user info persisted
		 */
		try {
			User persistedUser = ud.getUser(loguser.getUsername());
			if(persistedUser.getPassword().equals(loguser.getPassword())) {
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
