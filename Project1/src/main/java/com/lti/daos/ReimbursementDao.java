package com.lti.daos;

import java.util.List;
import com.lti.models.Reimbursement;

public interface ReimbursementDao {

	Reimbursement getReimburseById(int id);
	List<Reimbursement> getReimburse();
	Reimbursement addReimburse(Reimbursement reimburse);
	void removeReimburse(Reimbursement reimburse);
	void updateReimburse(Reimbursement reimburse);
	String getReimburseStatus(int reimbId);
	String getReimburseType(int reimbId);
	
}
