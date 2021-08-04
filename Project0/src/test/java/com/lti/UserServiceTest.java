
package com.lti;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lti.daos.UserDB;
import com.lti.daos.UserDao;
import com.lti.exceptions.UserInvalidException;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
		@Mock
		private UserDao ud;
		
		@InjectMocks
		private UserService us = new UserServiceImpl("Customer");
		@Test
		public void getUserTest() throws UserNotFoundException {
			/*
			 * Questionable test, for demo purposes
			 */
			User sampleCustomer = new User();
			sampleCustomer.setUsername("Test");
			sampleCustomer.setPassword("pass");
		
			//ud= Mockito.mock(UserDao.class);
	
			Mockito.when(ud.getUser("Test")).thenReturn(sampleCustomer);
		
			User expected = new User();
			expected.setUsername("Test");
			expected.setPassword("pass");
			assertEquals(expected,us.getUser("Test"));
		}
		
		@Test
		public void findUserErrorTest() throws UserInvalidException {
		//	UserDao ud= Mockito.mock(UserDao.class);
			Mockito.when(ud.findUser("Test2")).thenThrow(new UserInvalidException());
			assertThrows(UserInvalidException.class,() ->us.findUser("Test2"));
		}
		
		@Test
		public void findUserTest() throws UserInvalidException {
			User sampleCustomer = new User();
			sampleCustomer.setUsername("Test");
			sampleCustomer.setPassword("pass");
			
		
		//	UserDao ud= Mockito.mock(UserDao.class);
			us.addUser(sampleCustomer);
			Mockito.when(ud.findUser("Test")).thenReturn(true);
		
			boolean expected =true;
			assertEquals(expected, us.findUser("Test"));
		}
		
	}

