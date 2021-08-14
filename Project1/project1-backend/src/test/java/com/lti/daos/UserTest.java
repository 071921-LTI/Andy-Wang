package com.lti.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lti.daos.UserDao;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.models.UserRoles;
import com.lti.util.ConnectionUtil;
import com.lti.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	private UserDao ud = new UserHibernate();
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
	}

	@AfterAll
	public static void end() {
		/*
		 * Closing resource, mocked behavior ends.
		 */
		mockedHibernateUtil.close();
	}
	@Test
	public void connectionTest() {
		try {
			Connection con = ConnectionUtil.getConnectionFromEnv();
			assertNotNull(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIdExists() throws UserNotFoundException {
		
		assertEquals(ud.getUserById(1), man);
	}
	
	@Test
	public void getByIdDoesNotExists() {
		assertEquals(ud.getUserById(100),null);
	}
	
	@Test
	public void getByUsernameExists(){
		assertEquals(ud.getUserByUsername("username"), empl);
	}
	
	@Test
	public void getByUsernameDoesNotExists() {
		assertEquals(ud.getUserByUsername("Test"),null);
	}
	
	@Test
	public void addUserValid() {
		
		assertEquals(ud.addUser(new User("first1", "last1","manager1", "password1","manager1@email.com", manager)), 4);
	}
	
	@Test
	public void addUserInvalid() {
		assertEquals(ud.addUser(empl), -1);
	}
	
	@Test
	public void getUsers() {
	List<User> users = new ArrayList<>();
//	users.add(man);
//	users.add(empl);
	assertEquals(ud.getUsers(), users);
	}
	
//	@Test
//	public void deleteUserValid() throws UserNotFoundException {
//		assertEquals(ud.deleteUser( new User("Admin", "password", 1)), 1);
//	}
//	
//	@Test
//	public void deleteUserInvalid() throws UserNotFoundException {
//		assertEquals(ud.deleteUser( new User("Adminnew", "password", 1)), 0);
//	}
}
