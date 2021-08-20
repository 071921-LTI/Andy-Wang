package com.lti.daos.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.models.UserRoles;
import com.lti.services.AuthServiceImpl;
import com.lti.services.AuthServices;
import com.lti.services.UserService;
import com.lti.services.UserServiceImpl;
import com.lti.util.ConnectionUtil;
import com.lti.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	private AuthServices as = new AuthServiceImpl();
	private UserService ud = new UserServiceImpl();
	private static MockedStatic<HibernateUtil> mockedHibernateUtil;
	private static SessionFactory sf;
	private static UserRoles employee = new UserRoles(2,"employee");
	private static UserRoles manager = new UserRoles(1,"manager");
	private static User empl = new User(2, "first", "last","username", "password", "employee@email.com", employee);
	
	private static User man = new User(1,"first", "last","manager", "password","manager@email.com", manager);
	
	public static SessionFactory getH2Connection() {
		if (sf == null || sf.isClosed()) {
			Configuration cg = new Configuration();
			cg.configure("hibernate.cfg.xml");
			cg.setProperty("hibernate.connection.url",
					"jdbc:h2:~/test;INIT=runscript from 'src/test/resources/setup.sql'");
			cg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			cg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
			cg.setProperty("hibernate.hbm2ddl.auto", "create");
			sf = cg.buildSessionFactory();
		}
		return sf;
	}

	@BeforeAll
	public static void init() throws SQLException {
		/*
		 * Mocking the ConnectionUtil class for the getConnectionFromEnv method to
		 * return a connection to the H2 while the mock is "open".
		 */
		mockedHibernateUtil = Mockito.mockStatic(HibernateUtil.class);
		mockedHibernateUtil.when(HibernateUtil::getSessionFactory).then(I -> getH2Connection());
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			// Use the classname that been mapped, not the table name
			Transaction tx = s.beginTransaction();		
			s.save(manager);
			s.save(employee);
			s.save(man);	
			s.save(empl);
			tx.commit();	
		}
	}

	@AfterAll
	public static void end() {
		/*
		 * Closing resource, mocked behavior ends.
		 */
		mockedHibernateUtil.close();
	}
	@Order(1)
	@Test
	public void connectionTest() {
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			assertNotNull(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test 
	public void loginTest() throws UserNotFoundException {
		assertEquals(as.login("username", "password"),empl);
	}
	
	@Order(3)
	@Test 
	public void loginTestInvalid() throws UserNotFoundException {
		assertEquals(as.login("username", "password1"),null);
	}
	
	@Order(4)
	@Test 
	public void authorizeManagerTest() throws UserNotFoundException {
		assertEquals(as.authorizeManager("1:manager"),true);
	}
	
	@Order(5)
	@Test 
	public void authorizeManagerInvalidTest() throws UserNotFoundException {
		assertEquals(as.authorizeManager("1:employee"),false);
	}
	
	@Order(6)
	@Test 
	public void authorizeTest() throws UserNotFoundException {
		assertEquals(as.authorize("1:employee"),false);
	}
	
	@Order(6)
	@Test 
	public void authorizeTestInvakid() throws UserNotFoundException {
		assertEquals(as.authorize("1:manager"),true);
	}
}

