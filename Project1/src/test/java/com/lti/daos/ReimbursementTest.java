package com.lti.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.models.Reimbursement;
import com.lti.models.User;
import com.lti.models.UserRoles;

public class ReimbursementTest {
	private ReimbursementDao rd = new ReimbursementHibernate();
	UserRoles employee = new UserRoles(2,"employee");
	UserRoles manager = new UserRoles(1,"manager");
	User user = new User("username", "password", "first", "last", "employee@email.com", employee);
	ReimburseType type = new ReimburseType(1);
	ReimburseStatus status= new ReimburseStatus(1);
	Reimbursement reimburse = new Reimbursement(10, new Date(),user, type, status);
	Reimbursement reimburse1 = new Reimbursement(100, new Date(), user, type, status);
	
	@Test
	public void getReimburseById() {
		
	}
	
	@Test
	public void getReimburse() {
		List<Reimbursement> reimb = new ArrayList<>();
		reimb.add(reimburse1);
		reimb.add(reimburse);
		assertEquals(rd.getReimburse(), reimb);
	}
	
	@Test
	public void addReimburseValid() {
		assertEquals(rd.addReimburse(new Reimbursement(10, new Date() ,user,type, status)), 3);
	}
	
	@Test
	public void addReimburseInvalid() {
		assertEquals(rd.addReimburse(reimburse),-1);
	}
	
	@Test
	public void getReimburseStatus() {
		assertEquals(rd.getReimburseStatus(1),"Pending");
	}
	
	@Test
	public void getReimburseType() {
		assertEquals(rd.getReimburseType(1),"lodging");
	}
	
//	@Test
//	public void updateReimburse() {
//		reimburse.setReimbAmount(20);
//		assertEquals(rd.updateReimburse(reimburse),1);
//	}
//	
//	@Test
//	public void deleteReimburseValid() {
//		assertEquals(rd.removeReimburse(reimburse1),1);
//	}
//	@Test
//	public void deleteReimburseInvalid() {
//		assertEquals(rd.removeReimburse(new Reimbursement(10, new Date() ,user, type, status)), -1);
//	}

}
