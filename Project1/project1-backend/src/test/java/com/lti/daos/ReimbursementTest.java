package com.lti.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.models.Reimbursement;
import com.lti.models.User;
import com.lti.models.UserRoles;
import com.lti.util.HibernateUtil;

@ExtendWith(MockitoExtension.class)
public class ReimbursementTest {

	private UserDao ud = new UserHibernate();
	private static MockedStatic<HibernateUtil> mockedHibernateUtil;
	private static SessionFactory sf;
	private static UserRoles employee = new UserRoles(2,"employee");
	private static UserRoles manager = new UserRoles(1,"manager");
	private static User empl = new User(2, "first", "last","username", "password", "employee@email.com", employee);
	private static User man = new User(1,"first", "last","manager", "password","manager@email.com", manager);
	private static ReimbursementDao rd = new ReimbursementHibernate();
	
	private static ReimburseType type = new ReimburseType(1,"lodging");
	private static ReimburseStatus status= new ReimburseStatus(1,"pending");
	private static Reimbursement reimburse = new Reimbursement(10, new Date(),empl, type, status);
	private static Reimbursement reimburse1 = new Reimbursement(100, new Date(), empl, type, status);
	private static Reimbursement reimburse2 = new Reimbursement(200, new Date(),empl,type, status);
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
			s.save(status);
			s.save(type);
			s.save(reimburse);
			s.save(reimburse1);
			
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
	@Test
	public void getReimburseById() {
		
	}
	
	@Test
	public void getReimburse() {
		assertEquals(rd.getReimburse().size(), 2);
	}
	
	@Test
	public void addReimburseValid() {
		assertEquals(rd.addReimburse(reimburse2).getReimbId(), 3);
	}
	
	
	@Test
	public void getReimburseStatus() {
		assertEquals(rd.getReimburseByStatus(status).size(),3);
	}
	
	@Test
	public void getReimburseType() {
		assertEquals(rd.getReimburseByType(type).size(),2);
	}
	
	@Test
	public void updateReimburse() {
		reimburse.setReimbAmount(20);
		rd.updateReimburse(reimburse);
		assertEquals(reimburse.getReimbAmount(),20);
	}
	
	@Test
	public void deleteReimburseValid() {
		rd.removeReimburse(reimburse);
		assertEquals(rd.getReimburseById(reimburse.getReimbId()),null);
	}

}
