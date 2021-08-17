package com.lti.services;

import java.util.List;

import com.lti.exceptions.ReimbursementNotFoundExeception;
import com.lti.models.ReimburseStatus;
import com.lti.models.Reimbursement;

public interface ReimburseService {
	public abstract boolean addReimburse(Reimbursement reimburse);
	public abstract boolean RemoveReimburse(Reimbursement reimburse);
	public abstract boolean ChangeReimburseStatus(Reimbursement reimburse, ReimburseStatus Status);
	public abstract List<Reimbursement> GetReimbursesByStatus(ReimburseStatus status);
	public abstract boolean UpdateReimbursement(Reimbursement reimburse);
	public abstract Reimbursement getReimburseById(int id);
	public abstract List<Reimbursement> GetAllReimbursements();

	
}
