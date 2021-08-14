package com.lti.daos;

import java.util.List;

import com.lti.models.ReimburseStatus;
import com.lti.models.ReimburseType;
import com.lti.models.Reimbursement;

public interface ReimbursementDao {

	Reimbursement getReimburseById(int id);
	List<Reimbursement> getReimburse();
	List<Reimbursement> getReimburseByStatus(ReimburseStatus status);
	List<Reimbursement> getReimburseByType(ReimburseType type);
	Reimbursement addReimburse(Reimbursement reimburse);
	void removeReimburse(Reimbursement reimburse);
	void updateReimburse(Reimbursement reimburse);
	
}
