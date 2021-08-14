package com.lti.daos;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lti.models.ReimburseStatus;
import com.lti.util.HibernateUtil;

public class ReimburseStatusHibernate implements ReimburseStatusDao{

	@Override
	public ReimburseStatus addReimburseStatus(ReimburseStatus status) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.save(status);
			tx.commit();
		}
		return status;
	}

	@Override
	public void deleteReimburseStatus(ReimburseStatus status) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.delete(status);
			tx.commit();
		}	
	}

	@Override
	public ReimburseStatus getReimburseStatusById(int id) {
		ReimburseStatus status = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			status = s.get(ReimburseStatus.class, id);
		}
		return status;
	}

}
