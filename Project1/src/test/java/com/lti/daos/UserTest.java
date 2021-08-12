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
	private static MockedStatic<ConnectionUtil> mockedConnectionUtil;
	private static MockedStatic<HibernateUtil> mockedHibernateUtil;
	private static Connection connection;
	UserRoles employee = new UserRoles(2,"employee");
	UserRoles manager = new UserRoles(1,"manager");

	/*
	 * Used to create a connection to our H2/ in memory db instead of "production"
	 * database
	 */
	public static Connection getH2Connection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:h2:~/test");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@BeforeAll
	public static void init() throws SQLException {
		/*
		 * Mocking the ConnectionUtil class for the getConnectionFromEnv method to
		 * return a connection to the H2 while the mock is "open".
		 */
		mockedConnectionUtil = Mockito.mockStatic(ConnectionUtil.class);
		mockedConnectionUtil.when(ConnectionUtil::getConnectionFromEnv).then(I -> getH2Connection());
	}

	@AfterAll
	public static void end() {
			/*
			 * Drops h2 tables after tests.
			 */
			try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
				RunScript.execute(c, new FileReader("teardown.sql"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			/*
			 * Closing resource, mocked behavior ends.
			 */
			mockedConnectionUtil.close();
	}

	@BeforeEach
	public void setUp() {
			/*
			 * Clear previous tables and recreates tables before each tests
			 */
			try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
				RunScript.execute(c, new FileReader("setup.sql"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
		User expected = new User(2, "tom");
		assertEquals(ud.getUserById(1), expected);
	}
	
	@Test
	public void getByIdDoesNotExists() {
		assertThrows(UserNotFoundException.class, ()->ud.getUserById(100));
	}
	
	@Test
	public void getByUsernameExists() throws UserNotFoundException {
		User expected = new User(3, "bill");
		assertEquals(ud.getUserByUsername("John"), expected);
	}
	
	@Test
	public void getByUsernameDoesNotExists() {
		assertThrows(UserNotFoundException.class, ()->ud.getUserByUsername("Test"));
	}
	
	@Test
	public void addUserValid() {
		
		assertEquals(ud.addUser(new User("Test", "Test", employee)), 4);
	}
	
	@Test
	public void addUserInvalid() {
		assertEquals(ud.addUser(new User("John", "pass", employee)), -1);
	}
	
	@Test
	public void getUsers() {
	List<User> users = new ArrayList<>();
	users.add(new User("Admin", "password", employee));
	users.add(new User("John", "pass", employee));
	users.add(new User("Jimmy", "randomPass", employee));
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
