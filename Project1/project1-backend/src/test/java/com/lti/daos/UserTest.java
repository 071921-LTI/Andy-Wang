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

import javax.persistence.NoResultException;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
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
	public void getByIdExists() throws UserNotFoundException {
		assertEquals(ud.getUserById(1), man);
	}
	
	@Order(3)
	@Test
	public void getByIdDoesNotExists() {
		assertEquals(ud.getUserById(100),null);
	}
	
	@Order(4)
	@Test
	public void getByUsernameExists(){
		
		assertEquals(ud.getUserByUsername("manager"), man);
	}
	
	@Order(5)
	@Test
	public void getByUsernameDoesNotExists() {
		assertThrows(NoResultException.class, () -> ud.getUserByUsername("Test"));
	}
	
	@Order(6)
	@Test
	public void addUserValid() {
		
		assertEquals(ud.addUser(new User(10,"newfirst", "newlast","newusername", "newpass","newuser@email.com", employee)).getId(), 4);
	}
	
	@Order(7)
	@Test
	public void addUserInvalid() {
		assertThrows(ConstraintViolationException.class, () -> ud.addUser(empl));
	}
	
	@Order(8)
	@Test
	public void getUsers() {
	List<User> users = new ArrayList<>();
	users.add(man);
	//users.add(empl);
	users.add(new User(4,"newfirst", "newlast","newusername", "newpass","newuser@email.com", employee));
	System.out.println(ud.getUsers());
	assertEquals(ud.getUsers(), users);
	}
	@Order(9)
	@Test
	public void deleteUserValid() throws UserNotFoundException {
		ud.deleteUser(empl);
		assertEquals(ud.getUserById(empl.getId()),null);
	}
	
	@Test
	public void deleteUserInvalid() throws UserNotFoundException {
		assertThrows(Exception.class, () -> ud.deleteUser(new User(10,manager)));
	}
}
