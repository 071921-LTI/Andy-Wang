package com.lti.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.lti.models.UserRoles;
import com.lti.util.HibernateUtil;

public class UserRoleHibernate implements UserRoleDao {

	@Override
	public UserRoles addUserRole(UserRoles role) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.save(role);
			tx.commit();
		}
		return role;
	}

	@Override
	public void deleteUserRole(UserRoles role) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.delete(role);
			tx.commit();
		}	
	}

	@Override
	public UserRoles getUserRoleById(int id) {
		UserRoles role= null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			role = s.get(UserRoles.class, id);
		}
		return role;
	}

}
