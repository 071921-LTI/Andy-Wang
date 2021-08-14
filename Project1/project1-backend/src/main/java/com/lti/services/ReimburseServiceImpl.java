package com.lti.services;

import java.util.List;

import com.lti.daos.ReimburseStatusDao;
import com.lti.daos.ReimburseStatusHibernate;
import com.lti.daos.ReimbursementDao;
import com.lti.daos.ReimbursementHibernate;
import com.lti.exceptions.ReimbursementNotFoundExeception;
import com.lti.models.ReimburseStatus;
import com.lti.models.Reimbursement;

public class ReimburseServiceImpl implements ReimburseService {

	ReimbursementDao rd = new ReimbursementHibernate();
	ReimburseStatusDao sd = new ReimburseStatusHibernate();
	
	@Override
	public boolean addReimburse(Reimbursement reimburse) {
		if (rd.addReimburse(reimburse) == null) {
			return false;
		}else {
			return true;
		}
		
		
	}

	@Override
	public Reimbursement RemoveReimburse(Reimbursement reimburse) throws ReimbursementNotFoundExeception {
		rd.removeReimburse(reimburse);
		return null;
	}

	@Override
	public boolean ChangeReimburseStatus(Reimbursement reimburse ,ReimburseStatus Status) {
		if (rd.getReimburseById(reimburse.getReimbId()) == null) {
			return false;
		}else {
			reimburse.setReimbStatusId(Status);
			rd.updateReimburse(reimburse);
			return true;
		}
	}

	@Override
	public List<Reimbursement> GetReimbursesByStatus(ReimburseStatus status) {
		return rd.getReimburseByStatus(status);
	}

	@Override
	public boolean UpdateReimbursement(Reimbursement reimburse) {
		rd.updateReimburse(reimburse);
		return true;
	}

}
