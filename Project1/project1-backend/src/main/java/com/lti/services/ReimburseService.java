package com.lti.services;

import java.util.List;

import com.lti.exceptions.ReimbursementNotFoundExeception;
import com.lti.models.ReimburseStatus;
import com.lti.models.Reimbursement;

public interface ReimburseService {
	public abstract boolean addReimburse(Reimbursement reimburse);
	public abstract Reimbursement RemoveReimburse(Reimbursement reimburse) throws ReimbursementNotFoundExeception;
	public abstract boolean ChangeReimburseStatus(Reimbursement reimburse, ReimburseStatus Status);
	public abstract List<Reimbursement> GetReimbursesByStatus(ReimburseStatus status);
	public abstract boolean UpdateReimbursement(Reimbursement reimburse);

	
}
