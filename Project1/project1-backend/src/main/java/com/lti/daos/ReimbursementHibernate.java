package com.lti.daos;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.models.Reimbursement;
import com.lti.models.User;
import com.lti.util.HibernateUtil;

public class ReimbursementHibernate implements ReimbursementDao {
	
	
	@Override
	public Reimbursement getReimburseById(int id) {
		Reimbursement reimburse= null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			reimburse = s.get(Reimbursement.class, id);
		}
		return reimburse;
	}

	@Override
	public List<Reimbursement> getReimburse() {
		List<Reimbursement> reimburses = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			// Use the classname that been mapped, not the table name
			reimburses= s.createQuery("FROM Reimbursement", Reimbursement.class).list();
			
		}
		return reimburses;
	}

	@Override
	public Reimbursement addReimburse(Reimbursement reimburse) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.save(reimburse);
			tx.commit();
		}
		return reimburse;
	}	
	
//	@Override
//	public String getReimburseStatus(int reimbId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getReimburseType(int reimbId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void updateReimburse(Reimbursement reimburse) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.update(reimburse);
			tx.commit();
		}
	}

	@Override
	public void removeReimburse(Reimbursement reimburse) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = s.beginTransaction();
			s.delete(reimburse);
			tx.commit();
		}	
	}

	@Override
	public List<Reimbursement> getReimburseByStatus(ReimburseStatus status) {
		List<Reimbursement> reimburses = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			reimburses = s.createQuery("FROM Reimbursement R WHERE R.reimbStatusId = " + status.getStatusId(),Reimbursement.class).list();
		}
		return reimburses;
	} 

	@Override
	public List<Reimbursement> getReimburseByType(ReimburseType type) {
		List<Reimbursement> reimburses = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			reimburses = s.createQuery("FROM Reimbursement R WHERE R.reimbTypeId = " + type.getTypeId(),Reimbursement.class).list();
		}
		return reimburses;
	}
		

}
