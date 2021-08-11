package com.lti.daos;

import java.util.List;
import com.lti.models.Reimbursement;

public interface ReimbursementDao {

	Reimbursement getReimburseById(int id);
	List<Reimbursement> getReimburse();
	int addReimburse(Reimbursement reimburse);
	String getReimburseStatus(int reimbId, int reimbStatId);
	String getReimburseType(int reimbId, int reimbTypeId);
	int updateReimburse(Reimbursement reimburse);
}
