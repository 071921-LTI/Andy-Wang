package com.lti.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lti.exceptions.UserNotFoundException;
import com.lti.models.User;
import com.lti.models.UserRoles;
import com.lti.util.HibernateUtil;

public class UserHibernate implements UserDao {

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		User user= null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			user = s.get(User.class, id);
		}
		return user;
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		User user= null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			user = s.get(User.class, username);
		}
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			// Use the classname that been mapped, not the table name
			users = s.createQuery("FROM User", User.class).list();
			
		}
		return users;
	}

	@Override
	public User addUser(User user) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		}
		return user;
	}

	@Override
	public void deleteUser(User user) throws UserNotFoundException {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.delete(user);
			tx.commit();
		}	
	}


	@Override
	public void updateUser(User user) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.update(user);
			tx.commit();
		}
	}

}
