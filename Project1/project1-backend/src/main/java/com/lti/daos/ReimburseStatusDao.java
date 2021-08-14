package com.lti.daos;

import com.lti.models.ReimburseStatus;

public interface ReimburseStatusDao {
	ReimburseStatus addReimburseStatus(ReimburseStatus Status);
	void deleteReimburseStatus(ReimburseStatus Status);
	ReimburseStatus getReimburseStatusById(int id);
	
}
