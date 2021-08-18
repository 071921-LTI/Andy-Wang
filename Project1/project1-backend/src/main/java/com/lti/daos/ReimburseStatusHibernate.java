package com.lti.daos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lti.models.ReimburseStatus;
import com.lti.models.User;
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
	
	@Override
	public ReimburseStatus getReimburseStatusByString(String str) {
		ReimburseStatus status= null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<ReimburseStatus> cq = cb.createQuery(ReimburseStatus.class);
			Root<ReimburseStatus> root = cq.from(ReimburseStatus.class);
			
			Predicate predicateForName = cb.equal(root.get("status"), str);
			
			cq.select(root).where(predicateForName);
			status = s.createQuery(cq).getSingleResult();
		}
		return status;
	}

}
