package com.lti.daos;

import com.lti.models.ReimburseType;

public interface ReimburseTypeDao {
	ReimburseType addReimburseType(ReimburseType Type);
	ReimburseType getReimburseTypeById(int id);
	void deleteReimburseType(ReimburseType type);
}
