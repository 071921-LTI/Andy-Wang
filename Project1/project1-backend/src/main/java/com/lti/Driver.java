package com.lti;

import java.util.Date;

import com.lti.daos.ReimburseStatusDao;
import com.lti.daos.ReimburseStatusHibernate;
import com.lti.daos.ReimburseTypeDao;
import com.lti.daos.ReimburseTypeHibernate;
import com.lti.daos.ReimbursementDao;
import com.lti.daos.ReimbursementHibernate;
import com.lti.daos.UserDao;
import com.lti.daos.UserHibernate;
import com.lti.daos.UserRoleDao;
import com.lti.daos.UserRoleHibernate;
import com.lti.exceptions.UserNotFoundException;
import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.models.Reimbursement;
import com.lti.models.User;
import com.lti.models.UserRoles;


public class Driver {
	static UserDao ud = new UserHibernate();
	static UserRoleDao rd = new UserRoleHibernate();
	static ReimbursementDao bd = new ReimbursementHibernate();
	static ReimburseTypeDao td = new ReimburseTypeHibernate();
	static ReimburseStatusDao sd = new ReimburseStatusHibernate();
	
	public static void test() {
		UserRoles employee = new UserRoles(2,"employee");
		UserRoles manager = new UserRoles(1,"manager");
		User empl = new User("first", "last","username", "password", "employee@email.com", employee);
		User man = new User("first", "last","manager", "password",  "manager@email.com", manager);
		//User t = new User("test","test",employee);
		System.out.println(empl);
		
		System.out.println(rd.addUserRole(manager));
		System.out.println(rd.addUserRole(employee));
		
		System.out.println(ud.addUser(empl));
		
		System.out.println(ud.addUser(man));
		
		
		System.out.println("get: " + ud.getUserById(1));
		
		System.out.println("getAll" + ud.getUsers());
		
		System.out.println("empl: " + empl);
		
		empl.setFirstname("Hello");
		
		ud.updateUser(empl);
		
		
		System.out.println("get: " + ud.getUserById(1));
		
		//rd.deleteUserRole(employee);
		
		ud.deleteUser(empl);
		
		
		System.out.println("getAll" + ud.getUsers());
	}
	
	public static void test2() {
		UserRoles employee = new UserRoles(1,"employee");
		User empl = new User("first", "last","username", "password", "employee@email.com", employee);
		ReimburseType type = new ReimburseType(1, "Lodging");
		ReimburseType type1 = new ReimburseType(2, "Food");
		ReimburseStatus status= new ReimburseStatus(1, "Pending");
		
		Reimbursement reimburse = new Reimbursement(10,new Date(),empl,type,status);
		
		System.out.println(rd.addUserRole(employee));
		System.out.println(ud.addUser(empl));
		System.out.println(td.addReimburseType(type));
		System.out.println(sd.addReimburseStatus(status));
		
		System.out.println(reimburse);
		System.out.println(bd.addReimburse(reimburse));
		System.out.println("get: " + bd.getReimburseById(1));
		System.out.println("getAll: " + bd.getReimburse());
		System.out.println("status: " + bd.getReimburseByStatus(status));
		System.out.println("type: " + bd.getReimburseByType(type));
		System.out.println(td.addReimburseType(type1));
		reimburse.setReimbTypeId(type1);
		bd.updateReimburse(reimburse);
		System.out.println("get: " + bd.getReimburseById(1));
		bd.removeReimburse(reimburse);
		System.out.println("getAll: " + bd.getReimburse());
		 
		
	}
	public static void main(String[] args) {
		//System.out.println(ud.getUsers());
		System.out.println(rd.getUserRoleById(2));
	}
}
