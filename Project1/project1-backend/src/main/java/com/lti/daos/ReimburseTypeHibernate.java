package com.lti.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.util.HibernateUtil;

public class ReimburseTypeHibernate implements ReimburseTypeDao{

	@Override
	public ReimburseType addReimburseType(ReimburseType type) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.save(type);
			tx.commit();
		}
		return type;

	}

	@Override
	public void deleteReimburseType(ReimburseType type) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.delete(type);
			tx.commit();
		}	
	}

	@Override
	public ReimburseType getReimburseTypeById(int id) {
		ReimburseType type = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			type = s.get(ReimburseType.class, id);
		}
		return type;
	}

}
